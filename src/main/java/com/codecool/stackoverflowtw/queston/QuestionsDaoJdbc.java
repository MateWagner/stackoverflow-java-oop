package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.answer.AnswersDaoJdbc;
import com.codecool.stackoverflowtw.exception.NotFoundException;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.SolutionDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.text.Format;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class QuestionsDaoJdbc implements QuestionsDAO {
    private final JdbcTemplate jdbcTemplate;

    public QuestionsDaoJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<QuestionDTO> getAllQuestion() {
        String sql = """
                SELECT question.id as id,
                question.client_id as client_id,
                title,
                question.description as description,
                question.date as date,
                solution_answer_id,
                count(a.id) as answer_count
                FROM question
                LEFT JOIN answer a on question.id = a.question_id
                GROUP BY question.id
                """;
        return jdbcTemplate.query(sql, new QuestionDTORowMapper());
    }

    @Override
    public List<QuestionDTO> getAllQuestion(String orderedBy, String order) {
        String sql = String.format("SELECT question.id as id, question.client_id as client_id, title, question.description as description, question.date as date, count(a.id) as answer_count FROM question LEFT JOIN answer a on question.id = a.question_id GROUP BY question.id, question.date, title ORDER BY %s %s", orderedBy, order.toUpperCase());

        return jdbcTemplate.query(sql, new QuestionDTORowMapper());
    }

    @Override
    public Integer addNewQuestion(NewQuestionDTO newQuestionDTO) {
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        String sql = """
                INSERT INTO question(title, description, client_id, date,solution_answer_id) VALUES (?,?,?,?,?)
                """;

        jdbcTemplate.update(connection -> getPreparedStatement(newQuestionDTO, sql, connection), generatedKeyHolder);

        Map<String, Object> keys = generatedKeyHolder.getKeys();
        if (keys == null) throw new NotFoundException("oops something went wrong");
        return (Integer) keys.get("id");
    }

    private static PreparedStatement getPreparedStatement(NewQuestionDTO newQuestionDTO, String sql, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, newQuestionDTO.title());
        statement.setString(2, newQuestionDTO.description());
        statement.setInt(3, newQuestionDTO.clientId());
        statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        statement.setObject(5, null);
        return statement;
    }

    @Override
    public int deleteQuestionById(int id) {
        String sql = """
                DELETE FROM question
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public Optional<QuestionDTO> getQuestionById(int id) {
        String sql = """
                SELECT question.id as id,
                question.client_id as client_id,
                title,
                question.description as description,
                question.date as date,
                solution_answer_id,
                count(a.id) as answer_count
                FROM question
                LEFT JOIN answer a on question.id = a.question_id
                WHERE question.id = ?
                GROUP BY question.id
                """;
        return jdbcTemplate.query(sql, new QuestionDTORowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public String setSolution(SolutionDTO solution) {
        AnswersDaoJdbc answersDaoJdbc = new AnswersDaoJdbc(jdbcTemplate);
        if (getQuestionById(solution.questionId()).isPresent()) {
            if (answersDaoJdbc.getAnswer(solution.answerId()).isPresent()) {
                String sql = """
                        UPDATE question
                        SET solution_answer_id = ?
                        WHERE  id = ?;
                        """;
                jdbcTemplate.update(con -> {
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setInt(1, solution.answerId());
                    statement.setInt(2, solution.questionId());
                    return statement;
                });
                return "Done";
            } else return "No answer found with the given id";
        } else return "No question found with the given id";
    }
}
