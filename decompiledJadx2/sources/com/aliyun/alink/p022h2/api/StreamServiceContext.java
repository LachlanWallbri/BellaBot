package com.aliyun.alink.p022h2.api;

import com.aliyun.alink.p022h2.connection.Connection;
import com.aliyun.alink.p022h2.entity.BaseHttpEntity;
import com.aliyun.alink.p022h2.utils.StreamUtil;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class StreamServiceContext {

    /* renamed from: a */
    private Connection f519a;

    /* renamed from: b */
    private String f520b;

    /* renamed from: c */
    private String f521c;

    /* renamed from: d */
    private String f522d;

    /* renamed from: e */
    private String f523e;

    public StreamServiceContext(Connection connection, BaseHttpEntity baseHttpEntity, String str) {
        this.f519a = connection;
        this.f523e = str;
        updateContext(baseHttpEntity);
    }

    public void updateContext(BaseHttpEntity baseHttpEntity) {
        this.f520b = baseHttpEntity.getRequestId();
        this.f521c = baseHttpEntity.getHeaders().path().toString();
        this.f522d = StreamUtil.getDataStreamId(baseHttpEntity.getHeaders());
    }

    public Connection getConnection() {
        return this.f519a;
    }

    public String getRequestId() {
        return this.f520b;
    }

    public String getRequestPath() {
        return this.f521c;
    }

    public String getDataStreamId() {
        return this.f522d;
    }

    public String getServiceName() {
        return this.f523e;
    }
}
