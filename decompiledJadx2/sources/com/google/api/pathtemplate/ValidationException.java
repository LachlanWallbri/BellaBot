package com.google.api.pathtemplate;

import java.util.Iterator;
import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ValidationException extends IllegalArgumentException {
    private static ThreadLocal<Stack<Supplier<String>>> contextLocal = new ThreadLocal<>();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface Supplier<T> {
        T get();
    }

    public static void pushCurrentThreadValidationContext(Supplier<String> supplier) {
        Stack<Supplier<String>> stack = contextLocal.get();
        if (stack == null) {
            stack = new Stack<>();
            contextLocal.set(stack);
        }
        stack.push(supplier);
    }

    public static void pushCurrentThreadValidationContext(final String str) {
        pushCurrentThreadValidationContext(new Supplier<String>() { // from class: com.google.api.pathtemplate.ValidationException.1
            @Override // com.google.api.pathtemplate.ValidationException.Supplier
            public String get() {
                return str;
            }
        });
    }

    public static void popCurrentThreadValidationContext() {
        Stack<Supplier<String>> stack = contextLocal.get();
        if (stack != null) {
            stack.pop();
        }
    }

    public ValidationException(String str, Object... objArr) {
        super(message(contextLocal.get(), str, objArr));
    }

    private static String message(Stack<Supplier<String>> stack, String str, Object... objArr) {
        if (stack == null || stack.isEmpty()) {
            return String.format(str, objArr);
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Supplier<String>> it = stack.iterator();
        while (it.hasNext()) {
            sb.append(it.next().get() + ": ");
        }
        return sb.toString() + String.format(str, objArr);
    }
}
