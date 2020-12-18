package ru.dadynsky.demo.web.party;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dadynsky.demo.dao.IItemDao;
import ru.dadynsky.demo.dao.IPartyDao;
import ru.dadynsky.demo.entity.Party;
import ru.dadynsky.demo.service.IPartyService;


@Controller
public class PartyController {
    private final IPartyService partyService;

    public PartyController(IPartyService partyService) {
        this.partyService = partyService;
    }

    @GetMapping(value = "/party")
    @ResponseBody
    public String getParties() {
        return partyService.getPartiesJson();
    }

    @PostMapping(
            value = "/party", consumes = "application/json", produces = "application/json")
    public ResponseEntity saveParty(
            @RequestBody Party party
    ) {
        partyService.save(party);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/party/{id}")
    @ResponseBody
    public String getParty(
            @PathVariable("id") Integer id
    ) {
        return partyService.getPartyJson(id);
    }

}
