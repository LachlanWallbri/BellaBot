package org.bouncycastle.tsp.cms;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.cms.Attributes;
import org.bouncycastle.asn1.cms.MetaData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes9.dex */
class MetaDataUtil {
    private final MetaData metaData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetaDataUtil(MetaData metaData) {
        this.metaData = metaData;
    }

    private String convertString(ASN1String aSN1String) {
        if (aSN1String != null) {
            return aSN1String.toString();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getFileName() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return convertString(metaData.getFileName());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getMediaType() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return convertString(metaData.getMediaType());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attributes getOtherMetaData() {
        MetaData metaData = this.metaData;
        if (metaData != null) {
            return metaData.getOtherMetaData();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws CMSException {
        MetaData metaData = this.metaData;
        if (metaData == null || !metaData.isHashProtected()) {
            return;
        }
        try {
            digestCalculator.getOutputStream().write(this.metaData.getEncoded(ASN1Encoding.DER));
        } catch (IOException e) {
            throw new CMSException("unable to initialise calculator from metaData: " + e.getMessage(), e);
        }
    }
}
