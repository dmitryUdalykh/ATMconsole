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

    private Integer addNum;

    @Override
    public Map<BankNote, Integer> execute(Object... arguments) throws AtmStateException {
        if (arguments.length != 3) {
            throw new AtmStateException();
        }
        Currency theCurrency = (Currency) arguments[0];
        int noteValue = (int) arguments[1];
        int addNum = (int) arguments[2];
        double noteCheckOne = Math.log10(noteValue);
        double noteCheckTwo = Math.log10(noteValue / 5.0);
        if (noteCheckOne == (int) noteCheckOne || noteCheckTwo == (int) noteCheckTwo) {
            moneyStorage.addNotes(theCurrency, noteValue, addNum);
            inMap.put(new BankNote(theCurrency, noteValue), addNum);
        } else {
            throw new AtmStateException();
        }
        return inMap;
    }
}
