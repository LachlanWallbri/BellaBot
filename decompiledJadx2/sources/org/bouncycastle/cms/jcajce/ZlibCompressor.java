package org.bouncycastle.cms.jcajce;

import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.OutputCompressor;

/* loaded from: classes9.dex */
public class ZlibCompressor implements OutputCompressor {
    private static final String ZLIB = "1.2.840.113549.1.9.16.3.8";

    @Override // org.bouncycastle.operator.OutputCompressor
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.8"));
    }

    @Override // org.bouncycastle.operator.OutputCompressor
    public OutputStream getOutputStream(OutputStream outputStream) {
        return new DeflaterOutputStream(outputStream);
    }
}
