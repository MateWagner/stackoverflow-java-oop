package com.codecool.stackoverflowtw.queston;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Question(
                rs.getInt("id"),
                rs.getInt("client_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("date").toLocalDateTime()
        );
    }
}
