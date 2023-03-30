package com.codecool.stackoverflowtw.answer;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class AnswerRowMapper implements RowMapper<Answer> {
    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Integer> answeredAnswerId = Optional.of(rs.getInt("answered_answer_id"));
        return new Answer(
                rs.getInt("id"),
                rs.getString("description"),
                rs.getTimestamp("date").toLocalDateTime(),
                rs.getInt("question_id"),
                answeredAnswerId,
                rs.getInt("client_id")
        );
    }

}
