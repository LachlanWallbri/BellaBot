package io.grpc.netty.shaded.io.netty.handler.codec.rtsp;

import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpMethod;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class RtspMethods {
    public static final HttpMethod OPTIONS = HttpMethod.OPTIONS;
    public static final HttpMethod DESCRIBE = HttpMethod.valueOf("DESCRIBE");
    public static final HttpMethod ANNOUNCE = HttpMethod.valueOf("ANNOUNCE");
    public static final HttpMethod SETUP = HttpMethod.valueOf("SETUP");
    public static final HttpMethod PLAY = HttpMethod.valueOf("PLAY");
    public static final HttpMethod PAUSE = HttpMethod.valueOf("PAUSE");
    public static final HttpMethod TEARDOWN = HttpMethod.valueOf("TEARDOWN");
    public static final HttpMethod GET_PARAMETER = HttpMethod.valueOf("GET_PARAMETER");
    public static final HttpMethod SET_PARAMETER = HttpMethod.valueOf("SET_PARAMETER");
    public static final HttpMethod REDIRECT = HttpMethod.valueOf("REDIRECT");
    public static final HttpMethod RECORD = HttpMethod.valueOf("RECORD");
    private static final Map<String, HttpMethod> methodMap = new HashMap();

    static {
        methodMap.put(DESCRIBE.toString(), DESCRIBE);
        methodMap.put(ANNOUNCE.toString(), ANNOUNCE);
        methodMap.put(GET_PARAMETER.toString(), GET_PARAMETER);
        methodMap.put(OPTIONS.toString(), OPTIONS);
        methodMap.put(PAUSE.toString(), PAUSE);
        methodMap.put(PLAY.toString(), PLAY);
        methodMap.put(RECORD.toString(), RECORD);
        methodMap.put(REDIRECT.toString(), REDIRECT);
        methodMap.put(SETUP.toString(), SETUP);
        methodMap.put(SET_PARAMETER.toString(), SET_PARAMETER);
        methodMap.put(TEARDOWN.toString(), TEARDOWN);
    }

    public static HttpMethod valueOf(String str) {
        ObjectUtil.checkNotNull(str, "name");
        String upperCase = str.trim().toUpperCase();
        if (upperCase.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        }
        HttpMethod httpMethod = methodMap.get(upperCase);
        return httpMethod != null ? httpMethod : HttpMethod.valueOf(upperCase);
    }

    private RtspMethods() {
    }
}
