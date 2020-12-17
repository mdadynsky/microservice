package ru.dadynsky.demo.web.party;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PartyPageController {

    @GetMapping(value = "/partyPage/open")
    @ResponseBody
    public String getParties(){
        return "1";
    }
}
