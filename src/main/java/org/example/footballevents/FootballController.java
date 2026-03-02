package org.example.footballevents;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FootballController {
    private final FootballApiService footballApiService;

    public FootballController(FootballApiService footballApiService) {
        this.footballApiService = footballApiService;
    }

    @Scheduled(fixedRate = 120000)
    @GetMapping("/matches")
    public String getMatches() {
        return footballApiService.getMatches();
    }
}
