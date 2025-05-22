package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "TopicConfiguration", strict = false)
/* loaded from: classes7.dex */
public class TopicConfiguration extends NotificationCommonConfiguration {

    @Element(name = "Topic")
    private String topic;

    public String topic() {
        return this.topic;
    }

    public void setTopic(String str) {
        this.topic = str;
    }
}
