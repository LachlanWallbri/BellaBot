package com.amazonaws.services.p048s3.model;

import java.io.Serializable;

/* loaded from: classes.dex */
public class Owner implements Serializable {
    private static final long serialVersionUID = -8916731456944569115L;
    private String displayName;

    /* renamed from: id */
    private String f1192id;

    public Owner() {
    }

    public Owner(String str, String str2) {
        this.f1192id = str;
        this.displayName = str2;
    }

    public String toString() {
        return "S3Owner [name=" + getDisplayName() + ",id=" + getId() + "]";
    }

    public String getId() {
        return this.f1192id;
    }

    public void setId(String str) {
        this.f1192id = str;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Owner)) {
            return false;
        }
        Owner owner = (Owner) obj;
        String id = owner.getId();
        String displayName = owner.getDisplayName();
        String id2 = getId();
        String displayName2 = getDisplayName();
        if (id == null) {
            id = "";
        }
        if (displayName == null) {
            displayName = "";
        }
        if (id2 == null) {
            id2 = "";
        }
        if (displayName2 == null) {
            displayName2 = "";
        }
        return id.equals(id2) && displayName.equals(displayName2);
    }

    public int hashCode() {
        String str = this.f1192id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
