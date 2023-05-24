package com.guy.class23b_and_6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView main_LST_games;
    private AppCompatImageView main_IMG_background;

    private ArrayList<Game> games;
    private Adapter_Game adapter_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LST_games = findViewById(R.id.main_LST_games);
        main_IMG_background = findViewById(R.id.main_IMG_background);
        Imager.me().imageByDrawableCrop(main_IMG_background, R.drawable.img_pattern_big);

        initList();

        //games = DataManager.mockGames();
        //updateList(games);




        //write();
        //read();
        changeDownloads("Age of Empires");

        updateGamesFromServer();
    }

    private void changeDownloads(String gameId) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference();

        //gamesRef.child("GAMES/Age of Empires/downloads").setValue(400);
        gamesRef.child("GAMES")
                .child(gameId)
                .child("downloads")
                .setValue(4022);
    }

    private void updateGamesFromServer() {
        games = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference("GAMES");

        gamesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("pttt", snapshot.toString());
                for (DataSnapshot child : snapshot.getChildren()) {
                    Game game = child.getValue(Game.class);
                    games.add(game);
                    Log.d("pttt", game.getTitle() + " added");
                }
                updateList(games);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("pttt", "onCancelled(" + error.getMessage() + ")");
            }
        });
    }

    private void read() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference("GAMES");

        gamesRef
                .child("Age of Empires")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("pttt", "onDataChange()");
                Game game = snapshot.getValue(Game.class);

                Log.d("pttt", snapshot.toString());
                Log.d("pttt", "downloads= " + game.getDownloads());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("pttt", "onCancelled(" + error.getMessage() + ")");

            }
        });

    }

    private void write() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference gamesRef = database.getReference("GAMES");
        for (Game game : games) {
            gamesRef.child(game.getTitle()).setValue(game);
        }
    }

    private void updateList(ArrayList<Game> games) {
        adapter_game.updateList(games);
    }

    private void initList() {
        adapter_game = new Adapter_Game(games);
        adapter_game.setOnGameClickListener(new Adapter_Game.OnGameClickListener() {
            @Override
            public void onClick(View view, Game game, int position) {
                Toast.makeText(MainActivity.this, game.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLikeClicked(View view, Game game, int position) {
                likeClicked(position);
            }
        });

        // one item in a row
//        main_LST_games.setLayoutManager(new LinearLayoutManager(this));

        // Grid view
        main_LST_games.setLayoutManager(new GridLayoutManager(this, 2));

        // Horizontal
//        main_LST_games.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));




        main_LST_games.setHasFixedSize(true);
        main_LST_games.setAdapter(adapter_game);

    }

    private void likeClicked(int position) {
        Game game = games.get(position);
        game.setLiked(!game.isLiked());
        main_LST_games.getAdapter().notifyItemChanged(position);

        Snackbar
                .make(findViewById(android.R.id.content),(game.isLiked() ? "liked " : "unliked ") + game.getTitle(), Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        game.setLiked(!game.isLiked());
                        main_LST_games.getAdapter().notifyItemChanged(position);
                    }
                })
                .show();
    }

}
