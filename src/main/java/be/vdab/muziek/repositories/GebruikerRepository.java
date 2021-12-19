package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Gebruiker;

import java.util.List;
import java.util.Optional;

public interface GebruikerRepository {
    void create(Gebruiker gebruiker);

    List<Gebruiker> findAll();

    Optional<Gebruiker> findById(long id);
}
