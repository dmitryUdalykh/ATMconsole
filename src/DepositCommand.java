import java.util.Collections;
import java.util.Map;


/**
 * Created by Test on 10/30/2016.
 */
class DepositCommand implements AtmCommand {

    private final MoneyStorage moneyStorage;

    DepositCommand(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<BankNote, Integer> execute(String... arguments) throws AtmStateException {
        AtmUtils.assertLengthCheck(3, arguments);
        Currency currencyToPut = Currency.getCurrency(arguments[0]);
        int valueToPut = AtmUtils.parseInt(arguments[1], "ILLEGAL TYPING OF VALUE");
        int numberToPut = AtmUtils.parseInt(arguments[2], "ILLEGAL TYPING OF NUMBER");

        ExistingBanknotes.assertBanknote(currencyToPut, valueToPut);
        moneyStorage.addNotes(currencyToPut, valueToPut, numberToPut);

        return Collections.singletonMap(new BankNote(currencyToPut, valueToPut), numberToPut);
    }
}
