package com.mvcminiframework.core.utils;

import com.mvcminiframework.core.exceptions.InvalidMVCComponentConfig;
import com.mvcminiframework.core.exceptions.MethodNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hikingcarrot7
 */
public class ReflectionUtils {

    public static Object callMethodByName(
	    Object methodObject,
	    Class<?> methodClass,
	    String methodName,
	    Object[] params) {

	Class<?>[] paramTypes = new Class<?>[params.length];

	for (int i = 0; i < params.length; i++)
	    paramTypes[i] = params[i].getClass();

	return callMethodByName(methodObject, methodClass, methodName, paramTypes, params);
    }

    public static Object callMethodByName(
	    Object methodObject,
	    Class<?> methodClass,
	    String methodName,
	    Class<?>[] paramTypes,
	    Object[] params) {

	Method method;

	try {
	    method = methodClass.getDeclaredMethod(methodName, paramTypes);
	} catch (SecurityException | NoSuchMethodException e) {
	    throw new MethodNotFoundException(methodName);
	}

	return callMethod(method, methodObject, params);
    }

    public static void callMethods(List<Method> methods, Object methodObject, Object[] params) {
	methods.forEach(method -> callMethod(method, methodObject, params));
    }

    public static Object callMethod(Method method, Object methodObject, Object[] params) {
	try {
	    return method.invoke(methodObject, params);
	} catch (IllegalAccessException | IllegalArgumentException ex) {
	    throw new InvalidMVCComponentConfig();
	} catch (InvocationTargetException ex) {
	    throw new RuntimeException(ex.getCause().getMessage());
	}
    }

    public static List<Method> getAllMethodsFromClass(Class<?> targetClass) {
	List<Method> methods = new ArrayList<>();
	methods.addAll(Arrays.asList(targetClass.getDeclaredMethods()));
	return methods;
    }

    public static List<Method> getAllAnnotatedMethodsFromClass(Class<?> targetClass, Class<? extends Annotation> annotationType) {
	List<Method> allMethods = getAllMethodsFromClass(targetClass);

	List<Method> annotatedMethods = allMethods.stream()
		.filter(method -> method.isAnnotationPresent(annotationType))
		.collect(Collectors.toList());

	return annotatedMethods;
    }

}
