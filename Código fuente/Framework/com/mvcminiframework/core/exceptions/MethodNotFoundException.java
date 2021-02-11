package com.mvcminiframework.core.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class MethodNotFoundException extends RuntimeException {

    public MethodNotFoundException(String methodName) {
	super("No encontramos el método: " + methodName);
    }

}
