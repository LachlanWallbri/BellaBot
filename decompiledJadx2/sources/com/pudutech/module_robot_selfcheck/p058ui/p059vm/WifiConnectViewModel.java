package com.pudutech.module_robot_selfcheck.p058ui.p059vm;

import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.module_robot_selfcheck.domain.request.WifiConnectRequest;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: WifiConnectViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/ui/vm/WifiConnectViewModel;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "()V", "wifiConnectRequest", "Lcom/pudutech/module_robot_selfcheck/domain/request/WifiConnectRequest;", "getWifiConnectRequest", "()Lcom/pudutech/module_robot_selfcheck/domain/request/WifiConnectRequest;", "wifiConnectRequest$delegate", "Lkotlin/Lazy;", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class WifiConnectViewModel extends BaseViewModel {

    /* renamed from: wifiConnectRequest$delegate, reason: from kotlin metadata */
    private final Lazy wifiConnectRequest = LazyKt.lazy(new Function0<WifiConnectRequest>() { // from class: com.pudutech.module_robot_selfcheck.ui.vm.WifiConnectViewModel$wifiConnectRequest$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final WifiConnectRequest invoke() {
            return new WifiConnectRequest(WifiConnectViewModel.this);
        }
    });

    public final WifiConnectRequest getWifiConnectRequest() {
        return (WifiConnectRequest) this.wifiConnectRequest.getValue();
    }
}
