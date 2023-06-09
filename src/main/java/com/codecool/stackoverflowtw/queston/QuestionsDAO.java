package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.data.ColumnNameForOrder;
import com.codecool.stackoverflowtw.queston.data.Order;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.SolutionDTO;

import java.util.List;
import java.util.Optional;

public interface QuestionsDAO {
    List<QuestionDTO> getAllQuestion();
    List<QuestionDTO> getAllQuestion(ColumnNameForOrder orderedBy, Order order);
    Integer addNewQuestion(NewQuestionDTO newQuestionDTO);
    int deleteQuestionById(int id);
    Optional<QuestionDTO> getQuestionById(int id);
    int setSolution(SolutionDTO solution);
    int deleteAnswersWithQuestionByQuestionId(int id);
    int updateQuestion(int id, NewQuestionDTO update);
}
