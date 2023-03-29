package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;

import java.util.List;
import java.util.Optional;

public interface QuestionsDAO {
    List<QuestionDTO> getAllQuestion();
    Integer addNewQuestion(NewQuestionDTO newQuestionDTO);
    int deleteQuestionById(int id);
    Optional<QuestionDTO> getQuestionById(int id);
}
