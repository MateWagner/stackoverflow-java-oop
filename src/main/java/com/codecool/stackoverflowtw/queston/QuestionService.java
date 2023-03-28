package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.exception.NotFoundException;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<Question> getAllQuestions() {
        return questionsDAO.getAllQuestion();
    }

    public Question getQuestionById(int id) {
        Optional<Question> question = questionsDAO.getQuestionById(id);
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
        int id = questionsDAO.addNewQuestion(question);
        if (id == -1) {
            throw new IllegalStateException("oops something went wrong");
        }
        return id;
    }
}
