package org.hash.mock.debug.manager;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.google.android.material.badge.BadgeDrawable;
import com.pudutech.disinfect.baselib.C4429R;
import java.util.ArrayList;
import org.hash.mock.debug.BubbleContext;
import org.hash.mock.debug.DebugViewManager;
import org.hash.mock.debug.MemoryInfo;
import org.hash.mock.debug.Utils;
import org.hash.mock.debug.view.DebugToolsCoordinator;
import org.hash.mock.debug.view.DebugToolsTrashLayout;
import org.hash.mock.debug.view.MagnetView;
import org.hash.mock.debug.view.sheet.entity.SimpleEntity;
import org.hash.mock.debug.view.wave.WaterView;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class DebugTools implements IDebugToolsManager {
    private static volatile DebugTools mInstance;
    private FrameLayout mContainer;
    private DebugViewManager mDebugConfig;
    private MagnetView mEnFloatingView;
    private DebugToolsCoordinator mLayoutCoordinator;
    private TextView mMemoryInfo;
    private DebugToolsTrashLayout mTrashView;
    private WaterView mWaterView;
    private SparseArray<ArrayList<SimpleEntity>> menuEntities;

    private DebugTools() {
    }

    public static IDebugToolsManager get() {
        if (mInstance == null) {
            synchronized (DebugTools.class) {
                if (mInstance == null) {
                    mInstance = new DebugTools();
                }
            }
        }
        return mInstance;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools fillMenuData(SparseArray<ArrayList<SimpleEntity>> sparseArray) {
        this.menuEntities = sparseArray;
        return this;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.hash.mock.debug.manager.DebugTools.1
            @Override // java.lang.Runnable
            public void run() {
                if (DebugTools.this.mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(DebugTools.this.mEnFloatingView) && DebugTools.this.mContainer != null) {
                    DebugTools.this.mContainer.removeView(DebugTools.this.mEnFloatingView);
                }
                DebugTools.this.mEnFloatingView = null;
                DebugTools.this.mMemoryInfo = null;
                DebugTools.this.mWaterView = null;
                DebugTools.this.mDebugConfig = null;
            }
        });
        return this;
    }

    private void ensureView(Context context) {
        synchronized (this) {
            if (this.mEnFloatingView != null) {
                return;
            }
            initTrash();
            initFloatView(context);
            addViewToWindow(this.mEnFloatingView);
            MemoryInfo.getInstance().start();
        }
    }

    private void initFloatView(Context context) {
        this.mEnFloatingView = new MagnetView(context.getApplicationContext());
        this.mWaterView = (WaterView) this.mEnFloatingView.findViewById(C4429R.id.waterview);
        this.mWaterView.start();
        this.mMemoryInfo = (TextView) this.mEnFloatingView.findViewById(C4429R.id.memory_info);
        this.mEnFloatingView.setOnRemoveListener(new MagnetView.OnRemoveListener() { // from class: org.hash.mock.debug.manager.DebugTools.2
            @Override // org.hash.mock.debug.view.MagnetView.OnRemoveListener
            public void onRemoved(MagnetView magnetView) {
                DebugTools.this.getConfig().dismiss();
            }
        });
        this.mEnFloatingView.setOnClickListener(new MagnetView.OnClickListener() { // from class: org.hash.mock.debug.manager.DebugTools.3
            @Override // org.hash.mock.debug.view.MagnetView.OnClickListener
            public void onClick(MagnetView magnetView) {
                if (DebugTools.this.mContainer == null) {
                    return;
                }
                DebugTools.this.getConfig().setupRecyclerView(DebugTools.this.mContainer, DebugTools.this.menuEntities).toggle();
            }
        });
        this.mEnFloatingView.setShouldStickToWall(true);
        this.mEnFloatingView.setLayoutCoordinator(this.mLayoutCoordinator);
        this.mEnFloatingView.setLayoutParams(getParams());
    }

    public DebugViewManager getConfig() {
        if (this.mDebugConfig == null) {
            this.mDebugConfig = new DebugViewManager();
        }
        return this.mDebugConfig;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools add() {
        ensureView(BubbleContext.getAppContext());
        return this;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public IDebugToolsManager attach(Activity activity) {
        attach(getActivityRoot(activity));
        return this;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools attach(FrameLayout frameLayout) {
        MagnetView magnetView;
        if (frameLayout == null || (magnetView = this.mEnFloatingView) == null) {
            this.mContainer = frameLayout;
            return null;
        }
        if (magnetView.getParent() == frameLayout) {
            return this;
        }
        removeView(this.mTrashView);
        removeView(this.mEnFloatingView);
        this.mContainer = frameLayout;
        addViewToWindow(this.mEnFloatingView);
        addViewToWindow(this.mTrashView);
        return this;
    }

    private void removeView(View view) {
        if (this.mContainer != null) {
            ViewParent parent = view.getParent();
            FrameLayout frameLayout = this.mContainer;
            if (parent == frameLayout) {
                frameLayout.removeView(view);
            }
        }
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools detach(Activity activity) {
        detach(getActivityRoot(activity));
        return this;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools detach(FrameLayout frameLayout) {
        MagnetView magnetView = this.mEnFloatingView;
        if (magnetView != null && frameLayout != null && ViewCompat.isAttachedToWindow(magnetView)) {
            frameLayout.removeView(this.mEnFloatingView);
            frameLayout.removeView(this.mTrashView);
        }
        if (this.mContainer == frameLayout) {
            this.mContainer = null;
        }
        return this;
    }

    private void addViewToWindow(FrameLayout frameLayout) {
        FrameLayout frameLayout2 = this.mContainer;
        if (frameLayout2 == null) {
            return;
        }
        frameLayout2.addView(frameLayout);
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = BadgeDrawable.BOTTOM_END;
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, (int) (-Utils.dp2px(20.0f)), Utils.getScreenHeight() / 3);
        return layoutParams;
    }

    private FrameLayout getActivityRoot(Activity activity) {
        if (activity == null) {
            return null;
        }
        try {
            return (FrameLayout) activity.getWindow().getDecorView().findViewById(R.id.content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initTrash() {
        this.mTrashView = new DebugToolsTrashLayout(BubbleContext.getAppContext());
        this.mTrashView.setVisibility(8);
        LayoutInflater.from(BubbleContext.getAppContext()).inflate(C4429R.layout.debug_tools_trash_layout, (ViewGroup) this.mTrashView, true);
        addViewToWindow(this.mTrashView);
        initializeLayoutCoordinator();
    }

    private void initializeLayoutCoordinator() {
        this.mLayoutCoordinator = new DebugToolsCoordinator.Builder().setTrashView(this.mTrashView).build();
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public DebugTools updateInfo(final CharSequence charSequence, float f) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.hash.mock.debug.manager.DebugTools.4
            @Override // java.lang.Runnable
            public void run() {
                if (DebugTools.this.mMemoryInfo != null) {
                    DebugTools.this.mMemoryInfo.setText(charSequence);
                }
            }
        });
        return this;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public boolean isMemoryInfoShow() {
        return this.mMemoryInfo != null;
    }

    @Override // org.hash.mock.debug.manager.IDebugToolsManager
    public boolean dismissMenu() {
        return getConfig().dismiss();
    }
}
