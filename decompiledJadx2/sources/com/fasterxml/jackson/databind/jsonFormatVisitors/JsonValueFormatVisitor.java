package com.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public interface JsonValueFormatVisitor {

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    public static class Base implements JsonValueFormatVisitor {
        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormatVisitor
        public void enumTypes(Set<String> set) {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormatVisitor
        public void format(JsonValueFormat jsonValueFormat) {
        }
    }

    void enumTypes(Set<String> set);

    void format(JsonValueFormat jsonValueFormat);
}
