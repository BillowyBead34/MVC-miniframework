package com.mvcminiframework.core.bridges;

import com.mvcminiframework.core.annotations.TransactionSubscriber;
import com.mvcminiframework.core.transactions.Transaction;
import com.mvcminiframework.core.utils.ReflectionUtils;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author hikingcarrot7
 */
public class ModelBridge extends Bridge {

    public ModelBridge(Transaction transaction) {
        super(transaction);
    }

    public void notifySubscribers() {
        notifySubscribers(null);
    }

    public void notifySubscribers(Object args) {
        if (transaction.hasSubscribers())
            callSubscribers(args);
    }

    private void callSubscribers(Object args) {
        callSubscribers(transaction.getSubscribers(), args);
    }

    private void callSubscribers(List<Object> subscribers, Object args) {
        subscribers.forEach(subscriber -> callSubscriber(subscriber, args));
    }

    private void callSubscriber(Object subscriber, Object args) {
        List<Method> methods = ReflectionUtils.getAllAnnotatedMethodsFromClass(subscriber.getClass(), TransactionSubscriber.class);
        methods.stream()
                .filter(this::shouldCallMethod)
                .forEach(method -> ReflectionUtils.callMethod(method, subscriber, new Object[]{args}));
    }

    private boolean shouldCallMethod(Method method) {
        TransactionSubscriber annotation = method.getAnnotation(TransactionSubscriber.class);
        return annotation.value().equals(transaction.getName());
    }

}
