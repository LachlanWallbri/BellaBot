package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTCTime;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;

/* loaded from: classes9.dex */
public class TBSCertList extends ASN1Object {
    Extensions crlExtensions;
    X500Name issuer;
    Time nextUpdate;
    ASN1Sequence revokedCertificates;
    AlgorithmIdentifier signature;
    Time thisUpdate;
    ASN1Integer version;

    /* loaded from: classes9.dex */
    public static class CRLEntry extends ASN1Object {
        Extensions crlEntryExtensions;
        ASN1Sequence seq;

        private CRLEntry(ASN1Sequence aSN1Sequence) {
            if (aSN1Sequence.size() >= 2 && aSN1Sequence.size() <= 3) {
                this.seq = aSN1Sequence;
                return;
            }
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }

        public static CRLEntry getInstance(Object obj) {
            if (obj instanceof CRLEntry) {
                return (CRLEntry) obj;
            }
            if (obj != null) {
                return new CRLEntry(ASN1Sequence.getInstance(obj));
            }
            return null;
        }

        public Extensions getExtensions() {
            if (this.crlEntryExtensions == null && this.seq.size() == 3) {
                this.crlEntryExtensions = Extensions.getInstance(this.seq.getObjectAt(2));
            }
            return this.crlEntryExtensions;
        }

        public Time getRevocationDate() {
            return Time.getInstance(this.seq.getObjectAt(1));
        }

        public ASN1Integer getUserCertificate() {
            return ASN1Integer.getInstance(this.seq.getObjectAt(0));
        }

        public boolean hasExtensions() {
            return this.seq.size() == 3;
        }

        @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
        public ASN1Primitive toASN1Primitive() {
            return this.seq;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class EmptyEnumeration implements Enumeration {
        private EmptyEnumeration() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return false;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            throw new NoSuchElementException("Empty Enumeration");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class RevokedCertificatesEnumeration implements Enumeration {

        /* renamed from: en */
        private final Enumeration f9087en;

        RevokedCertificatesEnumeration(Enumeration enumeration) {
            this.f9087en = enumeration;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f9087en.hasMoreElements();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return CRLEntry.getInstance(this.f9087en.nextElement());
        }
    }

    public TBSCertList(ASN1Sequence aSN1Sequence) {
        int i;
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 7) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        int i2 = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
            i2 = 1;
        } else {
            this.version = null;
        }
        int i3 = i2 + 1;
        this.signature = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(i2));
        int i4 = i3 + 1;
        this.issuer = X500Name.getInstance(aSN1Sequence.getObjectAt(i3));
        int i5 = i4 + 1;
        this.thisUpdate = Time.getInstance(aSN1Sequence.getObjectAt(i4));
        if (i5 >= aSN1Sequence.size() || !((aSN1Sequence.getObjectAt(i5) instanceof ASN1UTCTime) || (aSN1Sequence.getObjectAt(i5) instanceof ASN1GeneralizedTime) || (aSN1Sequence.getObjectAt(i5) instanceof Time))) {
            i = i5;
        } else {
            i = i5 + 1;
            this.nextUpdate = Time.getInstance(aSN1Sequence.getObjectAt(i5));
        }
        if (i < aSN1Sequence.size() && !(aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject)) {
            this.revokedCertificates = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(i));
            i++;
        }
        if (i >= aSN1Sequence.size() || !(aSN1Sequence.getObjectAt(i) instanceof ASN1TaggedObject)) {
            return;
        }
        this.crlExtensions = Extensions.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i), true));
    }

    public static TBSCertList getInstance(Object obj) {
        if (obj instanceof TBSCertList) {
            return (TBSCertList) obj;
        }
        if (obj != null) {
            return new TBSCertList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSCertList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extensions getExtensions() {
        return this.crlExtensions;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public Time getNextUpdate() {
        return this.nextUpdate;
    }

    public Enumeration getRevokedCertificateEnumeration() {
        ASN1Sequence aSN1Sequence = this.revokedCertificates;
        return aSN1Sequence == null ? new EmptyEnumeration() : new RevokedCertificatesEnumeration(aSN1Sequence.getObjects());
    }

    public CRLEntry[] getRevokedCertificates() {
        ASN1Sequence aSN1Sequence = this.revokedCertificates;
        if (aSN1Sequence == null) {
            return new CRLEntry[0];
        }
        CRLEntry[] cRLEntryArr = new CRLEntry[aSN1Sequence.size()];
        for (int i = 0; i < cRLEntryArr.length; i++) {
            cRLEntryArr[i] = CRLEntry.getInstance(this.revokedCertificates.getObjectAt(i));
        }
        return cRLEntryArr;
    }

    public AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    public Time getThisUpdate() {
        return this.thisUpdate;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public int getVersionNumber() {
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer == null) {
            return 1;
        }
        return aSN1Integer.intValueExact() + 1;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(7);
        ASN1Integer aSN1Integer = this.version;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        aSN1EncodableVector.add(this.signature);
        aSN1EncodableVector.add(this.issuer);
        aSN1EncodableVector.add(this.thisUpdate);
        Time time = this.nextUpdate;
        if (time != null) {
            aSN1EncodableVector.add(time);
        }
        ASN1Sequence aSN1Sequence = this.revokedCertificates;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(aSN1Sequence);
        }
        Extensions extensions = this.crlExtensions;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
