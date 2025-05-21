import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.*;

public class DataLoader {

    private DataLoader() {}

    public static List<String> loadWords(InputStream inputStream) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            return br.lines().skip(2).toList();
        }
    }

    public static List<String> loadWordsFromUrl(String url) throws IOException {
        URL wordsUrl = new URL(url);
        return loadWords(wordsUrl.openConnection().getInputStream());
    }
}
