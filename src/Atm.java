/**
 * Created by Test on 10/30/2016.
 */

import java.io.Console;
import java.util.Arrays;
import java.util.Map;

public class Atm {
    private AtmCommandFactory atmCommandFactory;
    private MoneyStorage moneyStorage;

    Atm() {
        moneyStorage = new MoneyStorage();
        atmCommandFactory = new AtmCommandFactory(moneyStorage);
    }

    //TODO: doesn't have to be public
    Map<BankNote, Integer> runCommand(String command, String... arguments) throws AtmStateException {
        AtmCommand atmCommand = atmCommandFactory.create(command);
        return atmCommand.execute(arguments);

        // TODO: an exception doesn't have to be caught here; it should be thrown from the method; let's remove "catch" and add
        // "throws AtmStateException" to the method signature
    }

    MoneyStorage getMoneyStorage() {
        return moneyStorage;
    }

    public static void main(String[] args) {
        Console atmConsole = System.console();

        Atm atm = new Atm();
        while (true) {
            // pass parameters from console to ATM
            try {
                String[] lineToRead = atmConsole.readLine().split("\\s");
                String command = lineToRead[0];

                if (command.equalsIgnoreCase("QUIT")) {
                    System.exit(0);
                }

                // TODO: this validation should be in the commands because if we leave it here we need to duplicate this validation in
                // other places - if, for example, we read data not from the console but from a file

                String[] arguments = Arrays.copyOfRange(lineToRead, 1, lineToRead.length);
                Map<BankNote, Integer> response = atm.runCommand(command, arguments);

                response.entrySet().forEach(entry ->
                        System.out.println(entry + " " + entry.getValue())
                );
                System.out.println("OK");
            } catch (AtmStateException e) {     // TODO: should be AtmStateException
                System.out.print("ERROR");
            }
        }
    }
}
