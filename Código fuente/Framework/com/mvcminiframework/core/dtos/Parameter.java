package com.mvcminiframework.core.dtos;

import java.util.Objects;

/**
 *
 * @author hikingcarrot7
 */
public class Parameter {

    private String name;
    private Object value;

    public static Parameter create(String name, Object value) {
        return new Parameter(name, value);
    }

    public static Parameter empty() {
        return new Parameter(null, null);
    }

    private Parameter(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Parameter other = (Parameter) obj;
        return Objects.equals(this.name, other.name);
    }

}
