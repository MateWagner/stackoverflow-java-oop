package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return null;
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        return 0;
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return false;
    }
}
