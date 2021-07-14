package model.logic;

import errors.Errors;
import errors.SemanticsException;
import model.logic.users.Student;

import java.util.Objects;

/**
 * This class models the evaluation and solution
 *
 * @author urliz
 * @version 1.0
 */
public class EvaluationAndSolution implements Comparable<EvaluationAndSolution> {

    private static final String BRACKETS = "[";
    private static final String COMMA = ",";
    private static final String BRACKETS_CLOSE = "]";
    private static final String SPACE = " ";
    private final Student student;
    private final StudentSolution studentSolution;
    private Correction correction;

    /**
     * Instantiates a EvaluationAndSolution object
     *
     * @param studentSolution the student solution
     * @param correction      the correction
     */
    public EvaluationAndSolution(StudentSolution studentSolution, Correction correction) {
        this.studentSolution = studentSolution;
        this.correction = correction;
        this.student = studentSolution.getStudent();
    }

    /**
     * function to change a solution
     *
     * @param solutionText the text of the solution
     * @throws SemanticsException If the command does not meet the requirements
     */
    public void changeSolution(SolutionText solutionText) throws SemanticsException {
        if (isCorrected()) throw new SemanticsException(Errors.ALREADY_EVALUATED);
        studentSolution.changeSolution(solutionText);
    }

    /**
     * return if it has been corrected
     *
     * @return if it as been corrected
     */
    public boolean isCorrected() {
        return correction != null;
    }

    /**
     * function to mark as plagiarism
     *
     * @return the grade or -1 if its marked as plagiarism
     */
    public int markPlagiarism() {
        if (correction.isPlagiarism()) {
            correction.markAsPlagiat();
            return correction.getGradeNumber();
        }
        correction.markAsPlagiat();
        return -1;
    }

    /**
     * function for the review command
     *
     * @param correction the correction
     * @throws SemanticsException If the command does not meet the requirements
     */
    public void reviewSolution(Correction correction) throws SemanticsException {
        if (!isCorrected()) {
            this.correction = correction;
            return;
        }

        if (isCorrected() && correction.equals(this.correction)) {
            this.correction = correction;
            return;
        }

        throw new SemanticsException(Errors.NOT_ALLOWED_TO_CORRECT);

    }

    /**
     * returns the grade
     *
     * @return the grade
     */
    public Integer getGradeOfAssignment() {
        if (isCorrected()) {
            return correction.getGradeNumber();
        } else {
            return null;
        }
    }

    /**
     * returns the student
     *
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * returns the students solution
     *
     * @return the students solution
     */
    public StudentSolution getStudentSolution() {
        return studentSolution;
    }

    /**
     * String representation of a students solution
     *
     * @return String representation of a studnts solution
     */
    public String studentSolutionToString() {
        return studentSolution.toString();
    }

    /**
     * String representation of list reviews command
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return correction.toString() + SPACE + BRACKETS + student
                .getMatriculationNumber().getMatriculationNumber() + COMMA + SPACE + correction.getGradeNumber() + BRACKETS_CLOSE;
    }

    /**
     * Equality
     *
     * @param o to check
     * @return the equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationAndSolution that = (EvaluationAndSolution) o;
        return Objects.equals(student, that.student);
    }

    /**
     * returns the hashCode
     *
     * @return hash values
     */
    @Override
    public int hashCode() {
        return Objects.hash(student);
    }

    /**
     * Comparison
     *
     * @param o to compare
     * @return the comparison
     */
    @Override
    public int compareTo(EvaluationAndSolution o) {
        return student.compareTo(o.getStudent());

    }
}
