package io.grpc.netty.shaded.io.netty.util.internal;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ResourcesUtil {
    public static File getFile(Class cls, String str) {
        try {
            return new File(URLDecoder.decode(cls.getResource(str).getFile(), "UTF-8"));
        } catch (UnsupportedEncodingException unused) {
            return new File(cls.getResource(str).getFile());
        }
    }

    private ResourcesUtil() {
    }
}
