package io.opencensus.metrics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class LabelKey {
    public abstract String getDescription();

    public abstract String getKey();

    public static LabelKey create(String str, String str2) {
        return new AutoValue_LabelKey(str, str2);
    }
}
