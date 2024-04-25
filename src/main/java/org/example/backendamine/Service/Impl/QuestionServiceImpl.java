package org.example.backendamine.Service.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.backendamine.Entities.Question;
import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Repository.QuestionRepository;
import org.example.backendamine.Repository.ThemeRepository;
import org.example.backendamine.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private  ThemeRepository themeRepository;


    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(long id, Question question) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        existingQuestion.setName(question.getName());
        existingQuestion.setText(question.getText());
        existingQuestion.setResponse(question.getResponse());
        return questionRepository.save(existingQuestion);
    }

    @Override
    public Question getQuestionById(long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public void deleteQuestion(long id) {
        questionRepository.deleteById(id);
    }


}
