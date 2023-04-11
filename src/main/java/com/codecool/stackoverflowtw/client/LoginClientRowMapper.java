package com.codecool.stackoverflowtw.client;

import com.codecool.stackoverflowtw.client.dto.LoginClientDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginClientRowMapper implements RowMapper<LoginClientDTO> {
    @Override
    public LoginClientDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new LoginClientDTO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("date"),
                rs.getBoolean("is_admin")
        );
    }
}
