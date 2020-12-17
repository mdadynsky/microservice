package ru.dadynsky.demo.service;


import ru.dadynsky.demo.entity.Party;

public interface IPartyService {

    String getPartiesJson();

    String getPartyJson(Integer id);

    void save(Party party);
}
