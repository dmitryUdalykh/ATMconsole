import java.io.Serializable;

/**
 * Created by Test on 08/27/2016.
 */
public class Money implements Serializable, Comparable {
    private String aa;
    private int bb;
    private int cc;
    Money(String cur, int val, int num){
        this.aa=cur;
        this.bb=val;
        this.cc=num;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (bb != money.bb&&cc != money.cc) return false;
        return aa != null ? aa.equals(money.aa) : money.aa == null;
    }
    @Override
    public int hashCode() {
        int result = aa != null ? aa.hashCode() : 0;
        result = 64 * result + bb;
        result = 32 * result + cc;
        return result;
    }
    public String getCurrency() {return aa;}
    public int getValue(){return bb;}
    public int getNumber(){
        return cc;
    }
    public void setCurrency(String a1) {aa=a1;}
    public void setValue(int b1){bb=b1;}
    public void setNumber(int c1){cc=c1;}
    public int compareTo(Object oo){
        Money tt=(Money)oo;
        return aa.compareTo(tt.getCurrency());
    }
}
