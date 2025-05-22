package com.amazonaws.services.cognitoidentityprovider.model;

import androidx.exifinterface.media.ExifInterface;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum AttributeDataType {
    String("String"),
    Number("Number"),
    DateTime(ExifInterface.TAG_DATETIME),
    Boolean("Boolean");

    private static final Map<String, AttributeDataType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("String", String);
        enumMap.put("Number", Number);
        enumMap.put(ExifInterface.TAG_DATETIME, DateTime);
        enumMap.put("Boolean", Boolean);
    }

    AttributeDataType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AttributeDataType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
