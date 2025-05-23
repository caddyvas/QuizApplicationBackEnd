package com.deepak.quizapplication.controller;

import com.deepak.quizapplication.model.Questions;
import com.deepak.quizapplication.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questionsService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
        return questionsService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
        return questionsService.addQuestion(question);
    }
}
