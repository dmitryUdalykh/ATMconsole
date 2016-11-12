import com.sun.javafx.collections.MappingChange;

import java.util.*;

/**
 * Created by Test on 10/30/2016.
 */
public class MoneyStorage{

    Comparator<BankNote> bankNoteComparator = new CurrencyComparator().thenComparing(new ValueComparator());
    private Map<BankNote, Integer> notes = new TreeMap<>(bankNoteComparator);
    private Map<Currency, Integer> currencyAmount = new TreeMap<Currency, Integer>();
    private Map<Currency, Integer[][]> currencyValues = new TreeMap<Currency, Integer[][]>();

    public boolean hasNote(Currency hasCurrency, int hasValue){
        return notes.containsKey(new BankNote (hasCurrency, hasValue));
    }

    public boolean hasCurrency(Currency hasCurrency2){
        return currencyAmount.containsKey(hasCurrency2);
    }

    public void addNotes(Currency addCurrency, int addValue, Integer addNumber) {
        BankNote keyToAdd = new BankNote(addCurrency, addValue);
        int[] VTD = valueToDimensions(addNumber);
        Integer[][] valueMatrix = currencyValues.get(addCurrency);
        Integer emptyArray[][]= {{0,0}, {0,0}, {0,0}, {0,0}};
        if(notes.containsKey(keyToAdd)){
            notes.put(keyToAdd, notes.get(keyToAdd) + addNumber);
        }else{
            notes.put(keyToAdd, addNumber);
        }
        if (currencyAmount.containsKey(addCurrency)) {
            Integer amountOne = currencyAmount.get(addCurrency);
            currencyAmount.put(addCurrency, amountOne + addValue * addNumber);
            currencyValues.put(addCurrency, updateValues(addNumber, VTD, valueMatrix));
        } else {
            currencyAmount.put(addCurrency, addNumber * addValue);
            currencyValues.put(addCurrency, updateValues(addNumber, VTD, emptyArray));
        }
    }

    public void pollNotes(Currency pollCurrency, int pollValue, int pollAmount) {
        Integer emptyArray2[][]= {{0,0}, {0,0}, {0,0}, {0,0}};
        BankNote keyToPoll = new BankNote(pollCurrency, pollValue);
        notes.put(keyToPoll, notes.get(keyToPoll) - pollAmount);
        if(notes.get(keyToPoll) == 0){
            notes.remove(keyToPoll);
        }
        currencyAmount.put(pollCurrency,currencyAmount.get(pollCurrency) - pollAmount);
        if(currencyAmount.get(pollCurrency) == 0){
            currencyAmount.remove(pollCurrency);
        }
        int VTD2[] = valueToDimensions(pollValue);
        Integer[][] valueMatrix2 = currencyValues.get(pollCurrency);
        valueMatrix2[ VTD2[0] ][ VTD2[1] ] = valueMatrix2[ VTD2[0] ][ VTD2[1] ] - pollAmount;
        currencyValues.put(pollCurrency, valueMatrix2);
        if(currencyValues.get(pollCurrency) == emptyArray2){
            currencyValues.remove(pollCurrency);
        }
    }

    public int getNoteNumber(BankNote keyFour) {
        Object numberToGet = notes.get(keyFour);
        return (Integer)numberToGet;
    }

    public Integer getCurrencyAmount(Currency NoteKey) {
        Object amountToGet = currencyAmount.get(NoteKey);
        return (Integer)amountToGet;
    }

    public int[] valueToDimensions(int valueToTransform){
        double logarithmic = Math.log10(valueToTransform);
        int[] dims = new int[2];
        if(logarithmic == (int)logarithmic){
            dims[1] = 0;
        }else{
            dims[1] = 1;
        }
        dims[0] = (int)logarithmic;
        return dims;
    }

    private Integer[][] updateValues(int aValue, int[] aField, Integer[][] matrixOfValues){
        matrixOfValues[ aField[0] ][ aField[1] ] += aValue;
        return matrixOfValues;
    }

    public Map<BankNote, Integer> getBanknotes(){
        /*
            [USD:
                [
         */
        return notes;
    }
}

