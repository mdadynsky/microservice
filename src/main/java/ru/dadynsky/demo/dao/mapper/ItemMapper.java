package ru.dadynsky.demo.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.dadynsky.demo.dao.IPartyDao;
import ru.dadynsky.demo.entity.Item;
import ru.dadynsky.demo.entity.Party;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class ItemMapper implements RowMapper<Item> {
    private final IPartyDao partyDao;

    public ItemMapper(IPartyDao partyDao) {
        this.partyDao = partyDao;
    }

    @Override
    public Item mapRow(ResultSet resultSet, int i) throws SQLException {
        Item item = new Item();
        item.setId(resultSet.getInt("id"));
        item.setOwnerId(resultSet.getInt("owner_id"));
        item.setParentId(resultSet.getInt("parent_id"));
        item.setSerial(resultSet.getString("serial"));
        item.setTypeId(resultSet.getInt("type"));
        item.setChildrenCount(resultSet.getInt("children_count"));

        if (item.getOwnerId() != null) {
            Optional<Party> optionalParty = partyDao.getParty(item.getOwnerId());
            optionalParty.ifPresent(item::setOwner);
        }
        return item;
    }

}
