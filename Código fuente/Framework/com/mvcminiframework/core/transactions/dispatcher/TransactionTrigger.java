package com.mvcminiframework.core.transactions.dispatcher;

import com.mvcminiframework.core.bridges.ControllerBridge;
import com.mvcminiframework.core.transactions.Transaction;
import com.mvcminiframework.core.utils.ReflectionUtils;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionTrigger {

    private final Transaction transaction;

    public TransactionTrigger(Transaction transaction) {
        this.transaction = transaction;
    }

    public void triggerTransaction() {
        callController();
    }

    private void callController() {
        Object controller = transaction.getController();

        ReflectionUtils.callMethodByName(
                controller,
                controller.getClass(),
                transaction.getControllerMethod(),
                new Object[]{new ControllerBridge(transaction)});
    }

    public Transaction getTransaction() {
        return transaction;
    }

}
