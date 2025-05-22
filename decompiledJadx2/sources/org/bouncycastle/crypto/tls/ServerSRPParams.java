package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class ServerSRPParams {

    /* renamed from: B */
    protected BigInteger f9636B;

    /* renamed from: N */
    protected BigInteger f9637N;

    /* renamed from: g */
    protected BigInteger f9638g;

    /* renamed from: s */
    protected byte[] f9639s;

    public ServerSRPParams(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, BigInteger bigInteger3) {
        this.f9637N = bigInteger;
        this.f9638g = bigInteger2;
        this.f9639s = Arrays.clone(bArr);
        this.f9636B = bigInteger3;
    }

    public static ServerSRPParams parse(InputStream inputStream) throws IOException {
        return new ServerSRPParams(TlsSRPUtils.readSRPParameter(inputStream), TlsSRPUtils.readSRPParameter(inputStream), TlsUtils.readOpaque8(inputStream), TlsSRPUtils.readSRPParameter(inputStream));
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.f9637N, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f9638g, outputStream);
        TlsUtils.writeOpaque8(this.f9639s, outputStream);
        TlsSRPUtils.writeSRPParameter(this.f9636B, outputStream);
    }

    public BigInteger getB() {
        return this.f9636B;
    }

    public BigInteger getG() {
        return this.f9638g;
    }

    public BigInteger getN() {
        return this.f9637N;
    }

    public byte[] getS() {
        return this.f9639s;
    }
}
