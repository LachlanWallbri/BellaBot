package io.minio.messages;

import com.amazonaws.auth.policy.internal.JsonDocumentFields;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

/* loaded from: classes7.dex */
public abstract class NotificationCommonConfiguration {

    @ElementList(inline = true, name = MoveError.LEVEL_EVENT)
    private List<EventType> events;

    @Element(name = "Filter", required = false)
    private Filter filter;

    /* renamed from: id */
    @Element(name = JsonDocumentFields.POLICY_ID, required = false)
    private String f8441id;

    /* renamed from: id */
    public String m3926id() {
        return this.f8441id;
    }

    public void setId(String str) {
        this.f8441id = str;
    }

    public List<EventType> events() {
        List list = this.events;
        if (list == null) {
            list = new LinkedList();
        }
        return Collections.unmodifiableList(list);
    }

    public void setEvents(List<EventType> list) {
        this.events = Collections.unmodifiableList(list);
    }

    public void setPrefixRule(String str) throws IllegalArgumentException {
        if (this.filter == null) {
            this.filter = new Filter();
        }
        this.filter.setPrefixRule(str);
    }

    public void setSuffixRule(String str) throws IllegalArgumentException {
        if (this.filter == null) {
            this.filter = new Filter();
        }
        this.filter.setSuffixRule(str);
    }

    public List<FilterRule> filterRuleList() {
        Filter filter = this.filter;
        return Collections.unmodifiableList(filter == null ? new LinkedList<>() : filter.filterRuleList());
    }
}
