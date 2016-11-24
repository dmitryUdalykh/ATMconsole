/**
 * Created by Test on 10/30/2016.
 */

import java.lang.Comparable;

class BankNote implements Comparable<BankNote> {
    private final Currency currency;
    private final int value;

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

    Currency getCurrency() {
        return currency;
    }

    // TODO: getters are constructed like "get<VAR_NAME>". For example, if a variable has name "foo" the getter would be "getFoo"; the same with
    // setter - "setFoo". The getter method name should be changed
    int getBanknoteValue() {
        return value;
    }

    @Override
    public int compareTo(BankNote testBankNote) {
        if (currency.equals(testBankNote.getCurrency())) {
            return Integer.compare(value, testBankNote.value);
        } else {
            return currency.compareTo(testBankNote.getCurrency());
        }
    }

    public String toString() {
        return currency + " " + value;
    }
}
