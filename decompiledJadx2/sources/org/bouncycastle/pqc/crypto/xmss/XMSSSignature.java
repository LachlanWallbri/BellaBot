package org.bouncycastle.pqc.crypto.xmss;

import java.io.IOException;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public final class XMSSSignature extends XMSSReducedSignature implements XMSSStoreableObjectInterface, Encodable {
    private final int index;
    private final byte[] random;

    /* loaded from: classes9.dex */
    public static class Builder extends XMSSReducedSignature.Builder {
        private int index;
        private final XMSSParameters params;
        private byte[] random;

        public Builder(XMSSParameters xMSSParameters) {
            super(xMSSParameters);
            this.index = 0;
            this.random = null;
            this.params = xMSSParameters;
        }

        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature.Builder
        public XMSSSignature build() {
            return new XMSSSignature(this);
        }

        public Builder withIndex(int i) {
            this.index = i;
            return this;
        }

        public Builder withRandom(byte[] bArr) {
            this.random = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            if (bArr == null) {
                throw new NullPointerException("signature == null");
            }
            int treeDigestSize = this.params.getTreeDigestSize();
            int len = this.params.getWOTSPlus().getParams().getLen();
            int height = this.params.getHeight() * treeDigestSize;
            this.index = Pack.bigEndianToInt(bArr, 0);
            this.random = XMSSUtil.extractBytesAtOffset(bArr, 4, treeDigestSize);
            withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, 4 + treeDigestSize, (len * treeDigestSize) + height));
            return this;
        }
    }

    private XMSSSignature(Builder builder) {
        super(builder);
        this.index = builder.index;
        int treeDigestSize = getParams().getTreeDigestSize();
        byte[] bArr = builder.random;
        if (bArr == null) {
            this.random = new byte[treeDigestSize];
        } else {
            if (bArr.length != treeDigestSize) {
                throw new IllegalArgumentException("size of random needs to be equal to size of digest");
            }
            this.random = bArr;
        }
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() throws IOException {
        return toByteArray();
    }

    public int getIndex() {
        return this.index;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature, org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int treeDigestSize = getParams().getTreeDigestSize();
        byte[] bArr = new byte[treeDigestSize + 4 + (getParams().getWOTSPlus().getParams().getLen() * treeDigestSize) + (getParams().getHeight() * treeDigestSize)];
        Pack.intToBigEndian(this.index, bArr, 0);
        XMSSUtil.copyBytesAtOffset(bArr, this.random, 4);
        int i = 4 + treeDigestSize;
        for (byte[] bArr2 : getWOTSPlusSignature().toByteArray()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i);
            i += treeDigestSize;
        }
        for (int i2 = 0; i2 < getAuthPath().size(); i2++) {
            XMSSUtil.copyBytesAtOffset(bArr, getAuthPath().get(i2).getValue(), i);
            i += treeDigestSize;
        }
        return bArr;
    }
}
