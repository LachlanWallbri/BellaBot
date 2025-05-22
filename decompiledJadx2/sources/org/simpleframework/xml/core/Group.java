package org.simpleframework.xml.core;

/* loaded from: classes9.dex */
interface Group {
    LabelMap getElements() throws Exception;

    Label getLabel(Class cls);

    Label getText() throws Exception;

    boolean isInline();

    boolean isTextList();

    String toString();
}
