package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
interface ArrayAdapterInterface<T> {
    int getArrayLength(T t);

    int getElementSizeInBytes();

    String getTag();

    T newArray(int i);
}
