package com.example.buyforall;

import com.example.Member.Vo.SecMemberVo;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController{
    
    @GetMapping("/main")
    public String main(Model model, @AuthenticationPrincipal SecMemberVo member) throws Exception {
        System.out.println(member == null ? "member = null" : "member != null");
        //인증 정보 객체 Model에 추가
        model.addAttribute("member",member);
        System.out.println(member);
        model.addAttribute("content", "/main");
        return "/templates";
    }
    
}
