import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generates a passphrase based on specified criteria.
 * This class combines words, symbols, and digits to create a secure passphrase.
 */
public class PassPhraseGenerator {

    private final SecureRandom rand;
    private final PassPhraseComponentGenerator componentGenerator;

    /**
     * Constructs a PassPhraseGenerator with a component generator and a random number generator.
     *
     * @param componentGenerator The generator for the components of the passphrase (words, symbols, digits).
     * @param rand The random number generator to use for shuffling and capitalization.
     */
    public PassPhraseGenerator(PassPhraseComponentGenerator componentGenerator, SecureRandom rand) {
        this.rand = rand;
        this.componentGenerator = componentGenerator;
    }

    /**
     * Generates a new passphrase with the specified number of words, symbols, digits, and capital letters.
     *
     * @param numberOfWords The number of words to include in the passphrase.
     * @param numberOfSymbols The number of special characters to include.
     * @param numberOfDigits The number of digits to include.
     * @param numberOfCapitalLetters The number of letters to capitalize.
     * @return A new PassPhrase object containing the generated passphrase.
     */
    public PassPhrase generate(int numberOfWords, int numberOfSymbols, int numberOfDigits, int numberOfCapitalLetters) {

        // Generate the basic components of the passphrase
        ArrayList<String> words = componentGenerator.generateWords(numberOfWords);
        ArrayList<Character> specialCharacters = componentGenerator.generateSpecialCharacters(numberOfSymbols);
        ArrayList<Integer> digits = componentGenerator.generateDigits(numberOfDigits);

        // Capitalize a random selection of characters in the generated words
        capitaliseRandomCharacters(words, numberOfCapitalLetters);

        // Assemble the final passphrase by combining and shuffling all components
        return new PassPhrase(assemblePassPhrase(words, specialCharacters, digits));
    }

    /**
     * Capitalizes a specified number of random characters within the list of words.
     *
     * @param words The list of words to modify.
     * @param numberOfCapitalLetters The number of characters to capitalize.
     */
    private void capitaliseRandomCharacters(ArrayList<String> words, int numberOfCapitalLetters) {
        // Create a list of all possible character positions in all words
        List<Position> allPositions = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                allPositions.add(new Position(i, j));
            }
        }

        // Shuffle the list to randomize the order of capitalization
        Collections.shuffle(allPositions, rand);

        // Determine how many letters to capitalize (cannot be more than available characters)
        int lettersToCapitalize = Math.min(numberOfCapitalLetters, allPositions.size());

        // Capitalize the characters at the chosen random positions
        for (int i = 0; i < lettersToCapitalize; i++) {
            Position pos = allPositions.get(i);
            int wordIndex = pos.getWordIndex();
            int letterIndex = pos.getLetterIndex();

            String word = words.get(wordIndex);
            char[] wordChars = word.toCharArray();
            wordChars[letterIndex] = Character.toUpperCase(wordChars[letterIndex]);
            words.set(wordIndex, new String(wordChars));
        }

    }

    /**
     * Assembles the final passphrase by combining words, special characters, and digits, then shuffling them.
     *
     * @param words The list of words.
     * @param specialCharacters The list of special characters.
     * @param digits The list of digits.
     * @return A string representing the final, shuffled passphrase.
     */
    private String assemblePassPhrase(ArrayList<String> words, ArrayList<Character> specialCharacters, ArrayList<Integer> digits) {

        // Create a list to hold all components of the passphrase as strings
        List<String> passPhraseComponents = new ArrayList<>();
        
        passPhraseComponents.addAll(words);

        // Add special characters to the list
        for (Character c : specialCharacters) {
            passPhraseComponents.add(c.toString());
        }

        // Add digits to the list
        for (Integer i : digits) {
            passPhraseComponents.add(i.toString());
        }

        // Shuffle all components to create a random order
        Collections.shuffle(passPhraseComponents, rand);

        // Join all components into a single string
        return String.join("", passPhraseComponents);
    }
}