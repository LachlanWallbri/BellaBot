package com.pudutech.bumblebee.robot_ui.p054ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iflytek.cloud.SpeechUtility;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract;
import com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowDownloadingDialog;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.VoiceModeAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.VoiceModeItemProvider;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.BusinessVoiceVm;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.disinfect.baselib.util.UiUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: VoiceModeDialog.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001SB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010.\u001a\u00020)H\u0016J\b\u0010/\u001a\u00020\u001aH\u0016J\b\u00100\u001a\u00020\"H\u0002J\u0010\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020\u0007H\u0002J\u0010\u00103\u001a\u00020)2\u0006\u00104\u001a\u000205H\u0014J\u0016\u00106\u001a\u00020)2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0002J\u001a\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u000b2\u0006\u0010;\u001a\u00020\u001aH\u0002J\u0006\u0010<\u001a\u00020)J\b\u0010=\u001a\u00020)H\u0014J\u0010\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020\u001aH\u0002J\u0010\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u000209H\u0002J\b\u0010B\u001a\u00020)H\u0016J\"\u0010C\u001a\u00020)2\u0006\u0010D\u001a\u0002092\b\u0010E\u001a\u0004\u0018\u00010\u00072\u0006\u0010F\u001a\u00020\u001aH\u0016J\u0018\u0010G\u001a\u00020)2\u0006\u00104\u001a\u0002052\u0006\u0010H\u001a\u00020IH\u0002J\b\u0010J\u001a\u00020)H\u0002J\u0018\u0010K\u001a\u00020)2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\"H\u0016J\b\u0010O\u001a\u00020)H\u0002J\u0016\u0010P\u001a\u00020)2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016J\u0010\u0010Q\u001a\u00020)2\u0006\u0010R\u001a\u00020\"H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0011\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R9\u0010$\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0004\u0012\u00020)\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006T"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceModeDialog;", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/BumbleBaseDialog;", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$ViewInterface;", "context", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "TAG", "", "_Job", "Lkotlinx/coroutines/Job;", "_adapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/VoiceModeAdapter;", "_businessVoiceVm", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm;", "get_businessVoiceVm", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/BusinessVoiceVm;", "_businessVoiceVm$delegate", "Lkotlin/Lazy;", "_coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "get_coroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "_coroutineScope$delegate", "_downProgressDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowDownloadingDialog;", "_lastPosition", "", "_robotVoicesPresenter", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesPresenter;", "get_robotVoicesPresenter", "()Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesPresenter;", "_robotVoicesPresenter$delegate", "_voiceList", "", "Lcom/pudutech/bumblebee/presenter/robot_voices_task/VoicePackageInfo;", "mActivity", "onComplete", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "voiceName", "", "getOnComplete", "()Lkotlin/jvm/functions/Function1;", "setOnComplete", "(Lkotlin/jvm/functions/Function1;)V", "dismiss", "getLayoutId", "getMachineTtsVoice", "getTitleItem", "title", "initView", "view", "Landroid/view/View;", "initVoiceList", "all", "isTypeDef", "", "adapter", RequestParameters.POSITION, "onDestory", "setData", "setLimitHeight", "size", "setTtsCompositeStatus", "isSuccess", "show", "showCouldSyncResult", "success", NotificationCompat.CATEGORY_MESSAGE, "code", "showDeletePopupWindow", "onClickListener", "Landroid/view/View$OnClickListener;", "showDownloadFinishTip", "showDownloadProgress", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/bumblebee/presenter/robot_voices_task/RobotVoicesContract$DownloadResult;", "info", "showFailedTip", "showListSync", "showVoicePackDownloadDialog", "item", "GridSpacingItemDecoration", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class VoiceModeDialog extends BumbleBaseDialog implements RobotVoicesContract.ViewInterface {
    private final String TAG;
    private Job _Job;
    private VoiceModeAdapter _adapter;

    /* renamed from: _businessVoiceVm$delegate, reason: from kotlin metadata */
    private final Lazy _businessVoiceVm;

    /* renamed from: _coroutineScope$delegate, reason: from kotlin metadata */
    private final Lazy _coroutineScope;
    private ShowDownloadingDialog _downProgressDialog;
    private int _lastPosition;

    /* renamed from: _robotVoicesPresenter$delegate, reason: from kotlin metadata */
    private final Lazy _robotVoicesPresenter;
    private final List<VoicePackageInfo> _voiceList;
    private Activity mActivity;
    private Function1<? super String, Unit> onComplete;

    /* JADX INFO: Access modifiers changed from: private */
    public final BusinessVoiceVm get_businessVoiceVm() {
        return (BusinessVoiceVm) this._businessVoiceVm.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CoroutineScope get_coroutineScope() {
        return (CoroutineScope) this._coroutineScope.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RobotVoicesPresenter get_robotVoicesPresenter() {
        return (RobotVoicesPresenter) this._robotVoicesPresenter.getValue();
    }

    public final void onDestory() {
    }

    public static final /* synthetic */ ShowDownloadingDialog access$get_downProgressDialog$p(VoiceModeDialog voiceModeDialog) {
        ShowDownloadingDialog showDownloadingDialog = voiceModeDialog._downProgressDialog;
        if (showDownloadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        return showDownloadingDialog;
    }

    public final Function1<String, Unit> getOnComplete() {
        return this.onComplete;
    }

    public final void setOnComplete(Function1<? super String, Unit> function1) {
        this.onComplete = function1;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceModeDialog(Activity context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = "VoiceModeDialog";
        this._robotVoicesPresenter = LazyKt.lazy(new Function0<RobotVoicesPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$_robotVoicesPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RobotVoicesPresenter invoke() {
                RobotVoicesPresenter robotVoicesPresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(RobotVoicesPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(RobotVoicesPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    robotVoicesPresenter = new RobotVoicesPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(RobotVoicesPresenter.class).toString(), robotVoicesPresenter);
                } else {
                    if (!(basePresenterInterface instanceof RobotVoicesPresenter)) {
                        basePresenterInterface = null;
                    }
                    robotVoicesPresenter = (RobotVoicesPresenter) basePresenterInterface;
                }
                if (robotVoicesPresenter != null) {
                    return robotVoicesPresenter;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesPresenter");
            }
        });
        this._coroutineScope = LazyKt.lazy(new Function0<CoroutineScope>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$_coroutineScope$2
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                return CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            }
        });
        this._businessVoiceVm = LazyKt.lazy(new Function0<BusinessVoiceVm>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$_businessVoiceVm$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BusinessVoiceVm invoke() {
                CoroutineScope coroutineScope;
                coroutineScope = VoiceModeDialog.this.get_coroutineScope();
                return new BusinessVoiceVm(coroutineScope);
            }
        });
        this._voiceList = new ArrayList();
        this._lastPosition = -1;
        this.mActivity = context;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public int getLayoutId() {
        return C4188R.layout.dialog_voice_mode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void initView(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this._downProgressDialog = new ShowDownloadingDialog(context);
        Button voice_mode_sure = (Button) findViewById(C4188R.id.voice_mode_sure);
        Intrinsics.checkExpressionValueIsNotNull(voice_mode_sure, "voice_mode_sure");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(voice_mode_sure, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$initView$1
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                VoiceModeDialog.this.dismiss();
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog
    public void setData() {
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        this._adapter = new VoiceModeAdapter(arrayList, context);
        final VoiceModeAdapter voiceModeAdapter = this._adapter;
        if (voiceModeAdapter != null) {
            voiceModeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$setData$$inlined$let$lambda$1
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                    int i2;
                    String str;
                    RobotVoicesPresenter robotVoicesPresenter;
                    String str2;
                    BusinessVoiceVm businessVoiceVm;
                    String str3;
                    int i3;
                    if (this.isTypeDef(VoiceModeAdapter.this, i)) {
                        i2 = this._lastPosition;
                        if (i2 == i) {
                            str3 = this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("setOnItemClickListener() _lastPosition =");
                            i3 = this._lastPosition;
                            sb.append(i3);
                            sb.append(" position =");
                            sb.append(i);
                            Pdlog.m3273d(str3, sb.toString());
                            return;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                        Object obj = adapter.getData().get(i);
                        if (obj != null) {
                            VoicePackageInfo voicePackageInfo = (VoicePackageInfo) obj;
                            if (voicePackageInfo.getId() == VoicePackageHelper.INSTANCE.getDefaultBusinessVoice()) {
                                businessVoiceVm = this.get_businessVoiceVm();
                                businessVoiceVm.loadTtsVoice();
                                this._lastPosition = i;
                            } else if (voicePackageInfo.getIsExist()) {
                                str = this.TAG;
                                Pdlog.m3273d(str, "setOnItemClickListener select " + obj);
                                robotVoicesPresenter = this.get_robotVoicesPresenter();
                                robotVoicesPresenter.select(voicePackageInfo);
                                TtsVoiceManager.INSTANCE.resetMerchantTtsAllData();
                                this._lastPosition = i;
                            }
                            str2 = this.TAG;
                            Pdlog.m3273d(str2, "setOnItemClickListener() item =" + obj);
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo");
                    }
                }
            });
            voiceModeAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$setData$$inlined$let$lambda$2
                @Override // com.chad.library.adapter.base.BaseQuickAdapter.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter<Object, BaseViewHolder> adapter, View view, int i) {
                    if (!this.isTypeDef(VoiceModeAdapter.this, i)) {
                        return true;
                    }
                    Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                    Object obj = adapter.getData().get(i);
                    if (obj != null) {
                        if (!Intrinsics.areEqual(VoicePackageHelper.INSTANCE.getVoiceType((VoicePackageInfo) obj), VoicePlayerHelper.VoiceType.CustomVoice.INSTANCE)) {
                            return true;
                        }
                        List<Object> data = adapter.getData();
                        Object obj2 = data != null ? data.get(i) : null;
                        if (obj2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo");
                        }
                        final VoicePackageInfo voicePackageInfo = (VoicePackageInfo) obj2;
                        if (!voicePackageInfo.getIsExist() || view == null) {
                            return true;
                        }
                        this.showDeletePopupWindow(view, new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$setData$$inlined$let$lambda$2.1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                RobotVoicesPresenter robotVoicesPresenter;
                                robotVoicesPresenter = this.get_robotVoicesPresenter();
                                robotVoicesPresenter.delete(voicePackageInfo);
                            }
                        });
                        return true;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageInfo");
                }
            });
            VoiceModeItemProvider voiceModeItemProvider = voiceModeAdapter.get_voiceModeItemProvider();
            if (voiceModeItemProvider != null) {
                voiceModeItemProvider.setOnUpdateClick(new Function2<VoicePackageInfo, Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$setData$$inlined$let$lambda$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(VoicePackageInfo voicePackageInfo, Integer num) {
                        invoke(voicePackageInfo, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(VoicePackageInfo item, int i) {
                        String str;
                        String str2;
                        String str3;
                        CoroutineScope coroutineScope;
                        Job launch$default;
                        Intrinsics.checkParameterIsNotNull(item, "item");
                        if (item.getIsExist() && item.getNewVersionAvailable()) {
                            str2 = VoiceModeDialog.this.TAG;
                            Pdlog.m3273d(str2, "onUpdateClick() " + item);
                            if (item.getId() != VoicePackageHelper.INSTANCE.getDefaultBusinessVoice()) {
                                VoiceModeDialog.this.showVoicePackDownloadDialog(item);
                                str3 = VoiceModeDialog.this.TAG;
                                Pdlog.m3273d(str3, "onUpdateClick() showVoicePackDownloadDialog " + item);
                                return;
                            }
                            VoiceModeDialog voiceModeDialog = VoiceModeDialog.this;
                            coroutineScope = voiceModeDialog.get_coroutineScope();
                            launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C43251(item, null), 3, null);
                            voiceModeDialog._Job = launch$default;
                            return;
                        }
                        if (item.getIsExist()) {
                            return;
                        }
                        str = VoiceModeDialog.this.TAG;
                        Pdlog.m3273d(str, "onUpdateClick() download " + item);
                        VoiceModeDialog.this.showVoicePackDownloadDialog(item);
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* compiled from: VoiceModeDialog.kt */
                    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/robot_ui/ui/dialog/VoiceModeDialog$setData$1$3$1"}, m3962k = 3, m3963mv = {1, 1, 16})
                    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$setData$$inlined$let$lambda$3$1 */
                    /* loaded from: classes3.dex */
                    public static final class C43251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ VoicePackageInfo $item;
                        Object L$0;
                        int label;

                        /* renamed from: p$ */
                        private CoroutineScope f4915p$;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C43251(VoicePackageInfo voicePackageInfo, Continuation continuation) {
                            super(2, continuation);
                            this.$item = voicePackageInfo;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C43251 c43251 = new C43251(this.$item, completion);
                            c43251.f4915p$ = (CoroutineScope) obj;
                            return c43251;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C43251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            BusinessVoiceVm businessVoiceVm;
                            String str;
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                CoroutineScope coroutineScope = this.f4915p$;
                                businessVoiceVm = VoiceModeDialog.this.get_businessVoiceVm();
                                this.L$0 = coroutineScope;
                                this.label = 1;
                                if (businessVoiceVm.updateTts(this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            str = VoiceModeDialog.this.TAG;
                            Pdlog.m3273d(str, "onUpdateClick() updateTts " + this.$item);
                            return Unit.INSTANCE;
                        }
                    }
                });
            }
            RecyclerView voice_mode_rv = (RecyclerView) findViewById(C4188R.id.voice_mode_rv);
            Intrinsics.checkExpressionValueIsNotNull(voice_mode_rv, "voice_mode_rv");
            voice_mode_rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
            ((RecyclerView) findViewById(C4188R.id.voice_mode_rv)).addItemDecoration(new GridSpacingItemDecoration());
            RecyclerView voice_mode_rv2 = (RecyclerView) findViewById(C4188R.id.voice_mode_rv);
            Intrinsics.checkExpressionValueIsNotNull(voice_mode_rv2, "voice_mode_rv");
            voice_mode_rv2.setAdapter(voiceModeAdapter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showVoicePackDownloadDialog(final VoicePackageInfo item) {
        get_robotVoicesPresenter().download(item);
        ShowDownloadingDialog showDownloadingDialog = this._downProgressDialog;
        if (showDownloadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        showDownloadingDialog.showCancel();
        ShowDownloadingDialog showDownloadingDialog2 = this._downProgressDialog;
        if (showDownloadingDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        showDownloadingDialog2.setTitle(getString(C4188R.string.pdStr7_91));
        ShowDownloadingDialog showDownloadingDialog3 = this._downProgressDialog;
        if (showDownloadingDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        showDownloadingDialog3.showProgress(String.valueOf(0));
        ShowDownloadingDialog showDownloadingDialog4 = this._downProgressDialog;
        if (showDownloadingDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        showDownloadingDialog4.setOnCloseBtnClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$showVoicePackDownloadDialog$1
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
                String str;
                RobotVoicesPresenter robotVoicesPresenter;
                str = VoiceModeDialog.this.TAG;
                Pdlog.m3273d(str, "cancelDownload " + item);
                robotVoicesPresenter = VoiceModeDialog.this.get_robotVoicesPresenter();
                robotVoicesPresenter.cancelDownload(item);
            }
        });
        ShowDownloadingDialog showDownloadingDialog5 = this._downProgressDialog;
        if (showDownloadingDialog5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        showDownloadingDialog5.show();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.dialog.BumbleBaseDialog, android.app.Dialog
    public void show() {
        super.show();
        get_robotVoicesPresenter().replaceView((RobotVoicesContract.ViewInterface) this);
        get_businessVoiceVm().setOnResult(new Function1<BusinessVoiceVm.TtsResult, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$show$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BusinessVoiceVm.TtsResult ttsResult) {
                invoke2(ttsResult);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BusinessVoiceVm.TtsResult it) {
                String str;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (Intrinsics.areEqual(it, BusinessVoiceVm.TtsResult.ResultFailure.INSTANCE)) {
                    VoiceModeDialog.this._lastPosition = -1;
                    VoiceModeDialog.this.setTtsCompositeStatus(false);
                    VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).dismiss();
                    VoiceModeDialog.this.showFailedTip();
                } else if (Intrinsics.areEqual(it, BusinessVoiceVm.TtsResult.ResultSelected.INSTANCE)) {
                    VoiceModeDialog.this.setTtsCompositeStatus(true);
                } else if (Intrinsics.areEqual(it, BusinessVoiceVm.TtsResult.ResultSuccess.INSTANCE)) {
                    VoiceModeDialog.this.setTtsCompositeStatus(true);
                    VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).dismiss();
                    VoiceModeDialog.this.showDownloadFinishTip();
                } else if (it instanceof BusinessVoiceVm.TtsResult.ResultProgress) {
                    VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).showProgress(String.valueOf(((BusinessVoiceVm.TtsResult.ResultProgress) it).getProgress()));
                    if (!VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).isShowing()) {
                        VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).hideCancel();
                        VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).setTitle(VoiceModeDialog.this.getString(C4188R.string.pdStr7_91));
                        VoiceModeDialog.access$get_downProgressDialog$p(VoiceModeDialog.this).show();
                    }
                }
                str = VoiceModeDialog.this.TAG;
                Pdlog.m3273d(str, "onResult() " + it);
            }
        });
        get_robotVoicesPresenter().syncList();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        get_robotVoicesPresenter().removeView((RobotVoicesContract.ViewInterface) this);
        Job job = this._Job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        BusinessVoiceVm businessVoiceVm = get_businessVoiceVm();
        businessVoiceVm.setOnResult((Function1) null);
        businessVoiceVm.onCleared();
    }

    private final void setLimitHeight(int size) {
        if (size >= 3) {
            RecyclerView voice_mode_rv = (RecyclerView) findViewById(C4188R.id.voice_mode_rv);
            Intrinsics.checkExpressionValueIsNotNull(voice_mode_rv, "voice_mode_rv");
            ViewGroup.LayoutParams layoutParams = voice_mode_rv.getLayoutParams();
            layoutParams.height = UiUtils.dip2px(410.0f);
            RecyclerView voice_mode_rv2 = (RecyclerView) findViewById(C4188R.id.voice_mode_rv);
            Intrinsics.checkExpressionValueIsNotNull(voice_mode_rv2, "voice_mode_rv");
            voice_mode_rv2.setLayoutParams(layoutParams);
            Pdlog.m3273d(this.TAG, "setLimitHeight()  410");
        }
    }

    /* compiled from: VoiceModeDialog.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceModeDialog$GridSpacingItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "(Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceModeDialog;)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    private final class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        public GridSpacingItemDecoration() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkParameterIsNotNull(outRect, "outRect");
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            Intrinsics.checkParameterIsNotNull(state, "state");
            super.getItemOffsets(outRect, view, parent, state);
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            VoiceModeDialog voiceModeDialog = VoiceModeDialog.this;
            boolean isTypeDef = voiceModeDialog.isTypeDef(voiceModeDialog._adapter, childAdapterPosition);
            int i = childAdapterPosition % 2;
            if (isTypeDef) {
                outRect.top = UiUtils.dip2px(16.0f);
            } else {
                outRect.top = UiUtils.dip2px(24.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTypeDef(VoiceModeAdapter adapter, int position) {
        return adapter != null && adapter.getItemViewType(position) == 0;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.ViewInterface
    public void showListSync(List<VoicePackageInfo> all) {
        Intrinsics.checkParameterIsNotNull(all, "all");
        setLimitHeight(all.size());
        initVoiceList(all);
    }

    private final void initVoiceList(List<VoicePackageInfo> all) {
        if (this._voiceList.size() > 0) {
            this._voiceList.clear();
        }
        this._voiceList.add(getTitleItem(getString(C4188R.string.voice_pack_live)));
        this._voiceList.addAll(all);
        if (!LanguageUtils.INSTANCE.isNoSupportMerchant()) {
            this._voiceList.add(getTitleItem(getString(C4188R.string.voice_pack_machine)));
            this._voiceList.add(getMachineTtsVoice());
        }
        if (VoicePackageHelper.INSTANCE.isSelectMerchantTts()) {
            new VoicePackageHelper().setSelectedIDRecord((String) null);
            get_businessVoiceVm().loadTtsVoice();
            for (VoicePackageInfo voicePackageInfo : this._voiceList) {
                voicePackageInfo.setSelected(voicePackageInfo.getId() == VoicePackageHelper.INSTANCE.getDefaultBusinessVoice());
            }
        }
        Function1<? super String, Unit> function1 = this.onComplete;
        if (function1 != null) {
            for (VoicePackageInfo voicePackageInfo2 : this._voiceList) {
                if (voicePackageInfo2.getSelected()) {
                    function1.invoke(voicePackageInfo2.getName());
                }
            }
        }
        Pdlog.m3273d(this.TAG, "initVoiceList() _voiceList =" + this._voiceList);
        VoiceModeAdapter voiceModeAdapter = this._adapter;
        if (voiceModeAdapter != null) {
            voiceModeAdapter.setNewData(this._voiceList);
        }
    }

    private final VoicePackageInfo getMachineTtsVoice() {
        VoicePackageInfo voicePackageInfo = new VoicePackageInfo();
        voicePackageInfo.setId(VoicePackageHelper.INSTANCE.getDefaultBusinessVoice());
        voicePackageInfo.setExist(true);
        voicePackageInfo.setName(getString(C4188R.string.advance_battery_guard_level_default));
        return voicePackageInfo;
    }

    private final VoicePackageInfo getTitleItem(String title) {
        VoicePackageInfo voicePackageInfo = new VoicePackageInfo();
        voicePackageInfo.setId(-10L);
        voicePackageInfo.setName(title);
        voicePackageInfo.setViewType(1);
        return voicePackageInfo;
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.ViewInterface
    public void showCouldSyncResult(boolean success, String msg, int code) {
        if (success) {
            return;
        }
        ToastUtils.show(getContext(), getString(C4188R.string.pdStr7_88), new Object[0]);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_voices_task.RobotVoicesContract.ViewInterface
    public void showDownloadProgress(RobotVoicesContract.DownloadResult result, VoicePackageInfo info) {
        Intrinsics.checkParameterIsNotNull(result, "result");
        Intrinsics.checkParameterIsNotNull(info, "info");
        ShowDownloadingDialog showDownloadingDialog = this._downProgressDialog;
        if (showDownloadingDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
        }
        if (showDownloadingDialog.isShowing()) {
            Pdlog.m3273d(this.TAG, "showDownloadProgress " + info);
            ShowDownloadingDialog showDownloadingDialog2 = this._downProgressDialog;
            if (showDownloadingDialog2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
            }
            showDownloadingDialog2.showProgress(String.valueOf(info.getDownloadProgress()));
        }
        if (result == RobotVoicesContract.DownloadResult.SUCCESS) {
            ShowDownloadingDialog showDownloadingDialog3 = this._downProgressDialog;
            if (showDownloadingDialog3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
            }
            showDownloadingDialog3.dismiss();
            showDownloadFinishTip();
            VoiceModeAdapter voiceModeAdapter = this._adapter;
            if (voiceModeAdapter != null) {
                voiceModeAdapter.notifyDataSetChanged();
            }
        } else if (result == RobotVoicesContract.DownloadResult.FAIL) {
            ShowDownloadingDialog showDownloadingDialog4 = this._downProgressDialog;
            if (showDownloadingDialog4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("_downProgressDialog");
            }
            showDownloadingDialog4.dismiss();
            showFailedTip();
            get_robotVoicesPresenter().syncList();
        }
        Pdlog.m3273d(this.TAG, "showDownloadProgress() result =" + result + " info =" + info);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFailedTip() {
        ShowTipMsgDialog showTipMsgDialog = new ShowTipMsgDialog(this.mActivity);
        showTipMsgDialog.showTipMsg(getString(C4188R.string.pdStr7_90));
        showTipMsgDialog.setCanCancel(true);
        showTipMsgDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDownloadFinishTip() {
        ShowTipMsgDialog showTipMsgDialog = new ShowTipMsgDialog(this.mActivity);
        showTipMsgDialog.showTipMsg(getString(C4188R.string.pdStr7_89));
        showTipMsgDialog.setCanCancel(false);
        showTipMsgDialog.show();
        showTipMsgDialog.autoHide(1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTtsCompositeStatus(boolean isSuccess) {
        if (!isSuccess && !VoicePackageHelper.INSTANCE.isSelectMerchantTts()) {
            TtsVoiceManager.INSTANCE.resetMerchantTtsAllData();
        }
        if (isSuccess) {
            VoicePackageHelper.INSTANCE.setSelectMerchantTts(true);
            new VoicePackageHelper().setSelectedIDRecord((String) null);
        }
        Iterator<T> it = this._voiceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            VoicePackageInfo voicePackageInfo = (VoicePackageInfo) it.next();
            voicePackageInfo.setSelected(false);
            if (isSuccess || VoicePackageHelper.INSTANCE.isSelectMerchantTts()) {
                if (voicePackageInfo.getId() == VoicePackageHelper.INSTANCE.getDefaultBusinessVoice()) {
                    voicePackageInfo.setSelected(true);
                    voicePackageInfo.setNewVersionAvailable(get_businessVoiceVm().getIsUpdate());
                }
            } else if (Intrinsics.areEqual(String.valueOf(voicePackageInfo.getId()), new VoicePackageHelper().getSelectedIDRecord())) {
                voicePackageInfo.setSelected(true);
            } else {
                String selectedIDRecord = new VoicePackageHelper().getSelectedIDRecord();
                if ((selectedIDRecord == null || selectedIDRecord.length() == 0) && voicePackageInfo.getId() == VoicePackageHelper.INSTANCE.getDefaultID()) {
                    voicePackageInfo.setSelected(true);
                }
            }
        }
        Function1<? super String, Unit> function1 = this.onComplete;
        if (function1 != null) {
            for (VoicePackageInfo voicePackageInfo2 : this._voiceList) {
                if (voicePackageInfo2.getSelected()) {
                    function1.invoke(voicePackageInfo2.getName());
                }
            }
        }
        VoiceModeAdapter voiceModeAdapter = this._adapter;
        if (voiceModeAdapter != null) {
            voiceModeAdapter.setNewData(this._voiceList);
        }
        Pdlog.m3273d(this.TAG, "setTtsCompositeStatus() isSuccess =" + isSuccess);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeletePopupWindow(View view, final View.OnClickListener onClickListener) {
        View inflate = getLayoutInflater().inflate(C4188R.layout.layout_popupwindow_delete, (ViewGroup) null, false);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2, false);
        inflate.setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceModeDialog$showDeletePopupWindow$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick(View v) {
                Intrinsics.checkParameterIsNotNull(v, "v");
                super.onSingleClick(v);
                onClickListener.onClick(v);
                popupWindow.dismiss();
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(true);
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        if (600 - iArr[1] < 150) {
            popupWindow.showAsDropDown(view, (view.getWidth() / 2) - (popupWindow.getWidth() / 2), -view.getHeight());
        } else {
            popupWindow.showAsDropDown(view, (view.getWidth() / 2) - (popupWindow.getWidth() / 2), -10);
        }
    }
}
