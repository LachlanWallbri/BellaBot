package org.jboss.netty.handler.codec.http.websocketx;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.base64.Base64;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
final class WebSocketUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] md5(byte[] bArr) {
        try {
            return MessageDigest.getInstance(MessageDigestAlgorithms.MD5).digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            throw new InternalError("MD5 not supported on this platform");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] sha1(byte[] bArr) {
        try {
            return MessageDigest.getInstance("SHA1").digest(bArr);
        } catch (NoSuchAlgorithmException unused) {
            throw new InternalError("SHA-1 not supported on this platform");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String base64(byte[] bArr) {
        return Base64.encode(ChannelBuffers.wrappedBuffer(bArr)).toString(CharsetUtil.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] randomBytes(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) randomNumber(0, 255);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int randomNumber(int i, int i2) {
        return (int) ((Math.random() * i2) + i);
    }

    private WebSocketUtil() {
    }
}
