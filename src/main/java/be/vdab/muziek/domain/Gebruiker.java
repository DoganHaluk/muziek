package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "gebruikers")
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private String paswoord;
    @OneToMany(mappedBy = "gebruiker")
    private List<Album> albums;

    public Gebruiker(String naam, String paswoord) {
        this.naam = naam;
        this.paswoord = paswoord;
        this.albums = new LinkedList<>();
    }

    protected Gebruiker() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public List<Album> getAlbums() {
        return Collections.unmodifiableList(albums);
    }

    public boolean add(Album album) {
        var toegevoegd = albums.add(album);
        var oudeGebruiker = album.getGebruiker();
        if (oudeGebruiker != null && oudeGebruiker != this) {
            oudeGebruiker.albums.remove(album);
        }
        if (this != oudeGebruiker) {
            album.setGebruiker(this);
        }
        return toegevoegd;
    }
}
