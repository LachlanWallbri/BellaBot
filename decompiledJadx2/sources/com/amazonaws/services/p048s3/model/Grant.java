package com.amazonaws.services.p048s3.model;

/* loaded from: classes.dex */
public class Grant {
    private Grantee grantee;
    private Permission permission;

    public Grant(Grantee grantee, Permission permission) {
        this.grantee = null;
        this.permission = null;
        this.grantee = grantee;
        this.permission = permission;
    }

    public Grantee getGrantee() {
        return this.grantee;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public int hashCode() {
        Grantee grantee = this.grantee;
        int hashCode = ((grantee == null ? 0 : grantee.hashCode()) + 31) * 31;
        Permission permission = this.permission;
        return hashCode + (permission != null ? permission.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Grant grant = (Grant) obj;
        Grantee grantee = this.grantee;
        if (grantee == null) {
            if (grant.grantee != null) {
                return false;
            }
        } else if (!grantee.equals(grant.grantee)) {
            return false;
        }
        return this.permission == grant.permission;
    }

    public String toString() {
        return "Grant [grantee=" + this.grantee + ", permission=" + this.permission + "]";
    }
}
