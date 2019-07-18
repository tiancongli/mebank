package au.com.mebank.interview.balance.view.impl;

import au.com.mebank.interview.balance.view.IView;

/**
 * The base view
 * @author ltiancong@gmail.com
 * @date 2019/7/18 10:58 AM
 */
public abstract class BaseView implements IView {
    @Override
    public String toString() {
        return present();
    }
}
