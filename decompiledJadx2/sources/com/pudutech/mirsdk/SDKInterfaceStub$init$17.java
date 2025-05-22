package com.pudutech.mirsdk;

import android.content.Context;
import com.pudutech.mirsdk.aidl.ISDKListener;
import com.pudutech.mirsdk.config.ToolsUpgrade;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: SDKService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SDKInterfaceStub$init$17", m3970f = "SDKService.kt", m3971i = {0, 0}, m3972l = {171}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "toolsUpgrade"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes5.dex */
public final class SDKInterfaceStub$init$17 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5569p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SDKInterfaceStub$init$17(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SDKInterfaceStub$init$17 sDKInterfaceStub$init$17 = new SDKInterfaceStub$init$17(completion);
        sDKInterfaceStub$init$17.f5569p$ = (CoroutineScope) obj;
        return sDKInterfaceStub$init$17;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SDKInterfaceStub$init$17) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: SDKService.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m3961d2 = {"<anonymous>", "", "l", "Lcom/pudutech/mirsdk/aidl/ISDKListener;", "<anonymous parameter 1>", "", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.SDKInterfaceStub$init$17$1 */
    /* loaded from: classes4.dex */
    static final class C47951 extends Lambda implements Function2<ISDKListener, String, Unit> {
        final /* synthetic */ int $it;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C47951(int i) {
            super(2);
            this.$it = i;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(ISDKListener iSDKListener, String str) {
            invoke2(iSDKListener, str);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(ISDKListener l, String str) {
            Intrinsics.checkParameterIsNotNull(l, "l");
            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
            l.onBatterySOH(this.$it);
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Context context;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5569p$;
            SDKInterfaceStub sDKInterfaceStub = SDKInterfaceStub.INSTANCE;
            context = SDKInterfaceStub.context;
            if (context == null) {
                Intrinsics.throwNpe();
            }
            ToolsUpgrade toolsUpgrade = new ToolsUpgrade(context);
            this.L$0 = coroutineScope;
            this.L$1 = toolsUpgrade;
            this.label = 1;
            if (toolsUpgrade.chkMapifyAndRGBDViewer(true, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
