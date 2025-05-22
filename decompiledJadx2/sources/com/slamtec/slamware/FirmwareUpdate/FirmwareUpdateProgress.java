package com.slamtec.slamware.FirmwareUpdate;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class FirmwareUpdateProgress {
    private int currentStep;
    private String currentStepName;
    private int currentStepProgress;
    private int totalStep;

    public FirmwareUpdateProgress(int i, int i2, int i3, String str) {
        this.currentStep = i;
        this.totalStep = i2;
        this.currentStepProgress = i3;
        this.currentStepName = str;
    }

    public int getCurrentStep() {
        return this.currentStep;
    }

    public int getTotalStep() {
        return this.totalStep;
    }

    public int getCurrentStepProgress() {
        return this.currentStepProgress;
    }

    public String getCurrentStepName() {
        return this.currentStepName;
    }
}
