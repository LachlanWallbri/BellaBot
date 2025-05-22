package com.amazonaws.services.p048s3.model;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes.dex */
public class ObjectTagging implements Serializable {
    private List<Tag> tagSet;

    public ObjectTagging(List<Tag> list) {
        this.tagSet = list;
    }

    public List<Tag> getTagSet() {
        return this.tagSet;
    }

    public void setTagSet(List<Tag> list) {
        this.tagSet = list;
    }

    private ObjectTagging withTagSet(List<Tag> list) {
        this.tagSet = list;
        return this;
    }
}
