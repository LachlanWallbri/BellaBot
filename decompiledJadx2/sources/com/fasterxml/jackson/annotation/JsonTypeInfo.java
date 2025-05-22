package com.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes.dex */
public @interface JsonTypeInfo {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.fasterxml.jackson.annotation.JsonTypeInfo$As */
    /* loaded from: classes.dex */
    public enum EnumC1586As {
        PROPERTY,
        WRAPPER_OBJECT,
        WRAPPER_ARRAY,
        EXTERNAL_PROPERTY,
        EXISTING_PROPERTY
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Deprecated
    /* loaded from: classes.dex */
    public static abstract class None {
    }

    Class<?> defaultImpl() default JsonTypeInfo.class;

    EnumC1586As include() default EnumC1586As.PROPERTY;

    String property() default "";

    EnumC1587Id use();

    boolean visible() default false;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* renamed from: com.fasterxml.jackson.annotation.JsonTypeInfo$Id */
    /* loaded from: classes.dex */
    public enum EnumC1587Id {
        NONE(null),
        CLASS("@class"),
        MINIMAL_CLASS("@c"),
        NAME("@type"),
        CUSTOM(null);

        private final String _defaultPropertyName;

        EnumC1587Id(String str) {
            this._defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return this._defaultPropertyName;
        }
    }
}
