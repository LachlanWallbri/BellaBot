package com.aliyun.alink.p022h2.connection;

import io.netty.handler.codec.http2.Http2Settings;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ConnectionListener.java */
/* renamed from: com.aliyun.alink.h2.connection.b */
/* loaded from: classes.dex */
public interface InterfaceC0885b {
    void onSettingReceive(Connection connection, Http2Settings http2Settings);

    void onStatusChange(ConnectionStatus connectionStatus, Connection connection);
}
