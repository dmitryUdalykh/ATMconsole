import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Test on 10/30/2016.
 */
public class FooTest {

    @Before
    public void setUp() {
        System.out.println("before!!");
    }

    @Test
    public void shouldAddTwoNumbers() {
        Assert.assertEquals(10, Foo.add(3, 7));
    }

    @Test
    public void shouldMultiplyZero() {
        Assert.assertEquals(0, Foo.multiply(0, 7));
    }
    @Test
    public void shouldMultiplyTwoNumbers() {
        Assert.assertEquals(10, Foo.multiply(2, 5));
    }

}
