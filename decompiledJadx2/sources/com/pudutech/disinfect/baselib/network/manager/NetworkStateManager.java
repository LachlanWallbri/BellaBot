package com.pudutech.disinfect.baselib.network.manager;

import com.pudutech.disinfect.baselib.callback.UnPeekLiveData;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NetwrokStateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/manager/NetworkStateManager;", "", "()V", "networkStateCallback", "Lcom/pudutech/disinfect/baselib/callback/UnPeekLiveData;", "Lcom/pudutech/disinfect/baselib/network/manager/NetState;", "getNetworkStateCallback", "()Lcom/pudutech/disinfect/baselib/callback/UnPeekLiveData;", "Companion", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NetworkStateManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<NetworkStateManager>() { // from class: com.pudutech.disinfect.baselib.network.manager.NetworkStateManager$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final NetworkStateManager invoke() {
            return new NetworkStateManager(null);
        }
    });
    private final UnPeekLiveData<NetState> networkStateCallback;

    private NetworkStateManager() {
        this.networkStateCallback = new UnPeekLiveData<>();
    }

    public /* synthetic */ NetworkStateManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final UnPeekLiveData<NetState> getNetworkStateCallback() {
        return this.networkStateCallback;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* compiled from: NetwrokStateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/manager/NetworkStateManager$Companion;", "", "()V", "instance", "Lcom/pudutech/disinfect/baselib/network/manager/NetworkStateManager;", "getInstance", "()Lcom/pudutech/disinfect/baselib/network/manager/NetworkStateManager;", "instance$delegate", "Lkotlin/Lazy;", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        public final NetworkStateManager getInstance() {
            Lazy lazy = NetworkStateManager.instance$delegate;
            Companion companion = NetworkStateManager.INSTANCE;
            return (NetworkStateManager) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
