package com.codecool.stackoverflowtw.queston.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public record QuestionDTO(int id, int clientId, String title, String description, LocalDateTime date, int answerCount, Optional<Integer> solutionAnswerId) {}
