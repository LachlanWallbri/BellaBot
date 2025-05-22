package com.pudutech.lib_update.base.net;

import com.pudutech.lib_update.config.UrlManager;
import com.pudutech.light_network.intercepter.RequestInterceptor;
import okhttp3.HttpUrl;

/* loaded from: classes4.dex */
public class AddMoreHostWork extends RequestInterceptor {
    @Override // com.pudutech.light_network.intercepter.RequestInterceptor
    public HttpUrl addMoreHost(int i) {
        if (i == 0 || i == 1) {
            return null;
        }
        if (i == 2) {
            return HttpUrl.get(UrlManager.getUPDATE_HOST());
        }
        return HttpUrl.parse("http://192.168.1.101:9500");
    }
}
