package org.example.backendamine.Service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Question;
import org.example.backendamine.Entities.Reponse;
import org.example.backendamine.Entities.Response.ReponseRequest;
import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Entities.User;
import org.example.backendamine.Repository.QuestionRepository;
import org.example.backendamine.Repository.ReponseRepository;
import org.example.backendamine.Repository.ThemeRepository;
import org.example.backendamine.Repository.UserRepository;
import org.example.backendamine.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final  QuestionRepository questionRepository;

    private final  ThemeRepository themeRepository;
    private final ReponseRepository reponseRepository;
    private final UserRepository userRepository;


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
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // First delete all responses associated with the question to avoid foreign key constraint violation.
        reponseRepository.deleteAll(question.getReponse());

        // Remove the question from the theme to maintain integrity.
        Theme theme = themeRepository.findByQuestionsId(id);
        if(theme != null) {
            theme.getQuestions().remove(question);
            themeRepository.save(theme);
        }

        // Now safe to delete the question as no responses are linked to it.
        questionRepository.delete(question);
    }
    @Override
    public List<Question> getQuestionsByThemeId(long themeId) {
        return questionRepository.findByThemeId(themeId);
    }

    @Override
    @Transactional
    public List<Reponse> saveReponses(List<ReponseRequest> reponses, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<Reponse> reponseList = reponses.stream()
                .map(reponseRequest -> {
                    Question question = questionRepository.findById(reponseRequest.getIdQuestion())
                            .orElseThrow(() -> new EntityNotFoundException("Question not found"));
                    Reponse r =  Reponse.builder()
                            .reponse(reponseRequest.getReponse())
                            .user(user)
                            .build();
                    question.getReponse().add(r);
                    questionRepository.save(question);
                    return r;
                }).collect(Collectors.toList());

        return reponseRepository.saveAll(reponseList);
    }

}
