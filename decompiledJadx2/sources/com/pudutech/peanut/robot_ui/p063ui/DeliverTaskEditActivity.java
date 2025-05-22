package com.pudutech.peanut.robot_ui.p063ui;

import android.R;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.airbnb.lottie.LottieAnimationView;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.kotlinmvp.mvp_base.BasePresenterInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveManager;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveSortType;
import com.pudutech.mirsdkwrap.lib.robot.BatteryInfoManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.peanut.presenter.PresenterHolder;
import com.pudutech.peanut.presenter.delivery_task.DeliveryModel;
import com.pudutech.peanut.presenter.delivery_task.TrayModel;
import com.pudutech.peanut.presenter.general_task.DispatchKeyContract;
import com.pudutech.peanut.presenter.general_task.DispatchKeyPresenter;
import com.pudutech.peanut.presenter.information_system_task.InformationSystemContract;
import com.pudutech.peanut.presenter.information_system_task.InformationSystemPresenter;
import com.pudutech.peanut.presenter.performance.MovePerformance;
import com.pudutech.peanut.robot_ui.C5508R;
import com.pudutech.peanut.robot_ui.RobotContext;
import com.pudutech.peanut.robot_ui.config.Constans;
import com.pudutech.peanut.robot_ui.extend.ViewExtKt;
import com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener;
import com.pudutech.peanut.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.peanut.robot_ui.manager.LightPlayManager;
import com.pudutech.peanut.robot_ui.manager.TableTaskManager;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.DeliverySettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.SettingActivity;
import com.pudutech.peanut.robot_ui.module.setting.p062ui.dialog.ShowTipMsgDialog;
import com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity;
import com.pudutech.peanut.robot_ui.p063ui.dialog.HomeSettingDialog;
import com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener;
import com.pudutech.peanut.robot_ui.p063ui.dialog.ReturnTipDialog;
import com.pudutech.peanut.robot_ui.p063ui.fragment.ISelectTableFragment;
import com.pudutech.peanut.robot_ui.p063ui.fragment.SelectTableFragment;
import com.pudutech.peanut.robot_ui.p063ui.helper.BeeperCallHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.LeaseHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.PalletCountHelper;
import com.pudutech.peanut.robot_ui.p063ui.helper.VoicePlayTasks;
import com.pudutech.peanut.robot_ui.p063ui.view.MyStatusBarLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.RobotDeliverTaskLayout;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.FaceVideoView;
import com.pudutech.peanut.robot_ui.p063ui.view.videoface.SceneAnimationResources;
import com.pudutech.peanut.robot_ui.util.FragmentUtils;
import com.pudutech.peanut.robot_ui.util.NavigationBarUtil;
import com.pudutech.peanut.robot_ui.util.NetStatusUtil;
import com.pudutech.peanut.robot_ui.util.ToastUtils;
import com.pudutech.peanut.robot_ui.viewmodel.HeyPuduWakeViewModel;
import com.pudutech.robot.module.voice.data.PlayEvent;
import com.pudutech.voiceinteraction.component.p069ui.VoiceInteractionDialog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: DeliverTaskEditActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000ñ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b%*\u0001C\u0018\u0000 ¬\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0002¬\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010R\u001a\u00020@2\u0006\u0010S\u001a\u00020\tH\u0002J\b\u0010T\u001a\u00020@H\u0002J\u0012\u0010U\u001a\u00020\u001b2\b\u0010V\u001a\u0004\u0018\u00010WH\u0016J\b\u0010X\u001a\u00020YH\u0002J\u0018\u0010Z\u001a\u0012\u0012\u0004\u0012\u00020\\0[j\b\u0012\u0004\u0012\u00020\\`]H\u0002J\b\u0010^\u001a\u00020@H\u0002J\b\u0010_\u001a\u00020@H\u0002J\b\u0010`\u001a\u00020@H\u0002J\b\u0010a\u001a\u00020@H\u0002J\b\u0010b\u001a\u00020\u001bH\u0002J\b\u0010c\u001a\u00020\u001bH\u0002J\b\u0010d\u001a\u00020\u001bH\u0002J\u0012\u0010e\u001a\u00020@2\b\u0010f\u001a\u0004\u0018\u00010gH\u0002J\u0012\u0010h\u001a\u00020@2\b\u0010f\u001a\u0004\u0018\u00010gH\u0016J\b\u0010i\u001a\u00020@H\u0002J1\u0010j\u001a\u00020@2\u0006\u0010k\u001a\u00020\u00062\b\u0010l\u001a\u0004\u0018\u00010m2\u0006\u0010n\u001a\u00020\u001b2\b\u0010o\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0002\u0010pJ(\u0010q\u001a\u00020@2\u0016\u0010r\u001a\u0012\u0012\u0004\u0012\u00020\\0[j\b\u0012\u0004\u0012\u00020\\`]2\u0006\u0010s\u001a\u00020\tH\u0002J\u0012\u0010t\u001a\u00020@2\b\u0010u\u001a\u0004\u0018\u00010vH\u0014J\b\u0010w\u001a\u00020@H\u0014J\u0018\u0010x\u001a\u00020\u001b2\u0006\u0010y\u001a\u00020z2\u0006\u0010{\u001a\u00020\tH\u0016J\u0010\u0010|\u001a\u00020@2\u0006\u0010}\u001a\u00020\u0006H\u0002J\u0012\u0010~\u001a\u00020@2\b\u0010f\u001a\u0004\u0018\u00010gH\u0014J\b\u0010\u007f\u001a\u00020@H\u0014J5\u0010\u0080\u0001\u001a\u00020\u001b2\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0017\u0010\u0083\u0001\u001a\u0012\u0012\u0004\u0012\u00020\\0[j\b\u0012\u0004\u0012\u00020\\`]2\u0007\u0010\u0084\u0001\u001a\u00020\u001bH\u0016J6\u0010\u0085\u0001\u001a\u00020\u001b2\u0019\u0010\u0086\u0001\u001a\u0014\u0012\u0005\u0012\u00030\u0087\u00010[j\t\u0012\u0005\u0012\u00030\u0087\u0001`]2\u0007\u0010}\u001a\u00030\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u0006H\u0016J\t\u0010\u008a\u0001\u001a\u00020@H\u0014J\t\u0010\u008b\u0001\u001a\u00020@H\u0014J\t\u0010\u008c\u0001\u001a\u00020@H\u0014J\u0012\u0010\u008d\u0001\u001a\u00020@2\u0007\u0010\u008e\u0001\u001a\u00020\u001bH\u0016J\t\u0010\u008f\u0001\u001a\u00020@H\u0002J\t\u0010\u0090\u0001\u001a\u00020@H\u0002J\t\u0010\u0091\u0001\u001a\u00020@H\u0002J\t\u0010\u0092\u0001\u001a\u00020@H\u0002J\t\u0010\u0093\u0001\u001a\u00020@H\u0002J\t\u0010\u0094\u0001\u001a\u00020@H\u0002J\t\u0010\u0095\u0001\u001a\u00020@H\u0002J\t\u0010\u0096\u0001\u001a\u00020@H\u0002J\u001e\u0010\u0097\u0001\u001a\u0004\u0018\u00010/2\u0006\u0010{\u001a\u00020\t2\t\b\u0002\u0010\u0098\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u0099\u0001\u001a\u00020@H\u0002J\u0012\u0010\u009a\u0001\u001a\u00020@2\u0007\u0010\u009b\u0001\u001a\u00020\u001bH\u0002J\u0011\u0010\u009c\u0001\u001a\u00020@2\u0006\u0010k\u001a\u00020\u0006H\u0002J\t\u0010\u009d\u0001\u001a\u00020@H\u0002J\t\u0010\u009e\u0001\u001a\u00020@H\u0002J\t\u0010\u009f\u0001\u001a\u00020@H\u0002J\t\u0010 \u0001\u001a\u00020@H\u0002J\t\u0010¡\u0001\u001a\u00020@H\u0002J\u0013\u0010¢\u0001\u001a\u00020@2\b\b\u0002\u0010s\u001a\u00020\tH\u0002J\t\u0010£\u0001\u001a\u00020@H\u0002J\t\u0010¤\u0001\u001a\u00020@H\u0002J\u0012\u0010¥\u0001\u001a\u00020@2\u0007\u0010\u009b\u0001\u001a\u00020\u001bH\u0002J\t\u0010¦\u0001\u001a\u00020@H\u0002J\t\u0010§\u0001\u001a\u00020@H\u0002J(\u0010¨\u0001\u001a\u00020@2\u0007\u0010©\u0001\u001a\u00020\u001b2\t\b\u0002\u0010ª\u0001\u001a\u00020\u001b2\t\b\u0002\u0010«\u0001\u001a\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0017\u001a\u0004\b \u0010!R\u000e\u0010#\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00100\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\u0017\u001a\u0004\b2\u00103R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00108\u001a\u000209¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010=\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0006\u0012\u0004\u0018\u00010@0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010A\u001a\u0010\u0012\u0004\u0012\u00020?\u0012\u0006\u0012\u0004\u0018\u00010@0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u00020CX\u0082\u0004¢\u0006\u0004\n\u0002\u0010DR\u0014\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001b0FX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010G\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(K\u0012\u0013\u0012\u00110\t¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u001b0HX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010M\u001a\u001d\u0012\u0013\u0012\u00110\u001b¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020@0>X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u00ad\u0001"}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/DeliverTaskEditActivity;", "Lcom/pudutech/peanut/robot_ui/ui/base/SleepBaseActivity;", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$ViewInterface;", "Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract$ViewInterface;", "()V", "PLAY_VOICE", "", "TAB_SHOW", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_FEATURE_AIVOICE", "TYPE_FEATURE_DIALOG", "TYPE_FEATURE_INPUT", "TYPE_FEATURE_NOMAL", "TYPE_FEATURE_STANDBY", "beeperCallHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/BeeperCallHelper;", "dispatchKeyPresenter", "Lcom/pudutech/peanut/presenter/general_task/DispatchKeyPresenter;", "getDispatchKeyPresenter", "()Lcom/pudutech/peanut/presenter/general_task/DispatchKeyPresenter;", "dispatchKeyPresenter$delegate", "Lkotlin/Lazy;", "faceAnimationView", "Lcom/pudutech/peanut/robot_ui/ui/view/videoface/FaceVideoView;", "fillInStatusInit", "", "homeSettingDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/HomeSettingDialog;", "informationSystemPresenter", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemPresenter;", "getInformationSystemPresenter", "()Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemPresenter;", "informationSystemPresenter$delegate", "isFirstStart", "isInStandby", "isJumpToMusicAc", "isPlayClickGoAlertVoice", "isPlayInputOneTableVoice", "isPlayInputOneTableVoiceFinish", "isPlayLowPowerVoice", "isRelease", "isShowLowPowerDialog", "leaseHelper", "Lcom/pudutech/peanut/robot_ui/ui/helper/LeaseHelper;", "lowerPowerDialog", "Lcom/pudutech/peanut/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "mHeyPUduWakeVm", "Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "getMHeyPUduWakeVm", "()Lcom/pudutech/peanut/robot_ui/viewmodel/HeyPuduWakeViewModel;", "mHeyPUduWakeVm$delegate", "mReturnDialog", "Lcom/pudutech/peanut/robot_ui/ui/dialog/ReturnTipDialog;", "mRobotType", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "needSelectTableDialog", "onAIVoiceDialogDismiss", "Lkotlin/Function1;", "Lcom/pudutech/voiceinteraction/component/ui/VoiceInteractionDialog;", "", "onAIVoiceDialogShow", "onHomeSettingDialogClickListener", "com/pudutech/peanut/robot_ui/ui/DeliverTaskEditActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/peanut/robot_ui/ui/DeliverTaskEditActivity$onHomeSettingDialogClickListener$1;", "onMissionStartListener", "Lkotlin/Function0;", "onTaskInputListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "tray", "destination", "onTouchSensorAnimationListener", "isShow", "selectTableFragment", "Lcom/pudutech/peanut/robot_ui/ui/fragment/ISelectTableFragment;", "showStatus", "addSelectTask", "task", "bindPresenter", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "getMovePerformance", "Lcom/pudutech/peanut/presenter/performance/MovePerformance;", "getSelectTask", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "initAiVoice", "initAllData", "initTablesInput", "initView", "isBirthdayMode", "isCanSaveDeliveryHistory", "isModifyMode", "jump", "intent", "Landroid/content/Intent;", "jumpAndFinish", "jumpToStartTask", "notifyBatteryInfo", "state", "model", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "isCharging", "i", "(ILcom/pudutech/mirsdk/hardware/serialize/ChargeState;ZLjava/lang/Integer;)V", "notifyOrderStart", "allTask", "employees", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDispatchKey", TransferTable.COLUMN_KEY, "Lcom/pudutech/peanut/presenter/general_task/DispatchKeyContract$KEY;", NotificationCompat.CATEGORY_MESSAGE, "onFeatureChange", "type", "onNewIntent", "onPause", "onReceiveDeliveryTask", "sortType", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveSortType;", "allTrays", "executeTask", "onReceiveOrderInfo", "info", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lcom/pudutech/peanut/presenter/information_system_task/InformationSystemContract$OrderInfoType;", "trayIndex", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "parserIntent", "playClickGoAlertVoice", "playEntryVoiceIfNeed", "release", "reset", "setSelectTableFragment", "setTaskListLayout", "settingInfoClickListener", "showDialog", "needCloseSensorFace", "showLowerNotify", "showRecycleTaskLayout", "boolean", "showReturnDialog", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "showStatusTip", "startDelayVoice", "startDeliverTask", "stopDelayVoice", "stopStandby", "switchBirthdayTheme", "unBindPresenter", "updateModeUi", "updateStartBtnLayout", "isModify", "isShowHistory", "isDelivery", "Companion", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeliverTaskEditActivity extends SleepBaseActivity implements InformationSystemContract.ViewInterface, DispatchKeyContract.ViewInterface {
    public static final int BIRTHDAY_MODE = 2;
    public static final int BIRTHDAY_MODIFY_MODE = 3;
    public static final int DIRECT_MODE = 4;
    public static final int DIRECT_MODIFY_MODE = 5;
    public static final String MODE_TYPE = "MODE_TYPE";
    public static final int MODIFY_MODE = 1;
    public static final int NEW_MODE = 0;
    public static final int RECYCLING_PLATE_MODE = 6;
    public static final int RECYCLING_PLATE_MODIFY_MODE = 7;
    public static final String SHOW_STATUS_KEY = "SHOW_STATUS_KEY";
    public static final int SPECIAL_MODE = 8;
    public static final int SPECIAL_MODIFY_MODE = 9;
    public static final int STATUS_CALL_TIMEOUT = 1;
    public static final int STATUS_NO_DINING_OUTLET = 2;
    public static final int STATUS_NO_RECYCLE_TABLE = 4;
    public static final int STATUS_NO_WASH_ROOM = 3;
    private static int currentModeType;
    private static boolean isCanFillIn;
    private static int isChangeMap;
    private final int TYPE_FEATURE_NOMAL;
    private HashMap _$_findViewCache;
    private FaceVideoView faceAnimationView;
    private boolean fillInStatusInit;
    private HomeSettingDialog homeSettingDialog;
    private boolean isInStandby;
    private boolean isJumpToMusicAc;
    private boolean isPlayClickGoAlertVoice;
    private boolean isPlayInputOneTableVoice;
    private boolean isPlayInputOneTableVoiceFinish;
    private boolean isPlayLowPowerVoice;
    private boolean isRelease;
    private boolean isShowLowPowerDialog;
    private ShowTipMsgDialog lowerPowerDialog;
    private ReturnTipDialog mReturnDialog;
    private int mRobotType;
    private ShowTipMsgDialog needSelectTableDialog;
    private ISelectTableFragment selectTableFragment;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isFirstBoot = true;
    private String TAG = getClass().getSimpleName();
    private final int PLAY_VOICE = 100;
    private final int TAB_SHOW = 101;
    private boolean isFirstStart = true;

    /* renamed from: dispatchKeyPresenter$delegate, reason: from kotlin metadata */
    private final Lazy dispatchKeyPresenter = LazyKt.lazy(new Function0<DispatchKeyPresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$dispatchKeyPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DispatchKeyPresenter invoke() {
            DispatchKeyPresenter dispatchKeyPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DispatchKeyPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(DispatchKeyPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                dispatchKeyPresenter = new DispatchKeyPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(DispatchKeyPresenter.class).toString(), dispatchKeyPresenter);
            } else {
                if (!(basePresenterInterface instanceof DispatchKeyPresenter)) {
                    basePresenterInterface = null;
                }
                dispatchKeyPresenter = (DispatchKeyPresenter) basePresenterInterface;
            }
            if (dispatchKeyPresenter == null) {
                Intrinsics.throwNpe();
            }
            return dispatchKeyPresenter;
        }
    });

    /* renamed from: informationSystemPresenter$delegate, reason: from kotlin metadata */
    private final Lazy informationSystemPresenter = LazyKt.lazy(new Function0<InformationSystemPresenter>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$informationSystemPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final InformationSystemPresenter invoke() {
            InformationSystemPresenter informationSystemPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(InformationSystemPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(InformationSystemPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                informationSystemPresenter = new InformationSystemPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(InformationSystemPresenter.class).toString(), informationSystemPresenter);
            } else {
                if (!(basePresenterInterface instanceof InformationSystemPresenter)) {
                    basePresenterInterface = null;
                }
                informationSystemPresenter = (InformationSystemPresenter) basePresenterInterface;
            }
            if (informationSystemPresenter == null) {
                Intrinsics.throwNpe();
            }
            return informationSystemPresenter;
        }
    });
    private final LeaseHelper leaseHelper = new LeaseHelper();
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();
    private int showStatus = -1;

    /* renamed from: mHeyPUduWakeVm$delegate, reason: from kotlin metadata */
    private final Lazy mHeyPUduWakeVm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(HeyPuduWakeViewModel.class), new Function0<ViewModelStore>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = ComponentActivity.this.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$$special$$inlined$viewModels$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            ViewModelProvider.Factory defaultViewModelProviderFactory = ComponentActivity.this.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            String str;
            boolean z;
            boolean z2;
            int unused;
            int i2 = message.what;
            i = DeliverTaskEditActivity.this.PLAY_VOICE;
            if (i2 == i) {
                str = DeliverTaskEditActivity.this.TAG;
                Pdlog.m3273d(str, "PLAY_VOICE");
                z = DeliverTaskEditActivity.this.isRelease;
                if (!z) {
                    z2 = DeliverTaskEditActivity.this.isPlayClickGoAlertVoice;
                    if (!z2) {
                        DeliverTaskEditActivity.this.playClickGoAlertVoice();
                    }
                }
                return true;
            }
            int i3 = message.what;
            unused = DeliverTaskEditActivity.this.TAB_SHOW;
            return true;
        }
    });
    private final DeliverTaskEditActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new OnHomeSettingDialogClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onHomeSettingDialogClickListener$1
        @Override // com.pudutech.peanut.robot_ui.p063ui.dialog.OnHomeSettingDialogClickListener
        public void onFunClick(HomeSettingDialog.FunctionType type, Intent intent) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            if (type == HomeSettingDialog.FunctionType.RECYCLE_PLATE_MODE && DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 6) {
                return;
            }
            if (type == HomeSettingDialog.FunctionType.DIRECT_MODE && DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 4) {
                return;
            }
            if (type == HomeSettingDialog.FunctionType.BIRTHDAY_MODE && DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 2) {
                return;
            }
            if (type == HomeSettingDialog.FunctionType.DELIVERY_MODE && DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 0) {
                return;
            }
            if (type == HomeSettingDialog.FunctionType.MUSIC_MODE) {
                DeliverTaskEditActivity.this.isJumpToMusicAc = true;
                DeliverTaskEditActivity.this.release();
            } else if (intent != null) {
                DeliverTaskEditActivity.this.jump(intent);
            }
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogDismiss = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onAIVoiceDialogDismiss$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionDialog voiceInteractionDialog) {
            invoke2(voiceInteractionDialog);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(VoiceInteractionDialog it) {
            String str;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "onDialogDismiss ");
            DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
            i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
            deliverTaskEditActivity.onFeatureChange(i);
            DeliverTaskEditActivity.this.startDelayVoice();
        }
    };
    private final Function1<VoiceInteractionDialog, Unit> onAIVoiceDialogShow = new Function1<VoiceInteractionDialog, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onAIVoiceDialogShow$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(VoiceInteractionDialog voiceInteractionDialog) {
            invoke2(voiceInteractionDialog);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(VoiceInteractionDialog it) {
            String str;
            int i;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "onDialogShow ");
            DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
            i = deliverTaskEditActivity.TYPE_FEATURE_AIVOICE;
            deliverTaskEditActivity.onFeatureChange(i);
            DeliverTaskEditActivity.this.stopDelayVoice();
        }
    };
    private final int TYPE_FEATURE_INPUT = 1;
    private final int TYPE_FEATURE_AIVOICE = 2;
    private final int TYPE_FEATURE_STANDBY = 3;
    private final int TYPE_FEATURE_DIALOG = 4;
    private final Function1<Boolean, Unit> onTouchSensorAnimationListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onTouchSensorAnimationListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            String str;
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "onTouchSensorAnimationListener " + z);
        }
    };
    private final Function2<Integer, String, Boolean> onTaskInputListener = new Function2<Integer, String, Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onTaskInputListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Boolean invoke(Integer num, String str) {
            return Boolean.valueOf(invoke(num.intValue(), str));
        }

        public final boolean invoke(int i, String destination) {
            String str;
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, " onTaskInputListener : tray = " + i + "; destination = " + destination + "; ");
            if (((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).selectTray(i - 1)) {
                ((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setTaskName(destination, false);
            } else {
                Context context = RobotContext.INSTANCE.getContext();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = DeliverTaskEditActivity.this.getString(C5508R.string.pdStr2_38);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_38)");
                Object[] objArr = {Integer.valueOf(i)};
                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                ToastUtils.show(context, format, new Object[0]);
            }
            return true;
        }
    };
    private final Function0<Boolean> onMissionStartListener = new Function0<Boolean>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onMissionStartListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Boolean invoke() {
            return Boolean.valueOf(invoke2());
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final boolean invoke2() {
            String str;
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "onMissionStartListener ");
            DeliverTaskEditActivity.this.startDeliverTask("");
            return true;
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[InformationSystemContract.OrderInfoType.values().length];

        static {
            $EnumSwitchMapping$0[InformationSystemContract.OrderInfoType.SCAN.ordinal()] = 1;
            $EnumSwitchMapping$0[InformationSystemContract.OrderInfoType.INFORMATION_SYS.ordinal()] = 2;
        }
    }

    private final DispatchKeyPresenter getDispatchKeyPresenter() {
        return (DispatchKeyPresenter) this.dispatchKeyPresenter.getValue();
    }

    private final InformationSystemPresenter getInformationSystemPresenter() {
        return (InformationSystemPresenter) this.informationSystemPresenter.getValue();
    }

    private final HeyPuduWakeViewModel getMHeyPUduWakeVm() {
        return (HeyPuduWakeViewModel) this.mHeyPUduWakeVm.getValue();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
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

    /* JADX WARN: Type inference failed for: r1v14, types: [com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onHomeSettingDialogClickListener$1] */
    public DeliverTaskEditActivity() {
    }

    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    /* compiled from: DeliverTaskEditActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0017\"\u0004\b \u0010\u0019R\u000e\u0010!\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/ui/DeliverTaskEditActivity$Companion;", "", "()V", "BIRTHDAY_MODE", "", "BIRTHDAY_MODIFY_MODE", "DIRECT_MODE", "DIRECT_MODIFY_MODE", "MODE_TYPE", "", "MODIFY_MODE", "NEW_MODE", "RECYCLING_PLATE_MODE", "RECYCLING_PLATE_MODIFY_MODE", "SHOW_STATUS_KEY", "SPECIAL_MODE", "SPECIAL_MODIFY_MODE", "STATUS_CALL_TIMEOUT", "STATUS_NO_DINING_OUTLET", "STATUS_NO_RECYCLE_TABLE", "STATUS_NO_WASH_ROOM", "currentModeType", "getCurrentModeType", "()I", "setCurrentModeType", "(I)V", "isCanFillIn", "", "()Z", "setCanFillIn", "(Z)V", "isChangeMap", "setChangeMap", "isFirstBoot", "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int isChangeMap() {
            return DeliverTaskEditActivity.isChangeMap;
        }

        public final void setChangeMap(int i) {
            DeliverTaskEditActivity.isChangeMap = i;
        }

        public final int getCurrentModeType() {
            return DeliverTaskEditActivity.currentModeType;
        }

        public final void setCurrentModeType(int i) {
            DeliverTaskEditActivity.currentModeType = i;
        }

        public final boolean isCanFillIn() {
            return DeliverTaskEditActivity.isCanFillIn;
        }

        public final void setCanFillIn(boolean z) {
            DeliverTaskEditActivity.isCanFillIn = z;
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getNeedReinitApp()) {
            return;
        }
        NavigationBarUtil.hideNavigationBars(getWindow());
        setContentView(C5508R.layout.activity_deliver_task_edit);
        if (RobotMapManager.INSTANCE.haveMapGroup()) {
            View _$_findCachedViewById = _$_findCachedViewById(C5508R.id.viewLine);
            if (_$_findCachedViewById != null) {
                _$_findCachedViewById.setVisibility(0);
            }
        } else {
            View _$_findCachedViewById2 = _$_findCachedViewById(C5508R.id.viewLine);
            if (_$_findCachedViewById2 != null) {
                _$_findCachedViewById2.setVisibility(8);
            }
        }
        Pdlog.m3273d(this.TAG, "onCreate");
        reset();
        initView();
        initAllData();
        getMHeyPUduWakeVm().getWakeUpAngle().observe(this, new Observer<Double>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$onCreate$1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Double it) {
                if (!Intrinsics.areEqual(it, 0.0d)) {
                    if (DeliverTaskEditActivity.this.getIsSleepState() != 0) {
                        DeliverTaskEditActivity.this.openBlackScreen();
                        return;
                    }
                    RobotMoveManager robotMoveManager = RobotMoveManager.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(it, "it");
                    robotMoveManager.rotate(it.doubleValue());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        setIntent(intent);
        reset();
        initAllData();
    }

    private final void initAllData() {
        boolean z = true;
        Pdlog.m3273d(this.TAG, "initAllData");
        setSelectTableFragment();
        parserIntent();
        initAiVoice();
        updateModeUi();
        setTaskListLayout();
        bindPresenter();
        onFeatureChange(this.TYPE_FEATURE_NOMAL);
        LightPlayManager.INSTANCE.playNormalStatus();
        if (((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).hasTask()) {
            onFeatureChange(this.TYPE_FEATURE_INPUT);
        }
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            int i = currentModeType;
            if (i != 4 && i != 5) {
                z = false;
            }
            iSelectTableFragment.setShowAllGroup(z);
        }
    }

    private final boolean isBirthdayMode() {
        int i = currentModeType;
        return i == 2 || i == 3;
    }

    private final void setTaskListLayout() {
        Pdlog.m3273d(this.TAG, "setTaskListLayout");
        if (isModifyMode()) {
            RobotDeliverTaskLayout.initData$default((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout), TableTaskManager.INSTANCE.getAllTask(), TableTaskManager.INSTANCE.getCurrentTask(), null, 4, null);
        } else if (isBirthdayMode()) {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).start();
        } else {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).startShowAnimation();
        }
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
    }

    private final void parserIntent() {
        Pdlog.m3273d(this.TAG, "parserIntent");
        Intent intent = getIntent();
        if (intent != null) {
            currentModeType = intent.getIntExtra("MODE_TYPE", 0);
            this.mRobotType = intent.getIntExtra(TurnBackActivity.ROBOT_TYPE, 0);
            if (this.mRobotType == 1 && TableTaskManager.INSTANCE.haveReturnTask()) {
                showReturnDialog(0);
            }
            if (currentModeType == 1) {
                String str = this.TAG + 1;
            }
            this.showStatus = intent.getIntExtra("SHOW_STATUS_KEY", 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onFeatureChange " + type);
        if (type == this.TYPE_FEATURE_NOMAL) {
            if (this.isRelease) {
                return;
            }
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).hasTask();
        } else {
            if (type == this.TYPE_FEATURE_INPUT || type == this.TYPE_FEATURE_AIVOICE || type == this.TYPE_FEATURE_STANDBY || type != this.TYPE_FEATURE_DIALOG || !this.isInStandby) {
                return;
            }
            Pdlog.m3273d(this.TAG, "is in standby , do not set actionTimerCount");
        }
    }

    private final void showStatusTip() {
        int i = this.showStatus;
        if (i == 1) {
            String string = getString(C5508R.string.pdStr11_2);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_2)");
            showDialog$default(this, string, false, 2, null);
            return;
        }
        if (i == 2) {
            String string2 = getString(C5508R.string.pdStr11_1);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr11_1)");
            showDialog$default(this, string2, false, 2, null);
        } else if (i == 3) {
            String string3 = getString(C5508R.string.pdStr16_27);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr16_27)");
            showDialog$default(this, string3, false, 2, null);
        } else {
            if (i != 4) {
                return;
            }
            String string4 = getString(C5508R.string.pdStr16_26);
            Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr16_26)");
            showDialog$default(this, string4, false, 2, null);
        }
    }

    static /* synthetic */ void updateStartBtnLayout$default(DeliverTaskEditActivity deliverTaskEditActivity, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = true;
        }
        deliverTaskEditActivity.updateStartBtnLayout(z, z2, z3);
    }

    private final void updateStartBtnLayout(boolean isModify, boolean isShowHistory, boolean isDelivery) {
        String string;
        int i = !isModify ? C5508R.drawable.home_go_btn : C5508R.drawable.modify_go_on_btn;
        if (isModify) {
            string = getString(C5508R.string.pdStr2_13);
        } else if (!isDelivery) {
            string = getString(C5508R.string.pdStr16_5);
        } else {
            string = getString(C5508R.string.pdStr2_2);
        }
        TextView btn_start = (TextView) _$_findCachedViewById(C5508R.id.btn_start);
        Intrinsics.checkExpressionValueIsNotNull(btn_start, "btn_start");
        btn_start.setVisibility(0);
        TextView btn_start2 = (TextView) _$_findCachedViewById(C5508R.id.btn_start);
        Intrinsics.checkExpressionValueIsNotNull(btn_start2, "btn_start");
        Sdk27PropertiesKt.setBackgroundResource(btn_start2, i);
        TextView btn_start3 = (TextView) _$_findCachedViewById(C5508R.id.btn_start);
        Intrinsics.checkExpressionValueIsNotNull(btn_start3, "btn_start");
        btn_start3.setText(string);
        if (isShowHistory) {
            ImageView historyTaskIv = (ImageView) _$_findCachedViewById(C5508R.id.historyTaskIv);
            Intrinsics.checkExpressionValueIsNotNull(historyTaskIv, "historyTaskIv");
            historyTaskIv.setVisibility(0);
            ImageView settingIv = (ImageView) _$_findCachedViewById(C5508R.id.settingIv);
            Intrinsics.checkExpressionValueIsNotNull(settingIv, "settingIv");
            settingIv.setVisibility(0);
            return;
        }
        ImageView historyTaskIv2 = (ImageView) _$_findCachedViewById(C5508R.id.historyTaskIv);
        Intrinsics.checkExpressionValueIsNotNull(historyTaskIv2, "historyTaskIv");
        historyTaskIv2.setVisibility(8);
        ImageView settingIv2 = (ImageView) _$_findCachedViewById(C5508R.id.settingIv);
        Intrinsics.checkExpressionValueIsNotNull(settingIv2, "settingIv");
        settingIv2.setVisibility(8);
    }

    private final void updateModeUi() {
        switch (currentModeType) {
            case 0:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, false, true, false, 4, null);
                TextView mode_desc = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc, "mode_desc");
                mode_desc.setText(getString(C5508R.string.pdStr2_1));
                return;
            case 1:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, true, false, false, 4, null);
                TextView mode_desc2 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc2, "mode_desc");
                mode_desc2.setText(getString(C5508R.string.pdStr17_6));
                return;
            case 2:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(true);
                MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper.setOpenBirthdaySwitch(true);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, false, false, false, 4, null);
                TextView mode_desc3 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc3, "mode_desc");
                mode_desc3.setText(getString(C5508R.string.pdStr9_1));
                return;
            case 3:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(true);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, true, false, false, 4, null);
                TextView mode_desc4 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc4, "mode_desc");
                mode_desc4.setText(getString(C5508R.string.pdStr17_6));
                return;
            case 4:
                showRecycleTaskLayout(false);
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                MusicPlayerHelper musicPlayerHelper2 = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper2, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper2.setOpenBirthdaySwitch(false);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, false, false, false, 4, null);
                TextView mode_desc5 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc5, "mode_desc");
                mode_desc5.setText(getString(C5508R.string.pdStr4_1));
                return;
            case 5:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                MusicPlayerHelper musicPlayerHelper3 = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper3, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper3.setOpenBirthdaySwitch(false);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, true, false, false, 4, null);
                TextView mode_desc6 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc6, "mode_desc");
                mode_desc6.setText(getString(C5508R.string.pdStr17_6));
                return;
            case 6:
                showRecycleTaskLayout(true);
                switchBirthdayTheme(false);
                updateStartBtnLayout$default(this, false, false, false, 4, null);
                return;
            case 7:
                showRecycleTaskLayout(true);
                switchBirthdayTheme(false);
                updateStartBtnLayout$default(this, true, false, false, 4, null);
                return;
            case 8:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, false, false, false, 4, null);
                TextView mode_desc7 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc7, "mode_desc");
                mode_desc7.setText(getString(C5508R.string.pdStr17_1));
                return;
            case 9:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, true, false, false, 4, null);
                TextView mode_desc8 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc8, "mode_desc");
                mode_desc8.setText(getString(C5508R.string.pdStr17_6));
                return;
            default:
                return;
        }
    }

    private final void showRecycleTaskLayout(boolean r3) {
        if (r3) {
            RobotDeliverTaskLayout robot_deliver_task_layout = (RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout);
            Intrinsics.checkExpressionValueIsNotNull(robot_deliver_task_layout, "robot_deliver_task_layout");
            robot_deliver_task_layout.setVisibility(4);
            TextView mode_desc = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
            Intrinsics.checkExpressionValueIsNotNull(mode_desc, "mode_desc");
            mode_desc.setVisibility(4);
            return;
        }
        RobotDeliverTaskLayout robot_deliver_task_layout2 = (RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout);
        Intrinsics.checkExpressionValueIsNotNull(robot_deliver_task_layout2, "robot_deliver_task_layout");
        robot_deliver_task_layout2.setVisibility(0);
        TextView mode_desc2 = (TextView) _$_findCachedViewById(C5508R.id.mode_desc);
        Intrinsics.checkExpressionValueIsNotNull(mode_desc2, "mode_desc");
        mode_desc2.setVisibility(0);
    }

    private final void switchBirthdayTheme(boolean r6) {
        View childAt;
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            iSelectTableFragment.setBirthdayTheme(r6);
        }
        if (r6) {
            LottieAnimationView birth_fall_anim = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
            Intrinsics.checkExpressionValueIsNotNull(birth_fall_anim, "birth_fall_anim");
            birth_fall_anim.setVisibility(0);
            LottieAnimationView birth_float_anim = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
            Intrinsics.checkExpressionValueIsNotNull(birth_float_anim, "birth_float_anim");
            birth_float_anim.setVisibility(0);
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim)).playAnimation();
            ((LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim)).playAnimation();
            View findViewById = findViewById(R.id.content);
            if (!(findViewById instanceof ViewGroup)) {
                findViewById = null;
            }
            ViewGroup viewGroup = (ViewGroup) findViewById;
            childAt = viewGroup != null ? viewGroup.getChildAt(0) : null;
            if (childAt != null) {
                childAt.setBackgroundColor(getColor(C5508R.color.theme_birthday_bg));
            }
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).switchBirthday(true);
            return;
        }
        LottieAnimationView birth_fall_anim2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
        Intrinsics.checkExpressionValueIsNotNull(birth_fall_anim2, "birth_fall_anim");
        birth_fall_anim2.setVisibility(8);
        LottieAnimationView birth_float_anim2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
        Intrinsics.checkExpressionValueIsNotNull(birth_float_anim2, "birth_float_anim");
        birth_float_anim2.setVisibility(8);
        View findViewById2 = findViewById(R.id.content);
        if (!(findViewById2 instanceof ViewGroup)) {
            findViewById2 = null;
        }
        ViewGroup viewGroup2 = (ViewGroup) findViewById2;
        childAt = viewGroup2 != null ? viewGroup2.getChildAt(0) : null;
        if (childAt != null) {
            childAt.setBackgroundColor(getColor(C5508R.color.theme_main_bg));
        }
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).switchBirthday(false);
    }

    private final void initView() {
        ImageView ivBack = (ImageView) _$_findCachedViewById(C5508R.id.ivBack);
        Intrinsics.checkExpressionValueIsNotNull(ivBack, "ivBack");
        ViewExtKt.onSingleClick(ivBack, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$1
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
                Intent intent = new Intent(DeliverTaskEditActivity.this, (Class<?>) HomeActivity.class);
                intent.putExtra(HomeActivity.BACK_TYPE_KEY, 2);
                DeliverTaskEditActivity.this.jumpAndFinish(intent);
            }
        });
        settingInfoClickListener();
        ((TextView) _$_findCachedViewById(C5508R.id.btn_start)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$2
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverTaskEditActivity.startDeliverTask$default(DeliverTaskEditActivity.this, null, 1, null);
            }
        });
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C5508R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setOnTaskChangeListener(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$3
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
                String str;
                int i;
                int i2;
                str = DeliverTaskEditActivity.this.TAG;
                Pdlog.m3273d(str, "robot_deliver_task_layout onTaskChangeListener");
                if (((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).hasTask()) {
                    DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                    i2 = deliverTaskEditActivity.TYPE_FEATURE_INPUT;
                    deliverTaskEditActivity.onFeatureChange(i2);
                } else {
                    DeliverTaskEditActivity deliverTaskEditActivity2 = DeliverTaskEditActivity.this;
                    i = deliverTaskEditActivity2.TYPE_FEATURE_NOMAL;
                    deliverTaskEditActivity2.onFeatureChange(i);
                }
            }
        });
        _$_findCachedViewById(C5508R.id.turn_back_fill_in_layout).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
            }
        });
        ((TextView) _$_findCachedViewById(C5508R.id.btn_go_to_task_ui)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$5
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
                View turn_back_fill_in_layout = DeliverTaskEditActivity.this._$_findCachedViewById(C5508R.id.turn_back_fill_in_layout);
                Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
                turn_back_fill_in_layout.setVisibility(8);
            }
        });
        ((ImageView) _$_findCachedViewById(C5508R.id.historyTaskIv)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$6
            @Override // com.pudutech.peanut.robot_ui.listener.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                DeliverTaskEditActivity.this.showReturnDialog(1);
            }
        });
        ImageView settingIv = (ImageView) _$_findCachedViewById(C5508R.id.settingIv);
        Intrinsics.checkExpressionValueIsNotNull(settingIv, "settingIv");
        ViewExtKt.onSingleClick(settingIv, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initView$7
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
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                deliverTaskEditActivity.jump(new Intent(deliverTaskEditActivity, (Class<?>) DeliverySettingActivity.class));
            }
        });
    }

    private final void setSelectTableFragment() {
        if (this.selectTableFragment == null) {
            this.selectTableFragment = new SelectTableFragment();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
            if (iSelectTableFragment == null) {
                Intrinsics.throwNpe();
            }
            FragmentUtils.replace(supportFragmentManager, iSelectTableFragment, C5508R.id.select_table_container);
            initTablesInput();
        }
    }

    private final boolean isModifyMode() {
        int i = currentModeType;
        return i == 1 || i == 3 || i == 5 || i == 7 || i == 9;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void startDeliverTask$default(DeliverTaskEditActivity deliverTaskEditActivity, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        deliverTaskEditActivity.startDeliverTask(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDeliverTask(String employees) {
        boolean z;
        String string;
        ShowTipMsgDialog showDialog;
        String currentMapDiningOutLetChosen = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
        if (currentMapDiningOutLetChosen == null || currentMapDiningOutLetChosen.length() == 0) {
            ToastUtils.show(this, getString(C5508R.string.please_setting_back_part), new Object[0]);
            return;
        }
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        if ((power != null ? power.intValue() : 0) > 2 || isModifyMode()) {
            Pdlog.m3273d(this.TAG, "startDeliverTask");
            ArrayList<TrayModel> selectTask = getSelectTask();
            ArrayList<TrayModel> arrayList = selectTask;
            if (!(arrayList instanceof Collection) || !arrayList.isEmpty()) {
                Iterator<T> it = arrayList.iterator();
                while (it.hasNext()) {
                    if (!((TrayModel) it.next()).getAllDestinations().isEmpty()) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (!z) {
                Pdlog.m3273d(this.TAG, "startDeliverTask task is null ...");
                if (this.needSelectTableDialog == null) {
                    int i = currentModeType;
                    if (i == 7 || i == 6) {
                        String string2 = getString(C5508R.string.pdStr16_30);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr16_30)");
                        showDialog = showDialog(string2, true);
                    } else {
                        String string3 = getString(C5508R.string.pdStr2_18);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr2_18)");
                        showDialog = showDialog(string3, true);
                    }
                    this.needSelectTableDialog = showDialog;
                }
                ShowTipMsgDialog showTipMsgDialog = this.needSelectTableDialog;
                if (showTipMsgDialog != null) {
                    int i2 = currentModeType;
                    if (i2 == 7 || i2 == 6) {
                        string = getString(C5508R.string.pdStr16_30);
                    } else {
                        string = getString(C5508R.string.pdStr2_18);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(string, "if (currentModeType == R…                        }");
                    showTipMsgDialog.showTipMsg(string);
                }
                ShowTipMsgDialog showTipMsgDialog2 = this.needSelectTableDialog;
                if (showTipMsgDialog2 != null) {
                    showTipMsgDialog2.show();
                    return;
                }
                return;
            }
            TableTaskManager.INSTANCE.setAllTask(selectTask, ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).getSortType(), getMovePerformance(), isCanSaveDeliveryHistory(), isBirthdayMode());
            TableTaskManager.INSTANCE.setAllReturnTask(selectTask);
            notifyOrderStart(selectTask, employees);
            Pdlog.m3273d(this.TAG, "startDeliverTask task is " + selectTask);
            int i3 = currentModeType;
            if (i3 == 0) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new DeliverTaskEditActivity$startDeliverTask$1(this, null), 2, null);
                return;
            } else if (i3 == 5) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new DeliverTaskEditActivity$startDeliverTask$2(this, null), 2, null);
                return;
            } else {
                jumpToStartTask();
                return;
            }
        }
        Pdlog.m3273d(this.TAG, "batteryPresenter check Allowed for move is false");
        String string4 = getString(C5508R.string.pdStr2_19);
        Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr2_19)");
        MyBaseActivity.showTipDialog$default(this, string4, null, null, null, 14, null);
    }

    private final boolean isCanSaveDeliveryHistory() {
        int i = currentModeType;
        return i == 0 || i == 1 || i == 8 || i == 9 || i == 3 || i == 2;
    }

    private final MovePerformance getMovePerformance() {
        return MovePerformance.NORMAL;
    }

    private final void notifyOrderStart(ArrayList<TrayModel> allTask, String employees) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = allTask.iterator();
        while (it.hasNext()) {
            Iterator<T> it2 = ((TrayModel) it.next()).getAllDestinations().iterator();
            while (it2.hasNext()) {
                ArrayList<InformationSystemContract.OrderInfo> foodInfo = ((DeliveryModel) it2.next()).getFoodInfo();
                if (foodInfo != null) {
                    arrayList.addAll(foodInfo);
                }
            }
        }
        arrayList.isEmpty();
        if (arrayList2.isEmpty()) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jumpToStartTask() {
        Pdlog.m3273d(this.TAG, "jumpToStartTask currentModeType = " + currentModeType);
        Intent intent = new Intent(this, (Class<?>) DeliverActivity.class);
        int i = currentModeType;
        if (i == 2 || i == 3) {
            intent.putExtra("DELIVER_MODE", 1);
        } else if (i == 4 || i == 5) {
            intent.putExtra("DELIVER_MODE", 2);
        } else if (i == 8 || i == 9) {
            intent.putExtra("DELIVER_MODE", 3);
        }
        int i2 = currentModeType;
        if (i2 == 0 || i2 == 4 || i2 == 2 || i2 == 8) {
            SpUtils.set(RobotContext.INSTANCE.getContext(), "key_deliver_recycle_plate_switch", false);
        }
        isCanFillIn = false;
        jump(intent);
    }

    private final ArrayList<TrayModel> getSelectTask() {
        return ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).getAllTask();
    }

    static /* synthetic */ ShowTipMsgDialog showDialog$default(DeliverTaskEditActivity deliverTaskEditActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return deliverTaskEditActivity.showDialog(str, z);
    }

    private final ShowTipMsgDialog showDialog(String msg, final boolean needCloseSensorFace) {
        openBlackScreen();
        return MyBaseActivity.showTipDialog$default(this, msg, new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$showDialog$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                int i;
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_DIALOG;
                deliverTaskEditActivity.onFeatureChange(i);
                boolean z = needCloseSensorFace;
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$showDialog$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                int i;
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
                deliverTaskEditActivity.onFeatureChange(i);
            }
        }, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playClickGoAlertVoice() {
        int i = currentModeType;
        if (i == 6 || i == 7) {
            return;
        }
        this.isPlayClickGoAlertVoice = true;
        VoicePlayTasks.INSTANCE.playDeliveryGoAlert();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (this.isPlayInputOneTableVoice && this.isPlayInputOneTableVoiceFinish) {
            startDelayVoice();
        }
        return super.dispatchTouchEvent(ev);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDelayVoice() {
        Pdlog.m3273d(this.TAG, "startDelayVoice");
        stopDelayVoice();
        if (this.isRelease || this.isPlayClickGoAlertVoice) {
            return;
        }
        this.mainHandler.sendEmptyMessageDelayed(this.PLAY_VOICE, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopDelayVoice() {
        this.mainHandler.removeMessages(this.PLAY_VOICE);
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
        if (this.isRelease && this.isJumpToMusicAc) {
            Pdlog.m3273d(this.TAG, "onStart isJumpToMusicAc");
            reset();
            initAllData();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            Pdlog.m3273d(this.TAG, "onWindowFocusChanged isFirstStart , start init something");
            this.isFirstStart = false;
            if (this.isRelease) {
                Pdlog.m3274e(this.TAG, "onWindowFocusChanged isRelease");
                return;
            }
            ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).startChangeListener(this);
            showStatusTip();
            Pdlog.m3273d(this.TAG, "onWindowFocusChanged isPlayLowPowerVoice = " + this.isPlayLowPowerVoice + "  ");
            playEntryVoiceIfNeed();
        }
    }

    private final void settingInfoClickListener() {
        ImageView imageView = (ImageView) _$_findCachedViewById(C5508R.id.setting_info);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        ImageView imageView2 = (ImageView) _$_findCachedViewById(C5508R.id.setting_info);
        if (imageView2 != null) {
            ViewExtKt.onSingleClick(imageView2, new Function1<View, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$settingInfoClickListener$1
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
                    DeliverTaskEditActivity.this.showSettingDialog();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingDialog() {
        Pdlog.m3273d(this.TAG, "showSettingDialog");
        if (this.homeSettingDialog == null) {
            this.homeSettingDialog = new HomeSettingDialog(this);
            HomeSettingDialog homeSettingDialog = this.homeSettingDialog;
            if (homeSettingDialog == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog.setOnHomeSettingDialogClickListener(this.onHomeSettingDialogClickListener);
            HomeSettingDialog homeSettingDialog2 = this.homeSettingDialog;
            if (homeSettingDialog2 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$showSettingDialog$1
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    String str;
                    boolean z;
                    int i;
                    str = DeliverTaskEditActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog OnDismissListener");
                    z = DeliverTaskEditActivity.this.isRelease;
                    if (z) {
                        return;
                    }
                    DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                    i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
                    deliverTaskEditActivity.onFeatureChange(i);
                }
            });
            HomeSettingDialog homeSettingDialog3 = this.homeSettingDialog;
            if (homeSettingDialog3 == null) {
                Intrinsics.throwNpe();
            }
            homeSettingDialog3.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$showSettingDialog$2
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    String str;
                    str = DeliverTaskEditActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog setOnShowListener");
                }
            });
        }
        onFeatureChange(this.TYPE_FEATURE_DIALOG);
        HomeSettingDialog homeSettingDialog4 = this.homeSettingDialog;
        if (homeSettingDialog4 == null) {
            Intrinsics.throwNpe();
        }
        homeSettingDialog4.show();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        RobotDeliverTaskLayout robotDeliverTaskLayout = (RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout);
        if (robotDeliverTaskLayout != null) {
            robotDeliverTaskLayout.removeAnimation();
        }
        LottieAnimationView lottieAnimationView = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_fall_anim);
        if (lottieAnimationView != null && lottieAnimationView.isAnimating()) {
            lottieAnimationView.cancelAnimation();
        }
        LottieAnimationView lottieAnimationView2 = (LottieAnimationView) _$_findCachedViewById(C5508R.id.birth_float_anim);
        if (lottieAnimationView2 == null || !lottieAnimationView2.isAnimating()) {
            return;
        }
        lottieAnimationView2.cancelAnimation();
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        DeliverTaskEditActivity deliverTaskEditActivity = this;
        getInformationSystemPresenter().replaceView(deliverTaskEditActivity);
        getDispatchKeyPresenter().replaceView(deliverTaskEditActivity);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        if (!isModifyMode()) {
            this.leaseHelper.setOnLeaseExpireForceCloseCallback(new Function0<Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$bindPresenter$1
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
                    String str;
                    str = DeliverTaskEditActivity.this.TAG;
                    Pdlog.m3273d(str, "onLeaseExpireForceCloseCallback ");
                    DeliverTaskEditActivity deliverTaskEditActivity2 = DeliverTaskEditActivity.this;
                    deliverTaskEditActivity2.jump(new Intent(deliverTaskEditActivity2, (Class<?>) SettingActivity.class));
                }
            });
            this.leaseHelper.bind(this);
            LeaseHelper leaseHelper = this.leaseHelper;
            Application application = getApplication();
            Intrinsics.checkExpressionValueIsNotNull(application, "this.application");
            leaseHelper.startCheck(application);
        }
        AiVoiceTriggerHelper.INSTANCE.setOnTaskInputListener(this.onTaskInputListener);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionStartListener(this.onMissionStartListener);
        Integer power = BatteryInfoManager.INSTANCE.getPower();
        if ((power != null ? power.intValue() : 0) < 10) {
            showLowerNotify();
        }
        if (isFirstBoot) {
            isFirstBoot = false;
        }
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        DeliverTaskEditActivity deliverTaskEditActivity = this;
        getInformationSystemPresenter().removeView(deliverTaskEditActivity);
        this.beeperCallHelper.unbind();
        this.leaseHelper.unbind();
        getDispatchKeyPresenter().removeView(deliverTaskEditActivity);
        AiVoiceTriggerHelper.INSTANCE.setOnTaskInputListener((Function2) null);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionStartListener((Function0) null);
        stopStandby();
        ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).stopChangeListener();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity
    public void jumpAndFinish(Intent intent) {
        jump(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        Pdlog.m3273d(this.TAG, "jump");
        release();
        if (intent != null) {
            startActivity(intent);
        }
        overridePendingTransition(0, 0);
    }

    private final void reset() {
        Pdlog.m3273d(this.TAG, "reset");
        this.isRelease = false;
        this.isPlayInputOneTableVoiceFinish = false;
        this.isPlayClickGoAlertVoice = false;
        this.isPlayLowPowerVoice = false;
        this.isShowLowPowerDialog = false;
        this.isPlayInputOneTableVoice = false;
        this.showStatus = -1;
        this.isFirstStart = true;
        this.isJumpToMusicAc = false;
        this.fillInStatusInit = false;
        View turn_back_fill_in_layout = _$_findCachedViewById(C5508R.id.turn_back_fill_in_layout);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
        turn_back_fill_in_layout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release() {
        this.isRelease = true;
        unBindPresenter();
        stopDelayVoice();
        VoicePlayTasks.INSTANCE.finishStop();
    }

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        setScreenDark();
    }

    private final void showStandbyAnimation() {
        Pdlog.m3273d(this.TAG, "showStandbyAnimation");
        this.isInStandby = true;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getStandby());
        onFeatureChange(this.TYPE_FEATURE_STANDBY);
        setScreenDark();
    }

    private final void stopStandby() {
        Pdlog.m3273d(this.TAG, "stopStandby");
        this.isInStandby = false;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.stopPlay();
        onFeatureChange(this.TYPE_FEATURE_NOMAL);
        resetScreenLight();
    }

    private final void initTablesInput() {
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            iSelectTableFragment.setOnSelectTable(new Function1<String, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initTablesInput$1
                /* JADX INFO: Access modifiers changed from: package-private */
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
                    String str;
                    boolean z;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    str = DeliverTaskEditActivity.this.TAG;
                    Pdlog.m3273d(str, "initTablesInput " + it);
                    z = DeliverTaskEditActivity.this.isPlayInputOneTableVoice;
                    if (!z) {
                        DeliverTaskEditActivity.this.isPlayInputOneTableVoice = true;
                        if (DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 3 || DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 2 || DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 5 || DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 4 || DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 7 || DeliverTaskEditActivity.INSTANCE.getCurrentModeType() == 6) {
                            DeliverTaskEditActivity.this.isPlayInputOneTableVoiceFinish = true;
                        } else {
                            VoicePlayTasks.INSTANCE.playDeliveryInputOne(new Function3<PlayEvent, String, Boolean, Unit>() { // from class: com.pudutech.peanut.robot_ui.ui.DeliverTaskEditActivity$initTablesInput$1.1
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent, String str2, Boolean bool) {
                                    invoke2(playEvent, str2, bool);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(PlayEvent event, String str2, Boolean bool) {
                                    Intrinsics.checkParameterIsNotNull(event, "event");
                                    if (event == PlayEvent.Finish) {
                                        DeliverTaskEditActivity.this.isPlayInputOneTableVoiceFinish = true;
                                        DeliverTaskEditActivity.this.startDelayVoice();
                                    }
                                    if (event == PlayEvent.Stop) {
                                        DeliverTaskEditActivity.this.isPlayInputOneTableVoiceFinish = true;
                                    }
                                }
                            });
                        }
                    }
                    DeliverTaskEditActivity.this.addSelectTask(it);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addSelectTask(String task) {
        RobotDeliverTaskLayout robotDeliverTaskLayout = (RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout);
        int i = currentModeType;
        robotDeliverTaskLayout.setTaskName(task, (i == 0 || i == 1 || i == 9 || i == 8) ? Constans.INSTANCE.getSingleTrayMultiTableSwitch() : false);
    }

    private final void playEntryVoiceIfNeed() {
        Pdlog.m3273d(this.TAG, "playEntryVoiceIfNeed");
        VoicePlayTasks.INSTANCE.playIntoDeliveryEdit();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity
    public void notifyBatteryInfo(int state, ChargeState model, boolean isCharging, Integer i) {
        if (state == 1) {
            if (this.isShowLowPowerDialog) {
                return;
            }
            Pdlog.m3275i(this.TAG, "showLowerNotify power：" + i);
            showLowerNotify();
            if (BatteryInfoManager.INSTANCE.getPower() != null) {
                Integer power = BatteryInfoManager.INSTANCE.getPower();
                if ((power != null ? power.intValue() : 0) > 5) {
                    VoicePlayTasks.INSTANCE.playLowPower5_10();
                    return;
                }
                Integer power2 = BatteryInfoManager.INSTANCE.getPower();
                if ((power2 != null ? power2.intValue() : 0) > 2) {
                    VoicePlayTasks.INSTANCE.playLowPower2_5();
                    return;
                }
                Integer power3 = BatteryInfoManager.INSTANCE.getPower();
                if ((power3 != null ? power3.intValue() : 0) < 2) {
                    VoicePlayTasks.INSTANCE.playLowPower0_2();
                    return;
                }
                return;
            }
            return;
        }
        if (state == 2) {
            if (i != null) {
                ((MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar)).setBattery(i.intValue());
                return;
            }
            return;
        }
        if (state != 3) {
            if (state == 4 && model != null && isCharging) {
                PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
                return;
            }
            return;
        }
        if (BatteryInfoManager.INSTANCE.getPower() != null) {
            Integer power4 = BatteryInfoManager.INSTANCE.getPower();
            if ((power4 != null ? power4.intValue() : 0) < 2) {
                VoicePlayTasks.INSTANCE.playLowPower0_2();
                return;
            }
            Integer power5 = BatteryInfoManager.INSTANCE.getPower();
            if ((power5 != null ? power5.intValue() : 0) < 5) {
                VoicePlayTasks.INSTANCE.playLowPower2_5();
                return;
            }
            Integer power6 = BatteryInfoManager.INSTANCE.getPower();
            if ((power6 != null ? power6.intValue() : 0) <= 10) {
                VoicePlayTasks.INSTANCE.playLowPower5_10();
            }
        }
    }

    private final void showLowerNotify() {
        openBlackScreen();
        Pdlog.m3273d(this.TAG, "showLowerNotify ");
        if (this.isShowLowPowerDialog) {
            return;
        }
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        ShowTipMsgDialog showTipMsgDialog = this.lowerPowerDialog;
        if (showTipMsgDialog != null && showTipMsgDialog.isShowing()) {
            this.isShowLowPowerDialog = true;
            return;
        }
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C5508R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        this.lowerPowerDialog = showDialog$default(this, string, false, 2, null);
    }

    @Override // com.pudutech.peanut.presenter.information_system_task.InformationSystemContract.ViewInterface
    public boolean onReceiveOrderInfo(ArrayList<InformationSystemContract.OrderInfo> info, InformationSystemContract.OrderInfoType type, int trayIndex) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(this.TAG, "onReceiveOrderInfo : info = " + info + "; type = " + type + "; ");
        int i = currentModeType;
        if (i == 4 || i == 5 || i == 6 || i == 7) {
            ToastUtils.show(this, getString(C5508R.string.pdStr2_35), new Object[0]);
            return false;
        }
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        if (Constans.INSTANCE.getSingleTrayMultiTableSwitch()) {
            Pdlog.m3273d(this.TAG, "getSingleTrayMultiTableSwitch true ");
            ToastUtils.show(this, getString(C5508R.string.pdStr2_34), new Object[0]);
            return false;
        }
        InformationSystemContract.OrderInfo orderInfo = info.get(0);
        Intrinsics.checkExpressionValueIsNotNull(orderInfo, "info[0]");
        InformationSystemContract.OrderInfo orderInfo2 = orderInfo;
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment == null || !iSelectTableFragment.hasTable(orderInfo2.getSeatName())) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C5508R.string.pdStr2_36);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_36)");
            Object[] objArr = {orderInfo2.getSeatName()};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(this, format, new Object[0]);
            return false;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i2 == 1) {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setTaskName(orderInfo2.getSeatName(), orderInfo2);
            return true;
        }
        if (i2 == 2) {
            if (trayIndex < 0 || trayIndex > ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).getInputTaskCount()) {
                return false;
            }
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).setTrayOrders(orderInfo2.getSeatName(), info, trayIndex - 1);
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.pudutech.peanut.presenter.information_system_task.InformationSystemContract.ViewInterface
    public boolean onReceiveDeliveryTask(MoveSortType sortType, ArrayList<TrayModel> allTrays, boolean executeTask) {
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Intrinsics.checkParameterIsNotNull(allTrays, "allTrays");
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        Pdlog.m3273d(this.TAG, "onReceiveDeliveryTask : sortType = " + sortType + "; allTrays = " + allTrays + "; executeTask = " + executeTask + "; ");
        if (currentModeType == 0) {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C5508R.id.robot_deliver_task_layout)).initData(allTrays, null, sortType);
            if (executeTask) {
                startDeliverTask("");
            }
            return true;
        }
        Pdlog.m3274e(this.TAG, "onReceiveDeliveryTask : is not NEW_MODE ");
        return false;
    }

    @Override // com.pudutech.peanut.presenter.general_task.DispatchKeyContract.ViewInterface
    public boolean onDispatchKey(DispatchKeyContract.KEY key, String msg) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3273d(this.TAG, "onDispatchKey : key = " + key + "; msg = " + msg + "; ");
        if (key == DispatchKeyContract.KEY.QR_EMPLOYEES) {
            getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
            startDeliverTask(msg);
            return true;
        }
        if (key != DispatchKeyContract.KEY.REMOTE_START) {
            return false;
        }
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        startDeliverTask(msg);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showReturnDialog(int state) {
        this.mReturnDialog = new ReturnTipDialog(this, state, state);
        ReturnTipDialog returnTipDialog = this.mReturnDialog;
        if (returnTipDialog != null) {
            returnTipDialog.show();
        }
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.BatteryBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (NetStatusUtil.isConnected(RobotContext.INSTANCE.getContext())) {
            MyStatusBarLayout myStatusBarLayout = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar);
            if (myStatusBarLayout != null) {
                myStatusBarLayout.onNetStatus(true);
                Unit unit = Unit.INSTANCE;
            }
        } else {
            MyStatusBarLayout myStatusBarLayout2 = (MyStatusBarLayout) _$_findCachedViewById(C5508R.id.layout_my_status_bar);
            if (myStatusBarLayout2 != null) {
                myStatusBarLayout2.onNetStatus(false);
                Unit unit2 = Unit.INSTANCE;
            }
        }
        if (isChangeMap == 1) {
            TableTaskManager.INSTANCE.clearAll();
        }
        Pdlog.m3273d(this.TAG, "onResume");
        getMHeyPUduWakeVm().startListening();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
        getMHeyPUduWakeVm().stopListening();
    }

    @Override // com.pudutech.peanut.robot_ui.p063ui.base.SleepBaseActivity, com.pudutech.peanut.robot_ui.p063ui.base.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
