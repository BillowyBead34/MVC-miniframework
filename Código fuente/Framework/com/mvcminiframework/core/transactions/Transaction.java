package com.mvcminiframework.core.transactions;

import com.mvcminiframework.core.dtos.Parameter;
import com.mvcminiframework.core.dtos.TransactionInfo;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author hikingcarrot7
 */
public class Transaction {

    private final TransactionInfo transactionInfo;
    private final List<Object> subscribers;
    private List<Parameter> params;
    private Object controller;
    private Object model;

    public Transaction(TransactionInfo transactionInfo,
            Object controller,
            Object model,
            List<Object> subscribers,
            List<Parameter> params) {

        this.transactionInfo = transactionInfo;
        this.controller = controller;
        this.model = model;
        this.subscribers = subscribers;
        this.params = params;
    }

    public String getName() {
        return transactionInfo.getName();
    }

    public Object getController() {
        return controller;
    }

    public String getControllerMethod() {
        return transactionInfo.getControllerMethod();
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Object getModel() {
        return model;
    }

    public String getModelMethod() {
        return transactionInfo.getModelMethod();
    }

    public void setModel(Object model) {
        this.model = model;
    }

    public void addSubscribers(Object[] subscribers) {
        addSubscribers(Arrays.asList(subscribers));
    }

    public void addSubscribers(List<Object> subscribers) {
        if (subscribers != null)
            subscribers.stream()
                    .filter(subscriber -> subscriber != null)
                    .forEach(this::addSubscriber);
    }

    public void addSubscriber(Object subscriber) {
        if (subscriber != null && !existsSubscriber(subscriber))
            subscribers.add(subscriber);
    }

    public void removeSubscriber(Object subscriber) {
        subscribers.remove(subscriber);
    }

    public boolean hasSubscribers() {
        return !subscribers.isEmpty();
    }

    public boolean existsSubscriber(Object subscriber) {
        return subscribers.contains(subscriber);
    }

    public List<Object> getSubscribers() {
        return subscribers;
    }

    public void addParam(String name, Object value) {
        addParam(Parameter.create(name, value));
    }

    public void addParam(Parameter param) {
        if (param != null && !existsParam(param))
            params.add(param);
    }

    public void addParams(List<Parameter> params) {
        if (params != null)
            params.stream()
                    .filter(param -> param != null)
                    .forEach(this::addParam);
    }

    public boolean existsParam(Parameter param) {
        return params.contains(param);
    }

    public List<Parameter> getParams() {
        return params;
    }

    public void setParams(List<Parameter> params) {
        this.params = params;
    }

    @Override public String toString() {
        return "Transaction{" + "name=" + getName() + ", controller=" + controller + ", model=" + model + '}';
    }

}
