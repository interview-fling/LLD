package com.interview.fling.services;

import com.interview.fling.exception.InvalidScreenException;
import com.interview.fling.model.Screen;
import com.interview.fling.model.Seat;
import com.interview.fling.model.SeatType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScreenService extends SeatService {

//    private static ScreenService instance;
//
//    public static ScreenService getInstance() {
//        if (instance == null) {
//            instance = new ScreenService();
//        }
//        return instance;
//    }

    private int screenCounter;
    private final Map<Integer, Screen> screenMap;

    protected ScreenService() {
        this.screenCounter = 0;
        this.screenMap = new HashMap<>();
    }

    protected Integer addScreen(Integer theatreId, String screenName) {
        screenCounter++;
        Screen screen = new Screen(screenCounter, theatreId, screenName);
        screenMap.put(screenCounter, screen);
        return screenCounter;
    }

    public Integer addSeat(Integer screenId, SeatType seatType, String seatNumber) {
        Screen screen = screenMap.get(screenId);
        Integer seatId = super.addSeat(screenId, seatType, seatNumber);
        screen.getSeatIds().add(seatId);
        return seatId;
    }

    public Screen getScreen(Integer screenId) {
        if (screenMap.containsKey(screenId)) {
            return screenMap.get(screenId);
        }
        throw new InvalidScreenException();
    }

    public List<Seat> getSeats(Integer screenId) {
        if (screenMap.containsKey(screenId)) {
            return screenMap.get(screenId).getSeatIds()
                    .stream()
                    .filter(getSeatMap()::containsKey)
                    .map(getSeatMap()::get)
                    .collect(Collectors.toList());
        }
        throw new InvalidScreenException();
    }


}
