package be.vdab.muziek.services;

import be.vdab.muziek.domain.Artiest;
import org.springframework.transaction.annotation.Transactional;

public interface ArtiestService {
    void create(Artiest artiest);

    Artiest findByNaam(String naam);
}
