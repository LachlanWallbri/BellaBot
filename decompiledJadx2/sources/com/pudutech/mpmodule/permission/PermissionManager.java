package com.pudutech.mpmodule.permission;

import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.android.schedulers.AndroidSchedulers;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class PermissionManager {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public interface OnPermissionResultListener {
        void onGranted();

        void onUnGranted();
    }

    private PermissionManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes6.dex */
    public static class PermissionManagerInstance {
        private static final PermissionManager INSTANCE = new PermissionManager();

        private PermissionManagerInstance() {
        }
    }

    public static PermissionManager getInstance() {
        return PermissionManagerInstance.INSTANCE;
    }

    public void checkPermission(RxPermissions rxPermissions, final OnPermissionResultListener onPermissionResultListener, String... strArr) {
        rxPermissions.request(strArr).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CPermissionObserver() { // from class: com.pudutech.mpmodule.permission.PermissionManager.1
            @Override // com.pudutech.mpmodule.permission.CPermissionObserver
            protected void onCNext(Boolean bool) {
                if (bool.booleanValue()) {
                    OnPermissionResultListener onPermissionResultListener2 = onPermissionResultListener;
                    if (onPermissionResultListener2 != null) {
                        onPermissionResultListener2.onGranted();
                        return;
                    }
                    return;
                }
                OnPermissionResultListener onPermissionResultListener3 = onPermissionResultListener;
                if (onPermissionResultListener3 != null) {
                    onPermissionResultListener3.onUnGranted();
                }
            }

            @Override // com.pudutech.mpmodule.permission.CPermissionObserver
            protected void onCError(Throwable th) {
                OnPermissionResultListener onPermissionResultListener2 = onPermissionResultListener;
                if (onPermissionResultListener2 != null) {
                    onPermissionResultListener2.onUnGranted();
                }
            }
        });
    }
}
