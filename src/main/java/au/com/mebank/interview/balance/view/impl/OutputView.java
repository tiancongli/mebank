package au.com.mebank.interview.balance.view.impl;

import au.com.mebank.interview.balance.view.IView;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The output view
 * @author ltiancong@gmail.com
 * @date 2019/7/18 10:55 AM
 */
@Data
@AllArgsConstructor
public class OutputView extends BaseView {
    private static final String SEPARATOR =
            "============================================";
    private IView view;

    @Override
    public String present() {
        return String.format("%s\n%s\n%s\n", SEPARATOR, view, SEPARATOR);
    }
}
