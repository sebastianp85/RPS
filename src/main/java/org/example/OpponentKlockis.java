package org.example;

import java.util.Calendar;

public class OpponentKlockis extends Opponent{
    @Override

    public int makeChoice() {
        Calendar calendar = Calendar.getInstance();
        int currentMin = calendar.get(Calendar.MINUTE);

        if (currentMin < 20){
            return 1;
        } else if (currentMin < 40) {
            return 2;
        } else {
            return 3;
        }
    }
}

