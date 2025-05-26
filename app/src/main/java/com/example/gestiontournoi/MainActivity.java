package com.example.gestiontournoi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//       EquipeManager equipeManager = new EquipeManager();
//       MatchManager matchManager = new MatchManager();
//
//        //btnCreerEquipe.setOnClickListener(v -> {
//            String nom = editNomEquipe.getText().toString();
//            String ville = editVilleEquipe.getText().toString();
//            equipeManager.ajouterEquipe(nom, ville);
//            // Mettre à jour spinner etc.
//        });
//
//        MatchManager matchManager = new MatchManager();
//
//        btnCreerMatch.setOnClickListener(v -> {
//            Equipe eq1 = equipeManager.getEquipes().get(spinnerEquipe1.getSelectedItemPosition());
//            Equipe eq2 = equipeManager.getEquipes().get(spinnerEquipe2.getSelectedItemPosition());
//            String date = editDate.getText().toString();
//            String lieu = editLieu.getText().toString();
//
//            if (!eq1.equals(eq2) && !date.isEmpty() && !lieu.isEmpty()) {
//                matchManager.ajouterMatch(eq1, eq2, date, lieu);
//                // Mettre à jour le spinner de matchs
//            }
//        });


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}