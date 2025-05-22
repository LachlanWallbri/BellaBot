package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;

/* loaded from: classes9.dex */
public class DocumentProvider implements Provider {
    private final DocumentBuilderFactory factory;

    public DocumentProvider() {
        this(safeDocumentBuilderFactory());
    }

    public DocumentProvider(DocumentBuilderFactory documentBuilderFactory) {
        this.factory = documentBuilderFactory;
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(InputStream inputStream) throws Exception {
        return provide(new InputSource(inputStream));
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(Reader reader) throws Exception {
        return provide(new InputSource(reader));
    }

    private EventReader provide(InputSource inputSource) throws Exception {
        return new DocumentReader(this.factory.newDocumentBuilder().parse(inputSource));
    }

    private static void setFeature(DocumentBuilderFactory documentBuilderFactory, String str, boolean z) {
        try {
            documentBuilderFactory.setFeature(str, z);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("The required security feature is not supported by your XML parser: " + str, e);
        }
    }

    public static DocumentBuilderFactory safeDocumentBuilderFactory() {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        setFeature(newInstance, "http://apache.org/xml/features/disallow-doctype-decl", true);
        setFeature(newInstance, "http://xml.org/sax/features/external-general-entities", false);
        setFeature(newInstance, "http://xml.org/sax/features/external-parameter-entities", false);
        setFeature(newInstance, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        newInstance.setXIncludeAware(false);
        newInstance.setExpandEntityReferences(false);
        newInstance.setNamespaceAware(true);
        return newInstance;
    }
}
