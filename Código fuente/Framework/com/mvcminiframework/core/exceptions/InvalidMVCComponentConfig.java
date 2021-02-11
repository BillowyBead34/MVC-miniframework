package com.mvcminiframework.core.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class InvalidMVCComponentConfig extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidMVCComponentConfig</code> without detail message.
     */
    public InvalidMVCComponentConfig() {
	this("Un componente del MVC no está bien configurado. Lee el manual para solucionar este error");
    }

    /**
     * Constructs an instance of <code>InvalidMVCComponentConfig</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMVCComponentConfig(String msg) {
	super(msg);
    }
}
