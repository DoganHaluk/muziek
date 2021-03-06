package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
class DefaultAlbumService implements AlbumService {
    private final AlbumRepository albumRepository;

    DefaultAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAll() {
        return albumRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void wijzigScore(long id, int score) {
        albumRepository.findById(id).orElseThrow(AlbumNietGevondenException::new).setScore(score);
    }

    @Override
    public void toevoegTrack(long id, String naam, LocalTime tijd) {
        albumRepository.findById(id).orElseThrow(AlbumNietGevondenException::new).addTrack(naam, tijd);
    }

    @Override
    public void delete(long id) {
        albumRepository.delete(id);
    }

    @Override
    public void create(Album album) {
        albumRepository.create(album);
    }
}
