package com.pudutech.robot.module.report.track2;

import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.custom.CustomArgs;
import com.pudutech.robot.module.report.ExtKt;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.text.StringsKt;

/* compiled from: charge.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004JA\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062*\u0010\u000b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\r0\f\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\rH\u0002¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/BatteryChargeTask;", "", "config", "Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig;", "(Lcom/pudutech/robot/module/report/track2/BaseBatteryChargeConfig;)V", "TAG", "", "sceneId", "customEvent", "", "status", "pairs", "", "Lkotlin/Pair;", "(Ljava/lang/String;[Lkotlin/Pair;)V", "onStart", TrackKey.POWER_PERCENT, "onStop", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class BatteryChargeTask {
    private final String TAG;
    private final BaseBatteryChargeConfig config;
    private final String sceneId;

    public BatteryChargeTask(BaseBatteryChargeConfig config) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        this.config = config;
        this.TAG = TrackType.BATTERY_CHARGE.toString();
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.sceneId = StringsKt.replace$default(uuid, "-", "", false, 4, (Object) null);
    }

    public final void onStart(String power_percent) {
        Intrinsics.checkParameterIsNotNull(power_percent, "power_percent");
        List mutableList = ArraysKt.toMutableList(ExtKt.toPairArray(this.config));
        mutableList.add(TuplesKt.m3968to(TrackKey.POWER_PERCENT, power_percent));
        Object[] array = mutableList.toArray(new Pair[0]);
        if (array != null) {
            Pair[] pairArr = (Pair[]) array;
            customEvent("ON_START", (Pair[]) Arrays.copyOf(pairArr, pairArr.length));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public final void onStop(String power_percent) {
        Intrinsics.checkParameterIsNotNull(power_percent, "power_percent");
        customEvent("ON_STOP", TuplesKt.m3968to(TrackKey.POWER_PERCENT, power_percent));
    }

    private final void customEvent(String status, Pair<String, ? extends Object>... pairs) {
        PuduEventTrackingManager puduEventTrackingManager = PuduEventTrackingManager.INSTANCE;
        String str = this.TAG;
        SpreadBuilder spreadBuilder = new SpreadBuilder(3);
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.BUSINESS_STATUS, status));
        spreadBuilder.add(TuplesKt.m3968to(TrackKey.SCENE_ID, this.sceneId));
        spreadBuilder.addSpread(pairs);
        puduEventTrackingManager.customEvent(new CustomArgs(str, MapsKt.mapOf((Pair[]) spreadBuilder.toArray(new Pair[spreadBuilder.size()])), 0));
    }
}
