package com.mvcminiframework.core.transactions.ioccontainer;

import com.mvcminiframework.core.dtos.Parameter;
import com.mvcminiframework.core.dtos.TransactionInfo;
import com.mvcminiframework.core.transactions.Transaction;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionFactory {

    public Transaction createTransaction(TransactionInfo transactionInfo, List<Object> subscribers, List<Parameter> params) throws
	    ClassNotFoundException, NoSuchMethodException,
	    InstantiationException, IllegalAccessException,
	    IllegalArgumentException, InvocationTargetException {

	Object controller = createObject(transactionInfo.getControllerPath());
	Object model = createObject(transactionInfo.getModelPath());

	return new Transaction(transactionInfo, controller, model, subscribers, params);
    }

    private Object createObject(String path) throws
	    ClassNotFoundException, NoSuchMethodException,
	    InstantiationException, IllegalAccessException,
	    IllegalArgumentException, InvocationTargetException {

	Class<?> object = Class.forName(path);
	return object.getConstructor().newInstance();
    }

}
