package com.pudutech.bumblebee.presenter.report_cloud.task;

import android.content.Context;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.robot.module.report.task.ReportNewSysUpgrade;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportUpgrade.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportUpgrade;", "", "()V", "mReportAppUpgrade", "Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportNewAppUpgrade;", "getMReportAppUpgrade", "()Lcom/pudutech/bumblebee/presenter/report_cloud/task/ReportNewAppUpgrade;", "mReportAppUpgrade$delegate", "Lkotlin/Lazy;", "mReportSysUpgrade", "Lcom/pudutech/robot/module/report/task/ReportNewSysUpgrade;", "getMReportSysUpgrade", "()Lcom/pudutech/robot/module/report/task/ReportNewSysUpgrade;", "mReportSysUpgrade$delegate", "recordAppUpgrade", "", AIUIConstant.KEY_CONTENT, "Landroid/content/Context;", "targetVerName", "", "recordSystemUpgrade", "targetVerCode", "reportAppUpgrade", "reportSystemUpgrade", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ReportUpgrade {
    public static final ReportUpgrade INSTANCE = new ReportUpgrade();

    /* renamed from: mReportAppUpgrade$delegate, reason: from kotlin metadata */
    private static final Lazy mReportAppUpgrade = LazyKt.lazy(new Function0<ReportNewAppUpgrade>() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportUpgrade$mReportAppUpgrade$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ReportNewAppUpgrade invoke() {
            return new ReportNewAppUpgrade();
        }
    });

    /* renamed from: mReportSysUpgrade$delegate, reason: from kotlin metadata */
    private static final Lazy mReportSysUpgrade = LazyKt.lazy(new Function0<ReportNewSysUpgrade>() { // from class: com.pudutech.bumblebee.presenter.report_cloud.task.ReportUpgrade$mReportSysUpgrade$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ReportNewSysUpgrade invoke() {
            return new ReportNewSysUpgrade();
        }
    });

    private final ReportNewAppUpgrade getMReportAppUpgrade() {
        return (ReportNewAppUpgrade) mReportAppUpgrade.getValue();
    }

    private final ReportNewSysUpgrade getMReportSysUpgrade() {
        return (ReportNewSysUpgrade) mReportSysUpgrade.getValue();
    }

    private ReportUpgrade() {
    }

    public final void recordAppUpgrade(Context content, String targetVerName) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(targetVerName, "targetVerName");
        getMReportAppUpgrade().recordAppUpgrade(content, targetVerName);
    }

    public final void recordSystemUpgrade(Context content, String targetVerCode) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(targetVerCode, "targetVerCode");
        getMReportSysUpgrade().record(targetVerCode);
    }

    public final void reportAppUpgrade(Context content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        getMReportAppUpgrade().report();
    }

    public final void reportSystemUpgrade(Context content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        getMReportSysUpgrade().report();
    }
}
