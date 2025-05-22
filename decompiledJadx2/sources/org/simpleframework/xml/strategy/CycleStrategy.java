package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

/* loaded from: classes9.dex */
public class CycleStrategy implements Strategy {
    private final Contract contract;
    private final ReadState read;
    private final WriteState write;

    public CycleStrategy() {
        this("id", Name.REFER);
    }

    public CycleStrategy(String str, String str2) {
        this(str, str2, Name.LABEL);
    }

    public CycleStrategy(String str, String str2, String str3) {
        this(str, str2, str3, Name.LENGTH);
    }

    public CycleStrategy(String str, String str2, String str3, String str4) {
        this.contract = new Contract(str, str2, str3, str4);
        this.write = new WriteState(this.contract);
        this.read = new ReadState(this.contract);
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public Value read(Type type, NodeMap nodeMap, Map map) throws Exception {
        ReadGraph find = this.read.find(map);
        if (find != null) {
            return find.read(type, nodeMap);
        }
        return null;
    }

    @Override // org.simpleframework.xml.strategy.Strategy
    public boolean write(Type type, Object obj, NodeMap nodeMap, Map map) {
        WriteGraph find = this.write.find(map);
        if (find != null) {
            return find.write(type, obj, nodeMap);
        }
        return false;
    }
}
