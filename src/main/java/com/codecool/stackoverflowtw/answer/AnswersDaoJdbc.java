package com.codecool.stackoverflowtw.answer;

import com.codecool.stackoverflowtw.answer.dto.AnswerDTO;
import com.codecool.stackoverflowtw.answer.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//get answer by question id and order by time
@Repository
public class AnswersDaoJdbc implements AnswersDAO {
    private final JdbcTemplate jdbcTemplate;
    private final static String SQL_QUERY_GET_ALL_ANSWER = """ 
            SELECT * FROM answer;
            """;
    private final static String SQL_QUERY_ADD_NEW_ANSWER = """
            INSERT INTO answer(description,date,question_id,answer_to_id,client_id) VALUES (?,?,?,?,?)
            """;
    private final static String SQL_QUERY_DELETE_ANSWER = """
            DELETE FROM answer WHERE id = ?
            """;
    private final static String SQL_QUERY_GET_ANSWER_BY_ID = """
            SELECT * FROM answer WHERE id = ?
            """;

    public AnswersDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Answer> getAllAnswer() {
        return jdbcTemplate.query(SQL_QUERY_GET_ALL_ANSWER, new AnswerRowMapper());
    }

    @Override
    public Integer postNewAnswer(NewAnswerDTO newAnswerDTO) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> prepareAddNewAnswerPreparedStatement(connection, newAnswerDTO), generatedKeyHolder);


        Map<String, Object> keys = generatedKeyHolder.getKeys();
        if (keys == null) return -1;
        return (Integer) keys.get("id");
    }

    @Override
    public void deleteAnswer(Integer answerId) throws NotFoundException {
        jdbcTemplate.update(SQL_QUERY_DELETE_ANSWER, answerId);
    }

    @Override
    public Answer getAnswer(Integer answerId) throws NotFoundException {
        List<Answer> answers = jdbcTemplate.query(SQL_QUERY_GET_ANSWER_BY_ID, new AnswerRowMapper(), answerId);
        if (answers.isEmpty())
            throw new NotFoundException("No answer found by given id");
        else
            return answers.get(0);
    }


    private PreparedStatement prepareAddNewAnswerPreparedStatement(Connection connection, NewAnswerDTO newAnswerDTO) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(SQL_QUERY_ADD_NEW_ANSWER, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, newAnswerDTO.desc());
        statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
        statement.setInt(3, newAnswerDTO.questionId());
        statement.setObject(4, newAnswerDTO.answerToId().orElse(null));
        statement.setInt(5, newAnswerDTO.clientId());

        return statement;
    }
}
