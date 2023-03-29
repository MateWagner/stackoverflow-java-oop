package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.exception.NotFoundException;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<Question> getAllQuestions() {
        // TODO
        return questionsDAO.getAllQuestion();
    }

    public QuestionDTO getQuestionById(int id) {
        return new QuestionDTO(id, "example title", "example desc", LocalDateTime.now());
    }

    public void deleteQuestionById(int id) {
        int affectedRows = questionsDAO.deleteQuestionById(id);
        if (affectedRows != 1) {
            throw new NotFoundException(String.format("Movie with id %s not found", id));
        }
    }

    public int addNewQuestion(NewQuestionDTO question) {
        return questionsDAO.addNewQuestion(question);
    }
}
