package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes9.dex */
class Primitive implements Converter {
    private final Context context;
    private final String empty;
    private final Class expect;
    private final PrimitiveFactory factory;
    private final Type type;

    public Primitive(Context context, Type type) {
        this(context, type, null);
    }

    public Primitive(Context context, Type type, String str) {
        this.factory = new PrimitiveFactory(context, type);
        this.expect = type.getType();
        this.context = context;
        this.empty = str;
        this.type = type;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            return readElement(inputNode);
        }
        return read(inputNode, this.expect);
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) throws Exception {
        if (obj != null) {
            throw new PersistenceException("Can not read existing %s for %s", this.expect, this.type);
        }
        return read(inputNode);
    }

    public Object read(InputNode inputNode, Class cls) throws Exception {
        String value = inputNode.getValue();
        if (value == null) {
            return null;
        }
        String str = this.empty;
        if (str != null && value.equals(str)) {
            return this.empty;
        }
        return readTemplate(value, cls);
    }

    private Object readElement(InputNode inputNode) throws Exception {
        Instance primitiveFactory = this.factory.getInstance(inputNode);
        if (!primitiveFactory.isReference()) {
            return readElement(inputNode, primitiveFactory);
        }
        return primitiveFactory.getInstance();
    }

    private Object readElement(InputNode inputNode, Instance instance) throws Exception {
        Object read = read(inputNode, this.expect);
        if (instance != null) {
            instance.setInstance(read);
        }
        return read;
    }

    private Object readTemplate(String str, Class cls) throws Exception {
        String property = this.context.getProperty(str);
        if (property != null) {
            return this.factory.getInstance(property, cls);
        }
        return null;
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        if (inputNode.isElement()) {
            validateElement(inputNode);
            return true;
        }
        inputNode.getValue();
        return true;
    }

    private boolean validateElement(InputNode inputNode) throws Exception {
        Instance primitiveFactory = this.factory.getInstance(inputNode);
        if (primitiveFactory.isReference()) {
            return true;
        }
        primitiveFactory.setInstance(null);
        return true;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) throws Exception {
        String text = this.factory.getText(obj);
        if (text != null) {
            outputNode.setValue(text);
        }
    }
}
