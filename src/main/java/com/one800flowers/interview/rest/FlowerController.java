package com.one800flowers.interview.rest;

import com.one800flowers.interview.model.Json;
import com.one800flowers.interview.model.Tally;
import com.one800flowers.interview.model.Update;
import com.one800flowers.interview.service.FeedService;
import com.one800flowers.interview.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flower")
public class FlowerController {

    private FeedService feedService;
    private FlowerService flowerService;

    @Autowired
    public FlowerController(FeedService feedService, FlowerService flowerService) {
        this.feedService = feedService;
        this.flowerService = flowerService;
    }

    @RequestMapping(value = "/usercount",  method = RequestMethod.GET)
    public Tally userCount() {
        return new Tally(flowerService.uniqueUserIds(feedService.feed()));
    }

    @RequestMapping(value = "/update",  method = RequestMethod.POST)
    public List<Json> update(@RequestBody Update update) {
        return this.flowerService.modify(feedService.feed(), update.getElement(), update.getTitle(), update.getBody());
    }
}
