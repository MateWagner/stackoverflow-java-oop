package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.AnswerDTO;
import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    private final AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<AnswerDTO> getAllAnswers() {
        return answersDAO.getAllAnswer().stream().map(AnswerDTO::new).toList();
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

    public Answer getAnswer(Integer answerId) {
        Optional<Answer> answer = answersDAO.getAnswer(answerId);
        if (answer.isEmpty()) throw new NotFoundException("there is no answer with id: " + answerId);
        return answer.get();
    }

    public void updateAnswer(int id, NewAnswerDTO answerDTO) {
        int affectedRows = answersDAO.updateAnswer(id, answerDTO);
        if (affectedRows != 1) {
            throw new NotFoundException(String.format("Question with id %s not found", id));
        }
    }
}
