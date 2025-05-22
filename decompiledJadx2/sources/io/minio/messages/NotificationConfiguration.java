package io.minio.messages;

import com.amazonaws.services.p048s3.internal.Constants;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Namespace(reference = Constants.XML_NAMESPACE)
@Root(name = "NotificationConfiguration", strict = false)
/* loaded from: classes7.dex */
public class NotificationConfiguration {

    @ElementList(inline = true, name = "CloudFunctionConfiguration", required = false)
    private List<CloudFunctionConfiguration> cloudFunctionConfigurationList;

    @ElementList(inline = true, name = "QueueConfiguration", required = false)
    private List<QueueConfiguration> queueConfigurationList;

    @ElementList(inline = true, name = "TopicConfiguration", required = false)
    private List<TopicConfiguration> topicConfigurationList;

    public List<CloudFunctionConfiguration> cloudFunctionConfigurationList() {
        List list = this.cloudFunctionConfigurationList;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }

    public void setCloudFunctionConfigurationList(List<CloudFunctionConfiguration> list) {
        this.cloudFunctionConfigurationList = Collections.unmodifiableList(list);
    }

    public List<QueueConfiguration> queueConfigurationList() {
        List list = this.queueConfigurationList;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }

    public void setQueueConfigurationList(List<QueueConfiguration> list) {
        this.queueConfigurationList = Collections.unmodifiableList(list);
    }

    public List<TopicConfiguration> topicConfigurationList() {
        List list = this.topicConfigurationList;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }

    public void setTopicConfigurationList(List<TopicConfiguration> list) {
        this.topicConfigurationList = Collections.unmodifiableList(list);
    }
}
