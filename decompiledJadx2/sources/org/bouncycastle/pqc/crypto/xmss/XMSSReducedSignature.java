package org.bouncycastle.pqc.crypto.xmss;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class XMSSReducedSignature implements XMSSStoreableObjectInterface {
    private final List<XMSSNode> authPath;
    private final XMSSParameters params;
    private final WOTSPlusSignature wotsPlusSignature;

    /* loaded from: classes9.dex */
    public static class Builder {
        private final XMSSParameters params;
        private WOTSPlusSignature wotsPlusSignature = null;
        private List<XMSSNode> authPath = null;
        private byte[] reducedSignature = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public XMSSReducedSignature build() {
            return new XMSSReducedSignature(this);
        }

        public Builder withAuthPath(List<XMSSNode> list) {
            this.authPath = list;
            return this;
        }

        public Builder withReducedSignature(byte[] bArr) {
            this.reducedSignature = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withWOTSPlusSignature(WOTSPlusSignature wOTSPlusSignature) {
            this.wotsPlusSignature = wOTSPlusSignature;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSReducedSignature(Builder builder) {
        List<XMSSNode> list;
        this.params = builder.params;
        XMSSParameters xMSSParameters = this.params;
        if (xMSSParameters == null) {
            throw new NullPointerException("params == null");
        }
        int treeDigestSize = xMSSParameters.getTreeDigestSize();
        int len = this.params.getWOTSPlus().getParams().getLen();
        int height = this.params.getHeight();
        byte[] bArr = builder.reducedSignature;
        if (bArr != null) {
            if (bArr.length != (len * treeDigestSize) + (height * treeDigestSize)) {
                throw new IllegalArgumentException("signature has wrong size");
            }
            byte[][] bArr2 = new byte[len];
            int i = 0;
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                bArr2[i2] = XMSSUtil.extractBytesAtOffset(bArr, i, treeDigestSize);
                i += treeDigestSize;
            }
            this.wotsPlusSignature = new WOTSPlusSignature(this.params.getWOTSPlus().getParams(), bArr2);
            list = new ArrayList<>();
            for (int i3 = 0; i3 < height; i3++) {
                list.add(new XMSSNode(i3, XMSSUtil.extractBytesAtOffset(bArr, i, treeDigestSize)));
                i += treeDigestSize;
            }
        } else {
            WOTSPlusSignature wOTSPlusSignature = builder.wotsPlusSignature;
            this.wotsPlusSignature = wOTSPlusSignature == null ? new WOTSPlusSignature(this.params.getWOTSPlus().getParams(), (byte[][]) Array.newInstance((Class<?>) byte.class, len, treeDigestSize)) : wOTSPlusSignature;
            list = builder.authPath;
            if (list == null) {
                list = new ArrayList<>();
            } else if (list.size() != height) {
                throw new IllegalArgumentException("size of authPath needs to be equal to height of tree");
            }
        }
        this.authPath = list;
    }

    public List<XMSSNode> getAuthPath() {
        return this.authPath;
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    public WOTSPlusSignature getWOTSPlusSignature() {
        return this.wotsPlusSignature;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int treeDigestSize = this.params.getTreeDigestSize();
        byte[] bArr = new byte[(this.params.getWOTSPlus().getParams().getLen() * treeDigestSize) + (this.params.getHeight() * treeDigestSize)];
        int i = 0;
        for (byte[] bArr2 : this.wotsPlusSignature.toByteArray()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i);
            i += treeDigestSize;
        }
        for (int i2 = 0; i2 < this.authPath.size(); i2++) {
            XMSSUtil.copyBytesAtOffset(bArr, this.authPath.get(i2).getValue(), i);
            i += treeDigestSize;
        }
        return bArr;
    }
}
