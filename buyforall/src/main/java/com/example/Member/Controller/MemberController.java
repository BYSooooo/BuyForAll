package com.example.Member.Controller;

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
    public @ResponseBody String checkEmail(@RequestParam("memberEmail") String memberEmail, Model model) {
        System.out.println("MemberController : "+memberEmail);
        int checkResult = memberService.checkEmail(memberEmail);
        System.out.println("checkResult : "+checkResult);
        
        return "/templates";

    }
}


