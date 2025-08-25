import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

public class PassPhraseComponentGenerator {
    
    private final SecureRandom rand;
    private final Map<String, String> wordMap;
    private static final String SPECIAL_CHARACTERS = "!#$%^&*()=-+[]\\{}:;\"'<>?/";

    public PassPhraseComponentGenerator(Map<String, String> wordMap, SecureRandom rand) {
        this.wordMap = wordMap;
        this.rand = rand;
    }

    public ArrayList<String> generateWords(int numberOfWords) {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            words.add(generateWord());
        }
        return words;
    }

    public ArrayList<Character> generateSpecialCharacters(int numberOfSymbols) {
        ArrayList<Character> specialCharacters = new ArrayList<>();
        for (int i = 0; i < numberOfSymbols; i++) {
            specialCharacters.add(generateSpecialCharacter());
        }
        return specialCharacters;

    }

    public ArrayList<Integer> generateDigits(int numberOfDigits) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < numberOfDigits; i++) {
            digits.add(rand.nextInt(10));
        }
        return digits;
    }

    private String generateWord() {
        StringBuilder wordNumber = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            wordNumber.append(rand.nextInt(6) + 1);
        }
        return wordMap.get(wordNumber.toString());
    }

    private char generateSpecialCharacter() {
        int randomIndex = rand.nextInt(SPECIAL_CHARACTERS.length());
        return SPECIAL_CHARACTERS.charAt(randomIndex);
    }    
}
