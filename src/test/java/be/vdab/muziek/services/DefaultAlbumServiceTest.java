package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.domain.Gebruiker;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultAlbumServiceTest {
    private DefaultAlbumService service;
    @Mock
    private AlbumRepository repository;
    private Album album;

    @BeforeEach
    void beforeEach() {
        service = new DefaultAlbumService(repository);
        var artiest = new Artiest("test");
        var gebruiker = new Gebruiker("test");
        album = new Album(artiest, gebruiker,"test", 0);
    }

    @Test
    void wijzigScore() {
        when(repository.findById(1)).thenReturn(Optional.of(album));
        service.wijzigScore(1, 10);
        assertThat(album.getScore()).isEqualTo(10);
        verify(repository).findById(1);
    }

    @Test
    void wijzigScoreVoorOnbestaandeAlbum() {
        assertThatExceptionOfType(AlbumNietGevondenException.class).isThrownBy(() -> service.wijzigScore(-1, 10));
        verify(repository).findById(-1);
    }
}
