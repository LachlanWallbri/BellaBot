package org.simpleframework.xml.core;

import java.util.Map;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

/* loaded from: classes9.dex */
class CompositeMap implements Converter {
    private final Entry entry;
    private final MapFactory factory;
    private final Converter key;
    private final Style style;
    private final Converter value;

    public CompositeMap(Context context, Entry entry, Type type) throws Exception {
        this.factory = new MapFactory(context, type);
        this.value = entry.getValue(context);
        this.key = entry.getKey(context);
        this.style = context.getStyle();
        this.entry = entry;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) throws Exception {
        Instance mapFactory = this.factory.getInstance(inputNode);
        Object instance = mapFactory.getInstance();
        return !mapFactory.isReference() ? populate(inputNode, instance) : instance;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) throws Exception {
        Instance mapFactory = this.factory.getInstance(inputNode);
        if (mapFactory.isReference()) {
            return mapFactory.getInstance();
        }
        mapFactory.setInstance(obj);
        return obj != null ? populate(inputNode, obj) : obj;
    }

    private Object populate(InputNode inputNode, Object obj) throws Exception {
        Map map = (Map) obj;
        while (true) {
            InputNode next = inputNode.getNext();
            if (next == null) {
                return map;
            }
            map.put(this.key.read(next), this.value.read(next));
        }
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        Instance mapFactory = this.factory.getInstance(inputNode);
        if (mapFactory.isReference()) {
            return true;
        }
        mapFactory.setInstance(null);
        return validate(inputNode, mapFactory.getType());
    }

    private boolean validate(InputNode inputNode, Class cls) throws Exception {
        InputNode next;
        do {
            next = inputNode.getNext();
            if (next == null) {
                return true;
            }
            if (!this.key.validate(next)) {
                return false;
            }
        } while (this.value.validate(next));
        return false;
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) throws Exception {
        Map map = (Map) obj;
        for (Object obj2 : map.keySet()) {
            OutputNode child = outputNode.getChild(this.style.getElement(this.entry.getEntry()));
            Object obj3 = map.get(obj2);
            this.key.write(child, obj2);
            this.value.write(child, obj3);
        }
    }
}
