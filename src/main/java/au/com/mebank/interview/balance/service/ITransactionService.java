package au.com.mebank.interview.balance.service;

import au.com.mebank.interview.balance.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;

/**
 * The class responsible for service of transactions
 * @author ltiancong@gmail.com
 * @date 2019/7/18 11:31 AM
 */
public interface ITransactionService {
    /**
     * initialize the data management system
     * @param transactions
     */
    void initializeData(List<Transaction> transactions);

    /**
     * get all outcome transactions by accountId, within certain period
     * @param accountId
     * @param beginTime
     * @param endTime
     * @return
     */
    SortedSet<Transaction> getOutcomeByAccount(String accountId, Date
            beginTime, Date endTime);

    /**
     * get all income transactions by accountId, within certain period
     * @param accountId
     * @param beginTime
     * @param endTime
     * @return
     */
    SortedSet<Transaction> getIncomeByAccount(String accountId, Date
            beginTime, Date endTime);
}
