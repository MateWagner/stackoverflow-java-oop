package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.AnswerDTO;
import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/all")
    public List<AnswerDTO> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    @GetMapping("/answers_of_question/{questionId}")
    public List<Answer> getAnswersOfQuestion(@PathVariable Integer questionId){
        return answerService.getAnswersOfQuestion(questionId);
    }

    @PostMapping("/")
    public Integer postNewAnswer(@RequestBody NewAnswerDTO answerDTO) {
        System.out.println("called: " + answerDTO.desc());
        return answerService.postNewAnswer(answerDTO);
    }

    @DeleteMapping("/{answerId}")
    public void deleteAnswer(@PathVariable Integer answerId) {
        answerService.deleteAnswer(answerId);
    }

    @GetMapping("/{answerId}")
    public AnswerDTO getAnswer(@PathVariable Integer answerId) {
        return answerService.getAnswer(answerId);
    }
}
