import java.util.Objects;

/**
 * Represents a position of a character within a list of words.
 * This class is used to identify a specific character by its word index and letter index.
 */
public class Position {
    private final int wordIndex;
    private final int letterIndex;
    
    /**
     * Constructs a Position with a word index and a letter index.
     *
     * @param wordIndex The index of the word in the list.
     * @param letterIndex The index of the character in the word.
     */
    public Position(int wordIndex, int letterIndex) {
        this.wordIndex = wordIndex;
        this.letterIndex = letterIndex;
    }

    /**
     * Checks if this Position is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return wordIndex == position.wordIndex && letterIndex == position.letterIndex;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(wordIndex, letterIndex);
    }

    /**
     * Gets the word index of this position.
     *
     * @return The word index.
     */
    public int getWordIndex() {
        return wordIndex;
    }

    /**
     * Gets the letter index of this position.
     *
     * @return The letter index.
     */
    public int getLetterIndex() {
        return letterIndex;
    }
}
