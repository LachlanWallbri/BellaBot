package org.simpleframework.xml.core;

import org.simpleframework.xml.Version;
import org.simpleframework.xml.filter.Filter;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class Source implements Context {
    private TemplateEngine engine;
    private Filter filter;
    private Session session;
    private Strategy strategy;
    private Support support;

    public Source(Strategy strategy, Support support, Session session) {
        this.filter = new TemplateFilter(this, support);
        this.engine = new TemplateEngine(this.filter);
        this.strategy = strategy;
        this.support = support;
        this.session = session;
    }

    @Override // org.simpleframework.xml.core.Context
    public boolean isStrict() {
        return this.session.isStrict();
    }

    @Override // org.simpleframework.xml.core.Context
    public Session getSession() {
        return this.session;
    }

    @Override // org.simpleframework.xml.core.Context
    public Support getSupport() {
        return this.support;
    }

    @Override // org.simpleframework.xml.core.Context
    public Style getStyle() {
        return this.support.getStyle();
    }

    @Override // org.simpleframework.xml.core.Context
    public boolean isFloat(Class cls) throws Exception {
        Support support = this.support;
        return Support.isFloat(cls);
    }

    @Override // org.simpleframework.xml.core.Context
    public boolean isFloat(Type type) throws Exception {
        return isFloat(type.getType());
    }

    @Override // org.simpleframework.xml.core.Context
    public boolean isPrimitive(Class cls) throws Exception {
        return this.support.isPrimitive(cls);
    }

    @Override // org.simpleframework.xml.core.Context
    public boolean isPrimitive(Type type) throws Exception {
        return isPrimitive(type.getType());
    }

    @Override // org.simpleframework.xml.core.Context
    public Instance getInstance(Class cls) {
        return this.support.getInstance(cls);
    }

    @Override // org.simpleframework.xml.core.Context
    public Instance getInstance(Value value) {
        return this.support.getInstance(value);
    }

    @Override // org.simpleframework.xml.core.Context
    public String getName(Class cls) throws Exception {
        return this.support.getName(cls);
    }

    @Override // org.simpleframework.xml.core.Context
    public Version getVersion(Class cls) throws Exception {
        return getScanner(cls).getRevision();
    }

    private Scanner getScanner(Class cls) throws Exception {
        return this.support.getScanner(cls);
    }

    @Override // org.simpleframework.xml.core.Context
    public Decorator getDecorator(Class cls) throws Exception {
        return getScanner(cls).getDecorator();
    }

    @Override // org.simpleframework.xml.core.Context
    public Caller getCaller(Class cls) throws Exception {
        return getScanner(cls).getCaller(this);
    }

    @Override // org.simpleframework.xml.core.Context
    public Schema getSchema(Class cls) throws Exception {
        Scanner scanner = getScanner(cls);
        if (scanner == null) {
            throw new PersistenceException("Invalid schema class %s", cls);
        }
        return new ClassSchema(scanner, this);
    }

    @Override // org.simpleframework.xml.core.Context
    public Object getAttribute(Object obj) {
        return this.session.get(obj);
    }

    @Override // org.simpleframework.xml.core.Context
    public Value getOverride(Type type, InputNode inputNode) throws Exception {
        NodeMap<InputNode> attributes = inputNode.getAttributes();
        if (attributes == null) {
            throw new PersistenceException("No attributes for %s", inputNode);
        }
        return this.strategy.read(type, attributes, this.session);
    }

    @Override // org.simpleframework.xml.core.Context
    public boolean setOverride(Type type, Object obj, OutputNode outputNode) throws Exception {
        NodeMap<OutputNode> attributes = outputNode.getAttributes();
        if (attributes == null) {
            throw new PersistenceException("No attributes for %s", outputNode);
        }
        return this.strategy.write(type, obj, attributes, this.session);
    }

    @Override // org.simpleframework.xml.core.Context
    public Class getType(Type type, Object obj) {
        if (obj != null) {
            return obj.getClass();
        }
        return type.getType();
    }

    @Override // org.simpleframework.xml.core.Context
    public String getProperty(String str) {
        return this.engine.process(str);
    }
}
