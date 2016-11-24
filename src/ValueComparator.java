/**
 * Created by Test on 11/04/2016.
 */

import java.util.*;

class ValueComparator implements Comparator<BankNote> {
    //TODO: add missing @Override annotation
    public int compare(BankNote alpha, BankNote beta) {
        // TODO can be simplfied:
        // return Integer.compare(alpha.getValue(), beta.getValue());

        if (alpha.getBanknoteValue() > beta.getBanknoteValue()) {
            return 1;
        } else if (alpha.getBanknoteValue() < beta.getBanknoteValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
