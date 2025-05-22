package io.minio.messages;

import com.amazonaws.services.p048s3.model.BucketLifecycleConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root(name = "Status")
@Convert(StatusConverter.class)
/* loaded from: classes7.dex */
public enum Status {
    DISABLED(BucketLifecycleConfiguration.DISABLED),
    ENABLED("Enabled");

    private final String value;

    Status(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static Status fromString(String str) {
        for (Status status : values()) {
            if (str.equals(status.value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status '" + str + "'");
    }

    /* loaded from: classes7.dex */
    public static class StatusConverter implements Converter<Status> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.simpleframework.xml.convert.Converter
        public Status read(InputNode inputNode) throws Exception {
            return Status.fromString(inputNode.getValue());
        }

        @Override // org.simpleframework.xml.convert.Converter
        public void write(OutputNode outputNode, Status status) throws Exception {
            outputNode.setValue(status.toString());
        }
    }
}
