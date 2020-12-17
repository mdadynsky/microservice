package ru.dadynsky.demo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import ru.dadynsky.demo.dao.IItemDao;
import ru.dadynsky.demo.dao.IPartyDao;
import ru.dadynsky.demo.entity.Item;
import ru.dadynsky.demo.entity.Party;

import java.util.List;
import java.util.Optional;


@Service
public class PartyService implements IPartyService {
    private final IPartyDao partyDao;
    private final IItemDao itemDao;

    public PartyService(IPartyDao partyDao, IItemDao itemDao) {
        this.partyDao = partyDao;
        this.itemDao = itemDao;
    }

    public String getPartiesJson() {
        List<Party> list = partyDao.getParties();

        JsonArray json = new JsonArray();
        for (Party party : list) {
            JsonObject row = new JsonObject();

            row.addProperty("id", party.getId());
            row.addProperty("name", party.getName());

            json.add(row);
        }
        return json.toString();
    }

    @Override
    public String getPartyJson(Integer id) {

        Optional<Party> optionalParty = partyDao.getParty(id);

        if (!optionalParty.isPresent()) {
            return "";
        }

        Party party = optionalParty.get();

        JsonObject row = new JsonObject();

        row.addProperty("id", party.getId());
        row.addProperty("name", party.getName());

        List<Item> items = itemDao.getItemsByOwner(id);
        if (items != null && items.size() > 0) {
            JsonArray json = new JsonArray();
            for (Item item : items) {
                json.add(itemDao.getJson(item));
            }
            row.add("items", json);
        } else {
            row.addProperty("items", "[null]");
        }

        return row.toString();
    }

    @Override
    public void save(Party party) {
        partyDao.save(party);
    }
}
