package com.pudutech.schedulerlib.p065ui;

import android.widget.TextView;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.schedulerlib.C5725R;
import com.pudutech.schedulerlib.ScheduleController;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* compiled from: SchedulerTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.schedulerlib.ui.SchedulerTestActivity$startSend$2", m3970f = "SchedulerTestActivity.kt", m3971i = {0, 1, 1}, m3972l = {160, 181}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "info"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes2.dex */
public final class SchedulerTestActivity$startSend$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mac;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7500p$;
    final /* synthetic */ SchedulerTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SchedulerTestActivity$startSend$2(SchedulerTestActivity schedulerTestActivity, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = schedulerTestActivity;
        this.$mac = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SchedulerTestActivity$startSend$2 schedulerTestActivity$startSend$2 = new SchedulerTestActivity$startSend$2(this.this$0, this.$mac, completion);
        schedulerTestActivity$startSend$2.f7500p$ = (CoroutineScope) obj;
        return schedulerTestActivity$startSend$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SchedulerTestActivity$startSend$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:12:0x0031 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        boolean z;
        ScheduleController scheduleController;
        int i;
        long j;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7500p$;
        }
        while (z) {
            if (SchedulerTestActivity.INSTANCE.isClick()) {
                this.L$0 = coroutineScope;
                this.label = 1;
                if (DelayKt.delay(20L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                RobotScheduleInfo robotScheduleInfo = new RobotScheduleInfo();
                robotScheduleInfo.setRobot_id(this.$mac);
                IntRange intRange = new IntRange(1, 25);
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
                Iterator<Integer> it = intRange.iterator();
                while (it.hasNext()) {
                    int nextInt = ((IntIterator) it).nextInt();
                    arrayList.add(Boxing.boxInt(nextInt * nextInt));
                }
                robotScheduleInfo.setTopology_path(CollectionsKt.toIntArray(new ArrayList(arrayList)));
                robotScheduleInfo.setRef_robot_id("r03");
                robotScheduleInfo.setAvoid_robot_id("r05");
                robotScheduleInfo.setScheduling_mode(SchedulingMode.Free);
                robotScheduleInfo.setAvoid_node_id(10);
                robotScheduleInfo.setAvoid_track_id(20);
                robotScheduleInfo.getNext_goal().update(0.1d, 0.2d, 0.5d);
                robotScheduleInfo.getFinal_goal().update(12.0d, 43.23d, 0.05d);
                robotScheduleInfo.getVirtual_goal().update(14.234d, 34.45d, 1.023d);
                robotScheduleInfo.setMap_flag("E14875B8D39A");
                robotScheduleInfo.getPose().update(12.44d, 65.76d, 23.4d);
                scheduleController = this.this$0.controller;
                scheduleController.sendMsg(robotScheduleInfo);
                SchedulerTestActivity schedulerTestActivity = this.this$0;
                i = schedulerTestActivity.sendIndex;
                schedulerTestActivity.sendIndex = i + 1;
                ((TextView) this.this$0._$_findCachedViewById(C5725R.id.send_tv)).post(new Runnable() { // from class: com.pudutech.schedulerlib.ui.SchedulerTestActivity$startSend$2.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i3;
                        TextView send_tv = (TextView) SchedulerTestActivity$startSend$2.this.this$0._$_findCachedViewById(C5725R.id.send_tv);
                        Intrinsics.checkExpressionValueIsNotNull(send_tv, "send_tv");
                        StringBuilder sb = new StringBuilder();
                        sb.append("发送：");
                        i3 = SchedulerTestActivity$startSend$2.this.this$0.sendIndex;
                        sb.append(i3);
                        send_tv.setText(sb.toString());
                    }
                });
                j = this.this$0.delayTime;
                this.L$0 = coroutineScope;
                this.L$1 = robotScheduleInfo;
                this.label = 2;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
