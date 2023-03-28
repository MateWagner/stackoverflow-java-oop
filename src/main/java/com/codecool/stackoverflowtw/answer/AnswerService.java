package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<Answer> getAllAnswers() {
        return answersDAO.getAllAnswer();
    }

    public Integer postNewAnswer(NewAnswerDTO newAnswerDTO) {
        return answersDAO.addNewAnswer(newAnswerDTO);
    }

    public void deleteAnswer(Integer answerId) {
        answersDAO.deleteAnswer(answerId);
    }

    public Answer getAnswer(Integer answerId){
        return answersDAO.getAnswer(answerId);
    }
}
