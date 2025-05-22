package com.pudutech.peanut.presenter.delivery_task;

import android.os.SystemClock;
import com.pudutech.peanut.presenter.information_system_task.InformationSystemContract;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0011\u0018\u00002\u00020\u0001:\u0002@ABK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u001c\b\u0002\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010?\u001a\u00020\u0003H\u0016R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00020\u0006\u0018\u0001`\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R\u001a\u0010'\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010 R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u000201X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00106\u001a\u00020\u001b8FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001e\"\u0004\b8\u0010 R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u000f\"\u0004\b>\u0010\u0011¨\u0006B"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "", "destination", "", "foodInfo", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lkotlin/collections/ArrayList;", "id", "type", "status", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$TaskStatus;", "(Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$TaskStatus;)V", "completeEmployeeId", "getCompleteEmployeeId$presenter_release", "()Ljava/lang/String;", "setCompleteEmployeeId$presenter_release", "(Ljava/lang/String;)V", "completeType", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$CompleteType;", "getCompleteType$presenter_release", "()Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$CompleteType;", "setCompleteType$presenter_release", "(Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$CompleteType;)V", "getDestination", "setDestination", "duration_ms", "", "estimatedTime", "getEstimatedTime", "()J", "setEstimatedTime", "(J)V", "getFoodInfo", "()Ljava/util/ArrayList;", "setFoodInfo", "(Ljava/util/ArrayList;)V", "getId", "setId", "initTimestamp_ms", "getInitTimestamp_ms", "setInitTimestamp_ms", "mode", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryMode;", "getMode", "()Lcom/pudutech/peanut/presenter/delivery_task/DeliveryMode;", "setMode", "(Lcom/pudutech/peanut/presenter/delivery_task/DeliveryMode;)V", "range", "", "getRange", "()D", "setRange", "(D)V", "spendTime_ms", "getSpendTime_ms", "setSpendTime_ms", "getStatus", "()Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$TaskStatus;", "setStatus", "(Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$TaskStatus;)V", "getType", "setType", "toString", "CompleteType", "TaskStatus", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
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
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$CompleteType;", "", "(Ljava/lang/String;I)V", "MANUAL", "REMOTE", "QRCODE", "TIMEOUT", "TRAY_EMPTY", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum CompleteType {
        MANUAL,
        REMOTE,
        QRCODE,
        TIMEOUT,
        TRAY_EMPTY
    }

    /* compiled from: DeliveryModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel$TaskStatus;", "", "(Ljava/lang/String;I)V", "IDLE", "ARRIVE", "DONE", "ADVANCE_DONE", "CANCEL", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum TaskStatus {
        IDLE,
        ARRIVE,
        DONE,
        ADVANCE_DONE,
        CANCEL
    }

    public DeliveryModel(String destination, ArrayList<InformationSystemContract.OrderInfo> arrayList, String str, String type, TaskStatus status) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.destination = destination;
        this.foodInfo = arrayList;
        this.id = str;
        this.type = type;
        this.status = status;
        this.mode = DeliveryMode.GENERAL;
    }

    public final String getDestination() {
        return this.destination;
    }

    public final void setDestination(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destination = str;
    }

    public /* synthetic */ DeliveryModel(String str, ArrayList arrayList, String str2, String str3, TaskStatus taskStatus, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (ArrayList) null : arrayList, (i & 4) != 0 ? (String) null : str2, (i & 8) != 0 ? "manual" : str3, (i & 16) != 0 ? TaskStatus.IDLE : taskStatus);
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

    public final TaskStatus getStatus() {
        return this.status;
    }

    public final void setStatus(TaskStatus taskStatus) {
        Intrinsics.checkParameterIsNotNull(taskStatus, "<set-?>");
        this.status = taskStatus;
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

    /* renamed from: getCompleteType$presenter_release, reason: from getter */
    public final CompleteType getCompleteType() {
        return this.completeType;
    }

    public final void setCompleteType$presenter_release(CompleteType completeType) {
        this.completeType = completeType;
    }

    /* renamed from: getCompleteEmployeeId$presenter_release, reason: from getter */
    public final String getCompleteEmployeeId() {
        return this.completeEmployeeId;
    }

    public final void setCompleteEmployeeId$presenter_release(String str) {
        this.completeEmployeeId = str;
    }

    public final DeliveryMode getMode() {
        return this.mode;
    }

    public final void setMode(DeliveryMode deliveryMode) {
        Intrinsics.checkParameterIsNotNull(deliveryMode, "<set-?>");
        this.mode = deliveryMode;
    }

    public String toString() {
        return "destination=" + this.destination + " mode=" + this.mode + " status=" + this.status;
    }
}
