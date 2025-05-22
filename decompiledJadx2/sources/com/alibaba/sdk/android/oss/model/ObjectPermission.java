package com.alibaba.sdk.android.oss.model;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public enum ObjectPermission {
    Private("private"),
    PublicRead("public-read"),
    PublicReadWrite("public-read-write"),
    Default("default"),
    Unknown("");

    private String permissionString;

    ObjectPermission(String str) {
        this.permissionString = str;
    }

    public static ObjectPermission parsePermission(String str) {
        for (ObjectPermission objectPermission : new ObjectPermission[]{Private, PublicRead, PublicReadWrite, Default}) {
            if (objectPermission.permissionString.equals(str)) {
                return objectPermission;
            }
        }
        return Unknown;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.permissionString;
    }
}
