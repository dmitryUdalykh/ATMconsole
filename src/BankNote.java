/**
 * Created by Test on 10/30/2016.
 */

import java.lang.Comparable;

public class BankNote implements Comparable<BankNote> {
    //TODO: make them final
    private Currency currency;
    private int value;

    BankNote(Currency thisCurrency, int thisValue) {
        this.currency = thisCurrency;
        this.value = thisValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankNote bankNoteCheck = (BankNote) o;
        if (value != bankNoteCheck.value) return false;
        return currency != null ? currency.equals(bankNoteCheck.currency) : bankNoteCheck.currency != null;
    }

    @Override
    public int hashCode() {
        int result = currency != null ? currency.hashCode() : 0;
        result = 128 * result + value;
        return result;
    }

    public Currency getCurrency() {
        return currency;
    }

    // TODO: Getters in Java are named "getSomething" so in this case it should be names as "getValue()
    public int valueGetter() {
        return value;
    }

    //TODO: misses @Override annotation
    public int compareTo(BankNote testBankNote) {
        if (currency.equals(testBankNote.getCurrency())) {
            // TODO: can be replaced with
            // return Integer.compare(value, testBankNote.value);
            if (value > testBankNote.valueGetter()) {
                return 1;
            } else if (value < testBankNote.valueGetter()) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return currency.compareTo(testBankNote.getCurrency());
        }
    }

    public String toString() {
        return currency + " " + value;
    }
}
