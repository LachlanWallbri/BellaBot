package io.netty.handler.codec.http.websocketx.extensions;

import java.util.Collections;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class WebSocketExtensionData {
    private final String name;
    private final Map<String, String> parameters;

    public WebSocketExtensionData(String str, Map<String, String> map) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        if (map == null) {
            throw new NullPointerException("parameters");
        }
        this.name = str;
        this.parameters = Collections.unmodifiableMap(map);
    }

    public String name() {
        return this.name;
    }

    public Map<String, String> parameters() {
        return this.parameters;
    }
}
