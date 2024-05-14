package org.example.backendamine.Service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Question;
import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Repository.QuestionRepository;
import org.example.backendamine.Repository.ReponseRepository;
import org.example.backendamine.Repository.ThemeRepository;
import org.example.backendamine.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final  QuestionRepository questionRepository;

    private final  ThemeRepository themeRepository;
    private final ReponseRepository reponseRepository;


    @Override
    public Question createQuestion(Question question,long id) {
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
        question.setTheme(theme);
        Question q =  questionRepository.save(question);
        theme.getQuestions().add(q);
        themeRepository.save(theme);
        return q;
    }

    @Override
    public Question updateQuestion(long id, Question question) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        existingQuestion.setName(question.getName());
        existingQuestion.setText(question.getText());
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
        Question questionReq = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        Theme theme = themeRepository.findByQuestionsId(id);
        theme.getQuestions().removeIf(question -> question.getId() == id);
        themeRepository.save(theme);
        reponseRepository.deleteAll(questionReq.getReponse());
        questionRepository.deleteById(id);
    }
    @Override
    public List<Question> getQuestionsByThemeId(long themeId) {
        return questionRepository.findByThemeId(themeId);
    }

}
