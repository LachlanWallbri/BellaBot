package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: LatticeScreenFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u0004H\u0002J\b\u0010\u0012\u001a\u00020\rH\u0002J\u0012\u0010\u0013\u001a\u00020\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001c\u001a\u00020\rH\u0016J\u001a\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/LatticeScreenFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "latScreenLayout", "Landroidx/appcompat/widget/LinearLayoutCompat;", "getLatScreenLayout", "()Landroidx/appcompat/widget/LinearLayoutCompat;", "latScreenLayout$delegate", "Lkotlin/Lazy;", "changeContent", "", "tv", "Landroid/widget/TextView;", AIUIConstant.KEY_CONTENT, "placeHolder", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "onViewCreated", "view", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LatticeScreenFragment extends Fragment {
    private HashMap _$_findViewCache;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: latScreenLayout$delegate, reason: from kotlin metadata */
    private final Lazy latScreenLayout = LazyKt.lazy(new LatticeScreenFragment$latScreenLayout$2(this));

    /* JADX INFO: Access modifiers changed from: private */
    public final LinearLayoutCompat getLatScreenLayout() {
        return (LinearLayoutCompat) this.latScreenLayout.getValue();
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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_lattice_screen, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private final void initView() {
        Pdlog.m3273d(this.TAG, "initView");
        String str = SpUtils.get(getContext(), "key_lattice_turn_back", "");
        TextView turn_back_text = (TextView) _$_findCachedViewById(C4188R.id.turn_back_text);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_text, "turn_back_text");
        changeContent$default(this, turn_back_text, str, null, 4, null);
        ((TextView) _$_findCachedViewById(C4188R.id.turn_back_text)).setOnClickListener(new LatticeScreenFragment$initView$1(this));
        String str2 = SpUtils.get(getContext(), "key_lattice_cruise", "");
        TextView cruise_text = (TextView) _$_findCachedViewById(C4188R.id.cruise_text);
        Intrinsics.checkExpressionValueIsNotNull(cruise_text, "cruise_text");
        changeContent$default(this, cruise_text, str2, null, 4, null);
        ((TextView) _$_findCachedViewById(C4188R.id.cruise_text)).setOnClickListener(new LatticeScreenFragment$initView$2(this));
        String str3 = SpUtils.get(getContext(), "key_lattice_welcome_area", "");
        TextView door_meet_text = (TextView) _$_findCachedViewById(C4188R.id.door_meet_text);
        Intrinsics.checkExpressionValueIsNotNull(door_meet_text, "door_meet_text");
        changeContent$default(this, door_meet_text, str3, null, 4, null);
        ((TextView) _$_findCachedViewById(C4188R.id.door_meet_text)).setOnClickListener(new LatticeScreenFragment$initView$3(this));
        String str4 = SpUtils.get(getContext(), "key_lattice_guide_table", "");
        TextView guide_table_text = (TextView) _$_findCachedViewById(C4188R.id.guide_table_text);
        Intrinsics.checkExpressionValueIsNotNull(guide_table_text, "guide_table_text");
        changeContent$default(this, guide_table_text, str4, null, 4, null);
        ((TextView) _$_findCachedViewById(C4188R.id.guide_table_text)).setOnClickListener(new LatticeScreenFragment$initView$4(this));
        TextView special_mode_text = (TextView) _$_findCachedViewById(C4188R.id.special_mode_text);
        Intrinsics.checkExpressionValueIsNotNull(special_mode_text, "special_mode_text");
        String specialModeText = Constans.INSTANCE.getSpecialModeText();
        String string = getString(C4188R.string.none_content_special);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.none_content_special)");
        changeContent(special_mode_text, specialModeText, string);
        ((TextView) _$_findCachedViewById(C4188R.id.special_mode_text)).setOnClickListener(new LatticeScreenFragment$initView$5(this));
        TextView recycle_text = (TextView) _$_findCachedViewById(C4188R.id.recycle_text);
        Intrinsics.checkExpressionValueIsNotNull(recycle_text, "recycle_text");
        changeContent$default(this, recycle_text, Constans.INSTANCE.getRecycleModeText(), null, 4, null);
        ((TextView) _$_findCachedViewById(C4188R.id.recycle_text)).setOnClickListener(new LatticeScreenFragment$initView$6(this));
        ((ImageView) _$_findCachedViewById(C4188R.id.lat_screen_anima_open)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LatticeScreenFragment$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                LinearLayoutCompat latScreenLayout;
                LinearLayoutCompat latScreenLayout2;
                LinearLayoutCompat latScreenLayout3;
                latScreenLayout = LatticeScreenFragment.this.getLatScreenLayout();
                if (latScreenLayout.getVisibility() == 0) {
                    latScreenLayout3 = LatticeScreenFragment.this.getLatScreenLayout();
                    latScreenLayout3.setVisibility(8);
                    ((ImageView) LatticeScreenFragment.this._$_findCachedViewById(C4188R.id.lat_screen_anima_open)).setImageResource(C4188R.drawable.icon_settings_advanced_open);
                } else {
                    latScreenLayout2 = LatticeScreenFragment.this.getLatScreenLayout();
                    latScreenLayout2.setVisibility(0);
                    ((ImageView) LatticeScreenFragment.this._$_findCachedViewById(C4188R.id.lat_screen_anima_open)).setImageResource(C4188R.drawable.icon_settings_advanced_packup);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void changeContent$default(LatticeScreenFragment latticeScreenFragment, TextView textView, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = latticeScreenFragment.getString(C4188R.string.none_content);
            Intrinsics.checkExpressionValueIsNotNull(str2, "getString(R.string.none_content)");
        }
        latticeScreenFragment.changeContent(textView, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeContent(TextView tv, String content, String placeHolder) {
        String str = content;
        if (str.length() > 0) {
            tv.setText(str);
            Sdk27PropertiesKt.setTextColor(tv, ContextCompat.getColor(requireContext(), C4188R.color.font_active_color));
        } else {
            tv.setText(placeHolder);
            Sdk27PropertiesKt.setTextColor(tv, ContextCompat.getColor(requireContext(), C4188R.color.font_disable_color));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }
}
