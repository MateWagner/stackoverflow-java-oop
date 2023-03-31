package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.SolutionDTO;

import java.util.List;
import java.util.Optional;

public interface QuestionsDAO {
    List<QuestionDTO> getAllQuestion();
    List<QuestionDTO> getAllQuestion(String orderedBy, String order);
    Integer addNewQuestion(NewQuestionDTO newQuestionDTO);
    int deleteQuestionById(int id);
    Optional<QuestionDTO> getQuestionById(int id);
    Optional<Question> getSingleQuestionById(int id);
    String  setSolution(SolutionDTO solution);
}
