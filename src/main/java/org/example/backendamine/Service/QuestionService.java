package org.example.backendamine.Service;

import org.example.backendamine.Entities.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question,long id);
    Question updateQuestion(long id, Question question);
    Question getQuestionById(long id);
    List<Question> getAllQuestions();
    void deleteQuestion(long id);
    List<Question> getQuestionsByThemeId(long themeId);
}
