package au.com.mebank.interview.balance.service;

import au.com.mebank.interview.balance.Constant;
import au.com.mebank.interview.balance.model.Transaction;
import au.com.mebank.interview.balance.service.impl.TransactionService;
import au.com.mebank.interview.balance.util.Helper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ltiancong@gmail.com
 * @date 2019-07-18 14:00
 */
public class TransactionServiceTest {
    private static ITransactionService transactionService =
            TransactionService.getInstance();

    private DateFormat dateFormat = new SimpleDateFormat(Constant
            .DATE_FORMAT);

    @BeforeAll
    static void prepareData() throws Exception {
        List<Transaction> transactions = Helper
                .parseTransactionHistory();
        transactionService.initializeData(transactions);
    }

    @Test
    void testGetOutcomeByAccount() throws ParseException {
        SortedSet<Transaction> res = transactionService.getOutcomeByAccount(
                "ACC334455",
                dateFormat.parse("20/10/2018 12:47:55"),
                dateFormat.parse("20/10/2018 17:47:55"));
        assertEquals(res.size(), 2);
    }

    @Test
    void testGetIncomeByAccount() throws ParseException {
        SortedSet<Transaction> res = transactionService.getIncomeByAccount(
                "ACC778899",
                dateFormat.parse("20/10/2018 12:47:55"),
                dateFormat.parse("21/10/2018 17:47:55"));
        assertEquals(res.size(), 3);
    }
}
