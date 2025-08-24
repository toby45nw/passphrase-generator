import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.IOException;

public class WordMapGenerator {
    private final Map<String, String> wordMap;
    
    public WordMapGenerator() {
        Map<String, String> tempMap = new HashMap<String, String>();
        Path filePath = Paths.get("eff_large_wordlist.txt");

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                String[] parts = line.split("\t", 2);
                tempMap.put(parts[0], parts[1]);
            } 

            } catch (IOException e) {
                e.printStackTrace();
        }

        this.wordMap = Collections.unmodifiableMap(tempMap);
    }

    public Map<String, String> getWordMap() {
        return this.wordMap;
    }
}