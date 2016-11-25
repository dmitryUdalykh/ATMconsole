/**
 * Created by Test on 11/16/2016.
 */

import java.util.*;

enum CommandType {
    REMAININGS("?"),
    ADD("+"),
    WITHDRAW("-"),
    //TODO: let's remove this UNDEFINED
    UNDEFINED("");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType getCommandType(String command) {
        //TODO: clean up commented out code
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
                // TODO: let's have .orElseThrow(..) instead and throw AtmStateException from here like in Currency. Don't forget to fix the unit test
                .orElse(UNDEFINED);

    }
}
