package org.bouncycastle.tsp.cms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.Evidence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;
import org.bouncycastle.asn1.cms.TimeStampTokenEvidence;
import org.bouncycastle.asn1.cms.TimeStampedData;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.tsp.TimeStampToken;
import org.bouncycastle.util.io.Streams;

/* loaded from: classes9.dex */
public class CMSTimeStampedDataGenerator extends CMSTimeStampedGenerator {
    public CMSTimeStampedData generate(TimeStampToken timeStampToken) throws CMSException {
        return generate(timeStampToken, (InputStream) null);
    }

    public CMSTimeStampedData generate(TimeStampToken timeStampToken, InputStream inputStream) throws CMSException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (inputStream != null) {
            try {
                Streams.pipeAll(inputStream, byteArrayOutputStream);
            } catch (IOException e) {
                throw new CMSException("exception encapsulating content: " + e.getMessage(), e);
            }
        }
        return new CMSTimeStampedData(new ContentInfo(CMSObjectIdentifiers.timestampedData, new TimeStampedData(this.dataUri != null ? new DERIA5String(this.dataUri.toString()) : null, this.metaData, byteArrayOutputStream.size() != 0 ? new BEROctetString(byteArrayOutputStream.toByteArray()) : null, new Evidence(new TimeStampTokenEvidence(new TimeStampAndCRL(timeStampToken.toCMSSignedData().toASN1Structure()))))));
    }

    public CMSTimeStampedData generate(TimeStampToken timeStampToken, byte[] bArr) throws CMSException {
        return generate(timeStampToken, new ByteArrayInputStream(bArr));
    }
}
