package com.livinglifetechway.quickpermissions_kotlin;

import android.content.Context;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment;
import com.livinglifetechway.quickpermissions_kotlin.util.PermissionsUtil;
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions;
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsRequest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PermissionsManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a?\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\r\u001a?\u0010\u000e\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u000f2\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0007\"\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u0010\u001a?\u0010\u000e\u001a\u0004\u0018\u00010\u0005*\u0004\u0018\u00010\u00112\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0007\"\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u0012\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m3961d2 = {"TAG", "", "runWithPermissionsHandler", "", TypedValues.Attributes.S_TARGET, "", "permissions", "", "callback", "Lkotlin/Function0;", "", "options", "Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsOptions;", "(Ljava/lang/Object;[Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsOptions;)Ljava/lang/Void;", PermissionsManagerKt.TAG, "Landroid/content/Context;", "(Landroid/content/Context;[Ljava/lang/String;Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsOptions;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Landroidx/fragment/app/Fragment;", "(Landroid/support/v4/app/Fragment;[Ljava/lang/String;Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsOptions;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "quickpermissions-kotlin_release"}, m3962k = 2, m3963mv = {1, 1, 13})
/* loaded from: classes.dex */
public final class PermissionsManagerKt {
    private static final String TAG = "runWithPermissions";

    public static /* synthetic */ Object runWithPermissions$default(Context context, String[] strArr, QuickPermissionsOptions quickPermissionsOptions, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            quickPermissionsOptions = new QuickPermissionsOptions(false, null, false, null, null, null, null, 127, null);
        }
        return runWithPermissions(context, strArr, quickPermissionsOptions, (Function0<Unit>) function0);
    }

    public static final Object runWithPermissions(Context context, String[] permissions2, QuickPermissionsOptions options, Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(options, "options");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        return runWithPermissionsHandler(context, permissions2, callback, options);
    }

    public static /* synthetic */ Object runWithPermissions$default(Fragment fragment, String[] strArr, QuickPermissionsOptions quickPermissionsOptions, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            quickPermissionsOptions = new QuickPermissionsOptions(false, null, false, null, null, null, null, 127, null);
        }
        return runWithPermissions(fragment, strArr, quickPermissionsOptions, (Function0<Unit>) function0);
    }

    public static final Object runWithPermissions(Fragment fragment, String[] permissions2, QuickPermissionsOptions options, Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(options, "options");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        return runWithPermissionsHandler(fragment, permissions2, callback, options);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v4, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v7, types: [com.livinglifetechway.quickpermissions_kotlin.util.PermissionsUtil] */
    private static final Void runWithPermissionsHandler(Object obj, String[] strArr, final Function0<Unit> function0, QuickPermissionsOptions quickPermissionsOptions) {
        AppCompatActivity appCompatActivity;
        PermissionCheckerFragment permissionCheckerFragment;
        Log.d(TAG, "runWithPermissions: start");
        Log.d(TAG, "runWithPermissions: permissions to check: " + strArr);
        if ((obj instanceof AppCompatActivity) || (obj instanceof Fragment)) {
            Log.d(TAG, "runWithPermissions: context found");
            if (obj instanceof Context) {
                appCompatActivity = (Context) obj;
            } else {
                appCompatActivity = obj instanceof Fragment ? ((Fragment) obj).getContext() : 0;
            }
            if (PermissionsUtil.INSTANCE.hasSelfPermission(appCompatActivity, (String[]) Arrays.copyOf(strArr, strArr.length))) {
                Log.d(TAG, "runWithPermissions: already has required permissions. Proceed with the execution.");
                function0.invoke();
            } else {
                Log.d(TAG, "runWithPermissions: doesn't have required permissions");
                boolean z = appCompatActivity instanceof AppCompatActivity;
                if (z) {
                    FragmentManager supportFragmentManager = appCompatActivity.getSupportFragmentManager();
                    permissionCheckerFragment = (PermissionCheckerFragment) (supportFragmentManager != null ? supportFragmentManager.findFragmentByTag(PermissionCheckerFragment.class.getCanonicalName()) : null);
                } else {
                    permissionCheckerFragment = appCompatActivity instanceof Fragment ? (PermissionCheckerFragment) appCompatActivity.getChildFragmentManager().findFragmentByTag(PermissionCheckerFragment.class.getCanonicalName()) : null;
                }
                if (permissionCheckerFragment == null) {
                    Log.d(TAG, "runWithPermissions: adding headless fragment for asking permissions");
                    permissionCheckerFragment = PermissionCheckerFragment.INSTANCE.newInstance();
                    if (z) {
                        AppCompatActivity appCompatActivity2 = appCompatActivity;
                        FragmentTransaction beginTransaction = appCompatActivity2.getSupportFragmentManager().beginTransaction();
                        beginTransaction.add(permissionCheckerFragment, PermissionCheckerFragment.class.getCanonicalName());
                        beginTransaction.commit();
                        FragmentManager supportFragmentManager2 = appCompatActivity2.getSupportFragmentManager();
                        if (supportFragmentManager2 != null) {
                            supportFragmentManager2.executePendingTransactions();
                        }
                    } else if (appCompatActivity instanceof Fragment) {
                        Fragment fragment = (Fragment) appCompatActivity;
                        FragmentTransaction beginTransaction2 = fragment.getChildFragmentManager().beginTransaction();
                        beginTransaction2.add(permissionCheckerFragment, PermissionCheckerFragment.class.getCanonicalName());
                        beginTransaction2.commit();
                        fragment.getChildFragmentManager().executePendingTransactions();
                    }
                }
                permissionCheckerFragment.setListener(new PermissionCheckerFragment.QuickPermissionsCallback() { // from class: com.livinglifetechway.quickpermissions_kotlin.PermissionsManagerKt$runWithPermissionsHandler$3
                    @Override // com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment.QuickPermissionsCallback
                    public void onPermissionsGranted(QuickPermissionsRequest quickPermissionsRequest) {
                        Log.d("runWithPermissions", "runWithPermissions: got permissions");
                        try {
                            Function0.this.invoke();
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }

                    @Override // com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment.QuickPermissionsCallback
                    public void onPermissionsDenied(QuickPermissionsRequest quickPermissionsRequest) {
                        Function1<QuickPermissionsRequest, Unit> permissionsDeniedMethod$quickpermissions_kotlin_release;
                        if (quickPermissionsRequest == null || (permissionsDeniedMethod$quickpermissions_kotlin_release = quickPermissionsRequest.getPermissionsDeniedMethod$quickpermissions_kotlin_release()) == null) {
                            return;
                        }
                        permissionsDeniedMethod$quickpermissions_kotlin_release.invoke(quickPermissionsRequest);
                    }

                    @Override // com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment.QuickPermissionsCallback
                    public void shouldShowRequestPermissionsRationale(QuickPermissionsRequest quickPermissionsRequest) {
                        Function1<QuickPermissionsRequest, Unit> rationaleMethod$quickpermissions_kotlin_release;
                        if (quickPermissionsRequest == null || (rationaleMethod$quickpermissions_kotlin_release = quickPermissionsRequest.getRationaleMethod$quickpermissions_kotlin_release()) == null) {
                            return;
                        }
                        rationaleMethod$quickpermissions_kotlin_release.invoke(quickPermissionsRequest);
                    }

                    @Override // com.livinglifetechway.quickpermissions_kotlin.util.PermissionCheckerFragment.QuickPermissionsCallback
                    public void onPermissionsPermanentlyDenied(QuickPermissionsRequest quickPermissionsRequest) {
                        Function1<QuickPermissionsRequest, Unit> permanentDeniedMethod$quickpermissions_kotlin_release;
                        if (quickPermissionsRequest == null || (permanentDeniedMethod$quickpermissions_kotlin_release = quickPermissionsRequest.getPermanentDeniedMethod$quickpermissions_kotlin_release()) == null) {
                            return;
                        }
                        permanentDeniedMethod$quickpermissions_kotlin_release.invoke(quickPermissionsRequest);
                    }
                });
                QuickPermissionsRequest quickPermissionsRequest = new QuickPermissionsRequest(permissionCheckerFragment, (String[]) Arrays.copyOf(strArr, strArr.length), false, null, false, null, null, null, null, null, null, 2044, null);
                quickPermissionsRequest.setHandleRationale(quickPermissionsOptions.getHandleRationale());
                quickPermissionsRequest.setHandlePermanentlyDenied(quickPermissionsOptions.getHandlePermanentlyDenied());
                quickPermissionsRequest.setRationaleMessage(StringsKt.isBlank(quickPermissionsOptions.getRationaleMessage()) ? "These permissions are required to perform this feature. Please allow us to use this feature. " : quickPermissionsOptions.getRationaleMessage());
                quickPermissionsRequest.setPermanentlyDeniedMessage(StringsKt.isBlank(quickPermissionsOptions.getPermanentlyDeniedMessage()) ? "Some permissions are permanently denied which are required to perform this operation. Please open app settings to grant these permissions." : quickPermissionsOptions.getPermanentlyDeniedMessage());
                quickPermissionsRequest.setRationaleMethod$quickpermissions_kotlin_release(quickPermissionsOptions.getRationaleMethod());
                quickPermissionsRequest.setPermanentDeniedMethod$quickpermissions_kotlin_release(quickPermissionsOptions.getPermanentDeniedMethod());
                quickPermissionsRequest.setPermissionsDeniedMethod$quickpermissions_kotlin_release(quickPermissionsOptions.getPermissionsDeniedMethod());
                permissionCheckerFragment.setRequestPermissionsRequest(quickPermissionsRequest);
                permissionCheckerFragment.requestPermissionsFromUser();
            }
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Found ");
        if (obj == null) {
            Intrinsics.throwNpe();
        }
        sb.append(obj.getClass().getCanonicalName());
        sb.append(" : No support from any classes other than AppCompatActivity/Fragment");
        throw new IllegalStateException(sb.toString());
    }
}
