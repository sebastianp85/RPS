package org.example;

import java.util.Scanner;
public class Game {
    private Player player;

    private Opponent opponent;
    private int roundsToWin;
    private MatchHistory matchHistory;
    private Statistics playerStatistics;

    public Game(Player player, Opponent opponent, int roundsToWin, Statistics playerStatistics) {
        this.player = player;
        this.opponent = opponent;
        this.roundsToWin = roundsToWin;
        this.playerStatistics = playerStatistics;

        matchHistory = new MatchHistory();
    }

    public MatchHistory getMatchHistory() {
        return matchHistory;
    }

    public static String getChoiceAsString (int choice) {
        switch (choice) {
            case 1:
                return "Sten";
            case 2:
                return "Sax";
            case 3:
                return "Påse";
            default:
                return "Ogiltigt val";
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        int playerChoice;
        int computerChoice = opponent.makeChoice();

        System.out.println("Välj något av alternativen");
        System.out.println("1. Sten\n2. Sax\n3. Påse");
        playerChoice = scanner.nextInt();

        System.out.println("Du valde: " + getChoiceAsString(playerChoice));
        System.out.println("Datorn valde: " + getChoiceAsString(computerChoice));

        if (playerChoice == computerChoice) {
            System.out.println("Det är lika");
        } else if (playerChoice == 1 && computerChoice == 3 || playerChoice == 2 && computerChoice == 1 || playerChoice == 3 && computerChoice == 2) {
            System.out.println("Du förlorade!");
            opponent.increaseScore();
        } else if (playerChoice == 1 && computerChoice == 2 || playerChoice == 2 && computerChoice == 3 || playerChoice == 3 && computerChoice == 1) {
            System.out.println("Du vann!");
            player.increaseScore();
        }

        Match match = new Match(playerChoice, computerChoice, player.getScore(), opponent.getScore());
        matchHistory.addMatch(match);

        System.out.println("Du har " + player.getScore() + " poäng");
        System.out.println("Datorn har " + opponent.getScore() + " poäng");
    }

    public boolean isGameOver() {
        if (player.getScore() == roundsToWin || opponent.getScore() == roundsToWin) {
            displayResult();

            System.out.println("Vill du spela igen?");
            System.out.println("1. Ja\n2. Nej, avsluta och gå till huvudmenyn");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                return false;
            } else if (choice == 2) {
                return true;
            } else {
                System.out.println("Ogiltigt val. Spelet avslutas och du går tillbaka till huvudmenyn");
                return true;
            }
        }
        return false;
    }

    public void displayResult() {
        System.out.println("Spelet är slut.");
        System.out.println("Resultatet blev: Spelare: " + player.getScore() + " poäng. Dator: " + opponent.getScore() + " poäng");

        playerStatistics.recordMatch();

        if (player.getScore() > opponent.getScore()) {
            System.out.println("Grattis du vann!");

            // Uppdatera statistiken för totala vinster
            playerStatistics.recordWin();

        } else {
            System.out.println("Datorn vann!");
        }

        //Uppdaterar statistiken för varje enskild motståndare
        if (opponent instanceof OpponentSlumpis) {
            playerStatistics.recordSlumpisMatch();
            if(player.getScore() > opponent.getScore()) playerStatistics.recordSlumpisWin();

        } else if (opponent instanceof OpponentKlockis) {
            playerStatistics.recordKlockisMatch();
            if(player.getScore() > opponent.getScore()) playerStatistics.recordKlockisWin();

        } else if (opponent instanceof OpponentNamnis) {
            playerStatistics.recordNamnisMatch();
            if(player.getScore() > opponent.getScore()) playerStatistics.recordNamnisWin();
        }

        player.resetScore();
        opponent.resetScore();
    }
}
