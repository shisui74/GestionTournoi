package com.example.gestiontournoi;

import java.util.ArrayList;

public class EquipeManager {
    private ArrayList<Equipe> equipes = new ArrayList<>();

    public void ajouterEquipe(String nom, String ville) {
        equipes.add(new Equipe(nom, ville));
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }
}
