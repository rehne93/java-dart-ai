package de.baernreuther.dartai.bot;

/**
 * Factory to create different Dartbots.
 */
public class DartBotFactory {

    /**
     * Given default variance for the score
     */
    private static final int DEFAULT_VARIANCE = 5;

    /**
     * Will create a simple Dartbot for 501 depending on the given average.
     * The checkrate will be 1/3*avg, the variance is 5.
     * @param avg average score of the bot (between 0 and 180)
     * @return
     */
    public static DartBot createSimple501DartBot(double avg) {
        return new SimpleDartBot(501, DEFAULT_VARIANCE, avg, avg / 3);
    }


    /**
     * Creates a simple Dart bot depending on average and checkRate. Default Variance is 5.
     * @param avg average score of the bot (between 0 and 180)
     * @param checkRate average checkrate of the bot, between 0 and 1
     * @param scoreLeft starting value, usually a x01 value
     * @return the configured dartbot
     */
    public static DartBot createDartBot(double avg, double checkRate, int scoreLeft) {
        return new SimpleDartBot(scoreLeft, DEFAULT_VARIANCE, checkRate, scoreLeft);
    }

    /**
     * Creates a Dartbot depending on all possible parameters
     * @param avg average score of the bot (between 0 and 180)
     * @param checkRate average checkrate of the bot, between 0 and 1
     * @param scoreLeft scoreleft, usually a X01 value
     * @param variance the variance of the average per score
     * @return the fully configured dartbot
     */
    public static DartBot creaateComplexDartBot(double avg, double checkRate, int scoreLeft, int variance) {
        return new SimpleDartBot(scoreLeft, DEFAULT_VARIANCE, checkRate, scoreLeft);
    }
}
