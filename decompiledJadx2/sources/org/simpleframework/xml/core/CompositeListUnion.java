package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

/* loaded from: classes9.dex */
class CompositeListUnion implements Repeater {
    private final Context context;
    private final LabelMap elements;
    private final Group group;
    private final Expression path;
    private final Style style;
    private final Type type;

    public CompositeListUnion(Context context, Group group, Expression expression, Type type) throws Exception {
        this.elements = group.getElements();
        this.style = context.getStyle();
        this.context = context;
        this.group = group;
        this.type = type;
        this.path = expression;
    }

    @Override // org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode) throws Exception {
        if (this.group.getText() == null) {
            return readElement(inputNode);
        }
        return readText(inputNode);
    }

    private Object readElement(InputNode inputNode) throws Exception {
        return this.elements.get(this.path.getElement(inputNode.getName())).getConverter(this.context).read(inputNode);
    }

    private Object readText(InputNode inputNode) throws Exception {
        return this.group.getText().getConverter(this.context).read(inputNode);
    }

    @Override // org.simpleframework.xml.core.Repeater, org.simpleframework.xml.core.Converter
    public Object read(InputNode inputNode, Object obj) throws Exception {
        return this.group.getText() != null ? readText(inputNode, obj) : readElement(inputNode, obj);
    }

    private Object readElement(InputNode inputNode, Object obj) throws Exception {
        return this.elements.get(this.path.getElement(inputNode.getName())).getConverter(this.context).read(inputNode, obj);
    }

    private Object readText(InputNode inputNode, Object obj) throws Exception {
        return this.group.getText().getConverter(this.context).read(inputNode.getParent(), obj);
    }

    @Override // org.simpleframework.xml.core.Converter
    public boolean validate(InputNode inputNode) throws Exception {
        return this.elements.get(this.path.getElement(inputNode.getName())).getConverter(this.context).validate(inputNode);
    }

    @Override // org.simpleframework.xml.core.Converter
    public void write(OutputNode outputNode, Object obj) throws Exception {
        Collection collection = (Collection) obj;
        if (this.group.isInline()) {
            if (!collection.isEmpty()) {
                write(outputNode, collection);
                return;
            } else {
                if (outputNode.isCommitted()) {
                    return;
                }
                outputNode.remove();
                return;
            }
        }
        write(outputNode, collection);
    }

    private void write(OutputNode outputNode, Collection collection) throws Exception {
        for (Object obj : collection) {
            if (obj != null) {
                Class<?> cls = obj.getClass();
                Label label = this.group.getLabel(cls);
                if (label == null) {
                    throw new UnionException("Entry of %s not declared in %s with annotation %s", cls, this.type, this.group);
                }
                write(outputNode, obj, label);
            }
        }
    }

    private void write(OutputNode outputNode, Object obj, Label label) throws Exception {
        Converter converter = label.getConverter(this.context);
        Set singleton = Collections.singleton(obj);
        if (!label.isInline()) {
            String element = this.style.getElement(label.getName());
            if (!outputNode.isCommitted()) {
                outputNode.setName(element);
            }
        }
        converter.write(outputNode, singleton);
    }
}
