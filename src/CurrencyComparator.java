/**
 * Created by Test on 11/04/2016.
 */

import java.util.*;

class CurrencyComparator implements Comparator<BankNote> {
    public int compare(BankNote one, BankNote two) {
        return -one.getCurrency().compareTo(two.getCurrency());
    }
}
