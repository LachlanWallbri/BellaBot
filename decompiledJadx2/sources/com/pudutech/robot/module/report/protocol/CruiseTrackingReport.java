package com.pudutech.robot.module.report.protocol;

import com.google.gson.annotations.SerializedName;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CruiseTrackingReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001e\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001e\u0010\u0015\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\b¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/CruiseTrackingReport;", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "()V", "businessInteractiveNumber", "", "getBusinessInteractiveNumber", "()J", "setBusinessInteractiveNumber", "(J)V", "couponNumber", "getCouponNumber", "setCouponNumber", "cruiseDuration", "getCruiseDuration", "setCruiseDuration", "featurDishesNumber", "getFeaturDishesNumber", "setFeaturDishesNumber", "goShopNumber", "getGoShopNumber", "setGoShopNumber", "interactiveNumber", "getInteractiveNumber", "setInteractiveNumber", "isEmpty", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CruiseTrackingReport implements BaseTrackingReport {

    @SerializedName("cruise")
    private long cruiseDuration = getDEFAULT_TRACKING_VALUE();

    @SerializedName("click_interactive")
    private long interactiveNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("interactive")
    private long businessInteractiveNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("click_dishes")
    private long featurDishesNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("click_coupon")
    private long couponNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("enter_shop")
    private long goShopNumber = getDEFAULT_TRACKING_VALUE();

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public double getDEFAULT_TRACKING_FLOAT_VALUE() {
        return BaseTrackingReport.DefaultImpls.getDEFAULT_TRACKING_FLOAT_VALUE(this);
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public long getDEFAULT_TRACKING_VALUE() {
        return BaseTrackingReport.DefaultImpls.getDEFAULT_TRACKING_VALUE(this);
    }

    public final long getCruiseDuration() {
        return this.cruiseDuration;
    }

    public final void setCruiseDuration(long j) {
        this.cruiseDuration = j;
    }

    public final long getInteractiveNumber() {
        return this.interactiveNumber;
    }

    public final void setInteractiveNumber(long j) {
        this.interactiveNumber = j;
    }

    public final long getBusinessInteractiveNumber() {
        return this.businessInteractiveNumber;
    }

    public final void setBusinessInteractiveNumber(long j) {
        this.businessInteractiveNumber = j;
    }

    public final long getFeaturDishesNumber() {
        return this.featurDishesNumber;
    }

    public final void setFeaturDishesNumber(long j) {
        this.featurDishesNumber = j;
    }

    public final long getCouponNumber() {
        return this.couponNumber;
    }

    public final void setCouponNumber(long j) {
        this.couponNumber = j;
    }

    public final long getGoShopNumber() {
        return this.goShopNumber;
    }

    public final void setGoShopNumber(long j) {
        this.goShopNumber = j;
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public boolean isEmpty() {
        return this.cruiseDuration == getDEFAULT_TRACKING_VALUE() && this.interactiveNumber == getDEFAULT_TRACKING_VALUE() && this.businessInteractiveNumber == getDEFAULT_TRACKING_VALUE() && this.featurDishesNumber == getDEFAULT_TRACKING_VALUE() && this.couponNumber == getDEFAULT_TRACKING_VALUE() && this.goShopNumber == getDEFAULT_TRACKING_VALUE();
    }

    public String toString() {
        return "cruiseDuration:" + this.cruiseDuration + ";interactiveNumber：" + this.interactiveNumber + ";businessInteractiveNumber:" + this.businessInteractiveNumber + ";featurDishesNumber:" + this.featurDishesNumber + ";couponNumber:" + this.couponNumber + ";goShopNumber:" + this.goShopNumber;
    }
}
