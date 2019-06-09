package de.baernreuther.dartai.model;

/**
 * Simulates one Dart-Shot containing three darts.
 */
public class DartRound {

    private int[] shots = new int[3];

    public DartRound(int score1, int score2, int score3) {
        shots[0] = score1;
        shots[1] = score2;
        shots[2] = score3;
    }

    public DartRound(int[] shots) {
        // TODO: ERROR HANDLING WHEN LENGTH != 3
        if (shots.length == 3) {
            this.shots = shots;
        }
    }

    public int[] getShots(){
        return this.shots;
    }

    public int getFirstDart(){
        return this.shots[0];
    }

    public int getSecondDart(){
        return this.shots[1];
    }
    public int getThirdDart(){
        return this.shots[2];
    }

    public int getScore(){
        return this.shots[0] + this.shots[1] + this.shots[2];
    }

}
