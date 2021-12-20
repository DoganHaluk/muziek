package be.vdab.muziek.services;

import be.vdab.muziek.domain.Gebruiker;
import be.vdab.muziek.repositories.GebruikerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultGebruikerService implements GebruikerService{
    private final GebruikerRepository gebruikerRepository;

    public DefaultGebruikerService(GebruikerRepository gebruikerRepository) {
        this.gebruikerRepository = gebruikerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public void create(Gebruiker gebruiker) {
        gebruikerRepository.create(gebruiker);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gebruiker> findAll() {
        return gebruikerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Gebruiker> findById(long id) {
        return gebruikerRepository.findById(id);
    }
}
