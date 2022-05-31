package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Gebruiker;
import be.vdab.muziek.services.GebruikerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("nieuwe")
class NieuweGebruikerController {
    private final GebruikerService gebruikerService;

    NieuweGebruikerController(GebruikerService gebruikerService) {
        this.gebruikerService = gebruikerService;
    }
    @GetMapping
    public ModelAndView nieuweGebruiker(){
        var modelAndView = new ModelAndView("nieuwe");
        modelAndView.addObject(new Gebruiker("",""));
        return modelAndView;
    }

    @PostMapping("gebruiker")
    public String voegNieuweGebruikerToe(@Valid Gebruiker gebruiker){
        gebruikerService.create(gebruiker);
        return "redirect:/login";
    }
}
