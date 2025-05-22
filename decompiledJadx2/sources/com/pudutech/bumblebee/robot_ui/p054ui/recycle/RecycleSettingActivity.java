package com.pudutech.bumblebee.robot_ui.p054ui.recycle;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.RecycleTtsVoiceAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.CustomTtsVoiceInputDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.recycle.RecycleSettingActivity;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListenerKt;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecycleSettingActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0087\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0012\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001b\u001a\u00020\rH\u0002J\u0010\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\fH\u0002J\b\u0010\u001e\u001a\u00020\rH\u0002J\b\u0010\u001f\u001a\u00020\rH\u0016J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\fH\u0002J\u0012\u0010!\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\rH\u0002J\u0010\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0016J\u0010\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020,H\u0016J=\u0010-\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\u00062!\u0010/\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b1\u0012\b\b2\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020\r00H\u0002J\b\u00104\u001a\u00020\rH\u0016J\u0010\u00105\u001a\u00020\r2\u0006\u00106\u001a\u00020)H\u0016J\u0010\u00107\u001a\u00020\r2\u0006\u0010+\u001a\u000208H\u0016J\b\u00109\u001a\u00020\rH\u0002J\b\u0010:\u001a\u00020\rH\u0002R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\r0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006;"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecycleSettingActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/disinfect/baselib/base/viewmodel/BaseViewModel;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "controllerListener", "Lkotlin/Function3;", "Lcom/chad/library/adapter/base/BaseViewHolder;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "", "deleteListener", "Lkotlin/Function2;", "editClickListener", "pointAdapter", "com/pudutech/bumblebee/robot_ui/ui/recycle/RecycleSettingActivity$pointAdapter$1", "Lcom/pudutech/bumblebee/robot_ui/ui/recycle/RecycleSettingActivity$pointAdapter$1;", "pointArriveAdapter", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/RecycleTtsVoiceAdapter;", "tableArriveAdapter", "tableLeaveAdapter", "voiceSwitchChanged", "Landroid/widget/CompoundButton;", "", "bindPresenter", "canAdd", "type", "clearEditFocus", "createObserver", "getAdapterByType", "initView", "saveInstanceState", "Landroid/os/Bundle;", "initVoice", "jumpAndFinish", "intent", "Landroid/content/Intent;", "layoutId", "", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showCustomInputDialog", AIUIConstant.KEY_CONTENT, "onContentChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "s", "showLowerNotify", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "stopPlay", "unbindPresenter", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RecycleSettingActivity extends MyBaseVmActivity<BaseViewModel> implements BatteryContract2.ViewInterface {
    private HashMap _$_findViewCache;
    private final RecycleSettingActivity$pointAdapter$1 pointAdapter;
    private final RecycleTtsVoiceAdapter pointArriveAdapter;
    private final RecycleTtsVoiceAdapter tableArriveAdapter;
    private final RecycleTtsVoiceAdapter tableLeaveAdapter;
    private final String TAG = getClass().getSimpleName();
    private final Function2<CompoundButton, Boolean, Unit> voiceSwitchChanged = new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$voiceSwitchChanged$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
            invoke(compoundButton, bool.booleanValue());
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code restructure failed: missing block: B:35:0x00a0, code lost:
        
            if (r0 != false) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00fa, code lost:
        
            if (r0 != false) goto L54;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0045, code lost:
        
            if (r0 != false) goto L14;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void invoke(CompoundButton buttonView, boolean z) {
            TtsVoiceHelper.TtsVoiceType ttsVoiceType;
            RecycleTtsVoiceAdapter adapterByType;
            boolean canAdd;
            RecycleTtsVoiceAdapter adapterByType2;
            boolean canAdd2;
            RecycleTtsVoiceAdapter adapterByType3;
            boolean canAdd3;
            Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
            int id = buttonView.getId();
            boolean z2 = true;
            if (id == C4188R.id.table_arrive_switch) {
                RecyclerView rv_table_arrive = (RecyclerView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.rv_table_arrive);
                Intrinsics.checkExpressionValueIsNotNull(rv_table_arrive, "rv_table_arrive");
                rv_table_arrive.setVisibility(z ? 0 : 8);
                ImageView add_table_arrive = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_table_arrive);
                Intrinsics.checkExpressionValueIsNotNull(add_table_arrive, "add_table_arrive");
                ImageView imageView = add_table_arrive;
                if (z) {
                    canAdd3 = RecycleSettingActivity.this.canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE);
                }
                z2 = false;
                imageView.setVisibility(z2 ? 0 : 8);
                if (!z) {
                    adapterByType3 = RecycleSettingActivity.this.getAdapterByType(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE);
                    if (adapterByType3.getPlaying() != null) {
                        RecycleSettingActivity.this.stopPlay();
                    }
                }
                ttsVoiceType = TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE;
            } else if (id == C4188R.id.table_leave_switch) {
                RecyclerView rv_table_leave = (RecyclerView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.rv_table_leave);
                Intrinsics.checkExpressionValueIsNotNull(rv_table_leave, "rv_table_leave");
                rv_table_leave.setVisibility(z ? 0 : 8);
                ImageView add_table_leave = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_table_leave);
                Intrinsics.checkExpressionValueIsNotNull(add_table_leave, "add_table_leave");
                ImageView imageView2 = add_table_leave;
                if (z) {
                    canAdd2 = RecycleSettingActivity.this.canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE);
                }
                z2 = false;
                imageView2.setVisibility(z2 ? 0 : 8);
                if (!z) {
                    adapterByType2 = RecycleSettingActivity.this.getAdapterByType(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE);
                    if (adapterByType2.getPlaying() != null) {
                        RecycleSettingActivity.this.stopPlay();
                    }
                }
                ttsVoiceType = TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE;
            } else if (id == C4188R.id.point_arrive_switch) {
                RecyclerView rv_point_arrive = (RecyclerView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.rv_point_arrive);
                Intrinsics.checkExpressionValueIsNotNull(rv_point_arrive, "rv_point_arrive");
                rv_point_arrive.setVisibility(z ? 0 : 8);
                ImageView add_point_arrive = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_point_arrive);
                Intrinsics.checkExpressionValueIsNotNull(add_point_arrive, "add_point_arrive");
                ImageView imageView3 = add_point_arrive;
                if (z) {
                    canAdd = RecycleSettingActivity.this.canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE);
                }
                z2 = false;
                imageView3.setVisibility(z2 ? 0 : 8);
                if (!z) {
                    adapterByType = RecycleSettingActivity.this.getAdapterByType(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE);
                    if (adapterByType.getPlaying() != null) {
                        RecycleSettingActivity.this.stopPlay();
                    }
                }
                ttsVoiceType = TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE;
            } else {
                ttsVoiceType = TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE;
            }
            TtsVoiceHelper.INSTANCE.setTtsType(RecycleSettingActivity.this, z ? TtsVoiceHelper.TtsVoiceOpenType.OPEN : TtsVoiceHelper.TtsVoiceOpenType.CLOSE, ttsVoiceType);
        }
    };
    private final Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> editClickListener = new Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$editClickListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
            invoke2(baseViewHolder, ttsConfigData, ttsVoiceType);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewHolder helper, final TtsVoiceHelper.TtsConfigData item, final TtsVoiceHelper.TtsVoiceType type) {
            Intrinsics.checkParameterIsNotNull(helper, "helper");
            Intrinsics.checkParameterIsNotNull(item, "item");
            Intrinsics.checkParameterIsNotNull(type, "type");
            RecycleSettingActivity.this.showCustomInputDialog(type, item.getName(), new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$editClickListener$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    RecycleTtsVoiceAdapter adapterByType;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (!Intrinsics.areEqual(item.getName(), it)) {
                        TtsVoiceHelper.INSTANCE.updateConfig(item, it, type);
                    }
                    adapterByType = RecycleSettingActivity.this.getAdapterByType(type);
                    adapterByType.notifyDataSetChanged();
                }
            });
        }
    };
    private final Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> controllerListener = new Function3<BaseViewHolder, TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$controllerListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
            invoke2(baseViewHolder, ttsConfigData, ttsVoiceType);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(BaseViewHolder helper, final TtsVoiceHelper.TtsConfigData item, TtsVoiceHelper.TtsVoiceType type) {
            final RecycleTtsVoiceAdapter adapterByType;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter2;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter3;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter4;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter5;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter6;
            Intrinsics.checkParameterIsNotNull(helper, "helper");
            Intrinsics.checkParameterIsNotNull(item, "item");
            Intrinsics.checkParameterIsNotNull(type, "type");
            adapterByType = RecycleSettingActivity.this.getAdapterByType(type);
            if (!Intrinsics.areEqual(adapterByType.getPlaying(), item)) {
                recycleTtsVoiceAdapter = RecycleSettingActivity.this.tableArriveAdapter;
                TtsVoiceHelper.TtsConfigData ttsConfigData = (TtsVoiceHelper.TtsConfigData) null;
                recycleTtsVoiceAdapter.setPlaying(ttsConfigData);
                recycleTtsVoiceAdapter2 = RecycleSettingActivity.this.tableLeaveAdapter;
                recycleTtsVoiceAdapter2.setPlaying(ttsConfigData);
                recycleTtsVoiceAdapter3 = RecycleSettingActivity.this.pointArriveAdapter;
                recycleTtsVoiceAdapter3.setPlaying(ttsConfigData);
                adapterByType.setPlaying(item);
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                recycleTtsVoiceAdapter4 = RecycleSettingActivity.this.tableArriveAdapter;
                recycleTtsVoiceAdapter4.notifyDataSetChanged();
                recycleTtsVoiceAdapter5 = RecycleSettingActivity.this.tableLeaveAdapter;
                recycleTtsVoiceAdapter5.notifyDataSetChanged();
                recycleTtsVoiceAdapter6 = RecycleSettingActivity.this.pointArriveAdapter;
                recycleTtsVoiceAdapter6.notifyDataSetChanged();
                TtsVoiceHelper.INSTANCE.playPcm(item, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$controllerListener$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                        invoke2(audioPlayEvent);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AudioTrackUtils.AudioPlayEvent data) {
                        Intrinsics.checkParameterIsNotNull(data, "data");
                        if (data == AudioTrackUtils.AudioPlayEvent.PLAYING) {
                            RecycleTtsVoiceAdapter.this.setPlaying(item);
                        } else {
                            RecycleTtsVoiceAdapter.this.setPlaying((TtsVoiceHelper.TtsConfigData) null);
                        }
                        RecycleTtsVoiceAdapter.this.notifyDataSetChanged();
                    }
                });
                return;
            }
            adapterByType.setPlaying((TtsVoiceHelper.TtsConfigData) null);
            adapterByType.notifyDataSetChanged();
            TtsVoiceHelper.INSTANCE.stopCruiseTts();
        }
    };
    private final Function2<TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit> deleteListener = new Function2<TtsVoiceHelper.TtsConfigData, TtsVoiceHelper.TtsVoiceType, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$deleteListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(TtsVoiceHelper.TtsConfigData ttsConfigData, TtsVoiceHelper.TtsVoiceType ttsVoiceType) {
            invoke2(ttsConfigData, ttsVoiceType);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TtsVoiceHelper.TtsConfigData item, TtsVoiceHelper.TtsVoiceType type) {
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter2;
            RecycleTtsVoiceAdapter recycleTtsVoiceAdapter3;
            Intrinsics.checkParameterIsNotNull(item, "item");
            Intrinsics.checkParameterIsNotNull(type, "type");
            TtsVoiceHelper.INSTANCE.deleteConfig(item, type);
            int i = RecycleSettingActivity.WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
            if (i == 1) {
                ImageView add_table_arrive = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_table_arrive);
                Intrinsics.checkExpressionValueIsNotNull(add_table_arrive, "add_table_arrive");
                add_table_arrive.setVisibility(0);
                recycleTtsVoiceAdapter = RecycleSettingActivity.this.tableArriveAdapter;
                recycleTtsVoiceAdapter.notifyDataSetChanged();
                return;
            }
            if (i == 2) {
                ImageView add_table_leave = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_table_leave);
                Intrinsics.checkExpressionValueIsNotNull(add_table_leave, "add_table_leave");
                add_table_leave.setVisibility(0);
                recycleTtsVoiceAdapter2 = RecycleSettingActivity.this.tableLeaveAdapter;
                recycleTtsVoiceAdapter2.notifyDataSetChanged();
                return;
            }
            if (i != 3) {
                return;
            }
            ImageView add_point_arrive = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_point_arrive);
            Intrinsics.checkExpressionValueIsNotNull(add_point_arrive, "add_point_arrive");
            add_point_arrive.setVisibility(0);
            recycleTtsVoiceAdapter3 = RecycleSettingActivity.this.pointArriveAdapter;
            recycleTtsVoiceAdapter3.notifyDataSetChanged();
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TtsVoiceHelper.TtsVoiceType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[TtsVoiceHelper.TtsVoiceType.values().length];
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 1;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 2;
            $EnumSwitchMapping$1[TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 3;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
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

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
    }

    public RecycleSettingActivity() {
        RecycleTtsVoiceAdapter recycleTtsVoiceAdapter = new RecycleTtsVoiceAdapter();
        recycleTtsVoiceAdapter.setType(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE);
        this.tableArriveAdapter = recycleTtsVoiceAdapter;
        RecycleTtsVoiceAdapter recycleTtsVoiceAdapter2 = new RecycleTtsVoiceAdapter();
        recycleTtsVoiceAdapter2.setType(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE);
        this.tableLeaveAdapter = recycleTtsVoiceAdapter2;
        RecycleTtsVoiceAdapter recycleTtsVoiceAdapter3 = new RecycleTtsVoiceAdapter();
        recycleTtsVoiceAdapter3.setType(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE);
        this.pointArriveAdapter = recycleTtsVoiceAdapter3;
        this.pointAdapter = new RecycleSettingActivity$pointAdapter$1(C4188R.layout.item_recycle_point);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RecycleTtsVoiceAdapter getAdapterByType(TtsVoiceHelper.TtsVoiceType type) {
        int i = WhenMappings.$EnumSwitchMapping$1[type.ordinal()];
        if (i == 1) {
            return this.tableArriveAdapter;
        }
        if (i == 2) {
            return this.tableLeaveAdapter;
        }
        if (i == 3) {
            return this.pointArriveAdapter;
        }
        return this.tableArriveAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean canAdd(TtsVoiceHelper.TtsVoiceType type) {
        return TtsVoiceHelper.INSTANCE.getTtsConfigList(type).size() < 5;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_recycle_setting;
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        Switch sort_switch = (Switch) _$_findCachedViewById(C4188R.id.sort_switch);
        Intrinsics.checkExpressionValueIsNotNull(sort_switch, "sort_switch");
        sort_switch.setChecked(Constans.INSTANCE.isOrderRecycle());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.sort_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initView$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                Constans.INSTANCE.setOrderRecycle(z);
            }
        }, 7, null);
        Switch repeat_switch = (Switch) _$_findCachedViewById(C4188R.id.repeat_switch);
        Intrinsics.checkExpressionValueIsNotNull(repeat_switch, "repeat_switch");
        repeat_switch.setChecked(Constans.INSTANCE.isRepeatLastRecycle());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.repeat_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initView$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                Constans.INSTANCE.setRepeatLastRecycle(z);
            }
        }, 7, null);
        Switch auto_complete_switch = (Switch) _$_findCachedViewById(C4188R.id.auto_complete_switch);
        Intrinsics.checkExpressionValueIsNotNull(auto_complete_switch, "auto_complete_switch");
        auto_complete_switch.setChecked(Constant.INSTANCE.isRecycleAutoComplete());
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.auto_complete_switch), null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initView$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton buttonView, boolean z) {
                Intrinsics.checkParameterIsNotNull(buttonView, "buttonView");
                Constant.INSTANCE.setRecycleAutoComplete(z);
            }
        }, 7, null);
        long j = 1000;
        ((EditText) _$_findCachedViewById(C4188R.id.complete_et)).setText(String.valueOf(Constant.INSTANCE.getRecycleCompleteTime() / j));
        EditText complete_et = (EditText) _$_findCachedViewById(C4188R.id.complete_et);
        Intrinsics.checkExpressionValueIsNotNull(complete_et, "complete_et");
        complete_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initView$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                String str2;
                String valueOf = String.valueOf(s);
                str = RecycleSettingActivity.this.TAG;
                Pdlog.m3273d(str, "complete_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "1";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.complete_et)).setText(String.valueOf(600L));
                    } else if (parseLong <= 0) {
                        parseLong = 1;
                        ((EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.complete_et)).setText(String.valueOf(1L));
                    }
                    EditText editText = (EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.complete_et);
                    EditText complete_et2 = (EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.complete_et);
                    Intrinsics.checkExpressionValueIsNotNull(complete_et2, "complete_et");
                    editText.setSelection(complete_et2.getText().length());
                    Constant.INSTANCE.setRecycleCompleteTime(parseLong * 1000);
                } catch (Exception e) {
                    str2 = RecycleSettingActivity.this.TAG;
                    Pdlog.m3274e(str2, "complete_et : " + Log.getStackTraceString(e));
                }
            }
        });
        ((EditText) _$_findCachedViewById(C4188R.id.pause_et)).setText(String.valueOf(Constant.INSTANCE.getRecyclePauseTime() / j));
        EditText pause_et = (EditText) _$_findCachedViewById(C4188R.id.pause_et);
        Intrinsics.checkExpressionValueIsNotNull(pause_et, "pause_et");
        pause_et.addTextChangedListener(new TextWatcher() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initView$$inlined$doAfterTextChanged$2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String str;
                String str2;
                String valueOf = String.valueOf(s);
                str = RecycleSettingActivity.this.TAG;
                Pdlog.m3273d(str, "pause_et : " + valueOf);
                if (valueOf.length() == 0) {
                    valueOf = "0";
                }
                try {
                    long parseLong = Long.parseLong(valueOf);
                    if (parseLong > 600) {
                        parseLong = 600;
                        ((EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.pause_et)).setText(String.valueOf(600L));
                        EditText editText = (EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.pause_et);
                        EditText pause_et2 = (EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.pause_et);
                        Intrinsics.checkExpressionValueIsNotNull(pause_et2, "pause_et");
                        editText.setSelection(pause_et2.getText().length());
                    } else if (parseLong < 5) {
                        parseLong = 5;
                    }
                    Constant.INSTANCE.setRecyclePauseTime(parseLong * 1000);
                } catch (Exception e) {
                    str2 = RecycleSettingActivity.this.TAG;
                    Pdlog.m3274e(str2, "pause_et : " + Log.getStackTraceString(e));
                }
            }
        });
        initVoice();
        LinearLayout back_ll = (LinearLayout) _$_findCachedViewById(C4188R.id.back_ll);
        Intrinsics.checkExpressionValueIsNotNull(back_ll, "back_ll");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(back_ll, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initView$6
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                TtsVoiceHelper.INSTANCE.stopCruiseTts();
                RecycleSettingActivity.this.finish();
            }
        }, 3, null);
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        bindPresenter();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        unbindPresenter();
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    private final void bindPresenter() {
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
    }

    private final void unbindPresenter() {
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
    }

    private final void initVoice() {
        if (LanguageUtils.INSTANCE.isNoSupportTts()) {
            CardView table_arrive_cv = (CardView) _$_findCachedViewById(C4188R.id.table_arrive_cv);
            Intrinsics.checkExpressionValueIsNotNull(table_arrive_cv, "table_arrive_cv");
            ViewExtKt.gone(table_arrive_cv);
            CardView table_leave_cv = (CardView) _$_findCachedViewById(C4188R.id.table_leave_cv);
            Intrinsics.checkExpressionValueIsNotNull(table_leave_cv, "table_leave_cv");
            ViewExtKt.gone(table_leave_cv);
            CardView point_leave_cv = (CardView) _$_findCachedViewById(C4188R.id.point_leave_cv);
            Intrinsics.checkExpressionValueIsNotNull(point_leave_cv, "point_leave_cv");
            ViewExtKt.gone(point_leave_cv);
        }
        Switch table_arrive_switch = (Switch) _$_findCachedViewById(C4188R.id.table_arrive_switch);
        Intrinsics.checkExpressionValueIsNotNull(table_arrive_switch, "table_arrive_switch");
        RecycleSettingActivity recycleSettingActivity = this;
        table_arrive_switch.setChecked(TtsVoiceHelper.INSTANCE.checkTtsOpenType(recycleSettingActivity, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        Switch table_leave_switch = (Switch) _$_findCachedViewById(C4188R.id.table_leave_switch);
        Intrinsics.checkExpressionValueIsNotNull(table_leave_switch, "table_leave_switch");
        table_leave_switch.setChecked(TtsVoiceHelper.INSTANCE.checkTtsOpenType(recycleSettingActivity, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        Switch point_arrive_switch = (Switch) _$_findCachedViewById(C4188R.id.point_arrive_switch);
        Intrinsics.checkExpressionValueIsNotNull(point_arrive_switch, "point_arrive_switch");
        point_arrive_switch.setChecked(TtsVoiceHelper.INSTANCE.checkTtsOpenType(recycleSettingActivity, TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE) == TtsVoiceHelper.TtsVoiceOpenType.OPEN);
        RecyclerView rv_table_arrive = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_table_arrive);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_arrive, "rv_table_arrive");
        RecyclerView recyclerView = rv_table_arrive;
        Switch table_arrive_switch2 = (Switch) _$_findCachedViewById(C4188R.id.table_arrive_switch);
        Intrinsics.checkExpressionValueIsNotNull(table_arrive_switch2, "table_arrive_switch");
        recyclerView.setVisibility(table_arrive_switch2.isChecked() ? 0 : 8);
        ImageView add_table_arrive = (ImageView) _$_findCachedViewById(C4188R.id.add_table_arrive);
        Intrinsics.checkExpressionValueIsNotNull(add_table_arrive, "add_table_arrive");
        ImageView imageView = add_table_arrive;
        Switch table_arrive_switch3 = (Switch) _$_findCachedViewById(C4188R.id.table_arrive_switch);
        Intrinsics.checkExpressionValueIsNotNull(table_arrive_switch3, "table_arrive_switch");
        imageView.setVisibility(table_arrive_switch3.isChecked() && canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE) ? 0 : 8);
        RecyclerView rv_table_leave = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_table_leave);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_leave, "rv_table_leave");
        RecyclerView recyclerView2 = rv_table_leave;
        Switch table_leave_switch2 = (Switch) _$_findCachedViewById(C4188R.id.table_leave_switch);
        Intrinsics.checkExpressionValueIsNotNull(table_leave_switch2, "table_leave_switch");
        recyclerView2.setVisibility(table_leave_switch2.isChecked() ? 0 : 8);
        ImageView add_table_leave = (ImageView) _$_findCachedViewById(C4188R.id.add_table_leave);
        Intrinsics.checkExpressionValueIsNotNull(add_table_leave, "add_table_leave");
        ImageView imageView2 = add_table_leave;
        Switch table_leave_switch3 = (Switch) _$_findCachedViewById(C4188R.id.table_leave_switch);
        Intrinsics.checkExpressionValueIsNotNull(table_leave_switch3, "table_leave_switch");
        imageView2.setVisibility(table_leave_switch3.isChecked() && canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE) ? 0 : 8);
        RecyclerView rv_point_arrive = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_point_arrive);
        Intrinsics.checkExpressionValueIsNotNull(rv_point_arrive, "rv_point_arrive");
        RecyclerView recyclerView3 = rv_point_arrive;
        Switch point_arrive_switch2 = (Switch) _$_findCachedViewById(C4188R.id.point_arrive_switch);
        Intrinsics.checkExpressionValueIsNotNull(point_arrive_switch2, "point_arrive_switch");
        recyclerView3.setVisibility(point_arrive_switch2.isChecked() ? 0 : 8);
        ImageView add_point_arrive = (ImageView) _$_findCachedViewById(C4188R.id.add_point_arrive);
        Intrinsics.checkExpressionValueIsNotNull(add_point_arrive, "add_point_arrive");
        ImageView imageView3 = add_point_arrive;
        Switch point_arrive_switch3 = (Switch) _$_findCachedViewById(C4188R.id.point_arrive_switch);
        Intrinsics.checkExpressionValueIsNotNull(point_arrive_switch3, "point_arrive_switch");
        imageView3.setVisibility(point_arrive_switch3.isChecked() && canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE) ? 0 : 8);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.table_arrive_switch), null, 0, false, this.voiceSwitchChanged, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.table_leave_switch), null, 0, false, this.voiceSwitchChanged, 7, null);
        VoiceSwitchChangeListenerKt.onVoiceSwitchChanged$default((Switch) _$_findCachedViewById(C4188R.id.point_arrive_switch), null, 0, false, this.voiceSwitchChanged, 7, null);
        ImageView add_table_arrive2 = (ImageView) _$_findCachedViewById(C4188R.id.add_table_arrive);
        Intrinsics.checkExpressionValueIsNotNull(add_table_arrive2, "add_table_arrive");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(add_table_arrive2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$1
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                RecycleSettingActivity.this.clearEditFocus();
                RecycleSettingActivity.showCustomInputDialog$default(RecycleSettingActivity.this, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE, null, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it2) {
                        boolean canAdd;
                        RecycleTtsVoiceAdapter recycleTtsVoiceAdapter;
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        ImageView add_table_arrive3 = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_table_arrive);
                        Intrinsics.checkExpressionValueIsNotNull(add_table_arrive3, "add_table_arrive");
                        ImageView imageView4 = add_table_arrive3;
                        canAdd = RecycleSettingActivity.this.canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE);
                        imageView4.setVisibility(canAdd ? 0 : 8);
                        recycleTtsVoiceAdapter = RecycleSettingActivity.this.tableArriveAdapter;
                        recycleTtsVoiceAdapter.notifyDataSetChanged();
                    }
                }, 2, null);
            }
        }, 3, null);
        ImageView add_table_leave2 = (ImageView) _$_findCachedViewById(C4188R.id.add_table_leave);
        Intrinsics.checkExpressionValueIsNotNull(add_table_leave2, "add_table_leave");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(add_table_leave2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$2
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                RecycleSettingActivity.this.clearEditFocus();
                RecycleSettingActivity.showCustomInputDialog$default(RecycleSettingActivity.this, TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE, null, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$2.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it2) {
                        boolean canAdd;
                        RecycleTtsVoiceAdapter recycleTtsVoiceAdapter;
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        ImageView add_table_leave3 = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_table_leave);
                        Intrinsics.checkExpressionValueIsNotNull(add_table_leave3, "add_table_leave");
                        ImageView imageView4 = add_table_leave3;
                        canAdd = RecycleSettingActivity.this.canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE);
                        imageView4.setVisibility(canAdd ? 0 : 8);
                        recycleTtsVoiceAdapter = RecycleSettingActivity.this.tableLeaveAdapter;
                        recycleTtsVoiceAdapter.notifyDataSetChanged();
                    }
                }, 2, null);
            }
        }, 3, null);
        ImageView add_point_arrive2 = (ImageView) _$_findCachedViewById(C4188R.id.add_point_arrive);
        Intrinsics.checkExpressionValueIsNotNull(add_point_arrive2, "add_point_arrive");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(add_point_arrive2, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$3
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                RecycleSettingActivity.this.clearEditFocus();
                RecycleSettingActivity.showCustomInputDialog$default(RecycleSettingActivity.this, TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE, null, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$3.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(String it2) {
                        boolean canAdd;
                        RecycleTtsVoiceAdapter recycleTtsVoiceAdapter;
                        Intrinsics.checkParameterIsNotNull(it2, "it");
                        ImageView add_point_arrive3 = (ImageView) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.add_point_arrive);
                        Intrinsics.checkExpressionValueIsNotNull(add_point_arrive3, "add_point_arrive");
                        ImageView imageView4 = add_point_arrive3;
                        canAdd = RecycleSettingActivity.this.canAdd(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE);
                        imageView4.setVisibility(canAdd ? 0 : 8);
                        recycleTtsVoiceAdapter = RecycleSettingActivity.this.pointArriveAdapter;
                        recycleTtsVoiceAdapter.notifyDataSetChanged();
                    }
                }, 2, null);
            }
        }, 3, null);
        ImageView iv_pause_reset = (ImageView) _$_findCachedViewById(C4188R.id.iv_pause_reset);
        Intrinsics.checkExpressionValueIsNotNull(iv_pause_reset, "iv_pause_reset");
        SingleVoiceClickListenerKt.onSingleVoiceClick$default(iv_pause_reset, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.recycle.RecycleSettingActivity$initVoice$4
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
                Intrinsics.checkParameterIsNotNull(it, "it");
                ((EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.pause_et)).setText(String.valueOf(10));
                EditText editText = (EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.pause_et);
                EditText pause_et = (EditText) RecycleSettingActivity.this._$_findCachedViewById(C4188R.id.pause_et);
                Intrinsics.checkExpressionValueIsNotNull(pause_et, "pause_et");
                editText.setSelection(pause_et.getText().length());
                Constant.INSTANCE.setRecyclePauseTime(10000L);
            }
        }, 3, null);
        RecyclerView rv_table_arrive2 = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_table_arrive);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_arrive2, "rv_table_arrive");
        rv_table_arrive2.setLayoutManager(new LinearLayoutManager(recycleSettingActivity));
        RecyclerView rv_table_leave2 = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_table_leave);
        Intrinsics.checkExpressionValueIsNotNull(rv_table_leave2, "rv_table_leave");
        rv_table_leave2.setLayoutManager(new LinearLayoutManager(recycleSettingActivity));
        RecyclerView rv_point_arrive2 = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_point_arrive);
        Intrinsics.checkExpressionValueIsNotNull(rv_point_arrive2, "rv_point_arrive");
        rv_point_arrive2.setLayoutManager(new LinearLayoutManager(recycleSettingActivity));
        this.tableArriveAdapter.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_ARRIVE));
        this.tableLeaveAdapter.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.RECYCLE_TABLE_LEAVE));
        this.pointArriveAdapter.setNewData(TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.RECYCLE_POINT_ARRIVE));
        this.tableArriveAdapter.setEditClickListener(this.editClickListener);
        this.tableLeaveAdapter.setEditClickListener(this.editClickListener);
        this.pointArriveAdapter.setEditClickListener(this.editClickListener);
        this.tableArriveAdapter.setControllerClickListener(this.controllerListener);
        this.tableLeaveAdapter.setControllerClickListener(this.controllerListener);
        this.pointArriveAdapter.setControllerClickListener(this.controllerListener);
        this.tableArriveAdapter.setDeleteListener(this.deleteListener);
        this.tableLeaveAdapter.setDeleteListener(this.deleteListener);
        this.pointArriveAdapter.setDeleteListener(this.deleteListener);
        this.tableArriveAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.rv_table_arrive));
        this.tableLeaveAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.rv_table_leave));
        this.pointArriveAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.rv_point_arrive));
        String dashWashChosen = RobotMapManager.INSTANCE.getDashWashChosen();
        if (dashWashChosen == null) {
            dashWashChosen = "";
        }
        if (dashWashChosen.length() == 0) {
            TextView title_points = (TextView) _$_findCachedViewById(C4188R.id.title_points);
            Intrinsics.checkExpressionValueIsNotNull(title_points, "title_points");
            title_points.setVisibility(8);
            CardView container_points = (CardView) _$_findCachedViewById(C4188R.id.container_points);
            Intrinsics.checkExpressionValueIsNotNull(container_points, "container_points");
            container_points.setVisibility(8);
            return;
        }
        TextView title_points2 = (TextView) _$_findCachedViewById(C4188R.id.title_points);
        Intrinsics.checkExpressionValueIsNotNull(title_points2, "title_points");
        title_points2.setVisibility(0);
        CardView container_points2 = (CardView) _$_findCachedViewById(C4188R.id.container_points);
        Intrinsics.checkExpressionValueIsNotNull(container_points2, "container_points");
        container_points2.setVisibility(0);
        RecyclerView rv_recycle_point = (RecyclerView) _$_findCachedViewById(C4188R.id.rv_recycle_point);
        Intrinsics.checkExpressionValueIsNotNull(rv_recycle_point, "rv_recycle_point");
        rv_recycle_point.setLayoutManager(new LinearLayoutManager(recycleSettingActivity));
        this.pointAdapter.setSelected(dashWashChosen);
        this.pointAdapter.setNewData(RobotMapManager.INSTANCE.getRecyclePoints());
        this.pointAdapter.bindToRecyclerView((RecyclerView) _$_findCachedViewById(C4188R.id.rv_recycle_point));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearEditFocus() {
        ((EditText) _$_findCachedViewById(C4188R.id.complete_et)).clearFocus();
        ((EditText) _$_findCachedViewById(C4188R.id.pause_et)).clearFocus();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void showCustomInputDialog$default(RecycleSettingActivity recycleSettingActivity, TtsVoiceHelper.TtsVoiceType ttsVoiceType, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "";
        }
        recycleSettingActivity.showCustomInputDialog(ttsVoiceType, str, function1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCustomInputDialog(TtsVoiceHelper.TtsVoiceType type, String content, Function1<? super String, Unit> onContentChange) {
        stopPlay();
        CustomTtsVoiceInputDialog customTtsVoiceInputDialog = new CustomTtsVoiceInputDialog();
        customTtsVoiceInputDialog.setVoiceType(type);
        customTtsVoiceInputDialog.setContent(content);
        customTtsVoiceInputDialog.show(getSupportFragmentManager(), "voice_custom");
        customTtsVoiceInputDialog.setOnContentChange(onContentChange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPlay() {
        TtsVoiceHelper.INSTANCE.stopCruiseTts();
        for (RecycleTtsVoiceAdapter recycleTtsVoiceAdapter : new RecycleTtsVoiceAdapter[]{this.tableArriveAdapter, this.tableLeaveAdapter, this.pointArriveAdapter}) {
            if (recycleTtsVoiceAdapter.getPlaying() != null) {
                recycleTtsVoiceAdapter.setPlaying((TtsVoiceHelper.TtsConfigData) null);
                recycleTtsVoiceAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
            jumpAndFinish(new Intent(this, (Class<?>) RobotChargingActivity.class));
        }
    }
}
