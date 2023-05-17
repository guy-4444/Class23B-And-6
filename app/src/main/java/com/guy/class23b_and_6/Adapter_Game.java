package com.guy.class23b_and_6;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;
import java.util.Locale;

public class Adapter_Game extends RecyclerView.Adapter<Adapter_Game.GameViewHolder> {

    private List<Game> games;
    private OnGameClickListener onGameClickListener;

    public Adapter_Game(List<Game> games) {
        this.games = games;
    }

    public void setOnGameClickListener(OnGameClickListener onGameClickListener) {
        this.onGameClickListener = onGameClickListener;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d("ptttA", "onCreateViewHolder()");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_game_horizontal, viewGroup, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Log.d("ptttB", "- - onBindViewHolder() " + position);
        Game game = getItem(position);

        holder.game_LBL_title.setText(game.getTitle());
        holder.game_LBL_genre.setText(game.getGenre().toString().toLowerCase(Locale.US));
        holder.game_LBL_year.setText("" + game.getYear());
        holder.game_LBL_downloads.setText(game.getDownloads() + " downloads");

        holder.game_RTG_rating.setRating((float) game.getRating());

        Imager.me().imageCrop(holder.game_IMG_main, game.getImage());

        if (game.isLiked()) {
            holder.game_IMG_like.setImageResource(R.drawable.ic_heart_filled);
        } else {
            holder.game_IMG_like.setImageResource(R.drawable.ic_heart_linear);
        }
    }

    @Override
    public int getItemCount() {
        return games == null ? 0 : games.size();
    }

    private Game getItem(int position) {
        return games.get(position);
    }

    public interface OnGameClickListener {
        void onClick(View view, Game game, int position);
        void onLikeClicked(View view, Game game, int position);
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView game_IMG_main;
        private AppCompatImageView game_IMG_like;
        private MaterialTextView game_LBL_title;
        private MaterialTextView game_LBL_genre;
        private MaterialTextView game_LBL_year;
        private MaterialTextView game_LBL_downloads;
        private AppCompatRatingBar game_RTG_rating;

        GameViewHolder(View itemView) {
            super(itemView);
            game_IMG_main = itemView.findViewById(R.id.game_IMG_main);
            game_IMG_like = itemView.findViewById(R.id.game_IMG_like);
            game_LBL_title = itemView.findViewById(R.id.game_LBL_title);
            game_LBL_genre = itemView.findViewById(R.id.game_LBL_genre);
            game_LBL_year = itemView.findViewById(R.id.game_LBL_year);
            game_LBL_downloads = itemView.findViewById(R.id.game_LBL_downloads);
            game_RTG_rating = itemView.findViewById(R.id.game_RTG_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGameClickListener.onClick(v, getItem(getAdapterPosition()), getAdapterPosition());
                }
            });

            game_IMG_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onGameClickListener.onLikeClicked(v, getItem(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }

}