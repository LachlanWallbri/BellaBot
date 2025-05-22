package com.pudutech.bumblebee.presenter.monitor_task;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClassifyDefine.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/monitor_task/Classify;", "", "process", "Lcom/pudutech/bumblebee/presenter/monitor_task/Process;", "display", "Lcom/pudutech/bumblebee/presenter/monitor_task/DisplayLevel;", "(Lcom/pudutech/bumblebee/presenter/monitor_task/Process;Lcom/pudutech/bumblebee/presenter/monitor_task/DisplayLevel;)V", "getDisplay", "()Lcom/pudutech/bumblebee/presenter/monitor_task/DisplayLevel;", "getProcess", "()Lcom/pudutech/bumblebee/presenter/monitor_task/Process;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class Classify {
    private final DisplayLevel display;
    private final Process process;

    public static /* synthetic */ Classify copy$default(Classify classify, Process process, DisplayLevel displayLevel, int i, Object obj) {
        if ((i & 1) != 0) {
            process = classify.process;
        }
        if ((i & 2) != 0) {
            displayLevel = classify.display;
        }
        return classify.copy(process, displayLevel);
    }

    /* renamed from: component1, reason: from getter */
    public final Process getProcess() {
        return this.process;
    }

    /* renamed from: component2, reason: from getter */
    public final DisplayLevel getDisplay() {
        return this.display;
    }

    public final Classify copy(Process process, DisplayLevel display) {
        Intrinsics.checkParameterIsNotNull(process, "process");
        Intrinsics.checkParameterIsNotNull(display, "display");
        return new Classify(process, display);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Classify)) {
            return false;
        }
        Classify classify = (Classify) other;
        return Intrinsics.areEqual(this.process, classify.process) && Intrinsics.areEqual(this.display, classify.display);
    }

    public int hashCode() {
        Process process = this.process;
        int hashCode = (process != null ? process.hashCode() : 0) * 31;
        DisplayLevel displayLevel = this.display;
        return hashCode + (displayLevel != null ? displayLevel.hashCode() : 0);
    }

    public String toString() {
        return "Classify(process=" + this.process + ", display=" + this.display + ")";
    }

    public Classify(Process process, DisplayLevel display) {
        Intrinsics.checkParameterIsNotNull(process, "process");
        Intrinsics.checkParameterIsNotNull(display, "display");
        this.process = process;
        this.display = display;
    }

    public final DisplayLevel getDisplay() {
        return this.display;
    }

    public final Process getProcess() {
        return this.process;
    }
}
