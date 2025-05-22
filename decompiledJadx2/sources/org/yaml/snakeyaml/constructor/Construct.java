package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.nodes.Node;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public interface Construct {
    Object construct(Node node);

    void construct2ndStep(Node node, Object obj);
}
