package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;

/* loaded from: classes9.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final boolean lazyEvaluate;
    private final int limit;
    private final byte[][] tmpBuffers;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1InputStream(InputStream inputStream, int i) {
        this(inputStream, i, false);
    }

    public ASN1InputStream(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.limit = i;
        this.lazyEvaluate = z;
        this.tmpBuffers = new byte[11];
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.findLimit(inputStream), z);
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive createPrimitiveDERObject(int i, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        if (i == 10) {
            return ASN1Enumerated.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
        }
        if (i == 12) {
            return new DERUTF8String(definiteLengthInputStream.toByteArray());
        }
        if (i == 30) {
            return new DERBMPString(getBMPCharBuffer(definiteLengthInputStream));
        }
        switch (i) {
            case 1:
                return ASN1Boolean.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            case 2:
                return new ASN1Integer(definiteLengthInputStream.toByteArray(), false);
            case 3:
                return ASN1BitString.fromInputStream(definiteLengthInputStream.getRemaining(), definiteLengthInputStream);
            case 4:
                return new DEROctetString(definiteLengthInputStream.toByteArray());
            case 5:
                return DERNull.INSTANCE;
            case 6:
                return ASN1ObjectIdentifier.fromOctetString(getBuffer(definiteLengthInputStream, bArr));
            default:
                switch (i) {
                    case 18:
                        return new DERNumericString(definiteLengthInputStream.toByteArray());
                    case 19:
                        return new DERPrintableString(definiteLengthInputStream.toByteArray());
                    case 20:
                        return new DERT61String(definiteLengthInputStream.toByteArray());
                    case 21:
                        return new DERVideotexString(definiteLengthInputStream.toByteArray());
                    case 22:
                        return new DERIA5String(definiteLengthInputStream.toByteArray());
                    case 23:
                        return new ASN1UTCTime(definiteLengthInputStream.toByteArray());
                    case 24:
                        return new ASN1GeneralizedTime(definiteLengthInputStream.toByteArray());
                    case 25:
                        return new DERGraphicString(definiteLengthInputStream.toByteArray());
                    case 26:
                        return new DERVisibleString(definiteLengthInputStream.toByteArray());
                    case 27:
                        return new DERGeneralString(definiteLengthInputStream.toByteArray());
                    case 28:
                        return new DERUniversalString(definiteLengthInputStream.toByteArray());
                    default:
                        throw new IOException("unknown tag " + i + " encountered");
                }
        }
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int i;
        int remaining = definiteLengthInputStream.getRemaining();
        if ((remaining & 1) != 0) {
            throw new IOException("malformed BMPString encoding encountered");
        }
        char[] cArr = new char[remaining / 2];
        byte[] bArr = new byte[8];
        int i2 = 0;
        int i3 = 0;
        while (remaining >= 8) {
            if (Streams.readFully(definiteLengthInputStream, bArr, 0, 8) != 8) {
                throw new EOFException("EOF encountered in middle of BMPString");
            }
            cArr[i3] = (char) ((bArr[0] << 8) | (bArr[1] & 255));
            cArr[i3 + 1] = (char) ((bArr[2] << 8) | (bArr[3] & 255));
            cArr[i3 + 2] = (char) ((bArr[4] << 8) | (bArr[5] & 255));
            cArr[i3 + 3] = (char) ((bArr[6] << 8) | (bArr[7] & 255));
            i3 += 4;
            remaining -= 8;
        }
        if (remaining > 0) {
            if (Streams.readFully(definiteLengthInputStream, bArr, 0, remaining) != remaining) {
                throw new EOFException("EOF encountered in middle of BMPString");
            }
            while (true) {
                int i4 = i2 + 1;
                int i5 = i4 + 1;
                i = i3 + 1;
                cArr[i3] = (char) ((bArr[i2] << 8) | (bArr[i4] & 255));
                if (i5 >= remaining) {
                    break;
                }
                i2 = i5;
                i3 = i;
            }
            i3 = i;
        }
        if (definiteLengthInputStream.getRemaining() == 0 && cArr.length == i3) {
            return cArr;
        }
        throw new IllegalStateException();
    }

    private static byte[] getBuffer(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        int remaining = definiteLengthInputStream.getRemaining();
        if (definiteLengthInputStream.getRemaining() >= bArr.length) {
            return definiteLengthInputStream.toByteArray();
        }
        byte[] bArr2 = bArr[remaining];
        if (bArr2 == null) {
            bArr2 = new byte[remaining];
            bArr[remaining] = bArr2;
        }
        Streams.readFully(definiteLengthInputStream, bArr2);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readLength(InputStream inputStream, int i, boolean z) throws IOException {
        int read = inputStream.read();
        if (read < 0) {
            throw new EOFException("EOF found when length expected");
        }
        if (read == 128) {
            return -1;
        }
        if (read <= 127) {
            return read;
        }
        int i2 = read & 127;
        if (i2 > 4) {
            throw new IOException("DER length more than 4 bytes: " + i2);
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int read2 = inputStream.read();
            if (read2 < 0) {
                throw new EOFException("EOF found reading length");
            }
            i3 = (i3 << 8) + read2;
        }
        if (i3 < 0) {
            throw new IOException("corrupted stream - negative length found");
        }
        if (i3 < i || z) {
            return i3;
        }
        throw new IOException("corrupted stream - out of bounds length found: " + i3 + " >= " + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readTagNumber(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 != 31) {
            return i2;
        }
        int i3 = 0;
        int read = inputStream.read();
        if ((read & 127) == 0) {
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        while (read >= 0 && (read & 128) != 0) {
            i3 = (i3 | (read & 127)) << 7;
            read = inputStream.read();
        }
        if (read >= 0) {
            return i3 | (read & 127);
        }
        throw new EOFException("EOF found inside tag value.");
    }

    protected ASN1Primitive buildObject(int i, int i2, int i3) throws IOException {
        boolean z = (i & 32) != 0;
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i3, this.limit);
        if ((i & 64) != 0) {
            return new DLApplicationSpecific(z, i2, definiteLengthInputStream.toByteArray());
        }
        if ((i & 128) != 0) {
            return new ASN1StreamParser(definiteLengthInputStream).readTaggedObject(z, i2);
        }
        if (!z) {
            return createPrimitiveDERObject(i2, definiteLengthInputStream, this.tmpBuffers);
        }
        if (i2 != 4) {
            if (i2 == 8) {
                return new DLExternal(readVector(definiteLengthInputStream));
            }
            if (i2 == 16) {
                return this.lazyEvaluate ? new LazyEncodedSequence(definiteLengthInputStream.toByteArray()) : DLFactory.createSequence(readVector(definiteLengthInputStream));
            }
            if (i2 == 17) {
                return DLFactory.createSet(readVector(definiteLengthInputStream));
            }
            throw new IOException("unknown tag " + i2 + " encountered");
        }
        ASN1EncodableVector readVector = readVector(definiteLengthInputStream);
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[readVector.size()];
        for (int i4 = 0; i4 != aSN1OctetStringArr.length; i4++) {
            ASN1Encodable aSN1Encodable = readVector.get(i4);
            if (!(aSN1Encodable instanceof ASN1OctetString)) {
                throw new ASN1Exception("unknown object encountered in constructed OCTET STRING: " + aSN1Encodable.getClass());
            }
            aSN1OctetStringArr[i4] = (ASN1OctetString) aSN1Encodable;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLimit() {
        return this.limit;
    }

    protected void readFully(byte[] bArr) throws IOException {
        if (Streams.readFully(this, bArr) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    protected int readLength() throws IOException {
        return readLength(this, this.limit, false);
    }

    public ASN1Primitive readObject() throws IOException {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int readTagNumber = readTagNumber(this, read);
        boolean z = (read & 32) != 0;
        int readLength = readLength();
        if (readLength >= 0) {
            try {
                return buildObject(read, readTagNumber, readLength);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        }
        if (!z) {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit);
        if ((read & 64) != 0) {
            return new BERApplicationSpecificParser(readTagNumber, aSN1StreamParser).getLoadedObject();
        }
        if ((read & 128) != 0) {
            return new BERTaggedObjectParser(true, readTagNumber, aSN1StreamParser).getLoadedObject();
        }
        if (readTagNumber == 4) {
            return new BEROctetStringParser(aSN1StreamParser).getLoadedObject();
        }
        if (readTagNumber == 8) {
            return new DERExternalParser(aSN1StreamParser).getLoadedObject();
        }
        if (readTagNumber == 16) {
            return new BERSequenceParser(aSN1StreamParser).getLoadedObject();
        }
        if (readTagNumber == 17) {
            return new BERSetParser(aSN1StreamParser).getLoadedObject();
        }
        throw new IOException("unknown BER object encountered");
    }

    ASN1EncodableVector readVector(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        if (definiteLengthInputStream.getRemaining() < 1) {
            return new ASN1EncodableVector(0);
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(definiteLengthInputStream);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (readObject == null) {
                return aSN1EncodableVector;
            }
            aSN1EncodableVector.add(readObject);
        }
    }
}
