package com.google.common.cache;

/* JADX WARN: Classes with same name are omitted:
  
 */
@FunctionalInterface
/* loaded from: classes3.dex */
public interface RemovalListener<K, V> {
    void onRemoval(RemovalNotification<K, V> removalNotification);
}
