package com.slamtec.slamware.robot;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class RecoverLocalizationOptions {
    private Integer maxRecoverTimeInMilliSeconds = null;
    private RecoverLocalizationMovement recoverMovementType = null;

    public Integer getMaxRecoverTimeInMilliSeconds() {
        return this.maxRecoverTimeInMilliSeconds;
    }

    public void setMaxRecoverTimeInMilliSeconds(Integer num) {
        this.maxRecoverTimeInMilliSeconds = num;
    }

    public RecoverLocalizationMovement getRecoverMovementType() {
        return this.recoverMovementType;
    }

    public void setRecoverMovementType(RecoverLocalizationMovement recoverLocalizationMovement) {
        this.recoverMovementType = recoverLocalizationMovement;
    }
}
