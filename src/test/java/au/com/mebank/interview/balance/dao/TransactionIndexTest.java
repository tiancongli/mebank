package au.com.mebank.interview.balance.dao;

import au.com.mebank.interview.balance.Constant;
import au.com.mebank.interview.balance.dao.impl.TransactionFromIndex;
import au.com.mebank.interview.balance.dao.impl.TransactionPrimaryIndex;
import au.com.mebank.interview.balance.dao.impl.TransactionToIndex;
import au.com.mebank.interview.balance.model.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.SortedSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ltiancong@gmail.com
 * @date 2019/7/18 12:11 PM
 */
public class TransactionIndexTest {
    private TransactionPrimaryIndex transactionPrimaryIndex =
            TransactionPrimaryIndex.getInstance();
    private ITransactionIndex transactionFromIndex =
            TransactionFromIndex.getInstance();
    private ITransactionIndex transactionToIndex =
            TransactionToIndex.getInstance();
    private DateFormat dateFormat = new SimpleDateFormat(Constant
            .DATE_FORMAT);
    private Transaction transaction;
    private Transaction reversal;

    @BeforeEach
    void prepareData() throws ParseException {
        transaction = Transaction.builder()
                .transactionId("TX10001")
                .fromAccountId("ACC334455")
                .toAccountId("ACC778899")
                .createdAt(dateFormat.parse("20/10/2018 12:47:55"))
                .amount(25)
                .transactionType("PAYMENT")
                .build();
        reversal = Transaction.builder()
                .transactionId("TX10002")
                .fromAccountId("ACC334455")
                .toAccountId("ACC778899")
                .createdAt(dateFormat.parse("20/10/2018 19:45:00"))
                .amount(25)
                .transactionType("REVERSAL")
                .relatedTransaction("TX10001")
                .build();
    }
    @Test
    void testFindPrimaryIndex() {
        transactionPrimaryIndex.add(transaction);
        Transaction res = transactionPrimaryIndex.find(transaction
                .getTransactionId());
        assertNotNull(res);
    }

    @Test
    void testFindPrimaryIndexWithoutReversal() {
        transactionPrimaryIndex.add(transaction);
        Transaction res = transactionPrimaryIndex.find(transaction
                .getTransactionId());
        assertNull(res.getRelatedTransaction());
    }

    @Test
    void testFindPrimaryIndexWithReversal() {
        transactionPrimaryIndex.add(transaction);
        transactionPrimaryIndex.add(reversal);
        Transaction res = transactionPrimaryIndex.find(transaction
                .getTransactionId());
        assertEquals(res.getRelatedTransaction(), reversal.getTransactionId());
    }

    @Test
    void testFindFromIndexWithinPeriod() throws ParseException {
        transactionFromIndex.add(transaction);
        SortedSet<Transaction> res = transactionFromIndex.find(
                "ACC334455",
                dateFormat.parse("20/10/2018 12:00:00"),
                dateFormat.parse("20/10/2018 13:00:00"));
        assertEquals(res.size(), 1);
    }

    @Test
    void testFindFromIndexNotWithinPeriod() throws ParseException {
        transactionFromIndex.add(transaction);
        SortedSet<Transaction> res = transactionFromIndex.find(
                "ACC334455",
                dateFormat.parse("20/10/2018 12:00:00"),
                dateFormat.parse("20/10/2018 12:10:00"));
        assertEquals(res.size(), 0);
    }

}
