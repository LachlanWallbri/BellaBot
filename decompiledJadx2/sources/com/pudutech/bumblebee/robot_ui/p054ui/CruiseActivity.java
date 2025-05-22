package com.pudutech.bumblebee.robot_ui.p054ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseConfig;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseContract;
import com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.advertise.AdSceneConfig;
import com.pudutech.bumblebee.robot_ui.advertise.AdVm;
import com.pudutech.bumblebee.robot_ui.advertise.AdverBaseFragment;
import com.pudutech.bumblebee.robot_ui.bean.ReturnPointBean;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.CruiseActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.greeter.GotoWelcomeAreaActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.task.CruiseTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM;
import com.pudutech.bumblebee.robot_ui.viewmodel.CruiseVm;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import com.warkiz.widget.SizeUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: CruiseActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000º\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002(|\u0018\u0000 À\u00012\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0004À\u0001Á\u0001B\u0005¢\u0006\u0002\u0010\u0005J\u0014\u0010\u008d\u0001\u001a\u00020<2\t\b\u0002\u0010\u008e\u0001\u001a\u000200H\u0002J\t\u0010\u008f\u0001\u001a\u00020<H\u0002J\t\u0010\u0090\u0001\u001a\u00020<H\u0002J\t\u0010\u0091\u0001\u001a\u00020<H\u0016J\t\u0010\u0092\u0001\u001a\u00020<H\u0002J,\u0010\u0093\u0001\u001a\n\u0012\u0005\u0012\u00030\u0095\u00010\u0094\u00012\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\t\b\u0002\u0010\u0098\u0001\u001a\u000200H\u0002¢\u0006\u0003\u0010\u0099\u0001J\u0013\u0010\u009a\u0001\u001a\u00020\u00072\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0002J\t\u0010\u009b\u0001\u001a\u00020<H\u0002J\t\u0010\u009c\u0001\u001a\u00020<H\u0002J\u0015\u0010\u009d\u0001\u001a\u00020<2\n\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009f\u0001H\u0016J\t\u0010 \u0001\u001a\u000200H\u0002J\t\u0010¡\u0001\u001a\u000200H\u0016J\u0013\u0010¢\u0001\u001a\u00020<2\b\u0010£\u0001\u001a\u00030¤\u0001H\u0016J\t\u0010¥\u0001\u001a\u00020\nH\u0016J\t\u0010¦\u0001\u001a\u00020<H\u0014J\t\u0010§\u0001\u001a\u00020<H\u0014J\u0012\u0010¨\u0001\u001a\u00020<2\u0007\u0010©\u0001\u001a\u00020\nH\u0002J\t\u0010ª\u0001\u001a\u00020<H\u0014J\t\u0010«\u0001\u001a\u00020<H\u0014J\t\u0010¬\u0001\u001a\u00020<H\u0014J\u0012\u0010\u00ad\u0001\u001a\u00020<2\u0007\u0010®\u0001\u001a\u000200H\u0016J\u0013\u0010¯\u0001\u001a\u00020<2\b\u0010\u0096\u0001\u001a\u00030\u0097\u0001H\u0002J\t\u0010°\u0001\u001a\u00020<H\u0002J\t\u0010±\u0001\u001a\u00020<H\u0002J\t\u0010²\u0001\u001a\u00020<H\u0002J\u0012\u0010³\u0001\u001a\u00020<2\u0007\u0010´\u0001\u001a\u00020\nH\u0002J\t\u0010µ\u0001\u001a\u00020<H\u0002J\t\u0010¶\u0001\u001a\u00020<H\u0002J\u0011\u0010·\u0001\u001a\u00020<2\u0006\u0010q\u001a\u00020%H\u0016J\t\u0010¸\u0001\u001a\u00020<H\u0002J\t\u0010¹\u0001\u001a\u00020<H\u0002J\t\u0010º\u0001\u001a\u00020<H\u0002J\t\u0010»\u0001\u001a\u00020<H\u0002J\u001d\u0010¼\u0001\u001a\u00020<2\b\u0010´\u0001\u001a\u00030\u0097\u00012\b\u0010½\u0001\u001a\u00030¾\u0001H\u0016J\t\u0010¿\u0001\u001a\u00020<H\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0016\u001a\u0004\b \u0010!R\u000e\u0010#\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0004\n\u0002\u0010)R#\u0010*\u001a\n \b*\u0004\u0018\u00010+0+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0016\u001a\u0004\b,\u0010-R \u00101\u001a\u0002002\u0006\u0010/\u001a\u0002008B@BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u000e\u00103\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00106\u001a\u000200X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00102\"\u0004\b7\u00108R\u000e\u00109\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020<0;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010>\u001a\u00020?8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\u0016\u001a\u0004\b@\u0010AR\u0014\u0010C\u001a\b\u0012\u0002\b\u0003\u0018\u00010DX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010F\u001a\u0004\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010H\u001a\u00020I8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bL\u0010\u0016\u001a\u0004\bJ\u0010KR\u000e\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010O\u001a\u00020P8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bS\u0010\u0016\u001a\u0004\bQ\u0010RR#\u0010T\u001a\n \b*\u0004\u0018\u00010+0+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010\u0016\u001a\u0004\bU\u0010-R\u000e\u0010W\u001a\u00020XX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010Y\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010Z\u001a4\u0012\u0013\u0012\u001100¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(^\u0012\u0013\u0012\u001100¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(_\u0012\u0004\u0012\u00020<\u0018\u00010[X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020aX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010b\u001a\b\u0012\u0004\u0012\u00020<0;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR\u0016\u0010g\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010;X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020iX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010j\u001a\u001d\u0012\u0013\u0012\u001100¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(l\u0012\u0004\u0012\u00020<0kX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010m\u001a2\u0012\u0013\u0012\u00110n¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(o\u0012\u0013\u0012\u00110p¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(q\u0012\u0004\u0012\u00020<0[X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010r\u001a\u001d\u0012\u0013\u0012\u00110s¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(t\u0012\u0004\u0012\u00020<0kX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020wX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010x\u001a\u001d\u0012\u0013\u0012\u00110y¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(z\u0012\u0004\u0012\u00020<0kX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010{\u001a\u00020|X\u0082\u0004¢\u0006\u0004\n\u0002\u0010}R\u001d\u0010~\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010\u0016\u001a\u0005\b\u007f\u0010\u0080\u0001R\u001e\u0010\u0082\u0001\u001a\u00020P8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0084\u0001\u0010\u0016\u001a\u0005\b\u0083\u0001\u0010RR\u0010\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0087\u0001\u001a\u001e\u0012\u0014\u0012\u00120\u0088\u0001¢\u0006\f\b\\\u0012\b\b]\u0012\u0004\b\b(q\u0012\u0004\u0012\u00020<0kX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0089\u0001\u001a\u0005\u0018\u00010\u008a\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u008b\u0001\u001a\u0011\u0012\u0007\u0012\u0005\u0018\u00010\u008c\u0001\u0012\u0004\u0012\u00020<0kX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Â\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseVmActivity;", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CruiseVm;", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_PAUSE_FEATURE_AIVOICE", "", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "arrivalVoiceTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "Lkotlin/Lazy;", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "callSetVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "getCallSetVM", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "callSetVM$delegate", "cruisePresenter", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruisePresenter;", "getCruisePresenter", "()Lcom/pudutech/bumblebee/presenter/cruise_task/CruisePresenter;", "cruisePresenter$delegate", "currentCruiseId", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewEvent;", "currentPauseFeature", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/CruiseActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity$functionButton$1;", "hasCopyStayPointView", "Landroid/view/View;", "getHasCopyStayPointView", "()Landroid/view/View;", "hasCopyStayPointView$delegate", "<set-?>", "", "isCruiseLayout", "()Z", "isFirstRepo", "isFirstStart", "isNeedStopDotVoice", "isOnErrorDialog", "setOnErrorDialog", "(Z)V", "isRelease", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "mAdVm", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "getMAdVm", "()Lcom/pudutech/bumblebee/robot_ui/advertise/AdVm;", "mAdVm$delegate", "mAdverBaseFragment", "Lcom/pudutech/bumblebee/robot_ui/advertise/AdverBaseFragment;", "mAdverType", "mContDownHandler", "Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity$CountDownHandler;", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "getMovingLoopVoiceTask", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "movingLoopVoiceTask$delegate", "noCopyStayPointView", "getNoCopyStayPointView", "noCopyStayPointView$delegate", "onCancelCruiseClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyVoiceClickListener;", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "getOnFallDropCallBack", "()Lkotlin/jvm/functions/Function0;", "setOnFallDropCallBack", "(Lkotlin/jvm/functions/Function0;)V", "onLostLocationLostCallback", "onPauseLayoutClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "onTouchSensorAnimationListener", "Lkotlin/Function1;", "isShow", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "onVoiceStateListener", "Lcom/pudutech/bumblebee/presenter/robot_voices/PlayEvent;", "playEvent", "pauseVoiceTask", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "scheduleEventLister", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "triggerEvent", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/CruiseActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity$singleBatteryListener$1;", "stayPointCopyWrite", "getStayPointCopyWrite", "()Ljava/lang/String;", "stayPointCopyWrite$delegate", "stayPointVoiceTask", "getStayPointVoiceTask", "stayPointVoiceTask$delegate", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "ttsPlayListener", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "adverPause", "isNeedGoneVideo", "adverResume", "bindPresenter", "createObserver", "finishCruise", "getAdverTimeFormat", "", "", "count", "", "isJudgeMin", "(JZ)[Ljava/lang/Object;", "getTimeFormat", "hideCountdownLayout", "initAiVoice", "initView", "saveInstanceState", "Landroid/os/Bundle;", "isAdVideo", "isBusyState", "jumpAndFinish", "i", "Landroid/content/Intent;", "layoutId", "onDestroy", "onPause", "onPauseFeatureChange", "type", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "refreshCountdown", "release", "releaseDataAndFinish", "releaseStayPointRes", "setCountdown", "time", "showAdver", "showCountdownLayout", "showCruiseEvent", "showOnArrivalStatus", "showOnPauseStatus", "showOnTheWayStatus", "showPauseUi", "showTimeLeft", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "unbindPresenter", "Companion", "CountDownHandler", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class CruiseActivity extends MyBaseVmActivity<CruiseVm> implements CruiseContract.ViewInterface, AutoResumeCountDownContract.ViewInterface {
    public static final String CRUISE_ID_KEY = "CRUISE_ID_KEY";
    public static final int START_TIME = 254;
    public static final int START_TIME_DELAY = 255;
    public static final String STEADY_MODE_KEY = "STEADY_MODE_KEY";
    public static final String STOP_KEY = "STOP_KEY";
    private final int TYPE_PAUSE_FEATURE_AIVOICE;
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private final int TYPE_PAUSE_FEATURE_TOUCH;
    private HashMap _$_findViewCache;
    private final VoiceTask arrivalVoiceTask;

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter;
    private int currentCruiseId;
    private CruiseContract.ViewEvent currentEventStatus;
    private int currentPauseFeature;
    private final CruiseActivity$functionButton$1 functionButton;

    /* renamed from: hasCopyStayPointView$delegate, reason: from kotlin metadata */
    private final Lazy hasCopyStayPointView;
    private boolean isCruiseLayout;
    private boolean isFirstRepo;
    private boolean isFirstStart;
    private boolean isNeedStopDotVoice;
    private boolean isOnErrorDialog;
    private boolean isRelease;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private VoiceTask locationLostVoiceTask;

    /* renamed from: mAdVm$delegate, reason: from kotlin metadata */
    private final Lazy mAdVm;
    private AdverBaseFragment<?> mAdverBaseFragment;
    private int mAdverType;
    private CountDownHandler mContDownHandler;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private final MotionEventHelper motionEventHelper;

    /* renamed from: movingLoopVoiceTask$delegate, reason: from kotlin metadata */
    private final Lazy movingLoopVoiceTask;

    /* renamed from: noCopyStayPointView$delegate, reason: from kotlin metadata */
    private final Lazy noCopyStayPointView;
    private final OnLazyVoiceClickListener onCancelCruiseClick;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceAnimationViewClick;
    private Function0<Unit> onFallDropCallBack;
    private Function0<Unit> onLostLocationLostCallback;
    private final OnLazyClickListener onPauseLayoutClick;
    private final Function1<Boolean, Unit> onTouchSensorAnimationListener;
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private final Function1<PlayEvent, Unit> onVoiceStateListener;
    private final VoiceTask pauseVoiceTask;
    private final Function1<ScheduleContract.TriggerEvent, Unit> scheduleEventLister;
    private final CruiseActivity$singleBatteryListener$1 singleBatteryListener;

    /* renamed from: stayPointCopyWrite$delegate, reason: from kotlin metadata */
    private final Lazy stayPointCopyWrite;

    /* renamed from: stayPointVoiceTask$delegate, reason: from kotlin metadata */
    private final Lazy stayPointVoiceTask;
    private final Function1<AudioTrackUtils.AudioPlayEvent, Unit> ttsPlayListener;
    private VoiceInteractionDialog voiceInteractionDialog;
    private final Function1<WakeupInfo, Unit> wakeupListener;
    private final String TAG = getClass().getSimpleName();

    /* renamed from: callSetVM$delegate, reason: from kotlin metadata */
    private final Lazy callSetVM = LazyKt.lazy(new Function0<CallSettingVM>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$callSetVM$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CallSettingVM invoke() {
            CruiseActivity cruiseActivity = CruiseActivity.this;
            ViewModel viewModel = new ViewModelProvider(cruiseActivity, new ViewModelProvider.AndroidViewModelFactory(cruiseActivity.getApplication())).get(CallSettingVM.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
            return (CallSettingVM) ((BaseViewModel) viewModel);
        }
    });
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();

    /* renamed from: cruisePresenter$delegate, reason: from kotlin metadata */
    private final Lazy cruisePresenter = LazyKt.lazy(new Function0<CruisePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$cruisePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CruisePresenter invoke() {
            CruisePresenter cruisePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(CruisePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(CruisePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                cruisePresenter = new CruisePresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(CruisePresenter.class).toString(), cruisePresenter);
            } else {
                if (!(basePresenterInterface instanceof CruisePresenter)) {
                    basePresenterInterface = null;
                }
                cruisePresenter = (CruisePresenter) basePresenterInterface;
            }
            if (cruisePresenter != null) {
                return cruisePresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter");
        }
    });
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CruiseContract.ViewEvent.values().length];

        static {
            $EnumSwitchMapping$0[CruiseContract.ViewEvent.EXIT.ordinal()] = 1;
            $EnumSwitchMapping$0[CruiseContract.ViewEvent.ACTIVE.ordinal()] = 2;
            $EnumSwitchMapping$0[CruiseContract.ViewEvent.PAUSE.ordinal()] = 3;
            $EnumSwitchMapping$0[CruiseContract.ViewEvent.ARRIVAL_STOP_DOT.ordinal()] = 4;
        }
    }

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    private final CallSettingVM getCallSetVM() {
        return (CallSettingVM) this.callSetVM.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CruisePresenter getCruisePresenter() {
        return (CruisePresenter) this.cruisePresenter.getValue();
    }

    private final View getHasCopyStayPointView() {
        return (View) this.hasCopyStayPointView.getValue();
    }

    private final AdVm getMAdVm() {
        return (AdVm) this.mAdVm.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
    }

    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper getMovingLoopVoiceTask() {
        return (TtsVoiceWrapperPlayer.VoiceTaskWrapper) this.movingLoopVoiceTask.getValue();
    }

    private final View getNoCopyStayPointView() {
        return (View) this.noCopyStayPointView.getValue();
    }

    private final String getStayPointCopyWrite() {
        return (String) this.stayPointCopyWrite.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TtsVoiceWrapperPlayer.VoiceTaskWrapper getStayPointVoiceTask() {
        return (TtsVoiceWrapperPlayer.VoiceTaskWrapper) this.stayPointVoiceTask.getValue();
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

    /* JADX WARN: Type inference failed for: r2v3, types: [com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$functionButton$1] */
    public CruiseActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$motionEventHelper$1$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PeripheralsSceneUtil.INSTANCE.showCruiseOnTheWay();
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$autoResumeCountDownPresenter$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AutoResumeCountDownPresenter invoke() {
                AutoResumeCountDownPresenter autoResumeCountDownPresenter;
                PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
                BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class).toString());
                Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class) + ' ' + basePresenterInterface);
                if (basePresenterInterface == null) {
                    autoResumeCountDownPresenter = new AutoResumeCountDownPresenter();
                    presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(AutoResumeCountDownPresenter.class).toString(), autoResumeCountDownPresenter);
                } else {
                    if (!(basePresenterInterface instanceof AutoResumeCountDownPresenter)) {
                        basePresenterInterface = null;
                    }
                    autoResumeCountDownPresenter = (AutoResumeCountDownPresenter) basePresenterInterface;
                }
                if (autoResumeCountDownPresenter != null) {
                    return autoResumeCountDownPresenter;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter");
            }
        });
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.isFirstStart = true;
        this.currentCruiseId = -1;
        this.isFirstRepo = true;
        this.mAdVm = LazyKt.lazy(new Function0<AdVm>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$mAdVm$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AdVm invoke() {
                CruiseActivity cruiseActivity = CruiseActivity.this;
                ViewModel viewModel = new ViewModelProvider(cruiseActivity, new ViewModelProvider.AndroidViewModelFactory(cruiseActivity.getApplication())).get(AdVm.class);
                Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
                return (AdVm) ((BaseViewModel) viewModel);
            }
        });
        this.functionButton = new FunctionButtonListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$functionButton$1
            @Override // com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener
            public void onClick() {
            }
        };
        this.mContDownHandler = new CountDownHandler(this);
        this.stayPointCopyWrite = LazyKt.lazy(new Function0<String>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$stayPointCopyWrite$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return SpUtils.get(CruiseActivity.this, Constans.KEY_CRUISE_STAY_COPY_WRITE_TYPE, "");
            }
        });
        this.movingLoopVoiceTask = LazyKt.lazy(new Function0<TtsVoiceWrapperPlayer.VoiceTaskWrapper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$movingLoopVoiceTask$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TtsVoiceWrapperPlayer.VoiceTaskWrapper invoke() {
                return new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(1L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(Long.valueOf(TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_TYPE) * 1000), VoiceItem.voice19_1)}), TtsVoiceWrapperPlayer.PlayType.CRUISE, null, 4, null);
            }
        });
        this.stayPointVoiceTask = LazyKt.lazy(new Function0<TtsVoiceWrapperPlayer.VoiceTaskWrapper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$stayPointVoiceTask$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TtsVoiceWrapperPlayer.VoiceTaskWrapper invoke() {
                return new TtsVoiceWrapperPlayer.VoiceTaskWrapper(null, TtsVoiceWrapperPlayer.PlayType.CRUISE_STAY_POINT, null, 4, null);
            }
        });
        this.pauseVoiceTask = new VoiceTask(-1L, VoiceItem.voice7_2);
        this.arrivalVoiceTask = new VoiceTask(-1L, VoiceItem.voice51_1);
        this.locationLostVoiceTask = new VoiceTask(-1L, VoiceItem.voice17_1);
        this.currentEventStatus = CruiseContract.ViewEvent.ACTIVE;
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onErrorDialogShowStatus$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Boolean bool2) {
                invoke(bool.booleanValue(), bool2.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, boolean z2) {
                String str;
                AdverBaseFragment adverBaseFragment;
                int i;
                if (z) {
                    CruiseActivity cruiseActivity = CruiseActivity.this;
                    i = cruiseActivity.TYPE_PAUSE_FEATURE_ERROR;
                    cruiseActivity.onPauseFeatureChange(i);
                    PeripheralsSceneUtil.INSTANCE.showRunError();
                    if (z2) {
                        CruiseTrack.INSTANCE.onEmergencyStop();
                    }
                }
                if (z) {
                    CruiseActivity.adverPause$default(CruiseActivity.this, false, 1, null);
                    CruiseActivity.this.setOnErrorDialog(true);
                    adverBaseFragment = CruiseActivity.this.mAdverBaseFragment;
                    if (adverBaseFragment != null) {
                        adverBaseFragment.showOutsideAdScene(AdSceneConfig.CRUISE_MODE_PAUSE);
                    }
                }
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorDialogShowStatus() show = " + z);
            }
        };
        this.wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$wakeupListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(WakeupInfo wakeupInfo) {
                invoke2(wakeupInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(WakeupInfo wakeupInfo) {
                VoiceInteractionDialog voiceInteractionDialog;
                VoiceInteractionDialog voiceInteractionDialog2;
                String str;
                CruiseContract.ViewEvent viewEvent;
                CruiseContract.ViewEvent viewEvent2;
                int i;
                VoiceInteractionDialog voiceInteractionDialog3;
                CruiseTrack.INSTANCE.onWakeup();
                voiceInteractionDialog = CruiseActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog == null) {
                    CruiseActivity cruiseActivity = CruiseActivity.this;
                    cruiseActivity.voiceInteractionDialog = new VoiceInteractionDialog(cruiseActivity);
                    voiceInteractionDialog3 = CruiseActivity.this.voiceInteractionDialog;
                    if (voiceInteractionDialog3 != null) {
                        voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$wakeupListener$1.1
                            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                            public void onDismiss() {
                                String str2;
                                CruiseContract.ViewEvent viewEvent3;
                                CruiseContract.ViewEvent viewEvent4;
                                int i2;
                                str2 = CruiseActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("wakeupListener onDismiss ");
                                viewEvent3 = CruiseActivity.this.currentEventStatus;
                                sb.append(viewEvent3);
                                Pdlog.m3273d(str2, sb.toString());
                                viewEvent4 = CruiseActivity.this.currentEventStatus;
                                if (viewEvent4 == CruiseContract.ViewEvent.PAUSE) {
                                    CruiseActivity cruiseActivity2 = CruiseActivity.this;
                                    i2 = CruiseActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                                    cruiseActivity2.onPauseFeatureChange(i2);
                                }
                                CruiseActivity.this.adverResume();
                            }
                        });
                    }
                }
                voiceInteractionDialog2 = CruiseActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                    return;
                }
                voiceInteractionDialog2.show();
                str = CruiseActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("wakeupListener show ");
                viewEvent = CruiseActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                viewEvent2 = CruiseActivity.this.currentEventStatus;
                if (viewEvent2 == CruiseContract.ViewEvent.PAUSE) {
                    CruiseActivity cruiseActivity2 = CruiseActivity.this;
                    i = cruiseActivity2.TYPE_PAUSE_FEATURE_AIVOICE;
                    cruiseActivity2.onPauseFeatureChange(i);
                }
                CruiseActivity.adverPause$default(CruiseActivity.this, false, 1, null);
            }
        };
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onFallDropCallBack$1
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
                CruiseActivity.this.releaseDataAndFinish();
            }
        };
        this.scheduleEventLister = new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$scheduleEventLister$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == ScheduleContract.TriggerEvent.AVOID) {
                    CruiseTrack.INSTANCE.onStartScheduling();
                    if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                        CruiseActivity.this.adverPause(true);
                        return;
                    }
                    return;
                }
                if (it == ScheduleContract.TriggerEvent.NORMAL) {
                    CruiseTrack.INSTANCE.onStopScheduling();
                    if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                        CruiseActivity.this.adverResume();
                    }
                }
            }
        };
        this.noCopyStayPointView = LazyKt.lazy(new Function0<View>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$noCopyStayPointView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return ((ViewStub) CruiseActivity.this.findViewById(C4188R.id.noCopyView)).inflate();
            }
        });
        this.hasCopyStayPointView = LazyKt.lazy(new Function0<View>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$hasCopyStayPointView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return ((ViewStub) CruiseActivity.this.findViewById(C4188R.id.hasCopyView)).inflate();
            }
        });
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onLostLocationLostCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                String str2;
                VoiceTask voiceTask;
                int i;
                LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                    str2 = CruiseActivity.this.TAG;
                    Pdlog.m3273d(str2, "onLostLocationLostCallback");
                    if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                        CruiseActivity.this.adverPause(true);
                    }
                    ((FaceVideoView) CruiseActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    voiceTask = CruiseActivity.this.locationLostVoiceTask;
                    voicePlayer.play(voiceTask);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    CruiseActivity cruiseActivity = CruiseActivity.this;
                    i = cruiseActivity.TYPE_PAUSE_FEATURE_ERROR;
                    cruiseActivity.onPauseFeatureChange(i);
                    return;
                }
                if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                    CruiseTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                    CruiseActivity.this.getCruisePresenter().actionCancelTask();
                    CruiseActivity cruiseActivity2 = CruiseActivity.this;
                    cruiseActivity2.jumpAndFinish(new Intent(cruiseActivity2, (Class<?>) LaserRunningLocationLostActivity.class));
                }
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onErrorClearCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) CruiseActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) CruiseActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                        CruiseActivity.this.getCruisePresenter().actionActive();
                        CruiseActivity.this.adverResume();
                    } else {
                        CruiseActivity.this.getCruisePresenter().actionPause();
                    }
                }
            }
        };
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$locationLostTouchCancelCallback$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                int i;
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                ((FaceVideoView) CruiseActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                    CruiseActivity.this.adverResume();
                }
                CruiseActivity cruiseActivity = CruiseActivity.this;
                i = cruiseActivity.TYPE_PAUSE_FEATURE_ERROR;
                cruiseActivity.onPauseFeatureChange(i);
            }
        };
        this.onTouchSensorAnimationListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onTouchSensorAnimationListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public final void invoke(boolean z) {
                String str;
                CruiseContract.ViewEvent viewEvent;
                CruiseContract.ViewEvent viewEvent2;
                int i;
                int i2;
                str = CruiseActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onTouchSensorAnimationListener ");
                sb.append(z);
                sb.append(" , current status = ");
                viewEvent = CruiseActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                    if (z) {
                        CruiseActivity.this.adverPause(true);
                    } else {
                        CruiseActivity.this.adverResume();
                    }
                }
                viewEvent2 = CruiseActivity.this.currentEventStatus;
                if (viewEvent2 == CruiseContract.ViewEvent.PAUSE) {
                    if (z) {
                        CruiseActivity cruiseActivity = CruiseActivity.this;
                        i2 = cruiseActivity.TYPE_PAUSE_FEATURE_TOUCH;
                        cruiseActivity.onPauseFeatureChange(i2);
                    } else {
                        CruiseActivity cruiseActivity2 = CruiseActivity.this;
                        i = cruiseActivity2.TYPE_PAUSE_FEATURE_NORMAL;
                        cruiseActivity2.onPauseFeatureChange(i);
                    }
                }
            }
        };
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                CruiseTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.ttsPlayListener = new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$ttsPlayListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                invoke2(audioPlayEvent);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
            
                r2 = r1.this$0.mAdverBaseFragment;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                AdverBaseFragment adverBaseFragment;
                AdverBaseFragment adverBaseFragment2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                    if (it == AudioTrackUtils.AudioPlayEvent.PLAYING) {
                        adverBaseFragment2 = CruiseActivity.this.mAdverBaseFragment;
                        if (adverBaseFragment2 != null) {
                            adverBaseFragment2.setVolume(0.3f);
                            return;
                        }
                        return;
                    }
                    if ((it == AudioTrackUtils.AudioPlayEvent.COMPLETE || it == AudioTrackUtils.AudioPlayEvent.STOP) && adverBaseFragment != null) {
                        adverBaseFragment.setVolume(1.0f);
                    }
                }
            }
        };
        this.onVoiceStateListener = new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onVoiceStateListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent) {
                invoke2(playEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PlayEvent it) {
                String str;
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                boolean z;
                String str2;
                CruiseContract.ViewEvent viewEvent;
                CruiseContract.ViewEvent viewEvent2;
                boolean isAdVideo;
                MotionEventHelper motionEventHelper2;
                String str3;
                TtsVoiceWrapperPlayer.VoiceTaskWrapper stayPointVoiceTask;
                Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> function1;
                String str4;
                CruiseContract.ViewEvent viewEvent3;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "onVoiceStateListener it: " + it);
                mDangerousAreaTipHelper = CruiseActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
                if (it == PlayEvent.COMPLETION_ONCE || it == PlayEvent.STOP) {
                    z = CruiseActivity.this.isNeedStopDotVoice;
                    if (!z) {
                        str2 = CruiseActivity.this.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("onVoiceStateListener 不是逗留状态，无需播报语音 currentEventStatus = ");
                        viewEvent = CruiseActivity.this.currentEventStatus;
                        sb.append(viewEvent);
                        Pdlog.m3273d(str2, sb.toString());
                    } else {
                        TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                        Context applicationContext = CruiseActivity.this.getApplicationContext();
                        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                        if (ttsVoiceHelper.isOpen(applicationContext, TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE)) {
                            str3 = CruiseActivity.this.TAG;
                            Pdlog.m3275i(str3, "onVoiceStateListener interval Time : " + TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE));
                            TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer = TtsVoiceWrapperPlayer.INSTANCE;
                            stayPointVoiceTask = CruiseActivity.this.getStayPointVoiceTask();
                            long timeInterval = ((long) TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE)) * 1000;
                            function1 = CruiseActivity.this.ttsPlayListener;
                            ttsVoiceWrapperPlayer.playCruiseStayPointVoice(stayPointVoiceTask, timeInterval, function1);
                            str4 = CruiseActivity.this.TAG;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("onVoiceStateListener 需要报语音 currentEventStatus = ");
                            viewEvent3 = CruiseActivity.this.currentEventStatus;
                            sb2.append(viewEvent3);
                            Pdlog.m3273d(str4, sb2.toString());
                        }
                    }
                    viewEvent2 = CruiseActivity.this.currentEventStatus;
                    if (viewEvent2 == CruiseContract.ViewEvent.ACTIVE) {
                        isAdVideo = CruiseActivity.this.isAdVideo();
                        if (isAdVideo) {
                            return;
                        }
                        motionEventHelper2 = CruiseActivity.this.motionEventHelper;
                        motionEventHelper2.playMovingVoice();
                    }
                }
            }
        };
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onFaceAnimationViewClick$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                CruiseContract.ViewEvent viewEvent;
                CruiseContract.ViewEvent viewEvent2;
                CruiseActivity.CountDownHandler countDownHandler;
                str = CruiseActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                viewEvent = CruiseActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                    return;
                }
                viewEvent2 = CruiseActivity.this.currentEventStatus;
                if (viewEvent2 == CruiseContract.ViewEvent.ACTIVE) {
                    countDownHandler = CruiseActivity.this.mContDownHandler;
                    if (countDownHandler != null) {
                        countDownHandler.removeCallbacksAndMessages(null);
                    }
                    CruiseActivity.this.getCruisePresenter().actionPause();
                }
            }
        }, 3, null);
        this.onPauseLayoutClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$onPauseLayoutClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                CruiseContract.ViewEvent viewEvent;
                boolean z;
                String str2;
                str = CruiseActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onPauseLayoutClick current status = ");
                viewEvent = CruiseActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                z = CruiseActivity.this.isRelease;
                if (z) {
                    str2 = CruiseActivity.this.TAG;
                    Pdlog.m3274e(str2, "goToCruise failed isRelease ");
                } else {
                    CruiseActivity.this.getCruisePresenter().actionActive();
                }
            }
        };
        this.onCancelCruiseClick = new CruiseActivity$onCancelCruiseClick$1(this);
        this.mAdverType = -1;
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_AIVOICE = 2;
        this.TYPE_PAUSE_FEATURE_TOUCH = 3;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
        this.singleBatteryListener = new CruiseActivity$singleBatteryListener$1(this);
    }

    /* renamed from: isOnErrorDialog, reason: from getter */
    public final boolean getIsOnErrorDialog() {
        return this.isOnErrorDialog;
    }

    public final void setOnErrorDialog(boolean z) {
        this.isOnErrorDialog = z;
    }

    public final Function0<Unit> getOnFallDropCallBack() {
        return this.onFallDropCallBack;
    }

    public final void setOnFallDropCallBack(Function0<Unit> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "<set-?>");
        this.onFallDropCallBack = function0;
    }

    /* compiled from: CruiseActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity$CountDownHandler;", "Landroid/os/Handler;", "activity", "Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity;", "(Lcom/pudutech/bumblebee/robot_ui/ui/CruiseActivity;)V", "count", "", "mActivityReference", "Ljava/lang/ref/WeakReference;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class CountDownHandler extends Handler {
        private long count;
        private final WeakReference<CruiseActivity> mActivityReference;

        public CountDownHandler(CruiseActivity activity) {
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            this.mActivityReference = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CruisePresenter cruisePresenter;
            CruisePresenter cruisePresenter2;
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            int i = msg.what;
            if (i == 254) {
                this.count = CruiseConfig.INSTANCE.getDelayAutoFinish_ms() / 1000;
                if (this.count == 0) {
                    CruiseTrack.INSTANCE.onFinishOne("", BaseMoveTrackTask.FinishOneCause.CountDown);
                    CruiseActivity cruiseActivity = this.mActivityReference.get();
                    if (cruiseActivity == null || (cruisePresenter = cruiseActivity.getCruisePresenter()) == null) {
                        return;
                    }
                    cruisePresenter.actionActive();
                    return;
                }
                CruiseActivity cruiseActivity2 = this.mActivityReference.get();
                if (cruiseActivity2 != null) {
                    cruiseActivity2.refreshCountdown(this.count);
                }
                sendEmptyMessageDelayed(255, 1000L);
                return;
            }
            if (i != 255) {
                return;
            }
            this.count--;
            long j = this.count;
            CruiseActivity cruiseActivity3 = this.mActivityReference.get();
            if (cruiseActivity3 != null) {
                cruiseActivity3.refreshCountdown(this.count);
            }
            if (this.count == 0) {
                CruiseTrack.INSTANCE.onFinishOne("", BaseMoveTrackTask.FinishOneCause.CountDown);
                if (cruiseActivity3 == null || (cruisePresenter2 = cruiseActivity3.getCruisePresenter()) == null) {
                    return;
                }
                cruisePresenter2.actionActive();
                return;
            }
            sendEmptyMessageDelayed(255, 1000L);
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity
    public boolean isBusyState() {
        Boolean value = getCallSetVM().getCruiseCanCallSwitchLD().getValue();
        return value == null || !value.booleanValue();
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public int layoutId() {
        return C4188R.layout.activity_cruise;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void initView(Bundle saveInstanceState) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new CruiseActivity$initView$1(null), 2, null);
        ((CruiseVm) getMViewModel()).getMAdverVm().reqCacheAdList(AdSceneConfig.CRUISE_MODE);
        Pdlog.m3273d(this.TAG, "onCreate");
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
        this.currentCruiseId = getIntent().getIntExtra("CRUISE_ID_KEY", -1);
        boolean booleanExtra = getIntent().getBooleanExtra(STEADY_MODE_KEY, false);
        ArrayList<String> stops = getIntent().getStringArrayListExtra(STOP_KEY);
        initAiVoice();
        bindPresenter();
        Pdlog.m3274e(this.TAG, "onCreate currentCruiseId is " + this.currentCruiseId);
        CruisePresenter cruisePresenter = getCruisePresenter();
        int i = this.currentCruiseId;
        MovePerformance movePerformance = booleanExtra ? MovePerformance.STEADY : MovePerformance.NORMAL;
        Intrinsics.checkExpressionValueIsNotNull(stops, "stops");
        cruisePresenter.actionCruise(i, movePerformance, stops);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout)).setOnClickListener(this.onPauseLayoutClick);
        ((LinearLayout) _$_findCachedViewById(C4188R.id.cancel_back_task)).setOnClickListener(this.onCancelCruiseClick);
        ((TextView) _$_findCachedViewById(C4188R.id.tvFinishStayPoint)).setOnClickListener(new SingleVoiceClickListener(null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$initView$2
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
                CruiseActivity.CountDownHandler countDownHandler;
                Intrinsics.checkParameterIsNotNull(it, "it");
                CruiseActivity.this.getCruisePresenter().actionActive();
                countDownHandler = CruiseActivity.this.mContDownHandler;
                if (countDownHandler != null) {
                    countDownHandler.removeCallbacksAndMessages(null);
                }
            }
        }, 3, null));
        showOnTheWayStatus();
        if (this.currentCruiseId < 0) {
            Pdlog.m3274e(this.TAG, "onCreate currentCruiseId is -1 ???????");
            jumpAndFinish(new Intent(this, (Class<?>) CruiseSelectActivity.class));
            return;
        }
        setCountdown((int) (BusinessSetting.INSTANCE.getCruisePauseKeepTime_ms() / 1000));
        PeripheralsSceneUtil.INSTANCE.setPlayMode(PeripheralsSceneUtil.Mode.Cruise);
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity
    public void createObserver() {
        CruiseActivity cruiseActivity = this;
        ((CruiseVm) getMViewModel()).getMAdverVm().get_adverDataLd().observe(cruiseActivity, new CruiseActivity$createObserver$$inlined$observe$1(this));
        getMAdVm().controlAdPlay(true);
        ((CruiseVm) getMViewModel()).getAutoReturnState().observe(cruiseActivity, (Observer) new Observer<T>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$createObserver$$inlined$observe$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                String str;
                CruiseContract.ViewEvent viewEvent;
                CruiseContract.ViewEvent viewEvent2;
                boolean booleanValue = ((Boolean) t).booleanValue();
                str = CruiseActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("createObserver autoReturnState = ");
                sb.append(booleanValue);
                sb.append(", currentEventStatus = ");
                viewEvent = CruiseActivity.this.currentEventStatus;
                sb.append(viewEvent);
                Pdlog.m3273d(str, sb.toString());
                if (booleanValue) {
                    viewEvent2 = CruiseActivity.this.currentEventStatus;
                    if (viewEvent2 == CruiseContract.ViewEvent.ACTIVE) {
                        CruiseActivity.this.finishCruise();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishCruise() {
        Intent intent;
        ReturnPointBean returnPointBean = (ReturnPointBean) null;
        if (Constans.INSTANCE.getCruiseReturnDestination().length() > 0) {
            try {
                returnPointBean = (ReturnPointBean) GsonSingleton.INSTANCE.getINSTANCE().getMGson().fromJson(Constans.INSTANCE.getCruiseReturnDestination(), new TypeToken<ReturnPointBean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$finishCruise$1
                }.getType());
            } catch (Exception e) {
                Pdlog.m3274e(this.TAG, "initCruiseReturnRecyclerView returnPoint from json: " + e);
            }
        }
        if (Intrinsics.areEqual(returnPointBean != null ? returnPointBean.getMapName() : null, RobotMapManager.INSTANCE.getDefaultPdmap())) {
            String pointName = returnPointBean.getPointName();
            if (Intrinsics.areEqual(pointName, RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen())) {
                intent = new Intent(this, (Class<?>) TurnBackActivity.class);
            } else if (Intrinsics.areEqual(pointName, RobotMapManager.INSTANCE.getCurrentMapUsherChosen())) {
                intent = new Intent(this, (Class<?>) GotoWelcomeAreaActivity.class);
            } else {
                intent = new Intent(this, (Class<?>) TurnBackActivity.class);
            }
        } else {
            intent = new Intent(this, (Class<?>) TurnBackActivity.class);
        }
        jumpAndFinish(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isAdVideo() {
        boolean z = this.mAdverType == 1 || ((CruiseVm) getMViewModel()).getMAdverVm().getAdverType(AdSceneConfig.CRUISE_MODE) == 1;
        Pdlog.m3273d(this.TAG, "isAdVideo() result =" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void adverPause$default(CruiseActivity cruiseActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        cruiseActivity.adverPause(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adverPause(boolean isNeedGoneVideo) {
        getMAdVm().controlAdPlay(false);
        getMAdVm().controlAdShow(!isNeedGoneVideo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adverResume() {
        getMAdVm().controlAdPlay(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAdver() {
        getGeneralPresenter().setNoNeedLcdClick(true);
        this.motionEventHelper.setOnTheWayAnimation((FaceVideoAnimation) null);
        FrameLayout cruise_no_ad = (FrameLayout) _$_findCachedViewById(C4188R.id.cruise_no_ad);
        Intrinsics.checkExpressionValueIsNotNull(cruise_no_ad, "cruise_no_ad");
        ViewExtKt.gone(cruise_no_ad);
        FrameLayout adver_contain = (FrameLayout) _$_findCachedViewById(C4188R.id.adver_contain);
        Intrinsics.checkExpressionValueIsNotNull(adver_contain, "adver_contain");
        ViewExtKt.show(adver_contain);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        if (isAdVideo()) {
            this.motionEventHelper.stopMovingVoice();
            MusicPlayerHelper.getInstance().stopPlay();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            getCruisePresenter().actionPause();
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            getCruisePresenter().actionPauseNoTimer();
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
            getCruisePresenter().actionPauseNoTimer();
            this.touchSensorEventHelper.stopCurrentAnimation();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else if (type == this.TYPE_PAUSE_FEATURE_TOUCH) {
            getCruisePresenter().actionPauseNoTimer();
        } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            hideCountdownLayout();
        }
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        releaseStayPointRes();
        startActivity(i);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        CruisePresenter cruisePresenter = getCruisePresenter();
        if (cruisePresenter != null) {
            cruisePresenter.actionPauseNoTimer();
        }
        CruisePresenter cruisePresenter2 = getCruisePresenter();
        if (cruisePresenter2 != null) {
            cruisePresenter2.actionCancelTask();
        }
        release();
        releaseStayPointRes();
        finish();
    }

    private final void showOnPauseStatus() {
        Pdlog.m3273d(this.TAG, "showOnPauseStatus");
        if (isCruiseLayout()) {
            RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
            Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
            if (pause_layout.getVisibility() == 8) {
                RelativeLayout pause_layout2 = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
                Intrinsics.checkExpressionValueIsNotNull(pause_layout2, "pause_layout");
                pause_layout2.setVisibility(0);
            }
            RelativeLayout customStayPointContainer = (RelativeLayout) _$_findCachedViewById(C4188R.id.customStayPointContainer);
            Intrinsics.checkExpressionValueIsNotNull(customStayPointContainer, "customStayPointContainer");
            customStayPointContainer.setVisibility(8);
        }
        AdverBaseFragment<?> adverBaseFragment = this.mAdverBaseFragment;
        if (adverBaseFragment != null) {
            adverBaseFragment.showOutsideAdScene(AdSceneConfig.CRUISE_MODE_PAUSE);
        }
        showCountdownLayout();
        this.currentEventStatus = CruiseContract.ViewEvent.PAUSE;
        this.motionEventHelper.setCanHandleMovingEvent(false);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        Pdlog.m3273d(this.TAG, "showOnPauseStatus:" + Constans.INSTANCE.isOpenCruiseFace());
        if (this.runningErrorHelper.isErrorShowing()) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            hideCountdownLayout();
        } else {
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        }
        PeripheralsSceneUtil.INSTANCE.showCruisePause();
        MusicPlayerHelper.getInstance().pausePlay();
    }

    private final void showOnArrivalStatus() {
        Pdlog.m3273d(this.TAG, "showOnArrivalStatus ");
        showPauseUi();
        if (getStayPointCopyWrite().length() > 0) {
            View findViewById = getHasCopyStayPointView().findViewById(C4188R.id.tvStayPoint);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.tvStayPoint)");
            ((TextView) findViewById).setText(getStayPointCopyWrite());
        }
        CountDownHandler countDownHandler = this.mContDownHandler;
        if (countDownHandler != null) {
            countDownHandler.sendEmptyMessage(254);
        }
        AdverBaseFragment<?> adverBaseFragment = this.mAdverBaseFragment;
        if (adverBaseFragment != null) {
            adverBaseFragment.showOutsideAdScene(AdSceneConfig.CRUISE_MODE_STOP);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void refreshCountdown(long count) {
        if (((CruiseVm) getMViewModel()).getMAdverVm().isCruiseAdver()) {
            Object[] adverTimeFormat = getAdverTimeFormat(count, true);
            AdverBaseFragment<?> adverBaseFragment = this.mAdverBaseFragment;
            if (adverBaseFragment != null) {
                Object obj = adverTimeFormat[0];
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                String str = (String) obj;
                Object obj2 = adverTimeFormat[1];
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                }
                adverBaseFragment.setContinueTaskTip(str, ((Boolean) obj2).booleanValue());
            }
            Pdlog.m3273d(this.TAG, "refreshCountdown() adverTitle =" + getTitle() + "  array = " + ArraysKt.toList(adverTimeFormat));
            return;
        }
        if (getStayPointCopyWrite().length() == 0) {
            getNoCopyStayPointView();
            TextView tvTimer = (TextView) _$_findCachedViewById(C4188R.id.tvTimer);
            Intrinsics.checkExpressionValueIsNotNull(tvTimer, "tvTimer");
            tvTimer.setText(getTimeFormat(count));
            return;
        }
        View findViewById = getHasCopyStayPointView().findViewById(C4188R.id.tvStayPoint);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById<TextView>(R.id.tvStayPoint)");
        ((TextView) findViewById).setText(getStayPointCopyWrite());
        TextView tvTimer2 = (TextView) _$_findCachedViewById(C4188R.id.tvTimer);
        Intrinsics.checkExpressionValueIsNotNull(tvTimer2, "tvTimer");
        tvTimer2.setText(getTimeFormat(count));
    }

    static /* synthetic */ Object[] getAdverTimeFormat$default(CruiseActivity cruiseActivity, long j, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cruiseActivity.getAdverTimeFormat(j, z);
    }

    private final Object[] getAdverTimeFormat(long count, boolean isJudgeMin) {
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        Object[] objArr = {"1", false};
        long j = 60;
        long j2 = count / j;
        long j3 = count % j;
        if (isJudgeMin && j2 < 1) {
            if (j3 < 10) {
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(j3);
                valueOf3 = sb.toString();
            } else {
                valueOf3 = Long.valueOf(j3);
            }
            objArr[0] = String.valueOf(valueOf3);
            objArr[1] = true;
            return objArr;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C4188R.string.stay_point_timer);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.stay_point_timer)");
        Object[] objArr2 = new Object[2];
        long j4 = 10;
        if (j2 < j4) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(j2);
            valueOf = sb2.toString();
        } else {
            valueOf = Long.valueOf(j2);
        }
        objArr2[0] = valueOf;
        if (j3 < j4) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append('0');
            sb3.append(j3);
            valueOf2 = sb3.toString();
        } else {
            valueOf2 = Long.valueOf(j3);
        }
        objArr2[1] = valueOf2;
        String format = String.format(string, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        objArr[0] = format;
        objArr[1] = false;
        return objArr;
    }

    private final String getTimeFormat(long count) {
        Object valueOf;
        Object valueOf2;
        long j = 60;
        long j2 = count / j;
        long j3 = count % j;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(C4188R.string.stay_point_timer);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.stay_point_timer)");
        Object[] objArr = new Object[2];
        long j4 = 10;
        if (j2 < j4) {
            StringBuilder sb = new StringBuilder();
            sb.append('0');
            sb.append(j2);
            valueOf = sb.toString();
        } else {
            valueOf = Long.valueOf(j2);
        }
        objArr[0] = valueOf;
        if (j3 < j4) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('0');
            sb2.append(j3);
            valueOf2 = sb2.toString();
        } else {
            valueOf2 = Long.valueOf(j3);
        }
        objArr[1] = valueOf2;
        String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return format;
    }

    private final void showPauseUi() {
        this.motionEventHelper.setCanHandleMovingEvent(false);
        if (isCruiseLayout()) {
            RelativeLayout pause_layout = (RelativeLayout) _$_findCachedViewById(C4188R.id.pause_layout);
            Intrinsics.checkExpressionValueIsNotNull(pause_layout, "pause_layout");
            pause_layout.setVisibility(8);
            RelativeLayout customStayPointContainer = (RelativeLayout) _$_findCachedViewById(C4188R.id.customStayPointContainer);
            Intrinsics.checkExpressionValueIsNotNull(customStayPointContainer, "customStayPointContainer");
            customStayPointContainer.setVisibility(0);
        }
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        Pdlog.m3273d(this.TAG, "showPauseUi：" + Constans.INSTANCE.isOpenCruiseFace());
        AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
        PeripheralsSceneUtil.INSTANCE.showCruisePause();
        MusicPlayerHelper.getInstance().pausePlay();
    }

    private final boolean isCruiseLayout() {
        FrameLayout frameLayout = (FrameLayout) _$_findCachedViewById(C4188R.id.cruise_no_ad);
        return frameLayout != null && frameLayout.isShown();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showOnTheWayStatus() {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        this.touchSensorEventHelper.setCanHandle(true, true);
        if (isAdVideo()) {
            TtsVoiceWrapperPlayer.INSTANCE.stop(getStayPointVoiceTask());
        }
        hideErrorTipDialog();
        AiVoiceManager.INSTANCE.stopAiRecording();
        if (isCruiseLayout()) {
            RelativeLayout customStayPointContainer = (RelativeLayout) _$_findCachedViewById(C4188R.id.customStayPointContainer);
            Intrinsics.checkExpressionValueIsNotNull(customStayPointContainer, "customStayPointContainer");
            customStayPointContainer.setVisibility(8);
        }
        if (((CruiseVm) getMViewModel()).getMAdverVm().isCruiseAdver()) {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
        } else {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isOpenCruiseFace()));
            FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
            String string = getString(C4188R.string.pdStr16_150);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr16_150)");
            faceVideoView.setTarget(string);
        }
        this.motionEventHelper.setCanHandleMovingEvent(true);
        PeripheralsSceneUtil.INSTANCE.showCruiseOnTheWay();
        if (!isAdVideo()) {
            MusicPlayerHelper.getInstance().startPlay(ModeEnum.CRUISE);
        }
        AdverBaseFragment<?> adverBaseFragment = this.mAdverBaseFragment;
        if (adverBaseFragment != null) {
            adverBaseFragment.setVolume(1.0f);
            adverBaseFragment.showOutsideAdScene(AdSceneConfig.CRUISE_MODE);
        }
    }

    @Override // com.pudutech.disinfect.baselib.base.activity.BaseVmActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    private final void release() {
        this.isRelease = true;
        MusicPlayerHelper.getInstance().release();
        VoiceInteractionDialog voiceInteractionDialog = this.voiceInteractionDialog;
        if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
            voiceInteractionDialog.dismiss();
        }
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        unbindPresenter();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        CruiseTrack cruiseTrack = CruiseTrack.INSTANCE;
        int intExtra = getIntent().getIntExtra("CRUISE_ID_KEY", -1);
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra(STOP_KEY);
        Intrinsics.checkExpressionValueIsNotNull(stringArrayListExtra, "intent.getStringArrayListExtra(STOP_KEY)");
        boolean booleanExtra = getIntent().getBooleanExtra(STEADY_MODE_KEY, false);
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@CruiseActivity::class.java.simpleName");
        cruiseTrack.onCreateTask(intExtra, stringArrayListExtra, booleanExtra, simpleName);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        CruiseActivity cruiseActivity = this;
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, cruiseActivity, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$bindPresenter$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                String str;
                if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                    CruiseActivity.this.adverResume();
                }
                CruiseActivity.this.getCruisePresenter().actionActive();
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "onTryRestart()");
            }
        });
        CruiseActivity cruiseActivity2 = this;
        getAutoResumeCountDownPresenter().replaceView(cruiseActivity2);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        this.touchSensorEventHelper.setOnVoiceStateListener(this.onVoiceStateListener);
        this.touchSensorEventHelper.setOnAnimationShowListener(this.onTouchSensorAnimationListener);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        getCruisePresenter().replaceView(cruiseActivity2);
        if (((CruiseVm) getMViewModel()).getMAdverVm().isCruiseAdver()) {
            this.motionEventHelper.setOnTheWayAnimation((FaceVideoAnimation) null);
            this.motionEventHelper.setCanPlayObstructedVideo(false);
        } else {
            this.motionEventHelper.setOnTheWayAnimation(SceneAnimationResources.INSTANCE.getOnTheWay(Constans.INSTANCE.isOpenCruiseFace()));
        }
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask = getMovingLoopVoiceTask();
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(movingLoopVoiceTask, face_animation_view2, this.scheduleEventLister);
        this.motionEventHelper.setCurrentMovingText(SpUtils.get(this, "key_lattice_cruise", ""));
        this.motionEventHelper.setOnVoicePlayListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$bindPresenter$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PlayEvent playEvent) {
                invoke2(playEvent);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
            
                r0 = r5.this$0.mAdverBaseFragment;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2(PlayEvent it) {
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                String str;
                AdverBaseFragment adverBaseFragment;
                AdverBaseFragment adverBaseFragment2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                mDangerousAreaTipHelper = CruiseActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
                if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                    if (it == PlayEvent.PLAYING) {
                        adverBaseFragment2 = CruiseActivity.this.mAdverBaseFragment;
                        if (adverBaseFragment2 != null) {
                            adverBaseFragment2.setVolume(0.3f);
                        }
                    } else if ((it == PlayEvent.STOP || it == PlayEvent.COMPLETION_ONCE) && adverBaseFragment != null) {
                        adverBaseFragment.setVolume(1.0f);
                    }
                }
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "onVoicePlayListener() " + it);
            }
        });
        this.motionEventHelper.setMoveVoicePlayingLedTask(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$bindPresenter$3
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
                str = CruiseActivity.this.TAG;
                Pdlog.m3273d(str, "moveVoicePlayingLedTask  " + z);
            }
        });
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
        getMDangerousAreaTipHelper().bindLifecycle(this);
        BeeperCallHelper beeperCallHelper = this.beeperCallHelper;
        Boolean value = getCallSetVM().getCruiseCanCallSwitchLD().getValue();
        if (value == null) {
            value = false;
        }
        BeeperCallHelper.bind$default(beeperCallHelper, cruiseActivity, value.booleanValue(), false, 4, null);
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        this.touchSensorEventHelper.unBindPresent();
        CruiseActivity cruiseActivity = this;
        getCruisePresenter().removeView(cruiseActivity);
        this.motionEventHelper.unBind();
        getAutoResumeCountDownPresenter().removeView(cruiseActivity);
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
        this.beeperCallHelper.unbind();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseContract.ViewInterface
    public void showCruiseEvent(CruiseContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3275i(this.TAG, "showCruiseEvent：" + event + " ; currentEventStatus = " + this.currentEventStatus);
        if (this.isFirstRepo) {
            this.isFirstRepo = false;
        }
        this.isNeedStopDotVoice = event == CruiseContract.ViewEvent.ARRIVAL_STOP_DOT;
        int i = WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, " Cruise：EXIT");
            CruiseTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            jumpAndFinish(new Intent(this, (Class<?>) CruiseSelectActivity.class));
        } else if (i == 2) {
            CruiseTrack.INSTANCE.onMove();
            Pdlog.m3273d(this.TAG, " Cruise：running");
            if (this.currentEventStatus != event) {
                showOnTheWayStatus();
                CountDownHandler countDownHandler = this.mContDownHandler;
                if (countDownHandler != null) {
                    countDownHandler.removeCallbacksAndMessages(null);
                }
            }
            if (Intrinsics.areEqual((Object) ((CruiseVm) getMViewModel()).getAutoReturnState().getValue(), (Object) true)) {
                finishCruise();
            }
        } else if (i == 3) {
            CruiseTrack.INSTANCE.onPause();
            Pdlog.m3273d(this.TAG, "Cruise：pausing");
            if (this.currentEventStatus != event) {
                showOnPauseStatus();
                VoicePlayer.INSTANCE.play(this.pauseVoiceTask);
            }
        } else if (i == 4) {
            CruiseTrack.INSTANCE.onArrive("");
            Pdlog.m3273d(this.TAG, "Cruise：ARRIVAL_STOP_DOT");
            if (this.currentEventStatus != event) {
                if (CruiseConfig.INSTANCE.getDelayAutoFinish_ms() / 1000 == 0) {
                    CruiseTrack.INSTANCE.onFinishOne("", BaseMoveTrackTask.FinishOneCause.Normal);
                    getCruisePresenter().actionActive();
                    return;
                }
                showOnArrivalStatus();
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                Context applicationContext = getApplicationContext();
                Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
                if (ttsVoiceHelper.isOpen(applicationContext, TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE)) {
                    Pdlog.m3275i(this.TAG, "interval Time : " + TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE));
                    TtsVoiceWrapperPlayer.INSTANCE.playCruiseStayPointVoice(getStayPointVoiceTask(), ((long) TtsVoiceHelper.INSTANCE.getTimeInterval(TtsVoiceHelper.TtsVoiceType.CRUISE_STAY_TYPE)) * 1000, this.ttsPlayListener);
                } else {
                    VoicePlayer.INSTANCE.play(this.arrivalVoiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.CruiseActivity$showCruiseEvent$1
                        /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
                        
                            r2 = r1.this$0.mAdverBaseFragment;
                         */
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                        */
                        public void onStateChange(PlayEvent event2) {
                            AdverBaseFragment adverBaseFragment;
                            AdverBaseFragment adverBaseFragment2;
                            Intrinsics.checkParameterIsNotNull(event2, "event");
                            if (((CruiseVm) CruiseActivity.this.getMViewModel()).getMAdverVm().isCruiseAdver()) {
                                if (event2 == PlayEvent.PLAYING) {
                                    adverBaseFragment2 = CruiseActivity.this.mAdverBaseFragment;
                                    if (adverBaseFragment2 != null) {
                                        adverBaseFragment2.setVolume(0.3f);
                                        return;
                                    }
                                    return;
                                }
                                if ((event2 == PlayEvent.COMPLETION_ONCE || event2 == PlayEvent.STOP) && adverBaseFragment != null) {
                                    adverBaseFragment.setVolume(1.0f);
                                }
                            }
                        }
                    }));
                }
            }
        }
        this.currentEventStatus = event;
        Pdlog.m3273d(this.TAG, "showCruiseEvent() end = " + this.currentEventStatus);
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.ViewInterface
    public void showTimeLeft(long time, AutoResumeCountDownContract.Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Pdlog.m3273d(this.TAG, "showTimeLeft " + time);
        setCountdown((int) time);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setCountdown(int time) {
        if (((CruiseVm) getMViewModel()).getMAdverVm().isCruiseAdver()) {
            Object[] adverTimeFormat = getAdverTimeFormat(time, true);
            AdverBaseFragment<?> adverBaseFragment = this.mAdverBaseFragment;
            if (adverBaseFragment != null) {
                Object obj = adverTimeFormat[0];
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                String str = (String) obj;
                Object obj2 = adverTimeFormat[1];
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                }
                adverBaseFragment.setContinueTaskTip(str, ((Boolean) obj2).booleanValue());
            }
            Pdlog.m3273d(this.TAG, "setCountdown() adverTitle =" + getTitle() + "  array =" + ArraysKt.toList(adverTimeFormat));
            return;
        }
        try {
            int sp2px = SizeUtils.sp2px(this, 24.0f);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr3_6);
            Intrinsics.checkExpressionValueIsNotNull(string, "this.getString(R.string.pdStr3_6)");
            Object[] objArr = new Object[1];
            objArr[0] = LanguageUtils.INSTANCE.isALaBo() ? String.valueOf(time) : Integer.valueOf(time);
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            int indexOf$default = StringsKt.indexOf$default((CharSequence) format, String.valueOf(time), 0, false, 6, (Object) null);
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(null, 0, sp2px, ColorStateList.valueOf(getColor(C4188R.color.theme_main_color)), null);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(textAppearanceSpan, indexOf$default, String.valueOf(time).length() + indexOf$default, 34);
            showCountdownLayout();
            TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            countdown_tv.setText(spannableStringBuilder);
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
        }
    }

    private final void hideCountdownLayout() {
        TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
        Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
        countdown_tv.setVisibility(4);
    }

    private final void showCountdownLayout() {
        if (isCruiseLayout()) {
            TextView countdown_tv = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
            Intrinsics.checkExpressionValueIsNotNull(countdown_tv, "countdown_tv");
            if (countdown_tv.getVisibility() != 0) {
                TextView countdown_tv2 = (TextView) _$_findCachedViewById(C4188R.id.countdown_tv);
                Intrinsics.checkExpressionValueIsNotNull(countdown_tv2, "countdown_tv");
                countdown_tv2.setVisibility(0);
            }
        }
    }

    private final void releaseStayPointRes() {
        TtsVoiceWrapperPlayer.INSTANCE.stop(getStayPointVoiceTask());
        CountDownHandler countDownHandler = this.mContDownHandler;
        if (countDownHandler != null) {
            countDownHandler.removeCallbacksAndMessages(null);
        }
        this.mContDownHandler = (CountDownHandler) null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseVmActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }
}
