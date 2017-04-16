package ru.ematveev.guessnumber2;

import java.util.Random;

public class GenerateNumberController {
    private Random random;

    /**
     * Initialization of the Random class.
     *
     * @param random random.
     */
    public GenerateNumberController(Random random) {
        this.random = random;
    }

    /**
     * Method of generating a random number in the range from 0 to n.
     *
     * @param n is the maximum number of range.
     * @return the random number.
     */
    public int generate(final int n) {
        return random.nextInt(n) + 1;
    }

    /**
     * Method numberEqualityCheck() for comparison of the number of hidden and entered by the user.
     *
     * @param guess  is the user-entered number.
     * @param target is conceived by program number.
     * @return enum which was the comparisonwhich.
     */
    public static GuessResult numberEqualityCheck(final int guess, final int target) {
        if (guess > target) {
            return GuessResult.GUESS_IS_BIGGER;
        } else if (guess < target) {
            return GuessResult.GUESS_IS_SMALL;
        }
        return GuessResult.EQUAL;
    }
}
