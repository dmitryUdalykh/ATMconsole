/**
 * Created by Test on 10/30/2016.
 */
public class AtmCommandFactory {

    private final MoneyStorage moneyStorage;

    public AtmCommandFactory(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    AtmCommand create(String action) throws AtmStateException {
        // TODO: let' create a enum CommandType with a String attribute keeping a command:
        /*
         enum CommandType {
              REMAININGS("?"),
              ADD("+"),
              WITHDRAW("+"),
              UNDEFINED("");

              private final String command;

              CommandType(String command) {
                  this.command = command;
              }

              public static CommandType getCommandType(String command) {
                    ......
              }


         */
        // TODO have a switch like:
        // switch (CommandType.getCommandType(action))
        switch (CommandType.getCommandType(action)) {
            case REMAININGS:
                return new RequestRemainings(moneyStorage);
            case ADD:
                return new DepositCommand(moneyStorage);
            case WITHDRAW:
                return new WithdrawalCommand(moneyStorage);
            case EXIT:
                System.exit(0);
            default:
                throw new IllegalArgumentException("CAN NOT FIND A COMMAND FOR THIS ACTION " + action);

        }
    }
}
