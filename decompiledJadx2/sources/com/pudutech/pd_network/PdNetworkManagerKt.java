package com.pudutech.pd_network;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.pd_network.bean.PdNetworkManagerBuilder;
import com.pudutech.pd_network.bean.ServiceGatewayConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: PdNetworkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010\u0003\u001a\u001e\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007\u001a\u0012\u0010\t\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b\u001a\u001e\u0010\f\u001a\u00020\u0005*\u00020\r2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\u0007¨\u0006\u000f"}, m3961d2 = {"createService", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/pudutech/pd_network/IPdNetworkManager;", "(Lcom/pudutech/pd_network/IPdNetworkManager;)Ljava/lang/Object;", "hardID", "", "block", "Lkotlin/Function1;", "", "init", "context", "Landroid/content/Context;", "setOnGatewayAction", "Lcom/pudutech/pd_network/IGateway;", "Lcom/pudutech/pd_network/bean/ServiceGatewayConfig;", "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PdNetworkManagerKt {
    public static final void init(IPdNetworkManager init, Context context) {
        Intrinsics.checkParameterIsNotNull(init, "$this$init");
        Intrinsics.checkParameterIsNotNull(context, "context");
        init.init(context, new Function1<PdNetworkManagerBuilder, Unit>() { // from class: com.pudutech.pd_network.PdNetworkManagerKt$init$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PdNetworkManagerBuilder pdNetworkManagerBuilder) {
                invoke2(pdNetworkManagerBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PdNetworkManagerBuilder receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
            }
        });
    }

    public static final /* synthetic */ <T> T createService(IPdNetworkManager createService) {
        Intrinsics.checkParameterIsNotNull(createService, "$this$createService");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) createService.createService(Object.class);
    }

    public static final void setOnGatewayAction(final IGateway setOnGatewayAction, final Function1<? super ServiceGatewayConfig, Unit> block) {
        Intrinsics.checkParameterIsNotNull(setOnGatewayAction, "$this$setOnGatewayAction");
        Intrinsics.checkParameterIsNotNull(block, "block");
        setOnGatewayAction.addOnGatewayAction(new OnGatewayAction() { // from class: com.pudutech.pd_network.PdNetworkManagerKt$setOnGatewayAction$1
            @Override // com.pudutech.pd_network.OnGatewayAction
            public void onException(Throwable e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
            }

            @Override // com.pudutech.pd_network.OnGatewayAction
            public void onSuccess(ServiceGatewayConfig bean) {
                Intrinsics.checkParameterIsNotNull(bean, "bean");
                IGateway.this.removeOnGatewayAction(this);
                block.invoke(bean);
            }
        });
    }

    public static final void hardID(IPdNetworkManager hardID, Function1<? super String, Unit> block) {
        Intrinsics.checkParameterIsNotNull(hardID, "$this$hardID");
        Intrinsics.checkParameterIsNotNull(block, "block");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new PdNetworkManagerKt$hardID$1(hardID, block, null), 3, null);
    }
}
