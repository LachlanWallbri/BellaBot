package com.aliyun.alink.linksdk.tmp.devicemodel;

import android.text.TextUtils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceModel {
    private List<Event> events;
    private List<String> extend;

    /* renamed from: id */
    private String f1035id;
    private String link;
    private Profile profile;
    private List<Property> properties;
    private String schema;
    private List<Service> services;

    public String getId() {
        return this.f1035id;
    }

    public void setId(String str) {
        this.f1035id = str;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String str) {
        this.schema = str;
    }

    @Deprecated
    public List<String> getExtend() {
        return this.extend;
    }

    @Deprecated
    public void setExtend(List<String> list) {
        this.extend = list;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> list) {
        this.properties = list;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> list) {
        this.events = list;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void setServices(List<Service> list) {
        this.services = list;
    }

    public String getServiceMethod(String str) {
        List<Service> list;
        if (!TextUtils.isEmpty(str) && (list = this.services) != null && !list.isEmpty()) {
            for (Service service : this.services) {
                if (str.equalsIgnoreCase(service.getIdentifier())) {
                    return service.getMethod();
                }
            }
        }
        return null;
    }

    public String getEventMethod(String str) {
        List<Event> list;
        if (!TextUtils.isEmpty(str) && (list = this.events) != null && !list.isEmpty()) {
            for (Event event : this.events) {
                if (str.equalsIgnoreCase(event.getIdentifier())) {
                    return event.getMethod();
                }
            }
        }
        return null;
    }

    public Event getEvent(String str) {
        List<Event> list;
        if (!TextUtils.isEmpty(str) && (list = this.events) != null && !list.isEmpty()) {
            for (Event event : this.events) {
                if (str.equalsIgnoreCase(event.getIdentifier())) {
                    return event;
                }
            }
        }
        return null;
    }
}
