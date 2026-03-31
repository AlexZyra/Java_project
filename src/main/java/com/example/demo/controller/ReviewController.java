package com.example.demo.controller;

import com.example.demo.entity.Review;
import com.example.demo.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/{movieId}")
    public String addReview(@PathVariable Long movieId, @RequestParam String comment) {
        Review review = new Review();
        review.setComment(comment);
        reviewService.addReview(movieId, review);
        return "redirect:/movies/" + movieId;
    }

    @GetMapping("/delete/{movieId}/{reviewId}")
    public String deleteReview(@PathVariable Long movieId, @PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/movies/" + movieId;
    }
}