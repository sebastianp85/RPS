package org.example;

public class OpponentNamnis extends Opponent{
    private String playerName;

    public OpponentNamnis(String playerName) {

        this.playerName = playerName;
    }

    @Override
    public int makeChoice() {
        int numericValue = calculateNumericValue(playerName);

        // Med % 3 får vi ett värde mellan 0-2 och väljer sedan att lägga till 1 för att hamna i intervallet 1-3
        int choice = (numericValue % 3) + 1;

        return choice;
    }

    private int calculateNumericValue(String playerName) {
        int numericValue = 0;
        for (char c : playerName.toCharArray()) {
            numericValue += (int) c;
        }
        return numericValue;
    }
}