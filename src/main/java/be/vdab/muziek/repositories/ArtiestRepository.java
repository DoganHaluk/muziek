package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;

import java.util.List;

public interface ArtiestRepository {
    void create(Artiest artiest);

    Artiest findByName(String naam);

    List<Artiest> findAll();
}
