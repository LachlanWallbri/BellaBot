package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class WebSocketUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final FastThreadLocal<MessageDigest> MD5 = new FastThreadLocal<MessageDigest>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public MessageDigest initialValue() throws Exception {
            try {
                return MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            } catch (NoSuchAlgorithmException unused) {
                throw new InternalError("MD5 not supported on this platform - Outdated?");
            }
        }
    };
    private static final FastThreadLocal<MessageDigest> SHA1 = new FastThreadLocal<MessageDigest>() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx.WebSocketUtil.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.FastThreadLocal
        public MessageDigest initialValue() throws Exception {
            try {
                return MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException unused) {
                throw new InternalError("SHA-1 not supported on this platform - Outdated?");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] md5(byte[] bArr) {
        return digest(MD5, bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] sha1(byte[] bArr) {
        return digest(SHA1, bArr);
    }

    private static byte[] digest(FastThreadLocal<MessageDigest> fastThreadLocal, byte[] bArr) {
        MessageDigest messageDigest = fastThreadLocal.get();
        messageDigest.reset();
        return messageDigest.digest(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String base64(byte[] bArr) {
        if (PlatformDependent.javaVersion() >= 8) {
            return Base64.getEncoder().encodeToString(bArr);
        }
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bArr);
        try {
            ByteBuf encode = io.grpc.netty.shaded.io.netty.handler.codec.base64.Base64.encode(wrappedBuffer);
            try {
                return encode.toString(CharsetUtil.UTF_8);
            } finally {
                encode.release();
            }
        } finally {
            wrappedBuffer.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] randomBytes(int i) {
        byte[] bArr = new byte[i];
        PlatformDependent.threadLocalRandom().nextBytes(bArr);
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int randomNumber(int i, int i2) {
        return (int) (i + (PlatformDependent.threadLocalRandom().nextDouble() * (i2 - i)));
    }

    private WebSocketUtil() {
    }
}
