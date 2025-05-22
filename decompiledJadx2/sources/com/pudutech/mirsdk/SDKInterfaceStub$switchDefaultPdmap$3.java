package com.pudutech.mirsdk;

import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.aidl.serialize.SwitchMapResult;
import com.pudutech.mirsdk.mircore.ReloadMapResultListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005"}, m3961d2 = {"com/pudutech/mirsdk/SDKInterfaceStub$switchDefaultPdmap$3", "Lcom/pudutech/mirsdk/mircore/ReloadMapResultListener$Stub;", "reloadMapFail", "", "reloadMapSuccess", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class SDKInterfaceStub$switchDefaultPdmap$3 extends ReloadMapResultListener.Stub {
    final /* synthetic */ String $pdmap;
    final /* synthetic */ Ref.BooleanRef $waitCoreLoadMap;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SDKInterfaceStub$switchDefaultPdmap$3(Ref.BooleanRef booleanRef, String str) {
        this.$waitCoreLoadMap = booleanRef;
        this.$pdmap = str;
    }

    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
    public void reloadMapFail() {
        ThreadSafeListener threadSafeListener;
        this.$waitCoreLoadMap.element = false;
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        threadSafeListener = SDKInterfaceStub.sdkListeners;
        threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchDefaultPdmap$3$reloadMapFail$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                it.onSwitchPdmapResult(SwitchMapResult.MapReloadFailed, SDKInterfaceStub$switchDefaultPdmap$3.this.$pdmap);
            }
        });
    }

    @Override // com.pudutech.mirsdk.mircore.ReloadMapResultListener
    public void reloadMapSuccess() {
        ThreadSafeListener threadSafeListener;
        this.$waitCoreLoadMap.element = false;
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        threadSafeListener = SDKInterfaceStub.sdkListeners;
        threadSafeListener.notify(new Function2<ISDKListener, String, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$switchDefaultPdmap$3$reloadMapSuccess$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                it.onSwitchPdmapResult(SwitchMapResult.Finish, SDKInterfaceStub$switchDefaultPdmap$3.this.$pdmap);
            }
        });
    }
}
