package org.bouncycastle.crypto.signers;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.Ed448PrivateKeyParameters;
import org.bouncycastle.crypto.params.Ed448PublicKeyParameters;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class Ed448Signer implements Signer {
    private final Buffer buffer = new Buffer();
    private final byte[] context;
    private boolean forSigning;
    private Ed448PrivateKeyParameters privateKey;
    private Ed448PublicKeyParameters publicKey;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Buffer extends ByteArrayOutputStream {
        private Buffer() {
        }

        synchronized byte[] generateSignature(Ed448PrivateKeyParameters ed448PrivateKeyParameters, Ed448PublicKeyParameters ed448PublicKeyParameters, byte[] bArr) {
            byte[] bArr2;
            bArr2 = new byte[114];
            ed448PrivateKeyParameters.sign(0, ed448PublicKeyParameters, bArr, this.buf, 0, this.count, bArr2, 0);
            reset();
            return bArr2;
        }

        @Override // java.io.ByteArrayOutputStream
        public synchronized void reset() {
            Arrays.fill(this.buf, 0, this.count, (byte) 0);
            this.count = 0;
        }

        synchronized boolean verifySignature(Ed448PublicKeyParameters ed448PublicKeyParameters, byte[] bArr, byte[] bArr2) {
            if (114 != bArr2.length) {
                return false;
            }
            boolean verify = Ed448.verify(bArr2, 0, ed448PublicKeyParameters.getEncoded(), 0, bArr, this.buf, 0, this.count);
            reset();
            return verify;
        }
    }

    public Ed448Signer(byte[] bArr) {
        this.context = Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() {
        Ed448PrivateKeyParameters ed448PrivateKeyParameters;
        if (!this.forSigning || (ed448PrivateKeyParameters = this.privateKey) == null) {
            throw new IllegalStateException("Ed448Signer not initialised for signature generation.");
        }
        return this.buffer.generateSignature(ed448PrivateKeyParameters, this.publicKey, this.context);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        this.forSigning = z;
        if (z) {
            this.privateKey = (Ed448PrivateKeyParameters) cipherParameters;
            this.publicKey = this.privateKey.generatePublicKey();
        } else {
            this.privateKey = null;
            this.publicKey = (Ed448PublicKeyParameters) cipherParameters;
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.buffer.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.buffer.write(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.buffer.write(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        Ed448PublicKeyParameters ed448PublicKeyParameters;
        if (this.forSigning || (ed448PublicKeyParameters = this.publicKey) == null) {
            throw new IllegalStateException("Ed448Signer not initialised for verification");
        }
        return this.buffer.verifySignature(ed448PublicKeyParameters, this.context, bArr);
    }
}
