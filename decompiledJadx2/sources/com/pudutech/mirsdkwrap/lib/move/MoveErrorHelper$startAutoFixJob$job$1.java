package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import com.pudutech.mirsdkwrap.lib.robot.device.LidarDeviceControl;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveErrorHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveErrorHelper$startAutoFixJob$job$1", m3970f = "MoveErrorHelper.kt", m3971i = {0}, m3972l = {201}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class MoveErrorHelper$startAutoFixJob$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $cb;

    /* renamed from: $e */
    final /* synthetic */ MoveError f6608$e;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6609p$;
    final /* synthetic */ MoveErrorHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveErrorHelper$startAutoFixJob$job$1(MoveErrorHelper moveErrorHelper, MoveError moveError, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveErrorHelper;
        this.f6608$e = moveError;
        this.$cb = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveErrorHelper$startAutoFixJob$job$1 moveErrorHelper$startAutoFixJob$job$1 = new MoveErrorHelper$startAutoFixJob$job$1(this.this$0, this.f6608$e, this.$cb, completion);
        moveErrorHelper$startAutoFixJob$job$1.f6609p$ = (CoroutineScope) obj;
        return moveErrorHelper$startAutoFixJob$job$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveErrorHelper$startAutoFixJob$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        DevicesControlHelper devicesControlHelper;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6609p$;
            devicesControlHelper = this.this$0.devicesControlHelper;
            LidarDeviceControl lidarDeviceControl = devicesControlHelper.getLidarDeviceControl();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = lidarDeviceControl.restart$module_robot_mirsdk_wrapper_release(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.checkFixJobAndCallBack(this.f6608$e.getError_type(), ((Boolean) obj).booleanValue(), this.$cb);
        return Unit.INSTANCE;
    }
}
