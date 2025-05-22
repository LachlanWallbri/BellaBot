package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import javax.annotation.Nullable;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "ServerSideEncryptionConfiguration")
/* loaded from: classes7.dex */
public class SseConfiguration {

    @Element(name = "Rule", required = false)
    private SseConfigurationRule rule;

    public SseConfiguration(@Element(name = "Rule", required = false) @Nullable SseConfigurationRule sseConfigurationRule) {
        this.rule = sseConfigurationRule;
    }

    public static SseConfiguration newConfigWithSseS3Rule() {
        return new SseConfiguration(new SseConfigurationRule(SseAlgorithm.AES256, null));
    }

    public static SseConfiguration newConfigWithSseKmsRule(@Nullable String str) {
        return new SseConfiguration(new SseConfigurationRule(SseAlgorithm.AWS_KMS, str));
    }

    public SseConfigurationRule rule() {
        return this.rule;
    }
}
