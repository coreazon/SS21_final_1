package model.logic.users;

import java.util.Objects;

/**
 * This class models a Instructor
 *
 * @author urliz
 * @version 1.0
 */
public class Instructor extends User implements Comparable<Instructor> {

    /**
     * Instantiates a new Instructor
     *
     * @param name the name of the instructor
     */
    public Instructor(Name name) {
        super(name);

    }

    /**
     * String representation of a Instructor
     *
     * @return String representation of a Instructor
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Equality of Instructors
     *
     * @param obj Instructor to examine
     * @return the equality
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Instructor instructor = (Instructor) obj;
        return Objects.equals(super.toString(), instructor.toString());
    }

    /**
     * Comparison of Instructors
     *
     * @param instructor Instructor to compare
     * @return the Comparison
     */
    @Override
    public int compareTo(Instructor instructor) {
        return toString().compareTo(instructor.toString());
    }

}
