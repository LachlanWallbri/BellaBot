package io.minio.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "QueueConfiguration", strict = false)
/* loaded from: classes7.dex */
public class QueueConfiguration extends NotificationCommonConfiguration {

    @Element(name = "Queue")
    private String queue;

    public String queue() {
        return this.queue;
    }

    public void setQueue(String str) {
        this.queue = str;
    }
}
