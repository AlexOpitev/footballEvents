package org.example.footballevents;

import lombok.Data;

@Data
public class FootballMatch {
    private String homeTeam;
    private String awayTeam;
    private String date;
    private String score;
}
