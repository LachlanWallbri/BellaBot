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
public class AgentName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agents/{agent}");
    private final String agent;
    private volatile Map<String, String> fieldValuesMap;
    private final String project;

    public String getProject() {
        return this.project;
    }

    public String getAgent() {
        return this.agent;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private AgentName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.agent = (String) Preconditions.checkNotNull(builder.getAgent());
    }

    /* renamed from: of */
    public static AgentName m582of(String str, String str2) {
        return newBuilder().setProject(str).setAgent(str2).build();
    }

    public static String format(String str, String str2) {
        return newBuilder().setProject(str).setAgent(str2).build().toString();
    }

    public static AgentName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "AgentName.parse: formattedString not in valid format");
        return m582of(validatedMatch.get("project"), validatedMatch.get("agent"));
    }

    public static List<AgentName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<AgentName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (AgentName agentName : list) {
            if (agentName == null) {
                arrayList.add("");
            } else {
                arrayList.add(agentName.toString());
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
                    builder.put("agent", this.agent);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "agent", this.agent);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private String agent;
        private String project;

        public String getProject() {
            return this.project;
        }

        public String getAgent() {
            return this.agent;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setAgent(String str) {
            this.agent = str;
            return this;
        }

        private Builder() {
        }

        private Builder(AgentName agentName) {
            this.project = agentName.project;
            this.agent = agentName.agent;
        }

        public AgentName build() {
            return new AgentName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AgentName)) {
            return false;
        }
        AgentName agentName = (AgentName) obj;
        return this.project.equals(agentName.project) && this.agent.equals(agentName.agent);
    }

    public int hashCode() {
        return ((this.project.hashCode() ^ 1000003) * 1000003) ^ this.agent.hashCode();
    }
}
