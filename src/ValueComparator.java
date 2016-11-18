/**
 * Created by Test on 11/04/2016.
 */

import java.util.*;

class ValueComparator implements Comparator<BankNote> {
    public int compare(BankNote alpha, BankNote beta) {
        if (alpha.getBanknoteValue() > beta.getBanknoteValue()) {
            return 1;
        } else if (alpha.getBanknoteValue() < beta.getBanknoteValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}