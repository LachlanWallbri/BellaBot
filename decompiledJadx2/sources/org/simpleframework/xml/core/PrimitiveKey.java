package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class PrimitiveKey implements Converter {
    private final Context context;
    private final Entry entry;
    private final PrimitiveFactory factory;
    private final Primitive root;
    private final Style style;
    private final Type type;

    public PrimitiveKey(Context context, Entry entry, Type type) {
        this.factory = new PrimitiveFactory(context, type);
        this.root = new Primitive(context, type);
        this.style = context.getStyle();
        this.context = context;
        this.entry = entry;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        if (!this.entry.isAttribute()) {
            return readElement(inputNode, key);
        }
        return readAttribute(inputNode, key);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        if (obj != null) {
            throw new PersistenceException("Can not read key of %s for %s", type, this.entry);
        }
        return read(inputNode);
    }

    private Object readAttribute(InputNode inputNode, String str) throws Exception {
        InputNode attribute = inputNode.getAttribute(this.style.getAttribute(str));
        if (attribute == null) {
            return null;
        }
        return this.root.read(attribute);
    }

    private Object readElement(InputNode inputNode, String str) throws Exception {
        InputNode next = inputNode.getNext(this.style.getElement(str));
        if (next == null) {
            return null;
        }
        return this.root.read(next);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        if (!this.entry.isAttribute()) {
            return validateElement(inputNode, key);
        }
        return validateAttribute(inputNode, key);
    }

    private boolean validateAttribute(InputNode inputNode, String str) throws Exception {
        InputNode attribute = inputNode.getAttribute(this.style.getElement(str));
        if (attribute == null) {
            return true;
        }
        return this.root.validate(attribute);
    }

    private boolean validateElement(InputNode inputNode, String str) throws Exception {
        InputNode next = inputNode.getNext(this.style.getElement(str));
        if (next == null) {
            return true;
        }
        return this.root.validate(next);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) throws Exception {
        if (!this.entry.isAttribute()) {
            writeElement(outputNode, obj);
        } else if (obj != null) {
            writeAttribute(outputNode, obj);
        }
    }

    private void writeElement(OutputNode outputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        OutputNode child = outputNode.getChild(this.style.getElement(key));
        if (obj == null || isOverridden(child, obj)) {
            return;
        }
        this.root.write(child, obj);
    }

    private void writeAttribute(OutputNode outputNode, Object obj) throws Exception {
        Class type = this.type.getType();
        String text = this.factory.getText(obj);
        String key = this.entry.getKey();
        if (key == null) {
            key = this.context.getName(type);
        }
        String attribute = this.style.getAttribute(key);
        if (text != null) {
            outputNode.setAttribute(attribute, text);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj) throws Exception {
        return this.factory.setOverride(this.type, obj, outputNode);
    }
}
