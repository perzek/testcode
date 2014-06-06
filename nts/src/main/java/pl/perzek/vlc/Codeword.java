package pl.perzek.vlc;

/**
 * Created by perzek on 2014-06-06.
 */
public class Codeword implements ProbabilityAware {
    private final double probability;
    private final char character;

    public Codeword(char character, double probability) {

        this.character = character;
        this.probability = probability;
    }

    public double getProbability() {
        return probability;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return String.format("C{P = %.2f; C = %c}", probability, character);
    }
}
