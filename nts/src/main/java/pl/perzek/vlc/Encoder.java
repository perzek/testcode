package pl.perzek.vlc;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by perzek on 2014-05-23.
 */
public class Encoder {

    public static final Logger LOGGER = LoggerFactory.getLogger(Encoder.class);

    public List<Codeword> generateCodes(String message) {
        Set<Codeword> codewords = orderCodewordsByProbability(message);

        createHuffmanTree(codewords);
        //TODO

        return null;

    }

    private HuffmanTreeNode<Codeword> createHuffmanTree(Set<Codeword> codewords) {
        //TODO
        return null;
    }

    public Set<Codeword> orderCodewordsByProbability(String message) {
        char[] charArray = message.toCharArray();
        Map<Character, Integer> wordCount = countEachWord(charArray);

        Set<Codeword> orderedCodewords = Sets.newTreeSet((o1, o2) -> (Double.compare(o2.getProbability(), o1.getProbability())));

        for (Map.Entry<Character, Integer> entry : wordCount.entrySet()) {
            orderedCodewords.add(new Codeword(entry.getKey(), entry.getValue() / (double) charArray.length));
        }

        return orderedCodewords;

    }

    public Map<Character, Integer> countEachWord(char[] message) {
        LOGGER.debug("Input param: [{}]", message);
        Map<Character, Integer> result = Maps.newHashMap();


        for (char c : message) {

            Integer integer = result.get(c);
            if (integer == null) {
                integer = 0;
            }
            result.put(c, ++integer);
        }

        return result;
    }

    public static class Codeword implements ProbabilityAware {
        private final double probability;
        private final char character;
        private final BitSet code;

        public Codeword(char character, double probability) {
            this(character, probability, null);
        }

        public Codeword(char character, double probability, BitSet code) {
            this.code = code;
            this.character = character;
            this.probability = probability;
        }

        public Codeword addCode(BitSet code) {
            return new Codeword(character, probability, code);
        }

        public double getProbability() {
            return probability;
        }

        public char getCharacter() {
            return character;
        }

        @Override
        public String toString() {
            return "Codeword{" +
                    "probability=" + probability +
                    ", character=" + character +
                    '}';
        }
    }

}
