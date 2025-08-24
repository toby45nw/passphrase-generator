import java.security.SecureRandom;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class PassPhraseGenerator {
    Map<String, String> wordMap;
    int numberOfWords;
    int numberOfSymbols;
    int numberOfCapitalLetters;
    int numberOfDigits;

    private static SecureRandom rand = new SecureRandom();

    ArrayList<String> words = new ArrayList<>();
    ArrayList<Character> specialCharcters = new ArrayList<>();
    ArrayList<Integer> digits = new ArrayList<>();

    List<Object> passPhrase = new ArrayList<>();


    public PassPhraseGenerator(Map<String, String> wordMap, int numberOfWords, int numberOfSymbols, int numberOfCapitalLetters, int numberOfDigits) {
        this.wordMap = wordMap;
        this.numberOfWords = numberOfWords;
        this.numberOfSymbols = numberOfSymbols;
        this.numberOfCapitalLetters = numberOfCapitalLetters;
        this.numberOfDigits = numberOfDigits;

        // Get the amount of words the user wants
        for (int i = 0; i < numberOfWords; i++) {
            words.add(getWord());
        }

        // Capitalize how ever may charcter the user wants
        capitaliseRandomCharacters();


        // Get a special character
        for (int i = 0; i < numberOfSymbols; i++) {
            specialCharcters.add(getSpecialCharacter());
        }

        for (int i=0; i<numberOfDigits; i++) {
            digits.add(rand.nextInt(10));
        }

        assemblePassPhrase();
    }

    private String getWord() {
        String word;
        String wordNumber = ""; 

        for (int j = 0; j < 5; j++) {
            int number = rand.nextInt(6) + 1;
            wordNumber += Integer.toString(number);
        }
        
        word = wordMap.get(wordNumber);
        return word;
    }

    private char getSpecialCharacter() {
        String specialCharcters = "!#$%^&*()=-+[]\\{}:;\"'<>?/";
        int randomIndex = rand.nextInt(specialCharcters.length());
        char specialCharcter = specialCharcters.charAt(randomIndex);
        return specialCharcter;
    }

    private ArrayList<String> capitaliseRandomCharacters() {

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

    private void assemblePassPhrase() {

        
        for (String s : words) {
            passPhrase.add(s);
        }

        // Add elements from the char array
        for (char c : specialCharcters) {
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

        // Print the result
        System.out.println(result);
    }

}