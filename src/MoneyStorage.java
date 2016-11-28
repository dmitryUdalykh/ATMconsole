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

    void addNotes(Currency addCurrency, int addValue, Integer addNumber) throws AtmStateException {
        ExistingBanknotes.assertBanknote(addCurrency, addValue);
        BankNote keyToAdd = new BankNote(addCurrency, addValue);
        notes.compute(keyToAdd, (bankNote, oldNumber) -> oldNumber == null ? addNumber : oldNumber + addNumber);
        currencyAmount.compute(addCurrency, (banknoteKey, integerNumber) -> integerNumber == null ? addValue * addNumber : integerNumber + addNumber * addValue);
    }

    void pollNotes(Currency pollCurrency, int pollValue, int pollNumber) {
        BankNote keyToPoll = new BankNote(pollCurrency, pollValue);
        notes.compute(keyToPoll, (bankNote, oldNumber) -> oldNumber == null ? 0 : oldNumber - pollNumber);
        currencyAmount.compute(pollCurrency, (currencyKey, oldAmount) -> oldAmount == null ? 0 : oldAmount - pollNumber);

    }

    int getNoteNumber(BankNote banknoteKey) throws AtmStateException {
        if (banknoteKey != null) {
            return notes.get(banknoteKey);
        } else {
            throw new AtmStateException("NULL INSTEAD OF A BANKNOTE");
        }
    }

    int getCurrencyAmount(Currency currencyKey) throws AtmStateException {
        if (currencyKey != null) {
            return currencyAmount.get(currencyKey);
        } else {
            throw new AtmStateException("ILLEGAL CURRENCY TYPING");
        }
    }

    Map<BankNote, Integer> getBanknotes() {
        return notes;
    }
}

