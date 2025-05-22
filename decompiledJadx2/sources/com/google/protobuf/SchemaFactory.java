package com.google.protobuf;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
