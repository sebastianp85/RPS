package org.example;

public class Player {
    private String name;
    private int score;

    private static Player instance;

    private Player() {
        // Privat konstruktor för att säkerställa Singleton
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }
}
