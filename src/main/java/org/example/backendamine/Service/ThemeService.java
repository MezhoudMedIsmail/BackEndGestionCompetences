package org.example.backendamine.Service;

import org.example.backendamine.Entities.Theme;

import java.util.List;

public interface ThemeService {
    Theme createTheme(Theme theme);
    Theme updateTheme(long id, Theme theme);
    Theme getThemeById(long id);
    List<Theme> getAllThemes();
    void deleteTheme(long id);
    void assignQuestionToTheme(long questionId, long themeId);
    void assignUserToTheme(long questionId, long themeId);

}
