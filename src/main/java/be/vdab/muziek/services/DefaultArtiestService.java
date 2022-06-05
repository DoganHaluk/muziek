package be.vdab.muziek.services;

import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.repositories.ArtiestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DefaultArtiestService implements ArtiestService {
    private final ArtiestRepository artiestRepository;

    public DefaultArtiestService(ArtiestRepository artiestRepository) {
        this.artiestRepository = artiestRepository;
    }

    @Override
    public void create(Artiest artiest) {
        artiestRepository.create(artiest);
    }

    @Override
    @Transactional(readOnly = true)
    public Artiest findByNaam(String naam){
        return artiestRepository.findByName(naam);
    }
}
