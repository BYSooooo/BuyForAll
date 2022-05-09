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
        model.addAttribute("content", "/main");
        return "/templates";
    }
    
    
}
