package com.pudutech.mirsdk.hardware.activity;

import android.widget.Switch;
import android.widget.TextView;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.ScheduleCommunication;
import com.pudutech.mirsdk.hardware.library.C5193R;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$onResume$1", m3970f = "HardwareActivity.kt", m3971i = {0}, m3972l = {729}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class HardwareActivity$onResume$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5970p$;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$onResume$1(HardwareActivity hardwareActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$onResume$1 hardwareActivity$onResume$1 = new HardwareActivity$onResume$1(this.this$0, completion);
        hardwareActivity$onResume$1.f5970p$ = (CoroutineScope) obj;
        return hardwareActivity$onResume$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$onResume$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5970p$;
            HardwareActivity$onResume$1$result$1 hardwareActivity$onResume$1$result$1 = new HardwareActivity$onResume$1$result$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(2000L, hardwareActivity$onResume$1$result$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new C51051(null), 2, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$onResume$1$1", m3970f = "HardwareActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$onResume$1$1 */
    /* loaded from: classes2.dex */
    public static final class C51051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5971p$;

        C51051(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C51051 c51051 = new C51051(completion);
            c51051.f5971p$ = (CoroutineScope) obj;
            return c51051;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C51051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            HardwareConnection hardwareConnection;
            HardwareConnection hardwareConnection2;
            ScheduleCommunication scheduler;
            Boolean boxBoolean;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Switch switchCarpetMode = (Switch) HardwareActivity$onResume$1.this.this$0._$_findCachedViewById(C5193R.id.switchCarpetMode);
            Intrinsics.checkExpressionValueIsNotNull(switchCarpetMode, "switchCarpetMode");
            hardwareConnection = HardwareActivity$onResume$1.this.this$0.hardware;
            HardwareInterface hardwareInterface = hardwareConnection.getInterface();
            switchCarpetMode.setChecked((hardwareInterface == null || (boxBoolean = Boxing.boxBoolean(hardwareInterface.getCarpetMode())) == null) ? false : boxBoolean.booleanValue());
            TextView txEspVersion = (TextView) HardwareActivity$onResume$1.this.this$0._$_findCachedViewById(C5193R.id.txEspVersion);
            Intrinsics.checkExpressionValueIsNotNull(txEspVersion, "txEspVersion");
            StringBuilder sb = new StringBuilder();
            sb.append("ESP Hardware Version: ");
            hardwareConnection2 = HardwareActivity$onResume$1.this.this$0.hardware;
            HardwareInterface hardwareInterface2 = hardwareConnection2.getInterface();
            sb.append((hardwareInterface2 == null || (scheduler = hardwareInterface2.getScheduler()) == null) ? null : scheduler.getESPVersion());
            txEspVersion.setText(sb.toString());
            return Unit.INSTANCE;
        }
    }
}
