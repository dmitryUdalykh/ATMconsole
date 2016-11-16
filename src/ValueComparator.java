/**
 * Created by Test on 11/04/2016.
 */

import java.util.*;

public class ValueComparator implements Comparator<BankNote> {
    public int compare(BankNote alpha, BankNote beta) {
        if (alpha.valueGetter() > beta.valueGetter()) {
            return 1;
        } else if (alpha.valueGetter() < beta.valueGetter()) {
            return -1;
        } else {
            return 0;
        }
    }
}