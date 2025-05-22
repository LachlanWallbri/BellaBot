package com.amazonaws.services.p048s3.internal;

import java.util.Date;

/* loaded from: classes.dex */
public interface ObjectExpirationResult {
    Date getExpirationTime();

    String getExpirationTimeRuleId();

    void setExpirationTime(Date date);

    void setExpirationTimeRuleId(String str);
}
