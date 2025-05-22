package com.google.cloud.dialogflow.v2beta1;

import com.google.api.pathtemplate.PathTemplate;
import com.google.api.resourcenames.ResourceName;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public class SessionName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/sessions/{session}");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String session;

    public String getProject() {
        return this.project;
    }

    public String getSession() {
        return this.session;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private SessionName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.session = (String) Preconditions.checkNotNull(builder.getSession());
    }

    /* renamed from: of */
    public static SessionName m601of(String str, String str2) {
        return newBuilder().setProject(str).setSession(str2).build();
    }

    public static String format(String str, String str2) {
        return newBuilder().setProject(str).setSession(str2).build().toString();
    }

    public static SessionName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "SessionName.parse: formattedString not in valid format");
        return m601of(validatedMatch.get("project"), validatedMatch.get("session"));
    }

    public static List<SessionName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<SessionName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (SessionName sessionName : list) {
            if (sessionName == null) {
                arrayList.add("");
            } else {
                arrayList.add(sessionName.toString());
            }
        }
        return arrayList;
    }

    public static boolean isParsableFrom(String str) {
        return PATH_TEMPLATE.matches(str);
    }

    @Override // com.google.api.resourcenames.ResourceName
    public Map<String, String> getFieldValuesMap() {
        if (this.fieldValuesMap == null) {
            synchronized (this) {
                if (this.fieldValuesMap == null) {
                    ImmutableMap.Builder builder = ImmutableMap.builder();
                    builder.put("project", this.project);
                    builder.put("session", this.session);
                    this.fieldValuesMap = builder.build();
                }
            }
        }
        return this.fieldValuesMap;
    }

    @Override // com.google.api.resourcenames.ResourceName
    public String getFieldValue(String str) {
        return getFieldValuesMap().get(str);
    }

    public String toString() {
        return PATH_TEMPLATE.instantiate("project", this.project, "session", this.session);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String project;
        private String session;

        public String getProject() {
            return this.project;
        }

        public String getSession() {
            return this.session;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setSession(String str) {
            this.session = str;
            return this;
        }

        private Builder() {
        }

        private Builder(SessionName sessionName) {
            this.project = sessionName.project;
            this.session = sessionName.session;
        }

        public SessionName build() {
            return new SessionName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionName)) {
            return false;
        }
        SessionName sessionName = (SessionName) obj;
        return this.project.equals(sessionName.project) && this.session.equals(sessionName.session);
    }

    public int hashCode() {
        return ((this.project.hashCode() ^ 1000003) * 1000003) ^ this.session.hashCode();
    }
}
