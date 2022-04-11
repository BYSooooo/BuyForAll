package com.example.buyforall;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController{
    
    @GetMapping("/main")
    public String main(Model model) throws Exception {
        model.addAttribute("content", "/main");
        System.out.println("MainController 경유 확인");
        return "/templates";
    }
    
    
}