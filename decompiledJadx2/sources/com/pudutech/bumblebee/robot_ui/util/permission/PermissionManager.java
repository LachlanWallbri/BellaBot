package com.pudutech.bumblebee.robot_ui.util.permission;

import androidx.appcompat.app.AppCompatActivity;
import com.pudutech.base.Pdlog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;

/* loaded from: classes4.dex */
public final class PermissionManager {

    /* loaded from: classes4.dex */
    public enum PermissionType {
        GRANTED("已授权"),
        DENIED("未授权"),
        WAIT("等待授权"),
        NOT_NEED("无需授权");

        String stringValue;

        PermissionType(String str) {
            this.stringValue = str;
        }

        public String stringValue() {
            return this.stringValue;
        }
    }

    public static final void requestGroupPermission(AppCompatActivity appCompatActivity, String[] strArr, final PermissionResultListener permissionResultListener) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        new RxPermissions(appCompatActivity).request(strArr).subscribe(new Consumer<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.util.permission.PermissionManager.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                Pdlog.m3273d("PermissionManager", "requestGroupPermission " + bool);
                if (bool.booleanValue()) {
                    PermissionResultListener permissionResultListener2 = PermissionResultListener.this;
                    if (permissionResultListener2 != null) {
                        permissionResultListener2.onPermissionResult(PermissionType.GRANTED);
                        return;
                    }
                    return;
                }
                PermissionResultListener permissionResultListener3 = PermissionResultListener.this;
                if (permissionResultListener3 != null) {
                    permissionResultListener3.onPermissionResult(PermissionType.DENIED);
                }
            }
        });
    }
}
