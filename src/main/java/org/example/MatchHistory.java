package org.example;

import java.util.ArrayList;
import java.util.List;
public class MatchHistory {
    private List<Match> matches;

    public MatchHistory() {
        matches = new ArrayList<>();
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<Match> getMatches() {
        return matches;
    }

}

