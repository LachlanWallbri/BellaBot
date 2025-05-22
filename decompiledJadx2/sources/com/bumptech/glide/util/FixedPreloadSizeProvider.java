package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int i, int i2) {
        this.size = new int[]{i, i2};
    }

    @Override // com.bumptech.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int i, int i2) {
        return this.size;
    }
}
