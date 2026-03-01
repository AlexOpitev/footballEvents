package org.example.footballevents;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    private TelegramSenderService telegramSenderService;
    private final List<Integer> leagueIds = List.of(235, 236, 78, 79, 140, 142, 66, 65, 61, 62, 94, 95, 135, 136, 253, 88, 307, 188, 98, 99, 100, 101, 497, 102, 242, 144, 268, 128, 265, 250, 722, 292, 293, 833, 482);
    private List<Match> finalResults = new ArrayList<>();
    public List<Match> tempResults = new ArrayList<>();

    public String getMatches() {
        List<Match> results = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "75kwgw7361l0l1ir");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        log.info("Started get data from api.sstats...");
        for (Integer leagueId : leagueIds) {
            String apiUrl = "https://api.sstats.net/games/list?LeagueId=" + leagueId + "&Live=true";
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
            Mapper mapper = new Mapper(response.getBody());
            results.addAll(mapper.addMatch());
        }
        log.info("Finished get data from api.sstats...");
        List<Match> tempResults = filterExist(filter(results));
        log.log(Level.INFO, "results: " + tempResults);
        if (!tempResults.isEmpty()) {
            telegramSenderService.sendMessage("540166889", tempResults.toString());
        }
        return tempResults.toString();
    }

    public List<Match> filter(List<Match> results) {
        return results.stream()
                .filter(m -> m.homeResult == 1)
                .filter(m -> m.awayResult == 1)
                .filter(m -> m.elapsed > 60)
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
