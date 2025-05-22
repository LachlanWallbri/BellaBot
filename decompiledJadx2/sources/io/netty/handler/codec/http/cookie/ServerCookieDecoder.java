package io.netty.handler.codec.http.cookie;

import io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ServerCookieDecoder extends CookieDecoder {
    private static final String RFC2965_DOMAIN = "$Domain";
    private static final String RFC2965_PATH = "$Path";
    private static final String RFC2965_PORT = "$Port";
    private static final String RFC2965_VERSION = "$Version";
    public static final ServerCookieDecoder STRICT = new ServerCookieDecoder(true);
    public static final ServerCookieDecoder LAX = new ServerCookieDecoder(false);

    private ServerCookieDecoder(boolean z) {
        super(z);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0063, code lost:
    
        r10 = -1;
        r11 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x006a, code lost:
    
        r6 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x006c, code lost:
    
        if (r6 != r0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x006e, code lost:
    
        r9 = r2;
        r10 = 0;
        r11 = 0;
        r2 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0073, code lost:
    
        r7 = r14.indexOf(59, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0077, code lost:
    
        if (r7 <= 0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x007a, code lost:
    
        r7 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x007b, code lost:
    
        r9 = r2;
        r10 = r6;
        r2 = r7;
        r11 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x008f -> B:9:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0098 -> B:9:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00a0 -> B:9:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00a9 -> B:9:0x002f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00ab -> B:9:0x002f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Set<Cookie> decode(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        DefaultCookie initCookie;
        int length = ((String) ObjectUtil.checkNotNull(str, "header")).length();
        if (length == 0) {
            return Collections.emptySet();
        }
        TreeSet treeSet = new TreeSet();
        boolean z = true;
        if (!str.regionMatches(true, 0, RFC2965_VERSION, 0, 8)) {
            z = false;
            i = 0;
            while (i != length) {
            }
            return treeSet;
        }
        int i5 = str.indexOf(59) + 1;
        i = i5;
        while (i != length) {
            char charAt = str.charAt(i);
            if (charAt == '\t' || charAt == '\n' || charAt == 11 || charAt == '\f' || charAt == '\r' || charAt == ' ' || charAt == ',' || charAt == ';') {
                i++;
            } else {
                i5 = i;
                while (true) {
                    char charAt2 = str.charAt(i5);
                    if (charAt2 == ';') {
                        i2 = i5;
                        break;
                    }
                    if (charAt2 == '=') {
                        break;
                    }
                    i5++;
                    if (i5 == length) {
                        i2 = length;
                        break;
                    }
                }
                if ((!z || (!str.regionMatches(i, RFC2965_PATH, 0, 5) && !str.regionMatches(i, RFC2965_DOMAIN, 0, 7) && !str.regionMatches(i, RFC2965_PORT, 0, 5))) && (initCookie = initCookie(str, i, i2, i4, i3)) != null) {
                    treeSet.add(initCookie);
                }
                i = i5;
                while (i != length) {
                }
            }
        }
        return treeSet;
    }
}
