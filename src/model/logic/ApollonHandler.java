package model.logic;

import java.util.Set;
import java.util.TreeSet;

import errors.Errors;
import errors.SemanticsException;
import model.logic.users.Instructor;
import model.logic.users.Matriculation;
import model.logic.users.Student;
import model.logic.users.Tutor;

/**
 * This class models a task handler of the apollo Task.
 * It is used to execute and handle every command of the task.
 *
 * @author urliz
 * @version 1.0
 */
public class ApollonHandler {


    private static final String SUCCESS_WITHOUT_MESSAGE = "";
    private int assignmentCounter;
    private Set<Assignment> assignmentSet;
    private Set<Student> studentSet;
    private Set<Instructor> instructorSet;
    private Set<Tutor> tutorSet;

    /**
     * Instantiate a new Apollon
     */
    public ApollonHandler() {
        this.assignmentSet = new TreeSet<>();
        this.studentSet = new TreeSet<>();
        this.instructorSet = new TreeSet<>();
        this.tutorSet = new TreeSet<>();
        assignmentCounter = 1;
    }

    /**
     * resets the game
     *
     * @return an Empty String if successful
     */
    public String resetCommand() {
        this.assignmentSet = new TreeSet<>();
        this.studentSet = new TreeSet<>();
        this.instructorSet = new TreeSet<>();
        this.tutorSet = new TreeSet<>();
        assignmentCounter = 1;

        return SUCCESS_WITHOUT_MESSAGE;
    }

    /**
     * Performs the instructor Command and executes the necessary steps to execute the command.
     *
     * @param instructor the Instructor
     * @return an empty String if it was successful
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String addInstructorCommand(Instructor instructor) throws SemanticsException {
        //check if instructor name exists
        if (instructorSet.contains(instructor)) throw new SemanticsException(Errors.INSTRUCTOR_EXISTS);
        if (tutorSet.contains(new Tutor(instructor.getName()))) throw new SemanticsException(Errors.NAME_TAKEN);
        instructorSet.add(instructor);

        return SUCCESS_WITHOUT_MESSAGE;
    }

    /**
     * Performs the list instructors Command and executes the necessary steps to execute the command.
     *
     * @return all instructors
     */
    public String listInstructors() {

        if (instructorSet.isEmpty()) return SUCCESS_WITHOUT_MESSAGE;

        var builder = new StringBuilder();
        for (Instructor instructor : instructorSet) {
            builder.append(instructor.toString()).append(System.lineSeparator());
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * Performs the tutor Command and executes the necessary steps to execute the command.
     *
     * @param tutor the tutor
     * @return an empty String if it was successful
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String addTutorCommand(Tutor tutor) throws SemanticsException {
        //check if tutor name exists
        if (tutorSet.contains(tutor)) throw new SemanticsException(Errors.TUTOR_EXISTS);
        if (instructorSet.contains(new Instructor(tutor.getName()))) throw new SemanticsException(Errors.NAME_TAKEN);
        tutorSet.add(tutor);

        return SUCCESS_WITHOUT_MESSAGE;
    }

    /**
     * Performs the list tutors Command and executes the necessary steps to execute the command.
     *
     * @return all instructors
     */
    public String listTutors() {

        if (tutorSet.isEmpty()) return SUCCESS_WITHOUT_MESSAGE;

        var builder = new StringBuilder();
        for (Tutor tutor : tutorSet) {
            builder.append(tutor.toString()).append(System.lineSeparator());
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * Performs the student Command and executes the necessary steps to execute the command.
     *
     * @param student the student
     * @return an empty String if it was successful
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String addStudentCommand(Student student) throws SemanticsException {
        //check if matriculation number exists
        if (studentSet.contains(student)) throw new SemanticsException(Errors.STUDENT_EXISTS);
        studentSet.add(student);
        return SUCCESS_WITHOUT_MESSAGE;
    }

    /**
     * Performs the list students Command and executes the necessary steps to execute the command.
     *
     * @return every student
     */
    public String listStudentsCommand() {
        if (studentSet.isEmpty()) return SUCCESS_WITHOUT_MESSAGE;

        var builder = new StringBuilder();
        for (Student student : studentSet) {
            builder.append(student.toString()).append(System.lineSeparator());
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    /**
     * Performs the assignment Command and executes the necessary steps to execute the command.
     *
     * @param textOfAssignment the assignment
     * @return the assignment and its id
     */
    public String addAssignmentCommand(TextOfAssignment textOfAssignment) throws SemanticsException {

        if (instructorSet.isEmpty()) throw new SemanticsException(Errors.NO_INSTRUCTOR);

        var newAssignment = new Assignment(textOfAssignment, assignmentCounter);
        assignmentSet.add(newAssignment);
        assignmentCounter++;
        return newAssignment.basicInformation();
    }

    /**
     * Performs the submit Command and executes the necessary steps to execute the command.
     *
     * @param assignmentID  the id of the assignment
     * @param matriculation the matriculation of the student
     * @param solutionText  the solution of the student
     * @return an empty String if it was successful
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String submitCommand(int assignmentID, Matriculation matriculation, SolutionText solutionText) throws SemanticsException {
        //check if assignment exists
        var newAssignment = findAssignment(assignmentID);
        //check if students exists        
        var newStudent = findStudent(matriculation);

        newAssignment.submitSolution(new StudentSolution(newStudent, solutionText));

        return SUCCESS_WITHOUT_MESSAGE;
    }

    /**
     * Performs the list solutions Command and executes the necessary steps to execute the command.
     *
     * @param assignmentID the id of the assignment
     * @return the student, his matriculation and his solution
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String listSolutionsCommand(int assignmentID) throws SemanticsException {
        return findAssignment(assignmentID).listSolutions();
    }

    /**
     * Performs the review Command and executes the necessary steps to execute the command.
     *
     * @param tutor          the tutor
     * @param assignmentID   the id of the assignment
     * @param matriculation  the matriculation of the student
     * @param grade          the given grade for solution
     * @param correctionText the comment of the tutor
     * @return an empty String if it was successful
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String reviewCommand(Tutor tutor, int assignmentID, Matriculation matriculation, Grade grade, CorrectionText correctionText) throws SemanticsException {
        //check tutor
        var correctionTutor = findTutor(tutor);
        //check assignment 
        var assignment = findAssignment(assignmentID);
        //check student
        var student = findStudent(matriculation);

        assignment.reviewSolution(student, new Correction(correctionTutor, grade, correctionText));

        return SUCCESS_WITHOUT_MESSAGE;
    }

    /**
     * Performs list reviews Command and executes the necessary steps to execute the command.
     *
     * @param assignmentID the id of the assignment
     * @return the tutor, his comment, the students matriculationNumber and the given Grade
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String listReviewsCommand(int assignmentID) throws SemanticsException {
        return findAssignment(assignmentID).listReviews();
    }

    /**
     * Performs the summary tasks Command and executes the necessary steps to execute the command.
     *
     * @return the assignmentID, the assignment name, the average, the number of Solution evaluated and the number of
     * solutions submitted
     */
    public String summaryTasksCommand(){
        var outputBuilder = new StringBuilder();
        if (assignmentSet.isEmpty()) return SUCCESS_WITHOUT_MESSAGE;
        for (Assignment assignment : assignmentSet) {
            outputBuilder.append(assignment.toString()).append(System.lineSeparator());
        }
        return outputBuilder.deleteCharAt(outputBuilder.length() - 1).toString();
    }

    /**
     * Performs the search searchPlagiarism Command and executes the necessary steps to execute the command.
     *
     * @param assignmentID the id of the assignment
     * @return the 2 students matriculationNumber, the longest common substring, the number of appearances and the
     * percentage of accordance
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String searchPlagiarismCommand(int assignmentID) throws SemanticsException {
        return findAssignment(assignmentID).searchPlagiarism();
    }

    /**
     * Performs the markPlagiarism Command and executes the necessary steps to execute the command.
     *
     * @param instructor    the instructor
     * @param matriculation the matriculationNumber
     * @param assignmentID  the assignmentID
     * @return the matriculation and assignmentID if marked as plagiarism
     * or the matriculation, assignmentID and the old grade if its been restored
     * @throws SemanticsException If the command does not meet the requirements
     */
    public String markPlagiarismCommand(Instructor instructor, Matriculation matriculation, int assignmentID) throws SemanticsException {
        //check if instructor exists
        if (!instructorSet.contains(instructor)) throw new SemanticsException("Instructor does not exist");
        var student = findStudent(matriculation);
        return findAssignment(assignmentID).markPlagiarism(student);
    }

    private Tutor findTutor(Tutor tutorToFind) throws SemanticsException {
        return tutorSet.stream().filter(tutor -> tutor.equals(tutorToFind)).findFirst().orElseThrow(() -> new SemanticsException(Errors.TUTOR_DOES_NOT_EXISTS));
    }

    private Student findStudent(Matriculation matriculation) throws SemanticsException {
        return studentSet.stream().filter(student -> student.getMatriculationNumber().equals(matriculation)).findFirst().orElseThrow(() -> new SemanticsException(Errors.NO_SUCH_STUDENT_EXISTS));
    }

    private Assignment findAssignment(int assignmentID) throws SemanticsException {
        return assignmentSet.stream().filter(assignment -> assignment.getIdAssignment() == assignmentID).findFirst().orElseThrow(() -> new SemanticsException(Errors.ASSIGNMENT_DOES_NOT_EXISTS));
    }

}