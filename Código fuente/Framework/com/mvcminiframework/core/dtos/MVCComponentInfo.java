package com.mvcminiframework.core.dtos;

/**
 *
 * @author hikingcarrot7
 */
public class MVCComponentInfo {

    private String path;
    private String method;

    public MVCComponentInfo(String path, String method) {
	this.path = path;
	this.method = method;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

    public String getMethod() {
	return method;
    }

    public void setMethod(String method) {
	this.method = method;
    }

    @Override public String toString() {
	return "MVCComponentInfo{" + "path=" + path + ", method=" + method + '}';
    }

}
