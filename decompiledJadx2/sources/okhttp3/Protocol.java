package okhttp3;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    private final String protocol;

    Protocol(String str) {
        this.protocol = str;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* compiled from: Protocol.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, m3961d2 = {"Lokhttp3/Protocol$Companion;", "", "()V", TmpConstant.PROPERTY_IDENTIFIER_GET, "Lokhttp3/Protocol;", "protocol", "", "okhttp"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Protocol get(String protocol) throws IOException {
            Intrinsics.checkParameterIsNotNull(protocol, "protocol");
            if (Intrinsics.areEqual(protocol, Protocol.access$getProtocol$p(Protocol.HTTP_1_0))) {
                return Protocol.HTTP_1_0;
            }
            if (Intrinsics.areEqual(protocol, Protocol.access$getProtocol$p(Protocol.HTTP_1_1))) {
                return Protocol.HTTP_1_1;
            }
            if (Intrinsics.areEqual(protocol, Protocol.access$getProtocol$p(Protocol.H2_PRIOR_KNOWLEDGE))) {
                return Protocol.H2_PRIOR_KNOWLEDGE;
            }
            if (Intrinsics.areEqual(protocol, Protocol.access$getProtocol$p(Protocol.HTTP_2))) {
                return Protocol.HTTP_2;
            }
            if (Intrinsics.areEqual(protocol, Protocol.access$getProtocol$p(Protocol.SPDY_3))) {
                return Protocol.SPDY_3;
            }
            if (Intrinsics.areEqual(protocol, Protocol.access$getProtocol$p(Protocol.QUIC))) {
                return Protocol.QUIC;
            }
            throw new IOException("Unexpected protocol: " + protocol);
        }
    }

    public static Protocol get(String str) throws IOException {
        if (str.equals(HTTP_1_0.protocol)) {
            return HTTP_1_0;
        }
        if (str.equals(HTTP_1_1.protocol)) {
            return HTTP_1_1;
        }
        if (str.equals(H2_PRIOR_KNOWLEDGE.protocol)) {
            return H2_PRIOR_KNOWLEDGE;
        }
        if (str.equals(HTTP_2.protocol)) {
            return HTTP_2;
        }
        if (str.equals(SPDY_3.protocol)) {
            return SPDY_3;
        }
        if (str.equals(QUIC.protocol)) {
            return QUIC;
        }
        throw new IOException("Unexpected protocol: " + str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
