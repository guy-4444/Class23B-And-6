package com.guy.class23b_and_6;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Game> mockGames() {
        ArrayList<Game> games = new ArrayList<>();

        games.add(new Game()
                .setTitle("FIFA 23")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/1811260/header.jpg")
                .setGenre(Game.GENRE.SPORTS)
                .setDownloads(2600000)
                .setRating(4.8)
                .setYear(2022)
        );

        games.add(new Game()
                .setTitle("Call of Duty®: WWII")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/476600/header.jpg")
                .setGenre(Game.GENRE.ACTION)
                .setDownloads(1900000)
                .setRating(4.1)
                .setYear(2017)
        );

        games.add(new Game()
                .setTitle("Minecraft")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/1928870/header.jpg?t=1682680309")
                .setGenre(Game.GENRE.STRATEGY)
                .setDownloads(4100000)
                .setRating(4.2)
                .setYear(2023)
        );

        games.add(new Game()
                .setTitle("Just Dance")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/446560/header.jpg?t=1680008263")
                .setGenre(Game.GENRE.SPORTS)
                .setDownloads(220000)
                .setRating(3.9)
                .setYear(2016)
        );

        games.add(new Game()
                .setTitle("Age of Empires")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/1017900/header.jpg")
                .setGenre(Game.GENRE.STRATEGY)
                .setDownloads(6000000)
                .setRating(2.4)
                .setYear(1997)
        );

        games.add(new Game()
                .setTitle("Age of Empires II")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/813780/header.jpg")
                .setGenre(Game.GENRE.STRATEGY)
                .setDownloads(12000000)
                .setRating(4.7)
                .setYear(1999)
        );

        games.add(new Game()
                .setTitle("Age of Empires III")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/105450/header.jpg")
                .setGenre(Game.GENRE.STRATEGY)
                .setDownloads(3500000)
                .setRating(4.9)
                .setYear(2005)
        );

        games.add(new Game()
                .setTitle("Age of Empires IV")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/1466860/header.jpg")
                .setGenre(Game.GENRE.STRATEGY)
                .setDownloads(2300000)
                .setRating(3.4)
                .setYear(2021)
        );

        games.add(new Game()
                .setTitle("Jump Puzzle")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/2353790/header.jpg")
                .setGenre(Game.GENRE.PUZZLE)
                .setDownloads(0)
                .setRating(0.0)
                .setYear(2024)
        );

        games.add(new Game()
                .setTitle("Counter-Strike: Global Offensive")
                .setImage("https://cdn.akamai.steamstatic.com/steam/apps/730/header.jpg")
                .setGenre(Game.GENRE.ACTION)
                .setDownloads(30000000)
                .setRating(4.9)
                .setYear(2012)
        );


        return games;
    }
}
