import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PassPhraseGeneratorTest {

    @Mock
    private PassPhraseComponentGenerator componentGenerator;

    @Mock
    private SecureRandom rand;

    @InjectMocks
    private PassPhraseGenerator passPhraseGenerator;

    @Test
    public void testGenerate() {
        // Given
        int numberOfWords = 2;
        int numberOfSymbols = 1;
        int numberOfDigits = 1;
        int numberOfCapitalLetters = 1;

        ArrayList<String> words = new ArrayList<>(Arrays.asList("wordone", "wordtwo"));
        ArrayList<Character> symbols = new ArrayList<>(Collections.singletonList('@'));
        ArrayList<Integer> digits = new ArrayList<>(Collections.singletonList(5));

        when(componentGenerator.generateWords(numberOfWords)).thenReturn(words);
        when(componentGenerator.generateSpecialCharacters(numberOfSymbols)).thenReturn(symbols);
        when(componentGenerator.generateDigits(numberOfDigits)).thenReturn(digits);

        // When
        PassPhrase passPhrase = passPhraseGenerator.generate(numberOfWords, numberOfSymbols, numberOfDigits, numberOfCapitalLetters);
        String passPhraseValue = passPhrase.value();

        // Then
        long wordCount = Arrays.stream(passPhraseValue.split("[^a-zA-Z]+")).filter(s -> !s.isEmpty()).count();
        long digitCount = passPhraseValue.chars().filter(Character::isDigit).count();
        long symbolCount = passPhraseValue.chars().filter(c -> !Character.isLetterOrDigit(c)).count();

        assertEquals(2, wordCount);
        assertEquals(1, digitCount);
        assertEquals(1, symbolCount);
    }
}
