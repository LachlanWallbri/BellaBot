package io.opencensus.contrib.http.util;

import io.opencensus.trace.propagation.TextFormat;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public class HttpPropagationUtil {
    private HttpPropagationUtil() {
    }

    public static TextFormat getCloudTraceFormat() {
        return new CloudTraceFormat();
    }
}
