package com.example.gestiontournoi;

import java.util.ArrayList;

public class MatchManager {

    private final ArrayList<Match> matchs = new ArrayList<>();

    public void ajouterMatch(Equipe e1, Equipe e2, String date, String lieu) {
        Match match = new Match(e1, e2, date, lieu);
        matchs.add(match);
    }

    public ArrayList<Match> getMatchs() {
        return matchs;
    }

    public Match getMatch(int index) {
        if (index >= 0 && index < matchs.size()) {
            return matchs.get(index);
        }
        return null;
    }

    public void resetScores(Match match) {
        match.resetScores();
    }
}

