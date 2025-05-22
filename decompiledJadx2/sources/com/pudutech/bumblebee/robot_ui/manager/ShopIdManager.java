package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;
import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: ShopIdManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/manager/ShopIdManager;", "Lcom/pudutech/bumblebee/robot_ui/manager/InitInterface;", "()V", "TAG", "", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "init", "", "context", "Landroid/content/Context;", "registerNetworkCallback", "unRegisterNetworkCallback", "updateShopId", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class ShopIdManager implements InitInterface {
    private static ConnectivityManager.NetworkCallback networkCallback;
    public static final ShopIdManager INSTANCE = new ShopIdManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private ShopIdManager() {
    }

    @Override // com.pudutech.bumblebee.robot_ui.manager.InitInterface
    public void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        updateShopId(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateShopId(Context context) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ShopIdManager$updateShopId$1(context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void registerNetworkCallback(final Context context) {
        Pdlog.m3273d(TAG, "registerNetworkCallback networkCallback: " + networkCallback);
        if (networkCallback == null) {
            networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.pudutech.bumblebee.robot_ui.manager.ShopIdManager$registerNetworkCallback$1
                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onAvailable(Network network) {
                    String str;
                    super.onAvailable(network);
                    ShopIdManager shopIdManager = ShopIdManager.INSTANCE;
                    str = ShopIdManager.TAG;
                    Pdlog.m3273d(str, "networkCallback onAvailable");
                    ShopIdManager.INSTANCE.unRegisterNetworkCallback(context);
                    ShopIdManager.INSTANCE.updateShopId(context);
                }

                @Override // android.net.ConnectivityManager.NetworkCallback
                public void onLost(Network network) {
                    String str;
                    super.onLost(network);
                    ShopIdManager shopIdManager = ShopIdManager.INSTANCE;
                    str = ShopIdManager.TAG;
                    Pdlog.m3273d(str, "networkCallback onLost");
                }
            };
            NetworkRequest build = new NetworkRequest.Builder().build();
            Object systemService = context != null ? context.getSystemService("connectivity") : null;
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
            }
            ((ConnectivityManager) systemService).registerNetworkCallback(build, networkCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unRegisterNetworkCallback(Context context) {
        Pdlog.m3273d(TAG, "unRegisterNetworkCallback");
        ConnectivityManager.NetworkCallback networkCallback2 = networkCallback;
        if (networkCallback2 != null) {
            try {
                Object systemService = context.getSystemService("connectivity");
                if (systemService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
                }
                ((ConnectivityManager) systemService).unregisterNetworkCallback(networkCallback2);
            } catch (Exception e) {
                Pdlog.m3273d(TAG, Log.getStackTraceString(e));
            }
        }
        networkCallback = (ConnectivityManager.NetworkCallback) null;
    }
}
