package com.amazonaws.services.p048s3.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class CanonicalGrantee implements Grantee, Serializable {

    /* renamed from: id */
    private String f1184id = null;
    private String displayName = null;

    @Override // com.amazonaws.services.p048s3.model.Grantee
    public String getTypeIdentifier() {
        return "id";
    }

    public CanonicalGrantee(String str) {
        setIdentifier(str);
    }

    @Override // com.amazonaws.services.p048s3.model.Grantee
    public void setIdentifier(String str) {
        this.f1184id = str;
    }

    @Override // com.amazonaws.services.p048s3.model.Grantee
    public String getIdentifier() {
        return this.f1184id;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean equals(Object obj) {
        if (obj instanceof CanonicalGrantee) {
            return this.f1184id.equals(((CanonicalGrantee) obj).f1184id);
        }
        return false;
    }

    public int hashCode() {
        return this.f1184id.hashCode();
    }
}
