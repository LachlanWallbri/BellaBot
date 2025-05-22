package com.amazonaws.services.p048s3.model.analytics;

import java.io.Serializable;

/* loaded from: classes.dex */
public class AnalyticsConfiguration implements Serializable {
    private AnalyticsFilter filter;

    /* renamed from: id */
    private String f1194id;
    private StorageClassAnalysis storageClassAnalysis;

    public String getId() {
        return this.f1194id;
    }

    public void setId(String str) {
        this.f1194id = str;
    }

    public AnalyticsConfiguration withId(String str) {
        setId(str);
        return this;
    }

    public AnalyticsFilter getFilter() {
        return this.filter;
    }

    public void setFilter(AnalyticsFilter analyticsFilter) {
        this.filter = analyticsFilter;
    }

    public AnalyticsConfiguration withFilter(AnalyticsFilter analyticsFilter) {
        setFilter(analyticsFilter);
        return this;
    }

    public StorageClassAnalysis getStorageClassAnalysis() {
        return this.storageClassAnalysis;
    }

    public void setStorageClassAnalysis(StorageClassAnalysis storageClassAnalysis) {
        this.storageClassAnalysis = storageClassAnalysis;
    }

    public AnalyticsConfiguration withStorageClassAnalysis(StorageClassAnalysis storageClassAnalysis) {
        setStorageClassAnalysis(storageClassAnalysis);
        return this;
    }
}
