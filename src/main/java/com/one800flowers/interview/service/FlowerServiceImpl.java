package com.one800flowers.interview.service;

import com.one800flowers.interview.model.Json;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Override
    public int uniqueUserIds(List<Json> feed) {
        Set<Integer> userids = new HashSet<>();
        feed.forEach(json -> userids.add(json.getUserId()));
        return userids.size();
    }

    @Override
    public List<Json> modify(List<Json> feed, int element, String title, String body) {
        if (element < 0 || element > feed.size()) {
            throw new IllegalArgumentException("Unable to update, not such element: " + element);
        }
        Json json = feed.get(element - 1);
        json.setTitle(title);
        json.setBody(body);
        feed.set(element - 1, json);
        return feed;
    }
}
