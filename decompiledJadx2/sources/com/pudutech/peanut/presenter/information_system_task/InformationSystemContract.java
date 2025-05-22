package com.pudutech.peanut.presenter.information_system_task;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.mvp_base.BaseViewInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InformationSystemContract.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract;", "", "()V", "OrderInfo", "OrderInfoType", "PresenterInterface", "ViewInterface", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class InformationSystemContract {

    /* compiled from: InformationSystemContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfoType;", "", "(Ljava/lang/String;I)V", "SCAN", "INFORMATION_SYS", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public enum OrderInfoType {
        SCAN,
        INFORMATION_SYS
    }

    /* compiled from: InformationSystemContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$PresenterInterface;", "Lcom/pudutech/kotlinmvp/mvp_base/BasePresenterInterface;", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface PresenterInterface extends BasePresenterInterface {
    }

    /* compiled from: InformationSystemContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t2\u0006\u0010\n\u001a\u00020\u0003H&J2\u0010\u000b\u001a\u00020\u00032\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\r0\u0007j\b\u0012\u0004\u0012\u00020\r`\t2\u0006\u0010\u0004\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/mvp_base/BaseViewInterface;", "onReceiveDeliveryTask", "", "type", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "allTrays", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "executeTask", "onReceiveOrderInfo", "info", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfoType;", "trayIndex", "", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public interface ViewInterface extends BaseViewInterface {
        boolean onReceiveDeliveryTask(MoveSortType type, ArrayList<TrayModel> allTrays, boolean executeTask);

        boolean onReceiveOrderInfo(ArrayList<OrderInfo> info, OrderInfoType type, int trayIndex);

        /* compiled from: InformationSystemContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public static final class DefaultImpls {
            public static /* synthetic */ boolean onReceiveOrderInfo$default(ViewInterface viewInterface, ArrayList arrayList, OrderInfoType orderInfoType, int i, int i2, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onReceiveOrderInfo");
                }
                if ((i2 & 4) != 0) {
                    i = 0;
                }
                return viewInterface.onReceiveOrderInfo(arrayList, orderInfoType, i);
            }
        }
    }

    /* compiled from: InformationSystemContract.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001:\u00013BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\fHÆ\u0003J\t\u0010+\u001a\u00020\u000eHÆ\u0003Jc\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u00064"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "", "seatOn", "", "seatName", "dishName", "dishesCount", "", "scid", "notifyTarget", "orderState", AUserTrack.UTKEY_START_TIME, "", "orderType", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo$Type;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo$Type;)V", "getDishName", "()Ljava/lang/String;", "getDishesCount", "()D", "getNotifyTarget", "getOrderState", "setOrderState", "(Ljava/lang/String;)V", "getOrderType", "()Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo$Type;", "setOrderType", "(Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo$Type;)V", "getScid", "getSeatName", "getSeatOn", "getStartTime", "()J", "setStartTime", "(J)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "Type", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final /* data */ class OrderInfo {
        private final String dishName;
        private final double dishesCount;
        private final String notifyTarget;
        private String orderState;
        private Type orderType;
        private final String scid;
        private final String seatName;
        private final String seatOn;
        private long startTime;

        /* compiled from: InformationSystemContract.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo$Type;", "", "(Ljava/lang/String;I)V", "Normal", "TrayOrder", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes5.dex */
        public enum Type {
            Normal,
            TrayOrder
        }

        /* renamed from: component1, reason: from getter */
        public final String getSeatOn() {
            return this.seatOn;
        }

        /* renamed from: component2, reason: from getter */
        public final String getSeatName() {
            return this.seatName;
        }

        /* renamed from: component3, reason: from getter */
        public final String getDishName() {
            return this.dishName;
        }

        /* renamed from: component4, reason: from getter */
        public final double getDishesCount() {
            return this.dishesCount;
        }

        /* renamed from: component5, reason: from getter */
        public final String getScid() {
            return this.scid;
        }

        /* renamed from: component6, reason: from getter */
        public final String getNotifyTarget() {
            return this.notifyTarget;
        }

        /* renamed from: component7, reason: from getter */
        public final String getOrderState() {
            return this.orderState;
        }

        /* renamed from: component8, reason: from getter */
        public final long getStartTime() {
            return this.startTime;
        }

        /* renamed from: component9, reason: from getter */
        public final Type getOrderType() {
            return this.orderType;
        }

        public final OrderInfo copy(String seatOn, String seatName, String dishName, double dishesCount, String scid, String notifyTarget, String orderState, long startTime, Type orderType) {
            Intrinsics.checkParameterIsNotNull(seatOn, "seatOn");
            Intrinsics.checkParameterIsNotNull(seatName, "seatName");
            Intrinsics.checkParameterIsNotNull(dishName, "dishName");
            Intrinsics.checkParameterIsNotNull(scid, "scid");
            Intrinsics.checkParameterIsNotNull(notifyTarget, "notifyTarget");
            Intrinsics.checkParameterIsNotNull(orderState, "orderState");
            Intrinsics.checkParameterIsNotNull(orderType, "orderType");
            return new OrderInfo(seatOn, seatName, dishName, dishesCount, scid, notifyTarget, orderState, startTime, orderType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OrderInfo)) {
                return false;
            }
            OrderInfo orderInfo = (OrderInfo) other;
            return Intrinsics.areEqual(this.seatOn, orderInfo.seatOn) && Intrinsics.areEqual(this.seatName, orderInfo.seatName) && Intrinsics.areEqual(this.dishName, orderInfo.dishName) && Double.compare(this.dishesCount, orderInfo.dishesCount) == 0 && Intrinsics.areEqual(this.scid, orderInfo.scid) && Intrinsics.areEqual(this.notifyTarget, orderInfo.notifyTarget) && Intrinsics.areEqual(this.orderState, orderInfo.orderState) && this.startTime == orderInfo.startTime && Intrinsics.areEqual(this.orderType, orderInfo.orderType);
        }

        public int hashCode() {
            String str = this.seatOn;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.seatName;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.dishName;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            long doubleToLongBits = Double.doubleToLongBits(this.dishesCount);
            int i = (hashCode3 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
            String str4 = this.scid;
            int hashCode4 = (i + (str4 != null ? str4.hashCode() : 0)) * 31;
            String str5 = this.notifyTarget;
            int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
            String str6 = this.orderState;
            int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
            long j = this.startTime;
            int i2 = (hashCode6 + ((int) (j ^ (j >>> 32)))) * 31;
            Type type = this.orderType;
            return i2 + (type != null ? type.hashCode() : 0);
        }

        public String toString() {
            return "OrderInfo(seatOn=" + this.seatOn + ", seatName=" + this.seatName + ", dishName=" + this.dishName + ", dishesCount=" + this.dishesCount + ", scid=" + this.scid + ", notifyTarget=" + this.notifyTarget + ", orderState=" + this.orderState + ", startTime=" + this.startTime + ", orderType=" + this.orderType + ")";
        }

        public OrderInfo(String seatOn, String seatName, String dishName, double d, String scid, String notifyTarget, String orderState, long j, Type orderType) {
            Intrinsics.checkParameterIsNotNull(seatOn, "seatOn");
            Intrinsics.checkParameterIsNotNull(seatName, "seatName");
            Intrinsics.checkParameterIsNotNull(dishName, "dishName");
            Intrinsics.checkParameterIsNotNull(scid, "scid");
            Intrinsics.checkParameterIsNotNull(notifyTarget, "notifyTarget");
            Intrinsics.checkParameterIsNotNull(orderState, "orderState");
            Intrinsics.checkParameterIsNotNull(orderType, "orderType");
            this.seatOn = seatOn;
            this.seatName = seatName;
            this.dishName = dishName;
            this.dishesCount = d;
            this.scid = scid;
            this.notifyTarget = notifyTarget;
            this.orderState = orderState;
            this.startTime = j;
            this.orderType = orderType;
        }

        public final String getSeatOn() {
            return this.seatOn;
        }

        public final String getSeatName() {
            return this.seatName;
        }

        public final String getDishName() {
            return this.dishName;
        }

        public final double getDishesCount() {
            return this.dishesCount;
        }

        public final String getScid() {
            return this.scid;
        }

        public final String getNotifyTarget() {
            return this.notifyTarget;
        }

        public final String getOrderState() {
            return this.orderState;
        }

        public final void setOrderState(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.orderState = str;
        }

        public final long getStartTime() {
            return this.startTime;
        }

        public final void setStartTime(long j) {
            this.startTime = j;
        }

        public /* synthetic */ OrderInfo(String str, String str2, String str3, double d, String str4, String str5, String str6, long j, Type type, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, d, str4, (i & 32) != 0 ? "" : str5, (i & 64) != 0 ? "" : str6, (i & 128) != 0 ? 0L : j, (i & 256) != 0 ? Type.Normal : type);
        }

        public final Type getOrderType() {
            return this.orderType;
        }

        public final void setOrderType(Type type) {
            Intrinsics.checkParameterIsNotNull(type, "<set-?>");
            this.orderType = type;
        }
    }
}
