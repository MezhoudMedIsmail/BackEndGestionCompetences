package org.example.backendamine.Service;

import org.example.backendamine.Entities.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(Long id, Question question);
    Question getQuestionById(Long id);
    List<Question> getAllQuestions();
    void deleteQuestion(Long id);
}
