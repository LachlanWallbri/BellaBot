package com.aliyun.alink.linksdk.cmp.core.listener;

import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IConnectNotifyListener {
    void onConnectStateChange(String str, ConnectState connectState);

    void onNotify(String str, String str2, AMessage aMessage);

    boolean shouldHandle(String str, String str2);
}
