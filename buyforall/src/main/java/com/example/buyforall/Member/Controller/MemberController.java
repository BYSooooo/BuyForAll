package com.example.buyforall.Member.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/loginForm")
    public String goLoginForm(Model model) {
        model.addAttribute("content","/views/member/loginForm");
        return "/templates";
    }


}
