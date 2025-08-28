import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Generates and provides a map of words from a wordlist file.
 * Each line in the wordlist file contains a key and a word separated by a tab.
 */
public class WordMapGenerator {
    private final Map<String, String> wordMap;
    
    /**
     * Constructs a WordMapGenerator by reading a wordlist file.
     *
     * @param wordlistPath The path to the wordlist file.
     * @throws RuntimeException if the wordlist file cannot be read.
     */
    public WordMapGenerator(String wordlistPath) {
        Map<String, String> tempMap = new HashMap<String, String>();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(wordlistPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("	", 2);
                if (parts.length == 2) {
                    tempMap.put(parts[0], parts[1]);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading wordlist file: " + wordlistPath, e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Wordlist file not found as resource: " + wordlistPath, e);
        }

        this.wordMap = Collections.unmodifiableMap(tempMap);
    }

    /**
     * Returns the unmodifiable word map.
     *
     * @return The map of words.
     */
    public Map<String, String> getWordMap() {
        return this.wordMap;
    }
}
