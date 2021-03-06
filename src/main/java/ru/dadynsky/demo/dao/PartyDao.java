package ru.dadynsky.demo.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.dadynsky.demo.dao.mapper.PartyMapper;
import ru.dadynsky.demo.entity.Party;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PartyDao implements IPartyDao {
    private final JdbcTemplate jdbcTemplate;
    private final PartyMapper partyMapper;

    protected PartyDao(JdbcTemplate jdbcTemplate, PartyMapper partyMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.partyMapper = partyMapper;
    }

    public List<Party> getParties() {
        return jdbcTemplate.query("select * from party", partyMapper);
    }

    public Optional<Party> getParty(Integer id) {
        return jdbcTemplate
                .query("select * from party where id = ?", new Object[]{id}, partyMapper)
                .stream()
                .findAny();
    }

    public void save(Party party) {
        List<Object> params = new ArrayList<>();
        params.add(party.getName());
        params.add(party.getCreateDate());
        params.add(party.getVersion());

        jdbcTemplate.update("insert into party (name,create_date,version) values (?,?,?)", params.toArray());
    }
}
