package com.codecool.stackoverflowtw.answer.dto;

import com.codecool.stackoverflowtw.answer.Answer;

import java.time.LocalDateTime;
import java.util.Optional;

public final class AnswerDTO {
    private final String desc;
    private final LocalDateTime date;
    private final int questionId;
    private final int clientId;
    private final Optional<Integer> answeredAnswerId;

    public AnswerDTO(Answer answer) {
        this.desc = answer.description();
        this.date = answer.date();
        this.questionId = answer.questionId();
        this.clientId = answer.client_id();
        this.answeredAnswerId = answer.answeredAnswerId();
    }

    public String desc() {
        return desc;
    }

    public LocalDateTime date() {
        return date;
    }

    public int questionId() {
        return questionId;
    }

    public int clientId() {
        return clientId;
    }

    public Optional<Integer> answeredAnswerId() {
        return answeredAnswerId;
    }

}
