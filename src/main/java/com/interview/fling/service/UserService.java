package com.interview.fling.service;

import com.interview.fling.exception.UserNotFoundException;
import com.interview.fling.models.Movie;
import com.interview.fling.models.Review;
import com.interview.fling.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static UserService instance;
    private final Map<String, User> userMap;

    private UserService() {
        this.userMap = new HashMap<>();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void addUser(User user) {
        if (userMap.containsKey(user.getId())) {
            return;
        }
        userMap.put(user.getId(), user);
    }

    public User getUser(String userId) {
        if (userMap.containsKey(userId)) {
            return userMap.get(userId);
        }
        throw new UserNotFoundException();
    }

    public void addReview(Review review) {
        userMap.get(review.getUserId()).addReview(review);
    }
}
