package com.pudutech.pd_network;

import com.pudutech.pd_network.bean.GatewayBean;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.bean.GatewayPlace;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: interface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u001a\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eH&J\u001a\u0010\u000f\u001a\u00020\u00032\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/pd_network/IGateway;", "", "addOnGatewayAction", "", "action", "Lcom/pudutech/pd_network/OnGatewayAction;", "gatewayPlace", "Lcom/pudutech/pd_network/bean/GatewayPlace;", "getGateway", "Lcom/pudutech/pd_network/bean/GatewayBean;", "mGatewayName", "Lcom/pudutech/pd_network/bean/GatewayName;", "getGatewayExecute", "throwException", "", "refreshGateway", "block", "Lkotlin/Function0;", "removeOnGatewayAction", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IGateway {
    void addOnGatewayAction(OnGatewayAction action);

    GatewayPlace gatewayPlace();

    GatewayBean getGateway(GatewayName mGatewayName) throws Exception;

    GatewayBean getGatewayExecute(GatewayName mGatewayName, boolean throwException);

    void refreshGateway(Function0<Unit> block);

    void removeOnGatewayAction(OnGatewayAction action);

    /* compiled from: interface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void refreshGateway$default(IGateway iGateway, Function0 function0, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: refreshGateway");
            }
            if ((i & 1) != 0) {
                function0 = (Function0) null;
            }
            iGateway.refreshGateway(function0);
        }

        public static /* synthetic */ GatewayBean getGatewayExecute$default(IGateway iGateway, GatewayName gatewayName, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getGatewayExecute");
            }
            if ((i & 2) != 0) {
                z = false;
            }
            return iGateway.getGatewayExecute(gatewayName, z);
        }
    }
}
