package com.example.javaquiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TopicController {

    private final QuestionRepository repo;

    public TopicController(QuestionRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/topic/{topic}")
    public String showTopic(@PathVariable String topic, Model model) {
        List<Question> questions = repo.findByTopicIgnoreCase(topic);
        model.addAttribute("topic", topic);
        model.addAttribute("questions", questions);
        return "topic-page"; // refers to topic-page.html in templates
    }
}
