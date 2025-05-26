package com.example.gestiontournoi;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // UI - Section Créer une équipe
    private EditText editNomEquipe, editVilleEquipe;
    private Button btnCreerEquipe;

    // UI - Section Créer un match
    private EditText editDate, editLieu;
    private Spinner spinnerEquipe1, spinnerEquipe2;
    private Button btnCreerMatch;

    // UI - Section Gérer un match
    private Spinner spinnerMatch;
    private TextView nomEquipeA, nomEquipeB, scoreEquipeA, scoreEquipeB;
    private Button btnAdd1A, btnAdd3A, btnAdd1B, btnAdd3B, btnFinMatch;

    // Gestionnaires
    private EquipeManager equipeManager;
    private MatchManager matchManager;

    // Adapter pour spinner de matchs
    private ArrayAdapter<Match> matchAdapter;

    // Match sélectionné
    private Match matchActuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // doit correspondre à ton layout XML

        // Init gestionnaires
        equipeManager = new EquipeManager();
        matchManager = new MatchManager();

        // --- Liaison UI ---

        // Création d’équipe
        editNomEquipe = findViewById(R.id.editNomEquipe);
        editVilleEquipe = findViewById(R.id.editvilleEquipe);
        btnCreerEquipe = findViewById(R.id.buttonCreerEquipe);

        // Création de match
        editDate = findViewById(R.id.editDate);
        editLieu = findViewById(R.id.editLieu);
        spinnerEquipe1 = findViewById(R.id.spinnerEquipe1);
        spinnerEquipe2 = findViewById(R.id.spinnerEquipe2);
        btnCreerMatch = findViewById(R.id.buttonCreerMatch);

        // Gestion match
        spinnerMatch = findViewById(R.id.spinnerMatch);
        nomEquipeA = findViewById(R.id.editEquipe1);
        nomEquipeB = findViewById(R.id.editEquipe2);
        scoreEquipeA = findViewById(R.id.textview_score);
        scoreEquipeB = findViewById(R.id.textview_score2);
        btnAdd1A = findViewById(R.id.buttonAdd1A);
        btnAdd3A = findViewById(R.id.buttonAdd3A);
        btnAdd1B = findViewById(R.id.buttonAdd1B);
        btnAdd3B = findViewById(R.id.buttonAdd3B);
        btnFinMatch = findViewById(R.id.buttonFinMatch);

        // Adapter équipes
        ArrayAdapter<Equipe> equipeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, equipeManager.getEquipes());
        equipeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEquipe1.setAdapter(equipeAdapter);
        spinnerEquipe2.setAdapter(equipeAdapter);

        // Adapter matchs
        matchAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, matchManager.getMatchs());
        matchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatch.setAdapter(matchAdapter);

        // --- Boutons ---

        btnCreerEquipe.setOnClickListener(v -> {
            String nom = editNomEquipe.getText().toString().trim();
            String ville = editVilleEquipe.getText().toString().trim();

            if (!nom.isEmpty() && !ville.isEmpty()) {
                equipeManager.ajouterEquipe(nom, ville);
                equipeAdapter.notifyDataSetChanged();
                Toast.makeText(this, "Équipe ajoutée", Toast.LENGTH_SHORT).show();
                editNomEquipe.setText("");
                editVilleEquipe.setText("");
            } else {
                Toast.makeText(this, "Nom et ville obligatoires", Toast.LENGTH_SHORT).show();
            }
        });

        btnCreerMatch.setOnClickListener(v -> {
            int pos1 = spinnerEquipe1.getSelectedItemPosition();
            int pos2 = spinnerEquipe2.getSelectedItemPosition();

            if (pos1 == pos2) {
                Toast.makeText(this, "Choisissez deux équipes différentes", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pos1 >= 0 && pos2 >= 0 && equipeManager.getEquipes().size() > 1) {
                String date = editDate.getText().toString().trim();
                String lieu = editLieu.getText().toString().trim();

                if (!date.isEmpty() && !lieu.isEmpty()) {
                    Equipe e1 = equipeManager.getEquipes().get(pos1);
                    Equipe e2 = equipeManager.getEquipes().get(pos2);
                    matchManager.ajouterMatch(e1, e2, date, lieu);
                    matchAdapter.notifyDataSetChanged();
                    Toast.makeText(this, "Match ajouté", Toast.LENGTH_SHORT).show();
                    editDate.setText("");
                    editLieu.setText("");
                } else {
                    Toast.makeText(this, "Date et lieu requis", Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinnerMatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matchActuel = matchManager.getMatch(position);
                if (matchActuel != null) {
                    afficherMatch();
                }
            }

            @Override public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnAdd1A.setOnClickListener(v -> ajouterPoints(true, 1));
        btnAdd3A.setOnClickListener(v -> ajouterPoints(true, 3));
        btnAdd1B.setOnClickListener(v -> ajouterPoints(false, 1));
        btnAdd3B.setOnClickListener(v -> ajouterPoints(false, 3));

        btnFinMatch.setOnClickListener(v -> {
            if (matchActuel != null) {
                String message = "Match terminé :\n"
                        + matchActuel.getEquipe1().getNom() + " " + matchActuel.getScore1()
                        + " - " + matchActuel.getScore2() + " " + matchActuel.getEquipe2().getNom();
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void afficherMatch() {
        nomEquipeA.setText(matchActuel.getEquipe1().getNom());
        nomEquipeB.setText(matchActuel.getEquipe2().getNom());
        scoreEquipeA.setText(String.valueOf(matchActuel.getScore1()));
        scoreEquipeB.setText(String.valueOf(matchActuel.getScore2()));
    }

    private void ajouterPoints(boolean equipeA, int points) {
        if (matchActuel == null) return;

        if (equipeA) {
            matchActuel.addPointsEquipe1(points);
        } else {
            matchActuel.addPointsEquipe2(points);
        }
        afficherMatch();
    }
}
