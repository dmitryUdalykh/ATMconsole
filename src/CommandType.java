/**
 * Created by Test on 11/16/2016.
 */
//TODO: doesn't have to be public
public enum CommandType {
    REMAININGS("?"),
    ADD("+"),
    WITHDRAW("-"),
    EXIT("EXIT"),       // TODO: I don't think EXIT should be on the ATM core commands - real ATMs don't have such a button - let's remove it
    UNDEFINED("");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    // TODO: write a unit test for this method
    // TODO: why it throws AtmStateException ?
    public static CommandType getCommandType(String command) throws AtmStateException {
        // TODO: Let's add a check for "null"
        // TODO: "CTcheck" - is a local variable - all the local variables. Check here http://javascript.crockford.com/javacodeconventions.pdf
        // "9 - Naming Conventions". the variable name should start with a lowcase letter and what "CTcheck" means?
        for (CommandType CTcheck : CommandType.values()) {
            if (CTcheck.command.equals(command)) {
                return CTcheck;
            }
        }
        return CommandType.UNDEFINED;

        /*

        TODO: after writing a test try out this Java 8 based implementation (check if it works) and understand how it works
        return Arrays.stream(CommandType.values())
                    .filter(commandType -> commandType.command.equals(command))
                    .findFirst()
                    .orElse(UNDEFINED);
        */

    }
}
