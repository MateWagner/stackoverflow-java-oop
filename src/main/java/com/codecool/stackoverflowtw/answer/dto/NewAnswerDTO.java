package com.codecool.stackoverflowtw.answer.dto;

import java.util.Optional;

public record NewAnswerDTO(String desc,int questionId, int clientId, Optional<Integer> answerToId) {
}
