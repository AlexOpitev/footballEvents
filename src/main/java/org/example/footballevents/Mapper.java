package org.example.footballevents;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class Mapper {
    private String json;

    public Mapper(String json) {
        this.json = json;
    }

    public List<Match> addMatch() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode dataArray = rootNode.get("data");
        List<Match> results = new ArrayList<>();

        for (JsonNode item : dataArray) {
            Match match = new Match();
            match.setId(item.get("id").asInt());
            match.setElapsed(item.get("elapsed").asInt());
            match.setHomeResult(item.get("homeResult").asInt());
            match.setAwayResult(item.get("awayResult").asInt());
            match.setDate(item.get("date").asText());
            match.setStatusName(item.get("statusName").asText());
            match.setHomeTeamName(item.get("homeTeam").get("name").asText());
            match.setAwayTeamName(item.get("awayTeam").get("name").asText());
            JsonNode league = item.at("/season/league");
            match.setLeagueId(league.get("id").asInt());
            results.add(match);
        }
        return results;
    }
}
