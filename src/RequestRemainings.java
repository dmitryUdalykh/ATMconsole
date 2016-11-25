import java.util.Map;

/**
 * Created by Test on 10/30/2016.
 */
class RequestRemainings implements AtmCommand {

    private MoneyStorage moneyStorage;
    RequestRemainings(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<BankNote, Integer> execute(String... arguments) {
        return moneyStorage.getBanknotes();
    }
}
