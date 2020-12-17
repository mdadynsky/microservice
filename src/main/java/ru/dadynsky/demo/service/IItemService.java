package ru.dadynsky.demo.service;

import org.springframework.http.ResponseEntity;
import ru.dadynsky.demo.entity.Item;

public interface IItemService {
    String getItemsJson(String serialNumber, Integer limit, Integer offset);

    String getItemJson(Integer id);

    void save(Item item);

    ResponseEntity sale(Integer id);
}
