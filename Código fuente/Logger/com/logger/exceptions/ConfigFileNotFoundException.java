package com.logger.exceptions;

/**
 *
 * @author hikingcarrot7
 */
public class ConfigFileNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of <code>ConfigFileNotFoundException</code> without detail message.
     */
    public ConfigFileNotFoundException() {
        this("No pude encontrar el archivo de configuración!");
    }

    /**
     * Constructs an instance of <code>ConfigFileNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ConfigFileNotFoundException(String msg) {
        super(msg);
    }
}
