package com.amazonaws.services.p048s3.model.transform;

import com.amazonaws.services.p048s3.internal.Constants;
import com.amazonaws.services.p048s3.internal.XmlWriter;
import com.amazonaws.services.p048s3.model.RequestPaymentConfiguration;

/* loaded from: classes.dex */
public class RequestPaymentConfigurationXmlFactory {
    public byte[] convertToXmlByteArray(RequestPaymentConfiguration requestPaymentConfiguration) {
        XmlWriter xmlWriter = new XmlWriter();
        xmlWriter.start("RequestPaymentConfiguration", "xmlns", Constants.XML_NAMESPACE);
        RequestPaymentConfiguration.Payer payer = requestPaymentConfiguration.getPayer();
        if (payer != null) {
            XmlWriter start = xmlWriter.start("Payer");
            start.value(payer.toString());
            start.end();
        }
        xmlWriter.end();
        return xmlWriter.getBytes();
    }
}
