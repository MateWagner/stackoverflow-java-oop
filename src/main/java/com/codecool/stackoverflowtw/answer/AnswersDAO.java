package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;

import java.util.List;

public interface AnswersDAO {
    List<Answer> getAllAnswer();
    Integer addNewAnswer(NewAnswerDTO newAnswerDTO);
    void deleteAnswer(Integer answerId);

    Answer getAnswer(Integer answerId);
}
