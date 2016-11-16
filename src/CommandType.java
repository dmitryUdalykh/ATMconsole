/**
 * Created by Test on 11/16/2016.
 */
public enum CommandType {
    REMAININGS("?"),
    ADD("+"),
    WITHDRAW("-"),
    EXIT("EXIT"),
    UNDEFINED("");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommandType(String command) throws AtmStateException {
        CommandType[] CTlavues = CommandType.values();
        for (CommandType CTcheck : CTlavues) {
            if (CTcheck.command.equals(command)) {
                return CTcheck;
            }
        }
        return CommandType.UNDEFINED;
    }
}
