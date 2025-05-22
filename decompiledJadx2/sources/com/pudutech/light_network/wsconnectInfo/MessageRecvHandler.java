package com.pudutech.light_network.wsconnectInfo;

import okio.ByteString;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public interface MessageRecvHandler {
    void onMessageRecv(String str);

    void onMessageRecvByte(ByteString byteString);
}
