import java.util.Collections;
import java.util.Map;
import java.util.Set;


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
        Currency.checkCurrency(currencyForDeposit);
        Currency currencyToPut = Currency.valueOf(currencyForDeposit);

        int valueToPut;
        try {
            valueToPut = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new AtmStateException("ILLEGAL TYPING OF VALUE");
        }

        int numberToPut;
        try {
            numberToPut = Integer.parseInt(arguments[2]);
        } catch (NumberFormatException e) {
            throw new AtmStateException("ILLEGAL TYPING OF NUMBER");
        }

        ExistingBanknotes exBankOne = new ExistingBanknotes();
        Set<BankNote> bankSet = exBankOne.getExistingBanknotes();
        if (bankSet.contains(new BankNote(currencyToPut, valueToPut))) {
            moneyStorage.addNotes(currencyToPut, valueToPut, numberToPut);
            return Collections.singletonMap(new BankNote(currencyToPut, valueToPut), numberToPut);
        } else {
            throw new AtmStateException("ILLEGAL VALUE OF A BANKNOTE");
        }
    }
}
