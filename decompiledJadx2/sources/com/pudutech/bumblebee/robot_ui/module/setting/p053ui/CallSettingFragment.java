package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.bean.ReturnPointBean;
import com.pudutech.bumblebee.robot_ui.manager.LoRaConnectState;
import com.pudutech.bumblebee.robot_ui.manager.LoRaInfo;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.GridSpacingItemDecoration;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.radiobtntv.RadioBtnTvAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.AppUpdateTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.KetBtnListDetailDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LoRaSignalDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.LoRaUpdateDialog;
import com.pudutech.bumblebee.robot_ui.repo.Gateway2;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM;
import com.pudutech.bumblebee.robot_ui.widget.dialog.RecycleViewDialog;
import com.pudutech.disinfect.baselib.ext.view.ViewExtKt;
import com.pudutech.disinfect.baselib.network.response.Gateway;
import com.pudutech.disinfect.baselib.network.response.KeyBtnWithDestination;
import com.pudutech.importmusic.QRCodeUtils;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.model.Version;
import com.pudutech.robot.opensdk.RemoteConnectState;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: CallSettingFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0010\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0002J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001dJ$\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u001a\u0010'\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u001d2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0018\u0010(\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0016\u0010)\u001a\u00020\u00142\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002J\u0018\u0010-\u001a\u00020\u00142\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010+H\u0002J\u0010\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020\u0014H\u0002J\u001c\u00103\u001a\u00020\u00142\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020605H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00067"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/CallSettingFragment;", "Landroidx/fragment/app/Fragment;", "()V", "TAG", "", "callVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "getCallVM", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "callVM$delegate", "Lkotlin/Lazy;", "loRaSignDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaSignalDialog;", "loRaUpdateDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaUpdateDialog;", "mKeyBtnListDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/KetBtnListDetailDialog;", "returnAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/radiobtntv/RadioBtnTvAdapter;", "initCruiseReturnRecyclerView", "", "initLocalServerView", "initObserver", "Landroidx/lifecycle/Observer;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/LoRaSignalDialog$SignType;", "initSwitchContent", "switchBtn", "Landroid/widget/TextView;", "switchContent", "Landroid/view/View;", "initView", "view", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "resetSwitchContent", "showGatewaySelectDialog", "list", "", "Lcom/pudutech/bumblebee/robot_ui/repo/Gateway2;", "showKeyBtnListDialog", "Lcom/pudutech/disinfect/baselib/network/response/KeyBtnWithDestination;", "showLoraUpdateConfirmDialog", "version", "Lcom/pudutech/lib_update/module/model/Version;", "showSignStrengthDialog", "updateLoRaUpdateDialog", "data", "Lkotlin/Pair;", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CallSettingFragment extends Fragment {
    private final String TAG = "CallSettingFragment";
    private HashMap _$_findViewCache;

    /* renamed from: callVM$delegate, reason: from kotlin metadata */
    private final Lazy callVM;
    private LoRaSignalDialog loRaSignDialog;
    private LoRaUpdateDialog loRaUpdateDialog;
    private KetBtnListDetailDialog mKeyBtnListDialog;
    private RadioBtnTvAdapter returnAdapter;

    /* JADX INFO: Access modifiers changed from: private */
    public final CallSettingVM getCallVM() {
        return (CallSettingVM) this.callVM.getValue();
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

    public CallSettingFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$viewModels$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Fragment invoke() {
                return Fragment.this;
            }
        };
        this.callVM = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(CallSettingVM.class), new Function0<ViewModelStore>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$viewModels$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) Function0.this.invoke()).getViewModelStore();
                Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "ownerProducer().viewModelStore");
                return viewModelStore;
            }
        }, (Function0) null);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        View inflate = inflater.inflate(C4188R.layout.fragment_call_setting, container, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…etting, container, false)");
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initObserver();
    }

    private final Observer<LoRaSignalDialog.SignType> initObserver() {
        final View view = getView();
        if (view == null) {
            return null;
        }
        LiveData<Integer> toastLD = getCallVM().getToastLD();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner, "viewLifecycleOwner");
        toastLD.observe(viewLifecycleOwner, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                int intValue = ((Number) t).intValue();
                str = this.TAG;
                Pdlog.m3275i(str, "toastLD > " + intValue);
                if (intValue <= 0) {
                    return;
                }
                ToastUtils.show(view.getContext(), this.getString(intValue), new Object[0]);
            }
        });
        LiveData<Boolean> openApiSwitch = getCallVM().getOpenApiSwitch();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner2, "viewLifecycleOwner");
        openApiSwitch.observe(viewLifecycleOwner2, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                CallSettingVM callVM;
                Switch r0;
                Boolean bool = (Boolean) t;
                str = this.TAG;
                Pdlog.m3275i(str, "openApiSwitch > " + bool);
                View view2 = this.getView();
                if (view2 != null && (r0 = (Switch) view2.findViewById(C4188R.id.sLinkSwitch)) != null) {
                    r0.setChecked(Intrinsics.areEqual((Object) bool, (Object) true));
                }
                if (Intrinsics.areEqual((Object) bool, (Object) true)) {
                    callVM = this.getCallVM();
                    if (!Intrinsics.areEqual((Object) callVM.getLocalServerSwitchLD().getValue(), (Object) true)) {
                        RelativeLayout local_server_layout = (RelativeLayout) view.findViewById(C4188R.id.local_server_layout);
                        Intrinsics.checkExpressionValueIsNotNull(local_server_layout, "local_server_layout");
                        ViewExtKt.gone(local_server_layout);
                    }
                }
                if (!Intrinsics.areEqual((Object) bool, (Object) true)) {
                    RelativeLayout local_server_layout2 = (RelativeLayout) view.findViewById(C4188R.id.local_server_layout);
                    Intrinsics.checkExpressionValueIsNotNull(local_server_layout2, "local_server_layout");
                    com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt.show(local_server_layout2);
                }
            }
        });
        LiveData<Boolean> localServerSwitchLD = getCallVM().getLocalServerSwitchLD();
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner3, "viewLifecycleOwner");
        localServerSwitchLD.observe(viewLifecycleOwner3, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Boolean it = (Boolean) t;
                str = this.TAG;
                Pdlog.m3273d(str, "localServerSwitchLD: " + it);
                Switch local_open_switch = (Switch) view.findViewById(C4188R.id.local_open_switch);
                Intrinsics.checkExpressionValueIsNotNull(local_open_switch, "local_open_switch");
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                local_open_switch.setChecked(it.booleanValue());
                if (it.booleanValue()) {
                    RelativeLayout local_server_layout = (RelativeLayout) view.findViewById(C4188R.id.local_server_layout);
                    Intrinsics.checkExpressionValueIsNotNull(local_server_layout, "local_server_layout");
                    com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt.show(local_server_layout);
                    LinearLayout llOpenApiContent = (LinearLayout) view.findViewById(C4188R.id.llOpenApiContent);
                    Intrinsics.checkExpressionValueIsNotNull(llOpenApiContent, "llOpenApiContent");
                    ViewExtKt.gone(llOpenApiContent);
                    EditText edittext_broker_host = (EditText) view.findViewById(C4188R.id.edittext_broker_host);
                    Intrinsics.checkExpressionValueIsNotNull(edittext_broker_host, "edittext_broker_host");
                    edittext_broker_host.setEnabled(false);
                    return;
                }
                LinearLayout llOpenApiContent2 = (LinearLayout) view.findViewById(C4188R.id.llOpenApiContent);
                Intrinsics.checkExpressionValueIsNotNull(llOpenApiContent2, "llOpenApiContent");
                com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt.show(llOpenApiContent2);
            }
        });
        LiveData<String> localHostLD = getCallVM().getLocalHostLD();
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner4, "viewLifecycleOwner");
        localHostLD.observe(viewLifecycleOwner4, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$observe$4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                ((EditText) view.findViewById(C4188R.id.edittext_broker_host)).setText((String) t);
            }
        });
        LiveData<RemoteConnectState> localServerConnectStateLD = getCallVM().getLocalServerConnectStateLD();
        LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner5, "viewLifecycleOwner");
        localServerConnectStateLD.observe(viewLifecycleOwner5, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$observe$5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                RemoteConnectState remoteConnectState = (RemoteConnectState) t;
                TextView textView = (TextView) view.findViewById(C4188R.id.textView_connect_state);
                if (textView != null) {
                    Context context = view.getContext();
                    textView.setText(Intrinsics.stringPlus(context != null ? context.getString(C4188R.string.advance_openapi_local_state_content) : null, remoteConnectState));
                }
            }
        });
        LiveData<BindCodeData> openApiCodeLD = getCallVM().getOpenApiCodeLD();
        LifecycleOwner viewLifecycleOwner6 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner6, "viewLifecycleOwner");
        openApiCodeLD.observe(viewLifecycleOwner6, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                BindCodeData bindCodeData = (BindCodeData) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "openApiCodeLD > " + bindCodeData);
                View view2 = CallSettingFragment.this.getView();
                if (view2 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(view2, "view ?: return@observe");
                    if (bindCodeData == null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view2.findViewById(C4188R.id.rlLinkCodeContent);
                        Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "view.rlLinkCodeContent");
                        ViewExtKt.gone(relativeLayout);
                        ProgressBar progressBar = (ProgressBar) view2.findViewById(C4188R.id.pbLinkLoad);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar, "view.pbLinkLoad");
                        ViewExtKt.visible(progressBar);
                        ImageView imageView = (ImageView) view2.findViewById(C4188R.id.ivLinkQRCode);
                        Intrinsics.checkExpressionValueIsNotNull(imageView, "view.ivLinkQRCode");
                        ViewExtKt.gone(imageView);
                        TextView textView = (TextView) view2.findViewById(C4188R.id.tvLinkQRCode);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "view.tvLinkQRCode");
                        textView.setText("");
                        return;
                    }
                    ProgressBar progressBar2 = (ProgressBar) view2.findViewById(C4188R.id.pbLinkLoad);
                    Intrinsics.checkExpressionValueIsNotNull(progressBar2, "view.pbLinkLoad");
                    ViewExtKt.gone(progressBar2);
                    ImageView imageView2 = (ImageView) view2.findViewById(C4188R.id.ivLinkQRCode);
                    Intrinsics.checkExpressionValueIsNotNull(imageView2, "view.ivLinkQRCode");
                    ViewExtKt.visible(imageView2);
                    TextView textView2 = (TextView) view2.findViewById(C4188R.id.tvLinkQRCode);
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "view.tvLinkQRCode");
                    textView2.setText(bindCodeData.getCode());
                    ((ImageView) view2.findViewById(C4188R.id.ivLinkQRCode)).setImageBitmap(QRCodeUtils.createQRCodeBitmap(bindCodeData.getCode(), 400, 400));
                    TextView textView3 = (TextView) view2.findViewById(C4188R.id.tvFetchLinkCode);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "view.tvFetchLinkCode");
                    textView3.setText(CallSettingFragment.this.getString(C4188R.string.pdStr7_142));
                }
            }
        });
        LiveData<BindCodeData> g4CodeLD = getCallVM().getG4CodeLD();
        LifecycleOwner viewLifecycleOwner7 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner7, "viewLifecycleOwner");
        g4CodeLD.observe(viewLifecycleOwner7, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                BindCodeData bindCodeData = (BindCodeData) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "g4CodeLD > " + bindCodeData);
                View view2 = CallSettingFragment.this.getView();
                if (view2 != null) {
                    Intrinsics.checkExpressionValueIsNotNull(view2, "view ?: return@observe");
                    if (bindCodeData == null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view2.findViewById(C4188R.id.rl4GCodeContent);
                        Intrinsics.checkExpressionValueIsNotNull(relativeLayout, "view.rl4GCodeContent");
                        ViewExtKt.gone(relativeLayout);
                        ProgressBar progressBar = (ProgressBar) view2.findViewById(C4188R.id.pb4GLoad);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar, "view.pb4GLoad");
                        ViewExtKt.visible(progressBar);
                        ImageView imageView = (ImageView) view2.findViewById(C4188R.id.iv4GQRCode);
                        Intrinsics.checkExpressionValueIsNotNull(imageView, "view.iv4GQRCode");
                        ViewExtKt.gone(imageView);
                        TextView textView = (TextView) view2.findViewById(C4188R.id.tv4GQRCode);
                        Intrinsics.checkExpressionValueIsNotNull(textView, "view.tv4GQRCode");
                        textView.setText("");
                        return;
                    }
                    ProgressBar progressBar2 = (ProgressBar) view2.findViewById(C4188R.id.pb4GLoad);
                    Intrinsics.checkExpressionValueIsNotNull(progressBar2, "view.pb4GLoad");
                    ViewExtKt.gone(progressBar2);
                    ImageView imageView2 = (ImageView) view2.findViewById(C4188R.id.iv4GQRCode);
                    Intrinsics.checkExpressionValueIsNotNull(imageView2, "view.iv4GQRCode");
                    ViewExtKt.visible(imageView2);
                    TextView textView2 = (TextView) view2.findViewById(C4188R.id.tv4GQRCode);
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "view.tv4GQRCode");
                    textView2.setText(bindCodeData.getCode());
                    ((ImageView) view2.findViewById(C4188R.id.iv4GQRCode)).setImageBitmap(QRCodeUtils.createQRCodeBitmap(bindCodeData.getCode(), 400, 400));
                    TextView textView3 = (TextView) view2.findViewById(C4188R.id.tvFetch4GCode);
                    Intrinsics.checkExpressionValueIsNotNull(textView3, "view.tvFetch4GCode");
                    textView3.setText(CallSettingFragment.this.getString(C4188R.string.pdStr7_142));
                }
            }
        });
        LiveData<Boolean> callReachedSwitchLD = getCallVM().getCallReachedSwitchLD();
        LifecycleOwner viewLifecycleOwner8 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner8, "viewLifecycleOwner");
        callReachedSwitchLD.observe(viewLifecycleOwner8, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$6
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Boolean bool = (Boolean) t;
                str = this.TAG;
                Pdlog.m3275i(str, "callReachedSwitchLD > " + bool);
                Switch sCallReached = (Switch) view.findViewById(C4188R.id.sCallReached);
                Intrinsics.checkExpressionValueIsNotNull(sCallReached, "sCallReached");
                sCallReached.setChecked(Intrinsics.areEqual((Object) bool, (Object) true));
                LinearLayout call_reached_time_root = (LinearLayout) view.findViewById(C4188R.id.call_reached_time_root);
                Intrinsics.checkExpressionValueIsNotNull(call_reached_time_root, "call_reached_time_root");
                ViewExtKt.visibleOrGone(call_reached_time_root, Intrinsics.areEqual((Object) bool, (Object) true));
                LinearLayout llCallReturn = (LinearLayout) view.findViewById(C4188R.id.llCallReturn);
                Intrinsics.checkExpressionValueIsNotNull(llCallReturn, "llCallReturn");
                ViewExtKt.visibleOrGone(llCallReturn, Intrinsics.areEqual((Object) bool, (Object) true));
            }
        });
        LiveData<Boolean> callAutoReachedSwitchLD = getCallVM().getCallAutoReachedSwitchLD();
        LifecycleOwner viewLifecycleOwner9 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner9, "viewLifecycleOwner");
        callAutoReachedSwitchLD.observe(viewLifecycleOwner9, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$7
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Boolean bool = (Boolean) t;
                str = this.TAG;
                Pdlog.m3275i(str, "callAutoReachedSwitchLD > " + bool);
                Switch sCallAutoReached = (Switch) view.findViewById(C4188R.id.sCallAutoReached);
                Intrinsics.checkExpressionValueIsNotNull(sCallAutoReached, "sCallAutoReached");
                sCallAutoReached.setChecked(Intrinsics.areEqual((Object) bool, (Object) true));
            }
        });
        LiveData<Long> callAutoReachedTimeLD = getCallVM().getCallAutoReachedTimeLD();
        LifecycleOwner viewLifecycleOwner10 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner10, "viewLifecycleOwner");
        callAutoReachedTimeLD.observe(viewLifecycleOwner10, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$8
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                String str2;
                Long l = (Long) t;
                str = this.TAG;
                Pdlog.m3275i(str, "callAutoReachedTimeLD > " + l);
                EditText editText = (EditText) view.findViewById(C4188R.id.etCallAutoReachedTime);
                if (l == null || (str2 = String.valueOf(l.longValue())) == null) {
                    str2 = "";
                }
                editText.setText(str2);
            }
        });
        LiveData<Long> callFreezeTimeLD = getCallVM().getCallFreezeTimeLD();
        LifecycleOwner viewLifecycleOwner11 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner11, "viewLifecycleOwner");
        callFreezeTimeLD.observe(viewLifecycleOwner11, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$9
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                String str2;
                Long l = (Long) t;
                str = this.TAG;
                Pdlog.m3275i(str, "callFreezeTimeLD > " + l);
                EditText editText = (EditText) view.findViewById(C4188R.id.etFreezeCall);
                if (l == null || (str2 = String.valueOf(l.longValue())) == null) {
                    str2 = "";
                }
                editText.setText(str2);
            }
        });
        LiveData<LoRaInfo> loraInfoLD = getCallVM().getLoraInfoLD();
        LifecycleOwner viewLifecycleOwner12 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner12, "viewLifecycleOwner");
        loraInfoLD.observe(viewLifecycleOwner12, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$observe$12
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                LoRaInfo loRaInfo = (LoRaInfo) t;
                if (loRaInfo == null) {
                    return;
                }
                TextView tv1KeyHardwareVersion = (TextView) view.findViewById(C4188R.id.tv1KeyHardwareVersion);
                Intrinsics.checkExpressionValueIsNotNull(tv1KeyHardwareVersion, "tv1KeyHardwareVersion");
                tv1KeyHardwareVersion.setText(loRaInfo.getHdVersion());
                TextView tv1KeyBootVersion = (TextView) view.findViewById(C4188R.id.tv1KeyBootVersion);
                Intrinsics.checkExpressionValueIsNotNull(tv1KeyBootVersion, "tv1KeyBootVersion");
                tv1KeyBootVersion.setText(loRaInfo.getBootLoaderVersion());
                TextView tv1KeyFirmVersion = (TextView) view.findViewById(C4188R.id.tv1KeyFirmVersion);
                Intrinsics.checkExpressionValueIsNotNull(tv1KeyFirmVersion, "tv1KeyFirmVersion");
                tv1KeyFirmVersion.setText(loRaInfo.getShowVersion());
            }
        });
        LiveData<LoRaConnectState> loraConnectStateLD = getCallVM().getLoraConnectStateLD();
        LifecycleOwner viewLifecycleOwner13 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner13, "viewLifecycleOwner");
        loraConnectStateLD.observe(viewLifecycleOwner13, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$10
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                if (Intrinsics.areEqual((LoRaConnectState) t, LoRaConnectState.Login.INSTANCE)) {
                    TextView tv1KeyConnectState = (TextView) view.findViewById(C4188R.id.tv1KeyConnectState);
                    Intrinsics.checkExpressionValueIsNotNull(tv1KeyConnectState, "tv1KeyConnectState");
                    Sdk27PropertiesKt.setTextColor(tv1KeyConnectState, Color.parseColor("#FF32C271"));
                    TextView tv1KeyConnectState2 = (TextView) view.findViewById(C4188R.id.tv1KeyConnectState);
                    Intrinsics.checkExpressionValueIsNotNull(tv1KeyConnectState2, "tv1KeyConnectState");
                    tv1KeyConnectState2.setText(this.getString(C4188R.string.pdStr7_17));
                    return;
                }
                TextView tv1KeyConnectState3 = (TextView) view.findViewById(C4188R.id.tv1KeyConnectState);
                Intrinsics.checkExpressionValueIsNotNull(tv1KeyConnectState3, "tv1KeyConnectState");
                Sdk27PropertiesKt.setTextColor(tv1KeyConnectState3, Color.parseColor("#66000000"));
                TextView tv1KeyConnectState4 = (TextView) view.findViewById(C4188R.id.tv1KeyConnectState);
                Intrinsics.checkExpressionValueIsNotNull(tv1KeyConnectState4, "tv1KeyConnectState");
                tv1KeyConnectState4.setText(this.getString(C4188R.string.pdStr7_18));
            }
        });
        LiveData<Boolean> loraExitLD = getCallVM().getLoraExitLD();
        LifecycleOwner viewLifecycleOwner14 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner14, "viewLifecycleOwner");
        loraExitLD.observe(viewLifecycleOwner14, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$observe$14
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                TextView tvSwitch1Key = (TextView) view.findViewById(C4188R.id.tvSwitch1Key);
                Intrinsics.checkExpressionValueIsNotNull(tvSwitch1Key, "tvSwitch1Key");
                ViewExtKt.visibleOrGone(tvSwitch1Key, Intrinsics.areEqual((Object) ((Boolean) t), (Object) true));
                if (!Intrinsics.areEqual((Object) r5, (Object) true)) {
                    ((TextView) view.findViewById(C4188R.id.tvBtnCallTitle)).setTextColor(ContextCompat.getColor(view.getContext(), C4188R.color.c_DF000000));
                    LinearLayout ll1KeyLayout = (LinearLayout) view.findViewById(C4188R.id.ll1KeyLayout);
                    Intrinsics.checkExpressionValueIsNotNull(ll1KeyLayout, "ll1KeyLayout");
                    ll1KeyLayout.setBackground(ContextCompat.getDrawable(view.getContext(), C4188R.drawable.shape_59ffffff_radius_8));
                    return;
                }
                ((TextView) view.findViewById(C4188R.id.tvBtnCallTitle)).setTextColor(ContextCompat.getColor(view.getContext(), C4188R.color.c_E60));
                LinearLayout ll1KeyLayout2 = (LinearLayout) view.findViewById(C4188R.id.ll1KeyLayout);
                Intrinsics.checkExpressionValueIsNotNull(ll1KeyLayout2, "ll1KeyLayout");
                ll1KeyLayout2.setBackground(ContextCompat.getDrawable(view.getContext(), C4188R.drawable.rectangle_setting_item_unselect_corners));
            }
        });
        LiveData<List<Gateway2>> loraGatewayListLD = getCallVM().getLoraGatewayListLD();
        LifecycleOwner viewLifecycleOwner15 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner15, "viewLifecycleOwner");
        loraGatewayListLD.observe(viewLifecycleOwner15, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$11
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                List list = (List) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "loraGatewayListLD > " + list);
                if (list == null) {
                    return;
                }
                if (list.isEmpty()) {
                    Context requireContext = CallSettingFragment.this.requireContext();
                    Context requireContext2 = CallSettingFragment.this.requireContext();
                    Intrinsics.checkExpressionValueIsNotNull(requireContext2, "requireContext()");
                    ToastUtils.show(requireContext, requireContext2.getResources().getString(C4188R.string.call_lora_refresh_2), new Object[0]);
                    return;
                }
                CallSettingFragment.this.showGatewaySelectDialog(list);
            }
        });
        LiveData<Gateway> crtGatewayLD = getCallVM().getCrtGatewayLD();
        LifecycleOwner viewLifecycleOwner16 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner16, "viewLifecycleOwner");
        crtGatewayLD.observe(viewLifecycleOwner16, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$$special$$inlined$observe$16
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Gateway gateway = (Gateway) t;
                TextView tv1KeyGatewayName = (TextView) view.findViewById(C4188R.id.tv1KeyGatewayName);
                Intrinsics.checkExpressionValueIsNotNull(tv1KeyGatewayName, "tv1KeyGatewayName");
                if (gateway == null || (str = gateway.getName()) == null) {
                    str = "";
                }
                tv1KeyGatewayName.setText(str);
            }
        });
        LiveData<Version> loraServerVersionLD = getCallVM().getLoraServerVersionLD();
        LifecycleOwner viewLifecycleOwner17 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner17, "viewLifecycleOwner");
        loraServerVersionLD.observe(viewLifecycleOwner17, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$12
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Version version = (Version) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "loraServerVersionLD > " + version);
                if (version == null) {
                    return;
                }
                CallSettingFragment.this.showLoraUpdateConfirmDialog(version);
            }
        });
        MutableLiveData<Pair<Integer, Integer>> loraUpdateStateLD = getCallVM().getLoraUpdateStateLD();
        LifecycleOwner viewLifecycleOwner18 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner18, "viewLifecycleOwner");
        loraUpdateStateLD.observe(viewLifecycleOwner18, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$13
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                LoRaUpdateDialog loRaUpdateDialog;
                Pair pair = (Pair) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "loraUpdateStateLD > " + pair);
                if (pair == null || ((Number) pair.getFirst()).intValue() == 5 || ((Number) pair.getFirst()).intValue() == 6) {
                    loRaUpdateDialog = CallSettingFragment.this.loRaUpdateDialog;
                    if (loRaUpdateDialog != null) {
                        loRaUpdateDialog.dismiss();
                        return;
                    }
                    return;
                }
                CallSettingFragment.this.updateLoRaUpdateDialog(pair);
            }
        });
        LiveData<List<KeyBtnWithDestination>> crtKeyBtnListLD = getCallVM().getCrtKeyBtnListLD();
        LifecycleOwner viewLifecycleOwner19 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner19, "viewLifecycleOwner");
        crtKeyBtnListLD.observe(viewLifecycleOwner19, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$14
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                CallSettingFragment.this.showKeyBtnListDialog((List) t);
            }
        });
        LiveData<List<ReturnPointBean>> callReturnPlaceLD = getCallVM().getCallReturnPlaceLD();
        LifecycleOwner viewLifecycleOwner20 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner20, "viewLifecycleOwner");
        callReturnPlaceLD.observe(viewLifecycleOwner20, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$15
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                RadioBtnTvAdapter radioBtnTvAdapter;
                List<T> list = (List) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "callReturnPlaceLD > " + list);
                radioBtnTvAdapter = CallSettingFragment.this.returnAdapter;
                if (radioBtnTvAdapter != null) {
                    radioBtnTvAdapter.setNewData(list);
                }
            }
        });
        LiveData<Boolean> greeterPointCanCallSwitchLD = getCallVM().getGreeterPointCanCallSwitchLD();
        LifecycleOwner viewLifecycleOwner21 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner21, "viewLifecycleOwner");
        greeterPointCanCallSwitchLD.observe(viewLifecycleOwner21, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$16
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Boolean bool = (Boolean) t;
                str = this.TAG;
                Pdlog.m3275i(str, "greeterPointCanCallSwitchLD > " + bool);
                Switch sGreeterPointCanCall = (Switch) view.findViewById(C4188R.id.sGreeterPointCanCall);
                Intrinsics.checkExpressionValueIsNotNull(sGreeterPointCanCall, "sGreeterPointCanCall");
                sGreeterPointCanCall.setChecked(bool != null ? bool.booleanValue() : false);
            }
        });
        LiveData<Boolean> cruiseCanCallSwitchLD = getCallVM().getCruiseCanCallSwitchLD();
        LifecycleOwner viewLifecycleOwner22 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner22, "viewLifecycleOwner");
        cruiseCanCallSwitchLD.observe(viewLifecycleOwner22, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$17
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                Boolean bool = (Boolean) t;
                str = this.TAG;
                Pdlog.m3275i(str, "cruiseCanCallSwitchLD > " + bool);
                Switch sCruiseCanCall = (Switch) view.findViewById(C4188R.id.sCruiseCanCall);
                Intrinsics.checkExpressionValueIsNotNull(sCruiseCanCall, "sCruiseCanCall");
                sCruiseCanCall.setChecked(bool != null ? bool.booleanValue() : false);
            }
        });
        LiveData<LoRaSignalDialog.SignType> loraRssiLD = getCallVM().getLoraRssiLD();
        LifecycleOwner viewLifecycleOwner23 = getViewLifecycleOwner();
        Intrinsics.checkExpressionValueIsNotNull(viewLifecycleOwner23, "viewLifecycleOwner");
        Observer<LoRaSignalDialog.SignType> observer = (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initObserver$$inlined$run$lambda$18
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                LoRaSignalDialog loRaSignalDialog;
                LoRaSignalDialog.SignType signType = (LoRaSignalDialog.SignType) t;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "loraRssiLD > " + signType);
                loRaSignalDialog = CallSettingFragment.this.loRaSignDialog;
                if (loRaSignalDialog != null) {
                    loRaSignalDialog.setSignalType(signType);
                }
            }
        };
        loraRssiLD.observe(viewLifecycleOwner23, observer);
        return observer;
    }

    public final void initView(final View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        TextView tvSwitchOpenApi = (TextView) view.findViewById(C4188R.id.tvSwitchOpenApi);
        Intrinsics.checkExpressionValueIsNotNull(tvSwitchOpenApi, "tvSwitchOpenApi");
        LinearLayout call_layout = (LinearLayout) view.findViewById(C4188R.id.call_layout);
        Intrinsics.checkExpressionValueIsNotNull(call_layout, "call_layout");
        initSwitchContent(tvSwitchOpenApi, call_layout);
        TextView tvSwitchPDLink = (TextView) view.findViewById(C4188R.id.tvSwitchPDLink);
        Intrinsics.checkExpressionValueIsNotNull(tvSwitchPDLink, "tvSwitchPDLink");
        ConstraintLayout llPDLinkContent = (ConstraintLayout) view.findViewById(C4188R.id.llPDLinkContent);
        Intrinsics.checkExpressionValueIsNotNull(llPDLinkContent, "llPDLinkContent");
        initSwitchContent(tvSwitchPDLink, llPDLinkContent);
        TextView tvSwitch4G = (TextView) view.findViewById(C4188R.id.tvSwitch4G);
        Intrinsics.checkExpressionValueIsNotNull(tvSwitch4G, "tvSwitch4G");
        LinearLayout ll4GContent = (LinearLayout) view.findViewById(C4188R.id.ll4GContent);
        Intrinsics.checkExpressionValueIsNotNull(ll4GContent, "ll4GContent");
        initSwitchContent(tvSwitch4G, ll4GContent);
        TextView tvSwitch1Key = (TextView) view.findViewById(C4188R.id.tvSwitch1Key);
        Intrinsics.checkExpressionValueIsNotNull(tvSwitch1Key, "tvSwitch1Key");
        LinearLayout ll1KeyContent = (LinearLayout) view.findViewById(C4188R.id.ll1KeyContent);
        Intrinsics.checkExpressionValueIsNotNull(ll1KeyContent, "ll1KeyContent");
        initSwitchContent(tvSwitch1Key, ll1KeyContent);
        TextView llSwitchReturn = (TextView) view.findViewById(C4188R.id.llSwitchReturn);
        Intrinsics.checkExpressionValueIsNotNull(llSwitchReturn, "llSwitchReturn");
        RecyclerView rvCallReturn = (RecyclerView) view.findViewById(C4188R.id.rvCallReturn);
        Intrinsics.checkExpressionValueIsNotNull(rvCallReturn, "rvCallReturn");
        initSwitchContent(llSwitchReturn, rvCallReturn);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) view.findViewById(C4188R.id.sLinkSwitch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$1
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
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.switchLinkCall(z);
            }
        }, 7, null);
        CardView cvFetch4GCode = (CardView) view.findViewById(C4188R.id.cvFetch4GCode);
        Intrinsics.checkExpressionValueIsNotNull(cvFetch4GCode, "cvFetch4GCode");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(cvFetch4GCode, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                RelativeLayout rl4GCodeContent = (RelativeLayout) view.findViewById(C4188R.id.rl4GCodeContent);
                Intrinsics.checkExpressionValueIsNotNull(rl4GCodeContent, "rl4GCodeContent");
                ViewExtKt.visible(rl4GCodeContent);
                callVM = this.getCallVM();
                callVM.fetch4GCode();
            }
        }, 3, null);
        CardView cvFetchLinkCode = (CardView) view.findViewById(C4188R.id.cvFetchLinkCode);
        Intrinsics.checkExpressionValueIsNotNull(cvFetchLinkCode, "cvFetchLinkCode");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(cvFetchLinkCode, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                RelativeLayout rlLinkCodeContent = (RelativeLayout) view.findViewById(C4188R.id.rlLinkCodeContent);
                Intrinsics.checkExpressionValueIsNotNull(rlLinkCodeContent, "rlLinkCodeContent");
                ViewExtKt.visible(rlLinkCodeContent);
                callVM = this.getCallVM();
                callVM.fetchOpenApiCode();
            }
        }, 3, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) view.findViewById(C4188R.id.sCallReached), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$4
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                CallSettingVM callVM;
                CallSettingVM callVM2;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                callVM = this.getCallVM();
                callVM.switchCallReached(z);
                LinearLayout call_reached_time_root = (LinearLayout) view.findViewById(C4188R.id.call_reached_time_root);
                Intrinsics.checkExpressionValueIsNotNull(call_reached_time_root, "call_reached_time_root");
                ViewExtKt.visibleOrGone(call_reached_time_root, z);
                callVM2 = this.getCallVM();
                callVM2.selectCallReturnPlace(0);
                LinearLayout llCallReturn = (LinearLayout) view.findViewById(C4188R.id.llCallReturn);
                Intrinsics.checkExpressionValueIsNotNull(llCallReturn, "llCallReturn");
                ViewExtKt.visibleOrGone(llCallReturn, z);
                if (z) {
                    return;
                }
                CallSettingFragment callSettingFragment = this;
                TextView llSwitchReturn2 = (TextView) view.findViewById(C4188R.id.llSwitchReturn);
                Intrinsics.checkExpressionValueIsNotNull(llSwitchReturn2, "llSwitchReturn");
                RecyclerView rvCallReturn2 = (RecyclerView) view.findViewById(C4188R.id.rvCallReturn);
                Intrinsics.checkExpressionValueIsNotNull(rvCallReturn2, "rvCallReturn");
                callSettingFragment.resetSwitchContent(llSwitchReturn2, rvCallReturn2);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) view.findViewById(C4188R.id.sCallAutoReached), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$5
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
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.switchCallAutoReached(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) view.findViewById(C4188R.id.sGreeterPointCanCall), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$6
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
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.switchGreeterPointCanCall(z);
            }
        }, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) view.findViewById(C4188R.id.sCruiseCanCall), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$7
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
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.switchCruiseCanCall(z);
            }
        }, 7, null);
        EditText etCallAutoReachedTime = (EditText) view.findViewById(C4188R.id.etCallAutoReachedTime);
        Intrinsics.checkExpressionValueIsNotNull(etCallAutoReachedTime, "etCallAutoReachedTime");
        etCallAutoReachedTime.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$8
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                CallSettingVM callVM;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "etCallAutoReachedTime > " + ((Object) s));
                callVM = CallSettingFragment.this.getCallVM();
                callVM.setCallAutoReachedTime(String.valueOf(s));
            }
        });
        EditText etFreezeCall = (EditText) view.findViewById(C4188R.id.etFreezeCall);
        Intrinsics.checkExpressionValueIsNotNull(etFreezeCall, "etFreezeCall");
        etFreezeCall.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$9
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                CallSettingVM callVM;
                str = CallSettingFragment.this.TAG;
                Pdlog.m3275i(str, "etFreezeCall > " + ((Object) s));
                callVM = CallSettingFragment.this.getCallVM();
                callVM.setCallFreezeTime(String.valueOf(s));
            }
        });
        TextView tvReSet = (TextView) view.findViewById(C4188R.id.tvReSet);
        Intrinsics.checkExpressionValueIsNotNull(tvReSet, "tvReSet");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(tvReSet, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.setCallFreezeTime(null);
            }
        }, 3, null);
        final RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(500L);
        rotateAnimation.setRepeatCount(0);
        ImageView iv1KeyRefresh = (ImageView) view.findViewById(C4188R.id.iv1KeyRefresh);
        Intrinsics.checkExpressionValueIsNotNull(iv1KeyRefresh, "iv1KeyRefresh");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(iv1KeyRefresh, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$11
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                ((ImageView) view.findViewById(C4188R.id.iv1KeyRefresh)).startAnimation(rotateAnimation);
                callVM = this.getCallVM();
                callVM.checkLoraConnectState();
            }
        }, 3, null);
        TextView tv1KeyGatewayName = (TextView) view.findViewById(C4188R.id.tv1KeyGatewayName);
        Intrinsics.checkExpressionValueIsNotNull(tv1KeyGatewayName, "tv1KeyGatewayName");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(tv1KeyGatewayName, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$12
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.fetch1KeyGateWay();
            }
        }, 3, null);
        ImageView iv1KeySelectGateway = (ImageView) view.findViewById(C4188R.id.iv1KeySelectGateway);
        Intrinsics.checkExpressionValueIsNotNull(iv1KeySelectGateway, "iv1KeySelectGateway");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(iv1KeySelectGateway, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$13
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.fetch1KeyGateWay();
            }
        }, 3, null);
        TextView tv1KeyFirmVersion = (TextView) view.findViewById(C4188R.id.tv1KeyFirmVersion);
        Intrinsics.checkExpressionValueIsNotNull(tv1KeyFirmVersion, "tv1KeyFirmVersion");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(tv1KeyFirmVersion, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$14
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.checkLoRaUpdate();
            }
        }, 3, null);
        LinearLayout ll1KeySignStrength = (LinearLayout) view.findViewById(C4188R.id.ll1KeySignStrength);
        Intrinsics.checkExpressionValueIsNotNull(ll1KeySignStrength, "ll1KeySignStrength");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(ll1KeySignStrength, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initView$$inlined$run$lambda$15
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view2) {
                invoke2(view2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                CallSettingFragment.this.showSignStrengthDialog();
                callVM = CallSettingFragment.this.getCallVM();
                callVM.startFetchLoraRssi();
            }
        }, 3, null);
        initCruiseReturnRecyclerView();
        initLocalServerView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSignStrengthDialog() {
        LoRaSignalDialog loRaSignalDialog = this.loRaSignDialog;
        if (loRaSignalDialog == null || !loRaSignalDialog.isShowing()) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            LoRaSignalDialog loRaSignalDialog2 = new LoRaSignalDialog(requireActivity);
            loRaSignalDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$showSignStrengthDialog$$inlined$apply$lambda$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    CallSettingVM callVM;
                    CallSettingFragment.this.loRaSignDialog = (LoRaSignalDialog) null;
                    callVM = CallSettingFragment.this.getCallVM();
                    callVM.stopFetchLoraRssi();
                }
            });
            loRaSignalDialog2.show();
            this.loRaSignDialog = loRaSignalDialog2;
        }
    }

    private final void initCruiseReturnRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C4188R.id.rvCallReturn);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setHasFixedSize(true);
        this.returnAdapter = new RadioBtnTvAdapter();
        recyclerView.setAdapter(this.returnAdapter);
        RadioBtnTvAdapter radioBtnTvAdapter = this.returnAdapter;
        if (radioBtnTvAdapter != null) {
            radioBtnTvAdapter.setOnItemClickListener(new OnLazyItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initCruiseReturnRecyclerView$$inlined$apply$lambda$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyItemClickListener
                public void onSingleItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                    CallSettingVM callVM;
                    Intrinsics.checkParameterIsNotNull(adapter, "adapter");
                    Intrinsics.checkParameterIsNotNull(view, "view");
                    callVM = CallSettingFragment.this.getCallVM();
                    callVM.selectCallReturnPlace(position);
                }
            });
        }
        LinearLayout llKeyBtnList = (LinearLayout) _$_findCachedViewById(C4188R.id.llKeyBtnList);
        Intrinsics.checkExpressionValueIsNotNull(llKeyBtnList, "llKeyBtnList");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(llKeyBtnList, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initCruiseReturnRecyclerView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.fetchKeyBtnList();
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0017, code lost:
    
        if (r0.isShowing() == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void showKeyBtnListDialog(List<KeyBtnWithDestination> list) {
        if (list == null) {
            KetBtnListDetailDialog ketBtnListDetailDialog = this.mKeyBtnListDialog;
            if (ketBtnListDetailDialog != null) {
                ketBtnListDetailDialog.dismiss();
                return;
            }
            return;
        }
        KetBtnListDetailDialog ketBtnListDetailDialog2 = this.mKeyBtnListDialog;
        if (ketBtnListDetailDialog2 != null) {
            if (ketBtnListDetailDialog2 == null) {
                Intrinsics.throwNpe();
            }
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        KetBtnListDetailDialog ketBtnListDetailDialog3 = new KetBtnListDetailDialog(requireActivity);
        ketBtnListDetailDialog3.setOnRefreshClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$showKeyBtnListDialog$$inlined$apply$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                CallSettingVM callVM;
                callVM = CallSettingFragment.this.getCallVM();
                CallSettingVM.refreshKeyBtnList$default(callVM, false, 1, null);
            }
        });
        this.mKeyBtnListDialog = ketBtnListDetailDialog3;
        KetBtnListDetailDialog ketBtnListDetailDialog4 = this.mKeyBtnListDialog;
        if (ketBtnListDetailDialog4 != null) {
            ketBtnListDetailDialog4.show();
        }
        KetBtnListDetailDialog ketBtnListDetailDialog5 = this.mKeyBtnListDialog;
        if (ketBtnListDetailDialog5 != null) {
            ketBtnListDetailDialog5.setData(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoraUpdateConfirmDialog(final Version version) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
        final AppUpdateTipMsgDialog appUpdateTipMsgDialog = new AppUpdateTipMsgDialog(requireActivity, new VerionResult(true, version));
        appUpdateTipMsgDialog.showBatteryInfo(false);
        appUpdateTipMsgDialog.setOnUpdateClick(new Function1<VerionResult, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$showLoraUpdateConfirmDialog$$inlined$run$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(VerionResult verionResult) {
                invoke2(verionResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(VerionResult it) {
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(it, "it");
                AppUpdateTipMsgDialog.this.dismiss();
                callVM = this.getCallVM();
                callVM.startLoRaUpdate(version);
            }
        });
        appUpdateTipMsgDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateLoRaUpdateDialog(Pair<Integer, Integer> data) {
        LoRaUpdateDialog loRaUpdateDialog = this.loRaUpdateDialog;
        if (loRaUpdateDialog == null || !loRaUpdateDialog.isShowing()) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(requireActivity, "requireActivity()");
            this.loRaUpdateDialog = new LoRaUpdateDialog(requireActivity);
            LoRaUpdateDialog loRaUpdateDialog2 = this.loRaUpdateDialog;
            if (loRaUpdateDialog2 != null) {
                loRaUpdateDialog2.show();
            }
        }
        LoRaUpdateDialog loRaUpdateDialog3 = this.loRaUpdateDialog;
        if (loRaUpdateDialog3 != null) {
            String string = getString(C4188R.string.pdStr16_93);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr16_93)");
            loRaUpdateDialog3.setTitle(string);
        }
        LoRaUpdateDialog loRaUpdateDialog4 = this.loRaUpdateDialog;
        if (loRaUpdateDialog4 != null) {
            loRaUpdateDialog4.setProgress(String.valueOf(data.getSecond().intValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showGatewaySelectDialog(final List<Gateway2> list) {
        Object obj;
        Context requireContext = requireContext();
        Intrinsics.checkExpressionValueIsNotNull(requireContext, "requireContext()");
        final RecycleViewDialog recycleViewDialog = new RecycleViewDialog(requireContext);
        RadioBtnTvAdapter radioBtnTvAdapter = new RadioBtnTvAdapter();
        radioBtnTvAdapter.setNewData(list);
        radioBtnTvAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$showGatewaySelectDialog$1
            @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> baseQuickAdapter, View view, int i) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((Gateway2) it.next()).setCheck(false);
                }
                ((Gateway2) list.get(i)).setCheck(true);
                baseQuickAdapter.notifyDataSetChanged();
                recycleViewDialog.setConfirmEnable(true);
            }
        });
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((Gateway2) obj).getCheck()) {
                    break;
                }
            }
        }
        if (obj == null) {
            recycleViewDialog.setConfirmEnable(false);
        }
        recycleViewDialog.setRecycleView(radioBtnTvAdapter, new GridLayoutManager(requireContext(), 2), new GridSpacingItemDecoration(2, DensityUtil.dp2px(getContext(), 24.0f), false));
        recycleViewDialog.setCloseIvVisibility(false);
        String string = getString(C4188R.string.call_select_gateway);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.call_select_gateway)");
        recycleViewDialog.setTitleText(string);
        recycleViewDialog.setConfirmListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$showGatewaySelectDialog$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Object obj2;
                CallSettingVM callVM;
                Iterator it2 = list.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj2 = null;
                        break;
                    } else {
                        obj2 = it2.next();
                        if (((Gateway2) obj2).getCheck()) {
                            break;
                        }
                    }
                }
                Gateway2 gateway2 = (Gateway2) obj2;
                if (gateway2 != null) {
                    callVM = CallSettingFragment.this.getCallVM();
                    callVM.select1keyGateway(gateway2.getMGateway());
                    recycleViewDialog.dismiss();
                }
            }
        });
        recycleViewDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetSwitchContent(TextView switchBtn, View switchContent) {
        ViewExtKt.gone(switchContent);
        Drawable drawable = getResources().getDrawable(C4188R.drawable.ic_icon_cell_arrow2);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        switchBtn.setCompoundDrawablesRelative(null, null, drawable, null);
        switchBtn.setText(C4188R.string.pdStr7_143);
    }

    private final void initSwitchContent(final TextView switchBtn, final View switchContent) {
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(switchBtn, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initSwitchContent$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                boolean z = switchContent.getVisibility() == 0;
                switchContent.setVisibility(z ? 8 : 0);
                if (z) {
                    Drawable drawable = CallSettingFragment.this.getResources().getDrawable(C4188R.drawable.ic_icon_cell_arrow2);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    switchBtn.setCompoundDrawablesRelative(null, null, drawable, null);
                    switchBtn.setText(C4188R.string.pdStr7_143);
                    return;
                }
                Drawable drawable2 = CallSettingFragment.this.getResources().getDrawable(C4188R.drawable.ic_icon_cell_arrow);
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                switchBtn.setCompoundDrawablesRelative(null, null, drawable2, null);
                switchBtn.setText(C4188R.string.pdStr7_144);
            }
        }, 3, null);
    }

    private final void initLocalServerView() {
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.local_open_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initLocalServerView$1
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
                String str;
                CallSettingVM callVM;
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                str = CallSettingFragment.this.TAG;
                Pdlog.m3273d(str, "initLocalServerView : buttonView = " + buttonView + "; isChecked = " + z + "; ");
                callVM = CallSettingFragment.this.getCallVM();
                callVM.switchLocalCall(z);
                if (!z) {
                    LinearLayout llOpenApiContent = (LinearLayout) CallSettingFragment.this._$_findCachedViewById(C4188R.id.llOpenApiContent);
                    Intrinsics.checkExpressionValueIsNotNull(llOpenApiContent, "llOpenApiContent");
                    llOpenApiContent.setVisibility(0);
                    EditText edittext_broker_host = (EditText) CallSettingFragment.this._$_findCachedViewById(C4188R.id.edittext_broker_host);
                    Intrinsics.checkExpressionValueIsNotNull(edittext_broker_host, "edittext_broker_host");
                    edittext_broker_host.setEnabled(true);
                    return;
                }
                LinearLayout llOpenApiContent2 = (LinearLayout) CallSettingFragment.this._$_findCachedViewById(C4188R.id.llOpenApiContent);
                Intrinsics.checkExpressionValueIsNotNull(llOpenApiContent2, "llOpenApiContent");
                llOpenApiContent2.setVisibility(8);
                EditText edittext_broker_host2 = (EditText) CallSettingFragment.this._$_findCachedViewById(C4188R.id.edittext_broker_host);
                Intrinsics.checkExpressionValueIsNotNull(edittext_broker_host2, "edittext_broker_host");
                edittext_broker_host2.setEnabled(false);
            }
        }, 7, null);
        EditText edittext_broker_host = (EditText) _$_findCachedViewById(C4188R.id.edittext_broker_host);
        Intrinsics.checkExpressionValueIsNotNull(edittext_broker_host, "edittext_broker_host");
        edittext_broker_host.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.CallSettingFragment$initLocalServerView$$inlined$addTextChangedListener$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                CallSettingVM callVM;
                EditText edittext_broker_host2 = (EditText) CallSettingFragment.this._$_findCachedViewById(C4188R.id.edittext_broker_host);
                Intrinsics.checkExpressionValueIsNotNull(edittext_broker_host2, "edittext_broker_host");
                String obj = edittext_broker_host2.getText().toString();
                if (obj != null) {
                    String obj2 = StringsKt.trim((CharSequence) obj).toString();
                    str = CallSettingFragment.this.TAG;
                    Pdlog.m3273d(str, "edittext_broker_host " + obj2);
                    callVM = CallSettingFragment.this.getCallVM();
                    callVM.setHost(obj2);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        });
    }
}
