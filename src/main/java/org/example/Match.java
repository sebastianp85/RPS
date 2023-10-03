package org.example;

public class Match {
    private int playerChoice;
    private int computerChoice;
    private int playerScore;
    private int computerScore;

    public Match(int playerChoice, int computerChoice, int playerScore, int computerScore) {
        this.playerChoice = playerChoice;
        this.computerChoice = computerChoice;
        this.playerScore = playerScore;
        this.computerScore = computerScore;
    }

    public int getPlayerChoice() {
        return playerChoice;
    }

    public int getComputerChoice() {
        return computerChoice;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }
}
