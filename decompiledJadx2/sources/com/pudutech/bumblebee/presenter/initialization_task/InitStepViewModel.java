package com.pudutech.bumblebee.presenter.initialization_task;

import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.aidl.serialize.StepState;
import kotlin.Metadata;

/* compiled from: InitStepViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0004H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/initialization_task/InitStepViewModel;", "", "()V", "description", "", "getDescription", "()Ljava/lang/String;", "setDescription", "(Ljava/lang/String;)V", "state", "Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "getState", "()Lcom/pudutech/mirsdk/aidl/serialize/StepState;", "setState", "(Lcom/pudutech/mirsdk/aidl/serialize/StepState;)V", "step", "Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "getStep", "()Lcom/pudutech/mirsdk/aidl/serialize/InitStep;", "setStep", "(Lcom/pudutech/mirsdk/aidl/serialize/InitStep;)V", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InitStepViewModel {
    private String description;
    private StepState state = StepState.Idle;
    private InitStep step;

    public final InitStep getStep() {
        return this.step;
    }

    public final void setStep(InitStep initStep) {
        this.step = initStep;
    }

    public final StepState getState() {
        return this.state;
    }

    public final void setState(StepState stepState) {
        this.state = stepState;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "step=" + this.step + " state=" + this.state + " description=" + this.description;
    }
}
