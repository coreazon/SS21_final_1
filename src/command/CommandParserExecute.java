package command;

import core.Pair;
import errors.Errors;
import errors.SyntaxException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a Command Parser, which has the purpose to check the Validation of the Syntax and handles
 * the conversion of the inputted String
 *
 * @author urliz
 * @version 1.0
 */
public class CommandParserExecute implements CommandParser {


    /**
     * the quit command
     */
    public static final String QUIT_COMMAND = "quit";
    /**
     * the instructor command
     */
    public static final String INSTRUCTOR_COMMAND = "instructor";
    /**
     * the list-instructors command
     */
    public static final String LIST_INSTRUCTORS_COMMAND = "list-instructors";
    /**
     * the tutor command
     */
    public static final String TUTOR_COMMAND = "tutor";
    /**
     * the list-tutors command
     */
    public static final String LIST_TUTORS_COMMAND = "list-tutors";
    /**
     * the student command
     */
    public static final String STUDENT_COMMAND = "student";
    /**
     * the list-students command
     */
    public static final String LIST_STUDENTS_COMMAND = "list-students";
    /**
     * the assignment command
     */
    public static final String ASSIGNMENT_COMMAND = "assignment";
    /**
     * the submit command
     */
    public static final String SUBMIT_COMMAND = "submit";
    /**
     * the list-solutions command
     */
    public static final String LIST_SOLUTIONS_COMMAND = "list-solutions";
    /**
     * the review command
     */
    public static final String REVIEW_COMMAND = "review";
    /**
     * the list-reviews command
     */
    public static final String LIST_REVIEWS_COMMAND = "list-reviews";
    /**
     * the search-plagiarism command
     */
    public static final String SEARCH_PLAGIARISM_COMMAND = "search-plagiarism";
    /**
     * the mark-plagiarism command
     */
    public static final String MARK_PLAGIARISM_COMMAND = "mark-plagiarism";
    /**
     * the summary-tasks command
     */
    public static final String SUMMARY_TASKS_COMMAND = "summary-tasks";
    /**
     * the reset command
     */
    public static final String RESET_COMMAND = "reset";
    /**
     * Matriculation number
     */
    public static final String MATRICULATION = "[1-9][0-9]{4}";
    /**
     * any name
     */
    public static final String NAME = "\\S+";
    /**
     * empty String
     */
    public static final String EMPTY_STRING = "";
    /**
     * char space
     */
    public static final char SPACE_CHAR = ' ';
    /**
     * the id
     */
    public static final String ID = "[1-9][0-9]*";
    /**
     * the grade
     */
    public static final String GRADE = "[1-5]";
    /**
     * text
     */
    public static final String TEXT = "\\S+";
    /**
     * regex of the quit command
     */
    public static final String REGEX_QUIT_COMMAND = QUIT_COMMAND;


    //REGEX


    /**
     * regex of the list-instructors command
     */
    public static final String REGEX_LIST_INSTRUCTORS_COMMAND = LIST_INSTRUCTORS_COMMAND;
    /**
     * regex of the list-tutors command
     */
    public static final String REGEX_LIST_TUTORS_COMMAND = LIST_TUTORS_COMMAND;
    /**
     * regex of the list-students command
     */
    public static final String REGEX_LIST_STUDENTS_COMMAND = LIST_STUDENTS_COMMAND;
    /**
     * regex of the reset command
     */
    public static final String REGEX_RESET_COMMAND = RESET_COMMAND;
    /**
     * regex of the summary-tasks command
     */
    public static final String REGEX_SUMMARY_TASKS_COMMAND = SUMMARY_TASKS_COMMAND;
    /**
     * space
     */
    private static final String SPACE = " ";
    /**
     * regex of the instructor command
     */
    public static final String REGEX_INSTRUCTOR_COMMAND = INSTRUCTOR_COMMAND + SPACE + NAME;
    /**
     * regex of the tutor command
     */
    public static final String REGEX_TUTOR_COMMAND = TUTOR_COMMAND + SPACE + NAME;
    /**
     * regex of the student command
     */
    public static final String REGEX_STUDENT_COMMAND = STUDENT_COMMAND + SPACE + NAME + SPACE + MATRICULATION;
    /**
     * regex of the assignment command
     */
    public static final String REGEX_ASSIGNMENT_COMMAND = ASSIGNMENT_COMMAND + SPACE + NAME;
    /**
     * regex of the submit command
     */
    public static final String REGEX_SUBMIT_COMMAND = SUBMIT_COMMAND + SPACE + ID + SPACE + MATRICULATION + SPACE + TEXT;
    /**
     * regex of the MARK_PLAGIARISM_COMMAND command
     */
    public static final String REGEX_MARK_PLAGIARISM_COMMAND = MARK_PLAGIARISM_COMMAND + SPACE + NAME + SPACE + MATRICULATION + SPACE + ID;
    /**
     * regex of the search-plagiarism command
     */
    public static final String REGEX_SEARCH_PLAGIARISM_COMMAND = SEARCH_PLAGIARISM_COMMAND + SPACE + ID;
    /**
     * regex of the list-solutions command
     */
    public static final String REGEX_LIST_SOLUTIONS_COMMAND = LIST_SOLUTIONS_COMMAND + SPACE + ID;
    /**
     * regex of the review command
     */
    public static final String REGEX_REVIEW_COMMAND = REVIEW_COMMAND + SPACE + NAME + SPACE + ID + SPACE + MATRICULATION + SPACE + GRADE + SPACE + TEXT;
    /**
     * regex of the list-reviews command
     */
    public static final String REGEX_LIST_REVIEWS_COMMAND = LIST_REVIEWS_COMMAND + SPACE + ID;


    /**
     * check the input with the regex and throws Exception if it does not match
     * or returns the input as a Pair
     *
     * @param inputUser the whole input
     * @return the input as a Pair
     * @throws SyntaxException if the regex does not match
     */
    @Override
    public Pair<String, List<String>> parseCommand(String inputUser) throws SyntaxException {
        checkBasicRegex(inputUser);
        String commandValue = inputUser.split(SPACE)[0];

        return new Pair<>(checkCommand(commandValue, inputUser), createParameters(inputUser, commandValue));

    }

    private List<String> createParameters(String inputUser, String commandValue) {
        String modifiedInput = inputUser.substring(commandValue.length());
        if (modifiedInput.equals(EMPTY_STRING)) {
            return new LinkedList<>();
        }

        String[] outputAsStringArray = modifiedInput.substring(1).split(SPACE);
        return new LinkedList<>(Arrays.asList(outputAsStringArray));
    }

    private void checkBasicRegex(String inputUser) throws SyntaxException {
        if (inputUser.isEmpty()) throw new SyntaxException(Errors.COMMAND_PARAM_WRONG);
        if (inputUser.charAt(0) == SPACE_CHAR) {
            throw new SyntaxException(Errors.COMMAND_PARAM_WRONG);
        }
    }

    private String checkCommand(String command, String userInput) throws SyntaxException {


        if (userInput.matches(Commands.getCommand(command).getRegexOfCommand())) {
            return command;
        }
        throw new SyntaxException(Errors.COMMAND_PARAM_WRONG);
    }
}

