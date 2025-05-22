package org.simpleframework.xml.stream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/* loaded from: classes9.dex */
class DocumentReader implements EventReader {
    private static final String RESERVED = "xml";
    private EventNode peek;
    private NodeExtractor queue;
    private NodeStack stack = new NodeStack();

    public DocumentReader(Document document) {
        this.queue = new NodeExtractor(document);
        this.stack.push(document);
    }

    @Override // org.simpleframework.xml.stream.EventReader
    public EventNode peek() throws Exception {
        if (this.peek == null) {
            this.peek = next();
        }
        return this.peek;
    }

    @Override // org.simpleframework.xml.stream.EventReader
    public EventNode next() throws Exception {
        EventNode eventNode = this.peek;
        if (eventNode == null) {
            return read();
        }
        this.peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        org.w3c.dom.Node peek = this.queue.peek();
        if (peek == null) {
            return end();
        }
        return read(peek);
    }

    private EventNode read(org.w3c.dom.Node node) throws Exception {
        org.w3c.dom.Node parentNode = node.getParentNode();
        org.w3c.dom.Node pVar = this.stack.top();
        if (parentNode != pVar) {
            if (pVar != null) {
                this.stack.pop();
            }
            return end();
        }
        if (node != null) {
            this.queue.poll();
        }
        return convert(node);
    }

    private EventNode convert(org.w3c.dom.Node node) throws Exception {
        if (node.getNodeType() == 1) {
            if (node != null) {
                this.stack.push(node);
            }
            return start(node);
        }
        return text(node);
    }

    private Start start(org.w3c.dom.Node node) {
        Start start = new Start(node);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) {
        NamedNodeMap attributes = start.getAttributes();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            Entry attribute = attribute(attributes.item(i));
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(org.w3c.dom.Node node) {
        return new Entry(node);
    }

    private Text text(org.w3c.dom.Node node) {
        return new Text(node);
    }

    private End end() {
        return new End();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Entry extends EventAttribute {
        private final org.w3c.dom.Node node;

        public Entry(org.w3c.dom.Node node) {
            this.node = node;
        }

        @Override // org.simpleframework.xml.stream.Attribute
        public String getName() {
            return this.node.getLocalName();
        }

        @Override // org.simpleframework.xml.stream.Attribute
        public String getValue() {
            return this.node.getNodeValue();
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public String getPrefix() {
            return this.node.getPrefix();
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public String getReference() {
            return this.node.getNamespaceURI();
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public boolean isReserved() {
            String prefix = getPrefix();
            String name = getName();
            if (prefix != null) {
                return prefix.startsWith(DocumentReader.RESERVED);
            }
            return name.startsWith(DocumentReader.RESERVED);
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public Object getSource() {
            return this.node;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Start extends EventElement {
        private final Element element;

        public Start(org.w3c.dom.Node node) {
            this.element = (Element) node;
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getName() {
            return this.element.getLocalName();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getPrefix() {
            return this.element.getPrefix();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getReference() {
            return this.element.getNamespaceURI();
        }

        public NamedNodeMap getAttributes() {
            return this.element.getAttributes();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return this.element;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Text extends EventToken {
        private final org.w3c.dom.Node node;

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public boolean isText() {
            return true;
        }

        public Text(org.w3c.dom.Node node) {
            this.node = node;
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public String getValue() {
            return this.node.getNodeValue();
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return this.node;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class End extends EventToken {
        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
