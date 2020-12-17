package ru.dadynsky.demo.dao;


import org.springframework.stereotype.Repository;
import ru.dadynsky.demo.dao.mapper.PartyMapper;
import ru.dadynsky.demo.entity.Party;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PartyDao extends BaseJdbcTemplateDao {

    protected PartyDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<Party> getParties(){
        return this.getJdbcTemplate().query("select * from party", new Party[] {},
                (resultSet, rowNum) ->{

                    Party party = new Party();
                    party.setId(resultSet.getInt("id"));
                    party.setName(resultSet.getString("name"));
                    return party;

                });
    }

    public Party getParty(Integer id){
        List<Party> list = this.getJdbcTemplate()
                .query("select * from party where id = ?", new Object[]{id}, new PartyMapper() );

        if (list.size()==0)
            return null;

        return list.get(0);
    }

    public void save(Party party){
        List params = new ArrayList();
        params.add(party.getName());
        params.add(party.getCreateDate());
        params.add(party.getVersion());

        this.getJdbcTemplate().update("insert into party (name,create_date,version) values (?,?,?)", params.toArray());
    }
}
