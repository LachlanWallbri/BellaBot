package com.livinglifetechway.quickpermissions_kotlin.util;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QuickPermissionsOptions.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t¢\u0006\u0002\u0010\u000eJ\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\u0017\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tHÆ\u0003J\u0017\u0010(\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tHÆ\u0003J\u0017\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tHÆ\u0003Jy\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t2\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\t2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tHÆ\u0001J\u0013\u0010+\u001a\u00020\u00032\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR(\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR(\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0016\"\u0004\b\"\u0010\u0018¨\u00060"}, m3961d2 = {"Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsOptions;", "", "handleRationale", "", "rationaleMessage", "", "handlePermanentlyDenied", "permanentlyDeniedMessage", "rationaleMethod", "Lkotlin/Function1;", "Lcom/livinglifetechway/quickpermissions_kotlin/util/QuickPermissionsRequest;", "", "permanentDeniedMethod", "permissionsDeniedMethod", "(ZLjava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getHandlePermanentlyDenied", "()Z", "setHandlePermanentlyDenied", "(Z)V", "getHandleRationale", "setHandleRationale", "getPermanentDeniedMethod", "()Lkotlin/jvm/functions/Function1;", "setPermanentDeniedMethod", "(Lkotlin/jvm/functions/Function1;)V", "getPermanentlyDeniedMessage", "()Ljava/lang/String;", "setPermanentlyDeniedMessage", "(Ljava/lang/String;)V", "getPermissionsDeniedMethod", "setPermissionsDeniedMethod", "getRationaleMessage", "setRationaleMessage", "getRationaleMethod", "setRationaleMethod", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "quickpermissions-kotlin_release"}, m3962k = 1, m3963mv = {1, 1, 13})
/* loaded from: classes.dex */
public final /* data */ class QuickPermissionsOptions {
    private boolean handlePermanentlyDenied;
    private boolean handleRationale;
    private Function1<? super QuickPermissionsRequest, Unit> permanentDeniedMethod;
    private String permanentlyDeniedMessage;
    private Function1<? super QuickPermissionsRequest, Unit> permissionsDeniedMethod;
    private String rationaleMessage;
    private Function1<? super QuickPermissionsRequest, Unit> rationaleMethod;

    public QuickPermissionsOptions() {
        this(false, null, false, null, null, null, null, 127, null);
    }

    public static /* synthetic */ QuickPermissionsOptions copy$default(QuickPermissionsOptions quickPermissionsOptions, boolean z, String str, boolean z2, String str2, Function1 function1, Function1 function12, Function1 function13, int i, Object obj) {
        if ((i & 1) != 0) {
            z = quickPermissionsOptions.handleRationale;
        }
        if ((i & 2) != 0) {
            str = quickPermissionsOptions.rationaleMessage;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            z2 = quickPermissionsOptions.handlePermanentlyDenied;
        }
        boolean z3 = z2;
        if ((i & 8) != 0) {
            str2 = quickPermissionsOptions.permanentlyDeniedMessage;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            function1 = quickPermissionsOptions.rationaleMethod;
        }
        Function1 function14 = function1;
        if ((i & 32) != 0) {
            function12 = quickPermissionsOptions.permanentDeniedMethod;
        }
        Function1 function15 = function12;
        if ((i & 64) != 0) {
            function13 = quickPermissionsOptions.permissionsDeniedMethod;
        }
        return quickPermissionsOptions.copy(z, str3, z3, str4, function14, function15, function13);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getHandleRationale() {
        return this.handleRationale;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRationaleMessage() {
        return this.rationaleMessage;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getHandlePermanentlyDenied() {
        return this.handlePermanentlyDenied;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPermanentlyDeniedMessage() {
        return this.permanentlyDeniedMessage;
    }

    public final Function1<QuickPermissionsRequest, Unit> component5() {
        return this.rationaleMethod;
    }

    public final Function1<QuickPermissionsRequest, Unit> component6() {
        return this.permanentDeniedMethod;
    }

    public final Function1<QuickPermissionsRequest, Unit> component7() {
        return this.permissionsDeniedMethod;
    }

    public final QuickPermissionsOptions copy(boolean handleRationale, String rationaleMessage, boolean handlePermanentlyDenied, String permanentlyDeniedMessage, Function1<? super QuickPermissionsRequest, Unit> rationaleMethod, Function1<? super QuickPermissionsRequest, Unit> permanentDeniedMethod, Function1<? super QuickPermissionsRequest, Unit> permissionsDeniedMethod) {
        Intrinsics.checkParameterIsNotNull(rationaleMessage, "rationaleMessage");
        Intrinsics.checkParameterIsNotNull(permanentlyDeniedMessage, "permanentlyDeniedMessage");
        return new QuickPermissionsOptions(handleRationale, rationaleMessage, handlePermanentlyDenied, permanentlyDeniedMessage, rationaleMethod, permanentDeniedMethod, permissionsDeniedMethod);
    }

    public boolean equals(Object other) {
        if (this != other) {
            if (other instanceof QuickPermissionsOptions) {
                QuickPermissionsOptions quickPermissionsOptions = (QuickPermissionsOptions) other;
                if ((this.handleRationale == quickPermissionsOptions.handleRationale) && Intrinsics.areEqual(this.rationaleMessage, quickPermissionsOptions.rationaleMessage)) {
                    if (!(this.handlePermanentlyDenied == quickPermissionsOptions.handlePermanentlyDenied) || !Intrinsics.areEqual(this.permanentlyDeniedMessage, quickPermissionsOptions.permanentlyDeniedMessage) || !Intrinsics.areEqual(this.rationaleMethod, quickPermissionsOptions.rationaleMethod) || !Intrinsics.areEqual(this.permanentDeniedMethod, quickPermissionsOptions.permanentDeniedMethod) || !Intrinsics.areEqual(this.permissionsDeniedMethod, quickPermissionsOptions.permissionsDeniedMethod)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    public int hashCode() {
        boolean z = this.handleRationale;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.rationaleMessage;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        boolean z2 = this.handlePermanentlyDenied;
        int i2 = (hashCode + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        String str2 = this.permanentlyDeniedMessage;
        int hashCode2 = (i2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Function1<? super QuickPermissionsRequest, Unit> function1 = this.rationaleMethod;
        int hashCode3 = (hashCode2 + (function1 != null ? function1.hashCode() : 0)) * 31;
        Function1<? super QuickPermissionsRequest, Unit> function12 = this.permanentDeniedMethod;
        int hashCode4 = (hashCode3 + (function12 != null ? function12.hashCode() : 0)) * 31;
        Function1<? super QuickPermissionsRequest, Unit> function13 = this.permissionsDeniedMethod;
        return hashCode4 + (function13 != null ? function13.hashCode() : 0);
    }

    public String toString() {
        return "QuickPermissionsOptions(handleRationale=" + this.handleRationale + ", rationaleMessage=" + this.rationaleMessage + ", handlePermanentlyDenied=" + this.handlePermanentlyDenied + ", permanentlyDeniedMessage=" + this.permanentlyDeniedMessage + ", rationaleMethod=" + this.rationaleMethod + ", permanentDeniedMethod=" + this.permanentDeniedMethod + ", permissionsDeniedMethod=" + this.permissionsDeniedMethod + ")";
    }

    public QuickPermissionsOptions(boolean z, String rationaleMessage, boolean z2, String permanentlyDeniedMessage, Function1<? super QuickPermissionsRequest, Unit> function1, Function1<? super QuickPermissionsRequest, Unit> function12, Function1<? super QuickPermissionsRequest, Unit> function13) {
        Intrinsics.checkParameterIsNotNull(rationaleMessage, "rationaleMessage");
        Intrinsics.checkParameterIsNotNull(permanentlyDeniedMessage, "permanentlyDeniedMessage");
        this.handleRationale = z;
        this.rationaleMessage = rationaleMessage;
        this.handlePermanentlyDenied = z2;
        this.permanentlyDeniedMessage = permanentlyDeniedMessage;
        this.rationaleMethod = function1;
        this.permanentDeniedMethod = function12;
        this.permissionsDeniedMethod = function13;
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

    public /* synthetic */ QuickPermissionsOptions(boolean z, String str, boolean z2, String str2, Function1 function1, Function1 function12, Function1 function13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? "" : str, (i & 4) == 0 ? z2 : true, (i & 8) == 0 ? str2 : "", (i & 16) != 0 ? (Function1) null : function1, (i & 32) != 0 ? (Function1) null : function12, (i & 64) != 0 ? (Function1) null : function13);
    }

    public final Function1<QuickPermissionsRequest, Unit> getRationaleMethod() {
        return this.rationaleMethod;
    }

    public final void setRationaleMethod(Function1<? super QuickPermissionsRequest, Unit> function1) {
        this.rationaleMethod = function1;
    }

    public final Function1<QuickPermissionsRequest, Unit> getPermanentDeniedMethod() {
        return this.permanentDeniedMethod;
    }

    public final void setPermanentDeniedMethod(Function1<? super QuickPermissionsRequest, Unit> function1) {
        this.permanentDeniedMethod = function1;
    }

    public final Function1<QuickPermissionsRequest, Unit> getPermissionsDeniedMethod() {
        return this.permissionsDeniedMethod;
    }

    public final void setPermissionsDeniedMethod(Function1<? super QuickPermissionsRequest, Unit> function1) {
        this.permissionsDeniedMethod = function1;
    }
}
