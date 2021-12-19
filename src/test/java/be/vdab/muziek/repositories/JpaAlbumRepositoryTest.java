package be.vdab.muziek.repositories;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertArtiest.sql", "/insertAlbum.sql"})
@Import(JpaAlbumRepository.class)
class JpaAlbumRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ALBUMS = "albums";
    private final JpaAlbumRepository repository;
    private final EntityManager manager;

    JpaAlbumRepositoryTest(JpaAlbumRepository repository, EntityManager manager) {
        this.repository = repository;
        this.manager = manager;
    }

    @Test
    void findAll() {
        manager.clear();
        assertThat(repository.findAll())
                .hasSize(countRowsInTable(ALBUMS))
                .extracting(Album::getId)
                .isSorted();
        assertThat(repository.findAll())
                .extracting(Album::getArtiest)
                .extracting(Artiest::getNaam);
    }

    private long idVanTestAlbum() {
        return jdbcTemplate.queryForObject("SELECT id FROM albums WHERE naam= 'test'", Long.class);
    }

    @Test
    void findById() {
        assertThat(repository.findById(idVanTestAlbum())).hasValueSatisfying(album -> {
            assertThat(album.getNaam()).isEqualTo("test");
            assertThat(album.getArtiest().getNaam()).isEqualTo("test");
        });
    }

    @Test
    void findByOnbestandeId() {
        assertThat(repository.findById(-1)).isNotPresent();
    }
}
