package com.google.gson;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes3.dex
 */
/* loaded from: classes.dex */
public interface ExclusionStrategy {
    boolean shouldSkipClass(Class<?> cls);

    boolean shouldSkipField(FieldAttributes fieldAttributes);
}
