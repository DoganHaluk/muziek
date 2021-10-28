package be.vdab.muziek.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    private String naam;
    @Range(min = 0, max = 10)
    private int score;
    ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks;

    public Album(Artiest artiest, String naam, int score) {
        this.naam = naam;
        this.score = score;
        this.tracks = new LinkedHashSet<>();
    }

    protected Album() {
    }

    public long getId() {
        return id;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public String getNaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }

    public Set<Track> getTracks(){
        return Collections.unmodifiableSet(tracks);
    }
}
