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

    public Map<BankNote, Integer> runCommand(String command, Object... arguments) {
        try {
            AtmCommand atmCommand = atmCommandFactory.create(command);
            return atmCommand.execute(arguments);
        } catch (AtmStateException e) {
            System.out.println("ERROR");
        }
        return null;
    }

    public MoneyStorage getMoneyStorage() {
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
                if (lineToRead.length > 1) {
                    for (Currency z : Currency.values()) {
                        if (!lineToRead[1].equals(z)) {
                            throw new AtmStateException();
                        }
                    }
                }
                Object[] arguments = Arrays.copyOfRange(lineToRead, 1, lineToRead.length);
                Map<BankNote, Integer> response = atm.runCommand(command, arguments);

                response.entrySet().forEach(entry ->
                        System.out.println(entry + " " + entry.getValue())
                );
                System.out.println("OK");
            } catch (Exception e) {
                System.out.print("ERROR");
            }
        }
    }
}
