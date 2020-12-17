package ru.dadynsky.demo.dao;

import ru.dadynsky.demo.entity.Party;

import java.util.List;
import java.util.Optional;

public interface IPartyDao {
    List<Party> getParties();

    Optional<Party> getParty(Integer id);

    void save(Party party);
}
