package com.google.api.resourcenames;

import com.google.api.resourcenames.ResourceName;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ResourceNameFactory<T extends ResourceName> {
    T parse(String str);
}
