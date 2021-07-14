package model.logic.users;


import java.util.Objects;

/**
 * This class models a Matriculation matriculationNumber
 *
 * @author urliz
 * @version 1.0
 */
public class Matriculation {

    private final int matriculationNumber;

    /**
     * Instantiates a new Matriculation number
     *
     * @param matriculationNumber the matriculation number
     */
    public Matriculation(int matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    /**
     * returns the matriculationNumber
     *
     * @return the matriculationNumber
     */
    public int getMatriculationNumber() {
        return matriculationNumber;
    }

    /**
     * String representation of a matriculationNumber
     *
     * @return String representation of a matriculationNumber
     */
    @Override
    public String toString() {
        return String.valueOf(matriculationNumber);
    }

    /**
     * Equality of two MatriculationNumber
     *
     * @param o the MatriculationNumber to check
     * @return the Equality
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matriculation matriculation = (Matriculation) o;
        return getMatriculationNumber() == matriculation.getMatriculationNumber();
    }

    /**
     * generate hash code
     *
     * @return hash values
     */
    @Override
    public int hashCode() {
        return Objects.hash(getMatriculationNumber());
    }
}
