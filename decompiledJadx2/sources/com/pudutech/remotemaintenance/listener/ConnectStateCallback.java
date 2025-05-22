package com.pudutech.remotemaintenance.listener;

import kotlin.Metadata;

/* compiled from: ConnectStateCallback.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/listener/ConnectStateCallback;", "", "onConnectFailure", "", "errCode", "", "errMsg", "", "onConnected", "onConnecting", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface ConnectStateCallback {
    void onConnectFailure(int errCode, String errMsg);

    void onConnected();

    void onConnecting();
}
