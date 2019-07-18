package au.com.mebank.interview.balance.controller;

import au.com.mebank.interview.balance.Constant;
import au.com.mebank.interview.balance.controller.impl.BalanceController;
import au.com.mebank.interview.balance.model.Transaction;
import au.com.mebank.interview.balance.service.ITransactionService;
import au.com.mebank.interview.balance.service.impl.TransactionService;
import au.com.mebank.interview.balance.util.Helper;
import au.com.mebank.interview.balance.view.impl.BalanceView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ltiancong@gmail.com
 * @date 2019-07-18 15:31
 */
public class BalanceControllerTest {
    private static ITransactionService transactionService =
            TransactionService.getInstance();

    private BalanceController balanceController =
            BalanceController.getInstance();

    private DateFormat dateFormat = new SimpleDateFormat(Constant
            .DATE_FORMAT);

    @BeforeAll
    static void prepareData() throws Exception {
        List<Transaction> transactions = Helper
                .parseTransactionHistory();
        transactionService.initializeData(transactions);
    }

    @Test
    void testGetBalanceWithoutReversal() throws ParseException {
        BalanceView view = balanceController.getBalance(
                "ACC998877",
                dateFormat.parse("20/10/2018 12:47:55"),
                dateFormat.parse("21/10/2018 17:47:55"));
        assertEquals(-5, view.getBalance());
        assertEquals(1, view.getTransactionAmount());
    }

    @Test
    void testGetBalanceWithReversal() throws ParseException {
        BalanceView view = balanceController.getBalance(
                "ACC778899",
                dateFormat.parse("20/10/2018 12:47:55"),
                dateFormat.parse("21/10/2018 17:47:55"));
        assertEquals(37.25, view.getBalance());
        assertEquals(3, view.getTransactionAmount());
    }
}
