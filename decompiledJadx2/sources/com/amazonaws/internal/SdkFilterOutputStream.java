package com.amazonaws.internal;

import java.io.FilterOutputStream;
import java.io.OutputStream;

@Deprecated
/* loaded from: classes.dex */
public class SdkFilterOutputStream extends FilterOutputStream implements MetricAware {
    public SdkFilterOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // com.amazonaws.internal.MetricAware
    public boolean isMetricActivated() {
        if (this.out instanceof MetricAware) {
            return ((MetricAware) this.out).isMetricActivated();
        }
        return false;
    }
}
