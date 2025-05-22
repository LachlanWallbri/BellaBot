package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root(name = MoveError.LEVEL_EVENT)
@Convert(EventTypeConverter.class)
/* loaded from: classes7.dex */
public enum EventType {
    OBJECT_CREATED_ANY("s3:ObjectCreated:*"),
    OBJECT_CREATED_PUT("s3:ObjectCreated:Put"),
    OBJECT_CREATED_POST("s3:ObjectCreated:Post"),
    OBJECT_CREATED_COPY("s3:ObjectCreated:Copy"),
    OBJECT_CREATED_COMPLETE_MULTIPART_UPLOAD("s3:ObjectCreated:CompleteMultipartUpload"),
    OBJECT_ACCESSED_GET("s3:ObjectAccessed:Get"),
    OBJECT_ACCESSED_HEAD("s3:ObjectAccessed:Head"),
    OBJECT_ACCESSED_ANY("s3:ObjectAccessed:*"),
    OBJECT_REMOVED_ANY("s3:ObjectRemoved:*"),
    OBJECT_REMOVED_DELETE("s3:ObjectRemoved:Delete"),
    OBJECT_REMOVED_DELETED_MARKER_CREATED("s3:ObjectRemoved:DeleteMarkerCreated"),
    REDUCED_REDUNDANCY_LOST_OBJECT("s3:ReducedRedundancyLostObject");

    private final String value;

    EventType(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static EventType fromString(String str) {
        String str2 = "s3:" + str;
        for (EventType eventType : values()) {
            if (str.equals(eventType.value) || str2.equals(eventType.value)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("unknown event '" + str + "'");
    }

    /* loaded from: classes7.dex */
    public static class EventTypeConverter implements Converter<EventType> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.simpleframework.xml.convert.Converter
        public EventType read(InputNode inputNode) throws Exception {
            return EventType.fromString(inputNode.getValue());
        }

        @Override // org.simpleframework.xml.convert.Converter
        public void write(OutputNode outputNode, EventType eventType) throws Exception {
            outputNode.setValue(eventType.toString());
        }
    }
}
