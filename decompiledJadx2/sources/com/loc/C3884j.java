package com.loc;

/* compiled from: AMapCoreException.java */
/* renamed from: com.loc.j */
/* loaded from: classes4.dex */
public final class C3884j extends Exception {

    /* renamed from: a */
    private String f4222a;

    /* renamed from: b */
    private int f4223b;

    public C3884j(String str) {
        super(str);
        int i;
        this.f4222a = "未知的错误";
        this.f4223b = -1;
        this.f4222a = str;
        if ("IO 操作异常 - IOException".equals(str)) {
            i = 21;
        } else if ("socket 连接异常 - SocketException".equals(str)) {
            i = 22;
        } else if ("socket 连接超时 - SocketTimeoutException".equals(str)) {
            i = 23;
        } else if ("无效的参数 - IllegalArgumentException".equals(str)) {
            i = 24;
        } else if ("空指针异常 - NullPointException".equals(str)) {
            i = 25;
        } else if ("url异常 - MalformedURLException".equals(str)) {
            i = 26;
        } else if ("未知主机 - UnKnowHostException".equals(str)) {
            i = 27;
        } else if ("服务器连接失败 - UnknownServiceException".equals(str)) {
            i = 28;
        } else if ("协议解析错误 - ProtocolException".equals(str)) {
            i = 29;
        } else if ("http连接失败 - ConnectionException".equals(str)) {
            i = 30;
        } else if ("未知的错误".equals(str)) {
            i = 31;
        } else if ("key鉴权失败".equals(str)) {
            i = 32;
        } else if ("requeust is null".equals(str)) {
            i = 1;
        } else if ("request url is empty".equals(str)) {
            i = 2;
        } else if ("response is null".equals(str)) {
            i = 3;
        } else if ("thread pool has exception".equals(str)) {
            i = 4;
        } else if ("sdk name is invalid".equals(str)) {
            i = 5;
        } else if ("sdk info is null".equals(str)) {
            i = 6;
        } else if ("sdk packages is null".equals(str)) {
            i = 7;
        } else if ("线程池为空".equals(str)) {
            i = 8;
        } else {
            if (!"获取对象错误".equals(str)) {
                this.f4223b = -1;
                return;
            }
            i = 101;
        }
        this.f4223b = i;
    }

    /* renamed from: a */
    public final String m3118a() {
        return this.f4222a;
    }

    /* renamed from: b */
    public final int m3119b() {
        return this.f4223b;
    }
}
