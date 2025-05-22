package org.bouncycastle.asn1;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class DLFactory {
    static final ASN1Sequence EMPTY_SEQUENCE = new DLSequence();
    static final ASN1Set EMPTY_SET = new DLSet();

    DLFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Sequence createSequence(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? EMPTY_SEQUENCE : new DLSequence(aSN1EncodableVector);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Set createSet(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? EMPTY_SET : new DLSet(aSN1EncodableVector);
    }
}
