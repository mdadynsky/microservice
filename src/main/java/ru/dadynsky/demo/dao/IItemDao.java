package ru.dadynsky.demo.dao;

import com.google.gson.JsonObject;
import ru.dadynsky.demo.entity.Item;

import java.util.List;
import java.util.Optional;

public interface IItemDao {
    List<Item> getItemsByOwner(Integer ownerId);

    List<Item> getItemsByParentId(Integer parentId);

    List<Item> getItems(String serialNumber, Integer limit, Integer offset);

    List<Item> getItems(String serialNumber, Integer limit, Integer offset, Integer parentId, Integer ownerId);

    Optional<Item> getItem(Integer id);

    JsonObject getJson(Item item);

    void save(Item item);
}
