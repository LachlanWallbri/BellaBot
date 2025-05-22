package com.pudutech.robot.module.report.task;

import android.os.SystemClock;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import com.pudutech.robot.module.report.protocol.CustomerTrackingReport;
import com.pudutech.robot.module.report.protocol.bean.CruiseCardInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ReportCustomerTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001=B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010(\u001a\u00020)H\u0016J\u0006\u0010*\u001a\u00020+J&\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.002\b\b\u0002\u00101\u001a\u000202J\u0010\u00103\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u00104\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0006\u00105\u001a\u00020+J\u0010\u00106\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u00107\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u00108\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u00109\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u0010:\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u0010;\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202J\u0010\u0010<\u001a\u00020+2\b\b\u0002\u00101\u001a\u000202R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u001e\u001a\u001e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00120\u001fj\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012` X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportCustomerTask;", "Lcom/pudutech/robot/module/report/task/BaseTrackingReportTask;", "()V", "AWAKE_NUMBER_KEY", "", "BUSINESS_INTERACTIVE_NUMBER_KEY", "BUSINESS_INTERACTIVE_NUMBER_KEY_CRUISE", ReportCustomerTask.CARD_CLICK_TRACK, ReportCustomerTask.CARD_CLICK_TRACK_CRUISE, "COUPON_NUMBER_KEY", "COUPON_NUMBER_KEY_CRUISE", ReportCustomerTask.CUSTOMER_FLOW_NUMBER_KEY, ReportCustomerTask.CUSTOMER_FLOW_NUMBER_KEY_CRUISE, "FEATUR_DISHES_NUMBER_KEY", "FEATUR_DISHES_NUMBER_KEY_CRUISE", "GOSHOP_NUMBER_KEY", ReportCustomerTask.GOSHOP_NUMBER_KEY_CRUISE, "MAX_FLOW_SAVE_NUM", "", "SOLICIT_END_TIME", "SOLICIT_END_TIME_CRUISE", ReportCustomerTask.SOLICIT_ENTRY, "SOLICIT_START_TIME", "SOLICIT_START_TIME_CRUISE", ReportCustomerTask.SOLICIT_USE_TIME, ReportCustomerTask.SOLICIT_VOICE_USHER, ReportCustomerTask.SOLICIT_VOICE_USHER_CRUISE, "TAG", "gson", "Lcom/google/gson/Gson;", "hashMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "mList", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/task/ReportCustomerTask$Gongge;", "Lkotlin/collections/ArrayList;", "mSolicitTrackingTime", "", "userFlowNum", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "remove", "", "trackCardClick", "cardInfo", "Lcom/pudutech/robot/module/report/protocol/bean/CruiseCardInfo;", "cardInfoList", "", "isCruise", "", "trackSolicitEntry", "trackVoiceCustomer", "trackingAwakeEvent", "trackingBusinessInteractiveEvent", "trackingCouponEvent", "trackingCustomerFlowEvent", "trackingFeatureDishesEvent", "trackingGoShopEvent", "trackingSolicitEnd", "trackingSolicitStart", "Gongge", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class ReportCustomerTask extends BaseTrackingReportTask {
    private static final String AWAKE_NUMBER_KEY = "CUSTOMER_AWAKE_NUMBER_KEY";
    private static final String BUSINESS_INTERACTIVE_NUMBER_KEY = "CUSTOMER_INTERACTIVE_NUMBER_KEY";
    private static final String BUSINESS_INTERACTIVE_NUMBER_KEY_CRUISE = "CUSTOMER_INTERACTIVE_NUMBER_KEY_CRUISE";
    private static final String CARD_CLICK_TRACK = "CARD_CLICK_TRACK";
    private static final String CARD_CLICK_TRACK_CRUISE = "CARD_CLICK_TRACK_CRUISE";
    private static final String COUPON_NUMBER_KEY = "CUSTOMER_COUPON_NUMBER_KEY";
    private static final String COUPON_NUMBER_KEY_CRUISE = "CUSTOMER_COUPON_NUMBER_KEY_CRUISE";
    private static final String CUSTOMER_FLOW_NUMBER_KEY = "CUSTOMER_FLOW_NUMBER_KEY";
    private static final String CUSTOMER_FLOW_NUMBER_KEY_CRUISE = "CUSTOMER_FLOW_NUMBER_KEY_CRUISE";
    private static final String FEATUR_DISHES_NUMBER_KEY = "CUSTOMER_FEATUR_DISHES_NUMBER_KEY";
    private static final String FEATUR_DISHES_NUMBER_KEY_CRUISE = "CUSTOMER_FEATUR_DISHES_NUMBER_KEY_CRUISE";
    private static final String GOSHOP_NUMBER_KEY = "CUSTOMER_GOSHOP_NUMBER_KEY";
    private static final String GOSHOP_NUMBER_KEY_CRUISE = "GOSHOP_NUMBER_KEY_CRUISE";
    private static final int MAX_FLOW_SAVE_NUM = 5;
    private static final String SOLICIT_END_TIME = "solicit_end_time";
    private static final String SOLICIT_END_TIME_CRUISE = "solicit_end_time_cruise";
    private static final String SOLICIT_ENTRY = "SOLICIT_ENTRY";
    private static final String SOLICIT_START_TIME = "solicit_start_time";
    private static final String SOLICIT_START_TIME_CRUISE = "solicit_start_time_cruise";
    private static final String SOLICIT_USE_TIME = "SOLICIT_USE_TIME";
    private static final String SOLICIT_VOICE_USHER = "SOLICIT_VOICE_USHER";
    private static final String SOLICIT_VOICE_USHER_CRUISE = "SOLICIT_VOICE_USHER_CRUISE";
    private static final String TAG = "ReportCustomerTask";
    private static long mSolicitTrackingTime;
    private static volatile long userFlowNum;
    public static final ReportCustomerTask INSTANCE = new ReportCustomerTask();
    private static final Gson gson = new Gson();
    private static ArrayList<Gongge> mList = new ArrayList<>();
    private static final HashMap<Integer, Integer> hashMap = new HashMap<>();

    private ReportCustomerTask() {
    }

    @Override // com.pudutech.robot.module.report.task.BaseTrackingReportTask
    public BaseTrackingReport getReportData() {
        ArrayList arrayList;
        CustomerTrackingReport customerTrackingReport = new CustomerTrackingReport();
        customerTrackingReport.setAwakeNumber(getTracking(AWAKE_NUMBER_KEY));
        customerTrackingReport.setSolicitUseTime(getTracking(SOLICIT_USE_TIME));
        customerTrackingReport.setF_type(Long.valueOf(getTracking(SOLICIT_ENTRY)));
        String str = (String) null;
        Long f_type = customerTrackingReport.getF_type();
        if (f_type != null && f_type.longValue() == 1) {
            str = getTrackJsonValue(CARD_CLICK_TRACK);
            customerTrackingReport.setVoice_usher(getTracking(SOLICIT_VOICE_USHER));
            customerTrackingReport.setCustomerFlowNumber(getTracking(CUSTOMER_FLOW_NUMBER_KEY));
            customerTrackingReport.setBusinessInteractiveNumber(getTracking(BUSINESS_INTERACTIVE_NUMBER_KEY));
            customerTrackingReport.setFeaturDishesNumber(getTracking(FEATUR_DISHES_NUMBER_KEY));
            customerTrackingReport.setCouponNumber(getTracking(COUPON_NUMBER_KEY));
            customerTrackingReport.setGoShopNumber(getTracking(GOSHOP_NUMBER_KEY));
            customerTrackingReport.setEndTime(getTracking(SOLICIT_END_TIME) / 1000);
            customerTrackingReport.setStartTime(getTracking(SOLICIT_START_TIME) / 1000);
        } else if (f_type != null && f_type.longValue() == 2) {
            str = getTrackJsonValue(CARD_CLICK_TRACK_CRUISE);
            customerTrackingReport.setVoice_usher(getTracking(SOLICIT_VOICE_USHER_CRUISE));
            customerTrackingReport.setCustomerFlowNumber(getTracking(CUSTOMER_FLOW_NUMBER_KEY_CRUISE));
            customerTrackingReport.setBusinessInteractiveNumber(getTracking(BUSINESS_INTERACTIVE_NUMBER_KEY_CRUISE));
            customerTrackingReport.setFeaturDishesNumber(getTracking(FEATUR_DISHES_NUMBER_KEY_CRUISE));
            customerTrackingReport.setCouponNumber(getTracking(COUPON_NUMBER_KEY_CRUISE));
            customerTrackingReport.setGoShopNumber(getTracking(GOSHOP_NUMBER_KEY_CRUISE));
            customerTrackingReport.setStartTime(getTracking(SOLICIT_START_TIME_CRUISE) / 1000);
            customerTrackingReport.setEndTime(getTracking(SOLICIT_END_TIME_CRUISE) / 1000);
        } else {
            customerTrackingReport.setVoice_usher(0L);
            customerTrackingReport.setCustomerFlowNumber(0L);
            customerTrackingReport.setBusinessInteractiveNumber(0L);
            customerTrackingReport.setFeaturDishesNumber(0L);
            customerTrackingReport.setCouponNumber(0L);
            customerTrackingReport.setGoShopNumber(0L);
            customerTrackingReport.setStartTime(0L);
            customerTrackingReport.setEndTime(0L);
        }
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            arrayList = new ArrayList();
        } else {
            arrayList = (ArrayList) gson.fromJson(str, new TypeToken<ArrayList<Gongge>>() { // from class: com.pudutech.robot.module.report.task.ReportCustomerTask$getReportData$1
            }.getType());
        }
        customerTrackingReport.setGongge(arrayList);
        NetWorkLog.INSTANCE.mo3278d(TAG, "data:" + customerTrackingReport + ' ');
        return customerTrackingReport;
    }

    public static /* synthetic */ void trackingCustomerFlowEvent$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingCustomerFlowEvent(z);
    }

    public final void trackingCustomerFlowEvent(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingCustomerFlowEvent " + isCruise);
        if (isCruise) {
            BaseTrackingReportTask.addTracking$default(this, CUSTOMER_FLOW_NUMBER_KEY_CRUISE, 0L, 2, null);
        } else {
            BaseTrackingReportTask.addTracking$default(this, CUSTOMER_FLOW_NUMBER_KEY, 0L, 2, null);
        }
    }

    public final void trackingAwakeEvent() {
        BaseTrackingReportTask.addTracking$default(this, AWAKE_NUMBER_KEY, 0L, 2, null);
    }

    public static /* synthetic */ void trackingBusinessInteractiveEvent$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingBusinessInteractiveEvent(z);
    }

    public final void trackingBusinessInteractiveEvent(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingBusinessInteractiveEvent " + isCruise);
        if (isCruise) {
            BaseTrackingReportTask.addTracking$default(this, BUSINESS_INTERACTIVE_NUMBER_KEY_CRUISE, 0L, 2, null);
        } else {
            BaseTrackingReportTask.addTracking$default(this, BUSINESS_INTERACTIVE_NUMBER_KEY, 0L, 2, null);
        }
    }

    public static /* synthetic */ void trackingFeatureDishesEvent$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingFeatureDishesEvent(z);
    }

    public final void trackingFeatureDishesEvent(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingFeatureDishesEvent " + isCruise);
        if (isCruise) {
            BaseTrackingReportTask.addTracking$default(this, FEATUR_DISHES_NUMBER_KEY_CRUISE, 0L, 2, null);
        } else {
            BaseTrackingReportTask.addTracking$default(this, FEATUR_DISHES_NUMBER_KEY, 0L, 2, null);
        }
    }

    public static /* synthetic */ void trackingCouponEvent$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingCouponEvent(z);
    }

    public final void trackingCouponEvent(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingCouponEvent " + isCruise);
        if (isCruise) {
            BaseTrackingReportTask.addTracking$default(this, COUPON_NUMBER_KEY_CRUISE, 0L, 2, null);
        } else {
            BaseTrackingReportTask.addTracking$default(this, COUPON_NUMBER_KEY, 0L, 2, null);
        }
    }

    public static /* synthetic */ void trackingGoShopEvent$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingGoShopEvent(z);
    }

    public final void trackingGoShopEvent(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingGoShopEvent " + isCruise);
        if (isCruise) {
            BaseTrackingReportTask.addTracking$default(this, GOSHOP_NUMBER_KEY_CRUISE, 0L, 2, null);
        } else {
            BaseTrackingReportTask.addTracking$default(this, GOSHOP_NUMBER_KEY, 0L, 2, null);
        }
    }

    public static /* synthetic */ void trackingSolicitStart$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingSolicitStart(z);
    }

    public final void trackingSolicitStart(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingSolicitStart " + System.currentTimeMillis());
        mSolicitTrackingTime = SystemClock.elapsedRealtime();
        if (isCruise) {
            addTrack(SOLICIT_START_TIME_CRUISE, System.currentTimeMillis());
        } else {
            addTrack(SOLICIT_START_TIME, System.currentTimeMillis());
        }
    }

    public static /* synthetic */ void trackSolicitEntry$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackSolicitEntry(z);
    }

    public final void trackSolicitEntry(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackSolicitEntry " + isCruise);
        if (isCruise) {
            addTrack(SOLICIT_ENTRY, 2L);
        } else {
            addTrack(SOLICIT_ENTRY, 1L);
        }
    }

    public static /* synthetic */ void trackingSolicitEnd$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackingSolicitEnd(z);
    }

    public final void trackingSolicitEnd(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingSolicitEnd " + System.currentTimeMillis());
        if (mSolicitTrackingTime <= 0) {
            mSolicitTrackingTime = 0L;
            return;
        }
        if (isCruise) {
            addTrack(SOLICIT_END_TIME_CRUISE, System.currentTimeMillis());
        } else {
            addTrack(SOLICIT_END_TIME, System.currentTimeMillis());
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = elapsedRealtime - mSolicitTrackingTime;
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackingUsherEnd : endTime:" + elapsedRealtime + " totalDuration " + j);
        mSolicitTrackingTime = 0L;
        addTracking(SOLICIT_USE_TIME, j / 1000);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: ReportCustomerTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\b¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/ReportCustomerTask$Gongge;", "", "type", "", "index", "times", "(III)V", "getIndex", "()I", "setIndex", "(I)V", "getTimes", "setTimes", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class Gongge {
        private int index;
        private int times;
        private final int type;

        public static /* synthetic */ Gongge copy$default(Gongge gongge, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = gongge.type;
            }
            if ((i4 & 2) != 0) {
                i2 = gongge.index;
            }
            if ((i4 & 4) != 0) {
                i3 = gongge.times;
            }
            return gongge.copy(i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* renamed from: component2, reason: from getter */
        public final int getIndex() {
            return this.index;
        }

        /* renamed from: component3, reason: from getter */
        public final int getTimes() {
            return this.times;
        }

        public final Gongge copy(int type, int index, int times) {
            return new Gongge(type, index, times);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Gongge)) {
                return false;
            }
            Gongge gongge = (Gongge) other;
            return this.type == gongge.type && this.index == gongge.index && this.times == gongge.times;
        }

        public int hashCode() {
            return (((this.type * 31) + this.index) * 31) + this.times;
        }

        public String toString() {
            return "Gongge(type=" + this.type + ", index=" + this.index + ", times=" + this.times + ")";
        }

        public Gongge(int i, int i2, int i3) {
            this.type = i;
            this.index = i2;
            this.times = i3;
        }

        public /* synthetic */ Gongge(int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i4 & 4) != 0 ? 0 : i3);
        }

        public final int getIndex() {
            return this.index;
        }

        public final int getTimes() {
            return this.times;
        }

        public final int getType() {
            return this.type;
        }

        public final void setIndex(int i) {
            this.index = i;
        }

        public final void setTimes(int i) {
            this.times = i;
        }
    }

    public static /* synthetic */ void trackVoiceCustomer$default(ReportCustomerTask reportCustomerTask, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        reportCustomerTask.trackVoiceCustomer(z);
    }

    public final void trackVoiceCustomer(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackVoiceCustomer " + isCruise);
        if (isCruise) {
            BaseTrackingReportTask.addTracking$default(this, SOLICIT_VOICE_USHER_CRUISE, 0L, 2, null);
        } else {
            BaseTrackingReportTask.addTracking$default(this, SOLICIT_VOICE_USHER, 0L, 2, null);
        }
    }

    public static /* synthetic */ void trackCardClick$default(ReportCustomerTask reportCustomerTask, CruiseCardInfo cruiseCardInfo, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        reportCustomerTask.trackCardClick(cruiseCardInfo, list, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x018b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x015e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void trackCardClick(CruiseCardInfo cardInfo, List<CruiseCardInfo> cardInfoList, boolean isCruise) {
        String trackJsonValue;
        ArrayList<Gongge> arrayList;
        boolean z;
        Intrinsics.checkParameterIsNotNull(cardInfo, "cardInfo");
        Intrinsics.checkParameterIsNotNull(cardInfoList, "cardInfoList");
        NetWorkLog.INSTANCE.mo3278d(TAG, "isCruise " + isCruise + "======Start === =trackCardClick " + cardInfo + " before add " + cardInfoList + " ==========");
        ArrayList<CruiseCardInfo> arrayList2 = new ArrayList(cardInfoList.size());
        arrayList2.clear();
        for (CruiseCardInfo cruiseCardInfo : cardInfoList) {
            if (!arrayList2.contains(cruiseCardInfo)) {
                arrayList2.add(cruiseCardInfo);
            }
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackCardClick  list is " + arrayList2);
        if (hashMap.get(Integer.valueOf(cardInfo.getSerial())) == null) {
            hashMap.put(Integer.valueOf(cardInfo.getSerial()), 1);
        } else {
            HashMap<Integer, Integer> hashMap2 = hashMap;
            Integer valueOf = Integer.valueOf(cardInfo.getSerial());
            Integer num = hashMap.get(Integer.valueOf(cardInfo.getSerial()));
            if (num == null) {
                Intrinsics.throwNpe();
            }
            hashMap2.put(valueOf, Integer.valueOf(num.intValue() + 1));
        }
        if (isCruise) {
            trackJsonValue = getTrackJsonValue(CARD_CLICK_TRACK_CRUISE);
        } else {
            trackJsonValue = getTrackJsonValue(CARD_CLICK_TRACK);
        }
        if (trackJsonValue.length() > 0) {
            Object fromJson = gson.fromJson(trackJsonValue, new TypeToken<ArrayList<Gongge>>() { // from class: com.pudutech.robot.module.report.task.ReportCustomerTask$trackCardClick$2
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(gonListStr…yList<Gongge>>() {}.type)");
            arrayList = (ArrayList) fromJson;
        } else {
            arrayList = new ArrayList<>();
        }
        mList = arrayList;
        NetWorkLog.INSTANCE.mo3278d(TAG, "after put count to map " + hashMap + "  mList is " + mList + "  trackJsonValue  " + trackJsonValue);
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(mList);
        for (CruiseCardInfo cruiseCardInfo2 : arrayList2) {
            ArrayList<Gongge> arrayList3 = mList;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj : arrayList3) {
                Gongge gongge = (Gongge) obj;
                if (gongge.getIndex() == cruiseCardInfo2.getSerial()) {
                    int type = gongge.getType();
                    Integer type2 = cruiseCardInfo2.getType();
                    if (type2 != null && type == type2.intValue()) {
                        z = true;
                        if (!z) {
                            arrayList4.add(obj);
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
            ArrayList arrayList5 = arrayList4;
            if (arrayList5.isEmpty()) {
                Integer type3 = cruiseCardInfo2.getType();
                if (type3 == null) {
                    Intrinsics.throwNpe();
                }
                copyOnWriteArrayList.add(new Gongge(type3.intValue(), cruiseCardInfo2.getSerial(), 1));
            } else {
                Gongge gongge2 = (Gongge) arrayList5.get(0);
                Integer num2 = hashMap.get(Integer.valueOf(cruiseCardInfo2.getSerial()));
                if (num2 == null) {
                    Intrinsics.throwNpe();
                }
                gongge2.setTimes(num2.intValue());
                copyOnWriteArrayList.removeAll(arrayList5);
                copyOnWriteArrayList.add(gongge2);
            }
        }
        List mutableList = CollectionsKt.toMutableList((Collection) copyOnWriteArrayList);
        if (mutableList == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.robot.module.report.task.ReportCustomerTask.Gongge> /* = java.util.ArrayList<com.pudutech.robot.module.report.task.ReportCustomerTask.Gongge> */");
        }
        mList = (ArrayList) mutableList;
        NetWorkLog.INSTANCE.mo3278d(TAG, "isCruise " + isCruise + "======End after add Gong to list " + mList + " ===========");
        String gonJson = gson.toJson(mList);
        if (isCruise) {
            Intrinsics.checkExpressionValueIsNotNull(gonJson, "gonJson");
            addTrackJson(CARD_CLICK_TRACK_CRUISE, gonJson);
        } else {
            Intrinsics.checkExpressionValueIsNotNull(gonJson, "gonJson");
            addTrackJson(CARD_CLICK_TRACK, gonJson);
        }
    }

    public final void remove() {
        removeTracking(CUSTOMER_FLOW_NUMBER_KEY);
        removeTracking(CUSTOMER_FLOW_NUMBER_KEY_CRUISE);
        removeTracking(AWAKE_NUMBER_KEY);
        removeTracking(BUSINESS_INTERACTIVE_NUMBER_KEY);
        removeTracking(BUSINESS_INTERACTIVE_NUMBER_KEY_CRUISE);
        removeTracking(FEATUR_DISHES_NUMBER_KEY);
        removeTracking(FEATUR_DISHES_NUMBER_KEY_CRUISE);
        removeTracking(COUPON_NUMBER_KEY);
        removeTracking(COUPON_NUMBER_KEY_CRUISE);
        removeTracking(GOSHOP_NUMBER_KEY);
        removeTracking(GOSHOP_NUMBER_KEY_CRUISE);
        removeTracking(SOLICIT_USE_TIME);
        removeTracking(SOLICIT_VOICE_USHER);
        removeTracking(SOLICIT_VOICE_USHER_CRUISE);
        BaseTrackingReportTask.removeTackJson$default(this, CARD_CLICK_TRACK, null, 2, null);
        BaseTrackingReportTask.removeTackJson$default(this, CARD_CLICK_TRACK_CRUISE, null, 2, null);
        removeTracking(SOLICIT_ENTRY);
        removeTracking(SOLICIT_START_TIME_CRUISE);
        removeTracking(SOLICIT_START_TIME);
        removeTracking(SOLICIT_END_TIME);
        removeTracking(SOLICIT_END_TIME_CRUISE);
        mList.clear();
    }
}
