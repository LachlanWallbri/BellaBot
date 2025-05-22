package com.google.api.client.util;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.common.base.Ascii;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class FieldInfo {
    private static final Map<Field, FieldInfo> CACHE = new WeakHashMap();
    private final Field field;
    private final boolean isPrimitive;
    private final String name;
    private final Method[] setters;

    /* renamed from: of */
    public static FieldInfo m574of(Enum<?> r5) {
        try {
            FieldInfo m575of = m575of(r5.getClass().getField(r5.name()));
            Preconditions.checkArgument(m575of != null, "enum constant missing @Value or @NullValue annotation: %s", r5);
            return m575of;
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: of */
    public static FieldInfo m575of(Field field) {
        String str = null;
        if (field == null) {
            return null;
        }
        synchronized (CACHE) {
            FieldInfo fieldInfo = CACHE.get(field);
            boolean isEnumConstant = field.isEnumConstant();
            if (fieldInfo == null && (isEnumConstant || !Modifier.isStatic(field.getModifiers()))) {
                if (isEnumConstant) {
                    Value value = (Value) field.getAnnotation(Value.class);
                    if (value != null) {
                        str = value.value();
                    } else if (((NullValue) field.getAnnotation(NullValue.class)) == null) {
                        return null;
                    }
                } else {
                    Key key = (Key) field.getAnnotation(Key.class);
                    if (key == null) {
                        return null;
                    }
                    str = key.value();
                    field.setAccessible(true);
                }
                if ("##default".equals(str)) {
                    str = field.getName();
                }
                fieldInfo = new FieldInfo(field, str);
                CACHE.put(field, fieldInfo);
            }
            return fieldInfo;
        }
    }

    FieldInfo(Field field, String str) {
        this.field = field;
        this.name = str == null ? null : str.intern();
        this.isPrimitive = Data.isPrimitive(getType());
        this.setters = settersMethodForField(field);
    }

    private Method[] settersMethodForField(Field field) {
        ArrayList arrayList = new ArrayList();
        for (Method method : field.getDeclaringClass().getDeclaredMethods()) {
            if (Ascii.toLowerCase(method.getName()).equals(TmpConstant.PROPERTY_IDENTIFIER_SET + Ascii.toLowerCase(field.getName())) && method.getParameterTypes().length == 1) {
                arrayList.add(method);
            }
        }
        return (Method[]) arrayList.toArray(new Method[0]);
    }

    public Field getField() {
        return this.field;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getType() {
        return this.field.getType();
    }

    public Type getGenericType() {
        return this.field.getGenericType();
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.field.getModifiers());
    }

    public boolean isPrimitive() {
        return this.isPrimitive;
    }

    public Object getValue(Object obj) {
        return getFieldValue(this.field, obj);
    }

    public void setValue(Object obj, Object obj2) {
        Method[] methodArr = this.setters;
        if (methodArr.length > 0) {
            for (Method method : methodArr) {
                if (obj2 == null || method.getParameterTypes()[0].isAssignableFrom(obj2.getClass())) {
                    try {
                        method.invoke(obj, obj2);
                        return;
                    } catch (IllegalAccessException | InvocationTargetException unused) {
                        continue;
                    }
                }
            }
        }
        setFieldValue(this.field, obj, obj2);
    }

    public ClassInfo getClassInfo() {
        return ClassInfo.m572of(this.field.getDeclaringClass());
    }

    public <T extends Enum<T>> T enumValue() {
        return (T) Enum.valueOf(this.field.getDeclaringClass(), this.field.getName());
    }

    public static Object getFieldValue(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void setFieldValue(Field field, Object obj, Object obj2) {
        if (Modifier.isFinal(field.getModifiers())) {
            Object fieldValue = getFieldValue(field, obj);
            if (obj2 == null) {
                if (fieldValue == null) {
                    return;
                }
            } else if (obj2.equals(fieldValue)) {
                return;
            }
            throw new IllegalArgumentException("expected final value <" + fieldValue + "> but was <" + obj2 + "> on " + field.getName() + " field in " + obj.getClass().getName());
        }
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (SecurityException e2) {
            throw new IllegalArgumentException(e2);
        }
    }
}
