package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes8.dex */
public class ResourceAlignmentExtraField implements ZipExtraField {
    private static final int ALLOW_METHOD_MESSAGE_CHANGE_FLAG = 32768;
    public static final int BASE_SIZE = 2;

    /* renamed from: ID */
    public static final ZipShort f8919ID = new ZipShort(41246);
    private short alignment;
    private boolean allowMethodChange;
    private int padding;

    public ResourceAlignmentExtraField() {
    }

    public ResourceAlignmentExtraField(int i) {
        this(i, false);
    }

    public ResourceAlignmentExtraField(int i, boolean z) {
        this(i, z, 0);
    }

    public ResourceAlignmentExtraField(int i, boolean z, int i2) {
        if (i < 0 || i > 32767) {
            throw new IllegalArgumentException("Alignment must be between 0 and 0x7fff, was: " + i);
        }
        if (i2 < 0) {
            throw new IllegalArgumentException("Padding must not be negative, was: " + i2);
        }
        this.alignment = (short) i;
        this.allowMethodChange = z;
        this.padding = i2;
    }

    public short getAlignment() {
        return this.alignment;
    }

    public boolean allowMethodChange() {
        return this.allowMethodChange;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return f8919ID;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.padding + 2);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort(2);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] bArr = new byte[this.padding + 2];
        ZipShort.putShort(this.alignment | (this.allowMethodChange ? ShortCompanionObject.MIN_VALUE : (short) 0), bArr, 0);
        return bArr;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return ZipShort.getBytes(this.alignment | (this.allowMethodChange ? ShortCompanionObject.MIN_VALUE : (short) 0));
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        parseFromCentralDirectoryData(bArr, i, i2);
        this.padding = i2 - 2;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        if (i2 < 2) {
            throw new ZipException("Too short content for ResourceAlignmentExtraField (0xa11e): " + i2);
        }
        int value = ZipShort.getValue(bArr, i);
        this.alignment = (short) (value & 32767);
        this.allowMethodChange = (value & 32768) != 0;
    }
}
