package okhttp3;

import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.ByteString;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        return "Basic " + ByteString.encodeString(str + ":" + str2, charset).base64();
    }
}
