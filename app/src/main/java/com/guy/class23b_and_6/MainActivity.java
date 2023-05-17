package com.guy.class23b_and_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView main_LST_games;
    private AppCompatImageView main_IMG_background;
    private ArrayList<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_LST_games = findViewById(R.id.main_LST_games);
        main_IMG_background = findViewById(R.id.main_IMG_background);

        Imager.me().imageByDrawableCrop(main_IMG_background, R.drawable.img_pattern_big);
        games = DataManager.mockGames();

        initList();
    }

    private void initList() {
        Adapter_Game adapter_game = new Adapter_Game(games);
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
        //main_LST_games.setLayoutManager(new LinearLayoutManager(this));

        // Grid view
//        main_LST_games.setLayoutManager(new GridLayoutManager(this, 2));

        main_LST_games.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));




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