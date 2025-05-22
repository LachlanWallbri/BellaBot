package com.pudutech.disinfect.baselib.network.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pudutech.disinfect.baselib.network.NetWorkUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NetworkStateReceive.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/manager/NetworkStateReceive;", "Landroid/content/BroadcastReceiver;", "()V", "isInit", "", "()Z", "setInit", "(Z)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NetworkStateReceive extends BroadcastReceiver {
    private boolean isInit = true;

    /* renamed from: isInit, reason: from getter */
    public final boolean getIsInit() {
        return this.isInit;
    }

    public final void setInit(boolean z) {
        this.isInit = z;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        if (Intrinsics.areEqual(intent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE")) {
            if (!this.isInit) {
                if (!NetWorkUtil.INSTANCE.isNetworkAvailable(context)) {
                    NetState value = NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().getValue();
                    if (value != null) {
                        if (value.getIsSuccess()) {
                            NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().postValue(new NetState(false));
                            return;
                        }
                        return;
                    }
                    NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().postValue(new NetState(false));
                } else {
                    NetState value2 = NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().getValue();
                    if (value2 != null) {
                        if (value2.getIsSuccess()) {
                            return;
                        }
                        NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().postValue(new NetState(true));
                        return;
                    }
                    NetworkStateManager.INSTANCE.getInstance().getNetworkStateCallback().postValue(new NetState(true));
                }
            }
            this.isInit = false;
        }
    }
}
