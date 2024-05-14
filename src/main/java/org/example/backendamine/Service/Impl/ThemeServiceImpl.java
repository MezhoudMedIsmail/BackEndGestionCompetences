package org.example.backendamine.Service.Impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.*;
import org.example.backendamine.Repository.QuestionRepository;
import org.example.backendamine.Repository.ThemeRepository;
import org.example.backendamine.Repository.UserRepository;
import org.example.backendamine.Service.ThemeService;
import org.example.backendamine.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        existingTheme.setDepartement(TypeDepartement.valueOf(theme.getDepartement().name()));
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
    public List<User> getUsersByTheme(Long themeId) {
        // Check if the theme exists
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found"));

        // Fetch questions associated with the theme
        List<Question> questions = questionRepository.findByThemeId(theme.getId());

        // Collect all unique users from these questions' responses

        // Convert the set to a list and return
        return questions.stream()
                .flatMap(question -> question.getReponse().stream())
                .map(Reponse::getUser).distinct().collect(Collectors.toList());
    }



}

