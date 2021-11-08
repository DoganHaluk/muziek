package be.vdab.muziek.forms;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

public class TrackForm {
    @NotBlank
    private final String naam;
    private final LocalTime tijd;

    public TrackForm(String naam, LocalTime tijd) {
        this.naam = naam;
        this.tijd = tijd;
    }

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }
}
