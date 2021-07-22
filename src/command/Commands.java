package command;

import errors.Errors;
import errors.SyntaxException;
import errors.TaskException;
import model.logic.ApollonHandler;
import model.logic.CorrectionText;
import model.logic.Grade;
import model.logic.SolutionText;
import model.logic.TextOfAssignment;
import model.logic.users.Instructor;
import model.logic.users.Matriculation;
import model.logic.users.Name;
import model.logic.users.Student;
import model.logic.users.Tutor;

import java.util.List;

/**
 * The enum which holds all the Commands and is used to execute them
 *
 * @author urliz
 * @version 1.0
 */
public enum Commands {
    /**
     * the instructor command
     */
    INSTRUCTOR(CommandParserExecute.INSTRUCTOR_COMMAND, CommandParserExecute.REGEX_INSTRUCTOR_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;


            try {
                resultMessage = apollonHandler.addInstructorCommand(new Instructor(new Name(parameters.get(0))));

            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }
            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * the list-instructors command
     */
    LIST_INSTRUCTORS(CommandParserExecute.LIST_INSTRUCTORS_COMMAND, CommandParserExecute.REGEX_LIST_INSTRUCTORS_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {

            return new Result(Result.ResultType.SUCCESS, apollonHandler.listInstructors());
        }
    },
    /**
     * the tutor command
     */
    TUTOR(CommandParserExecute.TUTOR_COMMAND, CommandParserExecute.REGEX_TUTOR_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.addTutorCommand(new Tutor(new Name(parameters.get(0))));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }
            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * the list-tutors command
     */
    LIST_TUTORS(CommandParserExecute.LIST_TUTORS_COMMAND, CommandParserExecute.REGEX_LIST_TUTORS_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {

            return new Result(Result.ResultType.SUCCESS, apollonHandler.listTutors());
        }
    },
    /**
     * the student command
     */
    STUDENT(CommandParserExecute.STUDENT_COMMAND, CommandParserExecute.REGEX_STUDENT_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.addStudentCommand(new Student(new Name(parameters.get(0)), new Matriculation(Integer.parseInt(parameters.get(1)))));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * the list-students command
     */
    LIST_STUDENTS(CommandParserExecute.LIST_STUDENTS_COMMAND, CommandParserExecute.REGEX_LIST_STUDENTS_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {


            return new Result(Result.ResultType.SUCCESS, apollonHandler.listStudentsCommand());
        }
    },
    /**
     * the assignment command
     */
    ASSIGNMENT(CommandParserExecute.ASSIGNMENT_COMMAND, CommandParserExecute.REGEX_ASSIGNMENT_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {

            String resultMessage;

            try{
                resultMessage = apollonHandler.addAssignmentCommand(new TextOfAssignment(parameters.get(0)));
            }catch (TaskException e){
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }
            return new Result(Result.ResultType.SUCCESS, resultMessage);

        }
    },
    /**
     * the submit command
     */
    SUBMIT(CommandParserExecute.SUBMIT_COMMAND, CommandParserExecute.REGEX_SUBMIT_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.submitCommand(Integer.parseInt(parameters.get(0)), new Matriculation(Integer.parseInt(parameters.get(1))), new SolutionText(parameters.get(2)));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }
            return new Result(Result.ResultType.SUCCESS, resultMessage);

        }
    },
    /**
     * the list-solutions command
     */
    LIST_SOLUTIONS(CommandParserExecute.LIST_SOLUTIONS_COMMAND, CommandParserExecute.REGEX_LIST_SOLUTIONS_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.listSolutionsCommand(Integer.parseInt(parameters.get(0)));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);
        }
    },
    /**
     * the review command
     */
    REVIEW(CommandParserExecute.REVIEW_COMMAND, CommandParserExecute.REGEX_REVIEW_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.reviewCommand(new Tutor(new Name(parameters.get(0))), Integer.parseInt(parameters.get(1)),
                        new Matriculation(Integer.parseInt(parameters.get(2))),
                        Grade.findGradeThroughInt(Integer.parseInt(parameters.get(3))),
                        new CorrectionText(parameters.get(4)));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);

        }
    },
    /**
     * the list-reviews command
     */
    LIST_REVIEWS(CommandParserExecute.LIST_REVIEWS_COMMAND, CommandParserExecute.REGEX_LIST_REVIEWS_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.listReviewsCommand(Integer.parseInt(parameters.get(0)));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);

        }
    },
    /**
     * the searchPlagiarism command
     */
    SEARCH_PLAGIARISM(CommandParserExecute.SEARCH_PLAGIARISM_COMMAND, CommandParserExecute.REGEX_SEARCH_PLAGIARISM_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.searchPlagiarismCommand(Integer.parseInt(parameters.get(0)));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);

        }
    },
    /**
     * the mark plagiarism command
     */
    MARK_PLAGIARISM(CommandParserExecute.MARK_PLAGIARISM_COMMAND, CommandParserExecute.REGEX_MARK_PLAGIARISM_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            String resultMessage;

            try {
                resultMessage = apollonHandler.markPlagiarismCommand(new Instructor(new Name(parameters.get(0)))
                        , new Matriculation(Integer.parseInt(parameters.get(1))), Integer.parseInt(parameters.get(2)));
            } catch (TaskException e) {
                return new Result(Result.ResultType.FAILURE, e.getMessage());
            }

            return new Result(Result.ResultType.SUCCESS, resultMessage);

        }
    },
    /**
     * the summary tasks command
     */
    SUMMARY_TASKS(CommandParserExecute.SUMMARY_TASKS_COMMAND, CommandParserExecute.REGEX_SUMMARY_TASKS_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {
            return new Result(Result.ResultType.SUCCESS, apollonHandler.summaryTasksCommand());
        }
    },
    /**
     * the reset command
     */
    RESET(CommandParserExecute.RESET_COMMAND, CommandParserExecute.REGEX_RESET_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {

            return new Result(Result.ResultType.SUCCESS, apollonHandler.resetCommand());
        }
    },
    /**
     * the quit command
     */
    QUIT(CommandParserExecute.QUIT_COMMAND, CommandParserExecute.REGEX_QUIT_COMMAND) {
        @Override
        public Result executeCommand(List<String> parameters, ApollonHandler apollonHandler) {

            return new Result(Result.ResultType.SUCCESS);
        }
    };


    private final String commandName;
    private final String regexOfCommand;

    /**
     * constructor of a command
     *
     * @param commandName    the name of the command
     * @param regexOfCommand the regex which corresponds to the Command
     */
    Commands(String commandName, String regexOfCommand) {
        this.commandName = commandName;
        this.regexOfCommand = regexOfCommand;
    }

    /**
     * gets a command through the String representation
     *
     * @param commandName the command name
     * @return the found command
     * @throws SyntaxException if there is no command corresponding to the string
     */
    public static Commands getCommand(String commandName) throws SyntaxException {
        for (Commands command : Commands.values()) {
            if (command.commandName.equals(commandName)) return command;
        }
        throw new SyntaxException(Errors.COMMAND_NOT_IMPLEMENTED);
    }

    /**
     * returns the command name
     *
     * @return the command name
     */
    public String getCommandName() {
        return commandName;
    }

    /**
     * returns the regex of a command
     *
     * @return the regex of a command
     */
    public String getRegexOfCommand() {
        return regexOfCommand;
    }

    /**
     * executes a command with its parameters
     *
     * @param parameters     the parameters
     * @param apollonHandler th database on which the command is performed
     * @return a Result which documents if the command succeeded or not and the corresponding message
     */
    public abstract Result executeCommand(List<String> parameters, ApollonHandler apollonHandler);


}

