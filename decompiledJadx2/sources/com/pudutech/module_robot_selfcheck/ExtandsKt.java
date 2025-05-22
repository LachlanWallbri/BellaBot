package com.pudutech.module_robot_selfcheck;

import android.view.View;
import android.widget.Checkable;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mirsdk.map.EncodeUtils;
import com.pudutech.resources.language.Option;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: Extands.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0003\u001a2\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u00032\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003\u001a\n\u0010\u0010\u001a\u00020\u0011*\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\u0011*\u00020\u0011\u001a+\u0010\u0013\u001a\u00020\u0014\"\b\b\u0000\u0010\u0007*\u00020\b*\u0002H\u00072\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0005¢\u0006\u0002\u0010\u0018\u001a<\u0010\u0013\u001a\u00020\u0014\"\b\b\u0000\u0010\u0007*\u00020\b*\u0002H\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00052\u0014\b\u0004\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00140\u001aH\u0086\b¢\u0006\u0002\u0010\u001b\"\"\u0010\u0000\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0003X\u0082\u000e¢\u0006\u0002\n\u0000\"2\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0007*\u00020\b*\u0002H\u00072\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u001c"}, m3961d2 = {"supportList", "Ljava/util/ArrayList;", "Lcom/pudutech/resources/language/Option;", "Lkotlin/collections/ArrayList;", ES6Iterator.VALUE_PROPERTY, "", "lastClickTime", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "getLastClickTime", "(Landroid/view/View;)J", "setLastClickTime", "(Landroid/view/View;J)V", "getList", "setSupportedLocaleList", "data", "decodeMapName", "", "encodeMapName", "singleClick", "", "onClickListener", "Landroid/view/View$OnClickListener;", "time", "(Landroid/view/View;Landroid/view/View$OnClickListener;J)V", "block", "Lkotlin/Function1;", "(Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "module_robot_selfcheck_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ExtandsKt {
    private static ArrayList<Option> supportList;

    public static /* synthetic */ void singleClick$default(View singleClick, long j, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 800;
        }
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ExtandsKt$singleClick$1(singleClick, j, block));
    }

    public static final <T extends View> void singleClick(T singleClick, long j, Function1<? super T, Unit> block) {
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ExtandsKt$singleClick$1(singleClick, j, block));
    }

    public static /* synthetic */ void singleClick$default(View view, View.OnClickListener onClickListener, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = 800;
        }
        singleClick(view, onClickListener, j);
    }

    public static final <T extends View> void singleClick(final T singleClick, final View.OnClickListener onClickListener, final long j) {
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(onClickListener, "onClickListener");
        singleClick.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.module_robot_selfcheck.ExtandsKt$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ExtandsKt.getLastClickTime(singleClick) > j || (singleClick instanceof Checkable)) {
                    ExtandsKt.setLastClickTime(singleClick, currentTimeMillis);
                    onClickListener.onClick(singleClick);
                }
            }
        });
    }

    public static final <T extends View> void setLastClickTime(T lastClickTime, long j) {
        Intrinsics.checkParameterIsNotNull(lastClickTime, "$this$lastClickTime");
        lastClickTime.setTag(1766613352, Long.valueOf(j));
    }

    public static final <T extends View> long getLastClickTime(T lastClickTime) {
        Intrinsics.checkParameterIsNotNull(lastClickTime, "$this$lastClickTime");
        Object tag = lastClickTime.getTag(1766613352);
        if (!(tag instanceof Long)) {
            tag = null;
        }
        Long l = (Long) tag;
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }

    public static final String encodeMapName(String encodeMapName) {
        Intrinsics.checkParameterIsNotNull(encodeMapName, "$this$encodeMapName");
        byte[] bytes = encodeMapName.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String base64Encode2String = EncodeUtils.base64Encode2String(bytes);
        Intrinsics.checkExpressionValueIsNotNull(base64Encode2String, "base64Encode2String");
        return StringsKt.replace$default(StringsKt.replace$default(base64Encode2String, "+", "_", false, 4, (Object) null), "/", "-", false, 4, (Object) null);
    }

    public static final String decodeMapName(String decodeMapName) {
        Intrinsics.checkParameterIsNotNull(decodeMapName, "$this$decodeMapName");
        return EncodeUtils.base64Encode(StringsKt.replace$default(StringsKt.replace$default(decodeMapName, "_", "+", false, 4, (Object) null), "-", "/", false, 4, (Object) null)).toString();
    }

    public static final ArrayList<Option> setSupportedLocaleList(ArrayList<Option> data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        supportList = data;
        return supportList;
    }

    public static final ArrayList<Option> getList() {
        return supportList;
    }
}
