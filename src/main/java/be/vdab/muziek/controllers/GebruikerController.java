package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Artiest;
import be.vdab.muziek.exceptions.GebruikerNietGevondenException;
import be.vdab.muziek.forms.AlbumForm;
import be.vdab.muziek.services.AlbumService;
import be.vdab.muziek.services.ArtiestService;
import be.vdab.muziek.services.GebruikerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("gebruiker")
class GebruikerController {
    private final AlbumService albumService;
    private final GebruikerService gebruikerService;
    private final ArtiestService artiestService;

    GebruikerController(AlbumService albumService, GebruikerService gebruikerService, ArtiestService artiestService) {
        this.albumService = albumService;
        this.gebruikerService = gebruikerService;
        this.artiestService = artiestService;
    }

    @GetMapping("{id}")
    public ModelAndView gebruiker(@PathVariable long id) {
        var modelAndView = new ModelAndView("gebruiker", "gebruiker", gebruikerService.findById(id));
        return modelAndView.addObject("albums", albumService.findAll())
                .addObject(new AlbumForm("", ""));
    }

    @PostMapping("{id}/album")
    public String voegNieuweAlbumToe(@PathVariable long id, @Valid AlbumForm albumForm) {
        String artiestNaam = albumForm.getArtiestNaam();
        if (artiestService.findAll().stream().noneMatch(artiest -> artiest.getNaam().equals(artiestNaam))) {
            artiestService.create(new Artiest(albumForm.getArtiestNaam()));
        }
        Album album = new Album(artiestService.findByNaam(artiestNaam), gebruikerService.findById(id).orElseThrow(GebruikerNietGevondenException::new), albumForm.getAlbumNaam());
        albumService.create(album);
        return "redirect:/gebruiker/{id}";
    }
}
