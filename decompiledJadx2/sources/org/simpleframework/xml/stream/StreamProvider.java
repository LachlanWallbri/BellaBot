package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;

/* loaded from: classes9.dex */
public class StreamProvider implements Provider {
    private final XMLInputFactory factory;

    public StreamProvider(XMLInputFactory xMLInputFactory) {
        this.factory = xMLInputFactory;
    }

    public StreamProvider() {
        this.factory = XMLInputFactory.newInstance();
        this.factory.setProperty("javax.xml.stream.supportDTD", false);
        this.factory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
        this.factory.setXMLResolver(new XMLResolver() { // from class: org.simpleframework.xml.stream.StreamProvider.1
            public Object resolveEntity(String str, String str2, String str3, String str4) throws XMLStreamException {
                throw new XMLStreamException("Entity resolution disabled for default stream provider constructor.");
            }
        });
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(InputStream inputStream) throws Exception {
        return provide(this.factory.createXMLEventReader(inputStream));
    }

    @Override // org.simpleframework.xml.stream.Provider
    public EventReader provide(Reader reader) throws Exception {
        return provide(this.factory.createXMLEventReader(reader));
    }

    private EventReader provide(XMLEventReader xMLEventReader) throws Exception {
        return new StreamReader(xMLEventReader);
    }
}
