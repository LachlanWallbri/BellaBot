package com.aliyun.alink.dm.model;

import com.alibaba.fastjson.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class RequestModel<T> {

    /* renamed from: id */
    public String f462id = null;
    public String version = null;
    public String method = null;
    public T params = null;

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
