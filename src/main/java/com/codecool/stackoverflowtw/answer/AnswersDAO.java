package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;

import java.util.List;
import java.util.Optional;

public interface AnswersDAO {
    List<Answer> getAllAnswer();
    List<Answer> getAnswersOfQuestion(Integer questionId);
    Integer postNewAnswer(NewAnswerDTO newAnswerDTO);
    void deleteAnswer(Integer answerId);
    Optional<Answer> getAnswer(Integer answerId);

}
