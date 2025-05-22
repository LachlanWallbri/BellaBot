package com.pudutech.leaselib.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.pudutech.leaselib.LeaseSdk;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: NetWorkChangeReceiver.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\bH\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/leaselib/net/NetWorkChangeReceiver;", "Lcom/pudutech/leaselib/net/NetworkChangeEvent;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onNetWorkConnect", "Lkotlin/Function0;", "", "getOnNetWorkConnect", "()Lkotlin/jvm/functions/Function0;", "setOnNetWorkConnect", "(Lkotlin/jvm/functions/Function0;)V", "isConnected", "", "context", "Landroid/content/Context;", "onNetworkChange", "lib_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class NetWorkChangeReceiver extends NetworkChangeEvent {
    private final String TAG = getClass().getSimpleName();
    private Function0<Unit> onNetWorkConnect;

    public final Function0<Unit> getOnNetWorkConnect() {
        return this.onNetWorkConnect;
    }

    public final void setOnNetWorkConnect(Function0<Unit> function0) {
        this.onNetWorkConnect = function0;
    }

    @Override // com.pudutech.leaselib.net.NetworkChangeEvent
    public void onNetworkChange() {
        Function0<Unit> function0;
        if (!isConnected(LeaseSdk.INSTANCE.getContext()) || (function0 = this.onNetWorkConnect) == null) {
            return;
        }
        function0.invoke();
    }

    private final boolean isConnected(Context context) {
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            try {
                Intrinsics.throwNpe();
            } catch (Exception unused) {
                return false;
            }
        }
        return activeNetworkInfo.isConnected();
    }
}
