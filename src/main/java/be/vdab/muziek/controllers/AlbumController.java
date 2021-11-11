package be.vdab.muziek.controllers;

import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.forms.ScoreForm;
import be.vdab.muziek.forms.TrackForm;
import be.vdab.muziek.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("album")
class AlbumController {
    private final AlbumService albumService;

    AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("{id}")
    public ModelAndView album(@PathVariable long id) {
        var modelAndView = new ModelAndView("album");
        albumService.findById(id)
                .ifPresent(album -> modelAndView
                        .addObject(album)
                        .addObject(new ScoreForm(album.getScore()))
                        .addObject(new TrackForm("", "")));
        return modelAndView;
    }

    @PostMapping("{id}/score")
    public ModelAndView wijzigScore(@PathVariable long id, @Valid ScoreForm scoreForm, Errors error, RedirectAttributes redirect) {
        if (error.hasErrors()) {
            var modelAndView = new ModelAndView("album");
            albumService.findById(id).ifPresent(album -> modelAndView.addObject(album));
            return modelAndView;
        }
        try {
            albumService.wijzigScore(id, scoreForm.getScore());
            redirect.addAttribute("idScoreGewijzigd", id);
            return new ModelAndView("redirect:/album/{id}");
        } catch (AlbumNietGevondenException ex) {
            return new ModelAndView("album");
        }
    }

    @PostMapping("{id}/track")
    public ModelAndView toevoegTrack(@PathVariable long id, @Valid TrackForm trackForm, Errors error) {
        if (error.hasErrors()) {
            var modelAndView = new ModelAndView("album");
            albumService.findById(id).ifPresent(album -> modelAndView.addObject(album));
            return modelAndView;
        }
        try {
            albumService.toevoegTrack(id, trackForm.getNaam(), trackForm.getTijd());
            return new ModelAndView("redirect:/album/{id}");
        } catch (AlbumNietGevondenException ex) {
            return new ModelAndView("album");
        }
    }

    @PostMapping("{id}/verwijder")
    public String verwijderAlbum(@PathVariable long id) {
        albumService.delete(id);
        return "redirect:/";
    }
}
