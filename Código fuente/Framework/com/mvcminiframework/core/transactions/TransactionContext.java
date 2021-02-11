package com.mvcminiframework.core.transactions;

import com.mvcminiframework.core.dtos.Parameter;
import com.mvcminiframework.core.transactions.dispatcher.TransactionTrigger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionContext {

    private final TransactionLoader transactionLoader;

    public TransactionContext() {
        this.transactionLoader = TransactionLoader.getInstance();
    }

    public TransactionTrigger prepareTransaction(String transactionName) {
        return transactionLoader.loadTransaction(transactionName);
    }

    public void fireTransaction(String transactionName, Parameter... params) {
        fireTransaction(transactionName, new ArrayList<>(), new ArrayList<>(Arrays.asList(params)));
    }

    public void fireTransaction(String transactionName, Object[] subscribers, Parameter... params) {
        fireTransaction(transactionName, new ArrayList<>(Arrays.asList(subscribers)), params);
    }

    public void fireTransaction(String transactionName, List<Object> subscribers, Parameter... params) {
        fireTransaction(transactionName, subscribers, new ArrayList<>(Arrays.asList(params)));
    }

    public void fireTransaction(String transactionName, List<Object> subscribers, List<Parameter> params) {
        TransactionTrigger dispatcher = transactionLoader.loadTransaction(transactionName, subscribers, params);
        dispatcher.triggerTransaction();
    }

    public void addSubscriberToTransaction(String transactionName, Object subscriber) {
        Transaction transaction = transactionLoader.getLoadedTransaction(transactionName);
        transaction.addSubscriber(subscriber);
    }

    public void addSubscribersToTransaction(String transactionName, Object... subscribers) {
        addSubscribersToTransaction(transactionName, new ArrayList(Arrays.asList(subscribers)));
    }

    public void addSubscribersToTransaction(String transactionName, List<Object> subscribers) {
        if (subscribers != null)
            subscribers.forEach(subscriber -> addSubscriberToTransaction(transactionName, subscriber));
    }

    public void addParamToTransaction(String transactionName, Parameter param) {
        Transaction transaction = transactionLoader.getLoadedTransaction(transactionName);
        transaction.addParam(param);
    }

    public void addParamsToTransaction(String transactionName, List<Parameter> params) {
        if (params != null)
            params.forEach(param -> addParamToTransaction(transactionName, param));
    }

}
