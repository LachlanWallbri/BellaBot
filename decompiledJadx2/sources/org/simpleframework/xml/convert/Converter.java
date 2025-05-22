package org.simpleframework.xml.convert;

import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes9.dex */
public interface Converter<T> {
    T read(InputNode inputNode) throws Exception;

    void write(OutputNode outputNode, T t) throws Exception;
}
