package com.pudutech.robot.module.report.track2;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: robot_solicit.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u0006\u0010\u000f\u001a\u00020\u0007J\u0006\u0010\u0010\u001a\u00020\u0007J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0006H\u0002R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/PeopleMarker;", "", "pid", "", "block", "Lkotlin/Function2;", "", "", "(ILkotlin/jvm/functions/Function2;)V", AUserTrack.UTKEY_END_TIME, "Ljava/lang/Long;", "job", "Lkotlinx/coroutines/Job;", "releaseTime", AUserTrack.UTKEY_START_TIME, "calc", "refresh", "release", TypedValues.Transition.S_DURATION, "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PeopleMarker {
    private final Function2<Integer, Long, Unit> block;
    private Long endTime;
    private Job job;
    private final int pid;
    private long releaseTime;
    private Long startTime;

    /* JADX WARN: Multi-variable type inference failed */
    public PeopleMarker(int i, Function2<? super Integer, ? super Long, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.pid = i;
        this.block = block;
    }

    public final void refresh() {
        long j;
        CoroutineScope coroutineScope;
        Job launch$default;
        long currentTimeMillis = System.currentTimeMillis();
        j = Robot_solicitKt.PEOPLE_INVALID_TIME;
        this.releaseTime = currentTimeMillis + j;
        if (this.job == null) {
            this.startTime = Long.valueOf(System.currentTimeMillis());
            coroutineScope = Robot_solicitKt.scope;
            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new PeopleMarker$refresh$1(this, null), 3, null);
            this.job = launch$default;
            return;
        }
        this.endTime = Long.valueOf(System.currentTimeMillis());
    }

    public final void calc() {
        Long l = this.endTime;
        long longValue = l != null ? l.longValue() : 0L;
        Long l2 = this.startTime;
        if (l2 == null) {
            Intrinsics.throwNpe();
        }
        release(longValue - l2.longValue());
    }

    private final void release(long duration) {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.job = (Job) null;
        this.block.invoke(Integer.valueOf(this.pid), Long.valueOf(duration));
    }
}
