package com.mvcminiframework.core.transactions;

import com.mvcminiframework.core.dtos.Parameter;
import com.mvcminiframework.core.dtos.TransactionInfo;
import com.mvcminiframework.core.exceptions.TransactionNotFoundException;
import com.mvcminiframework.core.transactions.dispatcher.TransactionTrigger;
import com.mvcminiframework.core.transactions.ioccontainer.TransactionService;
import com.mvcminiframework.core.utils.ConfigParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionLoader {

    private static TransactionLoader instance;

    private final List<TransactionInfo> transactionsInfo;
    private final TransactionService transactionService;

    public static TransactionLoader getInstance() {
        if (instance == null)
            instance = new TransactionLoader();

        return instance;
    }

    private TransactionLoader() {
        this(new ConfigParser());
    }

    private TransactionLoader(ConfigParser configParser) {
        this.transactionsInfo = configParser.getTransactionsInfo();
        this.transactionService = TransactionService.getInstance();
    }

    public Transaction getLoadedTransaction(String transactionName) {
        Optional<TransactionInfo> transactionInfoMaybe = getTransactionInfo(transactionName);

        if (!transactionInfoMaybe.isPresent())
            throw new TransactionNotFoundException();

        return getLoadedTransaction(transactionInfoMaybe.get());
    }

    public Transaction getLoadedTransaction(TransactionInfo transactionInfo) {
        if (!isTransactionLoaded(transactionInfo.getName()))
            throw new TransactionNotFoundException();

        return transactionService.createOrGetTransaction(transactionInfo);
    }

    public TransactionTrigger loadTransaction(String transactionName) {
        return loadTransaction(transactionName, new ArrayList<>(), new ArrayList<>());
    }

    public TransactionTrigger loadTransaction(String transactionName, List<Object> subscribers, List<Parameter> params) {
        Optional<TransactionInfo> transactionInfoMaybe = getTransactionInfo(transactionName);

        if (!transactionInfoMaybe.isPresent())
            throw new TransactionNotFoundException();

        Transaction transaction = transactionService.createOrGetTransaction(transactionInfoMaybe.get(), subscribers, params);
        return new TransactionTrigger(transaction);
    }

    public Optional<TransactionInfo> getTransactionInfo(String transactionName) {
        return transactionsInfo
                .stream()
                .filter(transactionInfo -> transactionInfo.getName().equals(transactionName))
                .findAny();
    }

    public boolean isTransactionLoaded(String transactionName) {
        return transactionService.existTransaction(transactionName);
    }

}
