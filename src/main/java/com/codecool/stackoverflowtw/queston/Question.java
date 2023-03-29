package com.codecool.stackoverflowtw.queston;

import java.time.LocalDateTime;

public record Question(int id, int clientId, String title, String description, LocalDateTime date) {
}
