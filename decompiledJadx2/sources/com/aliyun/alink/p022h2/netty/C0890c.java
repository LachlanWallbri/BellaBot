package com.aliyun.alink.p022h2.netty;

import io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder;
import io.netty.handler.codec.http2.Http2ConnectionDecoder;
import io.netty.handler.codec.http2.Http2ConnectionEncoder;
import io.netty.handler.codec.http2.Http2FrameLogger;
import io.netty.handler.codec.http2.Http2Settings;
import io.netty.handler.logging.LogLevel;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: NettyHttp2HandlerBuilder.java */
/* renamed from: com.aliyun.alink.h2.netty.c */
/* loaded from: classes.dex */
public class C0890c extends AbstractHttp2ConnectionHandlerBuilder<C0889b, C0890c> {

    /* renamed from: a */
    private long f604a;

    /* renamed from: b */
    private String f605b = "debug";

    /* renamed from: c */
    private Map<String, LogLevel> f606c = new HashMap<String, LogLevel>() { // from class: com.aliyun.alink.h2.netty.NettyHttp2HandlerBuilder$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            put(MqttServiceConstants.TRACE_ACTION, LogLevel.TRACE);
            put("debug", LogLevel.DEBUG);
            put("info", LogLevel.INFO);
            put("warn", LogLevel.WARN);
            put("error", LogLevel.ERROR);
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    public boolean isServer() {
        return false;
    }

    public C0890c(long j) {
        this.f604a = j;
        frameLogger(m254b());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C0889b build() {
        return (C0889b) super.build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http2.AbstractHttp2ConnectionHandlerBuilder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public C0889b build(Http2ConnectionDecoder http2ConnectionDecoder, Http2ConnectionEncoder http2ConnectionEncoder, Http2Settings http2Settings) {
        C0889b c0889b = new C0889b(http2ConnectionDecoder, http2ConnectionEncoder, http2Settings, this.f604a);
        frameListener(c0889b);
        initialSettings().pushEnabled(true);
        initialSettings().initialWindowSize(1073741824);
        initialSettings().maxFrameSize(1048576);
        return c0889b;
    }

    /* renamed from: b */
    private Http2FrameLogger m254b() {
        LogLevel logLevel = this.f606c.get(this.f605b.toLowerCase());
        if (logLevel == null) {
            logLevel = LogLevel.INFO;
        }
        InternalLoggerFactory.setDefaultFactory(new C0892e());
        return new Http2FrameLogger(logLevel, (Class<?>) C0892e.class);
    }
}
