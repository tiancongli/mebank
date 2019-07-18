package au.com.mebank.interview.balance.view.impl;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The balance view
 * @author ltiancong@gmail.com
 * @date 2019/7/16 10:31 PM
 */
@Data
@AllArgsConstructor
public class BalanceView extends BaseView {
    private double balance;
    private int transactionAmount;

    @Override
    public String present() {
        String balancePrefix = "";
        if (balance < 0) {
            balancePrefix = "-";
        }
        return String.format(
                "Relative balance for the period is: %s$%.2f\n" +
                        "Number of transactions included is: %d",
                balancePrefix, Math.abs(balance), transactionAmount);
    }
}
