package com.amazonaws.services.p048s3.model.analytics;

import java.io.Serializable;

/* loaded from: classes.dex */
public enum StorageClassAnalysisSchemaVersion implements Serializable {
    V_1("V_1");

    private final String version;

    StorageClassAnalysisSchemaVersion(String str) {
        this.version = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.version;
    }
}
