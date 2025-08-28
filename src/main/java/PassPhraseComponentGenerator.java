import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

/**
 * Generates the individual components of a passphrase, such as words, symbols, and digits.
 */
public class PassPhraseComponentGenerator {
    
    private final SecureRandom rand;
    private final Map<String, String> wordMap;
    private static final String SPECIAL_CHARACTERS = "!#$%^&*()=-+[]\\{}:;\"'<>?/";
    private static final int DICE_ROLLS_PER_WORD = 5;
    private static final int DICE_SIDES = 6;

    /**
     * Constructs a PassPhraseComponentGenerator with a word map and a random number generator.
     *
     * @param wordMap A map of dice rolls to words.
     * @param rand The random number generator to use.
     */
    public PassPhraseComponentGenerator(Map<String, String> wordMap, SecureRandom rand) {
        this.wordMap = wordMap;
        this.rand = rand;
    }

    /**
     * Generates a list of random words.
     *
     * @param numberOfWords The number of words to generate.
     * @return An ArrayList of generated words.
     */
    public ArrayList<String> generateWords(int numberOfWords) {
        ArrayList<String> words = new ArrayList<>();
        for (int i = 0; i < numberOfWords; i++) {
            words.add(generateWord());
        }
        return words;
    }

    /**
     * Generates a list of random special characters.
     *
     * @param numberOfSymbols The number of special characters to generate.
     * @return An ArrayList of generated special characters.
     */
    public ArrayList<Character> generateSpecialCharacters(int numberOfSymbols) {
        ArrayList<Character> specialCharacters = new ArrayList<>();
        for (int i = 0; i < numberOfSymbols; i++) {
            specialCharacters.add(generateSpecialCharacter());
        }
        return specialCharacters;

    }

    /**
     * Generates a list of random digits.
     *
     * @param numberOfDigits The number of digits to generate.
     * @return An ArrayList of generated digits.
     */
    public ArrayList<Integer> generateDigits(int numberOfDigits) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (int i = 0; i < numberOfDigits; i++) {
            digits.add(rand.nextInt(10));
        }
        return digits;
    }

    /**
     * Generates a single word by simulating dice rolls and looking up the result in the word map.
     *
     * @return A randomly generated word.
     */
    private String generateWord() {
        StringBuilder wordNumber = new StringBuilder();
        for (int i = 0; i < DICE_ROLLS_PER_WORD; i++) {
            wordNumber.append(rand.nextInt(DICE_SIDES) + 1);
        }
        return wordMap.get(wordNumber.toString());
    }

    /**
     * Generates a single random special character from the defined set.
     *
     * @return A randomly selected special character.
     */
    private char generateSpecialCharacter() {
        int randomIndex = rand.nextInt(SPECIAL_CHARACTERS.length());
        return SPECIAL_CHARACTERS.charAt(randomIndex);
    }    
}
