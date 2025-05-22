package com.aliyun.alink.linksdk.tmp.api;

import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.google.gson.reflect.TypeToken;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InputParams<T> {
    protected T mData;

    public InputParams(T t) {
        this.mData = t;
    }

    public InputParams() {
    }

    public T getData() {
        return this.mData;
    }

    public void fromJson(String str) {
        this.mData = (T) GsonUtils.fromJson(str, new TypeToken<T>() { // from class: com.aliyun.alink.linksdk.tmp.api.InputParams.1
        }.getType());
    }
}
