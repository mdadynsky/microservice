package ru.dadynsky.demo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.dadynsky.demo.dao.IItemDao;
import ru.dadynsky.demo.entity.Item;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService implements IItemService {
    private final IItemDao itemDao;

    public ItemService(IItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public String getItemsJson(String serialNumber, Integer limit, Integer offset) {

        List<Item> list = itemDao.getItems(serialNumber, limit, offset);

        JsonArray json = new JsonArray();

        for (Item item : list) {
            json.add(itemDao.getJson(item));
        }
        return json.toString();
    }

    public String getItemJson(Integer id) {
        Optional<Item> optionalItem = itemDao.getItem(id);

        if (!optionalItem.isPresent()) {
            return "";
        }

        Item item = optionalItem.get();

        JsonObject row = new JsonObject();

        row.addProperty("id", item.getId());
        row.addProperty("parentId", item.getId());
        row.addProperty("serialNumber", item.getSerial());

        if (item.getOwner() != null)
            row.add("owner", item.getOwner().getJson());
        else
            row.addProperty("owner", "{}");

        row.addProperty("type", item.getType());
        row.addProperty("childrenCount", item.getChildrenCount());

        List<Item> children = itemDao.getItemsByParentId(id);
        if (children != null && children.size() > 0) {
            JsonArray json = new JsonArray();
            for (Item child : children) {
                json.add(child.getJson());
            }
            row.add("children", json);
        } else {
            row.addProperty("children", "[null]");
        }

        return row.toString();
    }

    public void save(Item item) {
        itemDao.save(item);
    }

    @Override
    public ResponseEntity sale(Integer id) {
        Optional<Item> optionalItem = itemDao.getItem(id);

        if (!optionalItem.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Item item = optionalItem.get();

        if (Item.TYPE_ITEM != item.getTypeId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        item.setOwnerId(null);
        item.setParentId(null);

        itemDao.save(item);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
