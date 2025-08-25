import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class PassPhraseGenerator {

    private final SecureRandom rand;
    private final PassPhraseComponentGenerator componentGenerator;

    public PassPhraseGenerator(PassPhraseComponentGenerator componentGenerator, SecureRandom rand) {
        this.rand = rand;
        this.componentGenerator = componentGenerator;
    }

    public PassPhrase generate(int numberOfWords, int numberOfSymbols, int numberOfDigits, int numberOfCapitalLetters) {

        ArrayList<String> words = componentGenerator.generateWords(numberOfWords);
        ArrayList<Character> specialCharacters = componentGenerator.generateSpecialCharacters(numberOfSymbols);
        ArrayList<Integer> digits = componentGenerator.generateDigits(numberOfDigits);

        capitaliseRandomCharacters(words, numberOfCapitalLetters);

        return new PassPhrase(assemblePassPhrase(words, specialCharacters, digits));

    }

    private ArrayList<String> capitaliseRandomCharacters(ArrayList<String> words, int numberOfCapitalLetters) {

        Set<Position> usedPositions = new HashSet<>();

        for (int i = 0; i < numberOfCapitalLetters; i++) {
            while (true) {
                int wordIndex = rand.nextInt(words.size());
                String word = words.get(wordIndex);

                if (word.isEmpty()) {
                    continue;
                }

                int letterIndex = rand.nextInt(word.length());

                Position newPosition = new Position(wordIndex, letterIndex);

                // The .add() method returns false if the element already exists
                if (usedPositions.add(newPosition)) {
                    // Position is unique, so we can modify the word and break the loop
                    char[] wordChars = word.toCharArray();
                    wordChars[letterIndex] = Character.toUpperCase(wordChars[letterIndex]);
                    words.set(wordIndex, new String(wordChars));
                    break;
                }
            }
        }

        return words;
    }

    private String assemblePassPhrase(ArrayList<String> words, ArrayList<Character> specialCharacters, ArrayList<Integer> digits) {

        List<Object> passPhrase = new ArrayList<>();
        
        for (String s : words) {
            passPhrase.add(s);
        }

        // Add elements from the char array
        for (char c : specialCharacters) {
            passPhrase.add(c);
        }

        // Add elements from the int array
        for (int i : digits) {
            passPhrase.add(i);
        }

         // Shuffle the list to randomize the order
        Collections.shuffle(passPhrase, rand);

        // Use a StringBuilder to create the final string
        StringBuilder finalString = new StringBuilder();

        // Append the elements from the shuffled list
        for (Object element : passPhrase) {
            finalString.append(element);
        }

        // Convert to a final string
        String result = finalString.toString();

        return result;
    }

}