package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root(name = "SSEAlgorithm")
@Convert(SseAlgorithmConverter.class)
/* loaded from: classes7.dex */
public enum SseAlgorithm {
    AES256(com.alibaba.sdk.android.oss.model.ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION),
    AWS_KMS("aws:kms");

    private final String value;

    SseAlgorithm(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static SseAlgorithm fromString(String str) {
        for (SseAlgorithm sseAlgorithm : values()) {
            if (str.equals(sseAlgorithm.value)) {
                return sseAlgorithm;
            }
        }
        throw new IllegalArgumentException("unknown SSE algorithm '" + str + "'");
    }

    /* loaded from: classes7.dex */
    public static class SseAlgorithmConverter implements Converter<SseAlgorithm> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.simpleframework.xml.convert.Converter
        public SseAlgorithm read(InputNode inputNode) throws Exception {
            return SseAlgorithm.fromString(inputNode.getValue());
        }

        @Override // org.simpleframework.xml.convert.Converter
        public void write(OutputNode outputNode, SseAlgorithm sseAlgorithm) throws Exception {
            outputNode.setValue(sseAlgorithm.toString());
        }
    }
}
