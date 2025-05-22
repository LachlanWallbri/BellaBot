package io.minio.messages;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root(name = "Metadata")
@Convert(MetadataConverter.class)
/* loaded from: classes7.dex */
public class Metadata {
    Map<String, String> map;

    public Metadata() {
    }

    public Metadata(Map<String, String> map) {
        this.map = Collections.unmodifiableMap(map);
    }

    public Map<String, String> get() {
        return this.map;
    }

    /* loaded from: classes7.dex */
    public static class MetadataConverter implements Converter<Metadata> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.simpleframework.xml.convert.Converter
        public Metadata read(InputNode inputNode) throws Exception {
            HashMap hashMap = new HashMap();
            while (true) {
                InputNode next = inputNode.getNext();
                if (next == null) {
                    break;
                }
                hashMap.put(next.getName(), next.getValue());
            }
            if (hashMap.size() > 0) {
                return new Metadata(hashMap);
            }
            return null;
        }

        @Override // org.simpleframework.xml.convert.Converter
        public void write(OutputNode outputNode, Metadata metadata) throws Exception {
            for (Map.Entry<String, String> entry : metadata.get().entrySet()) {
                outputNode.getChild(entry.getKey()).setValue(entry.getValue());
            }
            outputNode.commit();
        }
    }
}
