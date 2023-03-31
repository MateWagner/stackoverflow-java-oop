package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.exception.NotFoundException;
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
            return questionsDAO.getAllQuestion(orderedBy.get(), order.get());
        }
        return questionsDAO.getAllQuestion();
    }

    public QuestionDTO getQuestionById(int id) {
        Optional<QuestionDTO> question = questionsDAO.getQuestionById(id);
        if (question.isEmpty()) throw new NotFoundException(String.format("Question with id %s not found", id));
        return question.get();
    }

    public Question getSingleQuestionById(int id) {
        Optional<Question> question = questionsDAO.getSingleQuestionById(id);
        if (question.isEmpty()) throw new NotFoundException(String.format("Question with id %s not found", id));
        return question.get();
    }

    public String deleteQuestionById(int id) {
        int affectedRows = questionsDAO.deleteQuestionById(id);
        if (affectedRows != 1) {
            throw new NotFoundException(String.format("Question with id %s not found", id));
        }
        return "OK";
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(question);
    }

    public String setSolution(SolutionDTO solution) {
         return questionsDAO.setSolution(solution);
    }
}
