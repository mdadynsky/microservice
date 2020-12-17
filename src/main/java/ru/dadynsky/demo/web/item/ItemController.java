package ru.dadynsky.demo.web.item;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dadynsky.demo.dao.ItemDao;
import ru.dadynsky.demo.entity.Item;
import ru.dadynsky.demo.entity.Party;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    private ItemDao itemDao;

    @GetMapping(value = "/item")
    @ResponseBody
    public String getItems(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset
    ){
        List<Item> list = itemDao.getItems(serialNumber, limit, offset);

        JsonArray json = new JsonArray();

        for (Item item : list) {
            json.add(
                itemDao.getJson(item)
            );
        }
        return json.toString();
    }

    @PostMapping(
            value = "/item", consumes = "application/json", produces = "application/json")
    public ResponseEntity saveItem(
            @RequestBody Item item
    ){
        itemDao.save(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/item/{id}")
    @ResponseBody
    public String getParty(
            @PathVariable("id") Integer id
    ){
        Item item = itemDao.getItem(id);

        if (item==null)
            return "";

        JsonObject row = new JsonObject();

        row.addProperty("id", item.getId());
        row.addProperty("parentId", item.getId());
        row.addProperty("serialNumber", item.getSerial());

        if (item.getOwner()!=null)
            row.add("owner",  item.getOwner().getJson() );
        else
            row.addProperty("owner", "{}");

        row.addProperty("type", item.getType());
        row.addProperty("childrenCount", item.getChildrenCount());

        List<Item> children = itemDao.getItemsByParentId(id);
        if (children!=null && children.size()>0){
            JsonArray json = new JsonArray();
            for (Item child:children) {
                json.add(child.getJson());
            }
            row.add("children", json);
        } else {
            row.addProperty("children", "[null]");
        }

        return row.toString();
    }

    @PostMapping(value = "/item/{id}/sale")
    public ResponseEntity saleItem(
            @PathVariable("id") Integer id
    ){
        Item item = itemDao.getItem(id);

        if (Item.TYPE_ITEM != item.getTypeId())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        item.setOwnerId(null);
        item.setParentId(null);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}
