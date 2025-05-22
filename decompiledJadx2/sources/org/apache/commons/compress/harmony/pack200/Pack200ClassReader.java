package org.apache.commons.compress.harmony.pack200;

import org.objectweb.asm.ClassReader;

/* loaded from: classes9.dex */
public class Pack200ClassReader extends ClassReader {
    private boolean anySyntheticAttributes;
    private String fileName;
    private boolean lastConstantHadWideIndex;
    private int lastUnsignedShort;

    public Pack200ClassReader(byte[] bArr) {
        super(bArr);
    }

    public int readUnsignedShort(int i) {
        int readUnsignedShort = super.readUnsignedShort(i);
        if (this.b[i - 1] == 19) {
            this.lastUnsignedShort = readUnsignedShort;
        } else {
            this.lastUnsignedShort = -32768;
        }
        return readUnsignedShort;
    }

    public Object readConst(int i, char[] cArr) {
        this.lastConstantHadWideIndex = i == this.lastUnsignedShort;
        return super.readConst(i, cArr);
    }

    public String readUTF8(int i, char[] cArr) {
        String readUTF8 = super.readUTF8(i, cArr);
        if (!this.anySyntheticAttributes && "Synthetic".equals(readUTF8)) {
            this.anySyntheticAttributes = true;
        }
        return readUTF8;
    }

    public boolean lastConstantHadWideIndex() {
        return this.lastConstantHadWideIndex;
    }

    public boolean hasSyntheticAttributes() {
        return this.anySyntheticAttributes;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFileName() {
        return this.fileName;
    }
}
