package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.fragment.app.Fragment;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingContract;
import com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import java.util.HashMap;
import java.util.function.BiConsumer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: FunctionFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J&\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016J\u001a\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u001c\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/FunctionFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingContract$ViewInterface;", "()V", "TAG", "", "functionSettingPresenter", "Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "getFunctionSettingPresenter", "()Lcom/pudutech/bumblebee/presenter/setting/FunctionSettingPresenter;", "functionSettingPresenter$delegate", "Lkotlin/Lazy;", "bind", "", "initData", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onViewCreated", "view", "unBind", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class FunctionFragment extends Fragment implements FunctionSettingContract.ViewInterface {
    private HashMap _$_findViewCache;
    private final String TAG = "FunctionFragment";

    /* renamed from: functionSettingPresenter$delegate, reason: from kotlin metadata */
    private final Lazy functionSettingPresenter = LazyKt.lazy(new Function0<FunctionSettingPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$functionSettingPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FunctionSettingPresenter invoke() {
            FunctionSettingPresenter functionSettingPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                functionSettingPresenter = new FunctionSettingPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(FunctionSettingPresenter.class).toString(), functionSettingPresenter);
            } else {
                if (!(basePresenterInterface instanceof FunctionSettingPresenter)) {
                    basePresenterInterface = null;
                }
                functionSettingPresenter = (FunctionSettingPresenter) basePresenterInterface;
            }
            if (functionSettingPresenter != null) {
                return functionSettingPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.setting.FunctionSettingPresenter");
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final FunctionSettingPresenter getFunctionSettingPresenter() {
        return (FunctionSettingPresenter) this.functionSettingPresenter.getValue();
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
        bind();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        return inflater.inflate(C4188R.layout.fragment_function, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private final void initData() {
        getFunctionSettingPresenter().loadFunctionState().forEach(new BiConsumer<FunctionSettingContract.FunctionType, Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initData$1
            @Override // java.util.function.BiConsumer
            public final void accept(FunctionSettingContract.FunctionType t, Boolean u) {
                Intrinsics.checkParameterIsNotNull(t, "t");
                Intrinsics.checkParameterIsNotNull(u, "u");
                switch (t) {
                    case CRUISE_FUNCTION:
                        Switch cruise_switch = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.cruise_switch);
                        Intrinsics.checkExpressionValueIsNotNull(cruise_switch, "cruise_switch");
                        cruise_switch.setChecked(u.booleanValue());
                        return;
                    case DIRECT_DELIVER_FUNCTION:
                        Switch direct_switch = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.direct_switch);
                        Intrinsics.checkExpressionValueIsNotNull(direct_switch, "direct_switch");
                        direct_switch.setChecked(u.booleanValue());
                        return;
                    case GREETER_FUNCTION:
                        Switch greeter_switch = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.greeter_switch);
                        Intrinsics.checkExpressionValueIsNotNull(greeter_switch, "greeter_switch");
                        greeter_switch.setChecked(u.booleanValue());
                        return;
                    case SPECIAL_FUNCTION:
                        Switch special_switch = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.special_switch);
                        Intrinsics.checkExpressionValueIsNotNull(special_switch, "special_switch");
                        special_switch.setChecked(u.booleanValue());
                        return;
                    case RETURN_FUNCTION:
                        Switch return_back_switch = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.return_back_switch);
                        Intrinsics.checkExpressionValueIsNotNull(return_back_switch, "return_back_switch");
                        return_back_switch.setChecked(u.booleanValue());
                        return;
                    case BIRTHDAY_FUNCTION:
                        Switch birthday_switch = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.birthday_switch);
                        Intrinsics.checkExpressionValueIsNotNull(birthday_switch, "birthday_switch");
                        birthday_switch.setChecked(u.booleanValue());
                        return;
                    case RECYCLE_FUNCTION:
                        Switch switch_8 = (Switch) FunctionFragment.this._$_findCachedViewById(C4188R.id.switch_8);
                        Intrinsics.checkExpressionValueIsNotNull(switch_8, "switch_8");
                        switch_8.setChecked(u.booleanValue());
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private final void initView() {
        ((Switch) _$_findCachedViewById(C4188R.id.cruise_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_2 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.CRUISE_FUNCTION, z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.direct_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_3 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.DIRECT_DELIVER_FUNCTION, z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.greeter_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_4 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.GREETER_FUNCTION, z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.special_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$4
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_5 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.SPECIAL_FUNCTION, z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.return_back_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_6 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.RETURN_FUNCTION, z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.birthday_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_7 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.BIRTHDAY_FUNCTION, z);
            }
        }, 7, null));
        ((Switch) _$_findCachedViewById(C4188R.id.switch_8)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.FunctionFragment$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton v, boolean z) {
                String str;
                FunctionSettingPresenter functionSettingPresenter;
                Intrinsics.checkParameterIsNotNull(v, "v");
                str = FunctionFragment.this.TAG;
                Pdlog.m3273d(str, "initView switch_8 c = " + z);
                functionSettingPresenter = FunctionFragment.this.getFunctionSettingPresenter();
                functionSettingPresenter.updateFunctionState(FunctionSettingContract.FunctionType.RECYCLE_FUNCTION, z);
            }
        }, 7, null));
    }

    private final void bind() {
        getFunctionSettingPresenter().attachView(this);
    }

    private final void unBind() {
        getFunctionSettingPresenter().detachView(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        unBind();
    }
}
