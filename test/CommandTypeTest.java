/**
 * Created by Test on 11/25/2016.
 */

import org.junit.Assert;
import org.junit.Test;

public class CommandTypeTest {

    @Test
    public void testAtmCommand() throws AtmStateException {
        CommandType addCheck = CommandType.getCommandType("+");
        Assert.assertEquals(addCheck, CommandType.ADD);

        CommandType withdrawalCheck = CommandType.getCommandType("-");
        Assert.assertEquals(withdrawalCheck, CommandType.WITHDRAW);

        CommandType remainCheck = CommandType.getCommandType("?");
        Assert.assertEquals(remainCheck, CommandType.REMAININGS);
    }

    @Test(expected = AtmStateException.class)
    public void testCommandException() throws AtmStateException {
        CommandType.getCommandType("Abcde");
    }

    @Test(expected = AtmStateException.class)
    public void testCommandNull() throws AtmStateException {
        CommandType.getCommandType(null);
    }
}
