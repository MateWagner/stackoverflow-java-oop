package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all")
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @PostMapping("/")
    public Integer postNewAnswer(@RequestBody NewAnswerDTO answerDTO){
        return answerService.postNewAnswer(answerDTO);
    }
    @DeleteMapping("/{answerId}")
    public void deleteAnswer(@PathVariable Integer answerId) {
        answerService.deleteAnswer(answerId);
    }
}
