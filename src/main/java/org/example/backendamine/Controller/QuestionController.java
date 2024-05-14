package org.example.backendamine.Controller;
import org.example.backendamine.Entities.Question;
import org.example.backendamine.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/{id}")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question,@PathVariable long id) {
        return ResponseEntity.ok(questionService.createQuestion(question,id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable long id, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(id, question));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable long id) {
        questionService.deleteQuestion(id);
    }

    @GetMapping("/theme/{themeId}")
    public List<Question> getQuestionsByTheme(@PathVariable long themeId) {
        return questionService.getQuestionsByThemeId(themeId);
    }



}
