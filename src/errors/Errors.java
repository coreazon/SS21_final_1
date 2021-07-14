package errors;

/**
 * Error-messages storage
 *
 * @author urliz
 * @version 1.0
 */
public class Errors {

    /**
     * Error-Message which gets thrown when the command arguments are wrong
     */
    public static final String COMMAND_PARAM_WRONG = "the parameters are wrong";
    /**
     * Error-Message which gets thrown when the command should have been implemented but is not
     */
    public static final String COMMAND_NOT_IMPLEMENTED = "command not implemented";
    /**
     * Error-Message which gets thrown when a Instructor already exists
     */
    public static final String INSTRUCTOR_EXISTS = "Instructor exists";
    /**
     * Error-Message which gets thrown when a tutor already exists
     */
    public static final String TUTOR_EXISTS = "Tutor exists";
    /**
     * Error-Message which gets thrown when a student already exists
     */
    public static final String STUDENT_EXISTS = "Student exists";
    /**
     * Error-Message which gets thrown when a assignment does not exists
     */
    public static final String ASSIGNMENT_DOES_NOT_EXISTS = "assignment does not exists";
    /**
     * Error-Message which gets thrown when there is no such student
     */
    public static final String NO_SUCH_STUDENT_EXISTS = "no such student";
    /**
     * Error-Message which gets thrown when the tutor does not exists
     */
    public static final String TUTOR_DOES_NOT_EXISTS = "tutor does not exist";
    /**
     * Error-Message which gets thrown when when the solution does not exists
     */
    public static final String SOLUTION_DOES_NOT_EXIST = "solution does not exists";
    /**
     * Error-Message which gets thrown when there is no permission to correct
     */
    public static final String NOT_ALLOWED_TO_CORRECT = "no permission to correct";
    /**
     * Error-Message which gets thrown when the grade is invalid
     */
    public static final String INVALID_GRADE = "not a valid grade";
    /**
     * Error-Message which gets thrown when there is no solution
     */
    public static final String NO_SOLUTION = "no solution";
    /**
     * Error-Message which gets thrown when the solution was not evaluated yet
     */
    public static final String NOT_EVALUATED = "not evaluated yet";
    /**
     * Error-Message which gets thrown when an evaluation already exists
     */
    public static final String ALREADY_EVALUATED = "already evaluated";

    private Errors() throws IllegalAccessException {
        throw new IllegalStateException("Utility-class Constructor");
    }
}