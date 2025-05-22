package com.pudutech.bumblebee.robot_ui.util.finishapp;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.tencent.bugly.crashreport.CrashReport;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashReportConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/finishapp/CrashReportConfig;", "", "()V", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CrashReportConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static CrashReport.CrashHandleCallback save2FileOnCallback = new CrashReport.CrashHandleCallback() { // from class: com.pudutech.bumblebee.robot_ui.util.finishapp.CrashReportConfig$Companion$save2FileOnCallback$1
        @Override // com.tencent.bugly.BuglyStrategy.C5863a
        public synchronized Map<String, String> onCrashHandleStart(int crashType, String errorType, String errorMessage, String errorStack) {
            AppStatusTracker.finishAllActivities();
            Pdlog.m3273d("bugReport", "stop ");
            Pdlog.m3274e("Crash", "\ncrash type" + crashType + "\nerror type " + errorType + "\nerror message " + errorMessage + "\nerror errorStack " + errorStack);
            Pdlog.m3273d("bugReport", "In Kill process");
            Pdlog.m3273d("bugReport", "exit");
            return null;
        }
    };

    /* compiled from: CrashReportConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/finishapp/CrashReportConfig$Companion;", "", "()V", "save2FileOnCallback", "Lcom/tencent/bugly/crashreport/CrashReport$CrashHandleCallback;", "initCrashReport", "", "context", "Landroid/content/Context;", "appid", "", "debug", "", "resetRobotSerialID", "id", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void initCrashReport(Context context, String appid, boolean debug) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(appid, "appid");
            CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(context);
            userStrategy.setCrashHandleCallback(CrashReportConfig.save2FileOnCallback);
            CrashReport.initCrashReport(context, appid, debug, userStrategy);
        }

        public final void resetRobotSerialID(String id) {
            Intrinsics.checkParameterIsNotNull(id, "id");
            CrashReport.setUserId(id);
        }
    }
}
