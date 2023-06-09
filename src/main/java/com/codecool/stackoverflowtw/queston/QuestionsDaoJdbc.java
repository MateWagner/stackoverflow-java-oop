package com.codecool.stackoverflowtw.queston;

import com.codecool.stackoverflowtw.exception.NotFoundException;
import com.codecool.stackoverflowtw.queston.data.ColumnNameForOrder;
import com.codecool.stackoverflowtw.queston.data.Order;
import com.codecool.stackoverflowtw.queston.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.QuestionDTO;
import com.codecool.stackoverflowtw.queston.dto.SolutionDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
    public List<QuestionDTO> getAllQuestion(ColumnNameForOrder orderedBy, Order order) {
        String sql = String.format("SELECT question.id as id, question.client_id as client_id, title, question.description as description, question.date as date, solution_answer_id, count(a.id) as answer_count FROM question LEFT JOIN answer a on question.id = a.question_id GROUP BY question.id, question.date, title ORDER BY %s %s", orderedBy.name().toLowerCase(), order.name());

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
        String getAllAnswerID = """
           DELETE FROM answer WHERE question_id  = ?;
                """;
        jdbcTemplate.update(getAllAnswerID, id);

        String sql = """
                DELETE FROM question
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);

    }
    @Override
    public int deleteAnswersWithQuestionByQuestionId(int id) {
        String getAllAnswerID = """
           DELETE FROM answer WHERE question_id  = ?;
                """;
        jdbcTemplate.update(getAllAnswerID, id);

        String sql = """
                DELETE FROM question
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public int updateQuestion(int id, NewQuestionDTO update) {
        System.out.println(id);
        String sql = """
                UPDATE question
                SET title = ?, description = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, update.title(), update.description(), id);
    }

    @Override
    public Optional<QuestionDTO> getQuestionById(int id) {
        String sql = """
                SELECT question.id as id,
                       question.client_id as client_id,
                       title,
                       question.description as description,
                       question.date as date,
                       count(a.id) as answer_count,
                       question.solution_answer_id as solution_answer_id
                FROM question
                LEFT JOIN answer a ON question.id = a.question_id
                WHERE question.id = ?
                GROUP BY question.id, question.date, title, question.client_id, question.description, question.solution_answer_id
                """;
        return jdbcTemplate.query(sql, new QuestionDTORowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public int setSolution(SolutionDTO solution) {
        String sql = """
                UPDATE question
                SET solution_answer_id = ?
                WHERE  id = ?;
                """;
        return jdbcTemplate.update(sql, solution.answerId(), solution.questionId());

    }
}
