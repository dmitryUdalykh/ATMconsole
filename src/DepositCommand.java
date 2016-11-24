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
        if (arguments.length != 3) {
            throw new AtmStateException("WRONG NUMBER OF PARAMETERS");
        }

        String currencyForDeposit = arguments[0];
        Currency currencyToPut = Currency.getCurrency(currencyForDeposit);

        int valueToPut = AtmUtils.parseInt(arguments[1], new AtmStateException("ILLEGAL TYPING OF VALUE"));
        int numberToPut = AtmUtils.parseInt(arguments[2], new AtmStateException("ILLEGAL TYPING OF NUMBER"));

        ExistingBanknotes.assertBanknote(currencyToPut, valueToPut);
        moneyStorage.addNotes(currencyToPut, valueToPut, numberToPut);
        return Collections.singletonMap(new BankNote(currencyToPut, valueToPut), numberToPut);
    }
}
