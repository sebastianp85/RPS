package org.example;

public abstract class Opponent {
    protected int score;

    public abstract int makeChoice();

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }

    public void resetScore() {
        score = 0;
    }
}
