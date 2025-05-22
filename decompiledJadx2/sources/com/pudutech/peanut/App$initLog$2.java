package com.pudutech.peanut;

import android.util.Log;
import com.pudutech.mirsdk.update.ApiConstants;
import com.pudutech.peanut.robot_ui.util.finishapp.CrashReportConfig;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: App.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.App$initLog$2", m3970f = "App.kt", m3971i = {0}, m3972l = {127}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class App$initLog$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6900p$;
    final /* synthetic */ App this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public App$initLog$2(App app, Continuation continuation) {
        super(2, continuation);
        this.this$0 = app;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        App$initLog$2 app$initLog$2 = new App$initLog$2(this.this$0, completion);
        app$initLog$2.f6900p$ = (CoroutineScope) obj;
        return app$initLog$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((App$initLog$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6900p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        do {
            if (CoroutineScopeKt.isActive(coroutineScope)) {
                this.this$0.getMac();
                if (this.this$0.getMAC().equals(ApiConstants.MAC_ADDRESS)) {
                    this.L$0 = coroutineScope;
                    this.label = 1;
                } else {
                    App app = this.this$0;
                    app.setMAC(StringsKt.replace$default(app.getMAC(), ":", "", false, 4, (Object) null));
                    str = this.this$0.TAG;
                    Log.d(str, "MAC " + this.this$0.getMAC());
                    CrashReportConfig.INSTANCE.resetRobotSerialID(this.this$0.getMAC());
                }
            }
            return Unit.INSTANCE;
        } while (DelayKt.delay(10000L, this) != coroutine_suspended);
        return coroutine_suspended;
    }
}
