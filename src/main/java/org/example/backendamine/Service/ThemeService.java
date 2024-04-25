package org.example.backendamine.Service;

import org.example.backendamine.Entities.Theme;

import java.util.List;

public interface ThemeService {
    Theme createTheme(Theme theme);
    Theme updateTheme(Long id, Theme theme);
    Theme getThemeById(Long id);
    List<Theme> getAllThemes();
    void deleteTheme(Long id);
}
