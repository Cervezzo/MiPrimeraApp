package com.android.teaching.miprimeraapp.interactors;

import android.util.Log;

import com.android.teaching.miprimeraapp.R;
import com.android.teaching.miprimeraapp.model.GameModel;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GamesInteractorFirebase {

    private ArrayList<GameModel> games = new ArrayList<>();

    public void getGames(final GameInteractorCallback callback) {
        // 1- Llamar a Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesDatabaseReference =
                database.getReference("games");
        gamesDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // 2- Obtener la lista de GameModel

                for (DataSnapshot gameSnapshot : dataSnapshot.getChildren()) {

                    GameModel game = gameSnapshot.getValue(GameModel.class);
                    games.add(game);
                    Log.d("game", "Game: " + game.getName());
                }

                // 3- Notificar a callback.onGamesAvailable()
                callback.onGamesAvailable();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //errores
            }
        });

    }


    /**
     * Constructor
     */
    public GamesInteractorFirebase() {
        // Si no tengo juegos, los creo
    }

    public ArrayList<GameModel> getGames() {
        return games;
    }

    public GameModel getGameWithId(int id) {
        // Obtener de 'games' el juego con el identificador 'id'
        for (GameModel game : games) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }
}


























