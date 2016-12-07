/**
 * Created by Test on 10/30/2016.
 */
class AtmCommandFactory {

    private final MoneyStorage moneyStorage;

    AtmCommandFactory(MoneyStorage moneyStorage) {
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
            default:
                throw new AtmStateException("Cannot find a command for this action " + action);
        }
    }
}
