package com.tbruyelle.rxpermissions2;

import io.reactivex.Observable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Permission {
    public final boolean granted;
    public final String name;
    public final boolean shouldShowRequestPermissionRationale;

    public Permission(String str, boolean z) {
        this(str, z, false);
    }

    public Permission(String str, boolean z, boolean z2) {
        this.name = str;
        this.granted = z;
        this.shouldShowRequestPermissionRationale = z2;
    }

    public Permission(List<Permission> list) {
        this.name = combineName(list);
        this.granted = combineGranted(list).booleanValue();
        this.shouldShowRequestPermissionRationale = combineShouldShowRequestPermissionRationale(list).booleanValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Permission permission = (Permission) obj;
        if (this.granted == permission.granted && this.shouldShowRequestPermissionRationale == permission.shouldShowRequestPermissionRationale) {
            return this.name.equals(permission.name);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + (this.granted ? 1 : 0)) * 31) + (this.shouldShowRequestPermissionRationale ? 1 : 0);
    }

    public String toString() {
        return "Permission{name='" + this.name + "', granted=" + this.granted + ", shouldShowRequestPermissionRationale=" + this.shouldShowRequestPermissionRationale + '}';
    }

    private String combineName(List<Permission> list) {
        return ((StringBuilder) Observable.fromIterable(list).map(new Function<Permission, String>() { // from class: com.tbruyelle.rxpermissions2.Permission.2
            @Override // io.reactivex.functions.Function
            public String apply(Permission permission) throws Exception {
                return permission.name;
            }
        }).collectInto(new StringBuilder(), new BiConsumer<StringBuilder, String>() { // from class: com.tbruyelle.rxpermissions2.Permission.1
            @Override // io.reactivex.functions.BiConsumer
            public void accept(StringBuilder sb, String str) throws Exception {
                if (sb.length() == 0) {
                    sb.append(str);
                } else {
                    sb.append(", ");
                    sb.append(str);
                }
            }
        }).blockingGet()).toString();
    }

    private Boolean combineGranted(List<Permission> list) {
        return Observable.fromIterable(list).all(new Predicate<Permission>() { // from class: com.tbruyelle.rxpermissions2.Permission.3
            @Override // io.reactivex.functions.Predicate
            public boolean test(Permission permission) throws Exception {
                return permission.granted;
            }
        }).blockingGet();
    }

    private Boolean combineShouldShowRequestPermissionRationale(List<Permission> list) {
        return Observable.fromIterable(list).any(new Predicate<Permission>() { // from class: com.tbruyelle.rxpermissions2.Permission.4
            @Override // io.reactivex.functions.Predicate
            public boolean test(Permission permission) throws Exception {
                return permission.shouldShowRequestPermissionRationale;
            }
        }).blockingGet();
    }
}
