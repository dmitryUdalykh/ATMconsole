import java.util.Map;

/**
 * Created by Test on 10/30/2016.
 */
public class RequestRemainings implements AtmCommand {

    private MoneyStorage moneyStorage;

    public RequestRemainings(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<BankNote, Integer> execute(Object... arguments) {
        return moneyStorage.getBanknotes();
    }
}
