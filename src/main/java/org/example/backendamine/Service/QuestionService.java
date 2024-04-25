package org.example.backendamine.Service;

import org.example.backendamine.Entities.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(long id, Question question);
    Question getQuestionById(long id);
    List<Question> getAllQuestions();
    void deleteQuestion(long id);
}
