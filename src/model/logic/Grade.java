package model.logic;

import errors.Errors;
import errors.SyntaxException;

import java.util.Arrays;

/**
 * This enum represents a grade
 *
 * @author urliz
 * @version 1.0
 */
public enum Grade {
    /**
     * enum for the mark very good
     */
    VERY_GOOD(1, "sehr gut"),
    /**
     * enum for the mark good
     */
    GOOD(2, "gut"),
    /**
     * enum for the mark satisfactory
     */
    SATISFACTORY(3, "befriedigend"),
    /**
     * enum for the mark sufficient
     */
    SUFFICIENT(4, "ausreichend"),
    /**
     * enum for the mark inadequate
     */
    INADEQUATE(5, "mangelhaft");

    private final String germanRepresentation;
    private final int gradeNumber;

    /**
     * Instantiates a new Grade
     *
     * @param gradeNumber          the grade
     * @param germanRepresentation the german german Representation
     */
    Grade(int gradeNumber, String germanRepresentation) {
        this.germanRepresentation = germanRepresentation;
        this.gradeNumber = gradeNumber;
    }

    /**
     * function to find a grade though the int
     *
     * @param gradeNumber the grade
     * @return the grad as enum value
     * @throws SyntaxException If the command does not meet the requirements
     */
    public static Grade findGradeThroughInt(int gradeNumber) throws SyntaxException {
        return Arrays.stream(Grade.values()).filter(grade -> grade.gradeNumber == gradeNumber).findFirst().orElseThrow(() -> new SyntaxException(Errors.INVALID_GRADE));
    }

    /**
     * returns the german representation
     *
     * @return the german representation
     */
    public String getGermanRepresentation() {
        return germanRepresentation;
    }

    /**
     * returns the grade
     *
     * @return the grade
     */
    public int getGradeNumber() {
        return gradeNumber;
    }

}
