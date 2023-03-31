package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.SolutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions(@RequestParam Optional<String> orderedBy, @RequestParam Optional<String> order) {
        return questionService.getAllQuestions(orderedBy, order);
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }
    @GetMapping("/single/{id}")
    public Question getSingleQuestionById(@PathVariable int id) {
        return questionService.getSingleQuestionById(id);
    }

    @PostMapping("/")
    public int addNewQuestion(@RequestBody NewQuestionDTO question) {
        return questionService.addNewQuestion(question);
    }

    @PatchMapping("/solution")
    public String setSolution(@RequestBody SolutionDTO solution){
        return questionService.setSolution(solution);
    }

    @DeleteMapping("/{id}")
    public String deleteQuestionById(@PathVariable int id) {
       return questionService.deleteQuestionById(id);

    }
}
