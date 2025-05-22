package com.amazonaws.util;

import java.io.IOException;
import java.io.InputStream;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/* loaded from: classes.dex */
public class XmlUtils {
    public static XMLReader parse(InputStream inputStream, ContentHandler contentHandler) throws SAXException, IOException {
        XMLReader createXMLReader = XMLReaderFactory.createXMLReader();
        createXMLReader.setContentHandler(contentHandler);
        createXMLReader.parse(new InputSource(inputStream));
        inputStream.close();
        return createXMLReader;
    }
}
