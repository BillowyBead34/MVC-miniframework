package com.mvcminiframework.core.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mvcminiframework.core.dtos.MVCComponentInfo;
import com.mvcminiframework.core.dtos.TransactionInfo;
import com.mvcminiframework.core.exceptions.ConfigFileNotFoundException;
import com.mvcminiframework.core.exceptions.InvalidConfigFileException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author hikingcarrot7
 */
public class ConfigParser {

    private ConfigReader reader;
    private Gson gson;

    public ConfigParser() {
	this(ConfigReader.DEFAULT_CONFIG_PATH);
    }

    public ConfigParser(String configPath) {
	this.reader = new ConfigReader(configPath);
	this.gson = new Gson();
    }

    public List<TransactionInfo> getTransactionsInfo() {
	return parseJsonConfig();
    }

    private List<TransactionInfo> parseJsonConfig() {
	return Arrays.stream(getJsonObjects()).map(jsonObject -> {
	    String transactionName = getTransactionName(jsonObject);
	    MVCComponentInfo controllerInfo = getMVCComponentInfo("controller", jsonObject);
	    MVCComponentInfo modelInfo = getMVCComponentInfo("model", jsonObject);
	    return new TransactionInfo(transactionName, controllerInfo, modelInfo);
	}).collect(Collectors.toList());
    }

    private String getTransactionName(JsonObject jsonObject) {
	try {
	    JsonElement transactionName = jsonObject.get("name");
	    Objects.requireNonNull(transactionName, "No encontramos el atributo 'name' para una transacción");
	    return transactionName.getAsString();
	} catch (NullPointerException e) {
	    throw new InvalidConfigFileException(e.getMessage());
	}
    }

    private MVCComponentInfo getMVCComponentInfo(String componentName, JsonObject jsonObject) {
	try {
	    JsonObject componentInfo = jsonObject.getAsJsonObject(componentName);
	    JsonElement componentPath = componentInfo.get("path");
	    JsonElement componentMethod = componentInfo.get("method");

	    Objects.requireNonNull(componentPath, "No encontramos el atributo 'path' para el componente: " + componentName);
	    Objects.requireNonNull(componentMethod, "No encontramos el atributo 'method' para el componente: " + componentName);

	    return new MVCComponentInfo(componentPath.getAsString(), componentMethod.getAsString());
	} catch (NullPointerException e) {
	    throw new InvalidConfigFileException(e.getMessage());
	}
    }

    private JsonObject[] getJsonObjects() throws ConfigFileNotFoundException {
	try {
	    return gson.fromJson(reader.readConfigJson(), JsonObject[].class);
	} catch (JsonSyntaxException e) {
	    throw new InvalidConfigFileException(e.getMessage());
	}
    }

}
