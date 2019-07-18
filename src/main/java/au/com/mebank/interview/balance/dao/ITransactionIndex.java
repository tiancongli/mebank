package au.com.mebank.interview.balance.dao;

import au.com.mebank.interview.balance.model.Transaction;

import java.util.Date;
import java.util.SortedSet;

/**
 * The transaction index interface
 * including primary index and secondary index
 * @author ltiancong@gmail.com
 * @date 2019/7/16 8:28 PM
 */
public interface ITransactionIndex {
    /**
     * Add a new transaction into the index
     * @param transaction
     */
    void add(Transaction transaction);

    /**
     * Find a transaction according to the accountId,
     * and the transaction period
     * @param accountId
     * @param beginTime
     * @param endTime
     * @return
     */
    SortedSet<Transaction> find(String accountId, Date beginTime, Date endTime);
}
