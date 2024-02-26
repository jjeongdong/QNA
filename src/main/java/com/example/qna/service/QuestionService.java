package com.example.qna.service;

import com.example.qna.Exception.DataNotFoundException;
import com.example.qna.entity.Question;
import com.example.qna.entity.SiteUser;
import com.example.qna.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<Question> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return questionRepository.findAllByOrderByCreateDateDesc(pageable);
    }

    @Transactional(readOnly = true)
    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    @Transactional
    public void create(String subject, String content, SiteUser siteUser) {
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(siteUser);
        questionRepository.save(question);
    }

    @Transactional
    public void modify(Long id, String subject, String content) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();

            question.setSubject(subject);
            question.setContent(content);
            question.setModifyDate(LocalDateTime.now());
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }
}
