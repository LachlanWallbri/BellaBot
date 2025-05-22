package com.chad.library.adapter.base.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IExpandable<T> {
    int getLevel();

    List<T> getSubItems();

    boolean isExpanded();

    void setExpanded(boolean z);
}
