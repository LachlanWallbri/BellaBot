package com.pudutech.robot.module.report;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.gson.GsonBuilder;
import com.pudutech.event_tracking.PuduEventTrackingManager;
import com.pudutech.event_tracking.TrackDeviceInfo;
import com.pudutech.event_tracking._EventTrackingManagerBuilder;
import com.pudutech.pd_network.PdNetworkManager;
import com.pudutech.pd_network.bean.GatewayName;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import com.pudutech.robot.module.report.protocol.CruiseTrackingReport;
import com.pudutech.robot.module.report.protocol.TrackingReportRequest;
import com.pudutech.robot.module.report.protocol.UsherTrackingReport;
import com.pudutech.robot.module.report.task.ReportCruiseTask;
import com.pudutech.robot.module.report.task.ReportCustomerTask;
import com.pudutech.robot.module.report.task.ReportUsherTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: TrackingReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001@B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010.\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010/\u001a\u0004\u0018\u0001002\b\b\u0002\u00101\u001a\u00020\u001fJ'\u00102\u001a\u00020\"2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u00042\b\b\u0002\u00106\u001a\u00020\u001fH\u0000¢\u0006\u0002\b7J\u001a\u00102\u001a\u00020\"2\b\u00108\u001a\u0004\u0018\u00010\u00042\u0006\u00105\u001a\u00020\u0004H\u0002J\u001a\u00109\u001a\u00020\"2\b\u00108\u001a\u0004\u0018\u00010\u00042\u0006\u00105\u001a\u00020\u0004H\u0002J\b\u0010:\u001a\u00020\"H\u0002J\u000e\u0010;\u001a\u00020\"2\u0006\u0010<\u001a\u00020\u0004J\u000e\u0010=\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010>\u001a\u00020\"2\u0006\u0010?\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u00020(X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u000e\u0010-\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, m3961d2 = {"Lcom/pudutech/robot/module/report/TrackingReportManager;", "", "()V", "CRUISE_API", "", "CUSTOMER_API", "FILE_NAME", "KEY_CNT", "PRODUCT_HOST_DOMAIN", "REPORT_DELAY_DURATION", "", "TAG", "TEST_HOST_DOMAIN", "USHER_API", "cnt", "", "context", "Landroid/content/Context;", "getContext$module_robot_report_release", "()Landroid/content/Context;", "setContext$module_robot_report_release", "(Landroid/content/Context;)V", "delayReportHandler", "Landroid/os/Handler;", "iReportDataProvide", "Lcom/pudutech/robot/module/report/TrackingReportManager$IReportDataProvide;", "getIReportDataProvide$module_robot_report_release", "()Lcom/pudutech/robot/module/report/TrackingReportManager$IReportDataProvide;", "setIReportDataProvide$module_robot_report_release", "(Lcom/pudutech/robot/module/report/TrackingReportManager$IReportDataProvide;)V", "isTest", "", "mReportTask", "Lkotlin/Function0;", "", "mReportThread", "Landroid/os/HandlerThread;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences$module_robot_report_release", "()Landroid/content/SharedPreferences;", "setSharedPreferences$module_robot_report_release", "(Landroid/content/SharedPreferences;)V", "versionName", "init", "trackDeviceInfo", "Lcom/pudutech/event_tracking/TrackDeviceInfo;", "mReportData", "report", "obj", "Lcom/pudutech/robot/module/report/protocol/TrackingReportRequest;", "path", "save_to_history", "report$module_robot_report_release", "info", "reportOrThrown", "sendDelayReportMessage", "setHardwareVersion", "version", "setReportDateProvide", "throwOrLog", TmpConstant.SERVICE_DESC, "IReportDataProvide", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class TrackingReportManager {
    private static final String CRUISE_API = "/api/kettybot/cruise/collect/v1";
    private static final String CUSTOMER_API = "/api/kettybot/cruise/collect/v1";
    private static final String FILE_NAME = "TrackingReportToCloud";
    private static final String KEY_CNT = "TrackingReportCnt";
    private static final String PRODUCT_HOST_DOMAIN = "https://rmp.pudutech.com";
    private static final long REPORT_DELAY_DURATION = 600000;
    private static final String TEST_HOST_DOMAIN = "http://rmp-test.pudutech.com";
    private static final String USHER_API = "/api/kettybot/usher/collect/v2";
    private static int cnt;
    public static Context context;
    private static Handler delayReportHandler;
    private static IReportDataProvide iReportDataProvide;
    private static boolean isTest;
    public static SharedPreferences sharedPreferences;
    public static final TrackingReportManager INSTANCE = new TrackingReportManager();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static String versionName = "";
    private static final HandlerThread mReportThread = new HandlerThread("TrackingReportThread");
    private static final Function0<Unit> mReportTask = new Function0<Unit>() { // from class: com.pudutech.robot.module.report.TrackingReportManager$mReportTask$1
        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            TrackingReportManager trackingReportManager = TrackingReportManager.INSTANCE;
            str = TrackingReportManager.TAG;
            netWorkLog.mo3278d(str, "start reportTask:" + System.currentTimeMillis());
            TrackingReportManager.INSTANCE.sendDelayReportMessage();
            TrackingReportRequest trackingReportRequest = new TrackingReportRequest();
            trackingReportRequest.setData(ReportUsherTask.INSTANCE.getReportData());
            BaseTrackingReport data = trackingReportRequest.getData();
            if (data != null && !data.isEmpty()) {
                NetWorkLog netWorkLog2 = NetWorkLog.INSTANCE;
                TrackingReportManager trackingReportManager2 = TrackingReportManager.INSTANCE;
                str4 = TrackingReportManager.TAG;
                netWorkLog2.mo3278d(str4, "ReportUsherTask data is " + trackingReportRequest.getData());
                BaseTrackingReport data2 = trackingReportRequest.getData();
                if (data2 != null) {
                    if (data2 instanceof UsherTrackingReport) {
                        TrackingReportManager.report$module_robot_report_release$default(TrackingReportManager.INSTANCE, trackingReportRequest, "/api/kettybot/usher/collect/v2", false, 4, null);
                        ReportUsherTask.INSTANCE.remove();
                    } else {
                        NetWorkLog netWorkLog3 = NetWorkLog.INSTANCE;
                        TrackingReportManager trackingReportManager3 = TrackingReportManager.INSTANCE;
                        str5 = TrackingReportManager.TAG;
                        netWorkLog3.mo3278d(str5, "ReportUsherTask startTime bigger than endTime or startTime is 0 endTime is 0 ");
                    }
                }
            }
            trackingReportRequest.setData(ReportCruiseTask.INSTANCE.getReportData());
            BaseTrackingReport data3 = trackingReportRequest.getData();
            if (data3 == null || data3.isEmpty()) {
                return;
            }
            NetWorkLog netWorkLog4 = NetWorkLog.INSTANCE;
            TrackingReportManager trackingReportManager4 = TrackingReportManager.INSTANCE;
            str2 = TrackingReportManager.TAG;
            netWorkLog4.mo3278d(str2, "ReportCruiseTask data is " + trackingReportRequest.getData());
            BaseTrackingReport data4 = trackingReportRequest.getData();
            if (data4 != null) {
                if (data4 instanceof CruiseTrackingReport) {
                    TrackingReportManager.report$module_robot_report_release$default(TrackingReportManager.INSTANCE, trackingReportRequest, "/api/kettybot/cruise/collect/v1", false, 4, null);
                    ReportCruiseTask.INSTANCE.remove();
                } else {
                    NetWorkLog netWorkLog5 = NetWorkLog.INSTANCE;
                    TrackingReportManager trackingReportManager5 = TrackingReportManager.INSTANCE;
                    str3 = TrackingReportManager.TAG;
                    netWorkLog5.mo3278d(str3, "ReportCruiseTask startTime bigger than endTime or startTime is 0 endTime is 0");
                }
            }
        }
    };
    private static final CoroutineScope scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: TrackingReportManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/module/report/TrackingReportManager$IReportDataProvide;", "", "getHardVersion", "", "getMac", "getPowerPercent", "", "isCharging", "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public interface IReportDataProvide {
        String getHardVersion();

        String getMac();

        double getPowerPercent();

        boolean isCharging();
    }

    private TrackingReportManager() {
    }

    public final Context getContext$module_robot_report_release() {
        Context context2 = context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    public final void setContext$module_robot_report_release(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "<set-?>");
        context = context2;
    }

    public final IReportDataProvide getIReportDataProvide$module_robot_report_release() {
        return iReportDataProvide;
    }

    public final void setIReportDataProvide$module_robot_report_release(IReportDataProvide iReportDataProvide2) {
        iReportDataProvide = iReportDataProvide2;
    }

    public final SharedPreferences getSharedPreferences$module_robot_report_release() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        return sharedPreferences2;
    }

    public final void setSharedPreferences$module_robot_report_release(SharedPreferences sharedPreferences2) {
        Intrinsics.checkParameterIsNotNull(sharedPreferences2, "<set-?>");
        sharedPreferences = sharedPreferences2;
    }

    public static /* synthetic */ void init$default(TrackingReportManager trackingReportManager, Context context2, boolean z, TrackDeviceInfo trackDeviceInfo, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            trackDeviceInfo = (TrackDeviceInfo) null;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        trackingReportManager.init(context2, z, trackDeviceInfo, z2);
    }

    public final void init(Context context2, boolean isTest2, final TrackDeviceInfo trackDeviceInfo, final boolean mReportData) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        NetWorkLog.INSTANCE.mo3278d(TAG, "init ");
        context = context2;
        isTest = isTest2;
        SharedPreferences sharedPreferences2 = context2.getSharedPreferences(FILE_NAME, 0);
        Intrinsics.checkExpressionValueIsNotNull(sharedPreferences2, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        sharedPreferences = sharedPreferences2;
        SharedPreferences sharedPreferences3 = sharedPreferences;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        cnt = sharedPreferences3.getInt(KEY_CNT, 0);
        String str = context2.getPackageManager().getPackageInfo(context2.getPackageName(), 0).versionName;
        Intrinsics.checkExpressionValueIsNotNull(str, "context.packageManager.g…ckageName, 0).versionName");
        versionName = str;
        ReportCustomerTask.INSTANCE.init(context2);
        ReportUsherTask.INSTANCE.init(context2);
        ReportCruiseTask.INSTANCE.init(context2);
        if (!mReportThread.isAlive()) {
            mReportThread.start();
        }
        delayReportHandler = new Handler(mReportThread.getLooper());
        sendDelayReportMessage();
        PuduEventTrackingManager.INSTANCE.init(context2, new Function1<_EventTrackingManagerBuilder, Unit>() { // from class: com.pudutech.robot.module.report.TrackingReportManager$init$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(_EventTrackingManagerBuilder _eventtrackingmanagerbuilder) {
                invoke2(_eventtrackingmanagerbuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(_EventTrackingManagerBuilder receiver) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.setForceTrackDeviceInfo(TrackDeviceInfo.this);
                receiver.setReportData(mReportData);
            }
        });
    }

    public final void setHardwareVersion(String version) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        PuduEventTrackingManager.INSTANCE.hardwareVersion(version);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.pudutech.robot.module.report.TrackingReportManager$sam$java_lang_Runnable$0] */
    public final void sendDelayReportMessage() {
        NetWorkLog.INSTANCE.mo3278d(TAG, "sendDelayReportMessage " + System.currentTimeMillis());
        Handler handler = delayReportHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("delayReportHandler");
        }
        final Function0<Unit> function0 = mReportTask;
        if (function0 != null) {
            function0 = new Runnable() { // from class: com.pudutech.robot.module.report.TrackingReportManager$sam$java_lang_Runnable$0
                @Override // java.lang.Runnable
                public final /* synthetic */ void run() {
                    Intrinsics.checkExpressionValueIsNotNull(Function0.this.invoke(), "invoke(...)");
                }
            };
        }
        handler.postDelayed((Runnable) function0, REPORT_DELAY_DURATION);
    }

    public static /* synthetic */ void report$module_robot_report_release$default(TrackingReportManager trackingReportManager, TrackingReportRequest trackingReportRequest, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        trackingReportManager.report$module_robot_report_release(trackingReportRequest, str, z);
    }

    public final void report$module_robot_report_release(TrackingReportRequest obj, String path, boolean save_to_history) {
        Intrinsics.checkParameterIsNotNull(obj, "obj");
        Intrinsics.checkParameterIsNotNull(path, "path");
        NetWorkLog.INSTANCE.mo3278d(TAG, "report " + obj + ' ' + path + ' ' + save_to_history + ' ');
        int i = cnt;
        if (i >= 2147483547) {
            cnt = 0;
        } else {
            cnt = i + 1;
        }
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sharedPreferences");
        }
        sharedPreferences2.edit().putInt(KEY_CNT, cnt).apply();
        IReportDataProvide iReportDataProvide2 = iReportDataProvide;
        obj.setMac(iReportDataProvide2 != null ? iReportDataProvide2.getMac() : null);
        obj.setReport_number(cnt);
        IReportDataProvide iReportDataProvide3 = iReportDataProvide;
        obj.setHardver(iReportDataProvide3 != null ? iReportDataProvide3.getHardVersion() : null);
        obj.setSoftver(versionName);
        obj.setTimestamp(System.currentTimeMillis() / 1000);
        try {
            String json = new GsonBuilder().create().toJson(obj);
            NetWorkLog.INSTANCE.mo3278d(TAG, "report to could. " + json);
            if (save_to_history) {
                report(json, path);
            } else {
                reportOrThrown(json, path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            NetWorkLog.INSTANCE.mo3279e(TAG, "obj=" + obj + ' ' + e);
        }
    }

    private final void report(String info, String path) {
        PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
        GatewayName.ROBOT_COLL robot_coll = GatewayName.ROBOT_COLL.INSTANCE;
        if (info == null) {
            Intrinsics.throwNpe();
        }
        pdNetworkManager.report(robot_coll, path, info);
    }

    private final void reportOrThrown(String info, String path) {
        PdNetworkManager pdNetworkManager = PdNetworkManager.f10310INSTANCE;
        GatewayName.ROBOT_COLL robot_coll = GatewayName.ROBOT_COLL.INSTANCE;
        if (info == null) {
            Intrinsics.throwNpe();
        }
        pdNetworkManager.report(robot_coll, path, info);
    }

    public final void setReportDateProvide(IReportDataProvide iReportDataProvide2) {
        Intrinsics.checkParameterIsNotNull(iReportDataProvide2, "iReportDataProvide");
        iReportDataProvide = iReportDataProvide2;
    }

    public final void throwOrLog(String desc) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        if (isTest) {
            throw new Exception(desc);
        }
        NetWorkLog.INSTANCE.mo3279e(TAG, "report error : " + desc);
    }
}
