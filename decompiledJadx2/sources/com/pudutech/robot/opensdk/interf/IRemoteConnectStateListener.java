package com.pudutech.robot.opensdk.interf;

import com.pudutech.robot.opensdk.RemoteConnectState;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IRemoteConnectStateListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "", "onConnectState", "", "state", "Lcom/pudutech/robot/opensdk/RemoteConnectState;", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface IRemoteConnectStateListener {
    void onConnectState(RemoteConnectState state);
}
