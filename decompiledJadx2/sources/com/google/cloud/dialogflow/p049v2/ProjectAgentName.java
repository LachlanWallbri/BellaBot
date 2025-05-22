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
public class ProjectAgentName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent");
    private volatile Map<String, String> fieldValuesMap;
    private final String project;

    public String getProject() {
        return this.project;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private ProjectAgentName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
    }

    /* renamed from: of */
    public static ProjectAgentName m586of(String str) {
        return newBuilder().setProject(str).build();
    }

    public static String format(String str) {
        return newBuilder().setProject(str).build().toString();
    }

    public static ProjectAgentName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        return m586of(PATH_TEMPLATE.validatedMatch(str, "ProjectAgentName.parse: formattedString not in valid format").get("project"));
    }

    public static List<ProjectAgentName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<ProjectAgentName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ProjectAgentName projectAgentName : list) {
            if (projectAgentName == null) {
                arrayList.add("");
            } else {
                arrayList.add(projectAgentName.toString());
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
        return PATH_TEMPLATE.instantiate("project", this.project);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static class Builder {
        private String project;

        public String getProject() {
            return this.project;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        private Builder() {
        }

        private Builder(ProjectAgentName projectAgentName) {
            this.project = projectAgentName.project;
        }

        public ProjectAgentName build() {
            return new ProjectAgentName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProjectAgentName) {
            return this.project.equals(((ProjectAgentName) obj).project);
        }
        return false;
    }

    public int hashCode() {
        return this.project.hashCode() ^ 1000003;
    }
}
