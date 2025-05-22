package com.amazonaws.services.p048s3.model.analytics;

import java.io.Serializable;

/* loaded from: classes.dex */
public class StorageClassAnalysis implements Serializable {
    private StorageClassAnalysisDataExport dataExport;

    public StorageClassAnalysisDataExport getDataExport() {
        return this.dataExport;
    }

    public void setDataExport(StorageClassAnalysisDataExport storageClassAnalysisDataExport) {
        this.dataExport = storageClassAnalysisDataExport;
    }

    public StorageClassAnalysis withDataExport(StorageClassAnalysisDataExport storageClassAnalysisDataExport) {
        setDataExport(storageClassAnalysisDataExport);
        return this;
    }
}
