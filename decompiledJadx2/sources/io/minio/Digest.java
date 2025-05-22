package io.minio;

import com.google.common.io.BaseEncoding;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes7.dex */
public class Digest {
    public static final String ZERO_MD5_HASH = "1B2M2Y8AsgTpgAmY7PhCfg==";
    public static final String ZERO_SHA256_HASH = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    private Digest() {
    }

    public static String md5Hash(byte[] bArr, int i) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        messageDigest.update(bArr, 0, i);
        return Base64.getEncoder().encodeToString(messageDigest.digest());
    }

    public static String sha256Hash(byte[] bArr, int i) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArr, 0, i);
        return BaseEncoding.base16().encode(messageDigest.digest()).toLowerCase(Locale.US);
    }

    public static String sha256Hash(String str) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return sha256Hash(bytes, bytes.length);
    }

    @Deprecated
    public static String[] sha256Md5Hashes(Object obj, int i) throws NoSuchAlgorithmException, IOException, InsufficientDataException, InternalException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        MessageDigest messageDigest2 = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        if ((obj instanceof BufferedInputStream) || (obj instanceof RandomAccessFile)) {
            updateDigests(obj, i, messageDigest, messageDigest2);
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            messageDigest.update(bArr, 0, i);
            messageDigest2.update(bArr, 0, i);
        } else {
            throw new InternalException("Unknown data source to calculate SHA-256 hash. This should not happen, please report this issue at https://github.com/minio/minio-java/issues", null);
        }
        return new String[]{BaseEncoding.base16().encode(messageDigest.digest()).toLowerCase(Locale.US), BaseEncoding.base64().encode(messageDigest2.digest())};
    }

    private static int updateDigests(Object obj, int i, MessageDigest messageDigest, MessageDigest messageDigest2) throws IOException, InsufficientDataException {
        BufferedInputStream bufferedInputStream;
        int read;
        RandomAccessFile randomAccessFile = null;
        if (obj instanceof RandomAccessFile) {
            randomAccessFile = (RandomAccessFile) obj;
            bufferedInputStream = null;
        } else {
            bufferedInputStream = obj instanceof BufferedInputStream ? (BufferedInputStream) obj : null;
        }
        long j = 0;
        if (randomAccessFile != null) {
            j = randomAccessFile.getFilePointer();
        } else {
            bufferedInputStream.mark(i);
        }
        byte[] bArr = new byte[16384];
        int length = bArr.length;
        int i2 = 0;
        while (i2 < i) {
            int i3 = i - i2;
            if (i3 < length) {
                length = i3;
            }
            if (randomAccessFile != null) {
                read = randomAccessFile.read(bArr, 0, length);
            } else {
                read = bufferedInputStream.read(bArr, 0, length);
            }
            if (read < 0) {
                throw new InsufficientDataException("Insufficient data.  bytes read " + i2 + " expected " + i);
            }
            if (read > 0) {
                if (messageDigest != null) {
                    messageDigest.update(bArr, 0, read);
                }
                if (messageDigest2 != null) {
                    messageDigest2.update(bArr, 0, read);
                }
                i2 += read;
            }
        }
        if (randomAccessFile != null) {
            randomAccessFile.seek(j);
        } else {
            bufferedInputStream.reset();
        }
        return i2;
    }
}
