package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class EnumResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Enum<?> _defaultValue;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;

    protected EnumResolver(Class<Enum<?>> cls, Enum<?>[] enumArr, HashMap<String, Enum<?>> hashMap, Enum<?> r4) {
        this._enumClass = cls;
        this._enums = enumArr;
        this._enumsById = hashMap;
        this._defaultValue = r4;
    }

    public static EnumResolver constructFor(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum<?>[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            throw new IllegalArgumentException("No enum constants for class " + cls.getName());
        }
        String[] findEnumValues = annotationIntrospector.findEnumValues(cls, enumConstants, new String[enumConstants.length]);
        String[][] strArr = new String[findEnumValues.length];
        annotationIntrospector.findEnumAliases(cls, enumConstants, strArr);
        HashMap hashMap = new HashMap();
        int length = enumConstants.length;
        for (int i = 0; i < length; i++) {
            Enum<?> r7 = enumConstants[i];
            String str = findEnumValues[i];
            if (str == null) {
                str = r7.name();
            }
            hashMap.put(str, r7);
            String[] strArr2 = strArr[i];
            if (strArr2 != null) {
                for (String str2 : strArr2) {
                    if (!hashMap.containsKey(str2)) {
                        hashMap.put(str2, r7);
                    }
                }
            }
        }
        return new EnumResolver(cls, enumConstants, hashMap, annotationIntrospector.findDefaultEnumValue(cls));
    }

    @Deprecated
    public static EnumResolver constructUsingToString(Class<Enum<?>> cls) {
        return constructUsingToString(cls, null);
    }

    public static EnumResolver constructUsingToString(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Enum<?>[] enumConstants = cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        String[][] strArr = new String[enumConstants.length];
        annotationIntrospector.findEnumAliases(cls, enumConstants, strArr);
        int length = enumConstants.length;
        while (true) {
            length--;
            if (length >= 0) {
                Enum<?> r4 = enumConstants[length];
                hashMap.put(r4.toString(), r4);
                String[] strArr2 = strArr[length];
                if (strArr2 != null) {
                    for (String str : strArr2) {
                        if (!hashMap.containsKey(str)) {
                            hashMap.put(str, r4);
                        }
                    }
                }
            } else {
                return new EnumResolver(cls, enumConstants, hashMap, annotationIntrospector.findDefaultEnumValue(cls));
            }
        }
    }

    public static EnumResolver constructUsingMethod(Class<Enum<?>> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector) {
        Enum<?>[] enumConstants = cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumConstants.length;
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            Enum<?> r3 = enumConstants[length];
            try {
                Object value = annotatedMember.getValue(r3);
                if (value != null) {
                    hashMap.put(value.toString(), r3);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + r3 + ": " + e.getMessage());
            }
        }
        return new EnumResolver(cls, enumConstants, hashMap, annotationIntrospector != null ? annotationIntrospector.findDefaultEnumValue(cls) : null);
    }

    public static EnumResolver constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver constructUnsafeUsingToString(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructUsingToString(cls, annotationIntrospector);
    }

    public static EnumResolver constructUnsafeUsingMethod(Class<?> cls, AnnotatedMember annotatedMember, AnnotationIntrospector annotationIntrospector) {
        return constructUsingMethod(cls, annotatedMember, annotationIntrospector);
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    public Enum<?> findEnum(String str) {
        return this._enumsById.get(str);
    }

    public Enum<?> getEnum(int i) {
        if (i < 0) {
            return null;
        }
        Enum<?>[] enumArr = this._enums;
        if (i >= enumArr.length) {
            return null;
        }
        return enumArr[i];
    }

    public Enum<?> getDefaultValue() {
        return this._defaultValue;
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public List<Enum<?>> getEnums() {
        ArrayList arrayList = new ArrayList(this._enums.length);
        for (Enum<?> r0 : this._enums) {
            arrayList.add(r0);
        }
        return arrayList;
    }

    public Collection<String> getEnumIds() {
        return this._enumsById.keySet();
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
