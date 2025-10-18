package com.example.javaquiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PracticeController {

    @GetMapping("/practice")
    public String practicePage(Model model) {
        // Any other page-specific attributes can be added here if needed
        return "practice";
    }


}
