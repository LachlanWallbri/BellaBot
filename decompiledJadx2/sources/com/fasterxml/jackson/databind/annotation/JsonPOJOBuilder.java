package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface JsonPOJOBuilder {
    public static final String DEFAULT_BUILD_METHOD = "build";
    public static final String DEFAULT_WITH_PREFIX = "with";

    String buildMethodName() default "build";

    String withPrefix() default "with";

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public static class Value {
        public final String buildMethodName;
        public final String withPrefix;

        public Value(JsonPOJOBuilder jsonPOJOBuilder) {
            this(jsonPOJOBuilder.buildMethodName(), jsonPOJOBuilder.withPrefix());
        }

        public Value(String str, String str2) {
            this.buildMethodName = str;
            this.withPrefix = str2;
        }
    }
}
