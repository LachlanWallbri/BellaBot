package org.bouncycastle.crypto.tls;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.crypto.prng.RandomGenerator;
import org.bouncycastle.util.Times;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public abstract class AbstractTlsContext implements TlsContext {
    private static long counter = Times.nanoTime();
    private RandomGenerator nonceRandom;
    private SecureRandom secureRandom;
    private SecurityParameters securityParameters;
    private ProtocolVersion clientVersion = null;
    private ProtocolVersion serverVersion = null;
    private TlsSession session = null;
    private Object userObject = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractTlsContext(SecureRandom secureRandom, SecurityParameters securityParameters) {
        Digest createHash = TlsUtils.createHash((short) 4);
        byte[] bArr = new byte[createHash.getDigestSize()];
        secureRandom.nextBytes(bArr);
        this.nonceRandom = new DigestRandomGenerator(createHash);
        this.nonceRandom.addSeedMaterial(nextCounterValue());
        this.nonceRandom.addSeedMaterial(Times.nanoTime());
        this.nonceRandom.addSeedMaterial(bArr);
        this.secureRandom = secureRandom;
        this.securityParameters = securityParameters;
    }

    private static synchronized long nextCounterValue() {
        long j;
        synchronized (AbstractTlsContext.class) {
            j = counter + 1;
            counter = j;
        }
        return j;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public byte[] exportKeyingMaterial(String str, byte[] bArr, int i) {
        if (bArr != null && !TlsUtils.isValidUint16(bArr.length)) {
            throw new IllegalArgumentException("'context_value' must have length less than 2^16 (or be null)");
        }
        SecurityParameters securityParameters = getSecurityParameters();
        if (!securityParameters.isExtendedMasterSecret()) {
            throw new IllegalStateException("cannot export keying material without extended_master_secret");
        }
        byte[] clientRandom = securityParameters.getClientRandom();
        byte[] serverRandom = securityParameters.getServerRandom();
        int length = clientRandom.length + serverRandom.length;
        if (bArr != null) {
            length += bArr.length + 2;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(clientRandom, 0, bArr2, 0, clientRandom.length);
        int length2 = clientRandom.length + 0;
        System.arraycopy(serverRandom, 0, bArr2, length2, serverRandom.length);
        int length3 = length2 + serverRandom.length;
        if (bArr != null) {
            TlsUtils.writeUint16(bArr.length, bArr2, length3);
            int i2 = length3 + 2;
            System.arraycopy(bArr, 0, bArr2, i2, bArr.length);
            length3 = i2 + bArr.length;
        }
        if (length3 == length) {
            return TlsUtils.PRF(this, securityParameters.getMasterSecret(), str, bArr2, i);
        }
        throw new IllegalStateException("error in calculation of seed for export");
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public ProtocolVersion getClientVersion() {
        return this.clientVersion;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public RandomGenerator getNonceRandomGenerator() {
        return this.nonceRandom;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public TlsSession getResumableSession() {
        return this.session;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public SecurityParameters getSecurityParameters() {
        return this.securityParameters;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public ProtocolVersion getServerVersion() {
        return this.serverVersion;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public Object getUserObject() {
        return this.userObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setClientVersion(ProtocolVersion protocolVersion) {
        this.clientVersion = protocolVersion;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setResumableSession(TlsSession tlsSession) {
        this.session = tlsSession;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setServerVersion(ProtocolVersion protocolVersion) {
        this.serverVersion = protocolVersion;
    }

    @Override // org.bouncycastle.crypto.tls.TlsContext
    public void setUserObject(Object obj) {
        this.userObject = obj;
    }
}
