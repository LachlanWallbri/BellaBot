package com.aliyun.alink.p022h2.netty;

import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NettyHttp2LoggerFactory.java */
/* renamed from: com.aliyun.alink.h2.netty.e */
/* loaded from: classes.dex */
public class C0892e extends InternalLoggerFactory {

    /* renamed from: a */
    private static final NettyHttp2Logger f610a = new NettyHttp2Logger("NettyLog");

    @Override // io.netty.util.internal.logging.InternalLoggerFactory
    protected InternalLogger newInstance(String str) {
        return f610a;
    }
}
