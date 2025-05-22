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
public class EnvironmentSessionEntityTypeName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/environments/{environment}/users/{user}/sessions/{session}/entityTypes/{entity_type}");
    private final String entityType;
    private final String environment;
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String session;
    private final String user;

    public String getProject() {
        return this.project;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public String getUser() {
        return this.user;
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

    private EnvironmentSessionEntityTypeName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.environment = (String) Preconditions.checkNotNull(builder.getEnvironment());
        this.user = (String) Preconditions.checkNotNull(builder.getUser());
        this.session = (String) Preconditions.checkNotNull(builder.getSession());
        this.entityType = (String) Preconditions.checkNotNull(builder.getEntityType());
    }

    /* renamed from: of */
    public static EnvironmentSessionEntityTypeName m594of(String str, String str2, String str3, String str4, String str5) {
        return newBuilder().setProject(str).setEnvironment(str2).setUser(str3).setSession(str4).setEntityType(str5).build();
    }

    public static String format(String str, String str2, String str3, String str4, String str5) {
        return newBuilder().setProject(str).setEnvironment(str2).setUser(str3).setSession(str4).setEntityType(str5).build().toString();
    }

    public static EnvironmentSessionEntityTypeName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "EnvironmentSessionEntityTypeName.parse: formattedString not in valid format");
        return m594of(validatedMatch.get("project"), validatedMatch.get("environment"), validatedMatch.get("user"), validatedMatch.get("session"), validatedMatch.get("entity_type"));
    }

    public static List<EnvironmentSessionEntityTypeName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<EnvironmentSessionEntityTypeName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (EnvironmentSessionEntityTypeName environmentSessionEntityTypeName : list) {
            if (environmentSessionEntityTypeName == null) {
                arrayList.add("");
            } else {
                arrayList.add(environmentSessionEntityTypeName.toString());
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
                    builder.put("environment", this.environment);
                    builder.put("user", this.user);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "environment", this.environment, "user", this.user, "session", this.session, "entity_type", this.entityType);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String entityType;
        private String environment;
        private String project;
        private String session;
        private String user;

        public String getProject() {
            return this.project;
        }

        public String getEnvironment() {
            return this.environment;
        }

        public String getUser() {
            return this.user;
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

        public Builder setEnvironment(String str) {
            this.environment = str;
            return this;
        }

        public Builder setUser(String str) {
            this.user = str;
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

        private Builder(EnvironmentSessionEntityTypeName environmentSessionEntityTypeName) {
            this.project = environmentSessionEntityTypeName.project;
            this.environment = environmentSessionEntityTypeName.environment;
            this.user = environmentSessionEntityTypeName.user;
            this.session = environmentSessionEntityTypeName.session;
            this.entityType = environmentSessionEntityTypeName.entityType;
        }

        public EnvironmentSessionEntityTypeName build() {
            return new EnvironmentSessionEntityTypeName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnvironmentSessionEntityTypeName)) {
            return false;
        }
        EnvironmentSessionEntityTypeName environmentSessionEntityTypeName = (EnvironmentSessionEntityTypeName) obj;
        return this.project.equals(environmentSessionEntityTypeName.project) && this.environment.equals(environmentSessionEntityTypeName.environment) && this.user.equals(environmentSessionEntityTypeName.user) && this.session.equals(environmentSessionEntityTypeName.session) && this.entityType.equals(environmentSessionEntityTypeName.entityType);
    }

    public int hashCode() {
        return ((((((((this.project.hashCode() ^ 1000003) * 1000003) ^ this.environment.hashCode()) * 1000003) ^ this.user.hashCode()) * 1000003) ^ this.session.hashCode()) * 1000003) ^ this.entityType.hashCode();
    }
}
