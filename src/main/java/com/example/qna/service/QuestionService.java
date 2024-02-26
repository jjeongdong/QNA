package com.example.qna.service;

import com.example.qna.Exception.DataNotFoundException;
import com.example.qna.entity.Question;
import com.example.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}
