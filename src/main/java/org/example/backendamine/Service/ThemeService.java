package org.example.backendamine.Service;

import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Entities.ThemeDetailsDto;
import org.example.backendamine.Entities.User;

import java.util.List;

public interface ThemeService {
    Theme createTheme(Theme theme);
    Theme updateTheme(long id, Theme theme);
    Theme getThemeById(long id);
    List<Theme> getAllThemes();
    void deleteTheme(long id);
    void assignQuestionToTheme(long questionId, long themeId);
    List<User> getUsersByTheme(Long themeId);
}
