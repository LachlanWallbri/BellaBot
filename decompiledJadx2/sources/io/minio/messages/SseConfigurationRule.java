package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "Rule")
/* loaded from: classes7.dex */
public class SseConfigurationRule {

    @Element(name = "KMSMasterKeyID", required = false)
    @Path("ApplyServerSideEncryptionByDefault")
    private String kmsMasterKeyId;

    @Element(name = "SSEAlgorithm")
    @Path("ApplyServerSideEncryptionByDefault")
    private SseAlgorithm sseAlgorithm;

    public SseConfigurationRule(@Element(name = "SSEAlgorithm") @Nonnull SseAlgorithm sseAlgorithm, @Element(name = "KMSMasterKeyID", required = false) @Nullable String str) {
        this.sseAlgorithm = (SseAlgorithm) Objects.requireNonNull(sseAlgorithm, "SSE Algorithm must be provided");
        this.kmsMasterKeyId = str;
    }

    public String kmsMasterKeyId() {
        return this.kmsMasterKeyId;
    }

    public SseAlgorithm sseAlgorithm() {
        return this.sseAlgorithm;
    }
}
