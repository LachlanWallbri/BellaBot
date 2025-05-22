package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "CloudFunctionConfiguration", strict = false)
/* loaded from: classes7.dex */
public class CloudFunctionConfiguration extends NotificationCommonConfiguration {

    @Element(name = "CloudFunction")
    private String cloudFunction;

    public String cloudFunction() {
        return this.cloudFunction;
    }

    public void setCloudFunction(String str) {
        this.cloudFunction = str;
    }
}
