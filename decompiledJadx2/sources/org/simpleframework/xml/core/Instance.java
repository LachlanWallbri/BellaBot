package org.simpleframework.xml.core;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public interface Instance {
    Object getInstance() throws Exception;

    Class getType();

    boolean isReference();

    Object setInstance(Object obj) throws Exception;
}
