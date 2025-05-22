package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.InputNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public interface Repeater extends Converter {
    @Override // org.simpleframework.xml.core.Converter
    Object read(InputNode inputNode, Object obj) throws Exception;
}
