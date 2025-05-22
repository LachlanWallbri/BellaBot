package com.amazonaws.services.p048s3.model.analytics;

import java.io.Serializable;

/* loaded from: classes.dex */
public enum AnalyticsS3ExportFileFormat implements Serializable {
    CSV("CSV");

    private final String format;

    AnalyticsS3ExportFileFormat(String str) {
        this.format = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.format;
    }
}
