package com.example.Member.Controller;

import java.util.HashMap;
import java.util.Map;

import com.example.Mail.Service.MailService;
import com.example.Member.Service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}


