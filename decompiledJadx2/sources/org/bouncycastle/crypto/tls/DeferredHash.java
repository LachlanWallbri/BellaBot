package org.bouncycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Shorts;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class DeferredHash implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    private DigestInputBuffer buf;
    protected TlsContext context;
    private Hashtable hashes;
    private Short prfHashAlgorithm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeferredHash() {
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = null;
    }

    private DeferredHash(Short sh, Digest digest) {
        this.buf = null;
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = sh;
        this.hashes.put(sh, digest);
    }

    protected void checkStopBuffering() {
        if (this.buf == null || this.hashes.size() > 4) {
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            this.buf.updateDigest((Digest) elements.nextElement());
        }
        this.buf = null;
    }

    protected void checkTrackingHash(Short sh) {
        if (this.hashes.containsKey(sh)) {
            return;
        }
        this.hashes.put(sh, TlsUtils.createHash(sh.shortValue()));
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public Digest forkPRFHash() {
        checkStopBuffering();
        if (this.buf == null) {
            return TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        }
        Digest createHash = TlsUtils.createHash(this.prfHashAlgorithm.shortValue());
        this.buf.updateDigest(createHash);
        return createHash;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public byte[] getFinalHash(short s) {
        Digest digest = (Digest) this.hashes.get(Shorts.valueOf(s));
        if (digest == null) {
            throw new IllegalStateException("HashAlgorithm." + HashAlgorithm.getText(s) + " is not being tracked");
        }
        Digest cloneHash = TlsUtils.cloneHash(s, digest);
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(cloneHash);
        }
        byte[] bArr = new byte[cloneHash.getDigestSize()];
        cloneHash.doFinal(bArr, 0);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash notifyPRFDetermined() {
        int prfAlgorithm = this.context.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm != 0) {
            this.prfHashAlgorithm = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
            checkTrackingHash(this.prfHashAlgorithm);
            return this;
        }
        CombinedHash combinedHash = new CombinedHash();
        combinedHash.init(this.context);
        this.buf.updateDigest(combinedHash);
        return combinedHash.notifyPRFDetermined();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.reset();
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).reset();
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void sealHashAlgorithms() {
        checkStopBuffering();
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public TlsHandshakeHash stopTracking() {
        Digest cloneHash = TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(cloneHash);
        }
        DeferredHash deferredHash = new DeferredHash(this.prfHashAlgorithm, cloneHash);
        deferredHash.init(this.context);
        return deferredHash;
    }

    @Override // org.bouncycastle.crypto.tls.TlsHandshakeHash
    public void trackHashAlgorithm(short s) {
        if (this.buf == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        checkTrackingHash(Shorts.valueOf(s));
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(b);
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).update(b);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(bArr, i, i2);
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).update(bArr, i, i2);
        }
    }
}
