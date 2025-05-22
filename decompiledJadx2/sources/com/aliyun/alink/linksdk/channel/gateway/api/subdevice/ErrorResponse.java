package com.aliyun.alink.linksdk.channel.gateway.api.subdevice;

import com.alibaba.fastjson.JSONObject;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ErrorResponse<T> implements Serializable {
    public String code = null;
    public T data = null;
    public String message = null;

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
