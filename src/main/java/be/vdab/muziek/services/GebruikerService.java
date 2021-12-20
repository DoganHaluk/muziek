package be.vdab.muziek.services;

import be.vdab.muziek.domain.Gebruiker;

import java.util.List;
import java.util.Optional;

public interface GebruikerService {
    void create(Gebruiker gebruiker);

    List<Gebruiker> findAll();

    Optional<Gebruiker> findById(long id);
}
