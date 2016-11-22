/**
 * Created by Test on 11/18/2016.
 */

import java.util.Set;
import java.util.TreeSet;

class ExistingBanknotes {
    private final Set<BankNote> exBank = new TreeSet<>();

    ExistingBanknotes() {
        exBank.add(new BankNote(Currency.USD, 1));
        exBank.add(new BankNote(Currency.USD, 2));
        exBank.add(new BankNote(Currency.USD, 5));
        exBank.add(new BankNote(Currency.USD, 10));
        exBank.add(new BankNote(Currency.USD, 20));
        exBank.add(new BankNote(Currency.USD, 50));
        exBank.add(new BankNote(Currency.USD, 100));
        exBank.add(new BankNote(Currency.EUR, 5));
        exBank.add(new BankNote(Currency.EUR, 10));
        exBank.add(new BankNote(Currency.EUR, 20));
        exBank.add(new BankNote(Currency.EUR, 50));
        exBank.add(new BankNote(Currency.EUR, 100));
        exBank.add(new BankNote(Currency.EUR, 200));
        exBank.add(new BankNote(Currency.EUR, 500));
        exBank.add(new BankNote(Currency.JPY, 1000));
        exBank.add(new BankNote(Currency.JPY, 5000));
        exBank.add(new BankNote(Currency.JPY, 10000));
        exBank.add(new BankNote(Currency.CHF, 10));
        exBank.add(new BankNote(Currency.CHF, 20));
        exBank.add(new BankNote(Currency.CHF, 50));
        exBank.add(new BankNote(Currency.CHF, 100));
        exBank.add(new BankNote(Currency.CHF, 200));
        exBank.add(new BankNote(Currency.CHF, 1000));
        exBank.add(new BankNote(Currency.RUR, 10));
        exBank.add(new BankNote(Currency.RUR, 50));
        exBank.add(new BankNote(Currency.RUR, 100));
        exBank.add(new BankNote(Currency.RUR, 500));
        exBank.add(new BankNote(Currency.RUR, 1000));
        exBank.add(new BankNote(Currency.RUR, 5000));
    }

    Set<BankNote> getExistingBanknotes() {
        return exBank;
    }
}
