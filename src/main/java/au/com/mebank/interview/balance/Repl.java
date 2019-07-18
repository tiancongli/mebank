package au.com.mebank.interview.balance;

import au.com.mebank.interview.balance.controller.IController;
import au.com.mebank.interview.balance.controller.impl.BalanceController;

import java.util.Scanner;


/**
 * The repl
 * @author ltiancong@gmail.com
 * @date 2019/7/17 10:39 PM
 */
public class Repl {
    private static Repl instance = new Repl();

    private IController balanceController;

    public static Repl getInstance() {
        return instance;
    }

    private Repl() {
        balanceController = BalanceController.getInstance();
    }

    private enum Option {
        CALCULATE_BALANCE, QUIT
    }

    public void start() {
        try(Scanner scanner = new Scanner(System.in)) {
            while (true) {
                Option option = getOption(scanner);
                if (option == Option.QUIT) {
                    break;
                }
                balanceController.calculate(scanner);
            }
        }
    }

    private Option getOption(Scanner scanner) {
        System.out.println("> Calculate balance or quit? " +
                "[1.Calculate|2.Quit]");
        System.out.print("> ");
        String option = scanner.nextLine();
        if (option.startsWith("2") |
                option.toUpperCase().startsWith("Q")) {
            return Option.QUIT;
        }
        return Option.CALCULATE_BALANCE;
    }
}

