package org.bouncycastle.jcajce.provider.symmetric;

import org.bouncycastle.crypto.CipherKeyGenerator;
import org.bouncycastle.crypto.engines.ThreefishEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseBlockCipher;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseKeyGenerator;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters;
import org.bouncycastle.jcajce.provider.util.AlgorithmProvider;

/* loaded from: classes9.dex */
public final class Threefish {

    /* loaded from: classes9.dex */
    public static class AlgParams_1024 extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "Threefish-1024 IV";
        }
    }

    /* loaded from: classes9.dex */
    public static class AlgParams_256 extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "Threefish-256 IV";
        }
    }

    /* loaded from: classes9.dex */
    public static class AlgParams_512 extends IvAlgorithmParameters {
        @Override // org.bouncycastle.jcajce.provider.symmetric.util.IvAlgorithmParameters, java.security.AlgorithmParametersSpi
        protected String engineToString() {
            return "Threefish-512 IV";
        }
    }

    /* loaded from: classes9.dex */
    public static class CMAC_1024 extends BaseMac {
        public CMAC_1024() {
            super(new CMac(new ThreefishEngine(1024)));
        }
    }

    /* loaded from: classes9.dex */
    public static class CMAC_256 extends BaseMac {
        public CMAC_256() {
            super(new CMac(new ThreefishEngine(256)));
        }
    }

    /* loaded from: classes9.dex */
    public static class CMAC_512 extends BaseMac {
        public CMAC_512() {
            super(new CMac(new ThreefishEngine(512)));
        }
    }

    /* loaded from: classes9.dex */
    public static class ECB_1024 extends BaseBlockCipher {
        public ECB_1024() {
            super(new ThreefishEngine(1024));
        }
    }

    /* loaded from: classes9.dex */
    public static class ECB_256 extends BaseBlockCipher {
        public ECB_256() {
            super(new ThreefishEngine(256));
        }
    }

    /* loaded from: classes9.dex */
    public static class ECB_512 extends BaseBlockCipher {
        public ECB_512() {
            super(new ThreefishEngine(512));
        }
    }

    /* loaded from: classes9.dex */
    public static class KeyGen_1024 extends BaseKeyGenerator {
        public KeyGen_1024() {
            super("Threefish-1024", 1024, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes9.dex */
    public static class KeyGen_256 extends BaseKeyGenerator {
        public KeyGen_256() {
            super("Threefish-256", 256, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes9.dex */
    public static class KeyGen_512 extends BaseKeyGenerator {
        public KeyGen_512() {
            super("Threefish-512", 512, new CipherKeyGenerator());
        }
    }

    /* loaded from: classes9.dex */
    public static class Mappings extends AlgorithmProvider {
        private static final String PREFIX = Threefish.class.getName();

        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("Mac.Threefish-256CMAC", PREFIX + "$CMAC_256");
            configurableProvider.addAlgorithm("Mac.Threefish-512CMAC", PREFIX + "$CMAC_512");
            configurableProvider.addAlgorithm("Mac.Threefish-1024CMAC", PREFIX + "$CMAC_1024");
            configurableProvider.addAlgorithm("Cipher.Threefish-256", PREFIX + "$ECB_256");
            configurableProvider.addAlgorithm("Cipher.Threefish-512", PREFIX + "$ECB_512");
            configurableProvider.addAlgorithm("Cipher.Threefish-1024", PREFIX + "$ECB_1024");
            configurableProvider.addAlgorithm("KeyGenerator.Threefish-256", PREFIX + "$KeyGen_256");
            configurableProvider.addAlgorithm("KeyGenerator.Threefish-512", PREFIX + "$KeyGen_512");
            configurableProvider.addAlgorithm("KeyGenerator.Threefish-1024", PREFIX + "$KeyGen_1024");
            configurableProvider.addAlgorithm("AlgorithmParameters.Threefish-256", PREFIX + "$AlgParams_256");
            configurableProvider.addAlgorithm("AlgorithmParameters.Threefish-512", PREFIX + "$AlgParams_512");
            configurableProvider.addAlgorithm("AlgorithmParameters.Threefish-1024", PREFIX + "$AlgParams_1024");
        }
    }

    private Threefish() {
    }
}
