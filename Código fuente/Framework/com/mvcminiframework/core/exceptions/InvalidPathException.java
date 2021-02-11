package com.mvcminiframework.core.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class InvalidPathException extends RuntimeException {

    public InvalidPathException(String path) {
	super(String.format("La ruta %s es inválida!", path));
    }

}
