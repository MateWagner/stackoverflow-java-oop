package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;

import java.util.List;
import java.util.Optional;

public interface QuestionsDAO {
    List<Question> getAllQuestion();
    Integer addNewQuestion(NewQuestionDTO newQuestionDTO);
    int deleteQuestionById(int id);
    Optional<Question> getQuestionById(int id);
}
