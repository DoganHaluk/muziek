package be.vdab.muziek.forms;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TrackForm {
    @NotBlank
    private final String naam;
    private final String tijd;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public TrackForm(String naam, String tijd) {
        this.naam = naam;
        this.tijd = tijd;
    }

    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        if (tijd.equals("")) {
            return null;
        }
        return LocalTime.parse(tijd, formatter);
    }
}
