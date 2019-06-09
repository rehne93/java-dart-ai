package de.baernreuther.dartai.bot;

import de.baernreuther.dartai.data.Numbers;
import de.baernreuther.dartai.model.DartRound;

import java.util.List;

/**
 * A simple dart-bot who throws a given average +/- 3 at all time.
 */
public class SimpleDartBot implements DartBot {

    private boolean hasFinished = false;
    private int scoreLeft = 501;
    private double average = 0.0;

    public SimpleDartBot(double average) {
        this.average = average;
    }

    public SimpleDartBot(int scoreLeft, double average) {
        this.scoreLeft = scoreLeft;
        this.average = average;
    }

    @Override
    public DartRound playOneRound() {
        List<DartRound> combs = Numbers.getPossibleThreeDartCombinations();
        if (this.getScoreLeft() > average + 3) {
            // Just scoring.
            for (DartRound r : combs) {
                if (r.getScore() <= average + 3 && r.getScore() >= average - 3) {
                    this.scoreLeft -= r.getScore();
                    return r;
                }
            }
        } else {
            // Has to check for finish
            if (Numbers.getPossibleDoubleFinishes().contains(this.getScoreLeft())) {
                // We have to calculate a probablity for a finish.  We take the average/100*2 in this example
                System.out.println(this.average/1000*2);
                if (Math.random() < this.average / 1000 * 2) {
                    this.hasFinished = true;
                    System.out.println("FINISH");
                    // We dont calculate if its the first, second or third dart currently -> expect it to be the first always
                    this.scoreLeft = 0;
                    return new DartRound(this.getScoreLeft(), 0, 0);
                } else {
                    System.out.println("NO SCORE");
                    // not finished, simple example: no score
                    return new DartRound(0, 0, 0);
                }

            } else {
                // Getting to a even number
                for (DartRound r : combs) {
                    if (r.getScore() <= average + 3 && (this.getScoreLeft() - r.getScore()) % 2 == 0 && r.getScore() < this.getScoreLeft()) {
                        this.scoreLeft -= r.getScore();
                        return r;
                    }
                }

            }
        }
        return new DartRound(0, 0, 0);
    }

    @Override
    public boolean hasFinished() {
        return this.hasFinished;
    }

    @Override
    public int getScoreLeft() {
        return scoreLeft;
    }
}
