package com.codecool.stackoverflowtw.answer.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public record AnswerDTO(int id, String desc, LocalDateTime date, int questionId, int clientId, Optional<Integer> answeredAnswerId) {
}
