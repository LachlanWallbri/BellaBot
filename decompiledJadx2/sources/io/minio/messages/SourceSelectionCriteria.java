package io.minio.messages;

import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "SourceSelectionCriteria")
/* loaded from: classes7.dex */
public class SourceSelectionCriteria {

    @Element(name = "SseKmsEncryptedObjects", required = false)
    private SseKmsEncryptedObjects sseKmsEncryptedObjects;

    public SourceSelectionCriteria(@Element(name = "SseKmsEncryptedObjects", required = false) @Nullable SseKmsEncryptedObjects sseKmsEncryptedObjects) {
        this.sseKmsEncryptedObjects = sseKmsEncryptedObjects;
    }

    public SseKmsEncryptedObjects sseKmsEncryptedObjects() {
        return this.sseKmsEncryptedObjects;
    }
}
