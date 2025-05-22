package com.tbruyelle.rxpermissions2;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class RxPermissions {
    static final String TAG = RxPermissions.class.getSimpleName();
    static final Object TRIGGER = new Object();
    Lazy<RxPermissionsFragment> mRxPermissionsFragment;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    @FunctionalInterface
    /* loaded from: classes7.dex */
    public interface Lazy<V> {
        V get();
    }

    public RxPermissions(FragmentActivity fragmentActivity) {
        this.mRxPermissionsFragment = getLazySingleton(fragmentActivity.getSupportFragmentManager());
    }

    public RxPermissions(Fragment fragment) {
        this.mRxPermissionsFragment = getLazySingleton(fragment.getChildFragmentManager());
    }

    private Lazy<RxPermissionsFragment> getLazySingleton(final FragmentManager fragmentManager) {
        return new Lazy<RxPermissionsFragment>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.1
            private RxPermissionsFragment rxPermissionsFragment;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.tbruyelle.rxpermissions2.RxPermissions.Lazy
            public synchronized RxPermissionsFragment get() {
                if (this.rxPermissionsFragment == null) {
                    this.rxPermissionsFragment = RxPermissions.this.getRxPermissionsFragment(fragmentManager);
                }
                return this.rxPermissionsFragment;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RxPermissionsFragment getRxPermissionsFragment(FragmentManager fragmentManager) {
        RxPermissionsFragment findRxPermissionsFragment = findRxPermissionsFragment(fragmentManager);
        if (!(findRxPermissionsFragment == null)) {
            return findRxPermissionsFragment;
        }
        RxPermissionsFragment rxPermissionsFragment = new RxPermissionsFragment();
        fragmentManager.beginTransaction().add(rxPermissionsFragment, TAG).commitNow();
        return rxPermissionsFragment;
    }

    private RxPermissionsFragment findRxPermissionsFragment(FragmentManager fragmentManager) {
        return (RxPermissionsFragment) fragmentManager.findFragmentByTag(TAG);
    }

    public void setLogging(boolean z) {
        this.mRxPermissionsFragment.get().setLogging(z);
    }

    public <T> ObservableTransformer<T, Boolean> ensure(final String... strArr) {
        return new ObservableTransformer<T, Boolean>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.2
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<Boolean> apply(Observable<T> observable) {
                return RxPermissions.this.request(observable, strArr).buffer(strArr.length).flatMap(new Function<List<Permission>, ObservableSource<Boolean>>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.2.1
                    @Override // io.reactivex.functions.Function
                    public ObservableSource<Boolean> apply(List<Permission> list) {
                        if (list.isEmpty()) {
                            return Observable.empty();
                        }
                        Iterator<Permission> it = list.iterator();
                        while (it.hasNext()) {
                            if (!it.next().granted) {
                                return Observable.just(false);
                            }
                        }
                        return Observable.just(true);
                    }
                });
            }
        };
    }

    public <T> ObservableTransformer<T, Permission> ensureEach(final String... strArr) {
        return new ObservableTransformer<T, Permission>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.3
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<Permission> apply(Observable<T> observable) {
                return RxPermissions.this.request(observable, strArr);
            }
        };
    }

    public <T> ObservableTransformer<T, Permission> ensureEachCombined(final String... strArr) {
        return new ObservableTransformer<T, Permission>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.4
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<Permission> apply(Observable<T> observable) {
                return RxPermissions.this.request(observable, strArr).buffer(strArr.length).flatMap(new Function<List<Permission>, ObservableSource<Permission>>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.4.1
                    @Override // io.reactivex.functions.Function
                    public ObservableSource<Permission> apply(List<Permission> list) {
                        if (list.isEmpty()) {
                            return Observable.empty();
                        }
                        return Observable.just(new Permission(list));
                    }
                });
            }
        };
    }

    public Observable<Boolean> request(String... strArr) {
        return Observable.just(TRIGGER).compose(ensure(strArr));
    }

    public Observable<Permission> requestEach(String... strArr) {
        return Observable.just(TRIGGER).compose(ensureEach(strArr));
    }

    public Observable<Permission> requestEachCombined(String... strArr) {
        return Observable.just(TRIGGER).compose(ensureEachCombined(strArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Permission> request(Observable<?> observable, final String... strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("RxPermissions.request/requestEach requires at least one input permission");
        }
        return oneOf(observable, pending(strArr)).flatMap(new Function<Object, Observable<Permission>>() { // from class: com.tbruyelle.rxpermissions2.RxPermissions.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.functions.Function
            public Observable<Permission> apply(Object obj) {
                return RxPermissions.this.requestImplementation(strArr);
            }
        });
    }

    private Observable<?> pending(String... strArr) {
        for (String str : strArr) {
            if (!this.mRxPermissionsFragment.get().containsByPermission(str)) {
                return Observable.empty();
            }
        }
        return Observable.just(TRIGGER);
    }

    private Observable<?> oneOf(Observable<?> observable, Observable<?> observable2) {
        if (observable == null) {
            return Observable.just(TRIGGER);
        }
        return Observable.merge(observable, observable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Permission> requestImplementation(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        ArrayList arrayList2 = new ArrayList();
        for (String str : strArr) {
            this.mRxPermissionsFragment.get().log("Requesting permission " + str);
            if (isGranted(str)) {
                arrayList.add(Observable.just(new Permission(str, true, false)));
            } else if (isRevoked(str)) {
                arrayList.add(Observable.just(new Permission(str, false, false)));
            } else {
                PublishSubject<Permission> subjectByPermission = this.mRxPermissionsFragment.get().getSubjectByPermission(str);
                if (subjectByPermission == null) {
                    arrayList2.add(str);
                    subjectByPermission = PublishSubject.create();
                    this.mRxPermissionsFragment.get().setSubjectForPermission(str, subjectByPermission);
                }
                arrayList.add(subjectByPermission);
            }
        }
        if (!arrayList2.isEmpty()) {
            requestPermissionsFromFragment((String[]) arrayList2.toArray(new String[arrayList2.size()]));
        }
        return Observable.concat(Observable.fromIterable(arrayList));
    }

    public Observable<Boolean> shouldShowRequestPermissionRationale(Activity activity, String... strArr) {
        if (!isMarshmallow()) {
            return Observable.just(false);
        }
        return Observable.just(Boolean.valueOf(shouldShowRequestPermissionRationaleImplementation(activity, strArr)));
    }

    private boolean shouldShowRequestPermissionRationaleImplementation(Activity activity, String... strArr) {
        for (String str : strArr) {
            if (!isGranted(str) && !activity.shouldShowRequestPermissionRationale(str)) {
                return false;
            }
        }
        return true;
    }

    void requestPermissionsFromFragment(String[] strArr) {
        this.mRxPermissionsFragment.get().log("requestPermissionsFromFragment " + TextUtils.join(", ", strArr));
        this.mRxPermissionsFragment.get().requestPermissions(strArr);
    }

    public boolean isGranted(String str) {
        return !isMarshmallow() || this.mRxPermissionsFragment.get().isGranted(str);
    }

    public boolean isRevoked(String str) {
        return isMarshmallow() && this.mRxPermissionsFragment.get().isRevoked(str);
    }

    boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= 23;
    }

    void onRequestPermissionsResult(String[] strArr, int[] iArr) {
        this.mRxPermissionsFragment.get().onRequestPermissionsResult(strArr, iArr, new boolean[strArr.length]);
    }
}
