package org.simpleframework.xml.stream;

import java.util.Iterator;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/* loaded from: classes9.dex */
class StreamReader implements EventReader {
    private EventNode peek;
    private XMLEventReader reader;

    public StreamReader(XMLEventReader xMLEventReader) {
        this.reader = xMLEventReader;
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
        XMLEvent nextEvent = this.reader.nextEvent();
        if (nextEvent.isEndDocument()) {
            return null;
        }
        if (nextEvent.isStartElement()) {
            return start(nextEvent);
        }
        if (nextEvent.isCharacters()) {
            return text(nextEvent);
        }
        if (nextEvent.isEndElement()) {
            return end();
        }
        return read();
    }

    private Start start(XMLEvent xMLEvent) {
        Start start = new Start(xMLEvent);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) {
        Iterator<javax.xml.stream.events.Attribute> attributes = start.getAttributes();
        while (attributes.hasNext()) {
            Entry attribute = attribute(attributes.next());
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(javax.xml.stream.events.Attribute attribute) {
        return new Entry(attribute);
    }

    private Text text(XMLEvent xMLEvent) {
        return new Text(xMLEvent);
    }

    private End end() {
        return new End();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Entry extends EventAttribute {
        private final javax.xml.stream.events.Attribute entry;

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public boolean isReserved() {
            return false;
        }

        public Entry(javax.xml.stream.events.Attribute attribute) {
            this.entry = attribute;
        }

        @Override // org.simpleframework.xml.stream.Attribute
        public String getName() {
            return this.entry.getName().getLocalPart();
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public String getPrefix() {
            return this.entry.getName().getPrefix();
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public String getReference() {
            return this.entry.getName().getNamespaceURI();
        }

        @Override // org.simpleframework.xml.stream.Attribute
        public String getValue() {
            return this.entry.getValue();
        }

        @Override // org.simpleframework.xml.stream.EventAttribute, org.simpleframework.xml.stream.Attribute
        public Object getSource() {
            return this.entry;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class Start extends EventElement {
        private final StartElement element;
        private final Location location;

        public Start(XMLEvent xMLEvent) {
            this.element = xMLEvent.asStartElement();
            this.location = xMLEvent.getLocation();
        }

        @Override // org.simpleframework.xml.stream.EventElement, org.simpleframework.xml.stream.EventNode
        public int getLine() {
            return this.location.getLineNumber();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getName() {
            return this.element.getName().getLocalPart();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getPrefix() {
            return this.element.getName().getPrefix();
        }

        @Override // org.simpleframework.xml.stream.EventNode
        public String getReference() {
            return this.element.getName().getNamespaceURI();
        }

        public Iterator<javax.xml.stream.events.Attribute> getAttributes() {
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
        private final Characters text;

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public boolean isText() {
            return true;
        }

        public Text(XMLEvent xMLEvent) {
            this.text = xMLEvent.asCharacters();
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public String getValue() {
            return this.text.getData();
        }

        @Override // org.simpleframework.xml.stream.EventToken, org.simpleframework.xml.stream.EventNode
        public Object getSource() {
            return this.text;
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
