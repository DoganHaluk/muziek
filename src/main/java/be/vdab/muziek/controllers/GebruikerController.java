package be.vdab.muziek.controllers;

import be.vdab.muziek.services.AlbumService;
import be.vdab.muziek.services.GebruikerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gebruiker")
class GebruikerController {
    private final AlbumService albumService;
    private final GebruikerService gebruikerService;

    GebruikerController(AlbumService albumService, GebruikerService gebruikerService) {
        this.albumService = albumService;
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("{id}")
    public ModelAndView gebruiker(@PathVariable long id){
        var modelAndView =new ModelAndView("gebruiker", "gebruiker", gebruikerService.findById(id));
        return modelAndView.addObject("albums", albumService.findAll());
    }
}
