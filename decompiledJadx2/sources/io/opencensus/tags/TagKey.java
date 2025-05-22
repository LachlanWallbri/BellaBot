package io.opencensus.tags;

import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class TagKey {
    public static final int MAX_LENGTH = 255;

    public abstract String getName();

    public static TagKey create(String str) {
        Utils.checkArgument(isValid(str), "Invalid TagKey name: %s", str);
        return new AutoValue_TagKey(str);
    }

    private static boolean isValid(String str) {
        return !str.isEmpty() && str.length() <= 255 && StringUtils.isPrintableString(str);
    }
}
