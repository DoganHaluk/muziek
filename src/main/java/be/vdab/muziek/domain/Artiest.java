package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany(mappedBy = "artiest")
    private Set<Album> albums;

    public Artiest(String naam) {
        this.naam = naam;
        this.albums=new LinkedHashSet<>();
    }

    protected Artiest() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Album> getAlbums(){
        return Collections.unmodifiableSet(albums);
    }
}
