package com.one800flowers.interview.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one800flowers.interview.model.Json;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class FeedServiceImpl implements  FeedService {

    @Value( "${feed.url}" )
    private String feedUrl;
    @Override
    public List<Json> feed() {
        try {
            return new ObjectMapper().readValue(new URL(feedUrl), new TypeReference<List<Json>>(){});
        } catch (IOException e) {
            throw new IllegalStateException("Unable to get the feed, oh well.");
        }
    }
}
