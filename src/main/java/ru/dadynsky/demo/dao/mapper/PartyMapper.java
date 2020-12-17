package ru.dadynsky.demo.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.dadynsky.demo.entity.Party;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PartyMapper implements RowMapper<Party> {

    @Override
    public Party mapRow(ResultSet resultSet, int i) throws SQLException {
        Party party = new Party();
        party.setId(resultSet.getInt("id"));
        party.setName(resultSet.getString("name"));
        return party;
    }
}
