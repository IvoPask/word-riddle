import java.util.*;
import java.util.stream.Collectors;

public class WordUtils {

    private WordUtils() {
    }

    private static final String I = "I";
    private static final String A = "A";

    public static Set<String> getReducibleWords(List<String> words, int maxLength) {
        validateInput(words, maxLength);
        Map<Integer, Set<String>> wordsByLength = groupWordsByLength(words, maxLength);

        for (int i = 3; i <= maxLength; i++) {
            Iterator<String> wordIterator = wordsByLength.getOrDefault(i, Set.of()).iterator();
            Set<String> shorterWords = wordsByLength.getOrDefault(i - 1, Set.of());

            while (wordIterator.hasNext()) {
                String word = wordIterator.next();
                boolean shouldRemove = true;

                for (int j = 0; j < word.length(); j++) {
                    String temp = removeCharAt(word, j);
                    if (shorterWords.contains(temp)) {
                        shouldRemove = false;
                        break;
                    }
                }
                if (shouldRemove) {
                    wordIterator.remove();
                }
            }
        }
        return wordsByLength.getOrDefault(maxLength, Set.of());
    }

    private static void validateInput(List<String> words, int maxLength) {
        if (words == null) {
            throw new IllegalArgumentException(ErrorMessages.WORD_LIST_NULL);
        }
        if (words.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessages.WORD_LIST_EMPTY);
        }
        if (maxLength < 1 || maxLength > 20) {
            throw new IllegalArgumentException(ErrorMessages.MAX_LENGTH_INVALID);
        }
    }

    private static Map<Integer, Set<String>> groupWordsByLength(List<String> words, int maxLength) {
        return words.stream()
                .filter(w -> w.length() <= maxLength && (w.contains(I) || w.contains(A)))
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.toCollection(HashSet::new)));
    }

    private static String removeCharAt(String str, int index) {
        return str.substring(0, index) + str.substring(index + 1);
    }

}
