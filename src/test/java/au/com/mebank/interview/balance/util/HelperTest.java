package au.com.mebank.interview.balance.util;

import au.com.mebank.interview.balance.model.Transaction;
import au.com.mebank.interview.balance.util.Helper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author ltiancong@gmail.com
 * @date 2019-07-18 15:48
 */
public class HelperTest {
    @Test
    void testParseTransactionHistory() throws Exception {
        List<Transaction> transactions = Helper.parseTransactionHistory();
        assertEquals(transactions.size(), 5);
    }
}
