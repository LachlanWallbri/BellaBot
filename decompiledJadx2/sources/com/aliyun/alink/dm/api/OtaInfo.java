package com.aliyun.alink.dm.api;

import com.alibaba.fastjson.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OtaInfo {
    public String md5;
    public int size;
    public String url;
    public String version;

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
