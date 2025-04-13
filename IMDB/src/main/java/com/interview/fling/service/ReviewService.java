package com.interview.fling.service;

import com.interview.fling.exception.MovieNotExistException;
import com.interview.fling.exception.UserNotAllowedToAddReviewException;
import com.interview.fling.models.Movie;
import com.interview.fling.models.Review;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewService {
    private static ReviewService instance;
    private final MovieService movieService;
    private final UserService userService;

    private ReviewService() {
        movieService = MovieService.getInstance();
        userService = UserService.getInstance();
    }

    public static ReviewService getInstance() {
        if (instance == null) {
            instance = new ReviewService();
        }
        return instance;
    }

    public void addReview(Review review) {
        if (!userService.getUser(review.getUserId()).allowedToAddMoreRating()) {
            throw new UserNotAllowedToAddReviewException();
        }
        if (!movieService.movieExist(review.getMovieName())) {
            throw new MovieNotExistException();
        }
        userService.addReview(review);
        movieService.addReview(review);
    }

    public Movie topMovieInYear(String year) {
        List<Movie> movieList = movieService.getMovieListForYear(year);
        return movieList.stream()
                .max(Comparator.comparing(Movie::getAverageRating))
                .orElse(null);
    }
}
