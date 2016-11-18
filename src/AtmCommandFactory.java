/**
 * Created by Test on 10/30/2016.
 */
//TODO: doesn't have to be public - fix all such cases when "public" modifier can be left out
public class AtmCommandFactory {

    private final MoneyStorage moneyStorage;

    public AtmCommandFactory(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    AtmCommand create(String action) throws AtmStateException {


        switch (CommandType.getCommandType(action)) {
            case REMAININGS:
                return new RequestRemainings(moneyStorage);
            case ADD:
                return new DepositCommand(moneyStorage);
            case WITHDRAW:
                return new WithdrawalCommand(moneyStorage);
            // TODO: get rid of this - the factory should not do system exit - it should just create commands
            case EXIT:
                System.exit(0);
            default:
                //TODO: let's write a normal English phrase - not in capital letters: "Can not find...."
                // TODO: should throw AtmStateException
                throw new IllegalArgumentException("CAN NOT FIND A COMMAND FOR THIS ACTION " + action);

        }
    }
}
