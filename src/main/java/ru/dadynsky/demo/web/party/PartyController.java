package ru.dadynsky.demo.web.party;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ru.dadynsky.demo.dao.ItemDao;
import ru.dadynsky.demo.dao.PartyDao;
import ru.dadynsky.demo.entity.Item;
import ru.dadynsky.demo.entity.Party;

import java.util.List;

@Controller
public class PartyController {
    @Autowired
    private PartyDao partyDao;
    @Autowired
    private ItemDao itemDao;

    @GetMapping(value = "/party")
    @ResponseBody
    public String getParties(){
        List<Party> list = partyDao.getParties();

        JsonArray json = new JsonArray();
        for (Party party: list) {
            JsonObject row = new JsonObject();

            row.addProperty("id", party.getId());
            row.addProperty("name", party.getName());

            json.add(row);
        }
        return json.toString();
    }

    @PostMapping(
            value = "/party", consumes = "application/json", produces = "application/json")
    public ResponseEntity saveParty(
            @RequestBody Party party
    ){
        partyDao.save(party);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/party/{id}")
    @ResponseBody
    public String getParty(
            @PathVariable("id") Integer id
            ){
        Party party = partyDao.getParty(id);

        if (party==null)
            return "";

        JsonObject row = new JsonObject();

        row.addProperty("id", party.getId());
        row.addProperty("name", party.getName());

        List<Item> items = itemDao.getItemsByOwner(id);
        if (items!=null && items.size()>0){
            JsonArray json = new JsonArray();
            for (Item item:items) {
                json.add(
                        itemDao.getJson(item)
                );
            }
            row.add("items", json);
        } else {
            row.addProperty("items", "[null]");
        }

        return row.toString();
    }

}
