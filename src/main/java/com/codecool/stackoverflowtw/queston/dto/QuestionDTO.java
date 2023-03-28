package com.codecool.stackoverflowtw.queston.dto;

import java.time.LocalDateTime;

public record QuestionDTO(int id, String title, String description, LocalDateTime created) {}
