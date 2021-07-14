import core.Pair;
import errors.Errors;
import errors.SyntaxException;
import model.logic.ApollonHandler;
import core.Input;
import core.Output;


import java.util.List;

import command.CommandParserExecute;
import command.Commands;
import command.Result;

/**
 * The class describes a session for a command execution
 *
 * @author uxfob
 * @version 1.0
 */
public class Session {

    private final ApollonHandler apollonHandler;
    private final Output output;
    private final Output errorOutput;
    private final Input input;
    private final CommandParserExecute commandParserExecute;
    private boolean isCodeRunning;

    /**
     * Instantiates a new session
     *
     * @param output      the output consumer
     * @param errorOutput the input supplier
     * @param input       the error output consumer
     */
    public Session(Output output, Output errorOutput, Input input, CommandParserExecute commandParserExecute) {
        this.output = output;
        this.errorOutput = errorOutput;
        this.input = input;
        this.apollonHandler = new ApollonHandler();
        this.commandParserExecute = commandParserExecute;
    }

    /**
     * Method which starts the task
     */
    public void interactive() {
        isCodeRunning = true;
        while (isCodeRunning) {
            processSingleCommand();
        }
    }


    private void processSingleCommand() {
        String inputUser = input.read();
        Pair<String, List<String>> parsedArguments;

        try {
            parsedArguments = commandParserExecute.parseCommand(inputUser);
        } catch (SyntaxException e) {
            errorOutput.output(e.getMessage());
            return;
        }
        String command = parsedArguments.getFirstElement();
        List<String> parameters = parsedArguments.getSecondElement();
        executeSingleCommand(command, parameters);
    }

    private void executeSingleCommand(String commandName, List<String> parameters) {
        Result result;

        try {
            result = Commands.getCommand(commandName).executeCommand(parameters, apollonHandler);
        } catch (SyntaxException e) {
            result = new Result(Result.ResultType.FAILURE, e.getMessage());
        }
        switch (result.getResultType()) {
            case SUCCESS:
                if (result.getMessage() == null) {
                    isCodeRunning = false;
                } else if (result.getMessage().equals(CommandParserExecute.EMPTY_STRING)) {
                    return;
                } else {
                    output.output(result.getMessage());
                }
                break;
            case FAILURE:
                if (result.getMessage() != null) {
                    errorOutput.output(result.getMessage());
                } else {
                    errorOutput.output(Errors.COMMAND_PARAM_WRONG);
                }
                break;
            default:
                throw new IllegalStateException(Errors.COMMAND_NOT_IMPLEMENTED);
        }
    }


}

