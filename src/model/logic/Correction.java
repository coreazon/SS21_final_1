package model.logic;

import model.logic.users.Instructor;
import model.logic.users.Tutor;

/**
 * This class models a correction
 *
 * @author urliz
 * @version 1.0
 */
public class Correction implements Comparable<Correction>{

    private static final String COLON = ":";
    private static final String SPACE = " ";
    private final Tutor tutor;
    private final Grade grade;
    private final CorrectionText correctionText;
    private boolean isPlagiarism;

    /**
     * Instantiates a new Correction
     *
     * @param tutor          the tutor
     * @param grade          the grade
     * @param correctionText the correction text
     */
    public Correction(Tutor tutor, Grade grade, CorrectionText correctionText) {
        this.tutor = tutor;
        this.grade = grade;
        this.isPlagiarism = false;
        this.correctionText = correctionText;
    }

    /**
     * returns if its marked as plagiarism
     *
     * @return if its marked as plagiarism
     */
    public boolean isPlagiarism() {
        return isPlagiarism;
    }

    /*
     *marks as plagiarism
     */
    public void markAsPlagiat() {
        isPlagiarism = !isPlagiarism;
    }

    /**
     * returns the grade
     *
     * @return the grade
     */
    public int getGradeNumber() {
        if (isPlagiarism) return Grade.INADEQUATE.getGradeNumber();
        return grade.getGradeNumber();
    }

    public Tutor getTutor() {
        return tutor;
    }

    /**
     * String representation of a correction
     *
     * @return returns the String representation
     */
    @Override
    public String toString() {
        return tutor.toString() + COLON + SPACE + correctionText.toString();
    }

    /**
     * Equality of two corrections
     *
     * @param obj to check
     * @return the equality
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        var correction = (Correction) obj;
        return correction.tutor.equals(tutor);

    }

    @Override
    public int compareTo(Correction correction) {
        return tutor.toString().compareTo(correction.getTutor().toString());
    }

}
