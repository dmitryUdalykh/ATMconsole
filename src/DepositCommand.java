import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Test on 10/30/2016.
 */
public class DepositCommand implements AtmCommand {
    private MoneyStorage moneyStorage;
    private Map<BankNote, Integer> inMap = new TreeMap<>();

    public DepositCommand(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    //TODO: is not used
    private Integer addNum;

    @Override
    public Map<BankNote, Integer> execute(Object... arguments) throws AtmStateException {
        if (arguments.length != 3) {
            throw new AtmStateException();
        }

        // TODO: I think it's going to fail when we run the ATM from console - the arguments are String and casting to Currency or "int" will throw
        // an exception
        Currency theCurrency = (Currency) arguments[0];
        int noteValue = (int) arguments[1];
        int addNum = (int) arguments[2];
        double noteCheckOne = Math.log10(noteValue);
        double noteCheckTwo = Math.log10(noteValue / 5.0);
        if (noteCheckOne == (int) noteCheckOne || noteCheckTwo == (int) noteCheckTwo) {
            moneyStorage.addNotes(theCurrency, noteValue, addNum);
            inMap.put(new BankNote(theCurrency, noteValue), addNum);
        } else {
            //TODO: let's add some message explaining the problem to look like
            // throw new AtmStateException("explanation of the issue");
            throw new AtmStateException();
        }
        return inMap;
    }
}
