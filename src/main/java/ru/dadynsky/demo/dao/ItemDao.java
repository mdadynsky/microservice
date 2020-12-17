package ru.dadynsky.demo.dao;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ru.dadynsky.demo.dao.mapper.ItemMapper;

import ru.dadynsky.demo.entity.Item;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public class ItemDao extends BaseJdbcTemplateDao implements IItemDao {

    private final ItemMapper itemMapper;

    protected ItemDao(DataSource dataSource, ItemMapper itemMapper) {
        super(dataSource);
        this.itemMapper = itemMapper;
    }

    public List<Item> getItemsByOwner(Integer ownerId) {
        return getItems(null, null, null, null, ownerId);
    }

    public List<Item> getItemsByParentId(Integer parentId) {
        return getItems(null, null, null, parentId, null);
    }

    public List<Item> getItems(String serialNumber, Integer limit, Integer offset) {
        return getItems(serialNumber, limit, offset, null, null);
    }

    public List<Item> getItems(String serialNumber, Integer limit, Integer offset, Integer parentId, Integer ownerId) {
        String query = "select * from item where 1=1 ";
        List params = new ArrayList();

        if (!StringUtils.isEmpty(serialNumber)) {
            query += " and serial=? ";
            params.add(serialNumber);
        }

        if (parentId != null) {
            query += " and parent_id=? ";
            params.add(parentId);
        }

        if (ownerId != null) {
            query += " and owner_id=? ";
            params.add(ownerId);
        }

        if (limit != null) {
            query += "  limit ?";
            params.add(limit);
        }

        if (offset != null) {
            query += "  offset ?";
            params.add(offset);
        }

        return getJdbcTemplate()
                .query(query, params.toArray(), itemMapper);
    }

    public Optional<Item> getItem(Integer id) {
        return getJdbcTemplate()
                .query("select * from item where id = ?", new Object[]{id}, itemMapper)
                .stream()
                .findAny();
    }

    public JsonObject getJson(Item item) {
        JsonObject row = new JsonObject();

        row.addProperty("id", item.getId());
        row.addProperty("parentId", item.getParentId());
        row.addProperty("serialNumber", item.getSerial());

        if (item.getOwner() != null)
            row.add("owner", item.getOwner().getJson());
        else
            row.addProperty("owner", "{}");

        row.addProperty("type", item.getType());
        row.addProperty("childrenCount", item.getChildrenCount());

        return row;
    }

    public void save(Item item) {
        List<Object> params = new ArrayList();
        params.add(item.getOwner().getId());
        params.add(item.getCreateDate());
        params.add(item.getTypeId());
        params.add(item.getSerial());

        getJdbcTemplate().update("insert into item (owner_id,create_date,type, serial) values (?,?,?,?)", params.toArray());
    }
}
