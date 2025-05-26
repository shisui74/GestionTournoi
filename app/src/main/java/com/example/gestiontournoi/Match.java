package com.example.gestiontournoi;

public class Match {
    private Equipe equipe1;
    private Equipe equipe2;
    private String date;
    private String lieu;
    private int score1 = 0;
    private int score2 = 0;

    public Match(Equipe e1, Equipe e2, String date, String lieu) {
        this.equipe1 = e1;
        this.equipe2 = e2;
        this.date = date;
        this.lieu = lieu;
    }

    public void addPointsEquipe1(int points) { score1 += points; }
    public void addPointsEquipe2(int points) { score2 += points; }

    public int getScore1() { return score1; }
    public int getScore2() { return score2; }

    public Equipe getEquipe1() { return equipe1; }
    public Equipe getEquipe2() { return equipe2; }

    @Override
    public String toString() {
        return equipe1 + " vs " + equipe2 + " (" + date + ")";
    }
    public void resetScores() {
        this.score1 = 0;
        this.score2 = 0;
    }

}
