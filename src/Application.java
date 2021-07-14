import java.util.Scanner;

import command.CommandParserExecute;
import core.Input;
import core.Output;

/**
 * The Application. Creates the needed instances and runs the interactive command processing.
 *
 * @author urliz
 * @version 1.o
 */
public class Application {


    private static final Output OUTPUT = System.out::println;
    private static final String ERROR = "Error, ";
    private static final Input INPUT = () -> new Scanner(System.in).nextLine();
    private static final Output ERROR_OUTPUT = message -> System.out.println(ERROR + message);

    /**
     * Utility class constructor
     */
    private Application() {
        throw new IllegalStateException("Utility-class constructor.");
    }

    /**
     * The main entry point to the application.
     *
     * @param args the args but there are none
     */
    public static void main(String[] args) {

        var session = new Session(OUTPUT, ERROR_OUTPUT, INPUT, new CommandParserExecute());
        session.interactive();

    }


}
