package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.exception.NotFoundException;
import com.codecool.stackoverflowtw.queston.data.ColumnNameForOrder;
import com.codecool.stackoverflowtw.queston.data.Order;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.SolutionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions(Optional<String> orderedBy, Optional<String> order) {
        if (orderedBy.isPresent() && order.isPresent()) {
        Optional<ColumnNameForOrder> optionalColumn = ColumnNameForOrder.isMatchAny(orderedBy.get());
        Optional<Order> optionalOrder = Order.isMatchAny(order.get());
        if (optionalOrder.isPresent() && optionalColumn.isPresent())
            return questionsDAO.getAllQuestion(optionalColumn.get(), optionalOrder.get());
        }
        return questionsDAO.getAllQuestion();
        // TODO SLAP
    }

    public QuestionDTO getQuestionById(int id) {
        Optional<QuestionDTO> question = questionsDAO.getQuestionById(id);
        if (question.isEmpty()) throw new NotFoundException(String.format("Question with id %s not found", id));
        return question.get();
    }

    public String deleteQuestionById(int id) {
        int affectedRows = questionsDAO.deleteAnswersWithQuestionByQuestionId(id);
        if (affectedRows != 1) {
            throw new NotFoundException(String.format("Question with id %s not found", id));
        }
        return "OK";
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(question);
    }

    public void setSolution(SolutionDTO solution) {
        int effected = questionsDAO.setSolution(solution);
        if (effected == 0) throw new NotFoundException("Can't Change on question id: " + solution.questionId());
    }

    public void updateQuestion(int id, NewQuestionDTO update) {
        int affectedRows = questionsDAO.updateQuestion(id, update);
        if (affectedRows != 1) {
            throw new NotFoundException(String.format("Question with id %s not found", id));
        }
    }
}
