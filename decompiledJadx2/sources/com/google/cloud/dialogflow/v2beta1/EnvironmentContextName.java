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
public class EnvironmentContextName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/environments/{environment}/users/{user}/sessions/{session}/contexts/{context}");
    private final String context;
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

    public String getContext() {
        return this.context;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private EnvironmentContextName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.environment = (String) Preconditions.checkNotNull(builder.getEnvironment());
        this.user = (String) Preconditions.checkNotNull(builder.getUser());
        this.session = (String) Preconditions.checkNotNull(builder.getSession());
        this.context = (String) Preconditions.checkNotNull(builder.getContext());
    }

    /* renamed from: of */
    public static EnvironmentContextName m593of(String str, String str2, String str3, String str4, String str5) {
        return newBuilder().setProject(str).setEnvironment(str2).setUser(str3).setSession(str4).setContext(str5).build();
    }

    public static String format(String str, String str2, String str3, String str4, String str5) {
        return newBuilder().setProject(str).setEnvironment(str2).setUser(str3).setSession(str4).setContext(str5).build().toString();
    }

    public static EnvironmentContextName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "EnvironmentContextName.parse: formattedString not in valid format");
        return m593of(validatedMatch.get("project"), validatedMatch.get("environment"), validatedMatch.get("user"), validatedMatch.get("session"), validatedMatch.get("context"));
    }

    public static List<EnvironmentContextName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<EnvironmentContextName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (EnvironmentContextName environmentContextName : list) {
            if (environmentContextName == null) {
                arrayList.add("");
            } else {
                arrayList.add(environmentContextName.toString());
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
                    builder.put("context", this.context);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "environment", this.environment, "user", this.user, "session", this.session, "context", this.context);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String context;
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

        public String getContext() {
            return this.context;
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

        public Builder setContext(String str) {
            this.context = str;
            return this;
        }

        private Builder() {
        }

        private Builder(EnvironmentContextName environmentContextName) {
            this.project = environmentContextName.project;
            this.environment = environmentContextName.environment;
            this.user = environmentContextName.user;
            this.session = environmentContextName.session;
            this.context = environmentContextName.context;
        }

        public EnvironmentContextName build() {
            return new EnvironmentContextName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EnvironmentContextName)) {
            return false;
        }
        EnvironmentContextName environmentContextName = (EnvironmentContextName) obj;
        return this.project.equals(environmentContextName.project) && this.environment.equals(environmentContextName.environment) && this.user.equals(environmentContextName.user) && this.session.equals(environmentContextName.session) && this.context.equals(environmentContextName.context);
    }

    public int hashCode() {
        return ((((((((this.project.hashCode() ^ 1000003) * 1000003) ^ this.environment.hashCode()) * 1000003) ^ this.user.hashCode()) * 1000003) ^ this.session.hashCode()) * 1000003) ^ this.context.hashCode();
    }
}
