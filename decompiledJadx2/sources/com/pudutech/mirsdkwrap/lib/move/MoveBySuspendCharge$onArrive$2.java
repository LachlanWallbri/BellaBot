package com.pudutech.mirsdkwrap.lib.move;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.interf.MoveBySuspendChargeListener;
import com.pudutech.mirsdkwrap.lib.move.bean.SuspendChargeArriveState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoveBySuspendCharge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveBySuspendCharge$onArrive$2", m3970f = "MoveBySuspendCharge.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class MoveBySuspendCharge$onArrive$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $description;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6592p$;
    final /* synthetic */ MoveBySuspendCharge this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveBySuspendCharge$onArrive$2(MoveBySuspendCharge moveBySuspendCharge, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveBySuspendCharge;
        this.$description = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveBySuspendCharge$onArrive$2 moveBySuspendCharge$onArrive$2 = new MoveBySuspendCharge$onArrive$2(this.this$0, this.$description, completion);
        moveBySuspendCharge$onArrive$2.f6592p$ = (CoroutineScope) obj;
        return moveBySuspendCharge$onArrive$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveBySuspendCharge$onArrive$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SuspendChargeArriveState arriveState;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6592p$;
        Pdlog.m3273d(this.this$0.getTAG(), "onArrive " + this.$description);
        String str2 = this.$description;
        if (str2 == null || StringsKt.isBlank(str2)) {
            arriveState = new SuspendChargeArriveState("", "");
        } else {
            try {
                String str3 = this.$description;
                if (str3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                String substring = str3.substring(0, 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Pdlog.m3273d(this.this$0.getTAG(), "onArrive subStr = " + substring);
                if (Intrinsics.areEqual(substring, "\"")) {
                    String str4 = this.$description;
                    int length = this.$description.length() - 1;
                    if (str4 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    str = str4.substring(1, length);
                    Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                } else {
                    str = this.$description;
                }
                Pdlog.m3273d(this.this$0.getTAG(), "onArrive jsonString = " + str);
                arriveState = (SuspendChargeArriveState) this.this$0.getGson().fromJson(str, SuspendChargeArriveState.class);
            } catch (Exception e) {
                Pdlog.m3274e(this.this$0.getTAG(), "onArrive : " + Log.getStackTraceString(e));
                arriveState = new SuspendChargeArriveState("", "");
            }
        }
        MoveBySuspendChargeListener onMoveStateListener = this.this$0.getOnMoveStateListener();
        if (onMoveStateListener != null) {
            Intrinsics.checkExpressionValueIsNotNull(arriveState, "arriveState");
            onMoveStateListener.onArrive(arriveState);
        }
        this.this$0.setCurrentMoveState$module_robot_mirsdk_wrapper_release(RobotState.Arrive);
        this.this$0.runAsyn(new C53471(null));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MoveBySuspendCharge.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveBySuspendCharge$onArrive$2$1", m3970f = "MoveBySuspendCharge.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveBySuspendCharge$onArrive$2$1 */
    /* loaded from: classes6.dex */
    public static final class C53471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6593p$;

        C53471(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53471 c53471 = new C53471(completion);
            c53471.f6593p$ = (CoroutineScope) obj;
            return c53471;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6593p$;
            MoveBySuspendCharge$onArrive$2.this.this$0.destroy$module_robot_mirsdk_wrapper_release();
            return Unit.INSTANCE;
        }
    }
}
