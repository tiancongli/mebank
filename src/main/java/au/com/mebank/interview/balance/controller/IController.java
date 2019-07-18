package au.com.mebank.interview.balance.controller;

import java.util.Scanner;

/**
 * The controller interface
 * @author ltiancong@gmail.com
 * @date 2019/7/18 11:30 AM
 */
public interface IController {
    /**
     * Get scanner from REPL and solve the corresponding calculation
     * @param scanner input scanner
     */
    void calculate(Scanner scanner);
}
