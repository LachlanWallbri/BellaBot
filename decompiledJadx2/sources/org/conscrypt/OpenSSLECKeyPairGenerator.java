package org.conscrypt;

import com.aliyun.alink.dm.api.DMErrorCode;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public final class OpenSSLECKeyPairGenerator extends KeyPairGenerator {
    private static final String ALGORITHM = "EC";
    private static final int DEFAULT_KEY_SIZE = 256;
    private static final Map<Integer, String> SIZE_TO_CURVE_NAME = new HashMap();
    private OpenSSLECGroupContext group;

    static {
        SIZE_TO_CURVE_NAME.put(224, "secp224r1");
        SIZE_TO_CURVE_NAME.put(256, "prime256v1");
        SIZE_TO_CURVE_NAME.put(384, "secp384r1");
        SIZE_TO_CURVE_NAME.put(Integer.valueOf(DMErrorCode.ERROR_CMP_SEND_ERROR_CONNECT_NOT_CONNECTED), "secp521r1");
    }

    public OpenSSLECKeyPairGenerator() {
        super(ALGORITHM);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (this.group == null) {
            String str = SIZE_TO_CURVE_NAME.get(256);
            this.group = OpenSSLECGroupContext.getCurveByName(str);
            if (this.group == null) {
                throw new RuntimeException("Curve not recognized: " + str);
            }
        }
        OpenSSLKey openSSLKey = new OpenSSLKey(NativeCrypto.EC_KEY_generate_key(this.group.getNativeRef()));
        return new KeyPair(new OpenSSLECPublicKey(this.group, openSSLKey), new OpenSSLECPrivateKey(this.group, openSSLKey));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        String str = SIZE_TO_CURVE_NAME.get(Integer.valueOf(i));
        if (str == null) {
            throw new InvalidParameterException("unknown key size " + i);
        }
        OpenSSLECGroupContext curveByName = OpenSSLECGroupContext.getCurveByName(str);
        if (curveByName == null) {
            throw new InvalidParameterException("unknown curve " + str);
        }
        this.group = curveByName;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (algorithmParameterSpec instanceof ECParameterSpec) {
            this.group = OpenSSLECGroupContext.getInstance((ECParameterSpec) algorithmParameterSpec);
            return;
        }
        if (algorithmParameterSpec instanceof ECGenParameterSpec) {
            String name = ((ECGenParameterSpec) algorithmParameterSpec).getName();
            OpenSSLECGroupContext curveByName = OpenSSLECGroupContext.getCurveByName(name);
            if (curveByName == null) {
                throw new InvalidAlgorithmParameterException("unknown curve name: " + name);
            }
            this.group = curveByName;
            return;
        }
        throw new InvalidAlgorithmParameterException("parameter must be ECParameterSpec or ECGenParameterSpec");
    }

    public static void assertCurvesAreValid() {
        ArrayList arrayList = new ArrayList();
        for (String str : SIZE_TO_CURVE_NAME.values()) {
            if (OpenSSLECGroupContext.getCurveByName(str) == null) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() <= 0) {
            return;
        }
        throw new AssertionError("Invalid curve names: " + Arrays.toString(arrayList.toArray()));
    }
}
