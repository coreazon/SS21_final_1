package model.logic.users;

/**
 * This class models a Student
 *
 * @author urliz
 * @version 1.0
 */
public class Student extends User implements Comparable<Student> {

    private static final String SPACE = " ";
    private static final String OPEN_BRACKETS = "(";
    private static final String CLOSE_BRACKETS = ")";
    private final Matriculation matriculationNumber;

    /**
     * Instantiates a new Student
     *
     * @param name                the name
     * @param matriculationNumber the matriculationNumber
     */
    public Student(Name name, Matriculation matriculationNumber) {
        super(name);
        this.matriculationNumber = matriculationNumber;
    }

    /**
     * returns the matriculationNumber
     *
     * @return the matriculationNumber
     */
    public Matriculation getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     * String representation of a Student
     *
     * @return String representation of a Student
     */
    @Override
    public String toString() {
        return super.getName() + SPACE + OPEN_BRACKETS + matriculationNumber.toString() + CLOSE_BRACKETS;
    }

    /**
     * Comparison
     *
     * @param student the student to compare
     * @return the Comparison
     */
    @Override
    public int compareTo(Student student) {
        return Integer.compare(matriculationNumber.getMatriculationNumber(), student.getMatriculationNumber().getMatriculationNumber());
    }

    /**
     * Equality
     *
     * @param obj to check
     * @return the equality
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return student.matriculationNumber.getMatriculationNumber() == matriculationNumber.getMatriculationNumber();
    }

}
