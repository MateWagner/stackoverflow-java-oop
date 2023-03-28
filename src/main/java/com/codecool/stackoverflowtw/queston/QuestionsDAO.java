package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;

import java.util.List;

public interface QuestionsDAO {
    List<Question> getAllQuestion();
    Integer addNewQuestion(NewQuestionDTO newQuestionDTO);
}
