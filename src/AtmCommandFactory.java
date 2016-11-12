/**
 * Created by Test on 10/30/2016.
 */
public class AtmCommandFactory {

    private final MoneyStorage moneyStorage;

    public AtmCommandFactory(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    AtmCommand create(String action) throws AtmStateException {
        switch (action) {
            case "?":
                return new RequestRemainings(moneyStorage);
            case "+":
                return new DepositCommand(moneyStorage);
            case "-":
                return new WithdrawalCommand(moneyStorage);
            default:
                throw new IllegalArgumentException("Can not find command for action " + action);

        }
    }
}
