package com.example.javaquiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByTopicIgnoreCase(String topic);
    Optional<Question> findByTopicIgnoreCaseAndQuestionno(String topic, Integer questionno);
}
