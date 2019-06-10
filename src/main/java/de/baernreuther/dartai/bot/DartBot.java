package de.baernreuther.dartai.bot;

import de.baernreuther.dartai.model.DartRound;

import java.util.List;

/**
 * Implementation of a DartBot
 */
public interface DartBot {

    /**
     * The bot throws three darts
     *
     * @return one round played
     */
    default DartRound playOneRound() {
        return new DartRound(20, 20, 20);
    }

    /**
     * @return true, in case the bot is finished. false otherwise
     */
    boolean hasFinished();

    /**
     * @return the score the bot has left.
     */
    int getScoreLeft();


    /**
     * @return all rounds played by the bot
     */
    List<DartRound> getPlayedRounds();

    /**
     * Resets the dart bot to be able to start a new game.
     */
    void reset();

}
