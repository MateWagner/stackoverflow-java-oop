package com.codecool.stackoverflowtw.queston;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record Question(int id, int clientId, String title, String description, LocalDate date) {
}
