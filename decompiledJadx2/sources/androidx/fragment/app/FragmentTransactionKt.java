package androidx.fragment.app;

import android.os.Bundle;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.iflytek.aiui.AIUIConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: FragmentTransaction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\b\b\u0001\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b\u001a-\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b\u001a;\u0010\n\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\b\b\u0001\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tH\u0086\b¨\u0006\u000b"}, m3961d2 = {TmpConstant.GROUP_OP_ADD, "Landroidx/fragment/app/FragmentTransaction;", "F", "Landroidx/fragment/app/Fragment;", "containerViewId", "", AIUIConstant.KEY_TAG, "", "args", "Landroid/os/Bundle;", "replace", "fragment-ktx_release"}, m3962k = 2, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class FragmentTransactionKt {
    public static /* synthetic */ FragmentTransaction add$default(FragmentTransaction add, int i, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = (String) null;
        }
        if ((i2 & 4) != 0) {
            bundle = (Bundle) null;
        }
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction add2 = add.add(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(containerViewId, F::class.java, args, tag)");
        return add2;
    }

    public static final /* synthetic */ <F extends Fragment> FragmentTransaction add(FragmentTransaction add, int i, String str, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction add2 = add.add(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(containerViewId, F::class.java, args, tag)");
        return add2;
    }

    public static /* synthetic */ FragmentTransaction add$default(FragmentTransaction add, String tag, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = (Bundle) null;
        }
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction add2 = add.add(Fragment.class, bundle, tag);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(F::class.java, args, tag)");
        return add2;
    }

    public static final /* synthetic */ <F extends Fragment> FragmentTransaction add(FragmentTransaction add, String tag, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(add, "$this$add");
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction add2 = add.add(Fragment.class, bundle, tag);
        Intrinsics.checkExpressionValueIsNotNull(add2, "add(F::class.java, args, tag)");
        return add2;
    }

    public static /* synthetic */ FragmentTransaction replace$default(FragmentTransaction replace, int i, String str, Bundle bundle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = (String) null;
        }
        if ((i2 & 4) != 0) {
            bundle = (Bundle) null;
        }
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction replace2 = replace.replace(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(replace2, "replace(containerViewId, F::class.java, args, tag)");
        return replace2;
    }

    public static final /* synthetic */ <F extends Fragment> FragmentTransaction replace(FragmentTransaction replace, int i, String str, Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(replace, "$this$replace");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction replace2 = replace.replace(i, Fragment.class, bundle, str);
        Intrinsics.checkExpressionValueIsNotNull(replace2, "replace(containerViewId, F::class.java, args, tag)");
        return replace2;
    }
}
