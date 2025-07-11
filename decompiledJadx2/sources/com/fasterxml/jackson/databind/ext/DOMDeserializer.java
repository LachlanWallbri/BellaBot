package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public abstract class DOMDeserializer<T> extends FromStringDeserializer<T> {
    private static final DocumentBuilderFactory DEFAULT_PARSER_FACTORY;
    private static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public abstract T _deserialize(String str, DeserializationContext deserializationContext);

    static {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setNamespaceAware(true);
        newInstance.setExpandEntityReferences(false);
        try {
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        } catch (Error | ParserConfigurationException unused) {
        }
        try {
            newInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        } catch (Throwable unused2) {
        }
        try {
            newInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        } catch (Throwable unused3) {
        }
        DEFAULT_PARSER_FACTORY = newInstance;
    }

    protected DOMDeserializer(Class<T> cls) {
        super(cls);
    }

    protected final Document parse(String str) throws IllegalArgumentException {
        try {
            return documentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JSON String as XML: " + e.getMessage(), e);
        }
    }

    protected DocumentBuilder documentBuilder() throws ParserConfigurationException {
        return DEFAULT_PARSER_FACTORY.newDocumentBuilder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public static class NodeDeserializer extends DOMDeserializer<Node> {
        private static final long serialVersionUID = 1;

        public NodeDeserializer() {
            super(Node.class);
        }

        @Override // com.fasterxml.jackson.databind.ext.DOMDeserializer, com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public Node _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return parse(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public static class DocumentDeserializer extends DOMDeserializer<Document> {
        private static final long serialVersionUID = 1;

        public DocumentDeserializer() {
            super(Document.class);
        }

        @Override // com.fasterxml.jackson.databind.ext.DOMDeserializer, com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
        public Document _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return parse(str);
        }
    }
}
