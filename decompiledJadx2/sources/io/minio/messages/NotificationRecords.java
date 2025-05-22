package io.minio.messages;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* loaded from: classes7.dex */
public class NotificationRecords {

    @JsonProperty("Records")
    private List<Event> events;

    public List<Event> events() {
        return this.events;
    }
}
