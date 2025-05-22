package com.pudutech.lib_update.base.net;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes4.dex */
public interface HostType {
    public static final int HOST_HTTP_ENTRANCE = 0;
    public static final int HOST_POST_MAC = 1;
    public static final int HOST_UPDATE = 2;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes4.dex */
    public @interface HostTypeChecker {
    }
}
