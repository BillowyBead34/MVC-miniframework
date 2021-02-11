package com.mvcminiframework.core.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of <code>TransactionNotFoundException</code> without detail message.
     */
    public TransactionNotFoundException() {
	this("No pudimos encontrar esa transacción!");
    }

    /**
     * Constructs an instance of <code>TransactionNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public TransactionNotFoundException(String msg) {
	super(msg);
    }
}
