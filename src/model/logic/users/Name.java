package model.logic.users;

/**
 * This class represents a Name
 *
 * @author urliz
 * @version 1.0
 */
public class Name {

    private final String name;

    /**
     * Instantiates a new Name
     *
     * @param name the name
     */
    public Name(String name) {
        this.name = name;
    }

    /**
     * String representation of the Name
     *
     * @return String representation of the Name
     */
    @Override
    public String toString() {
        return name;
    }

}
