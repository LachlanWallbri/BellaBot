package com.amazonaws;

import com.amazonaws.internal.config.HttpClientConfig;
import com.amazonaws.internal.config.InternalConfig;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public enum ServiceNameFactory {
    ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getServiceName(String str) {
        HttpClientConfig httpClientConfig = InternalConfig.Factory.getInternalConfig().getHttpClientConfig(str);
        if (httpClientConfig == null) {
            return null;
        }
        return httpClientConfig.getServiceName();
    }
}
