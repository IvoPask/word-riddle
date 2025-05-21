import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataLoaderTest {

    private static final String URL =
            "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    @Test
    void testLoadWordsSkipsFirstTwoLines() throws Exception {
        String mockData = "Line1\nLine2\nword1\nword2\nword3";
        InputStream inputStream = new ByteArrayInputStream(mockData.getBytes());

        List<String> words = DataLoader.loadWords(inputStream);

        assertEquals(3, words.size());
        assertTrue(words.contains("word1"));
        assertEquals("word3", words.get(2));
    }

    @Test
    void testLoadFromUrl() throws Exception {

        List<String> words = DataLoader.loadWordsFromUrl(URL);

        assertEquals(279496, words.size());
        assertTrue(words.containsAll(List.of("STRING", "STARTLING", "TEST")));
    }

}
