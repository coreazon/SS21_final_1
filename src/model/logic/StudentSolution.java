package model.logic;

import model.logic.users.Student;

/**
 * This class represents students solution
 *
 * @author urliz
 * @version 1.0
 */
public class StudentSolution implements Comparable<StudentSolution> {

    private static final String SPACE = " ";
    private static final String OPEN_BRACKETS = "(%d)";
    private static final String COLON = ":";
    private final Student student;
    private SolutionText solutionText;

    /**
     * Instantiates a students solution
     *
     * @param student      the student
     * @param solutionText the solution text
     */
    public StudentSolution(Student student, SolutionText solutionText) {
        this.student = student;
        this.solutionText = solutionText;
    }

    /**
     * function to change a solution
     *
     * @param newSolutionText the new SolutionText
     */
    public void changeSolution(SolutionText newSolutionText) {
        this.solutionText = newSolutionText;
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
     * returns the solutionText
     *
     * @return the solutionText
     */
    public SolutionText getSolutionText() {
        return solutionText;
    }

    /**
     * String representation for the list solution command
     *
     * @return the String representation
     */
    @Override
    public String toString() {
        return student.getName().toString() + SPACE + String.format(OPEN_BRACKETS,
                student.getMatriculationNumber().getMatriculationNumber())
                + COLON + SPACE + solutionText.toString();
    }

    /**
     * Comparison
     *
     * @param studentSolution the studentSolution
     * @return the comparison
     */
    @Override
    public int compareTo(StudentSolution studentSolution) {
        return student.compareTo(studentSolution.student);
    }

}
