package com.example.javaquiz;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionRepository repo;

    public QuestionController(QuestionRepository repo) {
        this.repo = repo;
    }

    // List all questions for a topic (titles only)
    @GetMapping("/{topic}")
    public List<Map<String,Object>> getQuestionsByTopic(@PathVariable String topic) {
        return repo.findByTopicIgnoreCase(topic).stream()
                .map(q -> {
                    Map<String,Object> m = new HashMap<>();
                    m.put("questionno", (Object) q.getQuestionno());
                    m.put("question", (Object) q.getQuestion());
                    return m;
                })
                .toList();
    }

    // Get specific question by topic + questionno
    @GetMapping("/{topic}/{questionno}")
    public Question getQuestion(@PathVariable String topic, @PathVariable Integer questionno) {
        return repo.findByTopicIgnoreCaseAndQuestionno(topic, questionno).orElse(null);
    }
}

