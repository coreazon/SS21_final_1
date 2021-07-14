package model.logic.users;

import model.logic.StudentSolution;

/**
 * This class model the Plagiarism
 *
 * @author urliz
 * @version 1.0
 */
public class Plagiarism implements Comparable<Plagiarism> {

    private static final String SPACE = " ";
    private static final double ROUNDER_TEN_THOUSAND = 10000.0;
    private static final double ROUNDER_HUNDRED = 100.0;
    private static final String FORMAT_DECIMAL = "%.2f";
    private final StudentSolution firstStudentSolution;
    private final StudentSolution secondStudentSolution;
    private final double roundedPlagiarism;
    private final String subStringOfPlagiarism;

    /**
     * Instantiates a new Plagiarism
     *
     * @param firstStudentSolution  the first students solution
     * @param secondStudentSolution the second students solution
     */
    public Plagiarism(StudentSolution firstStudentSolution, StudentSolution secondStudentSolution) {
        this.firstStudentSolution = firstStudentSolution;
        this.secondStudentSolution = secondStudentSolution;
        this.subStringOfPlagiarism = firstStudentSolution.getSolutionText()
                .getLongestCommonSubstring(secondStudentSolution.getSolutionText());
        this.roundedPlagiarism = roundPlagiarism();
    }

    /**
     * returns the roundedPlagiarism
     *
     * @return the roundedPlagiarism
     */
    public double getRoundedPlagiarism() {
        return roundedPlagiarism;
    }

    /**
     * returns the firstStudentSolution
     *
     * @return the firstStudentSolution
     */
    public Student getFirstStudentSolution() {
        return firstStudentSolution.getStudent();
    }

    /**
     * returns the secondStudentSolution
     *
     * @return the secondStudentSolution
     */
    public Student getSecondStudent() {
        return secondStudentSolution.getStudent();
    }

    private double roundPlagiarism() {
        double firstStep = (double) subStringOfPlagiarism.length()
                / (double) firstStudentSolution.getSolutionText().toString().length() * ROUNDER_TEN_THOUSAND;
        return Math.round(firstStep) / ROUNDER_HUNDRED;
    }

    /**
     * String representation of the search Plagiarism command
     *
     * @return the String representation
     */
    @Override
    public String toString() {
        return firstStudentSolution.getStudent().getMatriculationNumber().toString() + SPACE
                + secondStudentSolution.getStudent().getMatriculationNumber().toString() + SPACE
                + subStringOfPlagiarism + SPACE + subStringOfPlagiarism.length() + SPACE + String.format(FORMAT_DECIMAL, roundedPlagiarism);
    }

    /**
     * Comparison
     *
     * @param plagiarism the plagiarism to compare
     * @return the comparison
     */
    @Override
    public int compareTo(Plagiarism plagiarism) {
        if (plagiarism.getRoundedPlagiarism() != roundedPlagiarism)
            return Double.compare(plagiarism.getRoundedPlagiarism(), roundedPlagiarism);
        if (!getFirstStudentSolution().equals(plagiarism.getFirstStudentSolution()))
            return getFirstStudentSolution().compareTo(plagiarism.getFirstStudentSolution());

        return getSecondStudent().compareTo(plagiarism.getSecondStudent());
    }
}
