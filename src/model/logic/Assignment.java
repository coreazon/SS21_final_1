package model.logic;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import errors.Errors;
import errors.SemanticsException;
import model.logic.users.Plagiarism;
import model.logic.users.Student;



/**
 * This class models an assignment
 *
 * @author urliz
 * @version 1.0
 */
public class Assignment implements Comparable<Assignment> {

    private static final String COLON = ":";
    private static final String SPACE = " ";
    private static final String FOR = "for";
    private static final String MARK_FOR = "mark for";
    private static final String MARKED_AS_PLAGIARISM = "marked as plagiarism";
    private static final String REMOVED_AND_GRADE = "removed and grade";
    private static final String RESTORED = "restored";
    private static final String FORMATTER = "[%s, %s / %s]";
    private static final String HYPHEN = "-";
    private static final String ASSIGNMENT_INFORMATION = "assignment id(%d)";
    private static final String FORMAT_DECIMAL = "%.2f";
    private static final double EPSILON = 0.0005;
    private final TextOfAssignment textOfAssignment;
    private final int idAssignment;
    private final Set<EvaluationAndSolution> evaluationAndSolutions;

    /**
     * Instantiates a new Assignment
     *
     * @param textOfAssignment the text of assignment
     * @param idAssignment     the assignment ID
     */
    public Assignment(TextOfAssignment textOfAssignment, int idAssignment) {

        this.textOfAssignment = textOfAssignment;
        this.idAssignment = idAssignment;
        this.evaluationAndSolutions = new TreeSet<>();
    }

    /**
     * returns the assignment ID
     *
     * @return the assignment ID
     */
    public int getIdAssignment() {
        return idAssignment;
    }

    private EvaluationAndSolution checkIfStudentHasSubmitSolution(Student student) {
        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions) {
            if (evaluationAndSolution.getStudent().equals(student)) return evaluationAndSolution;
        }
        return null;
    }

    private boolean checkIfStudentHasCorrection(Student student) throws SemanticsException {
        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions){
            if (evaluationAndSolution.getStudent().equals(student))
                if (!evaluationAndSolution.isCorrected()) return false;
        }
        return true;
    }

    /**
     * function that adds a students solution to the set
     *
     * @param studentSolution the students solution
     * @throws SemanticsException If the command does not meet the requirements
     */
    public void submitSolution(StudentSolution studentSolution) throws SemanticsException {

        //check if students exists in Set
        var possibleStudentSolution = checkIfStudentHasSubmitSolution(studentSolution.getStudent());
        if (hasCorrection()) throw new SemanticsException(Errors.NO_SUBMIT_ALLOWED);
        if (possibleStudentSolution != null) {
            possibleStudentSolution.changeSolution(studentSolution.getSolutionText());
            return;
        }
        evaluationAndSolutions.add(new EvaluationAndSolution(studentSolution, null));

    }

    private String calculateGradePointAverage() {
        if (evaluationAndSolutions.isEmpty()) return HYPHEN;

        var sumOfGrades = 0;
        var totalValidSolutions = 0;

        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions) {
            if (evaluationAndSolution.getGradeOfAssignment() != null) {
                sumOfGrades += evaluationAndSolution.getGradeOfAssignment();
                totalValidSolutions++;
            }
        }
        if (totalValidSolutions == 0) return HYPHEN;
        return String.format(FORMAT_DECIMAL, (double) sumOfGrades / (double) totalValidSolutions);
    }

    private int numberOfSubmissions() {
        return evaluationAndSolutions.size();
    }

    private int numberOfCorrections() {
        var numberOfCorrections = 0;
        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions) {
            if (evaluationAndSolution.isCorrected()) numberOfCorrections++;

        }
        return numberOfCorrections;
    }

    /**
     * The String representation for the assignment command
     *
     * @return the String representation
     */
    public String basicInformation() {
        return String.format(ASSIGNMENT_INFORMATION, idAssignment);
    }

    /**
     * function that check if a student has submitted a solution for the review command
     *
     * @param student    the student
     * @param correction the correction
     * @throws SemanticsException If the command does not meet the requirements
     */
    public void reviewSolution(Student student, Correction correction) throws SemanticsException {
        var studentSolution = checkIfStudentHasSubmitSolution(student);
        if (studentSolution == null) throw new SemanticsException(Errors.SOLUTION_DOES_NOT_EXIST);

        studentSolution.reviewSolution(correction);

    }

    /**
     * String representation and function for the markPlagiarism command
     *
     * @param student the student
     * @return String representation for the markPlagiarism command
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String markPlagiarism(Student student) throws SemanticsException {
        var solution = checkIfStudentHasSubmitSolution(student);
        if (solution == null) throw new SemanticsException(Errors.NO_SOLUTION);
        if (!checkIfStudentHasCorrection(student)) throw new SemanticsException(Errors.NOT_EVALUATED);
        var newGrade = solution.markPlagiarism();

        if (newGrade == -1) {
            return idAssignment + SPACE + FOR + SPACE + student.getMatriculationNumber().getMatriculationNumber()
                    + SPACE + MARKED_AS_PLAGIARISM;
        }
        return student.getMatriculationNumber().getMatriculationNumber() + SPACE +
                MARK_FOR + SPACE + idAssignment + SPACE + REMOVED_AND_GRADE + SPACE + newGrade + SPACE + RESTORED;
    }

    /**
     * String representation for the list reviews command
     *
     * @return String representation for the list review command
     */
    public String listReviews() {
        Set<EvaluationAndSolution> reviews = new TreeSet<>(EvaluationAndSolution::compareToTutor);
        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions) {
            if (!evaluationAndSolution.isCorrected()) continue;
            reviews.add(evaluationAndSolution);
        }

        var outputBuilder = new StringBuilder();
        for (EvaluationAndSolution evaluationAndSolution : reviews){
            outputBuilder.append(evaluationAndSolution.toString()).append(System.lineSeparator());
        }
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    /**
     * String representation for the list solutions command
     *
     * @return String representation for the list solutions command
     */
    public String listSolutions() {
        var outputSolution = new StringBuilder();

        for (EvaluationAndSolution studentSolution : evaluationAndSolutions) {
            outputSolution.append(studentSolution.studentSolutionToString()).append(System.lineSeparator());
        }
        return outputSolution.deleteCharAt(outputSolution.length() - 1).toString();
    }

    /**
     * String representation and function for the searchPlagiarism command
     *
     * @return String representation for the searchPlagiarism command
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String searchPlagiarism() throws SemanticsException {

        Set<Plagiarism> plagiarismSet = new TreeSet<>();

        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions) {
            if (!evaluationAndSolution.isCorrected()) throw new SemanticsException(Errors.NOT_EVALUATED);
            for (EvaluationAndSolution evaluationAndSolution2 : evaluationAndSolutions) {
                if (evaluationAndSolution.equals(evaluationAndSolution2)) continue;
                var plagiarism = new Plagiarism(evaluationAndSolution.getStudentSolution()
                        , evaluationAndSolution2.getStudentSolution());
                if (plagiarism.getRoundedPlagiarism() <= EPSILON) continue;
                plagiarismSet.add(plagiarism);

            }
        }
        var outputString = new StringBuilder();
        for (Plagiarism plagiarism : plagiarismSet) {
            outputString.append(plagiarism.toString()).append(System.lineSeparator());
        }
        return outputString.deleteCharAt(outputString.length() - 1).toString();
    }

    private boolean hasCorrection(){
        for (EvaluationAndSolution evaluationAndSolution : evaluationAndSolutions){
            if (evaluationAndSolution.isCorrected()) return true;
        }
        return false;
    }

    /**
     * String representation for the summary tasks command
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return idAssignment + COLON + SPACE + textOfAssignment.toString() + SPACE
                + String.format(FORMATTER, calculateGradePointAverage(), numberOfCorrections(), numberOfSubmissions());
    }

    /**
     * Comparison
     *
     * @param assignment the assignment to compare
     * @return the comparison
     */
    @Override
    public int compareTo(Assignment assignment) {
        return Integer.compare(idAssignment, assignment.idAssignment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return idAssignment == that.idAssignment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAssignment);
    }
}