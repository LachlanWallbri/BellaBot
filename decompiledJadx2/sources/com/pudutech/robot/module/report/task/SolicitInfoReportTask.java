package com.pudutech.robot.module.report.task;

import android.content.SharedPreferences;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.PuduReportManager;
import com.pudutech.robot.module.report.protocol.BaseReport;
import com.pudutech.robot.module.report.protocol.SolicitInfoReport;
import com.pudutech.robot.module.report.protocol.bean.CruiseCardInfo;
import com.pudutech.robot.module.report.protocol.bean.SolicitDetailInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SolicitInfoReportTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 +2\u00020\u0001:\u0001+B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0006\u0010\u0013\u001a\u00020\u0014J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019J\b\u0010\u001a\u001a\u0004\u0018\u00010\u0010J\b\u0010\u001b\u001a\u0004\u0018\u00010\u0010J\b\u0010\u001c\u001a\u0004\u0018\u00010\u0010J\b\u0010\u001d\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u0004\u0018\u00010\u0010J\b\u0010\"\u001a\u0004\u0018\u00010\u0010J\b\u0010#\u001a\u0004\u0018\u00010\u0010J\b\u0010$\u001a\u0004\u0018\u00010\u0010J\b\u0010%\u001a\u0004\u0018\u00010\u0010J\b\u0010&\u001a\u00020'H\u0007J\u0010\u0010(\u001a\u00020'2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\n\u0010)\u001a\u0004\u0018\u00010\u0010H\u0002J\u0010\u0010*\u001a\u00020'2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/SolicitInfoReportTask;", "Lcom/pudutech/robot/module/report/task/BaseReportTask;", "()V", "hashMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "mList", "Ljava/util/ArrayList;", "Lcom/pudutech/robot/module/report/protocol/bean/SolicitDetailInfo$Gongge;", "Lkotlin/collections/ArrayList;", "mStartTime", "", "solicitInfo", "Lcom/pudutech/robot/module/report/protocol/bean/SolicitDetailInfo;", "solicitInfoReport", "Lcom/pudutech/robot/module/report/protocol/SolicitInfoReport;", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseReport;", "getSaveInfo", "", "recordCardClick", "cardInfo", "Lcom/pudutech/robot/module/report/protocol/bean/CruiseCardInfo;", "cardInfoList", "", "recordCoupon", "recordDishes", "recordEndTime", "recordEnterShop", "recordEntry", "isCruise", "", "recordInteractive", "recordPassengerFlow", "recordStartTime", "recordVoice", "recordWakeUp", "reportToNet", "", "saveToSp", "saveTopSpAndRefreshEndTime", "setReportData", "Companion", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SolicitInfoReportTask extends BaseReportTask {
    public static final String SP_SAVE_INFO = "sp_save_info";
    private static final String TAG = "PeanutReportManager";
    private final HashMap<Integer, Integer> hashMap = new HashMap<>();
    private ArrayList<SolicitDetailInfo.Gongge> mList = new ArrayList<>();
    private long mStartTime;
    private SolicitDetailInfo solicitInfo;
    private SolicitInfoReport solicitInfoReport;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<SolicitInfoReportTask>() { // from class: com.pudutech.robot.module.report.task.SolicitInfoReportTask$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SolicitInfoReportTask invoke() {
            return new SolicitInfoReportTask();
        }
    });

    /* compiled from: SolicitInfoReportTask.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/SolicitInfoReportTask$Companion;", "", "()V", "SP_SAVE_INFO", "", "TAG", "instance", "Lcom/pudutech/robot/module/report/task/SolicitInfoReportTask;", "getInstance", "()Lcom/pudutech/robot/module/report/task/SolicitInfoReportTask;", "instance$delegate", "Lkotlin/Lazy;", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public final SolicitInfoReportTask getInstance() {
            Lazy lazy = SolicitInfoReportTask.instance$delegate;
            Companion companion = SolicitInfoReportTask.INSTANCE;
            return (SolicitInfoReportTask) lazy.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final SolicitInfoReport recordStartTime() {
        this.solicitInfo = new SolicitDetailInfo();
        this.solicitInfoReport = new SolicitInfoReport();
        this.mStartTime = System.currentTimeMillis();
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordStartTime " + this.solicitInfo);
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordStartTime after " + this.solicitInfo);
        saveToSp(this.solicitInfoReport);
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordEntry(boolean isCruise) {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordEntry " + isCruise + " solicitInfoReport " + this.solicitInfoReport + "  " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setF_type(isCruise ? 2L : 1L);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordEntry after solicitInfoReport " + this.solicitInfoReport + "  " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x016b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x013e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SolicitInfoReport recordCardClick(CruiseCardInfo cardInfo, List<CruiseCardInfo> cardInfoList) {
        ArrayList<SolicitDetailInfo.Gongge> arrayList;
        boolean z;
        Intrinsics.checkParameterIsNotNull(cardInfo, "cardInfo");
        Intrinsics.checkParameterIsNotNull(cardInfoList, "cardInfoList");
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordCardClick " + this.solicitInfo);
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordCardClick cardInfo " + cardInfo + " cardInfoList " + cardInfoList + " solicitInfo " + this.solicitInfo + " ==========");
        ArrayList<CruiseCardInfo> arrayList2 = new ArrayList(cardInfoList.size());
        arrayList2.clear();
        for (CruiseCardInfo cruiseCardInfo : cardInfoList) {
            if (!arrayList2.contains(cruiseCardInfo)) {
                arrayList2.add(cruiseCardInfo);
            }
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "trackCardClick  list is " + arrayList2);
        if (this.hashMap.get(Integer.valueOf(cardInfo.getSerial())) == null) {
            this.hashMap.put(Integer.valueOf(cardInfo.getSerial()), 1);
        } else {
            HashMap<Integer, Integer> hashMap = this.hashMap;
            Integer valueOf = Integer.valueOf(cardInfo.getSerial());
            Integer num = this.hashMap.get(Integer.valueOf(cardInfo.getSerial()));
            if (num == null) {
                Intrinsics.throwNpe();
            }
            hashMap.put(valueOf, Integer.valueOf(num.intValue() + 1));
        }
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        List<SolicitDetailInfo.Gongge> gongge = solicitDetailInfo != null ? solicitDetailInfo.getGongge() : null;
        if (gongge == null || gongge.isEmpty()) {
            arrayList = new ArrayList<>();
        } else {
            SolicitDetailInfo solicitDetailInfo2 = this.solicitInfo;
            List<SolicitDetailInfo.Gongge> gongge2 = solicitDetailInfo2 != null ? solicitDetailInfo2.getGongge() : null;
            if (gongge2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.robot.module.report.protocol.bean.SolicitDetailInfo.Gongge> /* = java.util.ArrayList<com.pudutech.robot.module.report.protocol.bean.SolicitDetailInfo.Gongge> */");
            }
            arrayList = (ArrayList) gongge2;
        }
        this.mList = arrayList;
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList(this.mList);
        for (CruiseCardInfo cruiseCardInfo2 : arrayList2) {
            ArrayList<SolicitDetailInfo.Gongge> arrayList3 = this.mList;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj : arrayList3) {
                SolicitDetailInfo.Gongge gongge3 = (SolicitDetailInfo.Gongge) obj;
                if (gongge3.getIndex() == cruiseCardInfo2.getSerial()) {
                    int type = gongge3.getType();
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
                SolicitDetailInfo.Gongge gongge4 = new SolicitDetailInfo.Gongge();
                Integer type3 = cruiseCardInfo2.getType();
                if (type3 == null) {
                    Intrinsics.throwNpe();
                }
                gongge4.setType(type3.intValue());
                gongge4.setIndex(cruiseCardInfo2.getSerial());
                gongge4.setTimes(1);
                copyOnWriteArrayList.add(gongge4);
            } else {
                SolicitDetailInfo.Gongge gongge5 = (SolicitDetailInfo.Gongge) arrayList5.get(0);
                Integer num2 = this.hashMap.get(Integer.valueOf(cruiseCardInfo2.getSerial()));
                if (num2 == null) {
                    Intrinsics.throwNpe();
                }
                gongge5.setTimes(num2.intValue());
                copyOnWriteArrayList.removeAll(arrayList5);
                copyOnWriteArrayList.add(gongge5);
            }
        }
        List mutableList = CollectionsKt.toMutableList((Collection) copyOnWriteArrayList);
        if (mutableList == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.pudutech.robot.module.report.protocol.bean.SolicitDetailInfo.Gongge> /* = java.util.ArrayList<com.pudutech.robot.module.report.protocol.bean.SolicitDetailInfo.Gongge> */");
        }
        this.mList = (ArrayList) mutableList;
        SolicitDetailInfo solicitDetailInfo3 = this.solicitInfo;
        if (solicitDetailInfo3 != null) {
            solicitDetailInfo3.setGongge(this.mList);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, " recordCardClick after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordPassengerFlow() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordPassengerFlow " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setPassenger_flow(solicitDetailInfo.getPassenger_flow() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordPassengerFlow after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordWakeUp() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordWakeUp " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setWake(solicitDetailInfo.getWake() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordWakeUp after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordInteractive() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordInteractive " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setInteractive(solicitDetailInfo.getInteractive() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordInteractive after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordDishes() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordDishes " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setClick_dishes(solicitDetailInfo.getClick_dishes() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordDishes after  " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordCoupon() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordCoupon " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setClick_coupon(solicitDetailInfo.getClick_coupon() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordCoupon after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordEnterShop() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordEnterShop " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setEnter_shop(solicitDetailInfo.getEnter_shop() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordEnterShop after  " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordVoice() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordVoice " + this.solicitInfo);
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setVoice_usher(solicitDetailInfo.getVoice_usher() + 1);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordVoice after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    private final SolicitInfoReport saveTopSpAndRefreshEndTime() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "saveTopSpAndRefreshEndTime");
        long currentTimeMillis = System.currentTimeMillis() - this.mStartTime;
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setDuration(currentTimeMillis / 1000);
        }
        SolicitInfoReport solicitInfoReport = this.solicitInfoReport;
        if (solicitInfoReport != null) {
            solicitInfoReport.setData(this.solicitInfo);
        }
        saveToSp(this.solicitInfoReport);
        return this.solicitInfoReport;
    }

    public final SolicitInfoReport recordEndTime() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordEndTime " + this.solicitInfo);
        long currentTimeMillis = System.currentTimeMillis() - this.mStartTime;
        SolicitDetailInfo solicitDetailInfo = this.solicitInfo;
        if (solicitDetailInfo != null) {
            solicitDetailInfo.setDuration(currentTimeMillis / 1000);
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "recordEndTime after " + this.solicitInfo);
        saveTopSpAndRefreshEndTime();
        return this.solicitInfoReport;
    }

    public final void saveToSp(SolicitInfoReport solicitInfoReport) {
        String str;
        if (solicitInfoReport != null) {
            solicitInfoReport.setData(this.solicitInfo);
        }
        if (solicitInfoReport != null) {
            str = getGson().toJson(solicitInfoReport);
            Intrinsics.checkExpressionValueIsNotNull(str, "gson.toJson(solicitInfoReport)");
        } else {
            str = "";
        }
        NetWorkLog.INSTANCE.mo3278d(TAG, "saveToSp " + solicitInfoReport + ' ' + this.solicitInfo);
        SharedPreferences sharedPreferences$module_robot_report_release = PuduReportManager.INSTANCE.getSharedPreferences$module_robot_report_release();
        if (sharedPreferences$module_robot_report_release != null) {
            sharedPreferences$module_robot_report_release.edit().putString(SP_SAVE_INFO, str).commit();
        }
    }

    public final String getSaveInfo() {
        return PuduReportManager.INSTANCE.getSharedPreferences$module_robot_report_release() != null ? String.valueOf(PuduReportManager.INSTANCE.getSharedPreferences$module_robot_report_release().getString(SP_SAVE_INFO, "")) : "";
    }

    public final void reportToNet() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "reportToNet  solicitInfoReport " + this.solicitInfoReport + "  solicitInfo " + this.solicitInfo);
        report();
        this.mList.clear();
        this.hashMap.clear();
        this.solicitInfo = (SolicitDetailInfo) null;
        this.solicitInfoReport = (SolicitInfoReport) null;
    }

    @Override // com.pudutech.robot.module.report.task.BaseReportTask
    public BaseReport getReportData() {
        return this.solicitInfoReport;
    }

    public final void setReportData(SolicitInfoReport solicitInfoReport) {
        this.solicitInfoReport = solicitInfoReport;
    }
}
