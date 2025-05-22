package org.yaml.snakeyaml.tokens;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class TagTuple {
    private final String handle;
    private final String suffix;

    public TagTuple(String str, String str2) {
        if (str2 == null) {
            throw new NullPointerException("Suffix must be provided.");
        }
        this.handle = str;
        this.suffix = str2;
    }

    public String getHandle() {
        return this.handle;
    }

    public String getSuffix() {
        return this.suffix;
    }
}
