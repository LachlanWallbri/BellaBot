package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: MachineInfoProcess.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.MachineInfoProcess$fetchFromHardware$fetchResult$1", m3970f = "MachineInfoProcess.kt", m3971i = {0, 0, 0, 0, 1, 1, 1}, m3972l = {454, 456}, m3973m = "invokeSuspend", m3974n = {"$this$withTimeoutOrNull", "retryTime", "copyList", "id", "$this$withTimeoutOrNull", "retryTime", "copyList"}, m3975s = {"L$0", "I$0", "L$1", "B$0", "L$0", "I$0", "L$1"})
/* loaded from: classes.dex */
public final class MachineInfoProcess$fetchFromHardware$fetchResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ KFunction $sendCAN;
    byte B$0;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5935p$;
    final /* synthetic */ MachineInfoProcess this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MachineInfoProcess$fetchFromHardware$fetchResult$1(MachineInfoProcess machineInfoProcess, KFunction kFunction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = machineInfoProcess;
        this.$sendCAN = kFunction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$1 = new MachineInfoProcess$fetchFromHardware$fetchResult$1(this.this$0, this.$sendCAN, completion);
        machineInfoProcess$fetchFromHardware$fetchResult$1.f5935p$ = (CoroutineScope) obj;
        return machineInfoProcess$fetchFromHardware$fetchResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((MachineInfoProcess$fetchFromHardware$fetchResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0179 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00e5  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x017a -> B:6:0x017d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        List list;
        List list2;
        List list3;
        List list4;
        List list5;
        int i;
        MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$1;
        List list6;
        List list7;
        MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$12;
        List list8;
        CoroutineScope coroutineScope2;
        Iterator it;
        byte[] m4415combineAskCmd7apg3OU;
        List list9;
        String str;
        List list10;
        List list11;
        List list12;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5935p$;
            list = this.this$0.fetchList;
            synchronized (list) {
                list2 = this.this$0.fetchList;
                list2.clear();
                this.this$0.setFetchSuccess(false);
                for (MachineInfo.FloatInfo floatInfo : MachineInfo.FloatInfo.values()) {
                    list7 = this.this$0.fetchList;
                    list7.add(UByte.m4522boximpl(floatInfo.getId()));
                }
                for (MachineInfo.IntInfo intInfo : MachineInfo.IntInfo.values()) {
                    list6 = this.this$0.fetchList;
                    list6.add(UByte.m4522boximpl(intInfo.getId()));
                }
                list3 = this.this$0.fetchList;
                list3.add(UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 23)));
                list4 = this.this$0.fetchList;
                list4.add(UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 24)));
                list5 = this.this$0.fetchList;
                Boxing.boxBoolean(list5.add(UByte.m4522boximpl(UByte.m4528constructorimpl((byte) 27))));
            }
            i = 0;
            machineInfoProcess$fetchFromHardware$fetchResult$1 = this;
            list9 = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.fetchList;
            if (list9.size() > 0) {
            }
        } else if (i2 == 1) {
            it = (Iterator) this.L$2;
            List list13 = (List) this.L$1;
            int i3 = this.I$0;
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            machineInfoProcess$fetchFromHardware$fetchResult$12 = this;
            list8 = list13;
            i = i3;
            coroutineScope2 = coroutineScope3;
            while (it.hasNext()) {
            }
            machineInfoProcess$fetchFromHardware$fetchResult$12.L$0 = coroutineScope2;
            machineInfoProcess$fetchFromHardware$fetchResult$12.I$0 = i;
            machineInfoProcess$fetchFromHardware$fetchResult$12.L$1 = list8;
            machineInfoProcess$fetchFromHardware$fetchResult$12.label = 2;
            if (DelayKt.delay(200L, machineInfoProcess$fetchFromHardware$fetchResult$12) != coroutine_suspended) {
            }
        } else if (i2 == 2) {
            int i4 = this.I$0;
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope4;
            MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$13 = this;
            MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$14 = machineInfoProcess$fetchFromHardware$fetchResult$13;
            i = i4 + 1;
            machineInfoProcess$fetchFromHardware$fetchResult$1 = machineInfoProcess$fetchFromHardware$fetchResult$14;
            list9 = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.fetchList;
            if (list9.size() > 0) {
                str = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("fetch list size:");
                list10 = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.fetchList;
                sb.append(list10.size());
                sb.append(" retryTime:");
                sb.append(i);
                Pdlog.m3275i(str, sb.toString());
                list11 = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.fetchList;
                synchronized (list11) {
                    list12 = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.fetchList;
                    list8 = CollectionsKt.toMutableList((Collection) list12);
                    Unit unit = Unit.INSTANCE;
                }
                coroutineScope2 = coroutineScope;
                machineInfoProcess$fetchFromHardware$fetchResult$12 = machineInfoProcess$fetchFromHardware$fetchResult$1;
                it = list8.iterator();
                while (it.hasNext()) {
                    byte data = ((UByte) it.next()).getData();
                    Function1 function1 = (Function1) machineInfoProcess$fetchFromHardware$fetchResult$12.$sendCAN;
                    m4415combineAskCmd7apg3OU = machineInfoProcess$fetchFromHardware$fetchResult$12.this$0.m4415combineAskCmd7apg3OU(data);
                    function1.invoke(UByteArray.m4570boximpl(m4415combineAskCmd7apg3OU));
                    machineInfoProcess$fetchFromHardware$fetchResult$12.L$0 = coroutineScope2;
                    machineInfoProcess$fetchFromHardware$fetchResult$12.I$0 = i;
                    machineInfoProcess$fetchFromHardware$fetchResult$12.L$1 = list8;
                    machineInfoProcess$fetchFromHardware$fetchResult$12.B$0 = data;
                    machineInfoProcess$fetchFromHardware$fetchResult$12.L$2 = it;
                    machineInfoProcess$fetchFromHardware$fetchResult$12.label = 1;
                    if (DelayKt.delay(0L, machineInfoProcess$fetchFromHardware$fetchResult$12) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                machineInfoProcess$fetchFromHardware$fetchResult$12.L$0 = coroutineScope2;
                machineInfoProcess$fetchFromHardware$fetchResult$12.I$0 = i;
                machineInfoProcess$fetchFromHardware$fetchResult$12.L$1 = list8;
                machineInfoProcess$fetchFromHardware$fetchResult$12.label = 2;
                if (DelayKt.delay(200L, machineInfoProcess$fetchFromHardware$fetchResult$12) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                i4 = i;
                machineInfoProcess$fetchFromHardware$fetchResult$13 = machineInfoProcess$fetchFromHardware$fetchResult$12;
                coroutineScope = coroutineScope2;
                MachineInfoProcess$fetchFromHardware$fetchResult$1 machineInfoProcess$fetchFromHardware$fetchResult$142 = machineInfoProcess$fetchFromHardware$fetchResult$13;
                i = i4 + 1;
                machineInfoProcess$fetchFromHardware$fetchResult$1 = machineInfoProcess$fetchFromHardware$fetchResult$142;
                list9 = machineInfoProcess$fetchFromHardware$fetchResult$1.this$0.fetchList;
                if (list9.size() > 0) {
                    return Boxing.boxBoolean(true);
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
