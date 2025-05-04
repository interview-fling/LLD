package com.interview.fling.services;

import com.interview.fling.model.Theatre;

import java.util.HashMap;
import java.util.Map;

public class TheatreService extends ScreenService{
    private static TheatreService instance;

    public static TheatreService getInstance() {
        if (instance == null) {
            instance = new TheatreService();
        }
        return instance;
    }

    private int theatreCounter;
    private final Map<Integer, Theatre> theatreMap;
    private TheatreService() {
        theatreMap = new HashMap<>();
        theatreCounter = 0;
    }

    public Integer addTheatre(String theatreName) {
        theatreCounter++;
        theatreMap.put(theatreCounter, new Theatre(theatreCounter, theatreName));
        return theatreCounter;
    }

    @Override
    public Integer addScreen(Integer theatreId, String screenName) {
        Theatre theatre = theatreMap.get(theatreId);
        Integer screenId = super.addScreen(theatreId, screenName);
        theatre.addScreen(screenId);
        return screenId;
    }
}
