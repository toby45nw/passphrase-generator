import java.util.Map;

public class Main {
    public static void main(String args[]){
        int numberOfWords;
        int numberOfSymbols;
        int numberOfCapitalLetters;
        int numberOfDigits;

        // Give the user the option to change these parameter later
        numberOfWords = 3;
        numberOfSymbols = 2;
        numberOfCapitalLetters = 2;
        numberOfDigits = 2;

        WordMapGenerator wordMapGenerator = new WordMapGenerator();
        Map<String, String> wordMap = wordMapGenerator.getWordMap();

        PassPhraseGenerator passPhraseGenerator = new PassPhraseGenerator(wordMap, numberOfWords, numberOfSymbols, numberOfCapitalLetters, numberOfDigits);


    }
    
}
