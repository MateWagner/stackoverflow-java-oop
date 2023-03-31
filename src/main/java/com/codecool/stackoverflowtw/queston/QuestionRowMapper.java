package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Optional<Integer> solutionAnswerId = Optional.of(rs.getInt("solution_answer_id"));
        return new Question(
                rs.getInt("id"),
                rs.getInt("client_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("date").toLocalDateTime()
        );
    }
}
