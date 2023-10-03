package org.example;

public class OpponentSlumpis extends Opponent{
    @Override
    public int makeChoice() {
        return (int) (Math.random() * 3) + 1;
    }
}
