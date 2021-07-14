package model.logic;

/**
 * This class represents a text for a assignment
 *
 * @author urliz
 * @version 1.0
 */
public class TextOfAssignment {
    private final String text;

    /**
     * Instantiates a text for a assignment
     *
     * @param text the text
     */
    public TextOfAssignment(String text) {
        this.text = text;
    }

    /**
     * The String representation
     *
     * @return the String representation
     */
    @Override
    public String toString() {
        return text;
    }
}
