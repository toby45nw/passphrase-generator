import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassPhraseComponentGeneratorTest {

    private PassPhraseComponentGenerator componentGenerator;

    @Mock
    private SecureRandom rand;

    @BeforeEach
    public void setUp() {
        Map<String, String> wordMap = new HashMap<>();
        wordMap.put("11111", "testword");
        componentGenerator = new PassPhraseComponentGenerator(wordMap, rand);
    }

    @Test
    public void testGenerateWords() {
        // Given
        int numberOfWords = 5;
        when(rand.nextInt(6)).thenReturn(0); // This will make the dice roll always 1

        // When
        ArrayList<String> words = componentGenerator.generateWords(numberOfWords);

        // Then
        assertEquals(numberOfWords, words.size());
        for (String word : words) {
            assertEquals("testword", word);
        }
    }

    @Test
    public void testGenerateSpecialCharacters() {
        // Given
        int numberOfSymbols = 10;
        String specialCharactersSet = "!#$%^&*()=-+[]\\{}:;\"'<>?/";
        when(rand.nextInt(specialCharactersSet.length())).thenReturn(0);

        // When
        ArrayList<Character> specialCharacters = componentGenerator.generateSpecialCharacters(numberOfSymbols);

        // Then
        assertEquals(numberOfSymbols, specialCharacters.size());
        for (Character c : specialCharacters) {
            assertEquals('!', c);
        }
    }

    @Test
    public void testGenerateDigits() {
        // Given
        int numberOfDigits = 20;
        when(rand.nextInt(10)).thenReturn(5);

        // When
        ArrayList<Integer> digits = componentGenerator.generateDigits(numberOfDigits);

        // Then
        assertEquals(numberOfDigits, digits.size());
        for (Integer digit : digits) {
            assertEquals(5, digit);
        }
    }
}