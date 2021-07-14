package model.logic;

/**
 * This class represents a correction text
 *
 * @author urliz
 * @version 1.0
 */
public class CorrectionText {


    private final String text;

    /**
     * Instantiates a CorrectionText
     *
     * @param text the comment
     */
    public CorrectionText(String text) {
        this.text = text;
    }

    /**
     * String representation of the correction text
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return text;
    }
}
