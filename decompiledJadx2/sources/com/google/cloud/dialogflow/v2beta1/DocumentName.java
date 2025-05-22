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
public class DocumentName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/knowledgeBases/{knowledge_base}/documents/{document}");
    private final String document;
    private volatile Map<String, String> fieldValuesMap;
    private final String knowledgeBase;
    private final String project;

    public String getProject() {
        return this.project;
    }

    public String getKnowledgeBase() {
        return this.knowledgeBase;
    }

    public String getDocument() {
        return this.document;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private DocumentName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.knowledgeBase = (String) Preconditions.checkNotNull(builder.getKnowledgeBase());
        this.document = (String) Preconditions.checkNotNull(builder.getDocument());
    }

    /* renamed from: of */
    public static DocumentName m591of(String str, String str2, String str3) {
        return newBuilder().setProject(str).setKnowledgeBase(str2).setDocument(str3).build();
    }

    public static String format(String str, String str2, String str3) {
        return newBuilder().setProject(str).setKnowledgeBase(str2).setDocument(str3).build().toString();
    }

    public static DocumentName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "DocumentName.parse: formattedString not in valid format");
        return m591of(validatedMatch.get("project"), validatedMatch.get("knowledge_base"), validatedMatch.get("document"));
    }

    public static List<DocumentName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<DocumentName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (DocumentName documentName : list) {
            if (documentName == null) {
                arrayList.add("");
            } else {
                arrayList.add(documentName.toString());
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
                    builder.put("document", this.document);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "knowledge_base", this.knowledgeBase, "document", this.document);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String document;
        private String knowledgeBase;
        private String project;

        public String getProject() {
            return this.project;
        }

        public String getKnowledgeBase() {
            return this.knowledgeBase;
        }

        public String getDocument() {
            return this.document;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setKnowledgeBase(String str) {
            this.knowledgeBase = str;
            return this;
        }

        public Builder setDocument(String str) {
            this.document = str;
            return this;
        }

        private Builder() {
        }

        private Builder(DocumentName documentName) {
            this.project = documentName.project;
            this.knowledgeBase = documentName.knowledgeBase;
            this.document = documentName.document;
        }

        public DocumentName build() {
            return new DocumentName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DocumentName)) {
            return false;
        }
        DocumentName documentName = (DocumentName) obj;
        return this.project.equals(documentName.project) && this.knowledgeBase.equals(documentName.knowledgeBase) && this.document.equals(documentName.document);
    }

    public int hashCode() {
        return ((((this.project.hashCode() ^ 1000003) * 1000003) ^ this.knowledgeBase.hashCode()) * 1000003) ^ this.document.hashCode();
    }
}
