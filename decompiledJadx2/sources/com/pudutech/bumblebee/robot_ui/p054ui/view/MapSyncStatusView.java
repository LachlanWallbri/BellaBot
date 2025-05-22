package com.pudutech.bumblebee.robot_ui.p054ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.enums.MapSyncStatus;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: MapSyncStatusView.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001d\u001a\u00020\u001eH\u0002R#\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR*\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0018\u001a\u0004\u0018\u00010\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0014@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/view/MapSyncStatusView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "loadingAnimation", "Landroid/view/animation/Animation;", "kotlin.jvm.PlatformType", "getLoadingAnimation", "()Landroid/view/animation/Animation;", "loadingAnimation$delegate", "Lkotlin/Lazy;", "mMap", "Ljava/util/HashMap;", "Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;", "Lcom/pudutech/bumblebee/robot_ui/ui/view/MapSyncShaderData;", "Lkotlin/collections/HashMap;", ES6Iterator.VALUE_PROPERTY, "status", "getStatus", "()Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;", "setStatus", "(Lcom/pudutech/bumblebee/robot_ui/enums/MapSyncStatus;)V", "updateView", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MapSyncStatusView extends FrameLayout {
    private HashMap _$_findViewCache;

    /* renamed from: loadingAnimation$delegate, reason: from kotlin metadata */
    private final Lazy loadingAnimation;
    private final HashMap<MapSyncStatus, MapSyncShaderData> mMap;
    private MapSyncStatus status;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MapSyncStatus.values().length];

        static {
            $EnumSwitchMapping$0[MapSyncStatus.LOADING.ordinal()] = 1;
            $EnumSwitchMapping$0[MapSyncStatus.SYNCED.ordinal()] = 2;
        }
    }

    private final Animation getLoadingAnimation() {
        return (Animation) this.loadingAnimation.getValue();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final MapSyncStatus getStatus() {
        return this.status;
    }

    public final void setStatus(MapSyncStatus mapSyncStatus) {
        this.status = mapSyncStatus;
        updateView();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MapSyncStatusView(Context context) {
        this(context, null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MapSyncStatusView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MapSyncStatusView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.loadingAnimation = LazyKt.lazy(new Function0<Animation>() { // from class: com.pudutech.bumblebee.robot_ui.ui.view.MapSyncStatusView$loadingAnimation$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                Animation loadAnimation = AnimationUtils.loadAnimation(MapSyncStatusView.this.getContext(), C4188R.anim.map_loading_anim);
                loadAnimation.setRepeatCount(-1);
                return loadAnimation;
            }
        });
        this.mMap = MapsKt.hashMapOf(TuplesKt.m3968to(MapSyncStatus.UPLOAD, new MapSyncShaderData(Integer.valueOf(C4188R.drawable.shape_1cc33d_radius_4), Integer.valueOf(C4188R.color.switch_text_on), getContext().getString(C4188R.string.upload), null, 8, null)), TuplesKt.m3968to(MapSyncStatus.SYNCED, new MapSyncShaderData(null, Integer.valueOf(C4188R.color.click_color_back_press), getContext().getString(C4188R.string.map_synced), null, 8, null)), TuplesKt.m3968to(MapSyncStatus.LOADING, new MapSyncShaderData(null, null, null, Integer.valueOf(C4188R.drawable.ic_loading))), TuplesKt.m3968to(MapSyncStatus.UPDATE, new MapSyncShaderData(Integer.valueOf(C4188R.drawable.shape_0072ff_radius_4), Integer.valueOf(C4188R.color.theme_main_color), getContext().getString(C4188R.string.pdStr16_88), null, 8, null)));
        LayoutInflater.from(context).inflate(C4188R.layout.layout_map_sync_status, (ViewGroup) this, true);
    }

    private final void updateView() {
        MapSyncStatus mapSyncStatus = this.status;
        if (mapSyncStatus != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[mapSyncStatus.ordinal()];
            if (i == 1) {
                TextView tv_action = (TextView) _$_findCachedViewById(C4188R.id.tv_action);
                Intrinsics.checkExpressionValueIsNotNull(tv_action, "tv_action");
                tv_action.setVisibility(8);
                ImageView ic_progress = (ImageView) _$_findCachedViewById(C4188R.id.ic_progress);
                Intrinsics.checkExpressionValueIsNotNull(ic_progress, "ic_progress");
                ic_progress.setVisibility(0);
                TextView tv_map_synced = (TextView) _$_findCachedViewById(C4188R.id.tv_map_synced);
                Intrinsics.checkExpressionValueIsNotNull(tv_map_synced, "tv_map_synced");
                tv_map_synced.setVisibility(8);
                ((ImageView) _$_findCachedViewById(C4188R.id.ic_progress)).startAnimation(getLoadingAnimation());
                return;
            }
            if (i == 2) {
                TextView tv_action2 = (TextView) _$_findCachedViewById(C4188R.id.tv_action);
                Intrinsics.checkExpressionValueIsNotNull(tv_action2, "tv_action");
                tv_action2.setVisibility(8);
                ImageView ic_progress2 = (ImageView) _$_findCachedViewById(C4188R.id.ic_progress);
                Intrinsics.checkExpressionValueIsNotNull(ic_progress2, "ic_progress");
                ic_progress2.setVisibility(8);
                ((ImageView) _$_findCachedViewById(C4188R.id.ic_progress)).clearAnimation();
                TextView tv_map_synced2 = (TextView) _$_findCachedViewById(C4188R.id.tv_map_synced);
                Intrinsics.checkExpressionValueIsNotNull(tv_map_synced2, "tv_map_synced");
                tv_map_synced2.setVisibility(0);
                TextView tv_map_synced3 = (TextView) _$_findCachedViewById(C4188R.id.tv_map_synced);
                Intrinsics.checkExpressionValueIsNotNull(tv_map_synced3, "tv_map_synced");
                HashMap<MapSyncStatus, MapSyncShaderData> hashMap = this.mMap;
                MapSyncStatus mapSyncStatus2 = this.status;
                if (mapSyncStatus2 == null) {
                    Intrinsics.throwNpe();
                }
                MapSyncShaderData mapSyncShaderData = hashMap.get(mapSyncStatus2);
                tv_map_synced3.setText(mapSyncShaderData != null ? mapSyncShaderData.getText() : null);
                return;
            }
        }
        MapSyncShaderData mapSyncShaderData2 = this.mMap.get(this.status);
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.tv_action);
        textView.setVisibility(0);
        if (mapSyncShaderData2 != null) {
            textView.setText(mapSyncShaderData2.getText());
            if (mapSyncShaderData2.getBg() != null) {
                textView.setBackground(ContextCompat.getDrawable(textView.getContext(), mapSyncShaderData2.getBg().intValue()));
            }
            if (mapSyncShaderData2.getBg() == null) {
                textView.setBackground((Drawable) null);
                Unit unit = Unit.INSTANCE;
            }
            if (mapSyncShaderData2.getTextColor() != null) {
                Sdk27PropertiesKt.setTextColor(textView, ContextCompat.getColor(textView.getContext(), mapSyncShaderData2.getTextColor().intValue()));
            }
        }
        TextView tv_map_synced4 = (TextView) _$_findCachedViewById(C4188R.id.tv_map_synced);
        Intrinsics.checkExpressionValueIsNotNull(tv_map_synced4, "tv_map_synced");
        tv_map_synced4.setVisibility(8);
        ImageView ic_progress3 = (ImageView) _$_findCachedViewById(C4188R.id.ic_progress);
        Intrinsics.checkExpressionValueIsNotNull(ic_progress3, "ic_progress");
        ic_progress3.setVisibility(8);
        ((ImageView) _$_findCachedViewById(C4188R.id.ic_progress)).clearAnimation();
    }
}
