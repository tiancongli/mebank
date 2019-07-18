package au.com.mebank.interview.balance.dao.impl;

import au.com.mebank.interview.balance.dao.ITransactionIndex;
import au.com.mebank.interview.balance.model.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The base class for secondary index of accountId
 * including fromAccountId, and toAccountId
 * @author ltiancong@gmail.com
 * @date 2019/7/17 8:17 PM
 */
public abstract class BaseTransactionAccountIndex implements
        ITransactionIndex {
    HashMap<String, TreeSet<Transaction>> index;

    @Override
    public SortedSet<Transaction> find(String accountId, Date beginTime,
                                       Date endTime) {
        TreeSet<Transaction> accountTransactions = index.get(accountId);
        if (accountTransactions == null) {
            return null;
        }
        Transaction beginPlaceholder = new Transaction(beginTime);
        Transaction endPlaceholder = new Transaction(endTime);
        SortedSet<Transaction> res =
                accountTransactions.subSet(beginPlaceholder,
                        true, endPlaceholder, true);
        return res;
    }

    @Override
    public void add(Transaction transaction) {
        String accountId = getAccountId(transaction);
        if (!index.containsKey(accountId)) {
            index.put(accountId, new TreeSet<>());
        }
        index.get(accountId).add(transaction);
    }

    /**
     * get corresponding accountId,
     * could be fromAccountId or toAccountId
     * @param transaction
     * @return
     */
    protected abstract String getAccountId(Transaction transaction);
}
