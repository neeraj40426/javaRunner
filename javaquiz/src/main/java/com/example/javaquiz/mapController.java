package com.example.javaquiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mapController {

    @GetMapping("/index")
    public String indexPage(Model model) {
        // Any other page-specific attributes can be added here if needed
        return "index";
    }
    @GetMapping("/about")
    public String aboutPage(Model model) {
        // Any other page-specific attributes can be added here if needed
        return "about";
    }

}
