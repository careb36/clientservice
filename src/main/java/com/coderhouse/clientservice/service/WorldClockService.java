package com.coderhouse.clientservice.service;

import com.coderhouse.clientservice.model.WorldClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorldClockService {

    private final RestTemplate restTemplate;

    @Autowired
    public WorldClockService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WorldClock getCurrentDateTime() {
        String url = "http://worldclockapi.com/api/json/utc/now";
        return restTemplate.getForObject(url, WorldClock.class);
    }
}
