import java.security.SecureRandom;
import java.util.Map;

public class Main {
    public static void main(String args[]){

        final SecureRandom rand = new SecureRandom();
        final Map<String, String> wordMap = new WordMapGenerator("eff_large_wordlist.txt").getWordMap();

        int numberOfWords = 3;
        int numberOfSymbols = 2;
        int numberOfCapitalLetters = 2;
        int numberOfDigits = 2;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-w":
                    if (i + 1 < args.length) {
                        try {
                            numberOfWords = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid value for number of words. Using default: " + numberOfWords);
                        }
                    }
                    break;
                case "-s":
                    if (i + 1 < args.length) {
                        try {
                            numberOfSymbols = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid value for number of symbols. Using default: " + numberOfSymbols);
                        }
                    }
                    break;
                case "-c":
                    if (i + 1 < args.length) {
                        try {
                            numberOfCapitalLetters = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid value for number of capital letters. Using default: " + numberOfCapitalLetters);
                        }
                    }
                    break;
                case "-d":
                    if (i + 1 < args.length) {
                        try {
                            numberOfDigits = Integer.parseInt(args[++i]);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid value for number of digits. Using default: " + numberOfDigits);
                        }
                    }
                    break;
                case "-h":
                case "--help":
                    System.out.println("Usage: java Main [-w words] [-s symbols] [-c capitals] [-d digits]");
                    System.out.println("Options:");
                    System.out.println("  -w <number>    Number of words (default: 3)");
                    System.out.println("  -s <number>    Number of symbols (default: 2)");
                    System.out.println("  -c <number>    Number of capital letters (default: 2)");
                    System.out.println("  -d <number>    Number of digits (default: 2)");
                    System.out.println("  -h, --help     Show this help message");
                    return;
            }
        }

        PassPhraseComponentGenerator passPhraseComponentGenerator = new PassPhraseComponentGenerator(wordMap, rand);

        PassPhraseGenerator passPhraseGenerator = new PassPhraseGenerator(passPhraseComponentGenerator, rand);

        PassPhrase passPhrase = passPhraseGenerator.generate(numberOfWords, numberOfSymbols, numberOfDigits, numberOfCapitalLetters);

        System.out.println(passPhrase);

    }
    
}
