package com.interview.fling;

import com.interview.fling.exception.UserNotAllowedToAddReviewException;
import com.interview.fling.models.Movie;
import com.interview.fling.models.Review;
import com.interview.fling.models.User;
import com.interview.fling.models.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.interview.fling.service.MovieService;
import com.interview.fling.service.ReviewService;
import com.interview.fling.service.UserService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private static final DecimalFormat df = new DecimalFormat("0.00");


    @Test
    void testUserQuery() {
        MovieService movieService = MovieService.getInstance();
        UserService userService = UserService.getInstance();
        ReviewService reviewService = ReviewService.getInstance();


        movieService.addMovie(new Movie("Movie1", "2010"));
        movieService.addMovie(new Movie("Movie2", "2010"));
        movieService.addMovie(new Movie("Movie3", "2010"));
        movieService.addMovie(new Movie("Movie4", "2010"));

        userService.addUser(new User("1", UserType.FREE));
        userService.addUser(new User("2", UserType.FREE));
        userService.addUser(new User("3", UserType.PAID));
        userService.addUser(new User("4", UserType.PAID));

        reviewService.addReview(new Review("Movie1", "1", "Great movie!", 5));
        reviewService.addReview(new Review("Movie1", "2", "Great movie!", 4));
        reviewService.addReview(new Review("Movie1", "3", "Great movie!", 1));
        reviewService.addReview(new Review("Movie1", "4", "Great movie!", 2));

        reviewService.addReview(new Review("Movie2", "1", "Great movie!", 3));
        reviewService.addReview(new Review("Movie2", "2", "Great movie!", 2));
        reviewService.addReview(new Review("Movie2", "3", "Great movie!", 2));
        reviewService.addReview(new Review("Movie2", "4", "Great movie!", 2));

        Assertions.assertThrows(UserNotAllowedToAddReviewException.class, () -> {reviewService.addReview(new Review("Movie3", "1", "Great movie!", 1));});
        Assertions.assertThrows(UserNotAllowedToAddReviewException.class, () -> {reviewService.addReview(new Review("Movie3", "2", "Great movie!", 4));});
        reviewService.addReview(new Review("Movie3", "3", "Great movie!", 3));
        reviewService.addReview(new Review("Movie3", "4", "Great movie!", 2));

        Assertions.assertThrows(UserNotAllowedToAddReviewException.class, () -> {reviewService.addReview(new Review("Movie4", "1", "Great movie!", 5));});
        Assertions.assertThrows(UserNotAllowedToAddReviewException.class, () -> {reviewService.addReview(new Review("Movie4", "2", "Great movie!", 2));});
        reviewService.addReview(new Review("Movie4", "3", "Great movie!", 4));
        reviewService.addReview(new Review("Movie4", "4", "Great movie!", 2));

        // Movie 1 = 12/ 4
        // Movie 2 = 9 / 4
        // Movie 3 = 5 / 2
        // Movie 4 = 6 / 2

        List<Movie> movieList = movieService.getMovieListForYear("2010");
        Assertions.assertEquals(4, movieList.size());
        Assertions.assertEquals("Movie1", movieList.get(0).getName());
        Assertions.assertEquals("Movie2", movieList.get(1).getName());
        Assertions.assertEquals("Movie3", movieList.get(2).getName());
        Assertions.assertEquals("Movie4", movieList.get(3).getName());
        Assertions.assertEquals((Double) 3.0, movieList.get(0).getAverageRating());
        Assertions.assertEquals((Double) 2.25, movieList.get(1).getAverageRating());
        Assertions.assertEquals((Double) 2.5, movieList.get(2).getAverageRating());
        Assertions.assertEquals((Double) 3.0, movieList.get(3).getAverageRating());

        Movie topMovie = reviewService.topMovieInYear("2010");
        assertEquals("Movie1", topMovie.getName());
    }

    @Test
    void testUserQuery2() {
        MovieService movieService = MovieService.getInstance();
        UserService userService = UserService.getInstance();
        ReviewService reviewService = ReviewService.getInstance();


        movieService.addMovie(new Movie("Movie1", "2010"));
        movieService.addMovie(new Movie("Movie2", "2010"));
        movieService.addMovie(new Movie("Movie3", "2011"));
        movieService.addMovie(new Movie("Movie4", "2011"));

        userService.addUser(new User("1", UserType.FREE));
        userService.addUser(new User("2", UserType.PAID));
        userService.addUser(new User("3", UserType.PAID));
        userService.addUser(new User("4", UserType.PAID));

        reviewService.addReview(new Review("Movie1", "1", "Great movie!", 5));
        reviewService.addReview(new Review("Movie1", "2", "Great movie!", 4));
        reviewService.addReview(new Review("Movie1", "3", "Great movie!", 1));
        reviewService.addReview(new Review("Movie1", "4", "Great movie!", 2));

        reviewService.addReview(new Review("Movie2", "1", "Great movie!", 3));
        reviewService.addReview(new Review("Movie2", "2", "Great movie!", 2));
        reviewService.addReview(new Review("Movie2", "3", "Great movie!", 2));
        reviewService.addReview(new Review("Movie2", "4", "Great movie!", 2));

        Assertions.assertThrows(UserNotAllowedToAddReviewException.class, () -> {reviewService.addReview(new Review("Movie3", "1", "Great movie!", 1));});
        reviewService.addReview(new Review("Movie3", "2", "Great movie!", 4));
        reviewService.addReview(new Review("Movie3", "3", "Great movie!", 3));
        reviewService.addReview(new Review("Movie3", "4", "Great movie!", 2));

        Assertions.assertThrows(UserNotAllowedToAddReviewException.class, () -> {reviewService.addReview(new Review("Movie4", "1", "Great movie!", 5));});
        reviewService.addReview(new Review("Movie4", "2", "Great movie!", 2));
        reviewService.addReview(new Review("Movie4", "3", "Great movie!", 4));
        reviewService.addReview(new Review("Movie4", "4", "Great movie!", 2));

        // Movie 1 = 12/ 4
        // Movie 2 = 9 / 4
        // Movie 3 = 9 / 3
        // Movie 4 = 8 / 3

        List<Movie> movieList = movieService.getMovieListForYear("2010");
        Assertions.assertEquals(2, movieList.size());
        Assertions.assertEquals("Movie1", movieList.get(0).getName());
        Assertions.assertEquals("Movie2", movieList.get(1).getName());
        Assertions.assertEquals((Double) 3.0, movieList.get(0).getAverageRating());
        Assertions.assertEquals((Double) 2.25, movieList.get(1).getAverageRating());

        movieList = movieService.getMovieListForYear("2011");
        Assertions.assertEquals(2, movieList.size());
        Assertions.assertEquals("Movie3", movieList.get(0).getName());
        Assertions.assertEquals("Movie4", movieList.get(1).getName());
        Assertions.assertEquals((Double) 3.0, movieList.get(0).getAverageRating());
        Assertions.assertEquals("2.67", df.format(movieList.get(1).getAverageRating()));

        Movie topMovie = reviewService.topMovieInYear("2010");
        assertEquals("Movie1", topMovie.getName());

        Movie topMovie2 = reviewService.topMovieInYear("2011");
        assertEquals("Movie3", topMovie2.getName());
    }
}