package com.pudutech.robot.module.report.task;

import android.content.Context;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.robot.module.report.ReportSpUtils;
import com.pudutech.robot.module.report.protocol.BaseTrackingReport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BaseTrackingReportTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006J\u0016\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0006J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0014H&J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0004J\u000e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\tJ\u0018\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004J\u0016\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/module/report/task/BaseTrackingReportTask;", "", "()V", "DEFAULT_TRACKING_FLOAT_VALUE", "", "DEFAULT_TRACKING_VALUE", "", "TAG", "mContext", "Landroid/content/Context;", "addTrack", "", TransferTable.COLUMN_KEY, ES6Iterator.VALUE_PROPERTY, "addTrackJson", AIUIConstant.KEY_CONTENT, "addTracking", "addTrackingFloatValue", "", "getReportData", "Lcom/pudutech/robot/module/report/protocol/BaseTrackingReport;", "getTrackJsonValue", "getTracking", "getTrackingFloatValue", "init", "context", "removeTackJson", "removeTracking", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public abstract class BaseTrackingReportTask {
    private final long DEFAULT_TRACKING_VALUE;
    private Context mContext;
    private final String TAG = "BaseTrackingReportTask";
    private final String DEFAULT_TRACKING_FLOAT_VALUE = "0.0";

    public abstract BaseTrackingReport getReportData();

    public final void init(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.mContext = context;
        ReportSpUtils reportSpUtils = ReportSpUtils.INSTANCE;
        Context context2 = this.mContext;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        }
        reportSpUtils.init(context2);
    }

    public static /* synthetic */ void addTracking$default(BaseTrackingReportTask baseTrackingReportTask, String str, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addTracking");
        }
        if ((i & 2) != 0) {
            j = 1;
        }
        baseTrackingReportTask.addTracking(str, j);
    }

    public final void addTracking(String key, long value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "tracking key:" + key + ",value:" + value);
        long j = ReportSpUtils.INSTANCE.get(key, this.DEFAULT_TRACKING_VALUE);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "before tracking value:" + j);
        long j2 = j + value;
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "current tracking value:" + j2);
        ReportSpUtils.INSTANCE.set(key, j2);
    }

    public final void addTrack(String key, long value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "addTrack :" + key + " value :" + value);
        long j = ReportSpUtils.INSTANCE.get(key, this.DEFAULT_TRACKING_VALUE);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "before tracking value :" + j + " after " + value);
        ReportSpUtils.INSTANCE.set(key, value);
    }

    public final long getTracking(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        long j = ReportSpUtils.INSTANCE.get(key, this.DEFAULT_TRACKING_VALUE);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, key + " getTracking current tracking value:" + j);
        return j;
    }

    public static /* synthetic */ void addTrackingFloatValue$default(BaseTrackingReportTask baseTrackingReportTask, String str, double d, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addTrackingFloatValue");
        }
        if ((i & 2) != 0) {
            d = 0.0d;
        }
        baseTrackingReportTask.addTrackingFloatValue(str, d);
    }

    public final void addTrackingFloatValue(String key, double value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "tacking key:" + key + ",value:" + value);
        String str = ReportSpUtils.INSTANCE.get(key, this.DEFAULT_TRACKING_FLOAT_VALUE);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "before tracking value : " + str);
        double parseDouble = Double.parseDouble(str) + value;
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "after tracking value:" + str);
        ReportSpUtils.INSTANCE.set(key, String.valueOf(parseDouble));
    }

    public final double getTrackingFloatValue(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        String str = ReportSpUtils.INSTANCE.get(key, this.DEFAULT_TRACKING_FLOAT_VALUE);
        NetWorkLog.INSTANCE.mo3278d(this.TAG, key + " getTrackingFloatValue " + str);
        return Double.parseDouble(str);
    }

    public final String getTrackJsonValue(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        String str = ReportSpUtils.INSTANCE.get(key, "");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, key + " getTrackJsonValue " + str);
        return str;
    }

    public final void removeTracking(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "removeTracking key:" + key);
        ReportSpUtils.INSTANCE.set(key, this.DEFAULT_TRACKING_VALUE);
    }

    public final void removeTracking(String key, String content) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(content, "content");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "removeTracking key:" + key);
        ReportSpUtils.INSTANCE.set(key, content);
    }

    public final void addTrackJson(String key, String content) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(content, "content");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "addTrackJson " + key + " content " + content);
        ReportSpUtils.INSTANCE.set(key, content);
    }

    public static /* synthetic */ void removeTackJson$default(BaseTrackingReportTask baseTrackingReportTask, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeTackJson");
        }
        if ((i & 2) != 0) {
            str2 = "";
        }
        baseTrackingReportTask.removeTackJson(str, str2);
    }

    public final void removeTackJson(String key, String content) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(content, "content");
        NetWorkLog.INSTANCE.mo3278d(this.TAG, "removeTrackJson " + key);
        ReportSpUtils.INSTANCE.set(key, content);
    }
}
