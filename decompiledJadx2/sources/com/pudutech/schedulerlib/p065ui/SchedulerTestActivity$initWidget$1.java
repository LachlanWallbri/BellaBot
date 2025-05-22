package com.pudutech.schedulerlib.p065ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import com.pudutech.base.SpUtils;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.ScheduleConstant;
import com.pudutech.schedulerlib.ScheduleController;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$initWidget$1", m3970f = "SchedulerTestActivity.kt", m3971i = {0}, m3972l = {99}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class SchedulerTestActivity$initWidget$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7492p$;
    final /* synthetic */ SchedulerTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerTestActivity$initWidget$1(SchedulerTestActivity schedulerTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerTestActivity$initWidget$1 schedulerTestActivity$initWidget$1 = new SchedulerTestActivity$initWidget$1(this.this$0, completion);
        schedulerTestActivity$initWidget$1.f7492p$ = (CoroutineScope) obj;
        return schedulerTestActivity$initWidget$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerTestActivity$initWidget$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ScheduleController scheduleController;
        ScheduleController scheduleController2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7492p$;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (CoroutineScopeKt.isActive(coroutineScope)) {
            scheduleController = this.this$0.controller;
            Boolean espIsConnected = scheduleController.espIsConnected();
            if (espIsConnected == null) {
                Intrinsics.throwNpe();
            }
            if (!espIsConnected.booleanValue()) {
                scheduleController2 = this.this$0.controller;
                if (scheduleController2.udpIsConnected()) {
                    break;
                }
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(100L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                break;
            }
        }
        SharedPreferences sharedPreferences = this.this$0.getSharedPreferences("mirsdk", 0);
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = sharedPreferences.getInt(ScheduleConstant.PREFERENCE_KEY, -1);
        if (intRef.element == -1) {
            intRef.element = SpUtils.get((Context) this.this$0, ScheduleConstant.PREFERENCE_KEY, 1);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(ScheduleConstant.PREFERENCE_KEY, intRef.element);
            edit.apply();
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C57441(intRef, null), 2, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes6.dex
      classes7.dex
     */
    /* compiled from: SchedulerTestActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$initWidget$1$1", m3970f = "SchedulerTestActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.schedulerlib.ui.SchedulerTestActivity$initWidget$1$1 */
    /* loaded from: classes2.dex */
    public static final class C57441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $chl;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7493p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C57441(Ref.IntRef intRef, Continuation continuation) {
            super(2, continuation);
            this.$chl = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C57441 c57441 = new C57441(this.$chl, completion);
            c57441.f7493p$ = (CoroutineScope) obj;
            return c57441;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C57441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            TextView textView = (TextView) SchedulerTestActivity$initWidget$1.this.this$0._$_findCachedViewById(C5725R.id.channel_id);
            Intrinsics.checkExpressionValueIsNotNull(textView, "this@SchedulerTestActivity.channel_id");
            textView.setText(String.valueOf(this.$chl.element + 1));
            return Unit.INSTANCE;
        }
    }
}
