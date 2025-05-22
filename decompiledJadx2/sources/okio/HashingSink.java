package okio;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.amazonaws.services.p048s3.internal.Constants;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class HashingSink extends ForwardingSink {

    @Nullable
    private final Mac mac;

    @Nullable
    private final MessageDigest messageDigest;

    public static HashingSink md5(Sink sink) {
        return new HashingSink(sink, MessageDigestAlgorithms.MD5);
    }

    public static HashingSink sha1(Sink sink) {
        return new HashingSink(sink, "SHA-1");
    }

    public static HashingSink sha256(Sink sink) {
        return new HashingSink(sink, "SHA-256");
    }

    public static HashingSink sha512(Sink sink) {
        return new HashingSink(sink, "SHA-512");
    }

    public static HashingSink hmacSha1(Sink sink, ByteString byteString) {
        return new HashingSink(sink, byteString, Constants.HMAC_SHA1_ALGORITHM);
    }

    public static HashingSink hmacSha256(Sink sink, ByteString byteString) {
        return new HashingSink(sink, byteString, "HmacSHA256");
    }

    public static HashingSink hmacSha512(Sink sink, ByteString byteString) {
        return new HashingSink(sink, byteString, "HmacSHA512");
    }

    private HashingSink(Sink sink, String str) {
        super(sink);
        try {
            this.messageDigest = MessageDigest.getInstance(str);
            this.mac = null;
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    private HashingSink(Sink sink, ByteString byteString, String str) {
        super(sink);
        try {
            Mac mac = Mac.getInstance(str);
            this.mac = mac;
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            this.messageDigest = null;
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* compiled from: HashingSink.kt */
    @Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000f"}, m3961d2 = {"Lokio/HashingSink$Companion;", "", "()V", "hmacSha1", "Lokio/HashingSink;", "sink", "Lokio/Sink;", TransferTable.COLUMN_KEY, "Lokio/ByteString;", "hmacSha256", "hmacSha512", "md5", "sha1", TmpConstant.VALUE_SHA256, "sha512", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final HashingSink md5(Sink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return new HashingSink(sink, MessageDigestAlgorithms.MD5);
        }

        @JvmStatic
        public final HashingSink sha1(Sink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return new HashingSink(sink, "SHA-1");
        }

        @JvmStatic
        public final HashingSink sha256(Sink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return new HashingSink(sink, "SHA-256");
        }

        @JvmStatic
        public final HashingSink sha512(Sink sink) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            return new HashingSink(sink, "SHA-512");
        }

        @JvmStatic
        public final HashingSink hmacSha1(Sink sink, ByteString key) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new HashingSink(sink, key, Constants.HMAC_SHA1_ALGORITHM);
        }

        @JvmStatic
        public final HashingSink hmacSha256(Sink sink, ByteString key) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new HashingSink(sink, key, "HmacSHA256");
        }

        @JvmStatic
        public final HashingSink hmacSha512(Sink sink, ByteString key) {
            Intrinsics.checkParameterIsNotNull(sink, "sink");
            Intrinsics.checkParameterIsNotNull(key, "key");
            return new HashingSink(sink, key, "HmacSHA512");
        }
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        C9045Util.checkOffsetAndCount(buffer.size, 0L, j);
        Segment segment = buffer.head;
        long j2 = 0;
        while (j2 < j) {
            int min = (int) Math.min(j - j2, segment.limit - segment.pos);
            MessageDigest messageDigest = this.messageDigest;
            if (messageDigest != null) {
                messageDigest.update(segment.data, segment.pos, min);
            } else {
                this.mac.update(segment.data, segment.pos, min);
            }
            j2 += min;
            segment = segment.next;
        }
        super.write(buffer, j);
    }

    public final ByteString hash() {
        MessageDigest messageDigest = this.messageDigest;
        return ByteString.m3992of(messageDigest != null ? messageDigest.digest() : this.mac.doFinal());
    }
}
