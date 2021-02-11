package com.mvcminiframework.core.bridges;

import com.mvcminiframework.core.transactions.Transaction;
import com.mvcminiframework.core.utils.ReflectionUtils;

/**
 *
 * @author hikingcarrot7
 */
public class ControllerBridge extends Bridge {

    public ControllerBridge(Transaction transaction) {
        super(transaction);
    }

    public <T> T callModel() {
        Object model = transaction.getModel();

        return (T) ReflectionUtils.callMethodByName(
                model,
                model.getClass(),
                transaction.getModelMethod(),
                new Object[]{new ModelBridge(transaction)});
    }

}
