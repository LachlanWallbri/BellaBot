package com.chad.library.adapter.base.provider;

import android.content.Context;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class BaseItemProvider<T, V extends BaseViewHolder> {
    public Context mContext;
    public List<T> mData;

    public abstract void convert(V v, T t, int i);

    public abstract int layout();

    public void onClick(V v, T t, int i) {
    }

    public boolean onLongClick(V v, T t, int i) {
        return false;
    }

    public abstract int viewType();
}
