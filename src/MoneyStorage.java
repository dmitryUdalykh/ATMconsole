import java.util.Map;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.function.BiFunction;

/**
 * Created by Test on 10/30/2016.
 */
class MoneyStorage {

    private Comparator<BankNote> bankNoteComparator = new CurrencyComparator().thenComparing(new ValueComparator());
    private Map<BankNote, Integer> notes = new TreeMap<>(bankNoteComparator);
    private Map<Currency, Integer> currencyAmount = new TreeMap<Currency, Integer>();

    boolean hasNote(Currency hasCurrency, int hasValue) {
        return notes.containsKey(new BankNote(hasCurrency, hasValue));
    }

    boolean hasCurrency(Currency hasCurrency2) {
        return currencyAmount.containsKey(hasCurrency2);
    }

    void addNotes(Currency addCurrency, int addValue, Integer addNumber) throws AtmStateException{
        ExistingBanknotes.assertBanknote(addCurrency, addValue);
        BankNote keyToAdd = new BankNote(addCurrency, addValue);
        notes.compute(keyToAdd, (bankNote, oldValue) -> oldValue == null ? addNumber : oldValue + addNumber);
        if (currencyAmount.containsKey(addCurrency)) {
            Integer amountOne = currencyAmount.get(addCurrency);
            currencyAmount.put(addCurrency, amountOne + addValue * addNumber);
        } else {
            currencyAmount.put(addCurrency, addNumber * addValue);
        }
    }

    void pollNotes(Currency pollCurrency, int pollValue, int pollNumber) {
        BankNote keyToPoll = new BankNote(pollCurrency, pollValue);
        notes.put(keyToPoll, notes.get(keyToPoll) - pollNumber);
        if (notes.get(keyToPoll) == 0) {
            notes.remove(keyToPoll);
        }
        currencyAmount.put(pollCurrency, currencyAmount.get(pollCurrency) - pollNumber);
        if (currencyAmount.get(pollCurrency) == 0) {
            currencyAmount.remove(pollCurrency);
        }
    }

    int getNoteNumber(BankNote banknoteKey) {
        Object numberToGet = notes.get(banknoteKey);
        return (Integer) numberToGet;
    }

    Integer getCurrencyAmount(Currency currencyKey) {
        Object amountToGet = currencyAmount.get(currencyKey);
        return (Integer) amountToGet;
    }

    Map<BankNote, Integer> getBanknotes() {
        return notes;
    }
}

