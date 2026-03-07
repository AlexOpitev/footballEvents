package org.example.footballevents;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@RequiredArgsConstructor
@JsonPropertyOrder({ "id", "date", "elapsed", "homeTeamName", "awayTeamName", "homeResult", "awayResult", "statusName", "leagueId"})
class Match{
    public Integer id;
    public String date;
    public Integer elapsed;
    public String homeTeamName;
    public String awayTeamName;
    public Integer homeResult;
    public Integer awayResult;
    public String statusName;
    public Integer leagueId;

    @Override
    public String toString() {
        return "Match {time:" + elapsed + " " + homeTeamName + " " + homeResult + ":" + awayResult + " " + awayTeamName + "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public Integer getHomeResult() {
        return homeResult;
    }

    public void setHomeResult(Integer homeResult) {
        this.homeResult = homeResult;
    }

    public Integer getAwayResult() {
        return awayResult;
    }

    public void setAwayResult(Integer awayResult) {
        this.awayResult = awayResult;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }
}
