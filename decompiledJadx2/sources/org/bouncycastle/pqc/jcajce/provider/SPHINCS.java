package org.bouncycastle.pqc.jcajce.provider;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyFactorySpi;

/* loaded from: classes9.dex */
public class SPHINCS {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.sphincs.";

    /* loaded from: classes9.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.Sphincs256KeyPairGeneratorSpi");
            addSignatureAlgorithm(configurableProvider, "SHA512", "SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.SignatureSpi$withSha512", PQCObjectIdentifiers.sphincs256_with_SHA512);
            addSignatureAlgorithm(configurableProvider, MessageDigestAlgorithms.SHA3_512, "SPHINCS256", "org.bouncycastle.pqc.jcajce.provider.sphincs.SignatureSpi$withSha3_512", PQCObjectIdentifiers.sphincs256_with_SHA3_512);
            registerOid(configurableProvider, PQCObjectIdentifiers.sphincs256, "SPHINCS256", new Sphincs256KeyFactorySpi());
            registerOidAlgorithmParameters(configurableProvider, PQCObjectIdentifiers.sphincs256, "SPHINCS256");
        }
    }
}
