package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.activity.MirSDKActivity;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.SDKInterface;
import com.pudutech.mirsdk.function.C4946R;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$moveWorkSpace$7 implements View.OnClickListener {
    final /* synthetic */ Ref.ObjectRef $autoTestChargJob;
    final /* synthetic */ Function1 $autoTestChargeTask;
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$moveWorkSpace$7(MirSDKActivity mirSDKActivity, Ref.ObjectRef objectRef, Function1 function1) {
        this.this$0 = mirSDKActivity;
        this.$autoTestChargJob = objectRef;
        this.$autoTestChargeTask = function1;
    }

    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$7$2", m3970f = "MirSDKActivity.kt", m3971i = {0, 0, 1, 1}, m3972l = {1081, 1091}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "piles", "$this$launch", "piles"}, m3975s = {"L$0", "L$1", "L$0", "L$1"})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$7$2 */
    /* loaded from: classes4.dex */
    static final class C48502 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5664p$;

        C48502(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48502 c48502 = new C48502(completion);
            c48502.f5664p$ = (CoroutineScope) obj;
            return c48502;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48502) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x00a9  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00be A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00bf  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x003e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x009c -> B:22:0x0077). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00bf -> B:7:0x0038). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C48502 c48502;
            Object obj2;
            Object obj3;
            SDKInterface sDKInterface;
            MoveActionInterface moveActionInterface;
            MoveActionInterface moveActionInterface2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5664p$;
            } else if (i == 1) {
                List<String> list = (List) this.L$1;
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Object obj4 = coroutine_suspended;
                c48502 = this;
                int i2 = MirSDKActivity.WhenMappings.$EnumSwitchMapping$3[SDKServiceConnection.INSTANCE.getChargeStateStore().ordinal()];
                if (i2 != 1 || i2 == 2) {
                    sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                        sDKInterface.suspendCharingUsingPile();
                    }
                    CoroutineScope coroutineScope3 = coroutineScope2;
                    obj2 = obj4;
                    c48502.L$0 = coroutineScope3;
                    c48502.L$1 = list;
                    c48502.label = 2;
                    if (DelayKt.delay(20000L, c48502) != obj2) {
                        return obj2;
                    }
                    coroutineScope = coroutineScope3;
                    obj3 = obj2;
                    if (CoroutineScopeKt.isActive(coroutineScope)) {
                        SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                        List<String> chargingPiles = (sDKInterface2 == null || (moveActionInterface2 = sDKInterface2.getMoveActionInterface()) == null) ? null : moveActionInterface2.getChargingPiles();
                        SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
                        if (sDKInterface3 != null && (moveActionInterface = sDKInterface3.getMoveActionInterface()) != null) {
                            if (chargingPiles == null) {
                                Intrinsics.throwNpe();
                            }
                            moveActionInterface.goChargingPile(chargingPiles.get(0));
                        }
                        List<String> list2 = chargingPiles;
                        coroutineScope2 = coroutineScope;
                        obj4 = obj3;
                        list = list2;
                    } else {
                        return Unit.INSTANCE;
                    }
                }
                if (CoroutineScopeKt.isActive(coroutineScope2)) {
                    c48502.L$0 = coroutineScope2;
                    c48502.L$1 = list;
                    c48502.label = 1;
                    if (DelayKt.delay(5000L, c48502) == obj4) {
                        return obj4;
                    }
                    int i22 = MirSDKActivity.WhenMappings.$EnumSwitchMapping$3[SDKServiceConnection.INSTANCE.getChargeStateStore().ordinal()];
                    if (i22 != 1) {
                    }
                    sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                    }
                }
                CoroutineScope coroutineScope32 = coroutineScope2;
                obj2 = obj4;
                c48502.L$0 = coroutineScope32;
                c48502.L$1 = list;
                c48502.label = 2;
                if (DelayKt.delay(20000L, c48502) != obj2) {
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope4;
            }
            obj3 = coroutine_suspended;
            c48502 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MirSDKActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$7$1", m3970f = "MirSDKActivity.kt", m3971i = {0, 1}, m3972l = {1617, 1624}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$7$1 */
    /* loaded from: classes5.dex */
    static final class C48491 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5663p$;

        C48491(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C48491 c48491 = new C48491(completion);
            c48491.f5663p$ = (CoroutineScope) obj;
            return c48491;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C48491) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0089  */
        /* JADX WARN: Type inference failed for: r0v2, types: [T, kotlinx.coroutines.Job] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            MoveActionInterface moveActionInterface;
            Job job;
            MoveActionInterface moveActionInterface2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5663p$;
                int i2 = MirSDKActivity.WhenMappings.$EnumSwitchMapping$3[SDKServiceConnection.INSTANCE.getChargeStateStore().ordinal()];
                if (i2 == 1 || i2 == 2) {
                    SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface != null) {
                        sDKInterface.suspendCharingUsingPile();
                    }
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (DelayKt.delay(2000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    SDKInterface sDKInterface2 = SDKServiceConnection.INSTANCE.getInterface();
                    if (sDKInterface2 != null && (moveActionInterface = sDKInterface2.getMoveActionInterface()) != null) {
                        moveActionInterface.pause();
                    }
                    job = (Job) MirSDKActivity$moveWorkSpace$7.this.$autoTestChargJob.element;
                    if (job != null) {
                        this.L$0 = coroutineScope;
                        this.label = 2;
                        if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    MirSDKActivity$moveWorkSpace$7.this.$autoTestChargJob.element = (Job) 0;
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        MirSDKActivity$moveWorkSpace$7.this.$autoTestChargJob.element = (Job) 0;
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            SDKInterface sDKInterface3 = SDKServiceConnection.INSTANCE.getInterface();
            if (sDKInterface3 != null && (moveActionInterface2 = sDKInterface3.getMoveActionInterface()) != null) {
                moveActionInterface2.pause();
            }
            job = (Job) MirSDKActivity$moveWorkSpace$7.this.$autoTestChargJob.element;
            if (job != null) {
            }
            MirSDKActivity$moveWorkSpace$7.this.$autoTestChargJob.element = (Job) 0;
            return Unit.INSTANCE;
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        MoveActionInterface moveActionInterface;
        Job job = (Job) this.$autoTestChargJob.element;
        final List<String> list = null;
        if (job != null && job.isActive()) {
            BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48491(null), 3, null);
            return;
        }
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
            list = moveActionInterface.getChargingPiles();
        }
        if (list != null) {
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "showing dialog select size:" + list.size());
            TextView elev_state = (TextView) this.this$0._$_findCachedViewById(C4946R.id.elev_state);
            Intrinsics.checkExpressionValueIsNotNull(elev_state, "elev_state");
            elev_state.setText("");
            AlertDialog.Builder builder = new AlertDialog.Builder(this.this$0);
            Object[] array = list.toArray(new String[0]);
            if (array != null) {
                AlertDialog create = builder.setSingleChoiceItems((CharSequence[]) array, -1, new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$7$dialog$1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        String str2;
                        str2 = MirSDKActivity$moveWorkSpace$7.this.this$0.TAG;
                        Pdlog.m3275i(str2, "select destination:" + ((String) list.get(i)));
                        dialogInterface.dismiss();
                        TextView task_id = (TextView) MirSDKActivity$moveWorkSpace$7.this.this$0._$_findCachedViewById(C4946R.id.task_id);
                        Intrinsics.checkExpressionValueIsNotNull(task_id, "task_id");
                        task_id.setText("目标充电桩 " + ((String) list.get(i)));
                        TextView elev_state2 = (TextView) MirSDKActivity$moveWorkSpace$7.this.this$0._$_findCachedViewById(C4946R.id.elev_state);
                        Intrinsics.checkExpressionValueIsNotNull(elev_state2, "elev_state");
                        elev_state2.setText("");
                        Function1 function1 = MirSDKActivity$moveWorkSpace$7.this.$autoTestChargeTask;
                        Object obj = list.get(i);
                        Intrinsics.checkExpressionValueIsNotNull(obj, "piles[i]");
                        function1.invoke(obj);
                    }
                }).create();
                Intrinsics.checkExpressionValueIsNotNull(create, "AlertDialog.Builder(this…               }.create()");
                create.show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        Toast.makeText(this.this$0, "destinations empty", 0).show();
    }
}
