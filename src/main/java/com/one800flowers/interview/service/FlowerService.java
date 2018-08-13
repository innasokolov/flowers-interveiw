package com.one800flowers.interview.service;

import com.one800flowers.interview.model.Json;

import java.util.List;

public interface FlowerService {
    int uniqueUserIds(List<Json> feed);
    List<Json> modify(List<Json> feed, int element, String title, String body);
}
