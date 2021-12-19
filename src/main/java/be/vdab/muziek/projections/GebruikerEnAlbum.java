package be.vdab.muziek.projections;

public class GebruikerEnAlbum {
    private final String gebruikersnaam;
    private final String albumsnaam;

    public GebruikerEnAlbum(String gebruikersnaam, String albumsnaam) {
        this.gebruikersnaam = gebruikersnaam;
        this.albumsnaam = albumsnaam;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getAlbumsnaam() {
        return albumsnaam;
    }
}
