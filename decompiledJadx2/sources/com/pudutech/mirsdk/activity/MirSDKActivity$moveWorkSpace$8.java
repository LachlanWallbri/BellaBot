package com.pudutech.mirsdk.activity;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.FunctionScope;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MirSDKActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, m3961d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MirSDKActivity$moveWorkSpace$8 implements View.OnClickListener {
    final /* synthetic */ MirSDKActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MirSDKActivity$moveWorkSpace$8(MirSDKActivity mirSDKActivity) {
        this.this$0 = mirSDKActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        String str;
        MoveActionInterface moveActionInterface;
        SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
        final List<String> chargingPiles = (sDKInterface == null || (moveActionInterface = sDKInterface.getMoveActionInterface()) == null) ? null : moveActionInterface.getChargingPiles();
        if (chargingPiles != null) {
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "showing dialog select size:" + chargingPiles.size());
            TextView elev_state = (TextView) this.this$0._$_findCachedViewById(C4946R.id.elev_state);
            Intrinsics.checkExpressionValueIsNotNull(elev_state, "elev_state");
            elev_state.setText("");
            AlertDialog.Builder builder = new AlertDialog.Builder(this.this$0);
            Object[] array = chargingPiles.toArray(new String[0]);
            if (array != null) {
                AlertDialog create = builder.setSingleChoiceItems((CharSequence[]) array, -1, new DialogInterface.OnClickListener() { // from class: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$8$dialog$1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        String str2;
                        str2 = MirSDKActivity$moveWorkSpace$8.this.this$0.TAG;
                        Pdlog.m3275i(str2, "select destination:" + ((String) chargingPiles.get(i)));
                        dialogInterface.dismiss();
                        TextView task_id = (TextView) MirSDKActivity$moveWorkSpace$8.this.this$0._$_findCachedViewById(C4946R.id.task_id);
                        Intrinsics.checkExpressionValueIsNotNull(task_id, "task_id");
                        task_id.setText("目标充电桩 " + ((String) chargingPiles.get(i)));
                        TextView elev_state2 = (TextView) MirSDKActivity$moveWorkSpace$8.this.this$0._$_findCachedViewById(C4946R.id.elev_state);
                        Intrinsics.checkExpressionValueIsNotNull(elev_state2, "elev_state");
                        elev_state2.setText("");
                        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, null, null, new C48511(i, null), 3, null);
                    }

                    /* JADX WARN: Classes with same name are omitted:
                      classes4.dex
                     */
                    /* compiled from: MirSDKActivity.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
                    @DebugMetadata(m3969c = "com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$8$dialog$1$1", m3970f = "MirSDKActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
                    /* renamed from: com.pudutech.mirsdk.activity.MirSDKActivity$moveWorkSpace$8$dialog$1$1 */
                    /* loaded from: classes5.dex */
                    static final class C48511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                        /* renamed from: $i */
                        final /* synthetic */ int f5665$i;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f5666p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C48511(int i, Continuation continuation) {
                            super(2, continuation);
                            this.f5665$i = i;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C48511 c48511 = new C48511(this.f5665$i, completion);
                            c48511.f5666p$ = (CoroutineScope) obj;
                            return c48511;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C48511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            MoveActionInterface moveActionInterface;
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.f5666p$;
                            SDKInterface sDKInterface = SDKServiceConnection.INSTANCE.getInterface();
                            if (sDKInterface != null && (moveActionInterface = sDKInterface.getMoveActionInterface()) != null) {
                                moveActionInterface.goChargingPile((String) chargingPiles.get(this.f5665$i));
                            }
                            return Unit.INSTANCE;
                        }
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
