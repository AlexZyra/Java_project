package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.entity.Genre;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAllWithReviews();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public Movie createMovie(Movie movie) {
        if (movie.getGenre() != null && movie.getGenre().getId() != null) {
            Genre genre = genreRepository.findById(movie.getGenre().getId())
                    .orElseThrow(() -> new RuntimeException("Genre not found"));
            movie.setGenre(genre);
        }
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie updatedMovie) {
        Movie existingMovie = getMovieById(id);
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setYear(updatedMovie.getYear());

        if (updatedMovie.getGenre() != null && updatedMovie.getGenre().getId() != null) {
            Genre genre = genreRepository.findById(updatedMovie.getGenre().getId())
                    .orElseThrow(() -> new RuntimeException("Genre not found"));
            existingMovie.setGenre(genre);
        }

        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }

    public List<Movie> searchMovies(String title) {
        if (title == null || title.trim().isEmpty()) {
            return getAllMovies();
        }
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getMoviesByYear(int year) {
        return movieRepository.findByYear(year);
    }
}