package com.mvcminiframework.core.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class InvalidConfigFileException extends RuntimeException {

    public InvalidConfigFileException(String details) {
	super(String.format("Hay errores en el archivo de configuración, revísalo! (%s)", details));
    }

}
