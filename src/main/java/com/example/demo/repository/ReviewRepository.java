package com.example.demo.repository;

import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieId(Long movieId);

//    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.movie.id = :movieId")
//    Double getAverageRatingByMovieId(Long movieId);
}