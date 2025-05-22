package org.simpleframework.xml.core;

import java.util.Arrays;
import java.util.List;

/* loaded from: classes9.dex */
class LabelGroup {
    private final List<Label> list;
    private final int size;

    public LabelGroup(Label label) {
        this((List<Label>) Arrays.asList(label));
    }

    public LabelGroup(List<Label> list) {
        this.size = list.size();
        this.list = list;
    }

    public List<Label> getList() {
        return this.list;
    }

    public Label getPrimary() {
        if (this.size > 0) {
            return this.list.get(0);
        }
        return null;
    }
}
