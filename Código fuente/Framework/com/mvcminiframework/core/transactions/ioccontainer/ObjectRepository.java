package com.mvcminiframework.core.transactions.ioccontainer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hikingcarrot7
 */
public class ObjectRepository<T> {

    private final List<T> repository;

    public ObjectRepository() {
	this.repository = new ArrayList<>();
    }

    public void saveObject(T object) {
	if (object != null)
	    repository.add(object);
    }

    public boolean existsObject(T object) {
	return repository.contains(object);
    }

    public void removeObject(T object) {
	repository.remove(object);
    }

    public List<T> getAllObjects() {
	return new ArrayList<>(repository);
    }

}
