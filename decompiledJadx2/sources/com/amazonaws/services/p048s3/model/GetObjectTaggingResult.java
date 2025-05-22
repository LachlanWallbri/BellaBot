package com.amazonaws.services.p048s3.model;

import java.util.List;

/* loaded from: classes.dex */
public class GetObjectTaggingResult {
    private List<Tag> tagSet;
    private String versionId;

    public GetObjectTaggingResult(List<Tag> list) {
        this.tagSet = list;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public GetObjectTaggingResult withVersionId(String str) {
        setVersionId(str);
        return this;
    }

    public List<Tag> getTagSet() {
        return this.tagSet;
    }

    public void setTagSet(List<Tag> list) {
        this.tagSet = list;
    }

    public GetObjectTaggingResult withTagSet(List<Tag> list) {
        setTagSet(list);
        return this;
    }
}
