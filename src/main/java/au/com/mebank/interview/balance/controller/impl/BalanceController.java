package au.com.mebank.interview.balance.controller.impl;

import au.com.mebank.interview.balance.Constant;
import au.com.mebank.interview.balance.controller.IController;
import au.com.mebank.interview.balance.model.Transaction;
import au.com.mebank.interview.balance.service.ITransactionService;
import au.com.mebank.interview.balance.service.impl.TransactionService;
import au.com.mebank.interview.balance.util.Helper;
import au.com.mebank.interview.balance.view.IView;
import au.com.mebank.interview.balance.view.impl.OutputView;
import au.com.mebank.interview.balance.view.impl.BalanceView;
import lombok.Data;

import java.util.Date;
import java.util.Scanner;
import java.util.SortedSet;

/**
 * The balance controller
 * @author ltiancong@gmail.com
 * @date 2019/7/17 9:11 PM
 */
public class BalanceController implements IController {
    private static final BalanceController instance = new
            BalanceController();

    private ITransactionService transactionService;

    @Data
    private static class BalanceResult {
        private double balance;
        private int count;

        /**
         * calculate the relative balance,
         * filter transactions which has a reversal
         * @param transactions
         * @param isIncome
         * @return
         */
        private BalanceResult calculate(SortedSet<Transaction> transactions,
                                        boolean isIncome) {
            if (transactions != null) {
                for (Transaction transaction : transactions) {
                    if (transaction.getRelatedTransaction() == null) {
                        if (isIncome) {
                            balance += transaction.getAmount();
                        } else {
                            balance -= transaction.getAmount();
                        }
                        count++;
                    }
                }
            }
            return this;
        }
    }

    public static BalanceController getInstance() {
        return instance;
    }

    private BalanceController() {
        transactionService = TransactionService.getInstance();
    }

    @Override
    public void calculate(Scanner scanner) {
        String accountId = Helper.getInput(scanner, Constant.ACCOUNT_ID);
        Date from = Helper.getDate(scanner, Constant.FROM);
        Date to = Helper.getDate(scanner, Constant.TO);
        IView balanceView = getBalance(accountId, from, to);
        System.out.println(new OutputView(balanceView));
    }

    public BalanceView getBalance(String accountId, Date
            beginTime, Date endTime) {
        SortedSet<Transaction> outcomes = transactionService
                .getOutcomeByAccount(accountId, beginTime, endTime);
        SortedSet<Transaction> incomes = transactionService
                .getIncomeByAccount(accountId, beginTime, endTime);
        BalanceResult res = new BalanceResult().calculate(outcomes, false).calculate
                (incomes, true);
        return new BalanceView(res.getBalance(), res.getCount());
    }
}
