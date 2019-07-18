package au.com.mebank.interview.balance.dao.impl;

import au.com.mebank.interview.balance.model.Transaction;

import java.util.HashMap;

/**
 * The index structure used to find transactions by fromAccountId
 * @author ltiancong@gmail.com
 * @date 2019/7/16 8:25 PM
 */
public class TransactionFromIndex extends BaseTransactionAccountIndex {
    private static final TransactionFromIndex instance = new
            TransactionFromIndex();

    public static TransactionFromIndex getInstance() {
        return instance;
    }

    private TransactionFromIndex() {
        index = new HashMap<>();
    }

    @Override
    protected String getAccountId(Transaction transaction) {
        return transaction.getFromAccountId();
    }
}
