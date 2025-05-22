package com.pudutech.bumblebee.robot_ui.util;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public final class KeyboardUtils {
    private static int sDecorViewDelta;

    /* loaded from: classes4.dex */
    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i);
    }

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void showSoftInput(Activity activity) {
        if (isSoftInputVisible(activity)) {
            return;
        }
        toggleSoftInput();
    }

    public static void showSoftInput(View view) {
        showSoftInput(view, 0);
    }

    public static void showSoftInput(View view, int i) {
        InputMethodManager inputMethodManager = (InputMethodManager) RobotContext.context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, i, new ResultReceiver(new Handler()) { // from class: com.pudutech.bumblebee.robot_ui.util.KeyboardUtils.1
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i2, Bundle bundle) {
                if (i2 == 1 || i2 == 3) {
                    KeyboardUtils.toggleSoftInput();
                }
            }
        });
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void hideSoftInput(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            View decorView = activity.getWindow().getDecorView();
            View findViewWithTag = decorView.findViewWithTag("keyboardTagView");
            if (findViewWithTag == null) {
                findViewWithTag = new EditText(activity);
                findViewWithTag.setTag("keyboardTagView");
                ((ViewGroup) decorView).addView(findViewWithTag, 0, 0);
            }
            currentFocus = findViewWithTag;
            currentFocus.requestFocus();
        }
        hideSoftInput(currentFocus);
    }

    public static void hideSoftInput(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) RobotContext.context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void toggleSoftInput() {
        InputMethodManager inputMethodManager = (InputMethodManager) RobotContext.context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 0);
    }

    public static boolean isSoftInputVisible(Activity activity) {
        return getDecorViewInvisibleHeight(activity.getWindow()) > 0;
    }

    public static boolean isSoftInputShow() {
        InputMethodManager inputMethodManager = (InputMethodManager) RobotContext.context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return false;
        }
        return inputMethodManager.isActive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getDecorViewInvisibleHeight(Window window) {
        View decorView = window.getDecorView();
        if (decorView == null) {
            return 0;
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getDecorViewInvisibleHeight: " + (decorView.getBottom() - rect.bottom));
        int abs = Math.abs(decorView.getBottom() - rect.bottom);
        if (abs <= getNavBarHeight() + getStatusBarHeight()) {
            sDecorViewDelta = abs;
            return 0;
        }
        return abs - sDecorViewDelta;
    }

    public static void registerSoftInputChangedListener(Activity activity, OnSoftInputChangedListener onSoftInputChangedListener) {
        registerSoftInputChangedListener(activity.getWindow(), onSoftInputChangedListener);
    }

    public static void registerSoftInputChangedListener(final Window window, final OnSoftInputChangedListener onSoftInputChangedListener) {
        if ((window.getAttributes().flags & 512) != 0) {
            window.clearFlags(512);
        }
        FrameLayout frameLayout = (FrameLayout) window.findViewById(R.id.content);
        final int[] iArr = {getDecorViewInvisibleHeight(window)};
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.pudutech.bumblebee.robot_ui.util.KeyboardUtils.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int decorViewInvisibleHeight = KeyboardUtils.getDecorViewInvisibleHeight(window);
                if (iArr[0] != decorViewInvisibleHeight) {
                    onSoftInputChangedListener.onSoftInputChanged(decorViewInvisibleHeight);
                    iArr[0] = decorViewInvisibleHeight;
                }
            }
        });
    }

    public static void fixAndroidBug5497(Activity activity) {
        fixAndroidBug5497(activity.getWindow());
    }

    public static void fixAndroidBug5497(final Window window) {
        FrameLayout frameLayout = (FrameLayout) window.findViewById(R.id.content);
        final View childAt = frameLayout.getChildAt(0);
        final int paddingBottom = childAt.getPaddingBottom();
        final int[] iArr = {getContentViewInvisibleHeight(window)};
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.pudutech.bumblebee.robot_ui.util.KeyboardUtils.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int contentViewInvisibleHeight = KeyboardUtils.getContentViewInvisibleHeight(window);
                if (iArr[0] != contentViewInvisibleHeight) {
                    View view = childAt;
                    view.setPadding(view.getPaddingStart(), childAt.getPaddingTop(), childAt.getPaddingEnd(), paddingBottom + KeyboardUtils.getDecorViewInvisibleHeight(window));
                    iArr[0] = contentViewInvisibleHeight;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getContentViewInvisibleHeight(Window window) {
        View findViewById = window.findViewById(R.id.content);
        if (findViewById == null) {
            return 0;
        }
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        Log.d("KeyboardUtils", "getContentViewInvisibleHeight: " + (findViewById.getBottom() - rect.bottom));
        int abs = Math.abs(findViewById.getBottom() - rect.bottom);
        if (abs <= getStatusBarHeight() + getNavBarHeight()) {
            return 0;
        }
        return abs;
    }

    public static void fixSoftInputLeaks(Activity activity) {
        fixSoftInputLeaks(activity.getWindow());
    }

    public static void fixSoftInputLeaks(Window window) {
        InputMethodManager inputMethodManager = (InputMethodManager) RobotContext.context.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        for (String str : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField(str);
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Object obj = declaredField.get(inputMethodManager);
                    if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                        declaredField.set(inputMethodManager, null);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void clickBlankArea2HideSoftInput() {
        Log.i("KeyboardUtils", "Please refer to the following code.");
    }

    private static int getStatusBarHeight() {
        Resources resources = RobotContext.context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    private static int getNavBarHeight() {
        Resources resources = RobotContext.context.getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private static Activity getActivityByView(View view) {
        return getActivityByContext(view.getContext());
    }

    private static Activity getActivityByContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}
