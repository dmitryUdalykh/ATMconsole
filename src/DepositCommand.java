import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


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
        Currency theCurrency = Currency.valueOf(currencyForDeposit);

        int noteValue;
        try {
            noteValue = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new AtmStateException("ILLEGAL TYPING OF VALUE");
        }

        int addNum;
        try {
            addNum = Integer.parseInt(arguments[2]);
        } catch (NumberFormatException e) {
            throw new AtmStateException("ILLEGAL TYPING OF NUMBER");
        }

        double noteCheckOne = Math.log10(noteValue);
        double noteCheckTwo = Math.log10(noteValue / 5.0);
        if (noteCheckOne == (int) noteCheckOne || noteCheckTwo == (int) noteCheckTwo) {
            moneyStorage.addNotes(theCurrency, noteValue, addNum);
            return Collections.singletonMap(new BankNote(theCurrency, noteValue), addNum);
        } else {
            throw new AtmStateException("ILLEGAL VALUE OF A BANKNOTE");
        }
    }
}
