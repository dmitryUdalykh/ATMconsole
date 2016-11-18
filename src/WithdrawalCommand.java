import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Test on 10/30/2016.
 */
public class WithdrawalCommand implements AtmCommand {

    // TODO: make final
    private MoneyStorage moneyStorage;

    public WithdrawalCommand(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    // TODO: can be a local method variable
    private Map<BankNote, Integer> outMap = new TreeMap<>();

    @Override
    public Map<BankNote, Integer> execute(String... arguments) throws AtmStateException {
        if (arguments.length != 2) {
            throw new AtmStateException("WRONG NUMBER OF PARAMETERS");
        }

        // TODO: move validation to "Currency" class
        boolean CurrencyCheck2 = false;
        for (Currency z : Currency.values()) {
            if (arguments[0].equals(z.toString())) {
                CurrencyCheck2 = true;
            }
        }
        if (!CurrencyCheck2) {
            throw new AtmStateException("ILLEGAL CURRENCY TYPE");
        }


        // TODO: add check that aruments[1] is a valid integer, throw AtmStateException otherwise
        Currency currencyToPoll = Currency.valueOf(arguments[0]);
        int amountToGet = Integer.parseInt(arguments[1]);

        //Checking whether the money storage contains this currency
        if (!moneyStorage.hasCurrency(currencyToPoll)) {
            throw new AtmStateException("NO SUCH CURRENCY");
        }

        int currencyAmount = moneyStorage.getCurrencyAmount(currencyToPoll);
        if (currencyAmount >= amountToGet) {
            //Checking whether it is possible to take a certain amount
            int valuesArray[] = {5000, 1000, 500, 100, 50, 10, 5, 1};
            int checkAmount = amountToGet;
            int[][] values2DArray = new int[4][2];
            int divisionCheck;
            for (int valueToCheck : valuesArray) {
                if (checkAmount >= valueToCheck) {
                    if (moneyStorage.hasNote(currencyToPoll, valueToCheck)) {
                        int remainingValue = moneyStorage.getNoteNumber(new BankNote(currencyToPoll, valueToCheck));
                        divisionCheck = checkAmount / valueToCheck;
                        if (remainingValue > divisionCheck) {
                            checkAmount = checkAmount - valueToCheck * divisionCheck;
                        } else {
                            checkAmount = checkAmount - valueToCheck * remainingValue;
                            divisionCheck = remainingValue;
                        }
                        int[] valPlace = moneyStorage.valueToDimensions(valueToCheck);
                        values2DArray[valPlace[0]][valPlace[1]] = divisionCheck;
                    }
                }
            }

            //Withdrawal operation
            if (checkAmount == 0) {
                for (int valueToPoll : valuesArray) {
                    int[] valueToPollDimensions = moneyStorage.valueToDimensions(valueToPoll);
                    int numberToPoll = values2DArray[valueToPollDimensions[0]][valueToPollDimensions[1]];
                    if (numberToPoll != 0) {
                        if (moneyStorage.hasNote(currencyToPoll, valueToPoll)) {
                            moneyStorage.pollNotes(currencyToPoll, valueToPoll, numberToPoll);
                            outMap.put(new BankNote(currencyToPoll, valueToPoll), numberToPoll);
                        }
                    }
                }
                return outMap;
            } else {
                throw new AtmStateException("NOT POSSIBLE WITH THE AVAILABLE BANKNOTES");
            }
        } else {
            throw new AtmStateException("NOT POSSIBLE WITH THE AVAILABLE BANKNOTES");
        }
    }
}

