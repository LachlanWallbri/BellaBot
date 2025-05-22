package io.minio.messages;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/* loaded from: classes7.dex */
public class PrefixConverter implements Converter<String> {
    @Override // org.simpleframework.xml.convert.Converter
    public String read(InputNode inputNode) throws Exception {
        String value = inputNode.getValue();
        return value != null ? value : "";
    }

    @Override // org.simpleframework.xml.convert.Converter
    public void write(OutputNode outputNode, String str) throws Exception {
        outputNode.setValue(str);
    }
}
