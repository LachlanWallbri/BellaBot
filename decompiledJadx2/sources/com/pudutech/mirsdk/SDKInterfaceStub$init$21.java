package com.pudutech.mirsdk;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", TmpConstant.SERVICE_DESC, "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$init$21 extends Lambda implements Function2<RobotState, String, Unit> {
    public static final SDKInterfaceStub$init$21 INSTANCE = new SDKInterfaceStub$init$21();

    SDKInterfaceStub$init$21() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(final RobotState state, final String desc) {
        ThreadSafeListener threadSafeListener;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        threadSafeListener = SDKInterfaceStub.sdkListeners;
        threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$init$21.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
                invoke2(iSDKListener, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ISDKListener it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onStateChange(RobotState.this, desc);
            }
        });
    }
}
