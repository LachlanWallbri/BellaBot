package com.pudutech.factory_test.single_test;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.bumblebee.robot.aidl.serialize.Pallet;
import com.pudutech.factory_test.C4491R;
import com.pudutech.mirsdk.SolicitService;
import java.util.Iterator;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: TrayTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.TrayTestActivity$delayCheckTask$1", m3970f = "TrayTestActivity.kt", m3971i = {0, 0}, m3972l = {202}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "tray"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes.dex */
final class TrayTestActivity$delayCheckTask$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $isCheckPlace;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5206p$;
    final /* synthetic */ TrayTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrayTestActivity$delayCheckTask$1(TrayTestActivity trayTestActivity, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = trayTestActivity;
        this.$isCheckPlace = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        TrayTestActivity$delayCheckTask$1 trayTestActivity$delayCheckTask$1 = new TrayTestActivity$delayCheckTask$1(this.this$0, this.$isCheckPlace, completion);
        trayTestActivity$delayCheckTask$1.f5206p$ = (CoroutineScope) obj;
        return trayTestActivity$delayCheckTask$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TrayTestActivity$delayCheckTask$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x00d7  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = false;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5206p$;
            Iterator<T> it = this.this$0.getLastEvent().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj2 = null;
                    break;
                }
                obj2 = it.next();
                if (Boxing.boxBoolean(((Pallet) obj2).getPalletId() == this.this$0.getTrayID()).booleanValue()) {
                    break;
                }
            }
            Pallet pallet = (Pallet) obj2;
            if (pallet != null && pallet.getIsPlaced() == this.$isCheckPlace) {
                this.this$0.getEvents().clear();
                AppCompatTextView tvGuide = (AppCompatTextView) this.this$0._$_findCachedViewById(C4491R.id.tvGuide);
                Intrinsics.checkExpressionValueIsNotNull(tvGuide, "tvGuide");
                tvGuide.setText("托盘检测 ( " + this.this$0.getCaseCnt() + " | " + this.this$0.getAllCaseNum() + "):\n自动检测中，请稍等");
                ((LinearLayoutCompat) this.this$0._$_findCachedViewById(C4491R.id.layoutOptions)).removeAllViews();
                this.L$0 = coroutineScope;
                this.L$1 = pallet;
                this.label = 1;
                if (DelayKt.delay(SolicitService.CAMERA_OPEN_TIME_OUT, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            if (!z) {
                TrayTestActivity trayTestActivity = this.this$0;
                trayTestActivity.setCaseCnt(trayTestActivity.getCaseCnt() + 1);
                this.this$0.FSM();
            } else {
                this.this$0.layoutRetryPlaceAndRemove();
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        TrayTestActivity trayTestActivity2 = this.this$0;
        z = trayTestActivity2.checkEventsSame(trayTestActivity2.getTrayID(), this.$isCheckPlace);
        if (!z) {
        }
        return Unit.INSTANCE;
    }
}
