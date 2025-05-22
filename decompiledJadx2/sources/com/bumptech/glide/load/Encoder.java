package com.bumptech.glide.load;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Encoder<T> {
    boolean encode(T t, File file, Options options);
}
