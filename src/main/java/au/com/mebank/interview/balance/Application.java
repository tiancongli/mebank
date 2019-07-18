package au.com.mebank.interview.balance;

import au.com.mebank.interview.balance.model.Transaction;
import au.com.mebank.interview.balance.service.ITransactionService;
import au.com.mebank.interview.balance.service.impl.TransactionService;
import au.com.mebank.interview.balance.util.Helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Main entry
 * @author ltiancong@gmail.com
 * @date 2019/7/16 2:43 PM
 */
public class Application {

    public static void main(String[] args)
            throws URISyntaxException, IOException {
        // parse csv
        List<Transaction> transactions = Helper.parseTransactionHistory();

        // build index
        ITransactionService transactionService = TransactionService.getInstance();
        transactionService.initializeData(transactions);

        // start repl
        Repl repl = Repl.getInstance();
        repl.start();
    }

}
