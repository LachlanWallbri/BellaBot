package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.p081x9.ECNamedCurveTable;
import org.bouncycastle.asn1.p081x9.X962Parameters;
import org.bouncycastle.asn1.p081x9.X9ECParameters;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.field.FiniteField;
import org.bouncycastle.math.field.Polynomial;
import org.bouncycastle.math.field.PolynomialExtensionField;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class EC5Util {
    private static Map customCurves = new HashMap();

    static {
        Enumeration names = CustomNamedCurves.getNames();
        while (names.hasMoreElements()) {
            String str = (String) names.nextElement();
            X9ECParameters byName = ECNamedCurveTable.getByName(str);
            if (byName != null) {
                customCurves.put(byName.getCurve(), CustomNamedCurves.getByName(str).getCurve());
            }
        }
        ECCurve curve = CustomNamedCurves.getByName("Curve25519").getCurve();
        customCurves.put(new ECCurve.C8419Fp(curve.getField().getCharacteristic(), curve.getA().toBigInteger(), curve.getB().toBigInteger(), curve.getOrder(), curve.getCofactor()), curve);
    }

    public static EllipticCurve convertCurve(ECCurve eCCurve, byte[] bArr) {
        return new EllipticCurve(convertField(eCCurve.getField()), eCCurve.getA().toBigInteger(), eCCurve.getB().toBigInteger(), null);
    }

    public static ECCurve convertCurve(EllipticCurve ellipticCurve) {
        ECField field = ellipticCurve.getField();
        BigInteger a = ellipticCurve.getA();
        BigInteger b = ellipticCurve.getB();
        if (field instanceof ECFieldFp) {
            ECCurve.C8419Fp c8419Fp = new ECCurve.C8419Fp(((ECFieldFp) field).getP(), a, b);
            return customCurves.containsKey(c8419Fp) ? (ECCurve) customCurves.get(c8419Fp) : c8419Fp;
        }
        ECFieldF2m eCFieldF2m = (ECFieldF2m) field;
        int m = eCFieldF2m.getM();
        int[] convertMidTerms = ECUtil.convertMidTerms(eCFieldF2m.getMidTermsOfReductionPolynomial());
        return new ECCurve.F2m(m, convertMidTerms[0], convertMidTerms[1], convertMidTerms[2], a, b);
    }

    public static ECField convertField(FiniteField finiteField) {
        if (ECAlgorithms.isFpField(finiteField)) {
            return new ECFieldFp(finiteField.getCharacteristic());
        }
        Polynomial minimalPolynomial = ((PolynomialExtensionField) finiteField).getMinimalPolynomial();
        int[] exponentsPresent = minimalPolynomial.getExponentsPresent();
        return new ECFieldF2m(minimalPolynomial.getDegree(), Arrays.reverse(Arrays.copyOfRange(exponentsPresent, 1, exponentsPresent.length - 1)));
    }

    public static ECPoint convertPoint(org.bouncycastle.math.ec.ECPoint eCPoint) {
        org.bouncycastle.math.ec.ECPoint normalize = eCPoint.normalize();
        return new ECPoint(normalize.getAffineXCoord().toBigInteger(), normalize.getAffineYCoord().toBigInteger());
    }

    public static org.bouncycastle.math.ec.ECPoint convertPoint(ECParameterSpec eCParameterSpec, ECPoint eCPoint) {
        return convertPoint(convertCurve(eCParameterSpec.getCurve()), eCPoint);
    }

    public static org.bouncycastle.math.ec.ECPoint convertPoint(ECCurve eCCurve, ECPoint eCPoint) {
        return eCCurve.createPoint(eCPoint.getAffineX(), eCPoint.getAffineY());
    }

    public static ECParameterSpec convertSpec(EllipticCurve ellipticCurve, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        ECPoint convertPoint = convertPoint(eCParameterSpec.getG());
        return eCParameterSpec instanceof ECNamedCurveParameterSpec ? new ECNamedCurveSpec(((ECNamedCurveParameterSpec) eCParameterSpec).getName(), ellipticCurve, convertPoint, eCParameterSpec.getN(), eCParameterSpec.getH()) : new ECParameterSpec(ellipticCurve, convertPoint, eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
    }

    public static org.bouncycastle.jce.spec.ECParameterSpec convertSpec(ECParameterSpec eCParameterSpec) {
        ECCurve convertCurve = convertCurve(eCParameterSpec.getCurve());
        org.bouncycastle.math.ec.ECPoint convertPoint = convertPoint(convertCurve, eCParameterSpec.getGenerator());
        BigInteger order = eCParameterSpec.getOrder();
        BigInteger valueOf = BigInteger.valueOf(eCParameterSpec.getCofactor());
        byte[] seed = eCParameterSpec.getCurve().getSeed();
        return eCParameterSpec instanceof ECNamedCurveSpec ? new ECNamedCurveParameterSpec(((ECNamedCurveSpec) eCParameterSpec).getName(), convertCurve, convertPoint, order, valueOf, seed) : new org.bouncycastle.jce.spec.ECParameterSpec(convertCurve, convertPoint, order, valueOf, seed);
    }

    public static ECParameterSpec convertToSpec(X962Parameters x962Parameters, ECCurve eCCurve) {
        if (!x962Parameters.isNamedCurve()) {
            if (x962Parameters.isImplicitlyCA()) {
                return null;
            }
            X9ECParameters x9ECParameters = X9ECParameters.getInstance(x962Parameters.getParameters());
            EllipticCurve convertCurve = convertCurve(eCCurve, x9ECParameters.getSeed());
            return x9ECParameters.getH() != null ? new ECParameterSpec(convertCurve, convertPoint(x9ECParameters.getG()), x9ECParameters.getN(), x9ECParameters.getH().intValue()) : new ECParameterSpec(convertCurve, convertPoint(x9ECParameters.getG()), x9ECParameters.getN(), 1);
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) x962Parameters.getParameters();
        X9ECParameters namedCurveByOid = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier);
        if (namedCurveByOid == null) {
            Map additionalECParameters = BouncyCastleProvider.CONFIGURATION.getAdditionalECParameters();
            if (!additionalECParameters.isEmpty()) {
                namedCurveByOid = (X9ECParameters) additionalECParameters.get(aSN1ObjectIdentifier);
            }
        }
        return new ECNamedCurveSpec(ECUtil.getCurveName(aSN1ObjectIdentifier), convertCurve(eCCurve, namedCurveByOid.getSeed()), convertPoint(namedCurveByOid.getG()), namedCurveByOid.getN(), namedCurveByOid.getH());
    }

    public static ECParameterSpec convertToSpec(X9ECParameters x9ECParameters) {
        return new ECParameterSpec(convertCurve(x9ECParameters.getCurve(), null), convertPoint(x9ECParameters.getG()), x9ECParameters.getN(), x9ECParameters.getH().intValue());
    }

    public static ECParameterSpec convertToSpec(ECDomainParameters eCDomainParameters) {
        return new ECParameterSpec(convertCurve(eCDomainParameters.getCurve(), null), convertPoint(eCDomainParameters.getG()), eCDomainParameters.getN(), eCDomainParameters.getH().intValue());
    }

    public static ECCurve getCurve(ProviderConfiguration providerConfiguration, X962Parameters x962Parameters) {
        Set acceptableNamedCurves = providerConfiguration.getAcceptableNamedCurves();
        if (!x962Parameters.isNamedCurve()) {
            if (x962Parameters.isImplicitlyCA()) {
                return providerConfiguration.getEcImplicitlyCa().getCurve();
            }
            if (acceptableNamedCurves.isEmpty()) {
                return X9ECParameters.getInstance(x962Parameters.getParameters()).getCurve();
            }
            throw new IllegalStateException("encoded parameters not acceptable");
        }
        ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(x962Parameters.getParameters());
        if (!acceptableNamedCurves.isEmpty() && !acceptableNamedCurves.contains(aSN1ObjectIdentifier)) {
            throw new IllegalStateException("named curve not acceptable");
        }
        X9ECParameters namedCurveByOid = ECUtil.getNamedCurveByOid(aSN1ObjectIdentifier);
        if (namedCurveByOid == null) {
            namedCurveByOid = (X9ECParameters) providerConfiguration.getAdditionalECParameters().get(aSN1ObjectIdentifier);
        }
        return namedCurveByOid.getCurve();
    }

    public static ECDomainParameters getDomainParameters(ProviderConfiguration providerConfiguration, ECParameterSpec eCParameterSpec) {
        if (eCParameterSpec != null) {
            return ECUtil.getDomainParameters(providerConfiguration, convertSpec(eCParameterSpec));
        }
        org.bouncycastle.jce.spec.ECParameterSpec ecImplicitlyCa = providerConfiguration.getEcImplicitlyCa();
        return new ECDomainParameters(ecImplicitlyCa.getCurve(), ecImplicitlyCa.getG(), ecImplicitlyCa.getN(), ecImplicitlyCa.getH(), ecImplicitlyCa.getSeed());
    }
}
