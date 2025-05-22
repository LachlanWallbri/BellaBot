package com.pudutech.peanut.robot_ui.extend;

import android.os.SystemClock;
import com.aliyun.alink.linksdk.alcs.coap.resources.LinkFormat;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import com.pudutech.robot.module.report.protocol.bean.DeliveryInfo;
import com.pudutech.robot.module.report.protocol.bean.MovingTaskType;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryInfoExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n¨\u0006\u000b"}, m3961d2 = {"setInfo", "", "Lcom/pudutech/robot/module/report/protocol/bean/DeliveryInfo;", LinkFormat.DOMAIN, "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;", "type", "Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType;", "tray", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "robot_ui_peanutRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliveryInfoExtKt {
    public static /* synthetic */ void setInfo$default(DeliveryInfo deliveryInfo, MoveReportData moveReportData, MovingTaskType movingTaskType, ArrayList arrayList, int i, Object obj) {
        if ((i & 4) != 0) {
            arrayList = new ArrayList();
        }
        setInfo(deliveryInfo, moveReportData, movingTaskType, arrayList);
    }

    public static final void setInfo(DeliveryInfo setInfo, MoveReportData d, MovingTaskType type, ArrayList<Integer> tray) {
        Intrinsics.checkParameterIsNotNull(setInfo, "$this$setInfo");
        Intrinsics.checkParameterIsNotNull(d, "d");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(tray, "tray");
        setInfo.average = d.getAverage();
        setInfo.duration = d.getAllTime();
        setInfo.duration_pause = d.getPauseTime();
        setInfo.duration_wait = d.getArriveWaitTime();
        setInfo.goal_id = d.getGoalId();
        setInfo.mileage = d.getMileage();
        setInfo.status = !d.getCancel();
        setInfo.order_id = SystemClock.elapsedRealtime();
        setInfo.task_type = type.getValue();
        setInfo.tray_list = tray;
    }
}
