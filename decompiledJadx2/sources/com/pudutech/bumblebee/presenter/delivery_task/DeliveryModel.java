package com.pudutech.bumblebee.presenter.delivery_task;

import android.os.SystemClock;
import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.robotsdk.RobotSetting;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: DeliveryModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001ABA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\b\u0010@\u001a\u00020\u0003H\u0016R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\r\"\u0004\b$\u0010\u000fR\u001a\u0010%\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u001eR\u001a\u0010(\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001c\u00104\u001a\u00020\u00198FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001c\"\u0004\b6\u0010\u001eR$\u00109\u001a\u0002082\u0006\u00107\u001a\u000208@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\r\"\u0004\b?\u0010\u000f¨\u0006B"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "", "destination", "", "foodInfo", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lkotlin/collections/ArrayList;", "id", "type", "(Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;)V", "completeEmployeeId", "getCompleteEmployeeId$module_bumblebee_presenter_robotRelease", "()Ljava/lang/String;", "setCompleteEmployeeId$module_bumblebee_presenter_robotRelease", "(Ljava/lang/String;)V", "completeType", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel$CompleteType;", "getCompleteType$module_bumblebee_presenter_robotRelease", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel$CompleteType;", "setCompleteType$module_bumblebee_presenter_robotRelease", "(Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel$CompleteType;)V", "getDestination", "setDestination", "duration_ms", "", "estimatedTime", "getEstimatedTime", "()J", "setEstimatedTime", "(J)V", "getFoodInfo", "()Ljava/util/ArrayList;", "setFoodInfo", "(Ljava/util/ArrayList;)V", "getId", "setId", "initTimestamp_ms", "getInitTimestamp_ms", "setInitTimestamp_ms", "mode", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "getMode", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "setMode", "(Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;)V", "range", "", "getRange", "()D", "setRange", "(D)V", "spendTime_ms", "getSpendTime_ms", "setSpendTime_ms", ES6Iterator.VALUE_PROPERTY, "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "status", "getStatus", "()Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "setStatus", "(Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;)V", "getType", "setType", "toString", "CompleteType", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryModel {
    private String completeEmployeeId;
    private CompleteType completeType;
    private String destination;
    private long duration_ms;
    private long estimatedTime;
    private ArrayList<InformationSystemContract.OrderInfo> foodInfo;
    private String id;
    private long initTimestamp_ms;
    private DeliveryMode mode;
    private double range;
    private long spendTime_ms;
    private TaskStatus status;
    private String type;

    /* compiled from: DeliveryModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel$CompleteType;", "", "(Ljava/lang/String;I)V", "MANUAL", "REMOTE", "QRCODE", "TIMEOUT", "TRAY_EMPTY", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public enum CompleteType {
        MANUAL,
        REMOTE,
        QRCODE,
        TIMEOUT,
        TRAY_EMPTY
    }

    public DeliveryModel(String destination, ArrayList<InformationSystemContract.OrderInfo> arrayList, String str, String type) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.destination = destination;
        this.foodInfo = arrayList;
        this.id = str;
        this.type = type;
        this.mode = DeliveryMode.GENERAL;
        this.status = TaskStatus.AWAIT;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final void setDestination(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destination = str;
    }

    public /* synthetic */ DeliveryModel(String str, ArrayList arrayList, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (ArrayList) null : arrayList, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? "manual" : str3);
    }

    public final ArrayList<InformationSystemContract.OrderInfo> getFoodInfo() {
        return this.foodInfo;
    }

    public final void setFoodInfo(ArrayList<InformationSystemContract.OrderInfo> arrayList) {
        this.foodInfo = arrayList;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.type = str;
    }

    public final long getInitTimestamp_ms() {
        return this.initTimestamp_ms;
    }

    public final void setInitTimestamp_ms(long j) {
        this.initTimestamp_ms = j;
    }

    public final void setSpendTime_ms(long j) {
        this.spendTime_ms = j;
    }

    public final long getSpendTime_ms() {
        if (this.initTimestamp_ms == 0) {
            return 0L;
        }
        long j = this.duration_ms;
        return j == 0 ? SystemClock.elapsedRealtime() - this.initTimestamp_ms : j;
    }

    public final double getRange() {
        return this.range;
    }

    public final void setRange(double d) {
        this.range = d;
    }

    public final long getEstimatedTime() {
        return this.estimatedTime;
    }

    public final void setEstimatedTime(long j) {
        this.estimatedTime = j;
    }

    /* renamed from: getCompleteType$module_bumblebee_presenter_robotRelease, reason: from getter */
    public final CompleteType getCompleteType() {
        return this.completeType;
    }

    public final void setCompleteType$module_bumblebee_presenter_robotRelease(CompleteType completeType) {
        this.completeType = completeType;
    }

    /* renamed from: getCompleteEmployeeId$module_bumblebee_presenter_robotRelease, reason: from getter */
    public final String getCompleteEmployeeId() {
        return this.completeEmployeeId;
    }

    public final void setCompleteEmployeeId$module_bumblebee_presenter_robotRelease(String str) {
        this.completeEmployeeId = str;
    }

    public final DeliveryMode getMode() {
        return this.mode;
    }

    public final void setMode(DeliveryMode deliveryMode) {
        Intrinsics.checkParameterIsNotNull(deliveryMode, "<set-?>");
        this.mode = deliveryMode;
    }

    public final TaskStatus getStatus() {
        return this.status;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x007f A[Catch: Exception -> 0x00c4, TryCatch #0 {Exception -> 0x00c4, blocks: (B:16:0x0068, B:18:0x0073, B:23:0x007f, B:26:0x008d), top: B:15:0x0068 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setStatus(TaskStatus value) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.status = value;
        Pdlog.m3273d("DeliveryModel", "set status = " + value + " , destination = " + this.destination);
        if (value.isOneOf(TaskStatus.CANCEL, TaskStatus.DONE, TaskStatus.DONE_BEFORE_ARRIVAL)) {
            if (this.duration_ms == 0) {
                this.duration_ms = SystemClock.elapsedRealtime() - this.initTimestamp_ms;
                return;
            }
            return;
        }
        if (value != TaskStatus.ON_THE_WAY || this.estimatedTime != 0 || this.range == 0.0d) {
            return;
        }
        try {
            String cruiseSpeedLevel = RobotSetting.INSTANCE.getCruiseSpeedLevel();
            String str = cruiseSpeedLevel;
            if (str != null && str.length() != 0) {
                z = false;
                if (z) {
                    double parseDouble = Double.parseDouble(cruiseSpeedLevel);
                    if (parseDouble > 0.6d) {
                        parseDouble = 0.6d;
                    }
                    this.estimatedTime = (long) ((this.range * 50) / parseDouble);
                    Pdlog.m3273d("DeliveryModel", "onStatusChanged : range = " + this.range + "; sp = " + cruiseSpeedLevel + " ; estimatedTime = " + this.estimatedTime);
                    return;
                }
                return;
            }
            z = true;
            if (z) {
            }
        } catch (Exception e) {
            Pdlog.m3274e("DeliveryModel", "estimatedTime  : " + Log.getStackTraceString(e));
        }
    }

    public String toString() {
        return "destination=" + this.destination + " mode=" + this.mode + " status=" + this.status;
    }
}
