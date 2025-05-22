package com.pudutech.freeinstall_ui.utils;

import android.content.Context;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.freeinstall_ui.AppContext;
import com.pudutech.module_freeinstall_ui.C5362R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CommonDialogUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/CommonDialogUtils;", "", "()V", "Companion", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CommonDialogUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: CommonDialogUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J^\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u000fJ:\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/freeinstall_ui/utils/CommonDialogUtils$Companion;", "", "()V", "showDoubleCommonDialog", "", "context", "Landroid/content/Context;", "title", "", AIUIConstant.KEY_CONTENT, "btnLeftString", "btnRightString", "isCloseShow", "", "btLeftClick", "Lkotlin/Function0;", "btRightClick", "showSingleCommonDialog", "btnString", "module_freeinstall_ui_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void showSingleCommonDialog$default(Companion companion, Context context, String str, String str2, String str3, Function0 function0, int i, Object obj) {
            if ((i & 2) != 0) {
                str = AppContext.INSTANCE.getContext().getString(C5362R.string.tips);
                Intrinsics.checkExpressionValueIsNotNull(str, "AppContext.context.getString(R.string.tips)");
            }
            String str4 = str;
            if ((i & 8) != 0) {
                str3 = AppContext.INSTANCE.getContext().getString(C5362R.string.confirm_free);
                Intrinsics.checkExpressionValueIsNotNull(str3, "AppContext.context.getSt…ng(R.string.confirm_free)");
            }
            String str5 = str3;
            if ((i & 16) != 0) {
                function0 = new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.CommonDialogUtils$Companion$showSingleCommonDialog$1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                };
            }
            companion.showSingleCommonDialog(context, str4, str2, str5, function0);
        }

        public final void showSingleCommonDialog(Context context, String title, String content, String btnString, Function0<Unit> btRightClick) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(content, "content");
            Intrinsics.checkParameterIsNotNull(btnString, "btnString");
            Intrinsics.checkParameterIsNotNull(btRightClick, "btRightClick");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new CommonDialogUtils$Companion$showSingleCommonDialog$2(context, title, content, btnString, btRightClick, null), 2, null);
        }

        public static /* synthetic */ void showDoubleCommonDialog$default(Companion companion, Context context, String str, String str2, String str3, String str4, boolean z, Function0 function0, Function0 function02, int i, Object obj) {
            String str5;
            String str6;
            String str7;
            if ((i & 2) != 0) {
                String string = AppContext.INSTANCE.getContext().getString(C5362R.string.tips);
                Intrinsics.checkExpressionValueIsNotNull(string, "AppContext.context.getString(R.string.tips)");
                str5 = string;
            } else {
                str5 = str;
            }
            if ((i & 8) != 0) {
                String string2 = AppContext.INSTANCE.getContext().getString(C5362R.string.cancel);
                Intrinsics.checkExpressionValueIsNotNull(string2, "AppContext.context.getString(R.string.cancel)");
                str6 = string2;
            } else {
                str6 = str3;
            }
            if ((i & 16) != 0) {
                String string3 = AppContext.INSTANCE.getContext().getString(C5362R.string.confirm_free);
                Intrinsics.checkExpressionValueIsNotNull(string3, "AppContext.context.getSt…ng(R.string.confirm_free)");
                str7 = string3;
            } else {
                str7 = str4;
            }
            companion.showDoubleCommonDialog(context, str5, str2, str6, str7, (i & 32) != 0 ? false : z, (i & 64) != 0 ? new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.CommonDialogUtils$Companion$showDoubleCommonDialog$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function0, (i & 128) != 0 ? new Function0<Unit>() { // from class: com.pudutech.freeinstall_ui.utils.CommonDialogUtils$Companion$showDoubleCommonDialog$2
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            } : function02);
        }

        public final void showDoubleCommonDialog(Context context, String title, String content, String btnLeftString, String btnRightString, boolean isCloseShow, Function0<Unit> btLeftClick, Function0<Unit> btRightClick) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(title, "title");
            Intrinsics.checkParameterIsNotNull(content, "content");
            Intrinsics.checkParameterIsNotNull(btnLeftString, "btnLeftString");
            Intrinsics.checkParameterIsNotNull(btnRightString, "btnRightString");
            Intrinsics.checkParameterIsNotNull(btLeftClick, "btLeftClick");
            Intrinsics.checkParameterIsNotNull(btRightClick, "btRightClick");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new CommonDialogUtils$Companion$showDoubleCommonDialog$3(context, title, content, btnRightString, btnLeftString, isCloseShow, btLeftClick, btRightClick, null), 2, null);
        }
    }
}
