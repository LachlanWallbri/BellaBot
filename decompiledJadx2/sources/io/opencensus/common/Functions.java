package io.opencensus.common;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class Functions {
    private static final Function<Object, Void> RETURN_NULL = new Function<Object, Void>() { // from class: io.opencensus.common.Functions.1
        @Override // io.opencensus.common.Function
        @Nullable
        public Void apply(Object obj) {
            return null;
        }
    };
    private static final Function<Object, Void> THROW_ILLEGAL_ARGUMENT_EXCEPTION = new Function<Object, Void>() { // from class: io.opencensus.common.Functions.2
        @Override // io.opencensus.common.Function
        public Void apply(Object obj) {
            throw new IllegalArgumentException();
        }
    };
    private static final Function<Object, Void> THROW_ASSERTION_ERROR = new Function<Object, Void>() { // from class: io.opencensus.common.Functions.3
        @Override // io.opencensus.common.Function
        public Void apply(Object obj) {
            throw new AssertionError();
        }
    };
    private static final Function<Object, String> RETURN_TO_STRING = new Function<Object, String>() { // from class: io.opencensus.common.Functions.4
        @Override // io.opencensus.common.Function
        public String apply(Object obj) {
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
    };

    private Functions() {
    }

    public static <T> Function<Object, T> returnNull() {
        return (Function<Object, T>) RETURN_NULL;
    }

    public static <T> Function<Object, T> returnConstant(final T t) {
        return new Function<Object, T>() { // from class: io.opencensus.common.Functions.5
            @Override // io.opencensus.common.Function
            public T apply(Object obj) {
                return (T) t;
            }
        };
    }

    public static Function<Object, String> returnToString() {
        return RETURN_TO_STRING;
    }

    public static <T> Function<Object, T> throwIllegalArgumentException() {
        return (Function<Object, T>) THROW_ILLEGAL_ARGUMENT_EXCEPTION;
    }

    public static <T> Function<Object, T> throwAssertionError() {
        return (Function<Object, T>) THROW_ASSERTION_ERROR;
    }
}
