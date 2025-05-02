package com.interview.fling.services;

import com.interview.fling.exception.InvalidShowException;
import com.interview.fling.mappers.SeatToShowSeatMapper;
import com.interview.fling.model.MovieShow;
import com.interview.fling.model.Screen;
import com.interview.fling.model.Seat;
import com.interview.fling.model.ShowSeat;
import com.interview.fling.strategy.PriceCalculatorStrategy;
import com.interview.fling.strategy.impl.DayOfWeekPriceCalculatorStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowService {
    private static ShowService instance;

    private int showCounter;
    private final Map<Integer, MovieShow> movieShowMap;
    private TheatreService theatreService;
    public static ShowService getInstance() {
        if (instance == null) {
            instance = new ShowService();
        }
        return instance;
    }

    private ShowService() {
        this.showCounter = 0;
        this.movieShowMap = new HashMap<>();
        this.theatreService = TheatreService.getInstance();
    }

    public Integer addShow(Integer screenId, Integer movieId, LocalDateTime startTime, List<PriceCalculatorStrategy> strategyList) {
        showCounter++;
        List<Seat> seats = theatreService.getSeats(screenId);
        Map<String, ShowSeat> showSeats = SeatToShowSeatMapper.mapSeatToShowSeat(seats);
        for (PriceCalculatorStrategy strategy : strategyList) {
            strategy.calculatePrice(startTime, showSeats);
        }
        MovieShow movieShow = new MovieShow(showCounter, movieId, screenId, startTime, showSeats);
        movieShowMap.put(showCounter, movieShow);
        return showCounter;
    }

    public Integer addShow(Integer screenId, Integer movieId, LocalDateTime startTime) {
        List<PriceCalculatorStrategy> strategyList = new ArrayList<>();
        strategyList.add(DayOfWeekPriceCalculatorStrategy.getInstance());
        return addShow(screenId, movieId, startTime, strategyList);
    }

    public MovieShow getShow(Integer showId) {
        if (movieShowMap.containsKey(showId)) {
            return movieShowMap.get(showId);
        }
        throw new InvalidShowException();
    }
}
