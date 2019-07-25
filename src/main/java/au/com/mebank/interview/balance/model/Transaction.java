package au.com.mebank.interview.balance.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ltiancong@gmail.com
 * @date 2019/7/16 4:53 PM
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Comparable<Transaction> {
    @CsvBindByName
    private String transactionId;

    @CsvBindByName
    private String fromAccountId;

    @CsvBindByName
    private String toAccountId;

    @CsvBindByName
    @CsvDate("dd/MM/yyyy HH:mm:ss")
    private Date createdAt;

    @CsvBindByName
    private double amount;

    @CsvBindByName
    private String transactionType;

    @CsvBindByName
    private String relatedTransaction;

    public Transaction(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId.trim();
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId.trim();
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId.trim();
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType.trim();
    }

    public void setRelatedTransaction(String relatedTransaction) {
        this.relatedTransaction = relatedTransaction.trim();
    }

    @Override
    public int compareTo(Transaction transaction) {
        return createdAt.compareTo(transaction.getCreatedAt());
    }
}
