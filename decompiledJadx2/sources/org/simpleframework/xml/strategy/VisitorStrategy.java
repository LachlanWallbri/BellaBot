package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes9.dex */
public class VisitorStrategy implements Strategy {
    private final Strategy strategy;
    private final Visitor visitor;

    public VisitorStrategy(Visitor visitor) {
        this(visitor, new TreeStrategy());
    }

    public VisitorStrategy(Visitor visitor, Strategy strategy) {
        this.strategy = strategy;
        this.visitor = visitor;
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public Value read(Type type, NodeMap<InputNode> nodeMap, Map map) throws Exception {
        Visitor visitor = this.visitor;
        if (visitor != null) {
            visitor.read(type, nodeMap);
        }
        return this.strategy.read(type, nodeMap, map);
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) throws Exception {
        boolean write = this.strategy.write(type, obj, nodeMap, map);
        Visitor visitor = this.visitor;
        if (visitor != null) {
            visitor.write(type, nodeMap);
        }
        return write;
    }
}
