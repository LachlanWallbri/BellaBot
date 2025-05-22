package com.livinglifetechway.quickpermissions_kotlin.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AlertBuilder;
import org.jetbrains.anko.AndroidDialogsKt;

/* compiled from: PermissionCheckerFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 \"2\u00020\u0001:\u0002\"#B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ#\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\"\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J+\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\bJ\b\u0010\u001c\u001a\u00020\bH\u0002J\b\u0010\u001d\u001a\u00020\bH\u0002J\u0006\u0010\u001e\u001a\u00020\bJ\u000e\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0004J\u0010\u0010!\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, m3961d2 = {"Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "mListener", "Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment$QuickPermissionsCallback;", "quickPermissionsRequest", "Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsRequest;", "clean", "", "handlePermissionResult", "permissions", "", "", "grantResults", "", "([Ljava/lang/String;[I)V", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "(I[Ljava/lang/String;[I)V", "openAppSettings", "removeListener", "removeRequestPermissionsRequest", "requestPermissionsFromUser", "setListener", "listener", "setRequestPermissionsRequest", "Companion", "QuickPermissionsCallback", "quickpermissions-kotlin_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes.dex */
public final class PermissionCheckerFragment extends Fragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int PERMISSIONS_REQUEST_CODE = 199;
    private static final String TAG = "QuickPermissionsKotlin";
    private QuickPermissionsCallback mListener;
    private QuickPermissionsRequest quickPermissionsRequest;

    /* compiled from: PermissionCheckerFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\t"}, m3961d2 = {"Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment$QuickPermissionsCallback;", "", "onPermissionsDenied", "", "quickPermissionsRequest", "Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsRequest;", "onPermissionsGranted", "onPermissionsPermanentlyDenied", "shouldShowRequestPermissionsRationale", "quickpermissions-kotlin_release"}, m3962k = 1, m3963mv = {1, 1, 13})
    /* loaded from: classes.dex */
    public interface QuickPermissionsCallback {
        void onPermissionsDenied(QuickPermissionsRequest quickPermissionsRequest);

        void onPermissionsGranted(QuickPermissionsRequest quickPermissionsRequest);

        void onPermissionsPermanentlyDenied(QuickPermissionsRequest quickPermissionsRequest);

        void shouldShowRequestPermissionsRationale(QuickPermissionsRequest quickPermissionsRequest);
    }

    /* compiled from: PermissionCheckerFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment$Companion;", "", "()V", "PERMISSIONS_REQUEST_CODE", "", "TAG", "", "newInstance", "Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment;", "quickpermissions-kotlin_release"}, m3962k = 1, m3963mv = {1, 1, 13})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PermissionCheckerFragment newInstance() {
            return new PermissionCheckerFragment();
        }
    }

    public final void setListener(QuickPermissionsCallback listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.mListener = listener;
        Log.d(TAG, "onCreate: listeners set");
    }

    private final void removeListener() {
        this.mListener = (QuickPermissionsCallback) null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: permission fragment created");
    }

    public final void setRequestPermissionsRequest(QuickPermissionsRequest quickPermissionsRequest) {
        this.quickPermissionsRequest = quickPermissionsRequest;
    }

    private final void removeRequestPermissionsRequest() {
        this.quickPermissionsRequest = (QuickPermissionsRequest) null;
    }

    public final void clean() {
        QuickPermissionsCallback quickPermissionsCallback;
        String[] deniedPermissions;
        QuickPermissionsRequest quickPermissionsRequest = this.quickPermissionsRequest;
        if (quickPermissionsRequest != null) {
            if (((quickPermissionsRequest == null || (deniedPermissions = quickPermissionsRequest.getDeniedPermissions()) == null) ? 0 : deniedPermissions.length) > 0 && (quickPermissionsCallback = this.mListener) != null) {
                quickPermissionsCallback.onPermissionsDenied(this.quickPermissionsRequest);
            }
            removeRequestPermissionsRequest();
            removeListener();
            return;
        }
        Log.w(TAG, "clean: QuickPermissionsRequest has already completed its flow. No further callbacks will be called for the current flow.");
    }

    public final void requestPermissionsFromUser() {
        if (this.quickPermissionsRequest != null) {
            Log.d(TAG, "requestPermissionsFromUser: requesting permissions");
            QuickPermissionsRequest quickPermissionsRequest = this.quickPermissionsRequest;
            String[] permissions2 = quickPermissionsRequest != null ? quickPermissionsRequest.getPermissions() : null;
            if (permissions2 == null) {
                permissions2 = new String[0];
            }
            requestPermissions(permissions2, 199);
            return;
        }
        Log.w(TAG, "requestPermissionsFromUser: QuickPermissionsRequest has already completed its flow. Cannot request permissions again from the request received from the callback. You can start the new flow by calling runWithPermissions() { } again.");
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        Log.d(TAG, "passing callback");
        handlePermissionResult(permissions2, grantResults);
    }

    private final void handlePermissionResult(String[] permissions2, int[] grantResults) {
        boolean z;
        boolean z2;
        AlertBuilder<DialogInterface> alert;
        AlertBuilder<DialogInterface> alert2;
        if (permissions2.length == 0) {
            Log.w(TAG, "handlePermissionResult: Permissions result discarded. You might have called multiple permissions request simultaneously");
            return;
        }
        if (PermissionsUtil.INSTANCE.hasSelfPermission(getContext(), permissions2)) {
            QuickPermissionsRequest quickPermissionsRequest = this.quickPermissionsRequest;
            if (quickPermissionsRequest != null) {
                quickPermissionsRequest.setDeniedPermissions(new String[0]);
            }
            QuickPermissionsCallback quickPermissionsCallback = this.mListener;
            if (quickPermissionsCallback != null) {
                quickPermissionsCallback.onPermissionsGranted(this.quickPermissionsRequest);
            }
            clean();
            return;
        }
        String[] deniedPermissions = PermissionsUtil.INSTANCE.getDeniedPermissions(permissions2, grantResults);
        QuickPermissionsRequest quickPermissionsRequest2 = this.quickPermissionsRequest;
        if (quickPermissionsRequest2 != null) {
            quickPermissionsRequest2.setDeniedPermissions(deniedPermissions);
        }
        int length = deniedPermissions.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                z2 = true;
                break;
            } else {
                if (!shouldShowRequestPermissionRationale(deniedPermissions[i])) {
                    z = true;
                    z2 = false;
                    break;
                }
                i++;
            }
        }
        QuickPermissionsRequest quickPermissionsRequest3 = this.quickPermissionsRequest;
        if (quickPermissionsRequest3 != null && quickPermissionsRequest3.getHandlePermanentlyDenied() && z) {
            QuickPermissionsRequest quickPermissionsRequest4 = this.quickPermissionsRequest;
            if (quickPermissionsRequest4 != null && quickPermissionsRequest4.getPermanentDeniedMethod$quickpermissions_kotlin_release() != null) {
                QuickPermissionsRequest quickPermissionsRequest5 = this.quickPermissionsRequest;
                if (quickPermissionsRequest5 != null) {
                    quickPermissionsRequest5.setPermanentlyDeniedPermissions(PermissionsUtil.INSTANCE.getPermanentlyDeniedPermissions(this, permissions2, grantResults));
                }
                QuickPermissionsCallback quickPermissionsCallback2 = this.mListener;
                if (quickPermissionsCallback2 != null) {
                    quickPermissionsCallback2.onPermissionsPermanentlyDenied(this.quickPermissionsRequest);
                    return;
                }
                return;
            }
            FragmentActivity activity = getActivity();
            if (activity == null || (alert2 = AndroidDialogsKt.alert(activity, new Function1<AlertBuilder<? extends DialogInterface>, Unit>() { // from class: com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment$handlePermissionResult$2
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                    invoke2(alertBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlertBuilder<? extends DialogInterface> receiver$0) {
                    QuickPermissionsRequest quickPermissionsRequest6;
                    Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                    quickPermissionsRequest6 = PermissionCheckerFragment.this.quickPermissionsRequest;
                    String permanentlyDeniedMessage = quickPermissionsRequest6 != null ? quickPermissionsRequest6.getPermanentlyDeniedMessage() : null;
                    if (permanentlyDeniedMessage == null) {
                        permanentlyDeniedMessage = "";
                    }
                    receiver$0.setMessage(permanentlyDeniedMessage);
                    receiver$0.positiveButton("SETTINGS", new Function1<DialogInterface, Unit>() { // from class: com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment$handlePermissionResult$2.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                            invoke2(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DialogInterface it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            PermissionCheckerFragment.this.openAppSettings();
                        }
                    });
                    receiver$0.negativeButton("CANCEL", new Function1<DialogInterface, Unit>() { // from class: com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment$handlePermissionResult$2.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                            invoke2(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DialogInterface it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            PermissionCheckerFragment.this.clean();
                        }
                    });
                }
            })) == null) {
                return;
            }
            alert2.setCancelable(false);
            if (alert2 != null) {
                alert2.show();
                return;
            }
            return;
        }
        QuickPermissionsRequest quickPermissionsRequest6 = this.quickPermissionsRequest;
        if (quickPermissionsRequest6 != null && quickPermissionsRequest6.getHandleRationale() && z2) {
            QuickPermissionsRequest quickPermissionsRequest7 = this.quickPermissionsRequest;
            if (quickPermissionsRequest7 != null && quickPermissionsRequest7.getRationaleMethod$quickpermissions_kotlin_release() != null) {
                QuickPermissionsCallback quickPermissionsCallback3 = this.mListener;
                if (quickPermissionsCallback3 != null) {
                    quickPermissionsCallback3.shouldShowRequestPermissionsRationale(this.quickPermissionsRequest);
                    return;
                }
                return;
            }
            FragmentActivity activity2 = getActivity();
            if (activity2 == null || (alert = AndroidDialogsKt.alert(activity2, new Function1<AlertBuilder<? extends DialogInterface>, Unit>() { // from class: com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment$handlePermissionResult$5
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                    invoke2(alertBuilder);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlertBuilder<? extends DialogInterface> receiver$0) {
                    QuickPermissionsRequest quickPermissionsRequest8;
                    Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
                    quickPermissionsRequest8 = PermissionCheckerFragment.this.quickPermissionsRequest;
                    String rationaleMessage = quickPermissionsRequest8 != null ? quickPermissionsRequest8.getRationaleMessage() : null;
                    if (rationaleMessage == null) {
                        rationaleMessage = "";
                    }
                    receiver$0.setMessage(rationaleMessage);
                    receiver$0.positiveButton("TRY AGAIN", new Function1<DialogInterface, Unit>() { // from class: com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment$handlePermissionResult$5.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                            invoke2(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DialogInterface it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            PermissionCheckerFragment.this.requestPermissionsFromUser();
                        }
                    });
                    receiver$0.negativeButton("CANCEL", new Function1<DialogInterface, Unit>() { // from class: com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment$handlePermissionResult$5.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DialogInterface dialogInterface) {
                            invoke2(dialogInterface);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(DialogInterface it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            PermissionCheckerFragment.this.clean();
                        }
                    });
                }
            })) == null) {
                return;
            }
            alert.setCancelable(false);
            if (alert != null) {
                alert.show();
                return;
            }
            return;
        }
        clean();
    }

    public final void openAppSettings() {
        if (this.quickPermissionsRequest != null) {
            FragmentActivity activity = getActivity();
            startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", activity != null ? activity.getPackageName() : null, null)), 199);
        } else {
            Log.w(TAG, "openAppSettings: QuickPermissionsRequest has already completed its flow. Cannot open app settings");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String[] strArr;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 199) {
            QuickPermissionsRequest quickPermissionsRequest = this.quickPermissionsRequest;
            int i = 0;
            if (quickPermissionsRequest == null || (strArr = quickPermissionsRequest.getPermissions()) == null) {
                strArr = new String[0];
            }
            int[] iArr = new int[strArr.length];
            int length = strArr.length;
            int i2 = 0;
            while (i < length) {
                String str = strArr[i];
                int i3 = i2 + 1;
                Context context = getContext();
                iArr[i2] = context != null ? ActivityCompat.checkSelfPermission(context, str) : -1;
                i++;
                i2 = i3;
            }
            handlePermissionResult(strArr, iArr);
        }
    }
}
