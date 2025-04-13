package com.interview.fling.service;

import com.interview.fling.models.Movie;
import com.interview.fling.models.Review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieService {

    private static MovieService instance;
    private final Map<String, Movie> movieMap;

    private MovieService() {
        this.movieMap = new HashMap<>();
    }

    public static MovieService getInstance() {
        if (instance == null) {
            instance = new MovieService();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
        if (movieMap.containsKey(movie.getName())) {
            return;
        }
        movieMap.put(movie.getName(), movie);
    }

    public boolean movieExist(String movieName) {
        return movieMap.containsKey(movieName);
    }

    public void addReview(Review review) {
        movieMap.get(review.getMovieName()).addReview(review);
    }

    public List<Movie> getMovieListForYear(String year) {
        return movieMap.values().stream()
                .filter(movie -> movie.getYear().equals(year))
                .collect(Collectors.toList());
    }
}
