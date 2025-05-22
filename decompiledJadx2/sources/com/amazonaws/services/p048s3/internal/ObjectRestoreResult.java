package com.amazonaws.services.p048s3.internal;

import java.util.Date;

/* loaded from: classes.dex */
public interface ObjectRestoreResult {
    Boolean getOngoingRestore();

    Date getRestoreExpirationTime();

    void setOngoingRestore(boolean z);

    void setRestoreExpirationTime(Date date);
}
