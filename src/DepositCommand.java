import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Test on 10/30/2016.
 */
public class DepositCommand implements AtmCommand {

    // TODO: make them final
    private MoneyStorage moneyStorage;
    // TODO this attribute doesn't have to be a class attribute; can be a local variable in "execute" method if it's needed at all - probably not.
    private Map<BankNote, Integer> inMap = new TreeMap<>();

    public DepositCommand(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<BankNote, Integer> execute(String... arguments) throws AtmStateException {

        if (arguments.length != 3) {
            throw new AtmStateException("WRONG NUMBER OF PARAMETERS");
        }

        // TODO: store arguments[0] to a variable and then re-use it
        // TODO: variables should be camel-cased: "currencyCheck"
        // TODO: create a static method in Currency class similar to what we have in CommandType to return Currency from a String and throw
        // AtmStateException if currency is invalid. Then resuse this code in WithdrawalCommand
        boolean CurrencyCheck = false;
        for (Currency z : Currency.values()) {
            if (arguments[0].equals(z.toString())) {
                CurrencyCheck = true;
            }
        }
        if (!CurrencyCheck) {
            throw new AtmStateException("ILLEGAL CURRENCY TYPE");
        }

        // TODO: add some blank lines below logically separating pieces of code
        // TODO: add a check if arugements 1 and 2 are not numbers - should throw AtmStateException - add a separate test case (new method) for that
        // in AtmTest
        Currency theCurrency = Currency.valueOf(arguments[0]);
        int noteValue = Integer.parseInt(arguments[1]);
        int addNum = Integer.parseInt(arguments[2]);
        double noteCheckOne = Math.log10(noteValue);
        double noteCheckTwo = Math.log10(noteValue / 5.0);
        if (noteCheckOne == (int) noteCheckOne || noteCheckTwo == (int) noteCheckTwo) {
            moneyStorage.addNotes(theCurrency, noteValue, addNum);
            //TODO: consider using Collections.singletonMap
            inMap.put(new BankNote(theCurrency, noteValue), addNum);
        } else {
            throw new AtmStateException("ILLEGAL VALUE OF A BANKNOTE");
        }
        return inMap;
    }
}
