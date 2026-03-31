package com.example.demo.service;

import com.example.demo.entity.Genre;
import com.example.demo.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with id: " + id));
    }

    public Genre createGenre(Genre genre) {
        if (genreRepository.existsByNameIgnoreCase(genre.getName())) {
            throw new RuntimeException("Genre already exists: " + genre.getName());
        }
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        Genre genre = getGenreById(id);
        if (!genre.getMovies().isEmpty()) {
            throw new RuntimeException("Cannot delete genre with associated movies");
        }
        genreRepository.deleteById(id);
    }
}