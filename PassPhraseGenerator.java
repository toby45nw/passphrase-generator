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

    private void capitaliseRandomCharacters(ArrayList<String> words, int numberOfCapitalLetters) {
        // Create a list of all possible character positions
        List<Position> allPositions = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                allPositions.add(new Position(i, j));
            }
        }

        // Shuffle the list to randomize the order
        Collections.shuffle(allPositions, rand);

        // Determine how many letters to capitalize (can't be more than available)
        int lettersToCapitalize = Math.min(numberOfCapitalLetters, allPositions.size());

        // Capitalize the characters at the chosen positions
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

    private String assemblePassPhrase(ArrayList<String> words, ArrayList<Character> specialCharacters, ArrayList<Integer> digits) {

        List<String> passPhraseComponents = new ArrayList<>();
        
        passPhraseComponents.addAll(words);

        for (Character c : specialCharacters) {
            passPhraseComponents.add(c.toString());
        }

        for (Integer i : digits) {
            passPhraseComponents.add(i.toString());
        }

        Collections.shuffle(passPhraseComponents, rand);

        return String.join("", passPhraseComponents);
    }
}