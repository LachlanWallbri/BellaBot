package com.tbruyelle.rxpermissions2;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import io.reactivex.subjects.PublishSubject;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class RxPermissionsFragment extends Fragment {
    private static final int PERMISSIONS_REQUEST_CODE = 42;
    private boolean mLogging;
    private Map<String, PublishSubject<Permission>> mSubjects = new HashMap();

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestPermissions(String[] strArr) {
        requestPermissions(strArr, 42);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 42) {
            return;
        }
        boolean[] zArr = new boolean[strArr.length];
        for (int i2 = 0; i2 < strArr.length; i2++) {
            zArr[i2] = shouldShowRequestPermissionRationale(strArr[i2]);
        }
        onRequestPermissionsResult(strArr, iArr, zArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onRequestPermissionsResult(String[] strArr, int[] iArr, boolean[] zArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            log("onRequestPermissionsResult  " + strArr[i]);
            PublishSubject<Permission> publishSubject = this.mSubjects.get(strArr[i]);
            if (publishSubject == null) {
                Log.e(RxPermissions.TAG, "RxPermissions.onRequestPermissionsResult invoked but didn't find the corresponding permission request.");
                return;
            }
            this.mSubjects.remove(strArr[i]);
            publishSubject.onNext(new Permission(strArr[i], iArr[i] == 0, zArr[i]));
            publishSubject.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isGranted(String str) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity.checkSelfPermission(str) == 0;
        }
        throw new IllegalStateException("This fragment must be attached to an activity.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRevoked(String str) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            throw new IllegalStateException("This fragment must be attached to an activity.");
        }
        return activity.getPackageManager().isPermissionRevokedByPolicy(str, getActivity().getPackageName());
    }

    public void setLogging(boolean z) {
        this.mLogging = z;
    }

    public PublishSubject<Permission> getSubjectByPermission(String str) {
        return this.mSubjects.get(str);
    }

    public boolean containsByPermission(String str) {
        return this.mSubjects.containsKey(str);
    }

    public void setSubjectForPermission(String str, PublishSubject<Permission> publishSubject) {
        this.mSubjects.put(str, publishSubject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void log(String str) {
        if (this.mLogging) {
            Log.d(RxPermissions.TAG, str);
        }
    }
}
