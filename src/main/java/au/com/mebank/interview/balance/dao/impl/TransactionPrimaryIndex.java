package au.com.mebank.interview.balance.dao.impl;

import au.com.mebank.interview.balance.dao.ITransactionIndex;
import au.com.mebank.interview.balance.model.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;

/**
 * The class of primary index
 * @author ltiancong@gmail.com
 * @date 2019/7/16 8:15 PM
 */
public class TransactionPrimaryIndex implements ITransactionIndex {
    private static final TransactionPrimaryIndex instance = new
            TransactionPrimaryIndex();

    private final HashMap<String, Transaction> primaryIndex;

    public static TransactionPrimaryIndex getInstance() {
        return instance;
    }

    private TransactionPrimaryIndex() {
        primaryIndex = new HashMap<>();
    }

    @Override
    public SortedSet<Transaction> find(String accountId, Date beginTime, Date endTime) {
        throw new UnsupportedOperationException();
    }

    public Transaction find(String transactionId) {
        return instance.primaryIndex.get(transactionId);
    }

    /**
     * When add transaction to a primary index
     * check whether it is a reversal,
     * if it is a reversal, then record its transactionId
     * and write into the relatedTransaction field of the original transaction
     * @param transaction
     */
    @Override
    public void add(Transaction transaction) {
        String relatedTransactionId = transaction.getRelatedTransaction();
        Transaction relatedTransaction = find(relatedTransactionId);
        if (relatedTransaction != null) {
            relatedTransaction.setRelatedTransaction(transaction
                    .getTransactionId());
        }
        instance.primaryIndex.put(transaction.getTransactionId(),
                transaction);
    }
}
