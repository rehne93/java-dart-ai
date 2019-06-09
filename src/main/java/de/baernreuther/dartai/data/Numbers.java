package de.baernreuther.dartai.data;

import de.baernreuther.dartai.model.DartRound;

import java.util.*;

/**
 * Utility class for all nececssary numbers for a dart-ai.
 */
public class Numbers {

    public static final int DART_MAX_SCORE = 60;
    public static final int DART_MIN_SCORE = 0;
    private static final List<Integer> boogeyNumbers = Arrays.asList(189, 162, 163, 165, 166, 168, 169);
    private static Set<Integer> possibleOneDartScores = new HashSet<>();
    private static Set<Integer> possibleThreeDartScores = new HashSet<>();
    private static List<DartRound> possibleThreeDartCombinations = new ArrayList<>();
    private static Set<Integer> possibleDoubleFinishes = new HashSet<>();

    /**
     * Returns a list with all boogey numbers for double out mode
     * @return all boogey numbers for double out mode
     */
    public static List<Integer> getBoogeyNumbers() {
        return boogeyNumbers;
    }

    /**
     * Calculates and returns all possible double finishes.
     * Calculation will only be used once.
     * @return a set of all possible double finishes with one dart.
     */
    public static Set<Integer> getPossibleDoubleFinishes(){
        if(possibleDoubleFinishes.isEmpty()){
            for(int i = 2; i <= 40; i+=2){
                possibleDoubleFinishes.add(i);
            }
            possibleDoubleFinishes.add(50);
        }
        return possibleDoubleFinishes;
    }

    /**
     * Returns a set with all scores possible with three darts.
     * @return
     */
    public static Set<Integer> getPossibleThreeDartScores() {
        if (possibleThreeDartScores.isEmpty()) {
            possibleThreeDartScores = new HashSet<Integer>();
            for (int i = DART_MIN_SCORE; i <= DART_MAX_SCORE; i++) {
                for (DartRound round : getPossibleThreeDartCombinations()) {
                    // Three darts added modulo 3 equals 0 is a possible score
                    if (round.getScore() % 3 == 0) {
                        possibleThreeDartScores.add(round.getScore());
                    }
                }
            }
        }
        return possibleThreeDartScores;
    }

    /**
     * Returns all scores possible with one dart
     *
     * @return
     */
    public static Set<Integer> getPossibleOneDartScores() {
        if (possibleOneDartScores.isEmpty()) {
            possibleOneDartScores = new HashSet<>();
            possibleOneDartScores.add(25); // Special case
            possibleOneDartScores.add(50); // Special Case
            for (int i = DART_MIN_SCORE; i <= 20; i++) {
                possibleOneDartScores.add(i);
                possibleOneDartScores.add(i*2);
                possibleOneDartScores.add(i*3);

            }

        }
        return possibleOneDartScores;
    }

    /**
     * Returns all combinations of possible scores in an array of size 3 of the form
     * [0] = shot 1
     * [1] = shot 2
     * [2] = shot 3
     *
     * @return all combinations of possible scores
     */
    public static List<DartRound> getPossibleThreeDartCombinations() {
        if(possibleThreeDartCombinations.isEmpty()) {
            for (int s1 : getPossibleOneDartScores()) {
                for (int s2 : getPossibleOneDartScores()) {
                    for (int s3 : getPossibleOneDartScores()) {
                        possibleThreeDartCombinations.add(new DartRound(s1,s2,s3));
                    }
                }
            }
        }
        // Shuffle the list to get different throws.
        Collections.shuffle(possibleThreeDartCombinations);
        return possibleThreeDartCombinations;
    }
}
