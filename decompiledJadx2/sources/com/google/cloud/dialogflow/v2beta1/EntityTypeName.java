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
public class EntityTypeName implements ResourceName {
    private static final PathTemplate PATH_TEMPLATE = PathTemplate.createWithoutUrlEncoding("projects/{project}/agent/entityTypes/{entity_type}");
    private final String entityType;
    private volatile Map<String, String> fieldValuesMap;
    private final String project;

    public String getProject() {
        return this.project;
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

    private EntityTypeName(Builder builder) {
        this.project = (String) Preconditions.checkNotNull(builder.getProject());
        this.entityType = (String) Preconditions.checkNotNull(builder.getEntityType());
    }

    /* renamed from: of */
    public static EntityTypeName m592of(String str, String str2) {
        return newBuilder().setProject(str).setEntityType(str2).build();
    }

    public static String format(String str, String str2) {
        return newBuilder().setProject(str).setEntityType(str2).build().toString();
    }

    public static EntityTypeName parse(String str) {
        if (str.isEmpty()) {
            return null;
        }
        Map<String, String> validatedMatch = PATH_TEMPLATE.validatedMatch(str, "EntityTypeName.parse: formattedString not in valid format");
        return m592of(validatedMatch.get("project"), validatedMatch.get("entity_type"));
    }

    public static List<EntityTypeName> parseList(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(parse(it.next()));
        }
        return arrayList;
    }

    public static List<String> toStringList(List<EntityTypeName> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (EntityTypeName entityTypeName : list) {
            if (entityTypeName == null) {
                arrayList.add("");
            } else {
                arrayList.add(entityTypeName.toString());
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
        return PATH_TEMPLATE.instantiate("project", this.project, "entity_type", this.entityType);
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes3.dex */
    public static class Builder {
        private String entityType;
        private String project;

        public String getProject() {
            return this.project;
        }

        public String getEntityType() {
            return this.entityType;
        }

        public Builder setProject(String str) {
            this.project = str;
            return this;
        }

        public Builder setEntityType(String str) {
            this.entityType = str;
            return this;
        }

        private Builder() {
        }

        private Builder(EntityTypeName entityTypeName) {
            this.project = entityTypeName.project;
            this.entityType = entityTypeName.entityType;
        }

        public EntityTypeName build() {
            return new EntityTypeName(this);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EntityTypeName)) {
            return false;
        }
        EntityTypeName entityTypeName = (EntityTypeName) obj;
        return this.project.equals(entityTypeName.project) && this.entityType.equals(entityTypeName.entityType);
    }

    public int hashCode() {
        return ((this.project.hashCode() ^ 1000003) * 1000003) ^ this.entityType.hashCode();
    }
}
