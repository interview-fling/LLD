package com.interview.fling.services;

import com.interview.fling.model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieService {
    private static MovieService instance;

    public static MovieService getInstance() {
        if (instance == null) {

            instance = new MovieService();
        }
        return instance;
    }

    private int count;
    private final Map<Integer, Movie> movieMap;
    private MovieService() {
        this.movieMap = new HashMap<>();
        this.count = 0;
    }

    public Integer addMovie(Movie movie) {
        count++;
        movieMap.put(count, movie);
        return count;
    }
}
