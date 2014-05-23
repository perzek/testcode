package pl.perzek.vlc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EncoderTest {

    private static final char[] availableChars = new char[]{'a', 'b', 'c'};
    private static final Random rand = new Random(System.currentTimeMillis());
    public static final Logger LOGGER = LoggerFactory.getLogger(EncoderTest.class);

    @Test
    public void testCountEachWord() throws Exception {
        Encoder e = new Encoder();

        Map<Character, Integer> map = e.countEachWord(generateMessage(new int[]{3, 4, 5}).toCharArray());

        assertThat(map.get('a'), is(3));
        assertThat(map.get('b'), is(4));
        assertThat(map.get('c'), is(5));

        map = e.countEachWord(generateMessage(new int[]{5, 4, 3}).toCharArray());

        assertThat(map.get('a'), is(5));
        assertThat(map.get('b'), is(4));
        assertThat(map.get('c'), is(3));
    }

    @Test
    public void testCountingSpeed() {
        Encoder e = new Encoder();
        long start = System.nanoTime();
        Map<Character, Integer> map = e.countEachWord(generateMessage(new int[]{30000000, 40000000, 50000000}).toCharArray());
        LOGGER.info("Counting took {} ms", (System.nanoTime() - start) / 1000000);
        assertThat(map.get('a'), is(30000000));
        assertThat(map.get('b'), is(40000000));
        assertThat(map.get('c'), is(50000000));
    }

    @Test
    public void testOrderingCodewords() {
        Encoder e = new Encoder();
        Set<Encoder.Codeword> codewords = e.orderCodewordsByProbability(generateMessage(new int[]{3, 4, 5}));
        LOGGER.info("Set: {}", codewords);
    }

    @Test
    public void testGenerateCodes() {
        Encoder e = new Encoder();

        e.generateCodes(generateMessage(new int[]{3, 4, 5}));
    }

    private String generateMessage(int[] counts) {
        StringBuilder sb = new StringBuilder();
        int index;
        while (isArrayNotEmpty(counts)) {
            index = rand.nextInt(counts.length);
            while (counts[index] == 0) {
                index = (index + 1) % counts.length;
            }

            sb.append(availableChars[index]);
            --counts[index];

        }
        return sb.toString();
    }

    private boolean isArrayNotEmpty(int[] counts) {
        boolean result = false;
        for (int count : counts) {
            result |= count > 0;
        }
        return result;
    }


}