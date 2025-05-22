package com.pudutech.bumblebee.business.ims.listener;

import kotlin.Metadata;

/* compiled from: IConnectStatusListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/listener/IConnectStatusListener;", "", "onConnected", "", "onConnecting", "onUnconnected", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IConnectStatusListener {
    void onConnected();

    void onConnecting();

    void onUnconnected();
}
