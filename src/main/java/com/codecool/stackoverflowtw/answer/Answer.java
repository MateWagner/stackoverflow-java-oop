package com.codecool.stackoverflowtw.answer;

import java.time.LocalDateTime;
import java.util.Optional;

public record Answer(int id, String description, LocalDateTime date, int questionId, Optional<Integer> answeredAnswerId, int client_id) {
}
