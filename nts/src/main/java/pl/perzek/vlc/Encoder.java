package pl.perzek.vlc;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by perzek on 2014-05-23.
 */
public class Encoder {

    public static final Logger LOGGER = LoggerFactory.getLogger(Encoder.class);

    public Map<String, Codeword> generateCodes(String message) {
        Set<Codeword> codewords = orderCodewordsByProbability(message);

        HuffmanTree<Codeword> huffmanTree = createHuffmanTree(codewords);

        return huffmanTree.getCodeMapping();

    }

    private HuffmanTree<Codeword> createHuffmanTree(Set<Codeword> codewords) {
        HuffmanTree<Codeword> tree = new HuffmanTree<>(codewords);
        LOGGER.info("Created tree {}", tree);
        return tree;
    }

    public Set<Codeword> orderCodewordsByProbability(String message) {
        char[] charArray = message.toCharArray();
        Map<Character, Integer> wordCount = countEachWord(charArray);

        Set<Codeword> orderedCodewords = Sets.newTreeSet((o1, o2) -> (Double.compare(o1.getProbability(), o2.getProbability())));

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

}
