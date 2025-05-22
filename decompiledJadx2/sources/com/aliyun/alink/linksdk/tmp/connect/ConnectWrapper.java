package com.aliyun.alink.linksdk.tmp.connect;

import com.aliyun.alink.linksdk.tmp.connect.IConnect;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class ConnectWrapper implements IConnect {
    private IConnect.ConnectType mConnectType;

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public boolean setOption(String str, int i, Object obj) {
        return false;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.IConnect
    public IConnect.ConnectType getConnectType() {
        return this.mConnectType;
    }
}
