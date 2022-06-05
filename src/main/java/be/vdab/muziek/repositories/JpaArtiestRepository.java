package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Artiest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaArtiestRepository implements ArtiestRepository {
    private final EntityManager manager;

    public JpaArtiestRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void create(Artiest artiest) {
        manager.persist(artiest);
    }

    @Override
    public Artiest findByName(String naam) {
        return manager.createNamedQuery("Artiest.findByNaam", Artiest.class)
                .setParameter("naam", naam)
                .getSingleResult();
    }
}
