package com.interview.fling.models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class User {
    @Getter
    String id;
    UserType userType;
    List<Review> reviewList;

    public User(String id, UserType userType) {
        this.id = id;
        this.userType = userType;
        this.reviewList = new ArrayList<>();
    }

    public boolean allowedToAddMoreRating() {
        return userType == UserType.PAID || reviewList.size() < 2;
    }

    public void addReview(Review review) {
        reviewList.add(review);
    }
}
