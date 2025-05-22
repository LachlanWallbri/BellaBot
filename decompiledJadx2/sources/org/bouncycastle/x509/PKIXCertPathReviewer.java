package org.bouncycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.apache.http.HttpHost;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.GeneralSubtree;
import org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import org.bouncycastle.asn1.x509.NameConstraints;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.qualified.MonetaryValue;
import org.bouncycastle.asn1.x509.qualified.QCStatement;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.LocaleString;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;
import org.bouncycastle.jce.provider.AnnotatedException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidator;
import org.bouncycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.bouncycastle.jce.provider.PKIXPolicyNode;
import org.bouncycastle.jce.provider.RFC3280CertPathUtilities;
import org.bouncycastle.util.Integers;

/* loaded from: classes9.dex */
public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String RESOURCE_NAME = "org.bouncycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;

    /* renamed from: n */
    protected int f9968n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;
    private static final String QC_STATEMENT = Extension.qCStatements.getId();
    private static final String CRL_DIST_POINTS = Extension.cRLDistributionPoints.getId();
    private static final String AUTH_INFO_ACCESS = Extension.authorityInfoAccess.getId();

    public PKIXCertPathReviewer() {
    }

    public PKIXCertPathReviewer(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        init(certPath, pKIXParameters);
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        Iterator<PKIXCertPathChecker> it = certPathCheckers.iterator();
        while (it.hasNext()) {
            try {
                try {
                    it.next().init(false);
                } catch (CertPathValidatorException e) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
                }
            } catch (CertPathReviewerException e2) {
                addError(e2.getErrorMessage(), e2.getIndex());
                return;
            }
        }
        for (int size = this.certs.size() - 1; size >= 0; size--) {
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty()) {
                criticalExtensionOIDs.remove(KEY_USAGE);
                criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                if (criticalExtensionOIDs.contains(QC_STATEMENT) && processQcStatements(x509Certificate, size)) {
                    criticalExtensionOIDs.remove(QC_STATEMENT);
                }
                Iterator<PKIXCertPathChecker> it2 = certPathCheckers.iterator();
                while (it2.hasNext()) {
                    try {
                        it2.next().check(x509Certificate, criticalExtensionOIDs);
                    } catch (CertPathValidatorException e3) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e3.getMessage(), e3, e3.getClass().getName()}), e3.getCause(), this.certPath, size);
                    }
                }
                if (!criticalExtensionOIDs.isEmpty()) {
                    Iterator<String> it3 = criticalExtensionOIDs.iterator();
                    while (it3.hasNext()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it3.next())}), size);
                    }
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                int i = this.f9968n;
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
                                            GeneralName generalName = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i2));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(generalName);
                                                pKIXNameConstraintValidator.checkExcluded(generalName);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(generalName)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints nameConstraints = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = nameConstraints.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = nameConstraints.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i3 = 0; i3 != excludedSubtrees.length; i3++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i3]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.f9968n;
        int i2 = i;
        int i3 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            int i4 = this.f9968n;
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i2 <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i2--;
                i3++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (basicConstraints != null && (pathLenConstraint = basicConstraints.getPathLenConstraint()) != null && (intValue = pathLenConstraint.intValue()) < i2) {
                i2 = intValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i3)}));
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x011d, code lost:
    
        if (r7 >= r34.f9968n) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0123, code lost:
    
        if (isSelfIssued(r4) == false) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x020a A[Catch: CertPathReviewerException -> 0x05ea, TryCatch #1 {CertPathReviewerException -> 0x05ea, blocks: (B:16:0x006a, B:20:0x007c, B:22:0x0087, B:26:0x0097, B:27:0x00a2, B:29:0x00a8, B:32:0x00c9, B:33:0x00d1, B:35:0x00d7, B:41:0x00dc, B:42:0x00e8, B:48:0x00f4, B:51:0x00fb, B:52:0x0104, B:54:0x010a, B:57:0x0114, B:64:0x011b, B:66:0x011f, B:71:0x0206, B:73:0x020a, B:74:0x020f, B:76:0x0215, B:78:0x0221, B:85:0x0228, B:83:0x022b, B:89:0x0230, B:91:0x0236, B:92:0x023f, B:94:0x0245, B:103:0x0267, B:104:0x0273, B:105:0x0274, B:111:0x0278, B:113:0x0280, B:114:0x0284, B:116:0x028a, B:119:0x02aa, B:121:0x02b4, B:123:0x02b7, B:124:0x02c3, B:126:0x02c4, B:127:0x02d0, B:130:0x02d3, B:131:0x02e0, B:133:0x02e6, B:135:0x030a, B:137:0x0322, B:138:0x0319, B:141:0x0327, B:142:0x032d, B:144:0x0333, B:153:0x033b, B:148:0x0365, B:159:0x0343, B:160:0x034f, B:162:0x0351, B:163:0x0360, B:166:0x0373, B:175:0x0390, B:177:0x039a, B:178:0x039e, B:180:0x03a4, B:194:0x03b4, B:183:0x03c1, B:204:0x03ce, B:206:0x03d8, B:109:0x0419, B:212:0x03e3, B:213:0x03f1, B:215:0x03f2, B:216:0x0400, B:223:0x0402, B:224:0x0410, B:225:0x012e, B:226:0x0132, B:228:0x0138, B:231:0x014e, B:233:0x0158, B:234:0x015d, B:236:0x0163, B:237:0x0171, B:239:0x0177, B:265:0x0183, B:249:0x0190, B:250:0x0196, B:252:0x019c, B:260:0x01b5, B:241:0x0186, B:248:0x018a, B:267:0x01ee, B:271:0x01f9, B:272:0x0205, B:278:0x0425, B:279:0x0432, B:281:0x0433, B:286:0x0442, B:288:0x044c, B:289:0x0451, B:291:0x0457, B:294:0x0465, B:308:0x047a, B:315:0x05d0, B:316:0x05dc, B:318:0x0486, B:319:0x0492, B:320:0x0493, B:322:0x0499, B:324:0x04a1, B:326:0x04a7, B:327:0x04ad, B:329:0x04b0, B:330:0x04b3, B:332:0x04b9, B:334:0x04c9, B:335:0x04cd, B:337:0x04d3, B:339:0x04db, B:342:0x04de, B:344:0x04e1, B:345:0x04e5, B:347:0x04eb, B:350:0x04fb, B:352:0x0501, B:353:0x0506, B:355:0x050c, B:357:0x0518, B:359:0x051c, B:362:0x051f, B:364:0x0524, B:365:0x0530, B:367:0x0535, B:368:0x053b, B:370:0x053e, B:371:0x0541, B:373:0x0547, B:375:0x0557, B:376:0x055b, B:378:0x0561, B:381:0x0571, B:386:0x0575, B:389:0x0578, B:391:0x057b, B:392:0x0581, B:394:0x0587, B:396:0x0599, B:402:0x05a2, B:404:0x05a8, B:405:0x05ac, B:407:0x05b2, B:409:0x05be, B:411:0x05c2, B:414:0x05c5, B:419:0x05dd, B:420:0x05e9), top: B:15:0x006a, inners: #2, #3, #4, #5, #7, #8, #9, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0236 A[Catch: CertPathReviewerException -> 0x05ea, TryCatch #1 {CertPathReviewerException -> 0x05ea, blocks: (B:16:0x006a, B:20:0x007c, B:22:0x0087, B:26:0x0097, B:27:0x00a2, B:29:0x00a8, B:32:0x00c9, B:33:0x00d1, B:35:0x00d7, B:41:0x00dc, B:42:0x00e8, B:48:0x00f4, B:51:0x00fb, B:52:0x0104, B:54:0x010a, B:57:0x0114, B:64:0x011b, B:66:0x011f, B:71:0x0206, B:73:0x020a, B:74:0x020f, B:76:0x0215, B:78:0x0221, B:85:0x0228, B:83:0x022b, B:89:0x0230, B:91:0x0236, B:92:0x023f, B:94:0x0245, B:103:0x0267, B:104:0x0273, B:105:0x0274, B:111:0x0278, B:113:0x0280, B:114:0x0284, B:116:0x028a, B:119:0x02aa, B:121:0x02b4, B:123:0x02b7, B:124:0x02c3, B:126:0x02c4, B:127:0x02d0, B:130:0x02d3, B:131:0x02e0, B:133:0x02e6, B:135:0x030a, B:137:0x0322, B:138:0x0319, B:141:0x0327, B:142:0x032d, B:144:0x0333, B:153:0x033b, B:148:0x0365, B:159:0x0343, B:160:0x034f, B:162:0x0351, B:163:0x0360, B:166:0x0373, B:175:0x0390, B:177:0x039a, B:178:0x039e, B:180:0x03a4, B:194:0x03b4, B:183:0x03c1, B:204:0x03ce, B:206:0x03d8, B:109:0x0419, B:212:0x03e3, B:213:0x03f1, B:215:0x03f2, B:216:0x0400, B:223:0x0402, B:224:0x0410, B:225:0x012e, B:226:0x0132, B:228:0x0138, B:231:0x014e, B:233:0x0158, B:234:0x015d, B:236:0x0163, B:237:0x0171, B:239:0x0177, B:265:0x0183, B:249:0x0190, B:250:0x0196, B:252:0x019c, B:260:0x01b5, B:241:0x0186, B:248:0x018a, B:267:0x01ee, B:271:0x01f9, B:272:0x0205, B:278:0x0425, B:279:0x0432, B:281:0x0433, B:286:0x0442, B:288:0x044c, B:289:0x0451, B:291:0x0457, B:294:0x0465, B:308:0x047a, B:315:0x05d0, B:316:0x05dc, B:318:0x0486, B:319:0x0492, B:320:0x0493, B:322:0x0499, B:324:0x04a1, B:326:0x04a7, B:327:0x04ad, B:329:0x04b0, B:330:0x04b3, B:332:0x04b9, B:334:0x04c9, B:335:0x04cd, B:337:0x04d3, B:339:0x04db, B:342:0x04de, B:344:0x04e1, B:345:0x04e5, B:347:0x04eb, B:350:0x04fb, B:352:0x0501, B:353:0x0506, B:355:0x050c, B:357:0x0518, B:359:0x051c, B:362:0x051f, B:364:0x0524, B:365:0x0530, B:367:0x0535, B:368:0x053b, B:370:0x053e, B:371:0x0541, B:373:0x0547, B:375:0x0557, B:376:0x055b, B:378:0x0561, B:381:0x0571, B:386:0x0575, B:389:0x0578, B:391:0x057b, B:392:0x0581, B:394:0x0587, B:396:0x0599, B:402:0x05a2, B:404:0x05a8, B:405:0x05ac, B:407:0x05b2, B:409:0x05be, B:411:0x05c2, B:414:0x05c5, B:419:0x05dd, B:420:0x05e9), top: B:15:0x006a, inners: #2, #3, #4, #5, #7, #8, #9, #10 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkPolicy() {
        int i;
        int i2;
        PKIXPolicyNode pKIXPolicyNode;
        Set<String> set;
        String str;
        int i3;
        int i4;
        PKIXPolicyNode pKIXPolicyNode2;
        String str2;
        PKIXPolicyNode pKIXPolicyNode3;
        int i5;
        int i6;
        int i7;
        int intValueExact;
        String str3;
        HashSet hashSet;
        String str4;
        int i8;
        int i9;
        Set<String> criticalExtensionOIDs;
        int i10;
        String str5 = "CertPathReviewer.policyExtError";
        Set<String> initialPolicies = this.pkixParams.getInitialPolicies();
        ArrayList[] arrayListArr = new ArrayList[this.f9968n + 1];
        for (int i11 = 0; i11 < arrayListArr.length; i11++) {
            arrayListArr[i11] = new ArrayList();
        }
        HashSet hashSet2 = new HashSet();
        hashSet2.add(RFC3280CertPathUtilities.ANY_POLICY);
        PKIXPolicyNode pKIXPolicyNode4 = new PKIXPolicyNode(new ArrayList(), 0, hashSet2, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
        arrayListArr[0].add(pKIXPolicyNode4);
        int i12 = this.pkixParams.isExplicitPolicyRequired() ? 0 : this.f9968n + 1;
        int i13 = this.pkixParams.isAnyPolicyInhibited() ? 0 : this.f9968n + 1;
        int i14 = this.pkixParams.isPolicyMappingInhibited() ? 0 : this.f9968n + 1;
        try {
            int size = this.certs.size() - 1;
            int i15 = i13;
            int i16 = i14;
            HashSet hashSet3 = null;
            int i17 = i12;
            X509Certificate x509Certificate = null;
            while (size >= 0) {
                int i18 = this.f9968n - size;
                X509Certificate x509Certificate2 = (X509Certificate) this.certs.get(size);
                try {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate2, CERTIFICATE_POLICIES);
                    if (aSN1Sequence == null || pKIXPolicyNode4 == null) {
                        set = initialPolicies;
                        str = str5;
                        i3 = i15;
                        i4 = i16;
                        pKIXPolicyNode2 = pKIXPolicyNode4;
                    } else {
                        Enumeration objects = aSN1Sequence.getObjects();
                        set = initialPolicies;
                        HashSet hashSet4 = new HashSet();
                        while (objects.hasMoreElements()) {
                            PolicyInformation policyInformation = PolicyInformation.getInstance(objects.nextElement());
                            PKIXPolicyNode pKIXPolicyNode5 = pKIXPolicyNode4;
                            ASN1ObjectIdentifier policyIdentifier = policyInformation.getPolicyIdentifier();
                            String str6 = str5;
                            hashSet4.add(policyIdentifier.getId());
                            if (!RFC3280CertPathUtilities.ANY_POLICY.equals(policyIdentifier.getId())) {
                                try {
                                    Set qualifierSet = getQualifierSet(policyInformation.getPolicyQualifiers());
                                    if (!processCertD1i(i18, arrayListArr, policyIdentifier, qualifierSet)) {
                                        processCertD1ii(i18, arrayListArr, policyIdentifier, qualifierSet);
                                    }
                                } catch (CertPathValidatorException e) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e, this.certPath, size);
                                }
                            }
                            pKIXPolicyNode4 = pKIXPolicyNode5;
                            str5 = str6;
                        }
                        str = str5;
                        pKIXPolicyNode2 = pKIXPolicyNode4;
                        if (hashSet3 != null && !hashSet3.contains(RFC3280CertPathUtilities.ANY_POLICY)) {
                            HashSet hashSet5 = new HashSet();
                            for (Object obj : hashSet3) {
                                if (hashSet4.contains(obj)) {
                                    hashSet5.add(obj);
                                }
                            }
                            hashSet4 = hashSet5;
                        }
                        Enumeration objects2 = aSN1Sequence.getObjects();
                        while (objects2.hasMoreElements()) {
                            PolicyInformation policyInformation2 = PolicyInformation.getInstance(objects2.nextElement());
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(policyInformation2.getPolicyIdentifier().getId())) {
                                try {
                                    Set qualifierSet2 = getQualifierSet(policyInformation2.getPolicyQualifiers());
                                    ArrayList arrayList = arrayListArr[i18 - 1];
                                    hashSet = hashSet4;
                                    for (int i19 = 0; i19 < arrayList.size(); i19++) {
                                        PKIXPolicyNode pKIXPolicyNode6 = (PKIXPolicyNode) arrayList.get(i19);
                                        for (Object obj2 : pKIXPolicyNode6.getExpectedPolicies()) {
                                            ArrayList arrayList2 = arrayList;
                                            int i20 = i15;
                                            if (obj2 instanceof String) {
                                                str4 = (String) obj2;
                                            } else if (obj2 instanceof ASN1ObjectIdentifier) {
                                                str4 = ((ASN1ObjectIdentifier) obj2).getId();
                                            } else {
                                                arrayList = arrayList2;
                                                i15 = i20;
                                            }
                                            Iterator children = pKIXPolicyNode6.getChildren();
                                            boolean z = false;
                                            while (children.hasNext()) {
                                                Iterator it = children;
                                                if (str4.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                                    z = true;
                                                }
                                                children = it;
                                            }
                                            if (z) {
                                                i8 = i16;
                                            } else {
                                                HashSet hashSet6 = new HashSet();
                                                hashSet6.add(str4);
                                                i8 = i16;
                                                PKIXPolicyNode pKIXPolicyNode7 = new PKIXPolicyNode(new ArrayList(), i18, hashSet6, pKIXPolicyNode6, qualifierSet2, str4, false);
                                                pKIXPolicyNode6.addChild(pKIXPolicyNode7);
                                                arrayListArr[i18].add(pKIXPolicyNode7);
                                            }
                                            arrayList = arrayList2;
                                            i15 = i20;
                                            i16 = i8;
                                        }
                                    }
                                    i3 = i15;
                                    i4 = i16;
                                    for (i9 = i18 - 1; i9 >= 0; i9--) {
                                        ArrayList arrayList3 = arrayListArr[i9];
                                        PKIXPolicyNode pKIXPolicyNode8 = pKIXPolicyNode2;
                                        while (i10 < arrayList3.size()) {
                                            PKIXPolicyNode pKIXPolicyNode9 = (PKIXPolicyNode) arrayList3.get(i10);
                                            i10 = (pKIXPolicyNode9.hasChildren() || (pKIXPolicyNode8 = removePolicyNode(pKIXPolicyNode8, arrayListArr, pKIXPolicyNode9)) != null) ? i10 + 1 : 0;
                                        }
                                        pKIXPolicyNode2 = pKIXPolicyNode8;
                                    }
                                    criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                    if (criticalExtensionOIDs != null) {
                                        boolean contains = criticalExtensionOIDs.contains(CERTIFICATE_POLICIES);
                                        ArrayList arrayList4 = arrayListArr[i18];
                                        for (int i21 = 0; i21 < arrayList4.size(); i21++) {
                                            ((PKIXPolicyNode) arrayList4.get(i21)).setCritical(contains);
                                        }
                                    }
                                    hashSet3 = hashSet;
                                } catch (CertPathValidatorException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e2, this.certPath, size);
                                }
                            }
                        }
                        hashSet = hashSet4;
                        i3 = i15;
                        i4 = i16;
                        while (i9 >= 0) {
                        }
                        criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs != null) {
                        }
                        hashSet3 = hashSet;
                    }
                    if (aSN1Sequence == null) {
                        pKIXPolicyNode2 = null;
                    }
                    if (i17 <= 0 && pKIXPolicyNode2 == null) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidPolicyTree"));
                    }
                    if (i18 != this.f9968n) {
                        try {
                            ASN1Primitive extensionValue = getExtensionValue(x509Certificate2, POLICY_MAPPINGS);
                            if (extensionValue != null) {
                                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) extensionValue;
                                for (int i22 = 0; i22 < aSN1Sequence2.size(); i22++) {
                                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence2.getObjectAt(i22);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(0);
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(1);
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier.getId())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                    }
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier2.getId())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                    }
                                }
                            }
                            if (extensionValue != null) {
                                ASN1Sequence aSN1Sequence4 = (ASN1Sequence) extensionValue;
                                HashMap hashMap = new HashMap();
                                HashSet<String> hashSet7 = new HashSet();
                                int i23 = 0;
                                while (i23 < aSN1Sequence4.size()) {
                                    ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.getObjectAt(i23);
                                    String id = ((ASN1ObjectIdentifier) aSN1Sequence5.getObjectAt(0)).getId();
                                    ASN1Sequence aSN1Sequence6 = aSN1Sequence4;
                                    String id2 = ((ASN1ObjectIdentifier) aSN1Sequence5.getObjectAt(1)).getId();
                                    if (hashMap.containsKey(id)) {
                                        ((Set) hashMap.get(id)).add(id2);
                                    } else {
                                        HashSet hashSet8 = new HashSet();
                                        hashSet8.add(id2);
                                        hashMap.put(id, hashSet8);
                                        hashSet7.add(id);
                                    }
                                    i23++;
                                    aSN1Sequence4 = aSN1Sequence6;
                                }
                                pKIXPolicyNode3 = pKIXPolicyNode2;
                                for (String str7 : hashSet7) {
                                    if (i4 > 0) {
                                        try {
                                            prepareNextCertB1(i18, arrayListArr, str7, hashMap, x509Certificate2);
                                            str3 = str;
                                        } catch (CertPathValidatorException e3) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e3, this.certPath, size);
                                        } catch (AnnotatedException e4) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str), e4, this.certPath, size);
                                        }
                                    } else {
                                        str3 = str;
                                        if (i4 <= 0) {
                                            pKIXPolicyNode3 = prepareNextCertB2(i18, arrayListArr, str7, pKIXPolicyNode3);
                                        }
                                    }
                                    str = str3;
                                }
                                str2 = str;
                            } else {
                                str2 = str;
                                pKIXPolicyNode3 = pKIXPolicyNode2;
                            }
                            if (isSelfIssued(x509Certificate2)) {
                                i5 = i3;
                                i6 = i4;
                            } else {
                                if (i17 != 0) {
                                    i17--;
                                }
                                i6 = i4 != 0 ? i4 - 1 : i4;
                                i5 = i3 != 0 ? i3 - 1 : i3;
                            }
                            try {
                                ASN1Sequence aSN1Sequence7 = (ASN1Sequence) getExtensionValue(x509Certificate2, POLICY_CONSTRAINTS);
                                if (aSN1Sequence7 != null) {
                                    Enumeration objects3 = aSN1Sequence7.getObjects();
                                    while (objects3.hasMoreElements()) {
                                        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects3.nextElement();
                                        int tagNo = aSN1TaggedObject.getTagNo();
                                        if (tagNo == 0) {
                                            int intValueExact2 = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact();
                                            if (intValueExact2 < i17) {
                                                i17 = intValueExact2;
                                            }
                                        } else if (tagNo == 1 && (intValueExact = ASN1Integer.getInstance(aSN1TaggedObject, false).intValueExact()) < i6) {
                                            i6 = intValueExact;
                                        }
                                    }
                                }
                                try {
                                    ASN1Integer aSN1Integer = (ASN1Integer) getExtensionValue(x509Certificate2, INHIBIT_ANY_POLICY);
                                    if (aSN1Integer == null || (i7 = aSN1Integer.intValueExact()) >= i5) {
                                        i7 = i5;
                                    }
                                    i15 = i7;
                                    pKIXPolicyNode4 = pKIXPolicyNode3;
                                } catch (AnnotatedException unused) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyInhibitExtError"), this.certPath, size);
                                }
                            } catch (AnnotatedException unused2) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
                            }
                        } catch (AnnotatedException e5) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyMapExtError"), e5, this.certPath, size);
                        }
                    } else {
                        str2 = str;
                        pKIXPolicyNode4 = pKIXPolicyNode2;
                        i15 = i3;
                        i6 = i4;
                    }
                    size--;
                    x509Certificate = x509Certificate2;
                    str5 = str2;
                    i16 = i6;
                    initialPolicies = set;
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, str5), e6, this.certPath, size);
                }
            }
            Set<String> set2 = initialPolicies;
            PKIXPolicyNode pKIXPolicyNode10 = pKIXPolicyNode4;
            if (!isSelfIssued(x509Certificate) && i17 > 0) {
                i17--;
            }
            try {
                ASN1Sequence aSN1Sequence8 = (ASN1Sequence) getExtensionValue(x509Certificate, POLICY_CONSTRAINTS);
                if (aSN1Sequence8 != null) {
                    Enumeration objects4 = aSN1Sequence8.getObjects();
                    i2 = i17;
                    while (objects4.hasMoreElements()) {
                        ASN1TaggedObject aSN1TaggedObject2 = (ASN1TaggedObject) objects4.nextElement();
                        if (aSN1TaggedObject2.getTagNo() == 0 && ASN1Integer.getInstance(aSN1TaggedObject2, false).intValueExact() == 0) {
                            i2 = 0;
                        }
                    }
                    i = 0;
                } else {
                    i = 0;
                    i2 = i17;
                }
                if (pKIXPolicyNode10 == null) {
                    if (this.pkixParams.isExplicitPolicyRequired()) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                    }
                    pKIXPolicyNode = null;
                } else if (isAnyPolicy(set2)) {
                    if (this.pkixParams.isExplicitPolicyRequired()) {
                        if (hashSet3.isEmpty()) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                        }
                        HashSet hashSet9 = new HashSet();
                        for (int i24 = i; i24 < arrayListArr.length; i24++) {
                            ArrayList arrayList5 = arrayListArr[i24];
                            for (int i25 = i; i25 < arrayList5.size(); i25++) {
                                PKIXPolicyNode pKIXPolicyNode11 = (PKIXPolicyNode) arrayList5.get(i25);
                                if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode11.getValidPolicy())) {
                                    Iterator children2 = pKIXPolicyNode11.getChildren();
                                    while (children2.hasNext()) {
                                        hashSet9.add(children2.next());
                                    }
                                }
                            }
                        }
                        Iterator it2 = hashSet9.iterator();
                        while (it2.hasNext()) {
                            hashSet3.contains(((PKIXPolicyNode) it2.next()).getValidPolicy());
                        }
                        if (pKIXPolicyNode10 != null) {
                            int i26 = this.f9968n - 1;
                            while (i26 >= 0) {
                                ArrayList arrayList6 = arrayListArr[i26];
                                PKIXPolicyNode pKIXPolicyNode12 = pKIXPolicyNode10;
                                for (int i27 = i; i27 < arrayList6.size(); i27++) {
                                    PKIXPolicyNode pKIXPolicyNode13 = (PKIXPolicyNode) arrayList6.get(i27);
                                    if (!pKIXPolicyNode13.hasChildren()) {
                                        pKIXPolicyNode12 = removePolicyNode(pKIXPolicyNode12, arrayListArr, pKIXPolicyNode13);
                                    }
                                }
                                i26--;
                                pKIXPolicyNode10 = pKIXPolicyNode12;
                            }
                        }
                    }
                    pKIXPolicyNode = pKIXPolicyNode10;
                } else {
                    HashSet<PKIXPolicyNode> hashSet10 = new HashSet();
                    for (int i28 = i; i28 < arrayListArr.length; i28++) {
                        ArrayList arrayList7 = arrayListArr[i28];
                        for (int i29 = i; i29 < arrayList7.size(); i29++) {
                            PKIXPolicyNode pKIXPolicyNode14 = (PKIXPolicyNode) arrayList7.get(i29);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode14.getValidPolicy())) {
                                Iterator children3 = pKIXPolicyNode14.getChildren();
                                while (children3.hasNext()) {
                                    PKIXPolicyNode pKIXPolicyNode15 = (PKIXPolicyNode) children3.next();
                                    if (!RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode15.getValidPolicy())) {
                                        hashSet10.add(pKIXPolicyNode15);
                                    }
                                }
                            }
                        }
                    }
                    PKIXPolicyNode pKIXPolicyNode16 = pKIXPolicyNode10;
                    for (PKIXPolicyNode pKIXPolicyNode17 : hashSet10) {
                        Set<String> set3 = set2;
                        if (!set3.contains(pKIXPolicyNode17.getValidPolicy())) {
                            pKIXPolicyNode16 = removePolicyNode(pKIXPolicyNode16, arrayListArr, pKIXPolicyNode17);
                        }
                        set2 = set3;
                    }
                    if (pKIXPolicyNode16 != null) {
                        int i30 = this.f9968n - 1;
                        while (i30 >= 0) {
                            ArrayList arrayList8 = arrayListArr[i30];
                            PKIXPolicyNode pKIXPolicyNode18 = pKIXPolicyNode16;
                            for (int i31 = i; i31 < arrayList8.size(); i31++) {
                                PKIXPolicyNode pKIXPolicyNode19 = (PKIXPolicyNode) arrayList8.get(i31);
                                if (!pKIXPolicyNode19.hasChildren()) {
                                    pKIXPolicyNode18 = removePolicyNode(pKIXPolicyNode18, arrayListArr, pKIXPolicyNode19);
                                }
                            }
                            i30--;
                            pKIXPolicyNode16 = pKIXPolicyNode18;
                        }
                    }
                    pKIXPolicyNode = pKIXPolicyNode16;
                }
                if (i2 <= 0 && pKIXPolicyNode == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicy"));
                }
            } catch (AnnotatedException unused3) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(2:86|87)|(6:(15:89|90|91|(11:93|94|(2:97|95)|98|99|(2:102|100)|103|104|105|106|107)|114|94|(1:95)|98|99|(1:100)|103|104|105|106|107)|103|104|105|106|107)|117|90|91|(0)|114|94|(1:95)|98|99|(1:100)) */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02b2, code lost:
    
        addError(new org.bouncycastle.i18n.ErrorBundle(org.bouncycastle.x509.PKIXCertPathReviewer.RESOURCE_NAME, "CertPathReviewer.crlAuthInfoAccError"), r8);
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02f8 A[LOOP:2: B:100:0x02f2->B:102:0x02f8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x028b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02ad A[Catch: AnnotatedException -> 0x02b2, TRY_LEAVE, TryCatch #16 {AnnotatedException -> 0x02b2, blocks: (B:91:0x02a5, B:93:0x02ad), top: B:90:0x02a5 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02cf A[LOOP:1: B:95:0x02c9->B:97:0x02cf, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void checkSignatures() {
        TrustAnchor trustAnchor;
        TrustAnchor trustAnchor2;
        X500Principal x500Principal;
        X509Certificate x509Certificate;
        PublicKey publicKey;
        int size;
        ErrorBundle errorBundle;
        ErrorBundle errorBundle2;
        CRLDistPoint cRLDistPoint;
        AuthorityInformationAccess authorityInformationAccess;
        Iterator it;
        X509Certificate x509Certificate2;
        Iterator it2;
        int i;
        X500Principal x500Principal2;
        PublicKey publicKey2;
        int i2;
        TrustAnchor trustAnchor3;
        X509Certificate x509Certificate3;
        ASN1Primitive extensionValue;
        ASN1Primitive extensionValue2;
        char c;
        char c2;
        AuthorityKeyIdentifier authorityKeyIdentifier;
        GeneralNames authorityCertIssuer;
        boolean[] keyUsage;
        char c3 = 2;
        char c4 = 1;
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathValidDate", new Object[]{new TrustedInput(this.validDate), new TrustedInput(new Date())}));
        try {
            X509Certificate x509Certificate4 = (X509Certificate) this.certs.get(this.certs.size() - 1);
            Collection trustAnchors = getTrustAnchors(x509Certificate4, this.pkixParams.getTrustAnchors());
            if (trustAnchors.size() > 1) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.conflictingTrustAnchors", new Object[]{Integers.valueOf(trustAnchors.size()), new UntrustedInput(x509Certificate4.getIssuerX500Principal())}));
            } else if (trustAnchors.isEmpty()) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noTrustAnchorFound", new Object[]{new UntrustedInput(x509Certificate4.getIssuerX500Principal()), Integers.valueOf(this.pkixParams.getTrustAnchors().size())}));
            } else {
                trustAnchor = (TrustAnchor) trustAnchors.iterator().next();
                try {
                    try {
                        CertPathValidatorUtilities.verifyX509Certificate(x509Certificate4, trustAnchor.getTrustedCert() != null ? trustAnchor.getTrustedCert().getPublicKey() : trustAnchor.getCAPublicKey(), this.pkixParams.getSigProvider());
                    } catch (SignatureException unused) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustButInvalidCert"));
                    } catch (Exception unused2) {
                    }
                } catch (CertPathReviewerException e) {
                    e = e;
                    addError(e.getErrorMessage());
                    trustAnchor2 = trustAnchor;
                    if (trustAnchor2 != null) {
                    }
                    if (trustAnchor2 != null) {
                    }
                    X509Certificate x509Certificate5 = x509Certificate;
                    X500Principal x500Principal3 = x500Principal;
                    PublicKey publicKey3 = publicKey;
                    size = this.certs.size() - 1;
                    while (size >= 0) {
                    }
                    this.trustAnchor = trustAnchor2;
                    this.subjectPublicKey = publicKey3;
                } catch (Throwable th) {
                    th = th;
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknown", new Object[]{new UntrustedInput(th.getMessage()), new UntrustedInput(th)}));
                    trustAnchor2 = trustAnchor;
                    if (trustAnchor2 != null) {
                    }
                    if (trustAnchor2 != null) {
                    }
                    X509Certificate x509Certificate52 = x509Certificate;
                    X500Principal x500Principal32 = x500Principal;
                    PublicKey publicKey32 = publicKey;
                    size = this.certs.size() - 1;
                    while (size >= 0) {
                    }
                    this.trustAnchor = trustAnchor2;
                    this.subjectPublicKey = publicKey32;
                }
            }
            trustAnchor = null;
        } catch (CertPathReviewerException e2) {
            e = e2;
            trustAnchor = null;
        } catch (Throwable th2) {
            th = th2;
            trustAnchor = null;
        }
        trustAnchor2 = trustAnchor;
        if (trustAnchor2 != null) {
            X509Certificate trustedCert = trustAnchor2.getTrustedCert();
            try {
                x500Principal = trustedCert != null ? getSubjectPrincipal(trustedCert) : new X500Principal(trustAnchor2.getCAName());
            } catch (IllegalArgumentException unused3) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustDNInvalid", new Object[]{new UntrustedInput(trustAnchor2.getCAName())}));
                x500Principal = null;
            }
            if (trustedCert != null && (keyUsage = trustedCert.getKeyUsage()) != null && !keyUsage[5]) {
                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustKeyUsage"));
            }
        } else {
            x500Principal = null;
        }
        if (trustAnchor2 != null) {
            x509Certificate = trustAnchor2.getTrustedCert();
            publicKey = x509Certificate != null ? x509Certificate.getPublicKey() : trustAnchor2.getCAPublicKey();
            try {
                AlgorithmIdentifier algorithmIdentifier = getAlgorithmIdentifier(publicKey);
                algorithmIdentifier.getAlgorithm();
                algorithmIdentifier.getParameters();
            } catch (CertPathValidatorException unused4) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustPubKeyError"));
            }
        } else {
            x509Certificate = null;
            publicKey = null;
        }
        X509Certificate x509Certificate522 = x509Certificate;
        X500Principal x500Principal322 = x500Principal;
        PublicKey publicKey322 = publicKey;
        size = this.certs.size() - 1;
        while (size >= 0) {
            int i3 = this.f9968n - size;
            X509Certificate x509Certificate6 = (X509Certificate) this.certs.get(size);
            if (publicKey322 != null) {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(x509Certificate6, publicKey322, this.pkixParams.getSigProvider());
                } catch (GeneralSecurityException e3) {
                    Object[] objArr = new Object[3];
                    objArr[0] = e3.getMessage();
                    objArr[c4] = e3;
                    objArr[c3] = e3.getClass().getName();
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr);
                    addError(errorBundle, size);
                    x509Certificate6.checkValidity(this.validDate);
                    if (this.pkixParams.isRevocationEnabled()) {
                    }
                    if (x500Principal2 != null) {
                    }
                    c = 2;
                    if (i == this.f9968n) {
                    }
                    x500Principal322 = x509Certificate3.getSubjectX500Principal();
                    publicKey322 = getNextWorkingKey(this.certs, i2);
                    try {
                        AlgorithmIdentifier algorithmIdentifier2 = getAlgorithmIdentifier(publicKey322);
                        algorithmIdentifier2.getAlgorithm();
                        algorithmIdentifier2.getParameters();
                    } catch (CertPathValidatorException unused5) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pubKeyError"), i2);
                        size = i2 - 1;
                        c3 = c;
                        c4 = c2;
                        x509Certificate522 = x509Certificate3;
                        trustAnchor2 = trustAnchor3;
                    }
                    size = i2 - 1;
                    c3 = c;
                    c4 = c2;
                    x509Certificate522 = x509Certificate3;
                    trustAnchor2 = trustAnchor3;
                }
            } else if (isSelfIssued(x509Certificate6)) {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(x509Certificate6, x509Certificate6.getPublicKey(), this.pkixParams.getSigProvider());
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.rootKeyIsValidButNotATrustAnchor"), size);
                } catch (GeneralSecurityException e4) {
                    Object[] objArr2 = new Object[3];
                    objArr2[0] = e4.getMessage();
                    objArr2[c4] = e4;
                    objArr2[c3] = e4.getClass().getName();
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.signatureNotVerified", objArr2);
                    addError(errorBundle, size);
                    x509Certificate6.checkValidity(this.validDate);
                    if (this.pkixParams.isRevocationEnabled()) {
                    }
                    if (x500Principal2 != null) {
                    }
                    c = 2;
                    if (i == this.f9968n) {
                    }
                    x500Principal322 = x509Certificate3.getSubjectX500Principal();
                    publicKey322 = getNextWorkingKey(this.certs, i2);
                    AlgorithmIdentifier algorithmIdentifier22 = getAlgorithmIdentifier(publicKey322);
                    algorithmIdentifier22.getAlgorithm();
                    algorithmIdentifier22.getParameters();
                    size = i2 - 1;
                    c3 = c;
                    c4 = c2;
                    x509Certificate522 = x509Certificate3;
                    trustAnchor2 = trustAnchor3;
                }
            } else {
                ErrorBundle errorBundle3 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.NoIssuerPublicKey");
                byte[] extensionValue3 = x509Certificate6.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (extensionValue3 != null && (authorityCertIssuer = (authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(DEROctetString.getInstance(extensionValue3).getOctets())).getAuthorityCertIssuer()) != null) {
                    GeneralName generalName = authorityCertIssuer.getNames()[0];
                    BigInteger authorityCertSerialNumber = authorityKeyIdentifier.getAuthorityCertSerialNumber();
                    if (authorityCertSerialNumber != null) {
                        errorBundle3.setExtraArguments(new Object[]{new LocaleString(RESOURCE_NAME, "missingIssuer"), " \"", generalName, "\" ", new LocaleString(RESOURCE_NAME, "missingSerial"), " ", authorityCertSerialNumber});
                    }
                }
                addError(errorBundle3, size);
            }
            try {
                x509Certificate6.checkValidity(this.validDate);
            } catch (CertificateExpiredException unused6) {
                errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateExpired", new Object[]{new TrustedInput(x509Certificate6.getNotAfter())});
                addError(errorBundle2, size);
                if (this.pkixParams.isRevocationEnabled()) {
                }
                if (x500Principal2 != null) {
                }
                c = 2;
                if (i == this.f9968n) {
                }
                x500Principal322 = x509Certificate3.getSubjectX500Principal();
                publicKey322 = getNextWorkingKey(this.certs, i2);
                AlgorithmIdentifier algorithmIdentifier222 = getAlgorithmIdentifier(publicKey322);
                algorithmIdentifier222.getAlgorithm();
                algorithmIdentifier222.getParameters();
                size = i2 - 1;
                c3 = c;
                c4 = c2;
                x509Certificate522 = x509Certificate3;
                trustAnchor2 = trustAnchor3;
            } catch (CertificateNotYetValidException unused7) {
                errorBundle2 = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certificateNotYetValid", new Object[]{new TrustedInput(x509Certificate6.getNotBefore())});
                addError(errorBundle2, size);
                if (this.pkixParams.isRevocationEnabled()) {
                }
                if (x500Principal2 != null) {
                }
                c = 2;
                if (i == this.f9968n) {
                }
                x500Principal322 = x509Certificate3.getSubjectX500Principal();
                publicKey322 = getNextWorkingKey(this.certs, i2);
                AlgorithmIdentifier algorithmIdentifier2222 = getAlgorithmIdentifier(publicKey322);
                algorithmIdentifier2222.getAlgorithm();
                algorithmIdentifier2222.getParameters();
                size = i2 - 1;
                c3 = c;
                c4 = c2;
                x509Certificate522 = x509Certificate3;
                trustAnchor2 = trustAnchor3;
            }
            if (this.pkixParams.isRevocationEnabled()) {
                try {
                    extensionValue2 = getExtensionValue(x509Certificate6, CRL_DIST_POINTS);
                } catch (AnnotatedException unused8) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPtExtError"), size);
                }
                try {
                    try {
                        if (extensionValue2 != null) {
                            cRLDistPoint = CRLDistPoint.getInstance(extensionValue2);
                            extensionValue = getExtensionValue(x509Certificate6, AUTH_INFO_ACCESS);
                            if (extensionValue != null) {
                                authorityInformationAccess = AuthorityInformationAccess.getInstance(extensionValue);
                                Vector cRLDistUrls = getCRLDistUrls(cRLDistPoint);
                                Vector oCSPUrls = getOCSPUrls(authorityInformationAccess);
                                it = cRLDistUrls.iterator();
                                while (it.hasNext()) {
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlDistPoint", new Object[]{new UntrustedUrlInput(it.next())}), size);
                                    x509Certificate6 = x509Certificate6;
                                }
                                x509Certificate2 = x509Certificate6;
                                it2 = oCSPUrls.iterator();
                                while (it2.hasNext()) {
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ocspLocation", new Object[]{new UntrustedUrlInput(it2.next())}), size);
                                }
                                x509Certificate3 = x509Certificate2;
                                i = i3;
                                x500Principal2 = x500Principal322;
                                publicKey2 = publicKey322;
                                i2 = size;
                                trustAnchor3 = trustAnchor2;
                                checkRevocation(this.pkixParams, x509Certificate3, this.validDate, x509Certificate522, publicKey322, cRLDistUrls, oCSPUrls, i2);
                            }
                            authorityInformationAccess = null;
                            Vector cRLDistUrls2 = getCRLDistUrls(cRLDistPoint);
                            Vector oCSPUrls2 = getOCSPUrls(authorityInformationAccess);
                            it = cRLDistUrls2.iterator();
                            while (it.hasNext()) {
                            }
                            x509Certificate2 = x509Certificate6;
                            it2 = oCSPUrls2.iterator();
                            while (it2.hasNext()) {
                            }
                            x509Certificate3 = x509Certificate2;
                            i = i3;
                            x500Principal2 = x500Principal322;
                            publicKey2 = publicKey322;
                            i2 = size;
                            trustAnchor3 = trustAnchor2;
                            checkRevocation(this.pkixParams, x509Certificate3, this.validDate, x509Certificate522, publicKey322, cRLDistUrls2, oCSPUrls2, i2);
                        }
                        checkRevocation(this.pkixParams, x509Certificate3, this.validDate, x509Certificate522, publicKey322, cRLDistUrls2, oCSPUrls2, i2);
                    } catch (CertPathReviewerException e5) {
                        e = e5;
                        addError(e.getErrorMessage(), i2);
                        if (x500Principal2 != null) {
                        }
                        c = 2;
                        if (i == this.f9968n) {
                        }
                        x500Principal322 = x509Certificate3.getSubjectX500Principal();
                        publicKey322 = getNextWorkingKey(this.certs, i2);
                        AlgorithmIdentifier algorithmIdentifier22222 = getAlgorithmIdentifier(publicKey322);
                        algorithmIdentifier22222.getAlgorithm();
                        algorithmIdentifier22222.getParameters();
                        size = i2 - 1;
                        c3 = c;
                        c4 = c2;
                        x509Certificate522 = x509Certificate3;
                        trustAnchor2 = trustAnchor3;
                    }
                    x509Certificate3 = x509Certificate2;
                    i = i3;
                    x500Principal2 = x500Principal322;
                    publicKey2 = publicKey322;
                    i2 = size;
                    trustAnchor3 = trustAnchor2;
                } catch (CertPathReviewerException e6) {
                    e = e6;
                    i = i3;
                    x500Principal2 = x500Principal322;
                    publicKey2 = publicKey322;
                    i2 = size;
                    trustAnchor3 = trustAnchor2;
                    x509Certificate3 = x509Certificate2;
                }
                cRLDistPoint = null;
                extensionValue = getExtensionValue(x509Certificate6, AUTH_INFO_ACCESS);
                if (extensionValue != null) {
                }
                authorityInformationAccess = null;
                Vector cRLDistUrls22 = getCRLDistUrls(cRLDistPoint);
                Vector oCSPUrls22 = getOCSPUrls(authorityInformationAccess);
                it = cRLDistUrls22.iterator();
                while (it.hasNext()) {
                }
                x509Certificate2 = x509Certificate6;
                it2 = oCSPUrls22.iterator();
                while (it2.hasNext()) {
                }
            } else {
                x509Certificate3 = x509Certificate6;
                i = i3;
                x500Principal2 = x500Principal322;
                publicKey2 = publicKey322;
                i2 = size;
                trustAnchor3 = trustAnchor2;
            }
            if (x500Principal2 != null || x509Certificate3.getIssuerX500Principal().equals(x500Principal2)) {
                c = 2;
            } else {
                c = 2;
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certWrongIssuer", new Object[]{x500Principal2.getName(), x509Certificate3.getIssuerX500Principal().getName()}), i2);
            }
            if (i == this.f9968n) {
                if (x509Certificate3 != null) {
                    c2 = 1;
                    if (x509Certificate3.getVersion() == 1) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i2);
                    }
                } else {
                    c2 = 1;
                }
                try {
                    BasicConstraints basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate3, BASIC_CONSTRAINTS));
                    if (basicConstraints == null) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBasicConstraints"), i2);
                    } else if (!basicConstraints.isCA()) {
                        addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCACert"), i2);
                    }
                } catch (AnnotatedException unused9) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.errorProcesingBC"), i2);
                }
                boolean[] keyUsage2 = x509Certificate3.getKeyUsage();
                if (keyUsage2 != null && !keyUsage2[5]) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCertSign"), i2);
                }
            } else {
                c2 = 1;
            }
            x500Principal322 = x509Certificate3.getSubjectX500Principal();
            try {
                publicKey322 = getNextWorkingKey(this.certs, i2);
                AlgorithmIdentifier algorithmIdentifier222222 = getAlgorithmIdentifier(publicKey322);
                algorithmIdentifier222222.getAlgorithm();
                algorithmIdentifier222222.getParameters();
            } catch (CertPathValidatorException unused10) {
                publicKey322 = publicKey2;
            }
            size = i2 - 1;
            c3 = c;
            c4 = c2;
            x509Certificate522 = x509Certificate3;
            trustAnchor2 = trustAnchor3;
        }
        this.trustAnchor = trustAnchor2;
        this.subjectPublicKey = publicKey322;
    }

    private X509CRL getCRL(String str) throws CertPathReviewerException {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals(HttpHost.DEFAULT_SCHEME_NAME) && !url.getProtocol().equals("https")) {
                return null;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            boolean z = false;
            for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
                QCStatement qCStatement = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals((ASN1Primitive) qCStatement.getStatementId())) {
                    errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance");
                } else {
                    if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals((ASN1Primitive) qCStatement.getStatementId())) {
                        if (QCStatement.id_etsi_qcs_QcSSCD.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD");
                        } else if (QCStatement.id_etsi_qcs_LimiteValue.equals((ASN1Primitive) qCStatement.getStatementId())) {
                            MonetaryValue monetaryValue = MonetaryValue.getInstance(qCStatement.getStatementInfo());
                            monetaryValue.getCurrency();
                            double doubleValue = monetaryValue.getAmount().doubleValue() * Math.pow(10.0d, monetaryValue.getExponent().doubleValue());
                            addNotification(monetaryValue.getCurrency().isAlphabetic() ? new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", new Object[]{monetaryValue.getCurrency().getAlphabetic(), new TrustedInput(new Double(doubleValue)), monetaryValue}) : new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(monetaryValue.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), monetaryValue}), i);
                        } else {
                            addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{qCStatement.getStatementId(), new UntrustedInput(qCStatement)}), i);
                            z = true;
                        }
                    }
                }
                addNotification(errorBundle, i);
            }
            return true ^ z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    protected void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    protected void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.f9968n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    protected void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.f9968n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void checkCRLs(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, int i) throws CertPathReviewerException {
        Iterator it;
        X509CRL x509crl;
        boolean z;
        X509CRL x509crl2;
        X509CRL x509crl3;
        boolean z2;
        boolean z3;
        String str;
        boolean[] keyUsage;
        X509CRL x509crl4;
        Iterator it2;
        String str2;
        boolean z4;
        Iterator it3;
        X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
        try {
            x509CRLStoreSelector.addIssuerName(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            x509CRLStoreSelector.setCertificateChecking(x509Certificate);
            try {
                Set findCRLs = CRL_UTIL.findCRLs(x509CRLStoreSelector, pKIXParameters);
                Iterator it4 = findCRLs.iterator();
                if (findCRLs.isEmpty()) {
                    Iterator it5 = CRL_UTIL.findCRLs(new X509CRLStoreSelector(), pKIXParameters).iterator();
                    ArrayList arrayList = new ArrayList();
                    while (it5.hasNext()) {
                        arrayList.add(((X509CRL) it5.next()).getIssuerX500Principal());
                    }
                    it3 = it4;
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCrlInCertstore", new Object[]{new UntrustedInput(x509CRLStoreSelector.getIssuerNames()), new UntrustedInput(arrayList), Integers.valueOf(arrayList.size())}), i);
                } else {
                    it3 = it4;
                }
                it = it3;
            } catch (AnnotatedException e) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlExtractionError", new Object[]{e.getCause().getMessage(), e.getCause(), e.getCause().getClass().getName()}), i);
                it = new ArrayList().iterator();
            }
            X509CRL x509crl5 = null;
            while (it.hasNext()) {
                x509crl5 = (X509CRL) it.next();
                if (x509crl5.getNextUpdate() == null || pKIXParameters.getDate().before(x509crl5.getNextUpdate())) {
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.localValidCRL", new Object[]{new TrustedInput(x509crl5.getThisUpdate()), new TrustedInput(x509crl5.getNextUpdate())}), i);
                    x509crl = x509crl5;
                    z = true;
                    break;
                }
                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.localInvalidCRL", new Object[]{new TrustedInput(x509crl5.getThisUpdate()), new TrustedInput(x509crl5.getNextUpdate())}), i);
            }
            x509crl = x509crl5;
            z = false;
            if (z) {
                x509crl2 = x509crl;
            } else {
                Iterator it6 = vector.iterator();
                while (it6.hasNext()) {
                    try {
                        str2 = (String) it6.next();
                        x509crl3 = getCRL(str2);
                    } catch (CertPathReviewerException e2) {
                        e = e2;
                        x509crl4 = x509crl;
                        it2 = it6;
                    }
                    if (x509crl3 != null) {
                        if (x509Certificate.getIssuerX500Principal().equals(x509crl3.getIssuerX500Principal())) {
                            x509crl4 = x509crl;
                            it2 = it6;
                            try {
                            } catch (CertPathReviewerException e3) {
                                e = e3;
                                addNotification(e.getErrorMessage(), i);
                                it6 = it2;
                                x509crl = x509crl4;
                            }
                            if (x509crl3.getNextUpdate() != null && !this.pkixParams.getDate().before(x509crl3.getNextUpdate())) {
                                Object[] objArr = new Object[3];
                                z4 = z;
                                try {
                                    objArr[0] = new TrustedInput(x509crl3.getThisUpdate());
                                    objArr[1] = new TrustedInput(x509crl3.getNextUpdate());
                                    objArr[2] = new UntrustedUrlInput(str2);
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineInvalidCRL", objArr), i);
                                    it6 = it2;
                                    x509crl = x509crl4;
                                    z = z4;
                                } catch (CertPathReviewerException e4) {
                                    e = e4;
                                    z = z4;
                                }
                            }
                            try {
                                addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineValidCRL", new Object[]{new TrustedInput(x509crl3.getThisUpdate()), new TrustedInput(x509crl3.getNextUpdate()), new UntrustedUrlInput(str2)}), i);
                                z = true;
                                break;
                            } catch (CertPathReviewerException e5) {
                                e = e5;
                                z = true;
                            }
                        } else {
                            try {
                                x509crl4 = x509crl;
                                it2 = it6;
                                try {
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.onlineCRLWrongCA", new Object[]{new UntrustedInput(x509crl3.getIssuerX500Principal().getName()), new UntrustedInput(x509Certificate.getIssuerX500Principal().getName()), new UntrustedUrlInput(str2)}), i);
                                } catch (CertPathReviewerException e6) {
                                    e = e6;
                                }
                            } catch (CertPathReviewerException e7) {
                                e = e7;
                                x509crl4 = x509crl;
                                it2 = it6;
                            }
                        }
                        addNotification(e.getErrorMessage(), i);
                        it6 = it2;
                        x509crl = x509crl4;
                    } else {
                        x509crl4 = x509crl;
                        it2 = it6;
                    }
                    z4 = z;
                    it6 = it2;
                    x509crl = x509crl4;
                    z = z4;
                }
                x509crl2 = x509crl;
            }
            x509crl3 = x509crl2;
            if (x509crl3 != null) {
                if (x509Certificate2 != null && (keyUsage = x509Certificate2.getKeyUsage()) != null && (keyUsage.length < 7 || !keyUsage[6])) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noCrlSigningPermited"));
                }
                if (publicKey == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlNoIssuerPublicKey"));
                }
                try {
                    x509crl3.verify(publicKey, BouncyCastleProvider.PROVIDER_NAME);
                    X509CRLEntry revokedCertificate = x509crl3.getRevokedCertificate(x509Certificate.getSerialNumber());
                    if (revokedCertificate != null) {
                        if (revokedCertificate.hasExtensions()) {
                            try {
                                ASN1Enumerated aSN1Enumerated = ASN1Enumerated.getInstance(getExtensionValue(revokedCertificate, Extension.reasonCode.getId()));
                                if (aSN1Enumerated != null) {
                                    str = crlReasons[aSN1Enumerated.intValueExact()];
                                    if (str == null) {
                                        str = crlReasons[7];
                                    }
                                    LocaleString localeString = new LocaleString(RESOURCE_NAME, str);
                                    if (date.before(revokedCertificate.getRevocationDate())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certRevoked", new Object[]{new TrustedInput(revokedCertificate.getRevocationDate()), localeString}));
                                    }
                                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.revokedAfterValidation", new Object[]{new TrustedInput(revokedCertificate.getRevocationDate()), localeString}), i);
                                }
                            } catch (AnnotatedException e8) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlReasonExtError"), e8);
                            }
                        }
                        str = null;
                        if (str == null) {
                        }
                        LocaleString localeString2 = new LocaleString(RESOURCE_NAME, str);
                        if (date.before(revokedCertificate.getRevocationDate())) {
                        }
                    } else {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notRevoked"), i);
                    }
                    if (x509crl3.getNextUpdate() == null || !x509crl3.getNextUpdate().before(this.pkixParams.getDate())) {
                        z2 = true;
                        z3 = false;
                    } else {
                        z2 = true;
                        z3 = false;
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlUpdateAvailable", new Object[]{new TrustedInput(x509crl3.getNextUpdate())}), i);
                    }
                    try {
                        ASN1Primitive extensionValue = getExtensionValue(x509crl3, ISSUING_DISTRIBUTION_POINT);
                        try {
                            ASN1Primitive extensionValue2 = getExtensionValue(x509crl3, DELTA_CRL_INDICATOR);
                            if (extensionValue2 != null) {
                                X509CRLStoreSelector x509CRLStoreSelector2 = new X509CRLStoreSelector();
                                try {
                                    x509CRLStoreSelector2.addIssuerName(getIssuerPrincipal(x509crl3).getEncoded());
                                    x509CRLStoreSelector2.setMinCRLNumber(((ASN1Integer) extensionValue2).getPositiveValue());
                                    try {
                                        x509CRLStoreSelector2.setMaxCRLNumber(((ASN1Integer) getExtensionValue(x509crl3, CRL_NUMBER)).getPositiveValue().subtract(BigInteger.valueOf(1L)));
                                        try {
                                            Iterator it7 = CRL_UTIL.findCRLs(x509CRLStoreSelector2, pKIXParameters).iterator();
                                            while (true) {
                                                if (!it7.hasNext()) {
                                                    z2 = z3;
                                                    break;
                                                }
                                                try {
                                                    ASN1Primitive extensionValue3 = getExtensionValue((X509CRL) it7.next(), ISSUING_DISTRIBUTION_POINT);
                                                    if (extensionValue == null) {
                                                        if (extensionValue3 == null) {
                                                            break;
                                                        }
                                                    } else if (extensionValue.equals(extensionValue3)) {
                                                        break;
                                                    }
                                                } catch (AnnotatedException e9) {
                                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.distrPtExtError"), e9);
                                                }
                                            }
                                            if (!z2) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noBaseCRL"));
                                            }
                                        } catch (AnnotatedException e10) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlExtractionError"), e10);
                                        }
                                    } catch (AnnotatedException e11) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlNbrExtError"), e11);
                                    }
                                } catch (IOException e12) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlIssuerException"), e12);
                                }
                            }
                            if (extensionValue != null) {
                                IssuingDistributionPoint issuingDistributionPoint = IssuingDistributionPoint.getInstance(extensionValue);
                                try {
                                    BasicConstraints basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
                                    if (issuingDistributionPoint.onlyContainsUserCerts() && basicConstraints != null && basicConstraints.isCA()) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyUserCert"));
                                    }
                                    if (issuingDistributionPoint.onlyContainsCACerts() && (basicConstraints == null || !basicConstraints.isCA())) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyCaCert"));
                                    }
                                    if (issuingDistributionPoint.onlyContainsAttributeCerts()) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlOnlyAttrCert"));
                                    }
                                } catch (AnnotatedException e13) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlBCExtError"), e13);
                                }
                            }
                        } catch (AnnotatedException unused) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.deltaCrlExtError"));
                        }
                    } catch (AnnotatedException unused2) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.distrPtExtError"));
                    }
                } catch (Exception e14) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlVerifyFailed"), e14);
                }
            }
            if (!z) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidCrlFound"));
            }
        } catch (IOException e15) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.crlIssuerException"), e15);
        }
    }

    protected void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) throws CertPathReviewerException {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    protected void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        }
        if (this.notifications != null) {
            return;
        }
        int i = this.f9968n;
        this.notifications = new List[i + 1];
        this.errors = new List[i + 1];
        int i2 = 0;
        while (true) {
            List[] listArr = this.notifications;
            if (i2 >= listArr.length) {
                checkSignatures();
                checkNameConstraints();
                checkPathLength();
                checkPolicy();
                checkCriticalExtensions();
                return;
            }
            listArr[i2] = new ArrayList();
            this.errors[i2] = new ArrayList();
            i2++;
        }
    }

    protected Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((DERIA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.f9968n;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    protected Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals((ASN1Primitive) AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((DERIA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    protected Collection getTrustAnchors(X509Certificate x509Certificate, Set set) throws CertPathReviewerException {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier authorityKeyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                x509CertSelector.setSerialNumber(authorityKeyIdentifier.getAuthorityCertSerialNumber());
                byte[] keyIdentifier = authorityKeyIdentifier.getKeyIdentifier();
                if (keyIdentifier != null) {
                    x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor = (TrustAnchor) it.next();
                if (trustAnchor.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor.getTrustedCert())) {
                        arrayList.add(trustAnchor);
                    }
                } else if (trustAnchor.getCAName() != null && trustAnchor.getCAPublicKey() != null && getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor.getCAName()))) {
                    arrayList.add(trustAnchor);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }

    public void init(CertPath certPath, PKIXParameters pKIXParameters) throws CertPathReviewerException {
        if (this.initialized) {
            throw new IllegalStateException("object is already initialized!");
        }
        this.initialized = true;
        if (certPath == null) {
            throw new NullPointerException("certPath was null");
        }
        this.certPath = certPath;
        this.certs = certPath.getCertificates();
        this.f9968n = this.certs.size();
        if (this.certs.isEmpty()) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
        }
        this.pkixParams = (PKIXParameters) pKIXParameters.clone();
        this.validDate = getValidDate(this.pkixParams);
        this.notifications = null;
        this.errors = null;
        this.trustAnchor = null;
        this.subjectPublicKey = null;
        this.policyTree = null;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }
}
