package com.pudutech.pd_network;

import kotlin.Metadata;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0005H&J\b\u0010\u000b\u001a\u00020\u0005H&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/pd_network/IOssTaskController;", "", "currentState", "Lcom/pudutech/pd_network/OssState;", "pause", "", "resume", "setCallback", "callback", "Lcom/pudutech/pd_network/OssCallback;", "start", "stop", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IOssTaskController {
    OssState currentState();

    void pause();

    void resume();

    void setCallback(OssCallback callback);

    void start();

    void stop();
}
