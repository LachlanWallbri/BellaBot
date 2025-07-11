package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface Converter<IN, OUT> {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public static abstract class None implements Converter<Object, Object> {
    }

    OUT convert(IN in);

    JavaType getInputType(TypeFactory typeFactory);

    JavaType getOutputType(TypeFactory typeFactory);
}
