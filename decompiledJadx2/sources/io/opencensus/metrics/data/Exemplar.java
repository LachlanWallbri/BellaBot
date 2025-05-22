package io.opencensus.metrics.data;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Exemplar {
    public abstract Map<String, AttachmentValue> getAttachments();

    public abstract Timestamp getTimestamp();

    public abstract double getValue();

    public static Exemplar create(double d, Timestamp timestamp, Map<String, AttachmentValue> map) {
        Utils.checkNotNull(map, "attachments");
        Map unmodifiableMap = Collections.unmodifiableMap(new HashMap(map));
        for (Map.Entry entry : unmodifiableMap.entrySet()) {
            Utils.checkNotNull(entry.getKey(), "key of attachments");
            Utils.checkNotNull(entry.getValue(), "value of attachments");
        }
        return new AutoValue_Exemplar(d, timestamp, unmodifiableMap);
    }
}
