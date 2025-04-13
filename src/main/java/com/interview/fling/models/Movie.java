package com.interview.fling.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    @Getter
    String name;
    @Getter
    String year;
    List<Review> reviewList;
    @Getter
    double averageRating;

    public Movie(String name, String year) {
        this.name = name;
        this.year = year;
        this.reviewList = new ArrayList<>();
        this.averageRating = 0.0;
    }

    public void addReview(Review review) {
        reviewList.add(review);
        averageRating = (averageRating*(reviewList.size()-1) + review.rating) / (reviewList.size());
    }
}
