package com.livinglifetechway.quickpermissions_kotlin.util;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QuickPermissionsRequest.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b6\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00ad\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0013J\u0006\u00101\u001a\u00020\u000eJ\t\u00102\u001a\u00020\u0003HÂ\u0003J\u0014\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0014\u00104\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\u0015J\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003¢\u0006\u0002\u0010\u0015J\t\u00106\u001a\u00020\bHÆ\u0003J\t\u00107\u001a\u00020\u0006HÆ\u0003J\t\u00108\u001a\u00020\bHÆ\u0003J\t\u00109\u001a\u00020\u0006HÆ\u0003J\u001c\u0010:\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÀ\u0003¢\u0006\u0002\b;J\u001c\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÀ\u0003¢\u0006\u0002\b=J\u001c\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rHÀ\u0003¢\u0006\u0002\b?J¸\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001¢\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\b2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010D\u001a\u00020EHÖ\u0001J\u0006\u0010F\u001a\u00020\u000eJ\u0006\u0010G\u001a\u00020\u000eJ\t\u0010H\u001a\u00020\u0006HÖ\u0001R\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\n\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR(\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b'\u0010\u0015\"\u0004\b(\u0010\u0017R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b)\u0010\u0015\"\u0004\b*\u0010\u0017R(\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010 \"\u0004\b,\u0010\"R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010$\"\u0004\b.\u0010&R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010 \"\u0004\b0\u0010\"R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, m3961d2 = {"Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsRequest;", "", TypedValues.Attributes.S_TARGET, "Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment;", "permissions", "", "", "handleRationale", "", "rationaleMessage", "handlePermanentlyDenied", "permanentlyDeniedMessage", "rationaleMethod", "Lkotlin/Function1;", "", "permanentDeniedMethod", "permissionsDeniedMethod", "deniedPermissions", "permanentlyDeniedPermissions", "(Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment;[Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;[Ljava/lang/String;[Ljava/lang/String;)V", "getDeniedPermissions", "()[Ljava/lang/String;", "setDeniedPermissions", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "getHandlePermanentlyDenied", "()Z", "setHandlePermanentlyDenied", "(Z)V", "getHandleRationale", "setHandleRationale", "getPermanentDeniedMethod$quickpermissions_kotlin_release", "()Lkotlin/jvm/functions/Function1;", "setPermanentDeniedMethod$quickpermissions_kotlin_release", "(Lkotlin/jvm/functions/Function1;)V", "getPermanentlyDeniedMessage", "()Ljava/lang/String;", "setPermanentlyDeniedMessage", "(Ljava/lang/String;)V", "getPermanentlyDeniedPermissions", "setPermanentlyDeniedPermissions", "getPermissions", "setPermissions", "getPermissionsDeniedMethod$quickpermissions_kotlin_release", "setPermissionsDeniedMethod$quickpermissions_kotlin_release", "getRationaleMessage", "setRationaleMessage", "getRationaleMethod$quickpermissions_kotlin_release", "setRationaleMethod$quickpermissions_kotlin_release", "cancel", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component7$quickpermissions_kotlin_release", "component8", "component8$quickpermissions_kotlin_release", "component9", "component9$quickpermissions_kotlin_release", "copy", "(Lcom/livinglifetechway/quickpermissions_kotlin/util/PermissionCheckerFragment;[Ljava/lang/String;ZLjava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;[Ljava/lang/String;[Ljava/lang/String;)Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsRequest;", "equals", "other", "hashCode", "", "openAppSettings", "proceed", "toString", "quickpermissions-kotlin_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes.dex */
public final /* data */ class QuickPermissionsRequest {
    private String[] deniedPermissions;
    private boolean handlePermanentlyDenied;
    private boolean handleRationale;
    private Function1<? super QuickPermissionsRequest, Unit> permanentDeniedMethod;
    private String permanentlyDeniedMessage;
    private String[] permanentlyDeniedPermissions;
    private String[] permissions;
    private Function1<? super QuickPermissionsRequest, Unit> permissionsDeniedMethod;
    private String rationaleMessage;
    private Function1<? super QuickPermissionsRequest, Unit> rationaleMethod;
    private PermissionCheckerFragment target;

    /* renamed from: component1, reason: from getter */
    private final PermissionCheckerFragment getTarget() {
        return this.target;
    }

    /* renamed from: component10, reason: from getter */
    public final String[] getDeniedPermissions() {
        return this.deniedPermissions;
    }

    /* renamed from: component11, reason: from getter */
    public final String[] getPermanentlyDeniedPermissions() {
        return this.permanentlyDeniedPermissions;
    }

    /* renamed from: component2, reason: from getter */
    public final String[] getPermissions() {
        return this.permissions;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getHandleRationale() {
        return this.handleRationale;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRationaleMessage() {
        return this.rationaleMessage;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getHandlePermanentlyDenied() {
        return this.handlePermanentlyDenied;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPermanentlyDeniedMessage() {
        return this.permanentlyDeniedMessage;
    }

    public final Function1<QuickPermissionsRequest, Unit> component7$quickpermissions_kotlin_release() {
        return this.rationaleMethod;
    }

    public final Function1<QuickPermissionsRequest, Unit> component8$quickpermissions_kotlin_release() {
        return this.permanentDeniedMethod;
    }

    public final Function1<QuickPermissionsRequest, Unit> component9$quickpermissions_kotlin_release() {
        return this.permissionsDeniedMethod;
    }

    public final QuickPermissionsRequest copy(PermissionCheckerFragment target, String[] permissions2, boolean handleRationale, String rationaleMessage, boolean handlePermanentlyDenied, String permanentlyDeniedMessage, Function1<? super QuickPermissionsRequest, Unit> rationaleMethod, Function1<? super QuickPermissionsRequest, Unit> permanentDeniedMethod, Function1<? super QuickPermissionsRequest, Unit> permissionsDeniedMethod, String[] deniedPermissions, String[] permanentlyDeniedPermissions) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(rationaleMessage, "rationaleMessage");
        Intrinsics.checkParameterIsNotNull(permanentlyDeniedMessage, "permanentlyDeniedMessage");
        Intrinsics.checkParameterIsNotNull(deniedPermissions, "deniedPermissions");
        Intrinsics.checkParameterIsNotNull(permanentlyDeniedPermissions, "permanentlyDeniedPermissions");
        return new QuickPermissionsRequest(target, permissions2, handleRationale, rationaleMessage, handlePermanentlyDenied, permanentlyDeniedMessage, rationaleMethod, permanentDeniedMethod, permissionsDeniedMethod, deniedPermissions, permanentlyDeniedPermissions);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof QuickPermissionsRequest) {
                QuickPermissionsRequest quickPermissionsRequest = (QuickPermissionsRequest) other;
                if (Intrinsics.areEqual(this.target, quickPermissionsRequest.target) && Intrinsics.areEqual(this.permissions, quickPermissionsRequest.permissions)) {
                    if ((this.handleRationale == quickPermissionsRequest.handleRationale) && Intrinsics.areEqual(this.rationaleMessage, quickPermissionsRequest.rationaleMessage)) {
                        if (!(this.handlePermanentlyDenied == quickPermissionsRequest.handlePermanentlyDenied) || !Intrinsics.areEqual(this.permanentlyDeniedMessage, quickPermissionsRequest.permanentlyDeniedMessage) || !Intrinsics.areEqual(this.rationaleMethod, quickPermissionsRequest.rationaleMethod) || !Intrinsics.areEqual(this.permanentDeniedMethod, quickPermissionsRequest.permanentDeniedMethod) || !Intrinsics.areEqual(this.permissionsDeniedMethod, quickPermissionsRequest.permissionsDeniedMethod) || !Intrinsics.areEqual(this.deniedPermissions, quickPermissionsRequest.deniedPermissions) || !Intrinsics.areEqual(this.permanentlyDeniedPermissions, quickPermissionsRequest.permanentlyDeniedPermissions)) {
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        PermissionCheckerFragment permissionCheckerFragment = this.target;
        int hashCode = (permissionCheckerFragment != null ? permissionCheckerFragment.hashCode() : 0) * 31;
        String[] strArr = this.permissions;
        int hashCode2 = (hashCode + (strArr != null ? Arrays.hashCode(strArr) : 0)) * 31;
        boolean z = this.handleRationale;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        String str = this.rationaleMessage;
        int hashCode3 = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        boolean z2 = this.handlePermanentlyDenied;
        int i3 = (hashCode3 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        String str2 = this.permanentlyDeniedMessage;
        int hashCode4 = (i3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Function1<? super QuickPermissionsRequest, Unit> function1 = this.rationaleMethod;
        int hashCode5 = (hashCode4 + (function1 != null ? function1.hashCode() : 0)) * 31;
        Function1<? super QuickPermissionsRequest, Unit> function12 = this.permanentDeniedMethod;
        int hashCode6 = (hashCode5 + (function12 != null ? function12.hashCode() : 0)) * 31;
        Function1<? super QuickPermissionsRequest, Unit> function13 = this.permissionsDeniedMethod;
        int hashCode7 = (hashCode6 + (function13 != null ? function13.hashCode() : 0)) * 31;
        String[] strArr2 = this.deniedPermissions;
        int hashCode8 = (hashCode7 + (strArr2 != null ? Arrays.hashCode(strArr2) : 0)) * 31;
        String[] strArr3 = this.permanentlyDeniedPermissions;
        return hashCode8 + (strArr3 != null ? Arrays.hashCode(strArr3) : 0);
    }

    public String toString() {
        return "QuickPermissionsRequest(target=" + this.target + ", permissions=" + Arrays.toString(this.permissions) + ", handleRationale=" + this.handleRationale + ", rationaleMessage=" + this.rationaleMessage + ", handlePermanentlyDenied=" + this.handlePermanentlyDenied + ", permanentlyDeniedMessage=" + this.permanentlyDeniedMessage + ", rationaleMethod=" + this.rationaleMethod + ", permanentDeniedMethod=" + this.permanentDeniedMethod + ", permissionsDeniedMethod=" + this.permissionsDeniedMethod + ", deniedPermissions=" + Arrays.toString(this.deniedPermissions) + ", permanentlyDeniedPermissions=" + Arrays.toString(this.permanentlyDeniedPermissions) + ")";
    }

    public QuickPermissionsRequest(PermissionCheckerFragment target, String[] permissions2, boolean z, String rationaleMessage, boolean z2, String permanentlyDeniedMessage, Function1<? super QuickPermissionsRequest, Unit> function1, Function1<? super QuickPermissionsRequest, Unit> function12, Function1<? super QuickPermissionsRequest, Unit> function13, String[] deniedPermissions, String[] permanentlyDeniedPermissions) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(permissions2, "permissions");
        Intrinsics.checkParameterIsNotNull(rationaleMessage, "rationaleMessage");
        Intrinsics.checkParameterIsNotNull(permanentlyDeniedMessage, "permanentlyDeniedMessage");
        Intrinsics.checkParameterIsNotNull(deniedPermissions, "deniedPermissions");
        Intrinsics.checkParameterIsNotNull(permanentlyDeniedPermissions, "permanentlyDeniedPermissions");
        this.target = target;
        this.permissions = permissions2;
        this.handleRationale = z;
        this.rationaleMessage = rationaleMessage;
        this.handlePermanentlyDenied = z2;
        this.permanentlyDeniedMessage = permanentlyDeniedMessage;
        this.rationaleMethod = function1;
        this.permanentDeniedMethod = function12;
        this.permissionsDeniedMethod = function13;
        this.deniedPermissions = deniedPermissions;
        this.permanentlyDeniedPermissions = permanentlyDeniedPermissions;
    }

    public final String[] getPermissions() {
        return this.permissions;
    }

    public final void setPermissions(String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "<set-?>");
        this.permissions = strArr;
    }

    public final boolean getHandleRationale() {
        return this.handleRationale;
    }

    public final void setHandleRationale(boolean z) {
        this.handleRationale = z;
    }

    public final String getRationaleMessage() {
        return this.rationaleMessage;
    }

    public final void setRationaleMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.rationaleMessage = str;
    }

    public final boolean getHandlePermanentlyDenied() {
        return this.handlePermanentlyDenied;
    }

    public final void setHandlePermanentlyDenied(boolean z) {
        this.handlePermanentlyDenied = z;
    }

    public final String getPermanentlyDeniedMessage() {
        return this.permanentlyDeniedMessage;
    }

    public final void setPermanentlyDeniedMessage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.permanentlyDeniedMessage = str;
    }

    public /* synthetic */ QuickPermissionsRequest(PermissionCheckerFragment permissionCheckerFragment, String[] strArr, boolean z, String str, boolean z2, String str2, Function1 function1, Function1 function12, Function1 function13, String[] strArr2, String[] strArr3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(permissionCheckerFragment, (i & 2) != 0 ? new String[0] : strArr, (i & 4) != 0 ? true : z, (i & 8) != 0 ? "" : str, (i & 16) == 0 ? z2 : true, (i & 32) == 0 ? str2 : "", (i & 64) != 0 ? (Function1) null : function1, (i & 128) != 0 ? (Function1) null : function12, (i & 256) != 0 ? (Function1) null : function13, (i & 512) != 0 ? new String[0] : strArr2, (i & 1024) != 0 ? new String[0] : strArr3);
    }

    public final Function1<QuickPermissionsRequest, Unit> getRationaleMethod$quickpermissions_kotlin_release() {
        return this.rationaleMethod;
    }

    public final void setRationaleMethod$quickpermissions_kotlin_release(Function1<? super QuickPermissionsRequest, Unit> function1) {
        this.rationaleMethod = function1;
    }

    public final Function1<QuickPermissionsRequest, Unit> getPermanentDeniedMethod$quickpermissions_kotlin_release() {
        return this.permanentDeniedMethod;
    }

    public final void setPermanentDeniedMethod$quickpermissions_kotlin_release(Function1<? super QuickPermissionsRequest, Unit> function1) {
        this.permanentDeniedMethod = function1;
    }

    public final Function1<QuickPermissionsRequest, Unit> getPermissionsDeniedMethod$quickpermissions_kotlin_release() {
        return this.permissionsDeniedMethod;
    }

    public final void setPermissionsDeniedMethod$quickpermissions_kotlin_release(Function1<? super QuickPermissionsRequest, Unit> function1) {
        this.permissionsDeniedMethod = function1;
    }

    public final String[] getDeniedPermissions() {
        return this.deniedPermissions;
    }

    public final void setDeniedPermissions(String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "<set-?>");
        this.deniedPermissions = strArr;
    }

    public final String[] getPermanentlyDeniedPermissions() {
        return this.permanentlyDeniedPermissions;
    }

    public final void setPermanentlyDeniedPermissions(String[] strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "<set-?>");
        this.permanentlyDeniedPermissions = strArr;
    }

    public final void proceed() {
        this.target.requestPermissionsFromUser();
    }

    public final void cancel() {
        this.target.clean();
    }

    public final void openAppSettings() {
        this.target.openAppSettings();
    }
}
