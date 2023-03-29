package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDTORowMapper implements RowMapper<QuestionDTO> {
    @Override
    public QuestionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new QuestionDTO(
                rs.getInt("id"),
                rs.getInt("client_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getTimestamp("date").toLocalDateTime(),
                rs.getInt("answer_count")
        );
    }
}
