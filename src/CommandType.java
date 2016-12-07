/**
 * Created by Test on 11/16/2016.
 */

import java.util.Arrays;

enum CommandType {
    REMAININGS("?"),
    ADD("+"),
    WITHDRAW("-");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommandType(String command) throws AtmStateException {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new AtmStateException("UNIDENTIFIED COMMAND"));
    }
}
