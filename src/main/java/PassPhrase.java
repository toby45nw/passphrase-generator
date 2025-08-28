/**
 * Represents a generated passphrase.
 * This class is a simple wrapper for the final passphrase string.
 */
public record PassPhrase(String value) {

    /**
     * Returns the string representation of the passphrase.
     *
     * @return The passphrase string.
     */
    @Override
    public String toString() {
        return value;
    }
}