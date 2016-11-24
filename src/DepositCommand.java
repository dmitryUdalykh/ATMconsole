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
        // TODO: instead of calling 2 lines do "Currency currencyToPut = Currency.getCurrency(currencyForDeposit)"
        Currency.checkCurrency(currencyForDeposit);
        Currency currencyToPut = Currency.valueOf(currencyForDeposit);

        // TODO: create a utility class names AtmUtils. Create a static method "parseInt" taking 2 parameters - the value to parse and error
        // message if the value can not be parsed. return integer if the value is ok and throw AtmStateException with the second parameter
        // if the value is not an integer
        // Re-use this method wherever int parsing is needed
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

        //TODO: let's add a method "assertBanknote(currency, value)" to ExistingBanknotes class and throw AtmStateException from there
        ExistingBanknotes exBankOne = new ExistingBanknotes();
        Set<BankNote> bankSet = exBankOne.getExistingBanknotes();

        if (bankSet.contains(new BankNote(currencyToPut, valueToPut))) {
            moneyStorage.addNotes(currencyToPut, valueToPut, numberToPut);
            return Collections.singletonMap(new BankNote(currencyToPut, valueToPut), numberToPut);
        } else {
            throw new AtmStateException("NOT EXISTING BANKNOTE");
        }
    }
}
