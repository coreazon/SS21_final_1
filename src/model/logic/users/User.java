package model.logic.users;

/**
 * This class models an abstract User
 *
 * @author urliz
 * @version 1.0
 */
public abstract class User {

    private final Name name;

    /**
     * Instantiate a new User
     *
     * @param name the name
     */
    protected User(Name name) {
        this.name = name;
    }

    /**
     * returns the name
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * String representation
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return name.toString();
    }

}
