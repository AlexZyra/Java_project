package com.example.demo.service;

import com.example.demo.entity.Review;
import com.example.demo.entity.Movie;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewService(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    public List<Review> getReviewsByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    public Review addReview(Long movieId, Review review) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        review.setMovie(movie);
        return reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // ВИДАЛІТЬ або закоментаруйте цей метод:
    // public Double getAverageRating(Long movieId) {
    //     return reviewRepository.getAverageRatingByMovieId(movieId);
    // }
}