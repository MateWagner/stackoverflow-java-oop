package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.AnswerDTO;
import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<AnswerDTO> getAllAnswers() {
        return answersDAO.getAllAnswer().stream().map(this::answerToAnswerDTO).toList();
    }

    public List<Answer> getAnswersOfQuestion(Integer questionId) {
        return answersDAO.getAnswersOfQuestion(questionId);
    }

    public Integer postNewAnswer(NewAnswerDTO newAnswerDTO) {
        return answersDAO.postNewAnswer(newAnswerDTO);
    }

    public void deleteAnswer(Integer answerId) {
        answersDAO.deleteAnswer(answerId);
    }

    public AnswerDTO getAnswer(Integer answerId) {
        return answerToAnswerDTO(answersDAO.getAnswer(answerId));
    }

    //replace with AnswerDto constructor
    private AnswerDTO answerToAnswerDTO(Answer answer) {
        return new AnswerDTO(answer.id(),
                answer.description(),
                answer.date(),
                answer.questionId(),
                answer.client_id(),
                answer.answeredAnswerId());
    }
}
