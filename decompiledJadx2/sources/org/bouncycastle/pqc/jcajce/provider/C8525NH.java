package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.jcajce.provider.newhope.NHKeyFactorySpi;

/* renamed from: org.bouncycastle.pqc.jcajce.provider.NH */
/* loaded from: classes9.dex */
public class C8525NH {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.newhope.";

    /* renamed from: org.bouncycastle.pqc.jcajce.provider.NH$Mappings */
    /* loaded from: classes9.dex */
    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.NH", "org.bouncycastle.pqc.jcajce.provider.newhope.NHKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.NH", "org.bouncycastle.pqc.jcajce.provider.newhope.NHKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyAgreement.NH", "org.bouncycastle.pqc.jcajce.provider.newhope.KeyAgreementSpi");
            registerOid(configurableProvider, PQCObjectIdentifiers.newHope, "NH", new NHKeyFactorySpi());
        }
    }
}
