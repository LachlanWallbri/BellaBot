package com.pudutech.bumblebee.robot_ui.ui_utils;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.apache.commons.logging.LogFactory;

/* compiled from: ViewExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0003\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0006\u001a\u0010\u0010\u0005\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00060\u0007\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0006\u001a\u0010\u0010\b\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00060\u0007\u001a\n\u0010\t\u001a\u00020\u0001*\u00020\u0006\u001a\n\u0010\n\u001a\u00020\u0001*\u00020\u0006\u001a*\u0010\u000b\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0011\u001a\n\u0010\u0012\u001a\u00020\u0001*\u00020\u0006\u001a\u0010\u0010\u0012\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00060\u0007\u001aR\u0010\u0013\u001a\u00020\u0001*\u00020\u00062\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00152\b\b\u0002\u0010\u0018\u001a\u00020\r2#\b\u0004\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00010\u001aH\u0086\b¨\u0006\u001e"}, m3961d2 = {"setInsertionDisabled", "", "editText", "Landroid/widget/EditText;", "disableCopyAndPaste", "gone", "Landroid/view/View;", "", "hide", "isGoneView", "isShowView", "setFastClickListener", "times", "", "filterMillis", "", "action", "Lkotlin/Function0;", "show", "singleClick", "param", "", "", "", LogFactory.PRIORITY_KEY, "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "v", "robot_ui_robotRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ViewExtKt {
    public static final void gone(View gone) {
        Intrinsics.checkParameterIsNotNull(gone, "$this$gone");
        gone.setVisibility(8);
    }

    public static final void isGoneView(View isGoneView) {
        Intrinsics.checkParameterIsNotNull(isGoneView, "$this$isGoneView");
        if (isGoneView.isShown()) {
            isGoneView.setVisibility(8);
        }
    }

    public static final void hide(View hide) {
        Intrinsics.checkParameterIsNotNull(hide, "$this$hide");
        hide.setVisibility(4);
    }

    public static final void show(View show) {
        Intrinsics.checkParameterIsNotNull(show, "$this$show");
        show.setVisibility(0);
    }

    public static final void isShowView(View isShowView) {
        Intrinsics.checkParameterIsNotNull(isShowView, "$this$isShowView");
        if (isShowView.isShown()) {
            return;
        }
        isShowView.setVisibility(0);
    }

    public static /* synthetic */ void setFastClickListener$default(View view, int i, long j, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j = 500;
        }
        setFastClickListener(view, i, j, function0);
    }

    public static final void setFastClickListener(View setFastClickListener, final int i, final long j, final Function0<Unit> action) {
        Intrinsics.checkParameterIsNotNull(setFastClickListener, "$this$setFastClickListener");
        Intrinsics.checkParameterIsNotNull(action, "action");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        final Ref.LongRef longRef = new Ref.LongRef();
        longRef.element = 0L;
        setFastClickListener.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt$setFastClickListener$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - longRef.element < j || longRef.element == 0) {
                    intRef.element++;
                    longRef.element = currentTimeMillis;
                } else {
                    intRef.element = 0;
                    longRef.element = 0L;
                }
                if (intRef.element >= i) {
                    intRef.element = 0;
                    longRef.element = 0L;
                    action.invoke();
                }
            }
        });
    }

    public static final void disableCopyAndPaste(final EditText disableCopyAndPaste) {
        Intrinsics.checkParameterIsNotNull(disableCopyAndPaste, "$this$disableCopyAndPaste");
        try {
            disableCopyAndPaste.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt$disableCopyAndPaste$1
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return true;
                }
            });
            disableCopyAndPaste.setLongClickable(false);
            disableCopyAndPaste.setOnTouchListener(new View.OnTouchListener() { // from class: com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt$disableCopyAndPaste$2
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent event) {
                    Intrinsics.checkExpressionValueIsNotNull(event, "event");
                    if (event.getAction() != 0) {
                        return false;
                    }
                    ViewExtKt.setInsertionDisabled(disableCopyAndPaste);
                    return false;
                }
            });
            disableCopyAndPaste.setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt$disableCopyAndPaste$3
                @Override // android.view.ActionMode.Callback
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override // android.view.ActionMode.Callback
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override // android.view.ActionMode.Callback
                public void onDestroyActionMode(ActionMode mode) {
                }

                @Override // android.view.ActionMode.Callback
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setInsertionDisabled(EditText editText) {
        try {
            Field declaredField = TextView.class.getDeclaredField("mEditor");
            Intrinsics.checkExpressionValueIsNotNull(declaredField, "TextView::class.java.getDeclaredField(\"mEditor\")");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(editText);
            Intrinsics.checkExpressionValueIsNotNull(obj, "editorField.get(editText)");
            Class<?> cls = Class.forName("android.widget.Editor");
            Field declaredField2 = cls.getDeclaredField("mInsertionControllerEnabled");
            Intrinsics.checkExpressionValueIsNotNull(declaredField2, "editorClass.getDeclaredF…ertionControllerEnabled\")");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, false);
            Field declaredField3 = cls.getDeclaredField("mSelectionControllerEnabled");
            Intrinsics.checkExpressionValueIsNotNull(declaredField3, "editorClass.getDeclaredF…ectionControllerEnabled\")");
            declaredField3.setAccessible(true);
            declaredField3.set(obj, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void singleClick$default(View singleClick, Map map, int i, Function1 block, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        Map param = map;
        if ((i2 & 2) != 0) {
            i = 0;
        }
        int i3 = i;
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ViewExtKt$singleClick$1(block, param, i3, param, i3));
    }

    public static final void singleClick(View singleClick, Map<String, ? extends Object> param, int i, Function1<? super View, Unit> block) {
        Intrinsics.checkParameterIsNotNull(singleClick, "$this$singleClick");
        Intrinsics.checkParameterIsNotNull(param, "param");
        Intrinsics.checkParameterIsNotNull(block, "block");
        singleClick.setOnClickListener(new ViewExtKt$singleClick$1(block, param, i, param, i));
    }

    public static final void gone(Iterable<? extends View> gone) {
        Intrinsics.checkParameterIsNotNull(gone, "$this$gone");
        Iterator<? extends View> it = gone.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(8);
        }
    }

    public static final void hide(Iterable<? extends View> hide) {
        Intrinsics.checkParameterIsNotNull(hide, "$this$hide");
        Iterator<? extends View> it = hide.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(4);
        }
    }

    public static final void show(Iterable<? extends View> show) {
        Intrinsics.checkParameterIsNotNull(show, "$this$show");
        Iterator<? extends View> it = show.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(0);
        }
    }
}
