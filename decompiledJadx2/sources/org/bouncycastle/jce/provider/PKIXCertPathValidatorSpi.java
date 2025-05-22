package org.bouncycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import org.bouncycastle.jcajce.PKIXExtendedParameters;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import org.bouncycastle.x509.ExtendedPKIXParameters;

/* loaded from: classes9.dex */
public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper;
    private final boolean isForCRLCheck;

    public PKIXCertPathValidatorSpi() {
        this(false);
    }

    public PKIXCertPathValidatorSpi(boolean z) {
        this.helper = new BCJcaJceHelper();
        this.isForCRLCheck = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static void checkCertificate(X509Certificate x509Certificate) throws AnnotatedException {
        if (x509Certificate instanceof BCX509Certificate) {
            RuntimeException runtimeException = null;
            try {
                if (((BCX509Certificate) x509Certificate).getTBSCertificateNative() != null) {
                    return;
                }
            } catch (RuntimeException e) {
                runtimeException = e;
            }
            throw new AnnotatedException("unable to process TBSCertificate", runtimeException);
        }
        try {
            TBSCertificate.getInstance(x509Certificate.getTBSCertificate());
        } catch (IllegalArgumentException e2) {
            throw new AnnotatedException(e2.getMessage());
        } catch (CertificateEncodingException e3) {
            throw new AnnotatedException("unable to process TBSCertificate", e3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v3, types: [java.security.cert.X509Certificate, org.bouncycastle.jce.provider.PKIXNameConstraintValidator] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v8, types: [org.bouncycastle.asn1.x509.AlgorithmIdentifier] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters pKIXExtendedParameters;
        List<? extends Certificate> list;
        X500Name ca;
        PublicKey cAPublicKey;
        HashSet hashSet;
        int i;
        int i2;
        int i3;
        List list2;
        X509Certificate x509Certificate;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        int version;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else {
            if (!(certPathParameters instanceof PKIXExtendedParameters)) {
                throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
            }
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        }
        if (pKIXExtendedParameters.getTrustAnchors() == null) {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
        List<? extends Certificate> certificates = certPath.getCertificates();
        int size = certificates.size();
        int i4 = -1;
        if (certificates.isEmpty()) {
            throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
        }
        Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
        try {
            TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
            if (findTrustAnchor == null) {
                list = certificates;
                try {
                    throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                } catch (AnnotatedException e) {
                    e = e;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath, list.size() - 1);
                }
            }
            checkCertificate(findTrustAnchor.getTrustedCert());
            PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
            int i5 = size + 1;
            ArrayList[] arrayListArr2 = new ArrayList[i5];
            for (int i6 = 0; i6 < arrayListArr2.length; i6++) {
                arrayListArr2[i6] = new ArrayList();
            }
            HashSet hashSet3 = new HashSet();
            hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
            PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
            arrayListArr2[0].add(pKIXPolicyNode);
            PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
            HashSet hashSet4 = new HashSet();
            int i7 = build.isExplicitPolicyRequired() ? 0 : i5;
            int i8 = build.isAnyPolicyInhibited() ? 0 : i5;
            if (build.isPolicyMappingInhibited()) {
                i5 = 0;
            }
            X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
            try {
                if (trustedCert != null) {
                    ca = PrincipalUtils.getSubjectPrincipal(trustedCert);
                    cAPublicKey = trustedCert.getPublicKey();
                } else {
                    ca = PrincipalUtils.getCA(findTrustAnchor);
                    cAPublicKey = findTrustAnchor.getCAPublicKey();
                }
                try {
                    i4 = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                    i4.getAlgorithm();
                    i4.getParameters();
                    if (build.getTargetConstraints() != null && !build.getTargetConstraints().match((Certificate) certificates.get(0))) {
                        throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                    }
                    List certPathCheckers = build.getCertPathCheckers();
                    Iterator it = certPathCheckers.iterator();
                    while (it.hasNext()) {
                        ((PKIXCertPathChecker) it.next()).init(false);
                    }
                    int i9 = i5;
                    int i10 = size;
                    int i11 = i8;
                    PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                    int size2 = certificates.size() - 1;
                    X509Certificate x509Certificate2 = null;
                    while (size2 >= 0) {
                        int i12 = size - size2;
                        Set set = initialPolicies;
                        X509Certificate x509Certificate3 = (X509Certificate) certificates.get(size2);
                        boolean z = size2 == certificates.size() + (-1);
                        try {
                            checkCertificate(x509Certificate3);
                            int i13 = i11;
                            List<? extends Certificate> list3 = certificates;
                            PKIXExtendedParameters pKIXExtendedParameters2 = build;
                            PKIXExtendedParameters pKIXExtendedParameters3 = build;
                            int i14 = i7;
                            List list4 = certPathCheckers;
                            int i15 = size2;
                            ?? r14 = pKIXNameConstraintValidator;
                            ArrayList[] arrayListArr3 = arrayListArr2;
                            TrustAnchor trustAnchor = findTrustAnchor;
                            RFC3280CertPathUtilities.processCertA(certPath, pKIXExtendedParameters2, size2, cAPublicKey, z, ca, trustedCert, this.helper);
                            RFC3280CertPathUtilities.processCertBC(certPath, i15, r14, this.isForCRLCheck);
                            PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, i15, RFC3280CertPathUtilities.processCertD(certPath, i15, hashSet4, pKIXPolicyNode2, arrayListArr3, i13, this.isForCRLCheck));
                            RFC3280CertPathUtilities.processCertF(certPath, i15, processCertE, i14);
                            if (pKIXExtendedParameters2 == size) {
                                i = i13;
                                i2 = i9;
                                i3 = i10;
                                list2 = list4;
                                x509Certificate = r14;
                            } else {
                                if (r14 != 0 && (version = r14.getVersion()) == 1) {
                                    if (version == 1) {
                                        x509Certificate = r14;
                                        if (x509Certificate.equals(trustAnchor.getTrustedCert())) {
                                            i = i13;
                                            i2 = i9;
                                            i3 = i10;
                                            list2 = list4;
                                        }
                                    }
                                    throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, i15);
                                }
                                x509Certificate = r14;
                                RFC3280CertPathUtilities.prepareNextCertA(certPath, i15);
                                int i16 = i9;
                                arrayListArr = arrayListArr3;
                                PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, i15, arrayListArr, processCertE, i16);
                                RFC3280CertPathUtilities.prepareNextCertG(certPath, i15, r14);
                                int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, i15, i14);
                                int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, i15, i16);
                                int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, i15, i13);
                                i14 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, i15, prepareNextCertH1);
                                int prepareNextCertI2 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, i15, prepareNextCertH2);
                                i = RFC3280CertPathUtilities.prepareNextCertJ(certPath, i15, prepareNextCertH3);
                                RFC3280CertPathUtilities.prepareNextCertK(certPath, i15);
                                i3 = RFC3280CertPathUtilities.prepareNextCertM(certPath, i15, RFC3280CertPathUtilities.prepareNextCertL(certPath, i15, i10));
                                RFC3280CertPathUtilities.prepareNextCertN(certPath, i15);
                                Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs != null) {
                                    hashSet2 = new HashSet(criticalExtensionOIDs);
                                    hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                    hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                    hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                    hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                    hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                    hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                    hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                    hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                    hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                } else {
                                    hashSet2 = new HashSet();
                                }
                                list2 = list4;
                                RFC3280CertPathUtilities.prepareNextCertO(certPath, i15, hashSet2, list2);
                                ca = PrincipalUtils.getSubjectPrincipal(x509Certificate);
                                try {
                                    cAPublicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i15, this.helper);
                                    AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(cAPublicKey);
                                    algorithmIdentifier.getAlgorithm();
                                    algorithmIdentifier.getParameters();
                                    pKIXPolicyNode2 = prepareCertB;
                                    i9 = prepareNextCertI2;
                                    trustedCert = x509Certificate;
                                    i11 = i;
                                    i10 = i3;
                                    size2 = i15 - 1;
                                    x509Certificate2 = x509Certificate;
                                    certPathCheckers = list2;
                                    certificates = list3;
                                    findTrustAnchor = trustAnchor;
                                    i7 = i14;
                                    arrayListArr2 = arrayListArr;
                                    pKIXNameConstraintValidator = r14;
                                    initialPolicies = set;
                                    build = pKIXExtendedParameters3;
                                } catch (CertPathValidatorException e2) {
                                    throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath, i15);
                                }
                            }
                            arrayListArr = arrayListArr3;
                            pKIXPolicyNode2 = processCertE;
                            i9 = i2;
                            i11 = i;
                            i10 = i3;
                            size2 = i15 - 1;
                            x509Certificate2 = x509Certificate;
                            certPathCheckers = list2;
                            certificates = list3;
                            findTrustAnchor = trustAnchor;
                            i7 = i14;
                            arrayListArr2 = arrayListArr;
                            pKIXNameConstraintValidator = r14;
                            initialPolicies = set;
                            build = pKIXExtendedParameters3;
                        } catch (AnnotatedException e3) {
                            throw new CertPathValidatorException(e3.getMessage(), e3.getUnderlyingException(), certPath, size2);
                        }
                    }
                    PKIXExtendedParameters pKIXExtendedParameters4 = build;
                    ArrayList[] arrayListArr4 = arrayListArr2;
                    TrustAnchor trustAnchor2 = findTrustAnchor;
                    Set set2 = initialPolicies;
                    List list5 = certPathCheckers;
                    int i17 = size2;
                    int i18 = i17 + 1;
                    int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i18, RFC3280CertPathUtilities.wrapupCertA(i7, x509Certificate2));
                    Set<String> criticalExtensionOIDs2 = x509Certificate2.getCriticalExtensionOIDs();
                    if (criticalExtensionOIDs2 != null) {
                        hashSet = new HashSet(criticalExtensionOIDs2);
                        hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                        hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                        hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                        hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                        hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                        hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                        hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                        hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                        hashSet.remove(Extension.extendedKeyUsage.getId());
                    } else {
                        hashSet = new HashSet();
                    }
                    RFC3280CertPathUtilities.wrapupCertF(certPath, i18, list5, hashSet);
                    X509Certificate x509Certificate4 = x509Certificate2;
                    PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, pKIXExtendedParameters4, set2, i18, arrayListArr4, pKIXPolicyNode2, hashSet4);
                    if (wrapupCertB > 0 || wrapupCertG != null) {
                        return new PKIXCertPathValidatorResult(trustAnchor2, wrapupCertG, x509Certificate4.getPublicKey());
                    }
                    throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, i17);
                } catch (CertPathValidatorException e4) {
                    throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e4, certPath, -1);
                }
            } catch (RuntimeException e5) {
                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e5, certPath, i4);
            }
        } catch (AnnotatedException e6) {
            e = e6;
            list = certificates;
        }
    }
}
