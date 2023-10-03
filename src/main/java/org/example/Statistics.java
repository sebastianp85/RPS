package org.example;

import java.util.stream.Stream;

public class Statistics {
    private static Statistics instace;
    private int totalWins;
    private int totalMatches;
    private int slumpisWins;
    private int slumpisMatches;
    private int klockisWins;
    private int klockisMatches;
    private int namnisWins;
    private int namnisMatches;

    private Statistics(){}

    public static Statistics getInstance() {
        if(instace == null) {
            instace = new Statistics();
        }
        return  instace;
    }

    public void recordWin() {
        totalWins++;
    }

    public void recordMatch() {
        totalMatches++;
    }

    public void recordSlumpisWin() {
        slumpisWins++;
    }

    public void recordSlumpisMatch(){
        slumpisMatches++;
    }

    public void recordKlockisWin() {
        klockisWins++;
    }

    public void recordKlockisMatch(){
        klockisMatches++;
    }

    public void recordNamnisWin() {
        namnisWins++;
    }

    public void recordNamnisMatch(){
        namnisMatches++;
    }

    public int getTotalWins() {
        return totalWins;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public double getWinPercentage() {
        return totalWins == 0 ? 0.0 : (double) totalWins / totalMatches * 100;
    }

    public double getSlumpisWinPercentage() {
        return slumpisWins == 0 ? 0.0 :
                Stream.of(slumpisWins, slumpisMatches)
                        .filter(i -> i > 0) // Filtrera bort nollor för att undvika division med noll
                        .mapToDouble(i -> ((double) i) / slumpisMatches * 100) // Beräkna vinstprocenten
                        .findFirst() // Hitta första resultatet som är vinstprocenten
                        .orElse(0.0); // Om inget resultat hittas, returnera 0.0
    }

    public double getKlockisWinPercentage() {
        return klockisWins == 0 ? 0.0 :
                Stream.of(klockisWins, klockisMatches)
                        .filter(i -> i > 0)
                        .mapToDouble(i -> ((double) i) / klockisMatches * 100)
                        .findFirst()
                        .orElse(0.0);
    }


    public double getNamnisWinPercentage() {
        return namnisWins == 0 ? 0.0 :
                     Stream.of(namnisWins, namnisMatches)
                    .filter(i -> i > 0)
                    .mapToDouble(i -> ((double) i) / namnisMatches * 100)
                    .findFirst()
                    .orElse(0.0);
        }

    public static void displayGameStats(){
        Statistics playerStatistics = getInstance();
        System.out.println("Totala vinster: " +  playerStatistics.getTotalWins());
        System.out.println("Totala matcher: " + playerStatistics.getTotalMatches());
        System.out.println("Total vinstprocent: " + playerStatistics.getWinPercentage() + "%");
        System.out.println("Vinstprocent mot Slumpis: " + playerStatistics.getSlumpisWinPercentage() + "%");
        System.out.println("Vinstprocent mot Klockis: " + playerStatistics.getKlockisWinPercentage() + "%");
        System.out.println("Vinstprocent mot Namnis: " + playerStatistics.getNamnisWinPercentage() + "%");
    }
}



