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
public class ContextName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/sessions/{session}/contexts/{context}");
    private final String context;
    private volatile Map<String, String> fieldValuesMap;
    private final String project;
    private final String session;

    public String getProject() {
        return this.project;
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

    private ContextName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.session = (String) Preconditions.checkNotNull(builder.getSession());
        this.context = (String) Preconditions.checkNotNull(builder.getContext());
    }

    /* renamed from: of */
    public static ContextName m590of(String str, String str2, String str3) {
        return newBuilder().setProject(str).setSession(str2).setContext(str3).build();
    }

    public static String format(String str, String str2, String str3) {
        return newBuilder().setProject(str).setSession(str2).setContext(str3).build().toString();
    }

    public static ContextName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "ContextName.parse: formattedString not in valid format");
        return m590of(validatedMatch.get("project"), validatedMatch.get("session"), validatedMatch.get("context"));
    }

    public static List<ContextName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<ContextName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ContextName contextName : list) {
            if (contextName == null) {
                arrayList.add("");
            } else {
                arrayList.add(contextName.toString());
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
        return PATH_TEMPLATE.instantiate("project", this.project, "session", this.session, "context", this.context);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String context;
        private String project;
        private String session;

        public String getProject() {
            return this.project;
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

        private Builder(ContextName contextName) {
            this.project = contextName.project;
            this.session = contextName.session;
            this.context = contextName.context;
        }

        public ContextName build() {
            return new ContextName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ContextName)) {
            return false;
        }
        ContextName contextName = (ContextName) obj;
        return this.project.equals(contextName.project) && this.session.equals(contextName.session) && this.context.equals(contextName.context);
    }

    public int hashCode() {
        return ((((this.project.hashCode() ^ 1000003) * 1000003) ^ this.session.hashCode()) * 1000003) ^ this.context.hashCode();
    }
}
