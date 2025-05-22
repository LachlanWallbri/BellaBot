package com.pudutech.bumblebee.robot_ui.module.setting.p053ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.ims.IMSKit;
import com.pudutech.bumblebee.business.ims.config.LoRaChannelConfig;
import com.pudutech.bumblebee.business.ims.event.CEvent;
import com.pudutech.bumblebee.business.ims.event.CEventCenter;
import com.pudutech.bumblebee.business.ims.event.Events;
import com.pudutech.bumblebee.business.ims.event.I_CEventListener;
import com.pudutech.bumblebee.business.ims.lora.Channel;
import com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2;
import com.pudutech.bumblebee.business.ims.utils.ShortUUID;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.hola_ims.IMSPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.base.BaseFragment;
import com.pudutech.bumblebee.robot_ui.bean.Beeper;
import com.pudutech.bumblebee.robot_ui.bean.CentralControl;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.database.DatabaseManagerFactory;
import com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder;
import com.pudutech.bumblebee.robot_ui.manager.ImsTaskObjHolder;
import com.pudutech.bumblebee.robot_ui.manager.LocalConfigManager;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.BeeperAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.ChannelSettingAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.DeleteBoxDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.SelectBindBoxDialog;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.BeeperUtils;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.NavigationBarUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: BoxSettingsFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010D\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017H\u0002J\u000e\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001b0FH\u0002J\b\u0010G\u001a\u00020$H\u0016J\b\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020IH\u0002J\b\u0010K\u001a\u00020IH\u0002J\b\u0010L\u001a\u00020IH\u0002J\b\u0010M\u001a\u00020IH\u0002J\u0012\u0010N\u001a\u00020I2\b\u0010O\u001a\u0004\u0018\u00010PH\u0016J,\u0010Q\u001a\u00020I2\b\u0010R\u001a\u0004\u0018\u00010\u00072\u0006\u0010S\u001a\u00020$2\u0006\u0010T\u001a\u00020$2\b\u0010U\u001a\u0004\u0018\u00010VH\u0016J\u0010\u0010W\u001a\u00020I2\b\u0010X\u001a\u0004\u0018\u00010YJ\b\u0010Z\u001a\u00020IH\u0016J\b\u0010[\u001a\u00020IH\u0016J\u001c\u0010\\\u001a\u00020I2\b\u0010]\u001a\u0004\u0018\u00010\u00072\b\u0010^\u001a\u0004\u0018\u00010_H\u0016J\b\u0010`\u001a\u00020IH\u0016J\u0010\u0010a\u001a\u00020I2\u0006\u0010b\u001a\u00020\u001bH\u0016J\b\u0010c\u001a\u00020IH\u0002J\b\u0010d\u001a\u00020IH\u0002J \u0010e\u001a\u00020I2\u0016\u0010f\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017H\u0002J\b\u0010g\u001a\u00020IH\u0002J\b\u0010h\u001a\u00020IH\u0002J\b\u0010i\u001a\u00020IH\u0002J\b\u0010j\u001a\u00020IH\u0002J\b\u0010k\u001a\u00020IH\u0002R\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\n \f*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000RK\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u0015j\b\u0012\u0004\u0012\u00020\u001b`\u00172\u0016\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u0015j\b\u0012\u0004\u0012\u00020\u001b`\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0012\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0004\n\u0002\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b+\u0010,R\u001b\u0010/\u001a\u0002008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010.\u001a\u0004\b1\u00102R\u000e\u00104\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020;X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010?\u001a\u00020@8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u0010.\u001a\u0004\bA\u0010B¨\u0006l"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/BoxSettingsFragment;", "Lcom/pudutech/bumblebee/robot_ui/base/BaseFragment;", "Lcom/pudutech/bumblebee/business/ims/event/I_CEventListener;", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$OnSetupChannelListener;", "()V", "EVENTS", "", "", "getEVENTS", "()[Ljava/lang/String;", "[Ljava/lang/String;", "TAG", "kotlin.jvm.PlatformType", "addBeeperMsgId", "animation", "Landroid/animation/ValueAnimator;", "beeperAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/BeeperAdapter;", "beeperLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "beeperList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/bean/Beeper;", "Lkotlin/collections/ArrayList;", "channelAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/ChannelSettingAdapter;", "<set-?>", "Lcom/pudutech/bumblebee/business/ims/lora/Channel;", "channelList", "getChannelList", "()Ljava/util/ArrayList;", "setChannelList", "(Ljava/util/ArrayList;)V", "channelList$delegate", "Lkotlin/properties/ReadWriteProperty;", "channelRecyclerHeight", "", "Ljava/lang/Integer;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "currentSelectedPos", "deleteDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DeleteBoxDialog;", "getDeleteDialog", "()Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DeleteBoxDialog;", "deleteDialog$delegate", "Lkotlin/Lazy;", "imsPresenter", "Lcom/pudutech/bumblebee/presenter/hola_ims/IMSPresenter;", "getImsPresenter", "()Lcom/pudutech/bumblebee/presenter/hola_ims/IMSPresenter;", "imsPresenter$delegate", "isOpenChannelSetting", "", "lastSelectedPos", "loadingDialog", "Landroid/app/Dialog;", "mLayoutManager", "rvBeeper", "Landroidx/recyclerview/widget/RecyclerView;", "rvChannel", "selectBindBoxDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/SelectBindBoxDialog;", "voiceClickListener", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleVoiceClickListener;", "getVoiceClickListener", "()Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleVoiceClickListener;", "voiceClickListener$delegate", "getBeeperData", "getChannelData", "", "getLayoutId", "hideChangeChannelLoading", "", "initBeeperLayout", "initControlCenterLayout", "initLayout", "notifyBeeperData2File", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCEvent", "topic", "msgCode", "resultCode", "obj", "", "onClick", "v", "Landroid/view/View;", "onDestroy", "onDestroyView", "onFailed", "errMsg", "resetFailedType", "Lcom/pudutech/bumblebee/business/ims/lora/LoRaChannelManager2$ResetFailedType;", "onSetting", "onSucceed", "channel", "removeSendingMsg", "resetRvBeeperHeight", "setBeeperAdapter", "beeperData", "setBeeperLayoutManager", "setChannelAdapterData", "setLinearLayoutManager", "setOnclickListener", "showChangeChannelLoading", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class BoxSettingsFragment extends BaseFragment implements I_CEventListener, LoRaChannelManager2.OnSetupChannelListener {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(BoxSettingsFragment.class), "channelList", "getChannelList()Ljava/util/ArrayList;"))};
    private HashMap _$_findViewCache;
    private String addBeeperMsgId;
    private ValueAnimator animation;
    private BeeperAdapter beeperAdapter;
    private LinearLayoutManager beeperLayoutManager;
    private ChannelSettingAdapter channelAdapter;
    private Integer channelRecyclerHeight;
    private boolean isOpenChannelSetting;
    private Dialog loadingDialog;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView rvBeeper;
    private RecyclerView rvChannel;
    private SelectBindBoxDialog selectBindBoxDialog;

    /* renamed from: voiceClickListener$delegate, reason: from kotlin metadata */
    private final Lazy voiceClickListener = LazyKt.lazy(new Function0<SingleVoiceClickListener>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$voiceClickListener$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SingleVoiceClickListener invoke() {
            return new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$voiceClickListener$2.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(View it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(View view) {
                    invoke2(view);
                    return Unit.INSTANCE;
                }
            }, 3, null);
        }
    });
    private int currentSelectedPos = -1;
    private int lastSelectedPos = -1;

    /* renamed from: channelList$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty channelList = Delegates.INSTANCE.notNull();
    private ArrayList<Beeper> beeperList = new ArrayList<>();

    /* renamed from: imsPresenter$delegate, reason: from kotlin metadata */
    private final Lazy imsPresenter = LazyKt.lazy(new Function0<IMSPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$imsPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IMSPresenter invoke() {
            IMSPresenter iMSPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IMSPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(IMSPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                iMSPresenter = new IMSPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(IMSPresenter.class).toString(), iMSPresenter);
            } else {
                if (!(basePresenterInterface instanceof IMSPresenter)) {
                    basePresenterInterface = null;
                }
                iMSPresenter = (IMSPresenter) basePresenterInterface;
            }
            if (iMSPresenter != null) {
                return iMSPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.hola_ims.IMSPresenter");
        }
    });
    private final String TAG = getClass().getSimpleName();
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getMain());
    private final String[] EVENTS = {Events.EVENT_ADD_DEVICE, com.pudutech.bumblebee.robot_ui.event.Events.EVENT_RESET_ALL_CONFIG, com.pudutech.bumblebee.robot_ui.event.Events.EVENT_READ_CONFIG};

    /* renamed from: deleteDialog$delegate, reason: from kotlin metadata */
    private final Lazy deleteDialog = LazyKt.lazy(new Function0<DeleteBoxDialog>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$deleteDialog$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeleteBoxDialog invoke() {
            final DeleteBoxDialog deleteBoxDialog = new DeleteBoxDialog();
            deleteBoxDialog.setOnNegativeClickListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$deleteDialog$2$1$1
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
                    DeleteBoxDialog.this.dismissDialog();
                }
            });
            return deleteBoxDialog;
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<Channel> getChannelList() {
        return (ArrayList) this.channelList.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeleteBoxDialog getDeleteDialog() {
        return (DeleteBoxDialog) this.deleteDialog.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IMSPresenter getImsPresenter() {
        return (IMSPresenter) this.imsPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SingleVoiceClickListener getVoiceClickListener() {
        return (SingleVoiceClickListener) this.voiceClickListener.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChannelList(ArrayList<Channel> arrayList) {
        this.channelList.setValue(this, $$delegatedProperties[0], arrayList);
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
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

    @Override // com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2.OnSetupChannelListener
    public void onSetting() {
    }

    public static final /* synthetic */ RecyclerView access$getRvBeeper$p(BoxSettingsFragment boxSettingsFragment) {
        RecyclerView recyclerView = boxSettingsFragment.rvBeeper;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvBeeper");
        }
        return recyclerView;
    }

    public static final /* synthetic */ RecyclerView access$getRvChannel$p(BoxSettingsFragment boxSettingsFragment) {
        RecyclerView recyclerView = boxSettingsFragment.rvChannel;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvChannel");
        }
        return recyclerView;
    }

    public final String[] getEVENTS() {
        return this.EVENTS;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment
    public int getLayoutId() {
        return C4188R.layout.fragment_box_settings;
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        if (getIsCreate()) {
            return;
        }
        super.onActivityCreated(savedInstanceState);
        CEventCenter.registerEventListener(this, this.EVENTS);
        initLayout();
    }

    private final void initLayout() {
        TextView tv_bound_beeper_name = (TextView) _$_findCachedViewById(C4188R.id.tv_bound_beeper_name);
        Intrinsics.checkExpressionValueIsNotNull(tv_bound_beeper_name, "tv_bound_beeper_name");
        if (StringsKt.isBlank(tv_bound_beeper_name.getText().toString())) {
            TextView tv_bound_beeper_name2 = (TextView) _$_findCachedViewById(C4188R.id.tv_bound_beeper_name);
            Intrinsics.checkExpressionValueIsNotNull(tv_bound_beeper_name2, "tv_bound_beeper_name");
            tv_bound_beeper_name2.setText(BeeperUtils.INSTANCE.generateRandomCentralControlCode());
        }
        Channel channel = LocalConfigManager.INSTANCE.getINSTANCE().getChannel();
        if (channel != null) {
            TextView tv_channel = (TextView) _$_findCachedViewById(C4188R.id.tv_channel);
            Intrinsics.checkExpressionValueIsNotNull(tv_channel, "tv_channel");
            tv_channel.setText(String.valueOf(channel.getChannelId()));
        }
        ((Switch) _$_findCachedViewById(C4188R.id.open_beeper_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$initLayout$2
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
                SingleVoiceClickListener voiceClickListener;
                IMSPresenter imsPresenter;
                String str;
                Intrinsics.checkParameterIsNotNull(v, "v");
                voiceClickListener = BoxSettingsFragment.this.getVoiceClickListener();
                voiceClickListener.onClick(v);
                if (z) {
                    BoxSettingsFragment.this.addBeeperMsgId = ShortUUID.INSTANCE.randomUUID();
                    imsPresenter = BoxSettingsFragment.this.getImsPresenter();
                    str = BoxSettingsFragment.this.addBeeperMsgId;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    TextView tv_bound_beeper_name3 = (TextView) BoxSettingsFragment.this._$_findCachedViewById(C4188R.id.tv_bound_beeper_name);
                    Intrinsics.checkExpressionValueIsNotNull(tv_bound_beeper_name3, "tv_bound_beeper_name");
                    imsPresenter.addBeeperBroadcastControl(z, str, tv_bound_beeper_name3.getText().toString(), new Function1<MessageProtobuf.Msg, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$initLayout$2.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg) {
                            invoke2(msg);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MessageProtobuf.Msg msg) {
                            String str2;
                            Intrinsics.checkParameterIsNotNull(msg, "msg");
                            str2 = BoxSettingsFragment.this.TAG;
                            Pdlog.m3275i(str2, "openOrCloseBeeperSuccess msg: " + msg);
                        }
                    }, new Function2<MessageProtobuf.Msg, String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$initLayout$2.3
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(MessageProtobuf.Msg msg, String str2) {
                            invoke2(msg, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MessageProtobuf.Msg msg, String reason) {
                            String str2;
                            Intrinsics.checkParameterIsNotNull(reason, "reason");
                            str2 = BoxSettingsFragment.this.TAG;
                            Pdlog.m3275i(str2, "openOrCloseBeeperFail msg: " + msg + "    reason: " + reason);
                        }
                    });
                    ConstraintLayout bound_beeper = (ConstraintLayout) BoxSettingsFragment.this._$_findCachedViewById(C4188R.id.bound_beeper);
                    Intrinsics.checkExpressionValueIsNotNull(bound_beeper, "bound_beeper");
                    bound_beeper.setVisibility(0);
                    ConstraintLayout control_center = (ConstraintLayout) BoxSettingsFragment.this._$_findCachedViewById(C4188R.id.control_center);
                    Intrinsics.checkExpressionValueIsNotNull(control_center, "control_center");
                    control_center.setVisibility(0);
                    return;
                }
                ConstraintLayout bound_beeper2 = (ConstraintLayout) BoxSettingsFragment.this._$_findCachedViewById(C4188R.id.bound_beeper);
                Intrinsics.checkExpressionValueIsNotNull(bound_beeper2, "bound_beeper");
                bound_beeper2.setVisibility(8);
                ConstraintLayout control_center2 = (ConstraintLayout) BoxSettingsFragment.this._$_findCachedViewById(C4188R.id.control_center);
                Intrinsics.checkExpressionValueIsNotNull(control_center2, "control_center");
                control_center2.setVisibility(8);
                BoxSettingsFragment.this.removeSendingMsg();
            }
        }, 7, null));
        initControlCenterLayout();
        initBeeperLayout();
        setOnclickListener();
    }

    private final void setOnclickListener() {
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.tv_refresh);
        final BoxSettingsFragment$setOnclickListener$1 boxSettingsFragment$setOnclickListener$1 = new BoxSettingsFragment$setOnclickListener$1(this);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$sam$android_view_View_OnClickListener$0
            @Override // android.view.View.OnClickListener
            public final /* synthetic */ void onClick(View view) {
                Intrinsics.checkExpressionValueIsNotNull(Function1.this.invoke(view), "invoke(...)");
            }
        });
    }

    public final void onClick(View v) {
        if (v == null || v.getId() != C4188R.id.tv_refresh) {
            return;
        }
        TextView tv_bound_beeper_name = (TextView) _$_findCachedViewById(C4188R.id.tv_bound_beeper_name);
        Intrinsics.checkExpressionValueIsNotNull(tv_bound_beeper_name, "tv_bound_beeper_name");
        tv_bound_beeper_name.setText(BeeperUtils.INSTANCE.generateRandomCentralControlCode());
    }

    private final void initBeeperLayout() {
        View rootView = getRootView();
        if (rootView == null) {
            Intrinsics.throwNpe();
        }
        View findViewById = rootView.findViewById(C4188R.id.rv_call_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "rootView!!.findViewById(R.id.rv_call_list)");
        this.rvBeeper = (RecyclerView) findViewById;
        ArrayList<Beeper> beeperData = getBeeperData();
        if (!beeperData.isEmpty()) {
            TextView tv_beeper_list = (TextView) _$_findCachedViewById(C4188R.id.tv_beeper_list);
            Intrinsics.checkExpressionValueIsNotNull(tv_beeper_list, "tv_beeper_list");
            tv_beeper_list.setVisibility(0);
            RecyclerView recyclerView = this.rvBeeper;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rvBeeper");
            }
            recyclerView.setVisibility(0);
        }
        setBeeperLayoutManager();
        if (this.beeperAdapter != null) {
            return;
        }
        setBeeperAdapter(beeperData);
        Unit unit = Unit.INSTANCE;
    }

    private final void setBeeperAdapter(final ArrayList<Beeper> beeperData) {
        BeeperAdapter beeperAdapter = new BeeperAdapter();
        beeperAdapter.setNewData(beeperData);
        beeperAdapter.setOnDeleteClickListener(new Function2<Beeper, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$setBeeperAdapter$$inlined$also$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Beeper beeper, Integer num) {
                invoke(beeper, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final Beeper beeper, final int i) {
                final DeleteBoxDialog deleteDialog;
                DeleteBoxDialog deleteDialog2;
                Intrinsics.checkParameterIsNotNull(beeper, "beeper");
                deleteDialog = BoxSettingsFragment.this.getDeleteDialog();
                deleteDialog.setOnPositiveClickListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$setBeeperAdapter$$inlined$also$lambda$1.1
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
                        String str;
                        BeeperAdapter beeperAdapter2;
                        ArrayList arrayList;
                        ArrayList arrayList2;
                        str = BoxSettingsFragment.this.TAG;
                        Pdlog.m3275i(str, "delete beeper:  " + beeper);
                        beeperAdapter2 = BoxSettingsFragment.this.beeperAdapter;
                        if (beeperAdapter2 != null) {
                            beeperAdapter2.notifyItemRangeRemoved(i, beeperData.size());
                        }
                        DatabaseManagerFactory.INSTANCE.getDatabaseManager().deleteBeeper(beeper.getMac());
                        arrayList = BoxSettingsFragment.this.beeperList;
                        arrayList.remove(beeper);
                        BoxSettingsFragment.this.notifyBeeperData2File();
                        BoxSettingsFragment.this.resetRvBeeperHeight();
                        arrayList2 = BoxSettingsFragment.this.beeperList;
                        if (arrayList2.isEmpty()) {
                            BoxSettingsFragment.access$getRvBeeper$p(BoxSettingsFragment.this).setVisibility(8);
                        }
                        Constans constans = Constans.INSTANCE;
                        String mac = beeper.getMac();
                        Intrinsics.checkExpressionValueIsNotNull(mac, "beeper.mac");
                        constans.removeWatchTableBindInfo(mac);
                        DeleteBoxDialog.this.dismissDialog();
                    }
                });
                deleteDialog2 = BoxSettingsFragment.this.getDeleteDialog();
                FragmentActivity activity = BoxSettingsFragment.this.getActivity();
                if (activity == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                deleteDialog2.showDialog(activity.getSupportFragmentManager(), "deleteDialog");
            }
        });
        beeperAdapter.setOnItemClickListener(new Function2<Beeper, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$setBeeperAdapter$$inlined$also$lambda$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Beeper beeper, Integer num) {
                invoke(beeper, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Beeper beeper, int i) {
                SelectBindBoxDialog selectBindBoxDialog;
                SelectBindBoxDialog selectBindBoxDialog2;
                SelectBindBoxDialog selectBindBoxDialog3;
                Intrinsics.checkParameterIsNotNull(beeper, "beeper");
                System.out.println((Object) ("**************" + beeper + ": " + i));
                selectBindBoxDialog = BoxSettingsFragment.this.selectBindBoxDialog;
                if (selectBindBoxDialog == null) {
                    BoxSettingsFragment.this.selectBindBoxDialog = new SelectBindBoxDialog();
                }
                selectBindBoxDialog2 = BoxSettingsFragment.this.selectBindBoxDialog;
                if (selectBindBoxDialog2 != null) {
                    selectBindBoxDialog2.setBeeper(beeper);
                }
                selectBindBoxDialog3 = BoxSettingsFragment.this.selectBindBoxDialog;
                if (selectBindBoxDialog3 != null) {
                    FragmentActivity activity = BoxSettingsFragment.this.getActivity();
                    if (activity == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
                    selectBindBoxDialog3.showDialog(activity.getSupportFragmentManager(), "selectBindBoxDialog");
                }
            }
        });
        this.beeperAdapter = beeperAdapter;
        RecyclerView recyclerView = this.rvBeeper;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvBeeper");
        }
        recyclerView.setAdapter(this.beeperAdapter);
    }

    private final void initControlCenterLayout() {
        View rootView = getRootView();
        if (rootView == null) {
            Intrinsics.throwNpe();
        }
        View findViewById = rootView.findViewById(C4188R.id.rv_channel_list);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "rootView!!.findViewById(R.id.rv_channel_list)");
        this.rvChannel = (RecyclerView) findViewById;
        ((ConstraintLayout) _$_findCachedViewById(C4188R.id.control_center)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$initControlCenterLayout$1
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
                ValueAnimator valueAnimator;
                ValueAnimator valueAnimator2;
                boolean z;
                boolean z2;
                ValueAnimator valueAnimator3;
                ValueAnimator valueAnimator4;
                Intrinsics.checkParameterIsNotNull(it, "it");
                valueAnimator = BoxSettingsFragment.this.animation;
                if (valueAnimator == null) {
                    final BoxSettingsFragment boxSettingsFragment = BoxSettingsFragment.this;
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat((ImageView) boxSettingsFragment._$_findCachedViewById(C4188R.id.iv_right), "rotation", 0.0f, 90.0f);
                    ofFloat.setDuration(200L);
                    boxSettingsFragment.animation = ofFloat;
                    valueAnimator3 = boxSettingsFragment.animation;
                    if (valueAnimator3 != null) {
                        valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$initControlCenterLayout$1$1$2
                            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                            public final void onAnimationUpdate(ValueAnimator it2) {
                                Integer num;
                                RecyclerView access$getRvChannel$p = BoxSettingsFragment.access$getRvChannel$p(BoxSettingsFragment.this);
                                num = BoxSettingsFragment.this.channelRecyclerHeight;
                                if (num != null) {
                                    int intValue = num.intValue();
                                    ViewGroup.LayoutParams layoutParams = access$getRvChannel$p.getLayoutParams();
                                    Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                    layoutParams.height = ((int) (((intValue * Float.parseFloat(it2.getAnimatedValue().toString())) * 100) / 90)) / 100;
                                    access$getRvChannel$p.setLayoutParams(layoutParams);
                                }
                            }
                        });
                    }
                    valueAnimator4 = boxSettingsFragment.animation;
                    if (valueAnimator4 != null) {
                        valueAnimator4.addListener(new AnimatorListenerAdapter() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$initControlCenterLayout$1$1$3
                            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                            public void onAnimationStart(Animator animation) {
                                LinearLayoutManager linearLayoutManager;
                                ChannelSettingAdapter channelSettingAdapter;
                                super.onAnimationStart(animation);
                                linearLayoutManager = BoxSettingsFragment.this.mLayoutManager;
                                if (linearLayoutManager == null) {
                                    BoxSettingsFragment.this.setLinearLayoutManager();
                                    Unit unit = Unit.INSTANCE;
                                }
                                channelSettingAdapter = BoxSettingsFragment.this.channelAdapter;
                                if (channelSettingAdapter != null) {
                                    return;
                                }
                                BoxSettingsFragment.this.setChannelAdapterData();
                                Unit unit2 = Unit.INSTANCE;
                            }
                        });
                    }
                }
                valueAnimator2 = BoxSettingsFragment.this.animation;
                if (valueAnimator2 != null) {
                    z2 = BoxSettingsFragment.this.isOpenChannelSetting;
                    if (!z2) {
                        valueAnimator2.start();
                    } else {
                        valueAnimator2.reverse();
                    }
                }
                BoxSettingsFragment boxSettingsFragment2 = BoxSettingsFragment.this;
                z = boxSettingsFragment2.isOpenChannelSetting;
                boxSettingsFragment2.isOpenChannelSetting = !z;
            }
        }, 3, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLinearLayoutManager() {
        if (this.mLayoutManager == null) {
            BoxSettingsFragment boxSettingsFragment = this;
            boxSettingsFragment.mLayoutManager = new LinearLayoutManager(boxSettingsFragment.getContext(), 1, false);
        }
        RecyclerView recyclerView = this.rvChannel;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvChannel");
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(this.mLayoutManager);
    }

    private final void setBeeperLayoutManager() {
        if (this.beeperLayoutManager == null) {
            BoxSettingsFragment boxSettingsFragment = this;
            boxSettingsFragment.beeperLayoutManager = new LinearLayoutManager(boxSettingsFragment.getContext(), 1, false);
        }
        RecyclerView recyclerView = this.rvBeeper;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvBeeper");
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(this.beeperLayoutManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChannelAdapterData() {
        Context it = getContext();
        if (it == null || this.channelAdapter != null) {
            return;
        }
        Intrinsics.checkExpressionValueIsNotNull(it, "it");
        ChannelSettingAdapter channelSettingAdapter = new ChannelSettingAdapter(it);
        List<Channel> channelData = getChannelData();
        channelSettingAdapter.setNewData(channelData);
        channelSettingAdapter.setCurrentSelectPos(Integer.valueOf(this.currentSelectedPos));
        this.channelRecyclerHeight = Integer.valueOf(DensityUtil.dp2px(channelSettingAdapter.getContext(), 80.0f) * channelData.size());
        channelSettingAdapter.setOnCheckedListener(new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$setChannelAdapterData$$inlined$let$lambda$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                ArrayList channelList;
                IMSPresenter imsPresenter;
                channelList = BoxSettingsFragment.this.getChannelList();
                Channel channel = (Channel) channelList.get(i);
                Channel channel2 = new Channel(new BigDecimal(String.valueOf(channel.getReceiveFrequency())).add(LoRaChannelConfig.INSTANCE.getStepFrequency()).floatValue(), new BigDecimal(String.valueOf(channel.getReceiveFrequency())).add(LoRaChannelConfig.INSTANCE.getStepFrequency()).floatValue());
                channel2.setChannelId(channel.getChannelId());
                imsPresenter = BoxSettingsFragment.this.getImsPresenter();
                imsPresenter.setupLocalChannel(channel2, BoxSettingsFragment.this);
                BoxSettingsFragment.this.showChangeChannelLoading();
            }
        });
        this.channelAdapter = channelSettingAdapter;
        RecyclerView recyclerView = this.rvChannel;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvChannel");
        }
        recyclerView.setAdapter(this.channelAdapter);
    }

    private final ArrayList<Beeper> getBeeperData() {
        CentralControl centralControl = LocalConfigManager.INSTANCE.getINSTANCE().getCentralControl();
        if (centralControl != null) {
            IMSKit.INSTANCE.getInstance().setCentralControlMac(centralControl.getMac());
        }
        ArrayList<Beeper> beeperList = LocalConfigManager.INSTANCE.getINSTANCE().getBeeperList();
        if (beeperList != null) {
            this.beeperList.addAll(beeperList);
            DatabaseManagerFactory.INSTANCE.getDatabaseManager().saveBeeperList(this.beeperList);
        }
        DatabaseManagerFactory.INSTANCE.getDatabaseManager().saveBeeperCardList(LocalConfigManager.INSTANCE.getINSTANCE().getBeeperCardList());
        CEvent cEvent = new CEvent();
        cEvent.setTopic(com.pudutech.bumblebee.robot_ui.event.Events.EVENT_READ_CONFIG);
        CEventCenter.dispatchEvent(cEvent);
        return this.beeperList;
    }

    private final List<Channel> getChannelData() {
        setChannelList(LoRaChannelManager2.INSTANCE.getINSTANCE().getChannelList());
        Channel channel = LocalConfigManager.INSTANCE.getINSTANCE().getChannel();
        Pdlog.m3275i(this.TAG, "currentChannel : " + channel);
        if (channel != null) {
            int i = 0;
            for (Object obj : getChannelList()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Channel channel2 = (Channel) obj;
                Pdlog.m3275i(this.TAG, "channelInfo: " + channel2);
                if (channel2.getChannelId() == channel.getChannelId()) {
                    this.currentSelectedPos = i;
                }
                i = i2;
            }
        }
        this.lastSelectedPos = this.currentSelectedPos;
        return getChannelList();
    }

    @Override // com.pudutech.bumblebee.business.ims.event.I_CEventListener
    public void onCEvent(String topic, int msgCode, int resultCode, Object obj) {
        Pdlog.m3275i(this.TAG, "topic: " + topic + "   msgCode: " + msgCode + "   result: " + resultCode + "    obj: " + obj);
        if (ImsTaskObjHolder.INSTANCE.getRemoteMsg() == null) {
            ImsTaskObjHolder.INSTANCE.setRemoteMsg((MessageProtobuf.Msg) (!(obj instanceof MessageProtobuf.Msg) ? null : obj));
            Unit unit = Unit.INSTANCE;
        }
        if (topic == null) {
            return;
        }
        int hashCode = topic.hashCode();
        if (hashCode == -53605402) {
            if (topic.equals(com.pudutech.bumblebee.robot_ui.event.Events.EVENT_READ_CONFIG)) {
                BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new BoxSettingsFragment$onCEvent$4(this, null), 3, null);
                return;
            }
            return;
        }
        if (hashCode != 420378517) {
            if (hashCode == 540670585 && topic.equals(Events.EVENT_ADD_DEVICE)) {
                if (obj != null) {
                    MessageProtobuf.Msg msg = (MessageProtobuf.Msg) obj;
                    Pdlog.m3273d(this.TAG, "onCEvent() event add device msg = " + msg);
                    if (msg.getCallPoint() == null) {
                        return;
                    }
                    String sender = msg.getSender();
                    String str = sender;
                    if (str == null || str.length() == 0) {
                        return;
                    }
                    Beeper beeper = new Beeper();
                    beeper.setMac(sender);
                    if (this.beeperList.contains(beeper)) {
                        return;
                    }
                    DatabaseManagerFactory.INSTANCE.getDatabaseManager().saveBeeper(beeper);
                    this.beeperList.add(beeper);
                    notifyBeeperData2File();
                    BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new BoxSettingsFragment$onCEvent$2(this, null), 3, null);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.protobuf.MessageProtobuf.Msg");
            }
            return;
        }
        if (topic.equals(com.pudutech.bumblebee.robot_ui.event.Events.EVENT_RESET_ALL_CONFIG)) {
            BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new BoxSettingsFragment$onCEvent$3(this, null), 3, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetRvBeeperHeight() {
        RecyclerView recyclerView = this.rvBeeper;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rvBeeper");
        }
        if (recyclerView != null) {
            ViewGroup.LayoutParams layoutParams = recyclerView.getLayoutParams();
            layoutParams.height = this.beeperList.size() * DensityUtil.dp2px(recyclerView.getContext(), 80.0f);
            recyclerView.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showChangeChannelLoading() {
        Dialog dialog = this.loadingDialog;
        if (dialog != null) {
            if (dialog != null) {
                dialog.show();
                return;
            }
            return;
        }
        FragmentActivity activity = getActivity();
        LayoutInflater layoutInflater = activity != null ? activity.getLayoutInflater() : null;
        View inflate = layoutInflater != null ? layoutInflater.inflate(C4188R.layout.dialog_loading, (ViewGroup) null) : null;
        if (inflate == null) {
            Intrinsics.throwNpe();
        }
        View findViewById = inflate.findViewById(C4188R.id.title_tv);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(id)");
        ((TextView) findViewById).setText(getString(C4188R.string.dialog_swith_lora_channel));
        this.loadingDialog = new Dialog(getActivity());
        Dialog dialog2 = this.loadingDialog;
        if (dialog2 != null) {
            dialog2.requestWindowFeature(1);
        }
        Dialog dialog3 = this.loadingDialog;
        if (dialog3 != null) {
            dialog3.setCancelable(false);
        }
        Dialog dialog4 = this.loadingDialog;
        Window window = dialog4 != null ? dialog4.getWindow() : null;
        NavigationBarUtil.focusNotAle(window);
        Dialog dialog5 = this.loadingDialog;
        if (dialog5 != null) {
            dialog5.show();
        }
        NavigationBarUtil.hideNavigationBar(window);
        NavigationBarUtil.clearFocusNotAle(window);
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        if (window != null) {
            window.setGravity(17);
        }
        if (attributes != null) {
            attributes.width = -1;
        }
        if (attributes != null) {
            attributes.height = -1;
        }
        if (window != null) {
            window.setAttributes(attributes);
        }
        Dialog dialog6 = this.loadingDialog;
        if (dialog6 != null) {
            dialog6.setContentView(inflate);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideChangeChannelLoading() {
        Dialog dialog = this.loadingDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyBeeperData2File() {
        LocalConfigManager.INSTANCE.getINSTANCE().setBeeperList(this.beeperList);
    }

    @Override // com.pudutech.bumblebee.robot_ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
        CEventCenter.unregisterEventListener(this, this.EVENTS);
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ValueAnimator valueAnimator = this.animation;
        if (valueAnimator != null) {
            if (!(valueAnimator.isStarted() || valueAnimator.isRunning())) {
                valueAnimator = null;
            }
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
        }
        removeSendingMsg();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSendingMsg() {
        String str = this.addBeeperMsgId;
        if (str != null) {
            ImsPresenterHolder.INSTANCE.removeMsg(str);
        }
        this.addBeeperMsgId = (String) null;
    }

    @Override // com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2.OnSetupChannelListener
    public void onFailed(String errMsg, LoRaChannelManager2.ResetFailedType resetFailedType) {
        Pdlog.m3274e(this.TAG, "switch local channel fail : " + errMsg);
        requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$onFailed$$inlined$runOnUiThread$1
            /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
            
                r3 = r6.this$0.channelAdapter;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                ChannelSettingAdapter channelSettingAdapter;
                ChannelSettingAdapter channelSettingAdapter2;
                List<Channel> data;
                ChannelSettingAdapter channelSettingAdapter3;
                BoxSettingsFragment.this.hideChangeChannelLoading();
                channelSettingAdapter = BoxSettingsFragment.this.channelAdapter;
                if (channelSettingAdapter != null && (data = channelSettingAdapter.getData()) != null) {
                    int i = 0;
                    for (Object obj : data) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Channel channel = (Channel) obj;
                        Channel channel2 = LocalConfigManager.INSTANCE.getINSTANCE().getChannel();
                        if (channel2 != null && channel2.getChannelId() == channel.getChannelId() && channelSettingAdapter3 != null) {
                            channelSettingAdapter3.setCurrentSelectPos(Integer.valueOf(i));
                        }
                        i = i2;
                    }
                }
                channelSettingAdapter2 = BoxSettingsFragment.this.channelAdapter;
                if (channelSettingAdapter2 != null) {
                    channelSettingAdapter2.notifyDataSetChanged();
                }
                ToastUtils.show(BoxSettingsFragment.this.requireContext(), BoxSettingsFragment.this.getString(C4188R.string.toast_lora_channel_swith_failed), new Object[0]);
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.ims.lora.LoRaChannelManager2.OnSetupChannelListener
    public void onSucceed(final Channel channel) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Pdlog.m3275i(this.TAG, "switch local channel success :" + channel);
        requireActivity().runOnUiThread(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.module.setting.ui.BoxSettingsFragment$onSucceed$$inlined$runOnUiThread$1
            /* JADX WARN: Code restructure failed: missing block: B:13:0x005d, code lost:
            
                r2 = r5.this$0.channelAdapter;
             */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void run() {
                ChannelSettingAdapter channelSettingAdapter;
                ChannelSettingAdapter channelSettingAdapter2;
                List<Channel> data;
                ChannelSettingAdapter channelSettingAdapter3;
                LocalConfigManager.INSTANCE.getINSTANCE().setChannel(channel);
                TextView tv_channel = (TextView) BoxSettingsFragment.this._$_findCachedViewById(C4188R.id.tv_channel);
                Intrinsics.checkExpressionValueIsNotNull(tv_channel, "tv_channel");
                tv_channel.setText(String.valueOf(channel.getChannelId()));
                channelSettingAdapter = BoxSettingsFragment.this.channelAdapter;
                if (channelSettingAdapter != null && (data = channelSettingAdapter.getData()) != null) {
                    int i = 0;
                    for (Object obj : data) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        if (channel.getChannelId() == ((Channel) obj).getChannelId() && channelSettingAdapter3 != null) {
                            channelSettingAdapter3.setCurrentSelectPos(Integer.valueOf(i));
                        }
                        i = i2;
                    }
                }
                channelSettingAdapter2 = BoxSettingsFragment.this.channelAdapter;
                if (channelSettingAdapter2 != null) {
                    channelSettingAdapter2.notifyDataSetChanged();
                }
                BoxSettingsFragment.this.hideChangeChannelLoading();
            }
        });
    }
}
