package com.aliyun.alink.linksdk.channel.gateway.api;

import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public enum GatewayConnectState {
    CONNECTED,
    DISCONNECTED,
    CONNECTING,
    CONNECTFAIL;

    public static GatewayConnectState toGatewayConnectState(ConnectState connectState) {
        if (connectState == ConnectState.CONNECTED) {
            return CONNECTED;
        }
        if (connectState == ConnectState.DISCONNECTED) {
            return DISCONNECTED;
        }
        if (connectState == ConnectState.CONNECTING) {
            return CONNECTING;
        }
        return CONNECTFAIL;
    }
}
