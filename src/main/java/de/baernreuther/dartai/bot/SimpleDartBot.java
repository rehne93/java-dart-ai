package de.baernreuther.dartai.bot;

import de.baernreuther.dartai.data.Numbers;
import de.baernreuther.dartai.model.DartRound;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple dart-bot who throws a given average +/- 3 at all time.
 */
public class SimpleDartBot implements DartBot {

    private boolean hasFinished = false;
    private int scoreLeft = 501;
    private int variance = 3;
    private double average;
    private double checkAvg;
    private List<DartRound> playedRounds = new ArrayList<>();

    protected SimpleDartBot(int scoreLeft, int variance, double average, double checkAvg) {
        this.scoreLeft = scoreLeft;
        this.variance = variance;
        this.average = average;
        this.checkAvg = checkAvg;
    }


    @Override
    public DartRound playOneRound() {
        List<DartRound> combs = Numbers.getPossibleThreeDartCombinations();
        if (this.getScoreLeft() > average + variance) {
            // Just scoring.
            for (DartRound r : combs) {
                if (r.getScore() <= average + variance && r.getScore() >= average - variance && this.scoreLeft - r.getScore() != 1) {
                    this.scoreLeft -= r.getScore();
                    playedRounds.add(r);
                    return getLastRound();
                }
            }
        } else {
            // Has to check for finish
            if (Numbers.getPossibleDoubleFinishes().contains(this.getScoreLeft())) {
                // We have to calculate a probablity for a finish.  We take the average/100*2 in this example
                if (Math.random() < this.checkAvg) {
                    this.hasFinished = true;
                    // We dont calculate if its the first, second or third dart currently -> expect it to be the first always
                    this.scoreLeft = 0;
                    playedRounds.add(new DartRound(this.getScoreLeft(), 0, 0));

                    return getLastRound();
                } else {
                    // 50% chance to halve the score
                    if (Math.random() < 0.5 && getScoreLeft() >= 4) {
                        int score = getScoreLeft() / 2;
                        this.scoreLeft -= score;
                        playedRounds.add(new DartRound(0, score, 0));
                    } else {
                        // not finished, simple example: no score
                        playedRounds.add(new DartRound(0, 0, 0));
                    }
                    return getLastRound();
                }

            } else {
                // Getting to a even number
                for (DartRound r : combs) {
                    if (r.getScore() <= average + this.variance && (this.getScoreLeft() - r.getScore()) % 2 == 0 && r.getScore() < this.getScoreLeft() && (this.getScoreLeft() - r.getScore() > 1)) {
                        this.scoreLeft -= r.getScore();
                        this.playedRounds.add(r);
                        return getLastRound();
                    }
                }

            }
        }
        // Should never happen
        return new DartRound(0, 0, 0);
    }


    private DartRound getLastRound() {
        return this.playedRounds.get(this.playedRounds.size() - 1);
    }

    @Override
    public boolean hasFinished() {
        return this.hasFinished;
    }

    @Override
    public int getScoreLeft() {
        return scoreLeft;
    }

    public List<DartRound> getPlayedRounds() {
        return playedRounds;
    }

    @Override
    public void reset() {
        this.playedRounds.clear();
        this.hasFinished = false;
        this.scoreLeft = 501;
    }
}
