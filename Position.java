import java.util.Objects;

public class Position {
    private final int wordIndex;
    private final int letterIndex;
    
    public Position(int wordIndex, int letterIndex) {
        this.wordIndex = wordIndex;
        this.letterIndex = letterIndex;
    }

    // Override equals() to check for logical equality based on content
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return wordIndex == position.wordIndex && letterIndex == position.letterIndex;
    }

     // Override hashCode() for efficient lookup in the HashSet
    @Override
    public int hashCode() {
        return Objects.hash(wordIndex, letterIndex);
    }

    public int getWordIndex() {
        return wordIndex;
    }

    public int getLetterIndex() {
        return letterIndex;
    }
}
