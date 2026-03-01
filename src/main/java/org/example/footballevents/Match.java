package org.example.footballevents;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@RequiredArgsConstructor
@JsonPropertyOrder({ "id", "date", "elapsed", "homeTeamName", "awayTeamName", "homeResult", "awayResult", "statusName"})
class Match{
    public Integer id;
    public String date;
    public Integer elapsed;
    public String homeTeamName;
    public String awayTeamName;
    public Integer homeResult;
    public Integer awayResult;
    public String statusName;

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStatusName() {
        return statusName;
    }

    public Integer getElapsed() {
        return elapsed;
    }

    public Integer getHomeResult() {
        return homeResult;
    }

    public Integer getAwayResult() {
        return awayResult;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setElapsed(Integer elapsed) {
        this.elapsed = elapsed;
    }

    public void setHomeResult(Integer homeResult) {
        this.homeResult = homeResult;
    }

    public void setAwayResult(Integer awayResult) {
        this.awayResult = awayResult;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    @Override
    public String toString() {
        return "Match {time:" + elapsed + " " + homeTeamName + " " + homeResult + ":" + awayResult + " " + awayTeamName + "}";
    }
}
