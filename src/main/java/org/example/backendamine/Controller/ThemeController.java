package org.example.backendamine.Controller;
import lombok.RequiredArgsConstructor;
import org.example.backendamine.Entities.Theme;
import org.example.backendamine.Service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/theme")
@RequiredArgsConstructor
public class ThemeController {


    private final ThemeService themeService;

    @PostMapping
    public ResponseEntity<Theme> createTheme(@RequestBody Theme theme) {
        return ResponseEntity.ok(themeService.createTheme(theme));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable long id, @RequestBody Theme theme) {
        return ResponseEntity.ok(themeService.updateTheme(id, theme));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theme> getThemeById(@PathVariable long id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @GetMapping
    public ResponseEntity<List<Theme>> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    @DeleteMapping("/{id}")
    public void deleteTheme(@PathVariable long id) {
        themeService.deleteTheme(id);
    }
    @PutMapping("question/{themeId}/{questionId}")
    public ResponseEntity<String> assignQuestionToTheme(
            @PathVariable long questionId,
            @PathVariable long themeId) {
        themeService.assignQuestionToTheme(questionId,themeId);
        return ResponseEntity.ok("Question assigned to theme successfully");
    }
    @PutMapping("user/{themeId}/{userId}")
    public ResponseEntity<String> assignUserToTheme(
            @PathVariable long userId,
            @PathVariable long themeId) {
        themeService.assignUserToTheme(userId,themeId);
        return ResponseEntity.ok("Question assigned to theme successfully");
    }
}

