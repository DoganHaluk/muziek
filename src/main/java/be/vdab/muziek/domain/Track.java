package be.vdab.muziek.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@Access(AccessType.FIELD)
public class Track {
    private String naam;
    private LocalTime tijd;

    public Track(String naam, LocalTime tijd) {
        this.naam = naam;
        this.tijd = tijd;
    }

    protected Track() {
    }

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }
}
