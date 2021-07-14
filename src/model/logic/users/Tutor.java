package model.logic.users;

import java.util.Objects;

/**
 * This class models a Tutor
 *
 * @author urliz
 * @version 1.0
 */
public class Tutor extends User implements Comparable<Tutor> {
    /**
     * Instantiate a new Tutor
     *
     * @param name the name of the Tutor
     */
    public Tutor(Name name) {
        super(name);
    }

    /**
     * String representation of a Tutor
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return super.toString();
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
        Tutor tutor = (Tutor) obj;
        return Objects.equals(super.toString(), tutor.toString());
    }

    /**
     * Comparison
     *
     * @param tutor to compare
     * @return the Comparison
     */
    @Override
    public int compareTo(Tutor tutor) {
        return toString().compareTo(tutor.toString());
    }
}
