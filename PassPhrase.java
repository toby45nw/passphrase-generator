/**
 * Represents a generated passphrase.
 * This class is a simple wrapper for the final passphrase string.
 */
public class PassPhrase {
    private final String value;

    /**
     * Constructs a PassPhrase object with the given value.
     *
     * @param value The passphrase string.
     */
    public PassPhrase(String value) {
        this.value = value;
    }

    /**
     * Gets the passphrase string.
     *
     * @return The passphrase.
     */
    public String getValue() {
        return value;
    }

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
