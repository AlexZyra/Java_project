package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalMovieService {

    public String getPopularMovies() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=YOUR_API_KEY";

        return restTemplate.getForObject(url, String.class);
    }
}