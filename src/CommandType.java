/**
 * Created by Test on 11/16/2016.
 */
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
        for (CommandType commandTypeCheck : CommandType.values()) {
            if (command != null && commandTypeCheck.command.equals(command)) {
                return commandTypeCheck;
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
