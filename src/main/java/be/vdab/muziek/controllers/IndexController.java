package be.vdab.muziek.controllers;

import be.vdab.muziek.services.AlbumService;
import be.vdab.muziek.services.GebruikerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
class IndexController {
    private final AlbumService albumService;
    private final GebruikerService gebruikerService;

    IndexController(AlbumService albumService, GebruikerService gebruikerService) {
        this.albumService = albumService;
        this.gebruikerService = gebruikerService;
    }

    @GetMapping
    public ModelAndView index(){
        var modelAndView =new ModelAndView("index", "gebruikers", gebruikerService.findAll());
        return modelAndView.addObject("albums", albumService.findAll());
    }
}
