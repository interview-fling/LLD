package com.interview.fling.models;

import lombok.Getter;

@Getter
public class Review {
    int rating;
    String text;
    String movieName;
    String userId;

    public Review(String movieName, String userId, String text, int rating) {
        this.rating = rating;
        this.text = text;
        this.movieName = movieName;
        this.userId = userId;
    }
}
