package com.google.cloud.dialogflow.p049v2;

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
/* loaded from: classes2.dex */
public class SessionEntityTypeName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/sessions/{session}/entityTypes/{entity_type}");
    private final String entityType;
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String session;

    public String getProject() {
        return this.project;
    }

    public String getSession() {
        return this.session;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private SessionEntityTypeName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.session = (String) Preconditions.checkNotNull(builder.getSession());
        this.entityType = (String) Preconditions.checkNotNull(builder.getEntityType());
    }

    /* renamed from: of */
    public static SessionEntityTypeName m588of(String str, String str2, String str3) {
        return newBuilder().setProject(str).setSession(str2).setEntityType(str3).build();
    }

    public static String format(String str, String str2, String str3) {
        return newBuilder().setProject(str).setSession(str2).setEntityType(str3).build().toString();
    }

    public static SessionEntityTypeName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "SessionEntityTypeName.parse: formattedString not in valid format");
        return m588of(validatedMatch.get("project"), validatedMatch.get("session"), validatedMatch.get("entity_type"));
    }

    public static List<SessionEntityTypeName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<SessionEntityTypeName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (SessionEntityTypeName sessionEntityTypeName : list) {
            if (sessionEntityTypeName == null) {
                arrayList.add("");
            } else {
                arrayList.add(sessionEntityTypeName.toString());
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
                    builder.put("entityType", this.entityType);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "session", this.session, "entity_type", this.entityType);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private String entityType;
        private String project;
        private String session;

        public String getProject() {
            return this.project;
        }

        public String getSession() {
            return this.session;
        }

        public String getEntityType() {
            return this.entityType;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setSession(String str) {
            this.session = str;
            return this;
        }

        public Builder setEntityType(String str) {
            this.entityType = str;
            return this;
        }

        private Builder() {
        }

        private Builder(SessionEntityTypeName sessionEntityTypeName) {
            this.project = sessionEntityTypeName.project;
            this.session = sessionEntityTypeName.session;
            this.entityType = sessionEntityTypeName.entityType;
        }

        public SessionEntityTypeName build() {
            return new SessionEntityTypeName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionEntityTypeName)) {
            return false;
        }
        SessionEntityTypeName sessionEntityTypeName = (SessionEntityTypeName) obj;
        return this.project.equals(sessionEntityTypeName.project) && this.session.equals(sessionEntityTypeName.session) && this.entityType.equals(sessionEntityTypeName.entityType);
    }

    public int hashCode() {
        return ((((this.project.hashCode() ^ 1000003) * 1000003) ^ this.session.hashCode()) * 1000003) ^ this.entityType.hashCode();
    }
}
