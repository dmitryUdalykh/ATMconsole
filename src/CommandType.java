/**
 * Created by Test on 11/16/2016.
 */

import java.util.*;

enum CommandType {
    REMAININGS("?"),
    ADD("+"),
    WITHDRAW("-"),
    UNDEFINED("");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommandType(String command) {
        /*
        for (CommandType commandTypeCheck : CommandType.values()) {
            if (command != null && commandTypeCheck.command.equals(command)) {
                return commandTypeCheck;
            }
        }
        return CommandType.UNDEFINED;
        */
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.command.equals(command))
                .findFirst()
                .orElse(UNDEFINED);

    }
}
