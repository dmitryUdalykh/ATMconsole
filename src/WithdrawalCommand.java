import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

/**
 * Created by Test on 10/30/2016.
 */
class WithdrawalCommand implements AtmCommand {

    private final MoneyStorage moneyStorage;

    WithdrawalCommand(MoneyStorage moneyStorage) {
        this.moneyStorage = moneyStorage;
    }

    @Override
    public Map<BankNote, Integer> execute(String... arguments) throws AtmStateException {
        Map<BankNote, Integer> outMap = new TreeMap<>();

        Set<BankNote> exBankForWithdrawal = new ExistingBanknotes().getExistingBanknotes();

        if (arguments.length != 2) {
            throw new AtmStateException("WRONG NUMBER OF PARAMETERS");
        }

        String currencyForWithdrawal = arguments[0];

        // TODO: instead of calling 2 lines do "Currency currencyToPut = Currency.getCurrency(currencyForDeposit)"
        Currency.checkCurrency(currencyForWithdrawal);
        Currency currencyToPoll = Currency.valueOf(currencyForWithdrawal);
        Map<BankNote, Integer> numbersMap = new HashMap<>();

        //TODO: used AtmUtils (see comment in DepositCommand)
        int amountToGet;
        try {
            amountToGet = Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            throw new AtmStateException("ILLEGAL TYPING OF AMOUNT");
        }

        //Checking whether the money storage contains this currency
        if (!moneyStorage.hasCurrency(currencyToPoll)) {
            throw new AtmStateException("NO SUCH CURRENCY");
        }

        //Checking whether it is possible to take a certain amount
        int currencyAmount = moneyStorage.getCurrencyAmount(currencyToPoll);
        if (currencyAmount >= amountToGet) {
            int checkAmount = amountToGet;
            //TODO: should be declared where it's assigned first time
            int divisionCheck;
            for (BankNote banknoteToCheck : exBankForWithdrawal) {
                if (banknoteToCheck.getCurrency().equals(currencyToPoll) && checkAmount >= banknoteToCheck.getBanknoteValue()) {
                    int valueToCheck = banknoteToCheck.getBanknoteValue();
                    if (moneyStorage.hasNote(currencyToPoll, valueToCheck)) {
                        int remainingNumber = moneyStorage.getNoteNumber(new BankNote(currencyToPoll, valueToCheck));
                        divisionCheck = checkAmount / valueToCheck;
                        if (remainingNumber > divisionCheck) {
                            checkAmount = checkAmount - valueToCheck * divisionCheck;
                            numbersMap.put(banknoteToCheck, divisionCheck);
                        } else {
                            checkAmount = checkAmount - valueToCheck * remainingNumber;
                            numbersMap.put(banknoteToCheck, remainingNumber);
                        }
                    }
                }
            }

            //Withdrawal operation
            if (checkAmount == 0) {
                for (BankNote banknoteToGet : exBankForWithdrawal) {
                    int valueToPoll = banknoteToGet.getBanknoteValue();
                    if (numbersMap.containsKey(banknoteToGet) && numbersMap.get(banknoteToGet) != 0) {
                        int numberToPoll = numbersMap.get(banknoteToGet);
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

        //TODO: add only one throw new AtmStateException here isnetad of the two above
    }
}

