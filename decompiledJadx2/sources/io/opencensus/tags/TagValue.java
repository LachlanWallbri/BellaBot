package io.opencensus.tags;

import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class TagValue {
    public static final int MAX_LENGTH = 255;

    public abstract String asString();

    public static TagValue create(String str) {
        Utils.checkArgument(isValid(str), "Invalid TagValue: %s", str);
        return new AutoValue_TagValue(str);
    }

    private static boolean isValid(String str) {
        return str.length() <= 255 && StringUtils.isPrintableString(str);
    }
}
