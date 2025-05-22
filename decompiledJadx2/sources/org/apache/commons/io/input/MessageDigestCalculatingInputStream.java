package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.commons.io.input.ObservableInputStream;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* loaded from: classes2.dex */
    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {

        /* renamed from: md */
        private final MessageDigest f8981md;

        public MessageDigestMaintainingObserver(MessageDigest messageDigest) {
            this.f8981md = messageDigest;
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        void data(int i) throws IOException {
            this.f8981md.update((byte) i);
        }

        @Override // org.apache.commons.io.input.ObservableInputStream.Observer
        void data(byte[] bArr, int i, int i2) throws IOException {
            this.f8981md.update(bArr, i, i2);
        }
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, MessageDigest messageDigest) {
        super(inputStream);
        this.messageDigest = messageDigest;
        add(new MessageDigestMaintainingObserver(messageDigest));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, String str) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(str));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(MessageDigestAlgorithms.MD5));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
