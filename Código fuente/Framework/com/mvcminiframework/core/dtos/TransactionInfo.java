package com.mvcminiframework.core.dtos;

/**
 *
 * @author hikingcarrot7
 */
public class TransactionInfo {

    private String name;
    private MVCComponentInfo controllerInfo;
    private MVCComponentInfo modelInfo;

    public TransactionInfo(String name, MVCComponentInfo controllerInfo, MVCComponentInfo modelInfo) {
	this.name = name;
	this.controllerInfo = controllerInfo;
	this.modelInfo = modelInfo;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getControllerPath() {
	return controllerInfo.getPath();
    }

    public String getControllerMethod() {
	return controllerInfo.getMethod();
    }

    public void setControllerInfo(MVCComponentInfo controllerInfo) {
	this.controllerInfo = controllerInfo;
    }

    public String getModelPath() {
	return modelInfo.getPath();
    }

    public String getModelMethod() {
	return modelInfo.getMethod();
    }

    public void setModelInfo(MVCComponentInfo modelInfo) {
	this.modelInfo = modelInfo;
    }

    @Override public String toString() {
	return "TransactionInfo{" + "name=" + name + ", controllerInfo=" + controllerInfo + ", modelInfo=" + modelInfo + '}';
    }

}
