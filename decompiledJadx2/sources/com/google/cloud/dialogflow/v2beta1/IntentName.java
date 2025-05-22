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
public class IntentName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/intents/{intent}");
    private volatile Map<String, String> fieldValuesMap;
    private final String intent;
    private final String project;

    public String getProject() {
        return this.project;
    }

    public String getIntent() {
        return this.intent;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    private IntentName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.intent = (String) Preconditions.checkNotNull(builder.getIntent());
    }

    /* renamed from: of */
    public static IntentName m596of(String str, String str2) {
        return newBuilder().setProject(str).setIntent(str2).build();
    }

    public static String format(String str, String str2) {
        return newBuilder().setProject(str).setIntent(str2).build().toString();
    }

    public static IntentName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "IntentName.parse: formattedString not in valid format");
        return m596of(validatedMatch.get("project"), validatedMatch.get("intent"));
    }

    public static List<IntentName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<IntentName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (IntentName intentName : list) {
            if (intentName == null) {
                arrayList.add("");
            } else {
                arrayList.add(intentName.toString());
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
                    builder.put("intent", this.intent);
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
        return PATH_TEMPLATE.instantiate("project", this.project, "intent", this.intent);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String intent;
        private String project;

        public String getProject() {
            return this.project;
        }

        public String getIntent() {
            return this.intent;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setIntent(String str) {
            this.intent = str;
            return this;
        }

        private Builder() {
        }

        private Builder(IntentName intentName) {
            this.project = intentName.project;
            this.intent = intentName.intent;
        }

        public IntentName build() {
            return new IntentName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntentName)) {
            return false;
        }
        IntentName intentName = (IntentName) obj;
        return this.project.equals(intentName.project) && this.intent.equals(intentName.intent);
    }

    public int hashCode() {
        return ((this.project.hashCode() ^ 1000003) * 1000003) ^ this.intent.hashCode();
    }
}
