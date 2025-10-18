package com.example.javaquiz;

import jakarta.persistence.*;
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String topic;
    private Integer questionno;

    @Column(length = 5000)
    private String question;

    @Column(length = 10000)
    private String code;

    // No-args constructor (required by JPA)
    public Question() {}

    // Constructor with fields (for easy saving)
    public Question(Integer questionno, String topic, String question, String code) {
        this.questionno = questionno;
        this.topic = topic;
        this.question = question;
        this.code = code;
    }

    // Getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public Integer getQuestionno() { return questionno; }
    public void setQuestionno(Integer questionno) { this.questionno = questionno; }

    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}
