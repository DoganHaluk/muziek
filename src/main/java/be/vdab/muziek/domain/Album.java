package be.vdab.muziek.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Collections;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name = Album.MET_ARTIEST, attributeNodes = @NamedAttributeNode("artiest"))
public class Album {
    public static final String MET_ARTIEST = "Album.metArtiest";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    private String naam;
    private int score;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks;

    public Album(Artiest artiest, String naam, int score) {
        setArtiest(artiest);
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

    public void setArtiest(Artiest artiest) {
        if (!artiest.getAlbums().contains(this)) {
            artiest.add(this);
        }
        this.artiest = artiest;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalTime getTijd() {
        LocalTime som = LocalTime.MIN;
        for (var track : tracks) {
            var tijd = track.getTijd();
            som = som.plusHours(tijd.getHour()).plusMinutes(tijd.getMinute()).plusSeconds(tijd.getSecond());
        }
        return som;
    }

    public void addTrack(String naam, LocalTime tijd) {
        tracks.add(new Track(naam, tijd));
    }
}
