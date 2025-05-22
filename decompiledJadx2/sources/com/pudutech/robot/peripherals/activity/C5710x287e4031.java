package com.pudutech.robot.peripherals.activity;

import com.pudutech.robot.peripherals.disinfection.UvcLampDeviceError;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NineTailsDebugActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1$invoke$1", m3970f = "NineTailsDebugActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* renamed from: com.pudutech.robot.peripherals.activity.NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1$invoke$1 */
/* loaded from: classes6.dex */
final class C5710x287e4031 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $l */
    final /* synthetic */ UvcLampDeviceError[] f7295$l;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7296p$;
    final /* synthetic */ NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5710x287e4031(NineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1 nineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1, UvcLampDeviceError[] uvcLampDeviceErrorArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = nineTailsDebugActivity$onUvLampDeviceErrorChangeListener$1;
        this.f7295$l = uvcLampDeviceErrorArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5710x287e4031 c5710x287e4031 = new C5710x287e4031(this.this$0, this.f7295$l, completion);
        c5710x287e4031.f7296p$ = (CoroutineScope) obj;
        return c5710x287e4031;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5710x287e4031) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f7296p$;
        UvcLampDeviceError[] uvcLampDeviceErrorArr = this.f7295$l;
        if (uvcLampDeviceErrorArr != null) {
            for (UvcLampDeviceError uvcLampDeviceError : uvcLampDeviceErrorArr) {
                String str = ("" + uvcLampDeviceError.name()) + ":";
            }
        }
        this.this$0.this$0.updateUvError("");
        return Unit.INSTANCE;
    }
}
