package okhttp3.internal.platform;

import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import org.conscrypt.ConscryptHostnameVerifier;

/* compiled from: ConscryptPlatform.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, m3961d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "Ljavax/net/ssl/SSLSession;", "verify"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class ConscryptPlatform$platformTrustManager$2 implements ConscryptHostnameVerifier {
    public static final ConscryptPlatform$platformTrustManager$2 INSTANCE = new ConscryptPlatform$platformTrustManager$2();

    ConscryptPlatform$platformTrustManager$2() {
    }

    public final boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
