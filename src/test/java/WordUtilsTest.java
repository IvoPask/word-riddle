import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.*;

class WordUtilsTest {

    private static final String URL =
            "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    @Test
    void testPerformanceUnderTwoSeconds() throws IOException {

        List<String> words = DataLoader.loadWordsFromUrl(URL);
        out.println("Loaded " + words.size() + " English words from dictionary");

        // Start timer once data is loaded
        long start = nanoTime();

        Set<String> result = WordUtils.getReducibleWords(words, 9);

        long end = System.nanoTime();
        long durationNs = end - start;
        double durationInMilliseconds = (double) durationNs / 1000000;
        double executionTimeLimitMs = 2000;

        out.println("Execution time: " + durationInMilliseconds + " ms");
        out.println("Number of words: " + result.size());
        out.println(result);
        assertEquals(775, result.size());
        assertTrue(durationInMilliseconds < executionTimeLimitMs);
    }

    @Test
    void testSingleValidReductionChain() {

        List<String> words = List.of(
                "TIROES", "TIRONIC", "TIROS", "TIRR", "TIRRED", "TIRRING", "TIRRIT", "TIRRITS", "TIRRIVEE",
                "TIRRIVEES", "TIRRIVIE", "TIRRIVIES", "TIRRS", "TIS", "TISANE", "TISANES", "TISICK", "TISICKS",
                "TISSUAL", "TISSUE", "TISSUED", "TISSUES", "TISSUEY", "TISSUIER", "TISSUIEST", "TISSUING", "TISSULAR",
                "TISWAS", "TISWASES", "TIT", "TITAN", "TITANATE", "TITANATES", "TITANESS", "TITANESSES", "SPARKLING",
                "ISLANDERS", "SLANDERS", "SANDERS", "SANDER", "SANER", "SANE", "SAN", "AN", "A");

        Set<String> result = WordUtils.getReducibleWords(words, 9);

        assertEquals(1, result.size());
        assertEquals("ISLANDERS", result.iterator().next());
    }

    @Test
    void testGetReducibleWordsShouldReturnEmptySet() {

        List<String> words = List.of(
                "TIROES", "TIRONIC", "TIROS", "TIRR", "TIRRED", "TIRRING", "TIRRIT", "TIRRITS", "TIRRIVEE",
                "TIRRIVEES", "TIRRIVIE", "TIRRIVIES", "TIRRS", "TIS", "TISANE", "TISANES", "TISICK", "TISICKS",
                "TISSUAL", "TISSUE", "TISSUED", "TISSUES", "TISSUEY", "TISSUIER", "TISSUIEST", "TISSUING", "TISSULAR",
                "TISWAS", "TISWASES", "TIT", "TITAN", "TITANATE", "TITANATES", "TITANESS", "TITANESSES", "SPARKLING",
                "ISLANDERS", "SLANDERS", "SANDERS", "SANDER", "SANE", "SAN", "AN", "A");

        Set<String> result = WordUtils.getReducibleWords(words, 9);

        assertEquals(0, result.size());
    }

    @Test
    void testContainsKnownReducibleWords() throws IOException {

        List<String> words = DataLoader.loadWordsFromUrl(URL);

        Set<String> result = WordUtils.getReducibleWords(words, 9);

        assertEquals(775, result.size());
        assertTrue(result.contains("SPARKLING"));
        assertTrue(result.contains("ISLANDERS"));
        assertTrue(result.contains("MINISTERS"));
        assertTrue(result.contains("STARTLING"));
        assertTrue(result.contains("SCRAPPIER"));
        assertTrue(result.contains("SPLITTING"));
        assertTrue(result.contains("RESTARTED"));
        assertTrue(result.contains("STRINGIER"));
        assertTrue(result.contains("STRINGERS"));
        assertTrue(result.contains("BRASSIEST"));
        assertTrue(result.contains("SUPINATED"));
        assertTrue(result.contains("LAMINATED"));
        assertTrue(result.contains("TRAPPINGS"));
        assertTrue(result.contains("CLEANSERS"));
        assertTrue(result.contains("DROWNINGS"));
        assertTrue(result.contains("STARLINGS"));

    }

    @Test
    void testGetReducibleWordsShouldReturnExceptionForNullList() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WordUtils.getReducibleWords(null, 0);
        });

        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals(ErrorMessages.WORD_LIST_NULL, exception.getMessage());
    }

    @Test
    void testGetReducibleWordsShouldReturnExceptionForEmptyList() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WordUtils.getReducibleWords(List.of(), 0);
        });

        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals(ErrorMessages.WORD_LIST_EMPTY, exception.getMessage());
    }

    @Test
    void testGetReducibleWordsShouldReturnExceptionForMaxLengthSmallerThanOne() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WordUtils.getReducibleWords(List.of("test"), 0);
        });

        assertEquals(IllegalArgumentException.class, exception.getClass());
        assertEquals(ErrorMessages.MAX_LENGTH_INVALID, exception.getMessage());
    }

    @Test
    void testGetReducibleWordsShouldReturnEmptySetIfNotMatches() {

        Set<String> result = WordUtils.getReducibleWords(List.of("Berbatov"), 2);

        assertEquals(0, result.size());
    }

    @Test
    void testMaxLengthOfOne() {
        Set<String> result = WordUtils.getReducibleWords(List.of("A", "I", "IS", "C"), 1);
        assertEquals(Set.of("A", "I"), result);
    }

}
