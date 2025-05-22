package com.pudutech.robot.module.report.protocol;

import kotlin.Metadata;

/* compiled from: SupportAbilityReport.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/SupportAbilityReport;", "Lcom/pudutech/robot/module/report/protocol/BaseV2Report;", "()V", "abilities", "", "getAbilities", "()[I", "setAbilities", "([I)V", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class SupportAbilityReport extends BaseV2Report {
    private int[] abilities;

    public SupportAbilityReport() {
        setType("support_ability");
    }

    public final int[] getAbilities() {
        return this.abilities;
    }

    public final void setAbilities(int[] iArr) {
        this.abilities = iArr;
    }
}
