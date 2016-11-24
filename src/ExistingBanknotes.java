/**
 * Created by Test on 11/18/2016.
 */

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonMap;

class ExistingBanknotes {
    //TODO: can be static
    private static final Set<BankNote> exBank = new TreeSet<>(new CurrencyComparator().thenComparing(new ValueComparator()).reversed());


    // TODO: check if this JDK8 approach works. Advantage - no currency duplication
    static {
        asList(
            singletonMap(Currency.USD, asList(100, 50, 20, 10, 2, 1)),
            singletonMap(Currency.RUR, asList(5000, 1000, 500, 100, 50, 10))
            // TODO: add other currencies
        ).forEach(
            currencyListMap -> currencyListMap.forEach(
                    (currency, values) -> values.forEach(
                            value -> exBank.add(new BankNote(currency, value))
                    )
            )
        );
    }

    ExistingBanknotes() {
        // observe the problem - each currency is repeated a bunch of times
        exBank.add(new BankNote(Currency.USD, 100));
        exBank.add(new BankNote(Currency.USD, 50));
        exBank.add(new BankNote(Currency.USD, 20));
        exBank.add(new BankNote(Currency.USD, 10));
        exBank.add(new BankNote(Currency.USD, 5));
        exBank.add(new BankNote(Currency.USD, 2));
        exBank.add(new BankNote(Currency.USD, 1));
        exBank.add(new BankNote(Currency.RUR, 5000));
        exBank.add(new BankNote(Currency.RUR, 1000));
        exBank.add(new BankNote(Currency.RUR, 500));
        exBank.add(new BankNote(Currency.RUR, 100));
        exBank.add(new BankNote(Currency.RUR, 50));
        exBank.add(new BankNote(Currency.RUR, 10));
        exBank.add(new BankNote(Currency.JPY, 10000));
        exBank.add(new BankNote(Currency.JPY, 5000));
        exBank.add(new BankNote(Currency.JPY, 1000));
        exBank.add(new BankNote(Currency.EUR, 500));
        exBank.add(new BankNote(Currency.EUR, 200));
        exBank.add(new BankNote(Currency.EUR, 100));
        exBank.add(new BankNote(Currency.EUR, 50));
        exBank.add(new BankNote(Currency.EUR, 20));
        exBank.add(new BankNote(Currency.EUR, 10));
        exBank.add(new BankNote(Currency.EUR, 5));
        exBank.add(new BankNote(Currency.CHF, 1000));
        exBank.add(new BankNote(Currency.CHF, 200));
        exBank.add(new BankNote(Currency.CHF, 100));
        exBank.add(new BankNote(Currency.CHF, 50));
        exBank.add(new BankNote(Currency.CHF, 20));
        exBank.add(new BankNote(Currency.CHF, 10));
    }

    //TODO: can be static
    Set<BankNote> getExistingBanknotes() {
        return exBank;
    }
}
