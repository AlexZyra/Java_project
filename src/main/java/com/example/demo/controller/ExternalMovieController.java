package com.example.demo.controller;

import com.example.demo.service.ExternalMovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/external")
public class ExternalMovieController {

    private final ExternalMovieService externalMovieService;

    public ExternalMovieController(ExternalMovieService externalMovieService) {
        this.externalMovieService = externalMovieService;
    }

    @GetMapping("/popular")
    public String getPopularMovies(Model model) {
        String popularMovies = externalMovieService.getPopularMovies();
        model.addAttribute("popularMovies", popularMovies);
        return "external/popular";
    }
}