package com.pudutech.bumblebee.robot_ui.viewmodel;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import com.pudutech.bumblebee.robot_ui.manager.LoRaInfo;
import com.pudutech.bumblebee.robot_ui.manager.LoRaUpdateState;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Collect.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, m3961d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 implements FlowCollector<Pair<? extends LoRaUpdateState, ? extends Integer>> {
    final /* synthetic */ CallSettingVM$startLoRaUpdate$1 this$0;

    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0096@¨\u0006\u0007"}, m3961d2 = {"emit", "", ExifInterface.GPS_DIRECTION_TRUE, ES6Iterator.VALUE_PROPERTY, "continuation", "Lkotlin/coroutines/Continuation;", "", "kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3$emit$1"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1", m3970f = "CallSettingVM.kt", m3971i = {0, 0, 0, 0, 1, 1, 1, 1}, m3972l = {138, 139}, m3973m = "emit", m3974n = {"this", ES6Iterator.VALUE_PROPERTY, "continuation", "it", "this", ES6Iterator.VALUE_PROPERTY, "continuation", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1$1 */
    /* loaded from: classes4.dex */
    public static final class C44091 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C44091(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
        }
    }

    public CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1(CallSettingVM$startLoRaUpdate$1 callSettingVM$startLoRaUpdate$1) {
        this.this$0 = callSettingVM$startLoRaUpdate$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x013a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object emit(Pair<? extends LoRaUpdateState, ? extends Integer> pair, Continuation continuation) {
        C44091 c44091;
        Object obj;
        Object coroutine_suspended;
        int i;
        C44091 c440912;
        Pair<? extends LoRaUpdateState, ? extends Integer> pair2;
        MutableLiveData mutableLiveData;
        CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1;
        MutableLiveData mutableLiveData2;
        CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$12;
        Job job;
        Object obj2 = pair;
        if (continuation instanceof C44091) {
            c44091 = (C44091) continuation;
            if ((c44091.label & Integer.MIN_VALUE) != 0) {
                c44091.label -= Integer.MIN_VALUE;
                obj = c44091.result;
                coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i = c44091.label;
                if (i != 0) {
                    ResultKt.throwOnFailure(obj);
                    c440912 = c44091;
                    pair2 = (Pair) obj2;
                    if (Intrinsics.areEqual(pair2.getFirst(), LoRaUpdateState.Idle.INSTANCE)) {
                        return Unit.INSTANCE;
                    }
                    mutableLiveData = this.this$0.this$0._loraUpdateState;
                    mutableLiveData.setValue(TuplesKt.m3968to(Boxing.boxInt(pair2.getFirst().getCode()), Boxing.boxInt(((int) (pair2.getSecond().doubleValue() * 0.25d)) + ((pair2.getFirst().getCode() - 1) * 25))));
                    if (Intrinsics.areEqual(pair2.getFirst(), LoRaUpdateState.UpdateSuccess.INSTANCE) || Intrinsics.areEqual(pair2.getFirst(), LoRaUpdateState.UpdateFail.INSTANCE)) {
                        MutableLiveData mutableLiveData3 = this.this$0.this$0._loraInfo;
                        LoRaInfo loRaInfo = (LoRaInfo) this.this$0.this$0._loraInfo.getValue();
                        mutableLiveData3.setValue(loRaInfo != null ? LoRaInfo.copy$default(loRaInfo, null, null, null, 0, "", 15, null) : null);
                        c44091.L$0 = this;
                        c44091.L$1 = obj2;
                        c44091.L$2 = c440912;
                        c44091.L$3 = pair2;
                        c44091.label = 1;
                        if (DelayKt.delay(10000L, c44091) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 = this;
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    if (i == 2) {
                        mutableLiveData2 = (MutableLiveData) c44091.L$4;
                        Object obj3 = c44091.L$1;
                        callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$12 = (CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1) c44091.L$0;
                        ResultKt.throwOnFailure(obj);
                        mutableLiveData2.setValue(obj);
                        job = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$12.this$0.this$0.loraUpdateJob;
                        if (job != null) {
                            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                        }
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Pair<? extends LoRaUpdateState, ? extends Integer> pair3 = (Pair) c44091.L$3;
                c440912 = (Continuation) c44091.L$2;
                Object obj4 = c44091.L$1;
                callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1 = (CallSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1) c44091.L$0;
                ResultKt.throwOnFailure(obj);
                pair2 = pair3;
                obj2 = obj4;
                MutableLiveData mutableLiveData4 = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1.this$0.this$0._loraInfo;
                CallSettingRepo callSettingRepo = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1.this$0.this$0.callRepo;
                c44091.L$0 = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1;
                c44091.L$1 = obj2;
                c44091.L$2 = c440912;
                c44091.L$3 = pair2;
                c44091.L$4 = mutableLiveData4;
                c44091.label = 2;
                obj = callSettingRepo.loraInfo(c44091);
                if (obj != coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableLiveData2 = mutableLiveData4;
                callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$12 = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1;
                mutableLiveData2.setValue(obj);
                job = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$12.this$0.this$0.loraUpdateJob;
                if (job != null) {
                }
                return Unit.INSTANCE;
            }
        }
        c44091 = new C44091(continuation);
        obj = c44091.result;
        coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i = c44091.label;
        if (i != 0) {
        }
        MutableLiveData mutableLiveData42 = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1.this$0.this$0._loraInfo;
        CallSettingRepo callSettingRepo2 = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1.this$0.this$0.callRepo;
        c44091.L$0 = callSettingVM$startLoRaUpdate$1$invokeSuspend$$inlined$collect$1;
        c44091.L$1 = obj2;
        c44091.L$2 = c440912;
        c44091.L$3 = pair2;
        c44091.L$4 = mutableLiveData42;
        c44091.label = 2;
        obj = callSettingRepo2.loraInfo(c44091);
        if (obj != coroutine_suspended) {
        }
    }
}
