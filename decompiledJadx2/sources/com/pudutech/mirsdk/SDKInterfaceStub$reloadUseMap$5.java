package com.pudutech.mirsdk;

import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "it", "Lcom/pudutech/mirsdk/aidl/ISDKListener;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class SDKInterfaceStub$reloadUseMap$5 extends Lambda implements Function2<ISDKListener, String, Unit> {
    final /* synthetic */ String $pdmap;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SDKInterfaceStub$reloadUseMap$5(String str) {
        super(2);
        this.$pdmap = str;
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
        it.onSwitchPdmapResult(SwitchMapResult.GoalMapNotExist, this.$pdmap);
    }
}
