package com.mvcminiframework.core.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class ComponentNotFoundException extends RuntimeException {

    public ComponentNotFoundException(String component) {
	super(String.format("No encontramos el %s en el archivo de configuración!", component));
    }

}
