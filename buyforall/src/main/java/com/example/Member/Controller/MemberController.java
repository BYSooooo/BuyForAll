package com.example.Member.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.example.Mail.Service.MailService;
import com.example.Member.Service.MemberService;
import com.example.Member.Vo.MemberVo;
import com.example.Member.Vo.SecMemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
    @Autowired
    public MemberService memberService;

    @Autowired
    public MailService mailService;
    
    //로그인 폼으로 이동
    @GetMapping("/loginForm")
    public String goLoginForm(Model model) {
        model.addAttribute("content","/views/member/loginForm");
        return "/templates";
    }
    //회원 가입 폼으로 이동
    @RequestMapping("/joinMemberForm")
    public String goJoinPage(Model model) {
        model.addAttribute("content", "/views/member/joinMemberForm");
        return "/templates";
    }
    //회원 가입 두번째 폼으로 이동
    @RequestMapping(value="/joinFormSe", method = RequestMethod.POST)
    public String goJoinPageSe(Model model, @RequestParam("memberEmail") String memberEmail) {
        model.addAttribute("memberEmail", memberEmail);
        model.addAttribute("content", "/views/member/joinMemberFormSe");
        return "/templates";
    }

    //회원 가입 단계에서 이메일 DB 확인
    @RequestMapping(value="/checkDBEmail", method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> checkEmail(@RequestParam("memberEmail") String memberEmail) {
        
        int checkResult = memberService.checkEmail(memberEmail);
        
        //Ajax로 값을 반환하기 위한 HashMap 생성
        Map<String,Object> returnResult = new HashMap<>();
        //중복되는 이메일이 없을 경우 인증 메일 발송
        if (checkResult == 0) {
            // 인증번호에 사용할 랜덤 변수를 생성해서 받아온다.
            String veriNum = this.mailService.createRandomNumber();
            mailService.createSendMail(memberEmail, veriNum);
            returnResult.put("veriNum", veriNum);
            returnResult.put("result", "인증 메일을 발송했습니다");
            return returnResult;
            
        // 중복되는 이메일이 있을 경우 실패 메시지 반환
        } else {
            returnResult.put("result", "이미 사용중인 이메일입니다.");
            return returnResult;
        }
    }
    //회원 가입 단계에서 회원 아이디 중복 검사 Ajax
    @RequestMapping(value="/checkDBId", method = RequestMethod.POST)
    public @ResponseBody Map<String, String> checkDBId(@RequestParam("memberId") String memberId) {
        int checkIdResult = memberService.checkId(memberId);

        Map<String, String> map = new HashMap<>();
        if(checkIdResult == 0) {
            //중복된 값이 없으므로 가입이 가능하다는 텍스트 반환
            map.put("result", "사용이 가능한 아이디 입니다.");
            map.put("nextButton","abled");
        } else {
            //중복 값이 있으므로 사용이 불가
            map.put("result", "사용중인 아이디입니다. 다른 아이디를 사용하세요.");
            map.put("nextButton","disabled");
        }
        return map;
    }



    //최종 회원 정보 DB 입력
    @RequestMapping(value="/joinMember", method=RequestMethod.POST)
    public String joinMember(@Valid @ModelAttribute MemberVo member, BindingResult bindingResult, 
    @RequestParam("roadAddress") String roadAddress, @RequestParam("extrAddress") String extrAddress, Model model) {

        // Vo Class의 Validation 결과를 통해 문제가 있으면 메시지 반환
        if (bindingResult.hasErrors()) {
            Map<String,String> map = memberService.validate(bindingResult);
            for(String key: map.keySet()) {
                model.addAttribute(key, map.get(key));  
            }
            //Validaion에 사용했던 값들 다시 반환
            model.addAttribute("memberId",member.getMemberId());
            model.addAttribute("memberEmail",member.getMemberEmail());
            model.addAttribute("memberName",member.getMemberName());
            model.addAttribute("postNumber",member.getPostNumber());
            model.addAttribute("roadAddress",roadAddress);
            model.addAttribute("extrAddress",extrAddress);
            model.addAttribute("content","/views/member/joinMemberFormSe");
            
        } else {
            //Validation 문제가 없다면 DB에 저장 단계로 진행
            //검색 주소와 나머지 주소를 합쳐서 주소를 만듦
            String fullAddress = roadAddress + " " + extrAddress;
            member.setMemberAddress(fullAddress);
            memberService.insertMember(member);
            model.addAttribute("content", "/views/member/joinComplete");
        }
        return "/templates";
    }   

    //로그인이 성공 했을 경우 실행할 작업
    @RequestMapping(value="/loginSuccess", method = RequestMethod.POST)
    public String loginSuccess(@AuthenticationPrincipal SecMemberVo member, Model model) {        
        //최종 로그인 일자를 수정한다.
        memberService.updateLastLogin(member);
        //회원 정보를 View로 전달한다.
        model.addAttribute("member", member);
        model.addAttribute("content","/main");
        return "/templates";  

    }
    
    //아이디, 비밀번호 찾기 페이지로 이동
    @GetMapping("/forgetInfo")
    public String forgetInfoPage(Model model) {
        model.addAttribute("content", "/views/member/forgetInfo");
        return "/templates";
    }

    //이메일로 아이디 찾기
    @RequestMapping(value="/searchIdForEmail", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> EmailForId(@RequestParam("memberEmail") String memberEmail) {
        //DB에서 이메일로 아이디 값을 반환
       String resultId = memberService.selectEmailForId(memberEmail);
       //ajax로 되돌려주기 위한 Map 선언
       Map<String,Object> map = new HashMap<String,Object>();
       map.put("result", resultId);
       return map;
    }

    //임시 비밀번호 발급
    @RequestMapping(value="/searchPwd", method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> searchPwd(@RequestParam("memberId") String memberId, @RequestParam("memberEmail") String memberEmail) {
        Map<String,Object> map = new HashMap<>();
        //DB에 일치하는 값이 있는지 확인
        int resultCheckDB = memberService.checkIdAndEmail(memberId,memberEmail);
        //일치하는 값이 있을 경우 임시 비밀번호 생성/메일 발송/DB 업데이트
        if(resultCheckDB == 1) {
            String tempPwd = mailService.createRandomNumber();
            System.out.println("임시 비밀번호 : "+tempPwd);
            mailService.createMailForPwd(memberEmail, tempPwd);
            memberService.modifyPwd(memberEmail,tempPwd);
            map.put("result2", "입력하신 이메일로 임시 비밀번호가 발송되었습니다.");
        } else {
            // 없을 경우 메시지만 반환
            map.put("result2", "입력하신 정보와 일치하는 회원을 찾을 수 없습니다. 다시 확인해주세요.");
        }
        return map;
    }

    //회원 정보 수정 페이지로 이동 시 작업
    @RequestMapping(value="/goModify", method=RequestMethod.GET)
    public String goModify(@AuthenticationPrincipal SecMemberVo member, Model model) {
        model.addAttribute("member", member);
        System.out.println(member); 
        model.addAttribute("content", "/views/member/modifyInfo");
        return "/templates";
    }
    
    
}


