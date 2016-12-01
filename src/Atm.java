/**
 * Created by Test on 10/30/2016.
 */

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

class Atm {
    private static final String QUIT_WORD = "QUIT";
    private static final String OK_WORD = "OK";
    private static final String ERROR_WORD = "ERROR";

    private AtmCommandFactory atmCommandFactory;

    Atm() {
        MoneyStorage moneyStorage = new MoneyStorage();
        atmCommandFactory = new AtmCommandFactory(moneyStorage);
    }

    Map<BankNote, Integer> runCommand(String command, String... arguments) throws AtmStateException {
        AtmCommand atmCommand = atmCommandFactory.create(command);
        return atmCommand.execute(arguments);
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        Atm atm = new Atm();
        while (true) {
            // pass parameters from console to ATM
            try {
                final String input = scanner.nextLine();
                String[] lineToRead = input.split("\\s");
                String command = lineToRead[0];
                String[] arguments = Arrays.copyOfRange(lineToRead, 1, lineToRead.length);
                if (command.equalsIgnoreCase(QUIT_WORD)) {
                    System.exit(0);
                }

                Map<BankNote, Integer> response = atm.runCommand(command, arguments);
                response.entrySet().forEach(entry ->
                        System.out.println(entry + " " + entry.getValue())
                );
                System.out.println(OK_WORD);
            } catch (AtmStateException e) {
                System.out.println(ERROR_WORD);
            }
        }
    }
}
