
package org.example;

import java.util.List;
import java.util.Scanner;

import static org.example.Statistics.displayGameStats;
public class MainMenu {

    public static void welcomeText(){
        System.out.println("Välkommen till Sten-Sax-Påse!\nVälj något av följande alternativ:");
    }

    public static void displayMainMenu(){
        System.out.println("1. Starta ett nytt spel");
        System.out.println("2. Visa historik");
        System.out.println("3. Visa statistik");
        System.out.println("0. Avsluta spelet");
    }
    public static void displayPlayMenu(){
        Scanner scanner = new Scanner(System.in);
        String playerName;
        int totalPlayerScore = 0;
        int totalComputerScore = 0;
        Game game = null;
        Player player = null;
        Statistics playerStatistics = Statistics.getInstance();
        while (true) {
            MainMenu.displayMainMenu();

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Ange ditt namn: ");
                    playerName = scanner.nextLine().trim();

                    if (!playerName.isEmpty()) {
                        player = Player.getInstance();
                        player.setName(playerName);

                        System.out.println("Välj motståndare:");
                        System.out.println("1. Slumpis");
                        System.out.println("2. Klockis");
                        System.out.println("3. Namnis");

                        int opponentChoice = scanner.nextInt();

                        Opponent opponent;

                        switch (opponentChoice) {
                            case 1:
                                opponent = new OpponentSlumpis();
                                break;
                            case 2:
                                opponent = new OpponentKlockis();
                                break;
                            case 3:
                                opponent = new OpponentNamnis(playerName);
                                break;
                            default:
                                opponent = new OpponentSlumpis(); // Standardmotståndare om ogiltigt val
                                break;
                        }

                        System.out.print("Ange antal rundor för att vinna: ");
                        int roundsToWin = scanner.nextInt();
                        game = new Game(player, opponent, roundsToWin, playerStatistics);

                        while (!game.isGameOver()) {
                            game.play();
                        }
                    } else {
                        System.out.println("Du måste ange ett namn för att starta spelet.");
                    }
                    break;

                case 2:
                    if (game != null) { // Kontrollera om game är instansierat innan du försöker komma åt det
                        List<Match> matchList = game.getMatchHistory().getMatches();
                        System.out.println("Matchhistorik:");
                        for (Match match : matchList) {
                            System.out.println(player.getName() + " valde: " + Game.getChoiceAsString(match.getPlayerChoice()));
                            System.out.println("Datorn valde: " + Game.getChoiceAsString(match.getComputerChoice()));
                            System.out.println(player.getName() + ": " + match.getPlayerScore() + " poäng");
                            System.out.println("Datorn: " + match.getComputerScore() + " poäng");
                            System.out.println("-----------------------------");

                            totalPlayerScore = match.getPlayerScore();
                            totalComputerScore = match.getComputerScore();
                        }
                        if(totalComputerScore > totalPlayerScore) {
                            System.out.println("Datorn vann med " + totalComputerScore + "-" + totalPlayerScore);
                        }  else {
                            System.out.println(player.getName() + " vann med " + totalPlayerScore + "-" + totalComputerScore);
                        }
                        System.out.println();

                    } else {
                        System.out.println("Inga matcher har spelats ännu.");
                    }
                    break;
                case 3:
                    displayGameStats();
                    break;
                case 0:
                    System.out.println("Tack för att du spelade. Hej då!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Ogiltigt val. Försök igen.");
                    break;
            }
        }
    }
}


