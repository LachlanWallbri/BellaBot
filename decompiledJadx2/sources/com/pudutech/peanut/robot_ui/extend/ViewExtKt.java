package com.pudutech.peanut.robot_ui.extend;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.util.PlaySound;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ViewExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u000e2\u0006\u0010\f\u001a\u00020\r\u001a-\u0010\u000f\u001a\u00020\n*\u00020\u00042!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\n0\u0011\u001a+\u0010\u0015\u001a\u00020\n\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0001¢\u0006\u0002\u0010\u0019\u001a<\u0010\u0015\u001a\u00020\n\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00012\u0014\b\u0004\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\n0\u0011H\u0086\b¢\u0006\u0002\u0010\u001b\"2\u0010\u0002\u001a\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001c"}, m3961d2 = {ES6Iterator.VALUE_PROPERTY, "", "lastClickTime", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "getLastClickTime", "(Landroid/view/View;)J", "setLastClickTime", "(Landroid/view/View;J)V", "clipData", "", "Landroid/widget/TextView;", "context", "Landroid/content/Context;", "", "onSingleClick", "listener", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "singleClick", "onClickListener", "Landroid/view/View$OnClickListener;", "time", "(Landroid/view/View;Landroid/view/View$OnClickListener;J)V", "block", "(Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "robot_ui_peanutRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ViewExtKt {
    public static final void onSingleClick(final View onSingleClick, final Function1<? super View, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(onSingleClick, "$this$onSingleClick");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = 0L;
        onSingleClick.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.extend.ViewExtKt$onSingleClick$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = currentTimeMillis - longRef.element;
                long j2 = 500;
                if (0 > j || j2 < j) {
                    listener.invoke(onSingleClick);
                    PlaySound.playBtnVoice(RobotContext.INSTANCE.getContext(), C5508R.raw.btn_click_1);
                    Pdlog.m3274e("View", "上次时间：" + longRef.element + ", 间隔时间：" + j);
                }
                longRef.element = currentTimeMillis;
            }
        });
    }

    public static final void clipData(TextView clipData, Context context) {
        String obj;
        Intrinsics.checkParameterIsNotNull(clipData, "$this$clipData");
        Intrinsics.checkParameterIsNotNull(context, "context");
        CharSequence text = clipData.getText();
        if (text == null || (obj = text.toString()) == null) {
            return;
        }
        clipData(obj, context);
    }

    public static final void clipData(String clipData, Context context) {
        Intrinsics.checkParameterIsNotNull(clipData, "$this$clipData");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("clipboard");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.content.ClipboardManager");
        }
        ((ClipboardManager) systemService).setPrimaryClip(ClipData.newPlainText("Label", StringsKt.trim((CharSequence) clipData).toString()));
    }

    public static /* synthetic */ void singleClick$default(View singleClick, long j, Function1 block, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 800;
        }
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ViewExtKt$singleClick$1(singleClick, j, block));
    }

    public static final <T extends View> void singleClick(T singleClick, long j, Function1<? super T, Unit> block) {
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ViewExtKt$singleClick$1(singleClick, j, block));
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
        singleClick.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.extend.ViewExtKt$singleClick$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ViewExtKt.getLastClickTime(singleClick) > j || (singleClick instanceof Checkable)) {
                    ViewExtKt.setLastClickTime(singleClick, currentTimeMillis);
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
}
