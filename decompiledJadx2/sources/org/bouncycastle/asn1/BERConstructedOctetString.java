package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* loaded from: classes9.dex */
public class BERConstructedOctetString extends BEROctetString {
    private static final int MAX_LENGTH = 1000;
    private Vector octs;

    public BERConstructedOctetString(Vector vector) {
        super(toBytes(vector));
        this.octs = vector;
    }

    public BERConstructedOctetString(ASN1Encodable aSN1Encodable) {
        this(aSN1Encodable.toASN1Primitive());
    }

    public BERConstructedOctetString(ASN1Primitive aSN1Primitive) {
        super(toByteArray(aSN1Primitive));
    }

    public BERConstructedOctetString(byte[] bArr) {
        super(bArr);
    }

    public static BEROctetString fromSequence(ASN1Sequence aSN1Sequence) {
        Vector vector = new Vector();
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            vector.addElement(objects.nextElement());
        }
        return new BERConstructedOctetString(vector);
    }

    private Vector generateOcts() {
        Vector vector = new Vector();
        int i = 0;
        while (i < this.string.length) {
            int i2 = i + 1000;
            byte[] bArr = new byte[(i2 > this.string.length ? this.string.length : i2) - i];
            System.arraycopy(this.string, i, bArr, 0, bArr.length);
            vector.addElement(new DEROctetString(bArr));
            i = i2;
        }
        return vector;
    }

    private static byte[] toByteArray(ASN1Primitive aSN1Primitive) {
        try {
            return aSN1Primitive.getEncoded();
        } catch (IOException unused) {
            throw new IllegalArgumentException("Unable to encode object");
        }
    }

    private static byte[] toBytes(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != vector.size(); i++) {
            try {
                byteArrayOutputStream.write(((DEROctetString) vector.elementAt(i)).getOctets());
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(vector.elementAt(i).getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.bouncycastle.asn1.BEROctetString
    public Enumeration getObjects() {
        Vector vector = this.octs;
        return vector == null ? generateOcts().elements() : vector.elements();
    }

    @Override // org.bouncycastle.asn1.BEROctetString, org.bouncycastle.asn1.ASN1OctetString
    public byte[] getOctets() {
        return this.string;
    }
}
