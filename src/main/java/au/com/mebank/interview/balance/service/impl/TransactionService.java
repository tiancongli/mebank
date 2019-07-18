package au.com.mebank.interview.balance.service.impl;

import au.com.mebank.interview.balance.dao.ITransactionIndex;
import au.com.mebank.interview.balance.dao.impl.TransactionFromIndex;
import au.com.mebank.interview.balance.dao.impl.TransactionPrimaryIndex;
import au.com.mebank.interview.balance.dao.impl.TransactionToIndex;
import au.com.mebank.interview.balance.model.Transaction;
import au.com.mebank.interview.balance.service.ITransactionService;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

/**
 * The concrete transactionService class
 * @author ltiancong@gmail.com
 * @date 2019/7/16 9:07 PM
 */
public class TransactionService implements ITransactionService {
    private static final TransactionService instance = new
            TransactionService();

    private ITransactionIndex transactionPrimaryIndex;
    private ITransactionIndex transactionFromIndex;
    private ITransactionIndex transactionToIndex;



    public static TransactionService getInstance() {
        return instance;
    }

    private TransactionService() {
        transactionPrimaryIndex = TransactionPrimaryIndex.getInstance();
        transactionFromIndex = TransactionFromIndex.getInstance();
        transactionToIndex = TransactionToIndex.getInstance();
    }

    @Override
    public void initializeData(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transactionPrimaryIndex.add(transaction);
            transactionFromIndex.add(transaction);
            transactionToIndex.add(transaction);
        }
    }

    @Override
    public SortedSet<Transaction> getOutcomeByAccount(String accountId, Date
            beginTime, Date endTime) {
        return transactionFromIndex.find(accountId, beginTime, endTime);
    }

    @Override
    public SortedSet<Transaction> getIncomeByAccount(String accountId, Date
            beginTime, Date endTime) {
        return transactionToIndex.find(accountId, beginTime, endTime);
    }
}
