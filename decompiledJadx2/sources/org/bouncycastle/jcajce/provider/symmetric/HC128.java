package org.bouncycastle.jcajce.provider.symmetric;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.HC128Engine;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseStreamCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes9.dex */
public final class HC128 {

    /* loaded from: classes9.dex */
    public static class AlgParams extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "HC128 IV";
        }
    }

    /* loaded from: classes9.dex */
    public static class Base extends BaseStreamCipher {
        public Base() {
            super(new HC128Engine(), 16);
        }
    }

    /* loaded from: classes9.dex */
    public static class KeyGen extends BaseKeyGenerator {
        public KeyGen() {
            super("HC128", 128, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes9.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = HC128.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Cipher.HC128", PREFIX + "$Base");
            configurableProvider.addAlgorithm("KeyGenerator.HC128", PREFIX + "$KeyGen");
            configurableProvider.addAlgorithm("AlgorithmParameters.HC128", PREFIX + "$AlgParams");
        }
    }

    private HC128() {
    }
}
