package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LotteryFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 \u001a2\u00020\u0001:\u0002\u001a\u001bB\u0005¢\u0006\u0002\u0010\u0002J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0013H\u0016J\u001a\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/LotteryFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "viewSet", "Ljava/util/HashSet;", "Landroid/widget/EditText;", "Lkotlin/collections/HashSet;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "", "onDestroyView", "onStop", "onViewCreated", "view", "saveProbabilities", "updateProbabilities", "Companion", "TextMonitor", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class LotteryFragment extends Fragment {
    public static float probablity_0;
    public static float probablity_1;
    public static float probablity_2;
    public static float probablity_3;
    private HashMap _$_findViewCache;
    private final String TAG = getClass().getSimpleName();
    private final HashSet<EditText> viewSet = new HashSet<>();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_interact, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Switch interaction_switch = (Switch) _$_findCachedViewById(C4188R.id.interaction_switch);
        Intrinsics.checkExpressionValueIsNotNull(interaction_switch, "interaction_switch");
        interaction_switch.setChecked(SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false));
        ((Switch) _$_findCachedViewById(C4188R.id.interaction_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LotteryFragment$onViewCreated$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                Pdlog.m3273d(LotteryFragment.this.TAG, "updateInteractionSwitch switch on is " + z);
                SpUtils.set(RobotContext.INSTANCE.getContext(), "key_interaction_switch", z);
            }
        }, 7, null));
        if (!this.viewSet.isEmpty()) {
            this.viewSet.clear();
        }
        this.viewSet.add((EditText) _$_findCachedViewById(C4188R.id.prize_level_0_input));
        this.viewSet.add((EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input));
        this.viewSet.add((EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input));
        this.viewSet.add((EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input));
        EditText editText = (EditText) _$_findCachedViewById(C4188R.id.prize_level_0_input);
        EditText prize_level_0_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_0_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_0_input, "prize_level_0_input");
        editText.addTextChangedListener(new TextMonitor(this, prize_level_0_input));
        EditText editText2 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input);
        EditText prize_level_1_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_1_input, "prize_level_1_input");
        editText2.addTextChangedListener(new TextMonitor(this, prize_level_1_input));
        EditText editText3 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
        EditText prize_level_2_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_2_input, "prize_level_2_input");
        editText3.addTextChangedListener(new TextMonitor(this, prize_level_2_input));
        EditText editText4 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
        EditText prize_level_3_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input, "prize_level_3_input");
        editText4.addTextChangedListener(new TextMonitor(this, prize_level_3_input));
        new Thread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LotteryFragment$onViewCreated$2
            @Override // java.lang.Runnable
            public final void run() {
                LotteryFragment.probablity_0 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_0", 0.0f);
                LotteryFragment.probablity_1 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_1", 0.0f);
                LotteryFragment.probablity_2 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_2", 0.0f);
                LotteryFragment.probablity_3 = SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_3", 0.0f);
                FragmentActivity activity = LotteryFragment.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LotteryFragment$onViewCreated$2.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            EditText editText5 = (EditText) LotteryFragment.this._$_findCachedViewById(C4188R.id.prize_level_0_input);
                            if (editText5 != null) {
                                editText5.setText(String.valueOf(LotteryFragment.probablity_0), (TextView.BufferType) null);
                            }
                            EditText editText6 = (EditText) LotteryFragment.this._$_findCachedViewById(C4188R.id.prize_level_1_input);
                            if (editText6 != null) {
                                editText6.setText(String.valueOf(LotteryFragment.probablity_1), (TextView.BufferType) null);
                            }
                            EditText editText7 = (EditText) LotteryFragment.this._$_findCachedViewById(C4188R.id.prize_level_2_input);
                            if (editText7 != null) {
                                editText7.setText(String.valueOf(LotteryFragment.probablity_2), (TextView.BufferType) null);
                            }
                            EditText editText8 = (EditText) LotteryFragment.this._$_findCachedViewById(C4188R.id.prize_level_3_input);
                            if (editText8 != null) {
                                editText8.setText(String.valueOf(LotteryFragment.probablity_3), (TextView.BufferType) null);
                            }
                            Pdlog.m3273d(LotteryFragment.this.TAG, "Last lottery probabilities: " + LotteryFragment.probablity_0 + "  " + LotteryFragment.probablity_1 + "  " + LotteryFragment.probablity_2 + "  " + LotteryFragment.probablity_3);
                        }
                    });
                }
            }
        }).start();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void updateProbabilities() {
        float f;
        EditText prize_level_1_input;
        float f2;
        EditText prize_level_2_input;
        float f3;
        EditText prize_level_3_input;
        EditText prize_level_0_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_0_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_0_input, "prize_level_0_input");
        float f4 = 0.0f;
        if (prize_level_0_input.getText() != null) {
            EditText prize_level_0_input2 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_0_input);
            Intrinsics.checkExpressionValueIsNotNull(prize_level_0_input2, "prize_level_0_input");
            if (StringsKt.toFloatOrNull(prize_level_0_input2.getText().toString()) != null) {
                EditText prize_level_0_input3 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_0_input);
                Intrinsics.checkExpressionValueIsNotNull(prize_level_0_input3, "prize_level_0_input");
                f = Float.parseFloat(prize_level_0_input3.getText().toString());
                probablity_0 = f;
                prize_level_1_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input);
                Intrinsics.checkExpressionValueIsNotNull(prize_level_1_input, "prize_level_1_input");
                if (prize_level_1_input.getText() != null) {
                    EditText prize_level_1_input2 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input);
                    Intrinsics.checkExpressionValueIsNotNull(prize_level_1_input2, "prize_level_1_input");
                    if (StringsKt.toFloatOrNull(prize_level_1_input2.getText().toString()) != null) {
                        EditText prize_level_1_input3 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input);
                        Intrinsics.checkExpressionValueIsNotNull(prize_level_1_input3, "prize_level_1_input");
                        f2 = Float.parseFloat(prize_level_1_input3.getText().toString());
                        probablity_1 = f2;
                        prize_level_2_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
                        Intrinsics.checkExpressionValueIsNotNull(prize_level_2_input, "prize_level_2_input");
                        if (prize_level_2_input.getText() != null) {
                            EditText prize_level_2_input2 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
                            Intrinsics.checkExpressionValueIsNotNull(prize_level_2_input2, "prize_level_2_input");
                            if (StringsKt.toFloatOrNull(prize_level_2_input2.getText().toString()) != null) {
                                EditText prize_level_2_input3 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
                                Intrinsics.checkExpressionValueIsNotNull(prize_level_2_input3, "prize_level_2_input");
                                f3 = Float.parseFloat(prize_level_2_input3.getText().toString());
                                probablity_2 = f3;
                                prize_level_3_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
                                Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input, "prize_level_3_input");
                                if (prize_level_3_input.getText() != null) {
                                    EditText prize_level_3_input2 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
                                    Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input2, "prize_level_3_input");
                                    if (StringsKt.toFloatOrNull(prize_level_3_input2.getText().toString()) != null) {
                                        EditText prize_level_3_input3 = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
                                        Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input3, "prize_level_3_input");
                                        f4 = Float.parseFloat(prize_level_3_input3.getText().toString());
                                    }
                                }
                                probablity_3 = f4;
                            }
                        }
                        f3 = 0.0f;
                        probablity_2 = f3;
                        prize_level_3_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
                        Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input, "prize_level_3_input");
                        if (prize_level_3_input.getText() != null) {
                        }
                        probablity_3 = f4;
                    }
                }
                f2 = 0.0f;
                probablity_1 = f2;
                prize_level_2_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
                Intrinsics.checkExpressionValueIsNotNull(prize_level_2_input, "prize_level_2_input");
                if (prize_level_2_input.getText() != null) {
                }
                f3 = 0.0f;
                probablity_2 = f3;
                prize_level_3_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
                Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input, "prize_level_3_input");
                if (prize_level_3_input.getText() != null) {
                }
                probablity_3 = f4;
            }
        }
        f = 0.0f;
        probablity_0 = f;
        prize_level_1_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_1_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_1_input, "prize_level_1_input");
        if (prize_level_1_input.getText() != null) {
        }
        f2 = 0.0f;
        probablity_1 = f2;
        prize_level_2_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_2_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_2_input, "prize_level_2_input");
        if (prize_level_2_input.getText() != null) {
        }
        f3 = 0.0f;
        probablity_2 = f3;
        prize_level_3_input = (EditText) _$_findCachedViewById(C4188R.id.prize_level_3_input);
        Intrinsics.checkExpressionValueIsNotNull(prize_level_3_input, "prize_level_3_input");
        if (prize_level_3_input.getText() != null) {
        }
        probablity_3 = f4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveProbabilities() {
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_0", probablity_0);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_1", probablity_1);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_2", probablity_2);
        SpUtils.set(RobotContext.INSTANCE.getContext(), "key_interaction_prize_probability_level_3", probablity_3);
        Pdlog.m3273d(this.TAG, "Save Lottery probabilities: " + probablity_0 + "  " + probablity_1 + "  " + probablity_2 + "  " + probablity_3);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        updateProbabilities();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        new Thread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.LotteryFragment$onDestroyView$1
            @Override // java.lang.Runnable
            public final void run() {
                LotteryFragment.this.saveProbabilities();
            }
        }).start();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.viewSet.clear();
    }

    /* compiled from: LotteryFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J*\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0016J*\u0010\u000f\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/LotteryFragment$TextMonitor;", "Landroid/text/TextWatcher;", "view", "Landroid/widget/EditText;", "(Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/LotteryFragment;Landroid/widget/EditText;)V", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "onTextChanged", "before", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    private final class TextMonitor implements TextWatcher {
        final /* synthetic */ LotteryFragment this$0;
        private final EditText view;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        public TextMonitor(LotteryFragment lotteryFragment, EditText view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.this$0 = lotteryFragment;
            this.view = view;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            String str = this.this$0.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("afterTextChanged:");
            sb.append((Object) s);
            sb.append("length:");
            sb.append(s != null ? Integer.valueOf(s.length()) : null);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            if (s != null) {
                Editable editable = s;
                if (editable.length() == 0) {
                    return;
                }
                if (!(StringsKt.toFloatOrNull(s.toString()) != null && !StringsKt.startsWith$default((CharSequence) editable, (CharSequence) ".", false, 2, (Object) null) && !StringsKt.startsWith$default((CharSequence) editable, (CharSequence) "00", false, 2, (Object) null) && s.length() <= 10 && Float.parseFloat(s.toString()) <= ((float) 1) && Float.parseFloat(s.toString()) >= ((float) 0))) {
                    Toast.makeText(RobotContext.INSTANCE.getContext(), this.this$0.getString(C4188R.string.pdStr7_111), 0).show();
                    TextMonitor textMonitor = this;
                    this.view.removeTextChangedListener(textMonitor);
                    this.view.setText((CharSequence) null, (TextView.BufferType) null);
                    this.view.addTextChangedListener(textMonitor);
                    return;
                }
                float parseFloat = Float.parseFloat(s.toString());
                float f = 0.0f;
                if (parseFloat == 0.0f) {
                    return;
                }
                if (parseFloat == 1.0f) {
                    Iterator it = this.this$0.viewSet.iterator();
                    while (it.hasNext()) {
                        EditText editText = (EditText) it.next();
                        if (!Intrinsics.areEqual(editText, this.view)) {
                            editText.setText(String.valueOf(0.0f), (TextView.BufferType) null);
                        }
                    }
                    return;
                }
                Iterator it2 = this.this$0.viewSet.iterator();
                while (it2.hasNext()) {
                    EditText editText2 = (EditText) it2.next();
                    if (!Intrinsics.areEqual(editText2, this.view)) {
                        Intrinsics.checkExpressionValueIsNotNull(editText2, "editText");
                        if (editText2.getText() != null && StringsKt.toFloatOrNull(editText2.getText().toString()) != null) {
                            f += Float.parseFloat(editText2.getText().toString());
                        }
                    }
                }
                if (f + parseFloat > 1.0f) {
                    Toast.makeText(RobotContext.INSTANCE.getContext(), this.this$0.getString(C4188R.string.pdStr7_112), 0).show();
                    TextMonitor textMonitor2 = this;
                    this.view.removeTextChangedListener(textMonitor2);
                    this.view.setText((CharSequence) null, (TextView.BufferType) null);
                    this.view.addTextChangedListener(textMonitor2);
                }
            }
        }
    }
}
