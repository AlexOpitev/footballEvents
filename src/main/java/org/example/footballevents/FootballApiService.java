package org.example.footballevents;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class FootballApiService {
    private static final Logger log = Logger.getLogger(FootballApiService.class.getName());
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private TelegramSenderService telegramSenderService;
    private List<Match> finalResults = new ArrayList<>();
    public List<Match> tempResults = new ArrayList<>();

    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanListDaily() {
        log.info("Список будет очищен: " + finalResults);
        finalResults.clear();
        log.info("Список очищен: " + finalResults);
    }

    public String getMatches() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "75kwgw7361l0l1ir");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        log.info("Start receiving data from api.sstats...");
        String apiUrl = "https://api.sstats.net/games/list?Live=true";
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        Mapper mapper = new Mapper(response.getBody());
        List<Match> liveMatch = mapper.addMatch();
        List<Match> needsMatch = liveMatch.stream().filter(match -> League.getIds().contains(match.getLeagueId())).toList();

        List<Match> tempResults = filterExist(filter(needsMatch));
        log.log(Level.INFO, "results: " + tempResults);
        if (!tempResults.isEmpty()) {
            telegramSenderService.sendMessage("540166889", tempResults.toString());
        }
        return tempResults.toString();
    }
    public String getAllMatches() {
        return finalResults.toString();
    }

    public List<Match> filter(List<Match> results) {
        return results.stream()
                .filter(m -> m.homeResult == 1)
                .filter(m -> m.awayResult == 1)
                .filter(m -> m.elapsed > 57 && m.elapsed < 73 )
                .toList();
    }

    public List<Match> filterExist(List<Match> results) {
        tempResults.clear();
        List<Integer> temp1 = finalResults.stream().map(Match::getId).toList();
        for (Match match : results) {
            if (!temp1.contains(match.id)) {
                finalResults.add(match);
                tempResults.add(match);
            }
        }
        return tempResults;
    }

}
