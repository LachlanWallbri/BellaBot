package com.amazonaws.mobile.auth.core.signin;

/* loaded from: classes.dex */
public interface SignInPermissionsHandler {
    int getPermissionRequestCode();

    void handleRequestPermissionsResult(int i, String[] strArr, int[] iArr);
}
