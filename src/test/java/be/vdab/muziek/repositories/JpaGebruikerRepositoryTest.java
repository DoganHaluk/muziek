package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Gebruiker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertGebruiker.sql", "/insertArtiest.sql", "/insertAlbum.sql"})
@Import(JpaGebruikerRepository.class)
public class JpaGebruikerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GEBRUIKERS = "gebruikers";
    private final JpaGebruikerRepository repository;
    private final EntityManager manager;
    private Gebruiker gebruiker;

    public JpaGebruikerRepositoryTest(JpaGebruikerRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @Test
    void findAll() {
        manager.clear();
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(GEBRUIKERS))
                .extracting(Gebruiker::getId)
                .isSorted();
    }

    private long idVanTestGebruiker() {
        return jdbcTemplate.queryForObject("SELECT id FROM gebruikers WHERE naam= 'test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestGebruiker())).hasValueSatisfying(gebruiker -> assertThat(gebruiker.getNaam()).isEqualTo("test"));
    }

    @Test
    void findByOnbestandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
}
