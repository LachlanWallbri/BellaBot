package com.amazonaws.services.cognitoidentity.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum AmbiguousRoleResolutionType {
    AuthenticatedRole("AuthenticatedRole"),
    Deny("Deny");

    private static final Map<String, AmbiguousRoleResolutionType> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("AuthenticatedRole", AuthenticatedRole);
        enumMap.put("Deny", Deny);
    }

    AmbiguousRoleResolutionType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static AmbiguousRoleResolutionType fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
