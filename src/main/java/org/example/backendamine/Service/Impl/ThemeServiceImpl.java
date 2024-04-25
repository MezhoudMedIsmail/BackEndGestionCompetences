package org.example.backendamine.Service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Question;
import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Entities.User;
import org.example.backendamine.Repository.QuestionRepository;
import org.example.backendamine.Repository.ThemeRepository;
import org.example.backendamine.Repository.UserRepository;
import org.example.backendamine.Service.ThemeService;
import org.example.backendamine.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService{

    private final ThemeRepository themeRepository;

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Override
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme updateTheme(long id, Theme theme) {
        Theme existingTheme = themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
        existingTheme.setTitle(theme.getTitle());
        existingTheme.setQuestions(theme.getQuestions());
        existingTheme.setUser(theme.getUser());
        return themeRepository.save(existingTheme);
    }

    @Override
    public Theme getThemeById(long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public void deleteTheme(long id) {
        themeRepository.deleteById(id);
    }
    @Override
    public void assignQuestionToTheme(long questionId, long themeId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found"));

        theme.getQuestions().add(question);
        themeRepository.save(theme);

    }

    @Override
    public void assignUserToTheme(long userId, long themeId) {
        User user =  userRepository.findById(userId).orElse(null);
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new EntityNotFoundException("Theme not found"));
        theme.getUser().add(user);
        themeRepository.save(theme);
    }

}

