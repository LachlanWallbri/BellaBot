package com.pudutech.voiceinteraction.component.utils;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: UrlOkManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/UrlOkManager;", "", "()V", "NPL_ANSWER", "", "NPL_LOG", "NPL_TTS_ANSWER", "isOkTestServer", "", "()Z", "setOkTestServer", "(Z)V", "url", "url_cloud_platform", "getUrl_cloud_platform", "()Ljava/lang/String;", "url_cloud_platform_test", "getUrl_cloud_platform_test", "url_dev", "url_test", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class UrlOkManager {
    public static final String NPL_ANSWER = "/api/nlp/conversation/v1";
    public static final String NPL_LOG = "/api/nlp/log/v1";
    public static final String NPL_TTS_ANSWER = "/api/common/tts/get_answer/v1";
    private static boolean isOkTestServer = false;
    public static final String url = "https://ai-gateway.pudutech.com";
    public static final String url_dev = "http://ai-gateway-pre-test.pudutech.com";
    public static final String url_test = "https://ai-gateway-test.pudutech.com";
    public static final UrlOkManager INSTANCE = new UrlOkManager();
    private static final String url_cloud_platform_test = url_cloud_platform_test;
    private static final String url_cloud_platform_test = url_cloud_platform_test;
    private static final String url_cloud_platform = url_cloud_platform;
    private static final String url_cloud_platform = url_cloud_platform;

    private UrlOkManager() {
    }

    public final boolean isOkTestServer() {
        return isOkTestServer;
    }

    public final void setOkTestServer(boolean z) {
        isOkTestServer = z;
    }

    public final String getUrl_cloud_platform_test() {
        return url_cloud_platform_test;
    }

    public final String getUrl_cloud_platform() {
        return url_cloud_platform;
    }
}
