package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;

public interface ArtiestRepository {
    void create(Artiest artiest);

    Artiest findByName(String naam);
}
