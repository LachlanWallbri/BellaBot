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
public class KnowledgeBaseName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/knowledgeBases/{knowledge_base}");
    private volatile Map<String, String> fieldValuesMap;
    private final String knowledgeBase;
    private final String project;

    public String getProject() {
        return this.project;
    }

    public String getKnowledgeBase() {
        return this.knowledgeBase;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private KnowledgeBaseName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.knowledgeBase = (String) Preconditions.checkNotNull(builder.getKnowledgeBase());
    }

    /* renamed from: of */
    public static KnowledgeBaseName m597of(String str, String str2) {
        return newBuilder().setProject(str).setKnowledgeBase(str2).build();
    }

    public static String format(String str, String str2) {
        return newBuilder().setProject(str).setKnowledgeBase(str2).build().toString();
    }

    public static KnowledgeBaseName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "KnowledgeBaseName.parse: formattedString not in valid format");
        return m597of(validatedMatch.get("project"), validatedMatch.get("knowledge_base"));
    }

    public static List<KnowledgeBaseName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<KnowledgeBaseName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (KnowledgeBaseName knowledgeBaseName : list) {
            if (knowledgeBaseName == null) {
                arrayList.add("");
            } else {
                arrayList.add(knowledgeBaseName.toString());
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
                    builder.put("knowledgeBase", this.knowledgeBase);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "knowledge_base", this.knowledgeBase);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String knowledgeBase;
        private String project;

        public String getProject() {
            return this.project;
        }

        public String getKnowledgeBase() {
            return this.knowledgeBase;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setKnowledgeBase(String str) {
            this.knowledgeBase = str;
            return this;
        }

        private Builder() {
        }

        private Builder(KnowledgeBaseName knowledgeBaseName) {
            this.project = knowledgeBaseName.project;
            this.knowledgeBase = knowledgeBaseName.knowledgeBase;
        }

        public KnowledgeBaseName build() {
            return new KnowledgeBaseName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KnowledgeBaseName)) {
            return false;
        }
        KnowledgeBaseName knowledgeBaseName = (KnowledgeBaseName) obj;
        return this.project.equals(knowledgeBaseName.project) && this.knowledgeBase.equals(knowledgeBaseName.knowledgeBase);
    }

    public int hashCode() {
        return ((this.project.hashCode() ^ 1000003) * 1000003) ^ this.knowledgeBase.hashCode();
    }
}
