package com.mvcminiframework.core.transactions.ioccontainer;

import com.logger.Logger;
import com.mvcminiframework.core.dtos.Parameter;
import com.mvcminiframework.core.dtos.TransactionInfo;
import com.mvcminiframework.core.exceptions.InvalidMVCComponentConfig;
import com.mvcminiframework.core.exceptions.InvalidPathException;
import com.mvcminiframework.core.exceptions.TransactionNotFoundException;
import com.mvcminiframework.core.transactions.Transaction;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionService {

    public static TransactionService instance;

    private final TransactionFactory transactionFactory;
    private final ObjectRepository<Transaction> repository;

    public static TransactionService getInstance() {
        if (instance == null)
            instance = new TransactionService();

        return instance;
    }

    private TransactionService() {
        this.transactionFactory = new TransactionFactory();
        this.repository = new ObjectRepository<>();
    }

    public Transaction createOrGetTransaction(TransactionInfo transactionInfo) {
        return createOrGetTransaction(transactionInfo, null, null);
    }

    public Transaction createOrGetTransaction(TransactionInfo transactionInfo, List<Object> subscribers, List<Parameter> params) {
        try {
            return tryToCreateTransaction(transactionInfo, subscribers, params);
        } catch (ClassNotFoundException ex) {
            throw new InvalidPathException(ex.getMessage());
        } catch (NoSuchMethodException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException ex) {
            throw new InvalidMVCComponentConfig();
        }
    }

    private Transaction tryToCreateTransaction(TransactionInfo transactionInfo, List<Object> subscribers, List<Parameter> params) throws
            ClassNotFoundException, NoSuchMethodException,
            InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        if (!existTransaction(transactionInfo.getName())) {
            Transaction transaction = transactionFactory.createTransaction(transactionInfo, subscribers, params);
            repository.saveObject(transaction);

            Logger.getLogger().log("Se ha creado la transacción " + transactionInfo.getName());

            return transaction;
        }

        Transaction transaction = getTransaction(transactionInfo.getName());
        transaction.addSubscribers(subscribers);
        transaction.setParams(params);
        Logger.getLogger().log("Se ha recuperado la transacción " + transactionInfo.getName());

        return transaction;
    }

    public Transaction getTransaction(String transactionName) {
        if (!existTransaction(transactionName))
            throw new TransactionNotFoundException();

        List<Transaction> transactions = getAllCreatedTransactions();

        return transactions.stream()
                .filter(t -> t.getName().equals(transactionName))
                .findAny()
                .get();
    }

    public boolean existTransaction(String transactionName) {
        List<Transaction> transactions = getAllCreatedTransactions();
        return transactions.stream().anyMatch(t -> t.getName().equals(transactionName));
    }

    public List<Transaction> getAllCreatedTransactions() {
        return repository.getAllObjects();
    }

    public void removeTransaction(String transactionName) {
        Transaction transaction = getTransaction(transactionName);
        repository.removeObject(transaction);
    }

}
