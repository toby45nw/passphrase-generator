import java.security.SecureRandom;
import java.util.Map;

public class Main {
    public static void main(String args[]){

        final SecureRandom rand = new SecureRandom();
        final Map<String, String> wordMap = new WordMapGenerator().getWordMap();

        int numberOfWords = 3;
        int numberOfSymbols = 2;
        int numberOfCapitalLetters = 2;
        int numberOfDigits = 2;

        PassPhraseComponentGenerator passPhraseComponentGenerator = new PassPhraseComponentGenerator(wordMap, rand);

        PassPhraseGenerator passPhraseGenerator = new PassPhraseGenerator(passPhraseComponentGenerator, rand);

        PassPhrase passPhrase = passPhraseGenerator.generate(numberOfWords, numberOfSymbols, numberOfDigits, numberOfCapitalLetters);

        System.out.println(passPhrase);

    }
    
}
