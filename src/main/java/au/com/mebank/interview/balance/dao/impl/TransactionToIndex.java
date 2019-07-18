package au.com.mebank.interview.balance.dao.impl;

import au.com.mebank.interview.balance.model.Transaction;

import java.util.HashMap;

/**
 * The index structure used to find transactions by toAccountId
 * @author ltiancong@gmail.com
 * @date 2019/7/16 8:25 PM
 */
public class TransactionToIndex extends BaseTransactionAccountIndex {
    private static final TransactionToIndex instance = new
            TransactionToIndex();
    
    public static TransactionToIndex getInstance() {
        return instance;
    }
    
    private TransactionToIndex() {
        index = new HashMap<>();
    }

    @Override
    protected String getAccountId(Transaction transaction) {
        return transaction.getToAccountId();
    }
}
