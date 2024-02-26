package com.example.qna.service;

import com.example.qna.Exception.DataNotFoundException;
import com.example.qna.entity.Answer;
import com.example.qna.entity.Question;
import com.example.qna.entity.SiteUser;
import com.example.qna.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Transactional
    public void create(Question question, String content, SiteUser siteUser) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(siteUser);
        answerRepository.save(answer);
    }

    @Transactional(readOnly = true)
    public Answer getAnswer(Long id) {
        Optional<Answer> answer = answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    @Transactional
    public void modify(Long id, String content) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (optionalAnswer.isPresent()) {
            Answer answer = optionalAnswer.get();

            answer.setContent(content);
            answer.setModifyDate(LocalDateTime.now());
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    @Transactional
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }
}
