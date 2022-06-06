package be.vdab.muziek.services;

import be.vdab.muziek.domain.Artiest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArtiestService {
    void create(Artiest artiest);

    Artiest findByNaam(String naam);

    List<Artiest> findAll();
}
