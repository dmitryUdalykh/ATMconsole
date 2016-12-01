/**
 * Created by Test on 10/30/2016.
 */

import java.util.Map;

class RequestRemainings implements AtmCommand {
    private final MoneyStorage moneyStorage;

    RequestRemainings(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<BankNote, Integer> execute(String... arguments) {
        return moneyStorage.getBanknotes();
    }
}
