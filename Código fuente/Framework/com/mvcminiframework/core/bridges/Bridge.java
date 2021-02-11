package com.mvcminiframework.core.bridges;

import com.mvcminiframework.core.dtos.Parameter;
import com.mvcminiframework.core.transactions.Transaction;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hikingcarrot7
 */
public abstract class Bridge {

    protected final Transaction transaction;

    public Bridge(Transaction transaction) {
        this.transaction = transaction;
    }

    public <T> T getValue(String name) {
        for (Parameter param : getParams())
            if (param.getName().equals(name))
                return (T) param.getValue();

        throw new NullPointerException("No existe ningún valor con el nombre: " + name);
    }

    public List<Object> getAllValues() {
        return getParams()
                .stream()
                .map(Parameter::getValue)
                .collect(Collectors.toList());
    }

    public void addSubscriber(Object subscriber) {
        transaction.addSubscriber(subscriber);
    }

    public void addParam(String name, Object value) {
        transaction.addParam(name, value);
    }

    public void addParam(Parameter param) {
        transaction.addParam(param);
    }

    public List<Parameter> getParams() {
        return transaction.getParams();
    }

}
