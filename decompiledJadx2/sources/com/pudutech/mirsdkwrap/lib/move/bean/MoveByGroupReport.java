package com.pudutech.mirsdkwrap.lib.move.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByGroupReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupReport;", "", "moveReportData", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;", "(Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;)V", "getMoveReportData", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveReportData;", "setMoveReportData", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveByGroupReport {
    private MoveReportData moveReportData;

    public MoveByGroupReport() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ MoveByGroupReport copy$default(MoveByGroupReport moveByGroupReport, MoveReportData moveReportData, int i, Object obj) {
        if ((i & 1) != 0) {
            moveReportData = moveByGroupReport.moveReportData;
        }
        return moveByGroupReport.copy(moveReportData);
    }

    /* renamed from: component1, reason: from getter */
    public final MoveReportData getMoveReportData() {
        return this.moveReportData;
    }

    public final MoveByGroupReport copy(MoveReportData moveReportData) {
        Intrinsics.checkParameterIsNotNull(moveReportData, "moveReportData");
        return new MoveByGroupReport(moveReportData);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof MoveByGroupReport) && Intrinsics.areEqual(this.moveReportData, ((MoveByGroupReport) other).moveReportData);
        }
        return true;
    }

    public int hashCode() {
        MoveReportData moveReportData = this.moveReportData;
        if (moveReportData != null) {
            return moveReportData.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "MoveByGroupReport(moveReportData=" + this.moveReportData + ")";
    }

    public MoveByGroupReport(MoveReportData moveReportData) {
        Intrinsics.checkParameterIsNotNull(moveReportData, "moveReportData");
        this.moveReportData = moveReportData;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ MoveByGroupReport(com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
        /*
            r17 = this;
            r0 = r19 & 1
            if (r0 == 0) goto L1e
            com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData r0 = new com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = 0
            r7 = 0
            r9 = 0
            r11 = 0
            r13 = 0
            r15 = 255(0xff, float:3.57E-43)
            r16 = 0
            r1 = r0
            r1.<init>(r2, r3, r4, r6, r7, r9, r11, r13, r15, r16)
            r0 = r17
            goto L22
        L1e:
            r0 = r17
            r1 = r18
        L22:
            r0.<init>(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.pudutech.mirsdkwrap.lib.move.bean.MoveByGroupReport.<init>(com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final MoveReportData getMoveReportData() {
        return this.moveReportData;
    }

    public final void setMoveReportData(MoveReportData moveReportData) {
        Intrinsics.checkParameterIsNotNull(moveReportData, "<set-?>");
        this.moveReportData = moveReportData;
    }
}
