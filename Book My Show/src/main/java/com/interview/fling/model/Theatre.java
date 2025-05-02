package com.interview.fling.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    Integer id;
    String name;

    @Getter
    List<Integer> screenIds;

    public void addScreen(Integer screenId) {
        screenIds.add(screenId);
    }

    public Theatre(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.screenIds = new ArrayList<>();
    }
}
