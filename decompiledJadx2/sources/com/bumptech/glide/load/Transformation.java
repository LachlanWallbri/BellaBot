package com.bumptech.glide.load;

import android.content.Context;
import com.bumptech.glide.load.engine.Resource;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface Transformation<T> extends Key {
    Resource<T> transform(Context context, Resource<T> resource, int i, int i2);
}
