package com.pudutech.robot.module.report.protocol;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import com.google.gson.annotations.SerializedName;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import com.pudutech.robot.module.report.task.ReportCustomerTask;
import java.util.List;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: CustomerTrackingReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001d\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u000204H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001e\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001e\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\"\u0010\u0015\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001b\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001e\u0010\u001e\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\bR&\u0010!\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010(\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0006\"\u0004\b*\u0010\bR\u001e\u0010+\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0006\"\u0004\b-\u0010\bR\u001e\u0010.\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\b¨\u00065"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/CustomerTrackingReport;", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "()V", "awakeNumber", "", "getAwakeNumber", "()J", "setAwakeNumber", "(J)V", "businessInteractiveNumber", "getBusinessInteractiveNumber", "setBusinessInteractiveNumber", "couponNumber", "getCouponNumber", "setCouponNumber", "customerFlowNumber", "getCustomerFlowNumber", "setCustomerFlowNumber", AUserTrack.UTKEY_END_TIME, "getEndTime", "setEndTime", "f_type", "getF_type", "()Ljava/lang/Long;", "setF_type", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "featurDishesNumber", "getFeaturDishesNumber", "setFeaturDishesNumber", "goShopNumber", "getGoShopNumber", "setGoShopNumber", "gongge", "", "Lcom/pudutech/robot/module/report/task/ReportCustomerTask$Gongge;", "getGongge", "()Ljava/util/List;", "setGongge", "(Ljava/util/List;)V", "solicitUseTime", "getSolicitUseTime", "setSolicitUseTime", AUserTrack.UTKEY_START_TIME, "getStartTime", "setStartTime", "voice_usher", "getVoice_usher", "setVoice_usher", "isEmpty", "", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class CustomerTrackingReport implements BaseTrackingReport {

    @SerializedName("f_type")
    private Long f_type;

    @SerializedName("gongge")
    private List<ReportCustomerTask.Gongge> gongge;

    @SerializedName("passenger_flow")
    private long customerFlowNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("wake")
    private long awakeNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("interactive")
    private long businessInteractiveNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("click_dishes")
    private long featurDishesNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("click_coupon")
    private long couponNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName("enter_shop")
    private long goShopNumber = getDEFAULT_TRACKING_VALUE();

    @SerializedName(TypedValues.Transition.S_DURATION)
    private long solicitUseTime = getDEFAULT_TRACKING_VALUE();

    @SerializedName("voice_usher")
    private long voice_usher = getDEFAULT_TRACKING_VALUE();

    @SerializedName("start_time")
    private long startTime = getDEFAULT_TRACKING_VALUE();

    @SerializedName("end_time")
    private long endTime = getDEFAULT_TRACKING_VALUE();

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public double getDEFAULT_TRACKING_FLOAT_VALUE() {
        return BaseTrackingReport.DefaultImpls.getDEFAULT_TRACKING_FLOAT_VALUE(this);
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public long getDEFAULT_TRACKING_VALUE() {
        return BaseTrackingReport.DefaultImpls.getDEFAULT_TRACKING_VALUE(this);
    }

    public final long getCustomerFlowNumber() {
        return this.customerFlowNumber;
    }

    public final void setCustomerFlowNumber(long j) {
        this.customerFlowNumber = j;
    }

    public final long getAwakeNumber() {
        return this.awakeNumber;
    }

    public final void setAwakeNumber(long j) {
        this.awakeNumber = j;
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

    public final long getSolicitUseTime() {
        return this.solicitUseTime;
    }

    public final void setSolicitUseTime(long j) {
        this.solicitUseTime = j;
    }

    public final long getVoice_usher() {
        return this.voice_usher;
    }

    public final void setVoice_usher(long j) {
        this.voice_usher = j;
    }

    public final List<ReportCustomerTask.Gongge> getGongge() {
        return this.gongge;
    }

    public final void setGongge(List<ReportCustomerTask.Gongge> list) {
        this.gongge = list;
    }

    public final Long getF_type() {
        return this.f_type;
    }

    public final void setF_type(Long l) {
        this.f_type = l;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long j) {
        this.startTime = j;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final void setEndTime(long j) {
        this.endTime = j;
    }

    @Override // com.pudutech.robot.module.report.protocol.BaseTrackingReport
    public boolean isEmpty() {
        if (this.customerFlowNumber == getDEFAULT_TRACKING_VALUE() && this.awakeNumber == getDEFAULT_TRACKING_VALUE() && this.businessInteractiveNumber == getDEFAULT_TRACKING_VALUE() && this.featurDishesNumber == getDEFAULT_TRACKING_VALUE() && this.couponNumber == getDEFAULT_TRACKING_VALUE() && this.goShopNumber == getDEFAULT_TRACKING_VALUE() && this.solicitUseTime == getDEFAULT_TRACKING_VALUE() && this.voice_usher == getDEFAULT_TRACKING_VALUE()) {
            List<ReportCustomerTask.Gongge> list = this.gongge;
            if (list == null || list.isEmpty()) {
                Long l = this.f_type;
                long default_tracking_value = getDEFAULT_TRACKING_VALUE();
                if (l != null && l.longValue() == default_tracking_value && this.startTime == getDEFAULT_TRACKING_VALUE() && this.endTime == getDEFAULT_TRACKING_VALUE()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return "customerFlowNumber:" + this.customerFlowNumber + ";awakeNumber：" + this.awakeNumber + ";interactiveNumber:" + this.businessInteractiveNumber + ";featurDishesNumber:" + this.featurDishesNumber + ";couponNumber:" + this.couponNumber + ";goShopNumber:" + this.goShopNumber + ";solicitUseTime:" + this.solicitUseTime + ";voice_usher:" + this.voice_usher + " gongge:" + this.gongge + " f_type:" + this.f_type + "; startTime:" + this.startTime + "; endTime:" + this.endTime + ';';
    }
}
