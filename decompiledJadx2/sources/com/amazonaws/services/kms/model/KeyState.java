package com.amazonaws.services.kms.model;

import com.amazonaws.services.p048s3.model.BucketLifecycleConfiguration;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum KeyState {
    Creating("Creating"),
    Enabled("Enabled"),
    Disabled(BucketLifecycleConfiguration.DISABLED),
    PendingDeletion("PendingDeletion"),
    PendingImport("PendingImport"),
    PendingReplicaDeletion("PendingReplicaDeletion"),
    Unavailable("Unavailable"),
    Updating("Updating");

    private static final Map<String, KeyState> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("Creating", Creating);
        enumMap.put("Enabled", Enabled);
        enumMap.put(BucketLifecycleConfiguration.DISABLED, Disabled);
        enumMap.put("PendingDeletion", PendingDeletion);
        enumMap.put("PendingImport", PendingImport);
        enumMap.put("PendingReplicaDeletion", PendingReplicaDeletion);
        enumMap.put("Unavailable", Unavailable);
        enumMap.put("Updating", Updating);
    }

    KeyState(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static KeyState fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
