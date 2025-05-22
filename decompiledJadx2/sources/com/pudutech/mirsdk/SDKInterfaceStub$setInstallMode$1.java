package com.pudutech.mirsdk;

import android.content.Context;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.config.InstallationModeConfig;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
import com.pudutech.mirsdk.mircore.NavigationInterface;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$setInstallMode$1", m3970f = "SDKService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class SDKInterfaceStub$setInstallMode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $open;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5577p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SDKInterfaceStub$setInstallMode$1(boolean z, Continuation continuation) {
        super(2, continuation);
        this.$open = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$setInstallMode$1 sDKInterfaceStub$setInstallMode$1 = new SDKInterfaceStub$setInstallMode$1(this.$open, completion);
        sDKInterfaceStub$setInstallMode$1.f5577p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$setInstallMode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$setInstallMode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InstallationModeConfig installationModeConfig;
        Context context;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5577p$;
        SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
        installationModeConfig = SDKInterfaceStub.installationModeConfig;
        SDKInterfaceStub sDKInterfaceStub2 = SDKInterfaceStub.INSTANCE;
        context = SDKInterfaceStub.context;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        installationModeConfig.setInstallMode(context, this.$open, new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.SDKInterfaceStub$setInstallMode$1.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                AIDLConnection aIDLConnection;
                NavigationInterface navigator;
                SDKInterfaceStub sDKInterfaceStub3 = SDKInterfaceStub.INSTANCE;
                aIDLConnection = SDKInterfaceStub.coreService;
                MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
                if (mirCoreInterface == null || (navigator = mirCoreInterface.getNavigator()) == null) {
                    return;
                }
                navigator.updateDynamicConfig(z);
            }
        });
        return Unit.INSTANCE;
    }
}
