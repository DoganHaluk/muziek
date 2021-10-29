package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany(mappedBy = "artiest")
    private List<Album> albums;

    public Artiest(String naam) {
        this.naam = naam;
        this.albums = new LinkedList<>();
    }

    protected Artiest() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    public boolean add(Album album) {
        var toegevoegd = albums.add(album);
        var oudeArtiest = album.getArtiest();
        if (oudeArtiest != null && oudeArtiest != this) {
            oudeArtiest.albums.remove(album);
        }
        if (this != oudeArtiest) {
            album.setArtiest(this);
        }
        return toegevoegd;
    }
}
