package org.example.backendamine.Service.Impl;

import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Repository.ThemeRepository;
import org.example.backendamine.Service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService{
    @Autowired
    private ThemeRepository themeRepository;

    @Override
    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme updateTheme(Long id, Theme theme) {
        Theme existingTheme = themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
        existingTheme.setTitle(theme.getTitle());
        existingTheme.setQuestions(theme.getQuestions());
        existingTheme.setUser(theme.getUser());
        return themeRepository.save(existingTheme);
    }

    @Override
    public Theme getThemeById(Long id) {
        return themeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theme not found"));
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public void deleteTheme(Long id) {
        themeRepository.deleteById(id);
    }
}

