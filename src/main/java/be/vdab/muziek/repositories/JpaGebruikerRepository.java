package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Gebruiker;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaGebruikerRepository implements GebruikerRepository{
    private final EntityManager manager;

    public JpaGebruikerRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Gebruiker gebruiker) {
        manager.persist(gebruiker);
    }

    @Override
    public List<Gebruiker> findAll() {
        return manager.createNamedQuery("Gebruiker.findAll", Gebruiker.class).getResultList();
    }

    @Override
    public Optional<Gebruiker> findById(long id) {
        return Optional.ofNullable(manager.find(Gebruiker.class, id));
    }
}
