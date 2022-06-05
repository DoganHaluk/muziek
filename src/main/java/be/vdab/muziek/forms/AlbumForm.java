package be.vdab.muziek.forms;

import javax.validation.constraints.NotBlank;

public class AlbumForm {
    @NotBlank
    private final String albumNaam;
    @NotBlank
    private final String artiestNaam;

    public AlbumForm(String albumNaam, String artiestNaam) {
        this.albumNaam = albumNaam;
        this.artiestNaam = artiestNaam;
    }

    public String getAlbumNaam() {
        return albumNaam;
    }

    public String getArtiestNaam() {
        return artiestNaam;
    }
}
