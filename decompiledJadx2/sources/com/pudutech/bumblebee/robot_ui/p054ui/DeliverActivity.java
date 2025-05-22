package com.pudutech.bumblebee.robot_ui.p054ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.ims.utils.ShortUUID;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener;
import com.pudutech.bumblebee.business.protobuf.MessageProtobuf;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.delivery_task.DeliverSettingModel;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryMode;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.delivery_task.ViewModel;
import com.pudutech.bumblebee.presenter.delivery_task.remote_task.RemoteDeliveryContract;
import com.pudutech.bumblebee.presenter.delivery_task.remote_task.RemoteDeliveryPresenter;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract;
import com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CallFromType;
import com.pudutech.bumblebee.presenter.robot_open_task.interf.OnCallNaviStateChangeListener;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.Property;
import com.pudutech.bumblebee.presenter.robot_voices.TtsVoiceProperty;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayerHelper;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceProperty;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleContract;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.bean.PalletTtsScheme;
import com.pudutech.bumblebee.robot_ui.bean.ReturnPointBean;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.config.RemoteTaskState;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.ImsPresenterHolder;
import com.pudutech.bumblebee.robot_ui.manager.ImsTaskObjHolder;
import com.pudutech.bumblebee.robot_ui.manager.LoRaManager;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.module.robot_user_interaction_animation.p052ui.LaserRunningLocationLostActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableBindItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.TableWatchBindItem;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.VoiceType;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.DeliveryPalletAlarmDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.SelectOutletDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.TransferDishesDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.greeter.GotoWelcomeAreaActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BluetoothHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CallHistoryManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CheckLocationHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.DangerousAreaTipHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.MotionEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.RunningErrorHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceManager;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceWrapperPlayer;
import com.pudutech.bumblebee.robot_ui.p054ui.view.DeliverArriveLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.DeliverPauseLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.LotteryResultLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoAnimation;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.repo.CallSettingRepo;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.DeliveryTrack;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.SingleClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VerticalAlignTextSpan;
import com.pudutech.bumblebee.robot_ui.ui_utils.ViewExtKt;
import com.pudutech.bumblebee.robot_ui.util.BeeperUtils;
import com.pudutech.bumblebee.robot_ui.util.CountdownUtil;
import com.pudutech.bumblebee.robot_ui.util.LotteryUtil;
import com.pudutech.bumblebee.robot_ui.util.PalletTaskUtil;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.CallSettingVM;
import com.pudutech.disinfect.baselib.base.viewmodel.BaseViewModel;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdk.aidl.serialize.LocateCase;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpcomponent.Music;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.bean.ModeEnum;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.resources.voice.VoiceUtils;
import com.pudutech.robot.module.report.track2.BaseMoveTrackTask;
import com.pudutech.robot.module.report.track2.TrackType;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DeliverActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000±\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\n*\u0003K\u009c\u0001\u0018\u0000 û\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002û\u0001B\u0005¢\u0006\u0002\u0010\u0005J\t\u0010¨\u0001\u001a\u00020VH\u0002J\t\u0010©\u0001\u001a\u00020VH\u0002J\u0012\u0010ª\u0001\u001a\u00020V2\u0007\u0010«\u0001\u001a\u000207H\u0002J\t\u0010¬\u0001\u001a\u00020VH\u0002J\n\u0010\u00ad\u0001\u001a\u00030®\u0001H\u0002J\u0012\u0010¯\u0001\u001a\u00020\n2\u0007\u0010«\u0001\u001a\u000207H\u0002J\u0012\u0010°\u0001\u001a\u00020 2\u0007\u0010«\u0001\u001a\u000207H\u0002J\u0015\u0010±\u0001\u001a\u00030²\u00012\t\b\u0002\u0010³\u0001\u001a\u00020NH\u0002J\t\u0010´\u0001\u001a\u00020\nH\u0002J\n\u0010µ\u0001\u001a\u00030¶\u0001H\u0002J\u0012\u0010·\u0001\u001a\u00020 2\u0007\u0010«\u0001\u001a\u000207H\u0002J\t\u0010¸\u0001\u001a\u00020VH\u0002J\t\u0010¹\u0001\u001a\u00020VH\u0002J\u0012\u0010º\u0001\u001a\u00020V2\u0007\u0010»\u0001\u001a\u00020\nH\u0002J\t\u0010¼\u0001\u001a\u00020NH\u0002J\u0015\u0010½\u0001\u001a\u00020N2\n\u0010¾\u0001\u001a\u0005\u0018\u00010¿\u0001H\u0002J\t\u0010À\u0001\u001a\u00020NH\u0002J\t\u0010Á\u0001\u001a\u00020NH\u0002J\u0013\u0010Â\u0001\u001a\u00020V2\b\u0010Ã\u0001\u001a\u00030Ä\u0001H\u0016J\t\u0010Å\u0001\u001a\u00020VH\u0002J\u0015\u0010Æ\u0001\u001a\u00020V2\n\u0010Ç\u0001\u001a\u0005\u0018\u00010È\u0001H\u0014J\t\u0010É\u0001\u001a\u00020VH\u0014J\t\u0010Ê\u0001\u001a\u00020VH\u0014J\u001d\u0010Ë\u0001\u001a\u00020V2\u0007\u0010Ì\u0001\u001a\u00020\u00072\t\b\u0002\u0010Í\u0001\u001a\u00020NH\u0002J0\u0010Î\u0001\u001a\u00020N2\b\u0010Ï\u0001\u001a\u00030Ð\u00012\u001b\u0010Ñ\u0001\u001a\u0016\u0012\u0005\u0012\u00030¿\u00010Ò\u0001j\n\u0012\u0005\u0012\u00030¿\u0001`Ó\u0001H\u0016J\t\u0010Ô\u0001\u001a\u00020VH\u0014J\t\u0010Õ\u0001\u001a\u00020VH\u0014J\t\u0010Ö\u0001\u001a\u00020VH\u0014J\u0012\u0010×\u0001\u001a\u00020V2\u0007\u0010Ø\u0001\u001a\u00020NH\u0016J\t\u0010Ù\u0001\u001a\u00020VH\u0002J\u0012\u0010Ú\u0001\u001a\u00020V2\u0007\u0010³\u0001\u001a\u00020NH\u0002J\u0012\u0010Û\u0001\u001a\u00020V2\u0007\u0010«\u0001\u001a\u000207H\u0002J\t\u0010Ü\u0001\u001a\u00020VH\u0002J\t\u0010Ý\u0001\u001a\u00020VH\u0002J\t\u0010Þ\u0001\u001a\u00020VH\u0002J\t\u0010ß\u0001\u001a\u00020VH\u0002J\t\u0010à\u0001\u001a\u00020VH\u0002J\t\u0010á\u0001\u001a\u00020VH\u0002J\u0012\u0010â\u0001\u001a\u00020V2\u0007\u0010ã\u0001\u001a\u00020\u0018H\u0016J\t\u0010ä\u0001\u001a\u00020VH\u0016J\u0012\u0010å\u0001\u001a\u00020V2\u0007\u0010«\u0001\u001a\u000207H\u0002J\u0012\u0010æ\u0001\u001a\u00020V2\u0007\u0010«\u0001\u001a\u000207H\u0002J\u0012\u0010ç\u0001\u001a\u00020V2\u0007\u0010«\u0001\u001a\u000207H\u0002J\u0012\u0010è\u0001\u001a\u00020V2\u0007\u0010é\u0001\u001a\u00020\nH\u0002J\u0012\u0010ê\u0001\u001a\u00020V2\u0007\u0010«\u0001\u001a\u000207H\u0002J\t\u0010ë\u0001\u001a\u00020VH\u0002J$\u0010ì\u0001\u001a\u00020V2\u0007\u0010\u008d\u0001\u001a\u0002012\u0007\u0010«\u0001\u001a\u0002072\u0007\u0010í\u0001\u001a\u00020\u0007H\u0016J\t\u0010î\u0001\u001a\u00020VH\u0002J\u001c\u0010ï\u0001\u001a\u00020V2\u0007\u0010ð\u0001\u001a\u00020\u00182\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016J\u001b\u0010ó\u0001\u001a\u00020V2\u0007\u0010\u008d\u0001\u001a\u0002012\u0007\u0010«\u0001\u001a\u000207H\u0016J\t\u0010ô\u0001\u001a\u00020VH\u0002J\u0012\u0010õ\u0001\u001a\u00020V2\u0007\u0010ö\u0001\u001a\u00020\u0007H\u0002J\t\u0010÷\u0001\u001a\u00020VH\u0002J\t\u0010ø\u0001\u001a\u00020VH\u0002J\t\u0010ù\u0001\u001a\u00020VH\u0002J\t\u0010ú\u0001\u001a\u00020VH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u0016\u001a\u0004\b$\u0010%R\u000e\u0010'\u001a\u00020(X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010+\u001a\u00020,8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u0016\u001a\u0004\b-\u0010.R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00106\u001a\u0004\u0018\u000107X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00109\u001a\u00020:8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\u0016\u001a\u0004\b;\u0010<R\u001b\u0010>\u001a\u00020?8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\u0016\u001a\u0004\b@\u0010AR\u001b\u0010C\u001a\u00020D8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bG\u0010\u0016\u001a\u0004\bE\u0010FR\u0010\u0010H\u001a\u0004\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0004\n\u0002\u0010LR\u000e\u0010M\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020NX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010T\u001a\b\u0012\u0004\u0012\u00020V0UX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010X\u001a\u0004\u0018\u00010YX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010Z\u001a\u00020[8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b^\u0010\u0016\u001a\u0004\b\\\u0010]R\u001b\u0010_\u001a\u00020`8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\u0016\u001a\u0004\ba\u0010bR\u001b\u0010d\u001a\u00020e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bh\u0010\u0016\u001a\u0004\bf\u0010gR\u000e\u0010i\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020kX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020mX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020oX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020YX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010q\u001a\b\u0012\u0004\u0012\u00020N0UX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010r\u001a\b\u0012\u0004\u0012\u00020V0UX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010u\u001a\n\u0012\u0004\u0012\u00020V\u0018\u00010UX\u0082\u000e¢\u0006\u0002\n\u0000R@\u0010v\u001a4\u0012\u0013\u0012\u00110N¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0013\u0012\u00110N¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b({\u0012\u0004\u0012\u00020V\u0018\u00010wX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010|\u001a\u00020}X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010~\u001a\b\u0012\u0004\u0012\u00020V0UX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020V\u0018\u00010UX\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0080\u0001\u001a\u00020tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0083\u0001\u001a\u00020tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0086\u0001\u001a\u001f\u0012\u0014\u0012\u00120N¢\u0006\r\bx\u0012\t\by\u0012\u0005\b\b(\u0088\u0001\u0012\u0004\u0012\u00020V0\u0087\u0001X\u0082\u0004¢\u0006\u0002\n\u0000RC\u0010\u0089\u0001\u001a6\u0012\u0015\u0012\u00130\u008a\u0001¢\u0006\r\bx\u0012\t\by\u0012\u0005\b\b(\u008b\u0001\u0012\u0015\u0012\u00130\u008c\u0001¢\u0006\r\bx\u0012\t\by\u0012\u0005\b\b(\u008d\u0001\u0012\u0004\u0012\u00020V0wX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0090\u0001\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0091\u0001\u001a\u00030\u0092\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0095\u0001\u0010\u0016\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0010\u0010\u0096\u0001\u001a\u00030\u0097\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u0098\u0001\u001a \u0012\u0015\u0012\u00130\u0099\u0001¢\u0006\r\bx\u0012\t\by\u0012\u0005\b\b(\u009a\u0001\u0012\u0004\u0012\u00020V0\u0087\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u009b\u0001\u001a\u00030\u009c\u0001X\u0082\u0004¢\u0006\u0005\n\u0003\u0010\u009d\u0001R\u0010\u0010\u009e\u0001\u001a\u00030\u009f\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010 \u0001\u001a\u0005\u0018\u00010¡\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010¢\u0001\u001a\u0005\u0018\u00010£\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010¤\u0001\u001a\u00030¥\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010¦\u0001\u001a\u0012\u0012\u0007\u0012\u0005\u0018\u00010§\u0001\u0012\u0004\u0012\u00020V0\u0087\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006ü\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DeliverActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryContract$ViewInterface;", "()V", "CALL_AUTO_COMPLETE_TIME_WHAT", "", "PLAY_ARRIVED_VOICE", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_PAUSE_FEATURE_AIVOICE", "TYPE_PAUSE_FEATURE_DIALOG", "TYPE_PAUSE_FEATURE_ERROR", "TYPE_PAUSE_FEATURE_NORMAL", "TYPE_PAUSE_FEATURE_TOUCH", "arriveLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getArriveLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "arriveLayout$delegate", "Lkotlin/Lazy;", "arriveVoiceLooperTime", "", "getArriveVoiceLooperTime", "()J", "arriveVoiceLooperTime$delegate", "arrivedMsgId", "arrivedMsgIds", "", "arrivedTakeWarnTask", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "arrivedWaitVoiceTask", "autoResumeCountDownPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "getAutoResumeCountDownPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownPresenter;", "autoResumeCountDownPresenter$delegate", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "callCompleteCountDown", "Lio/reactivex/disposables/Disposable;", "callSetVM", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "getCallSetVM", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/CallSettingVM;", "callSetVM$delegate", "currentEventStatus", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryContract$DeliveryEvent;", "currentFromMode", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "currentMode", "currentPauseFeature", "currentViewModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/ViewModel;", "customOutlet", "deliverArriverLayout", "Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverArriveLayout;", "getDeliverArriverLayout", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverArriveLayout;", "deliverArriverLayout$delegate", "deliverPauseLayout", "Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout;", "getDeliverPauseLayout", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout;", "deliverPauseLayout$delegate", "deliverPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter;", "getDeliverPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter;", "deliverPresenter$delegate", "deliveryPalletAlartDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/DeliveryPalletAlarmDialog;", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/DeliverActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/DeliverActivity$functionButton$1;", "isDeliverActive", "", "isFirstStart", "isNeedInitCurrentPalletTts", "isPlayingFinishVoice", "isRelease", "lastEventIsDone", "locationLostTouchCancelCallback", "Lkotlin/Function0;", "", "locationLostVoiceTask", "looperArrivedVoiceTask", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceWrapperPlayer$VoiceTaskWrapper;", "lotteryResultLayout", "Lcom/pudutech/bumblebee/robot_ui/ui/view/LotteryResultLayout;", "getLotteryResultLayout", "()Lcom/pudutech/bumblebee/robot_ui/ui/view/LotteryResultLayout;", "lotteryResultLayout$delegate", "mBluetoothHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper;", "getMBluetoothHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/BluetoothHelper;", "mBluetoothHelper$delegate", "mDangerousAreaTipHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "getMDangerousAreaTipHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/DangerousAreaTipHelper;", "mDangerousAreaTipHelper$delegate", "mTargetName", "mainHandler", "Landroid/os/Handler;", "mainScope", "Lkotlinx/coroutines/CoroutineScope;", "motionEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/MotionEventHelper;", "movingLoopVoiceTask", "onAiVoiceFinish", "onArrivingLayoutClick", "onDeliverFinishClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/OnLazyClickListener;", "onErrorClearCallback", "onErrorDialogShowStatus", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "show", "isEmergencyStop", "onFaceAnimationViewClick", "Lcom/pudutech/bumblebee/robot_ui/ui_utils/SingleClickListener;", "onFallDropCallBack", "onLostLocationLostCallback", "onLotteryBtnClick", "onLotteryLayoutHide", "Lcom/pudutech/bumblebee/robot_ui/ui/view/LotteryResultLayout$OnLayoutHideListener;", "onOutletSelectClick", "onPauseEvenClick", "Lcom/pudutech/bumblebee/robot_ui/ui/view/DeliverPauseLayout$OnEvenClickListener;", "onTouchSensorAnimationListener", "Lkotlin/Function1;", "isShow", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "palletTtsScheme", "Lcom/pudutech/bumblebee/robot_ui/bean/PalletTtsScheme;", "pauseVoiceTask", "remoteDeliveryPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryPresenter;", "getRemoteDeliveryPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/remote_task/RemoteDeliveryPresenter;", "remoteDeliveryPresenter$delegate", "runningErrorHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/RunningErrorHelper;", "scheduleEventLister", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleContract$TriggerEvent;", "triggerEvent", "singleBatteryListener", "com/pudutech/bumblebee/robot_ui/ui/DeliverActivity$singleBatteryListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/DeliverActivity$singleBatteryListener$1;", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "ttsConfigDataArrived", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "voicePlayerHelper", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoicePlayerHelper;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "bindPresenter", "callReturn", "dealPalletChange", "model", "dismissPalletDialog", "getDeliverMode", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "getLedDestinationStr", "getMerchantVoiceTask", "getOnTheWayAni", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoAnimation;", "isAdvance", "getThreadInfo", "getTrackType", "Lcom/pudutech/robot/module/report/track2/TrackType;", "getTrayVoiceTask", "hideOnTheWayLayout", "initAiVoice", "initCurrentPalletTts", "destination", "isCallDirect", "isPalletHaveTask", "t", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "isShowDeliverFace", "isSupportEuropeanPlayFormat", "jumpAndFinish", "i", "Landroid/content/Intent;", "onClickDeliverFinish", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onPauseFeatureChange", "type", "isSetPause", "onReceiveRemoteModifyTask", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "allTrays", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "playArriveMusic", "playArrivedFinish", "playArrivedVoice", "playArrivedWaitVoice", "release", "releaseDataAndFinish", "removeSendingMsg", "resetBtnFinish", "resetMusicProgress", "showCountdownFinish", "millisUntilFinished", "showDelayAutoFinish", "showDeliverArrivedStatus", "showDeliverArrivingStatus", "showDeliverPauseStatus", "showOnTheWayLayout", TypedValues.Attributes.S_TARGET, "showOnTheWayStatus", "showOutletSelectDialog", "showPalletChanged", "index", "showRecycleTargetDialog", "showTimeLeft", "time", "unit", "Lcom/pudutech/bumblebee/presenter/general_task/AutoResumeCountDownContract$Unit;", "showViewModelChanged", "startDeliverTask", "startDeliverTaskEditActivity", "mode", "stopArrivedMusic", "stopArrivedVoice", "stopPalletAlarm", "unbindPresenter", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverActivity extends MyBaseActivity implements DeliveryContract.ViewInterface, AutoResumeCountDownContract.ViewInterface, RemoteDeliveryContract.ViewInterface {
    public static final String DELIVER_FROM = "DELIVER_FROM";
    public static final String DELIVER_MODE = "DELIVER_MODE";
    public static final int FROM_CALL = 1;
    public static final int FROM_NORMAL = 0;
    public static final int MODE_BIRTHDAY = 1;
    public static final int MODE_CALL_DIRECT = 4;
    public static final int MODE_DIRECT = 2;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_SPECIAL = 3;
    private final int CALL_AUTO_COMPLETE_TIME_WHAT;
    private final int PLAY_ARRIVED_VOICE;
    private final int TYPE_PAUSE_FEATURE_AIVOICE;
    private final int TYPE_PAUSE_FEATURE_DIALOG;
    private final int TYPE_PAUSE_FEATURE_ERROR;
    private final int TYPE_PAUSE_FEATURE_NORMAL;
    private final int TYPE_PAUSE_FEATURE_TOUCH;
    private HashMap _$_findViewCache;

    /* renamed from: arriveLayout$delegate, reason: from kotlin metadata */
    private final Lazy arriveLayout;

    /* renamed from: arriveVoiceLooperTime$delegate, reason: from kotlin metadata */
    private final Lazy arriveVoiceLooperTime;
    private String arrivedMsgId;
    private List<String> arrivedMsgIds;
    private final VoiceTask arrivedTakeWarnTask;
    private final VoiceTask arrivedWaitVoiceTask;

    /* renamed from: autoResumeCountDownPresenter$delegate, reason: from kotlin metadata */
    private final Lazy autoResumeCountDownPresenter;
    private final BeeperCallHelper beeperCallHelper;
    private Disposable callCompleteCountDown;
    private DeliveryContract.DeliveryEvent currentEventStatus;
    private int currentMode;
    private int currentPauseFeature;
    private ViewModel currentViewModel;
    private String customOutlet;

    /* renamed from: deliverArriverLayout$delegate, reason: from kotlin metadata */
    private final Lazy deliverArriverLayout;

    /* renamed from: deliverPauseLayout$delegate, reason: from kotlin metadata */
    private final Lazy deliverPauseLayout;
    private DeliveryPalletAlarmDialog deliveryPalletAlartDialog;
    private final DeliverActivity$functionButton$1 functionButton;
    private boolean isDeliverActive;
    private boolean isFirstStart;
    private boolean isNeedInitCurrentPalletTts;
    private boolean isPlayingFinishVoice;
    private boolean isRelease;
    private boolean lastEventIsDone;
    private final Function0<Unit> locationLostTouchCancelCallback;
    private final VoiceTask locationLostVoiceTask;
    private TtsVoiceWrapperPlayer.VoiceTaskWrapper looperArrivedVoiceTask;

    /* renamed from: lotteryResultLayout$delegate, reason: from kotlin metadata */
    private final Lazy lotteryResultLayout;

    /* renamed from: mBluetoothHelper$delegate, reason: from kotlin metadata */
    private final Lazy mBluetoothHelper;

    /* renamed from: mDangerousAreaTipHelper$delegate, reason: from kotlin metadata */
    private final Lazy mDangerousAreaTipHelper;
    private String mTargetName;
    private final Handler mainHandler;
    private final MotionEventHelper motionEventHelper;
    private final TtsVoiceWrapperPlayer.VoiceTaskWrapper movingLoopVoiceTask;
    private final Function0<Boolean> onAiVoiceFinish;
    private final Function0<Unit> onArrivingLayoutClick;
    private final OnLazyClickListener onDeliverFinishClick;
    private Function0<Unit> onErrorClearCallback;
    private Function2<? super Boolean, ? super Boolean, Unit> onErrorDialogShowStatus;
    private final SingleClickListener onFaceAnimationViewClick;
    private Function0<Unit> onFallDropCallBack;
    private Function0<Unit> onLostLocationLostCallback;
    private final OnLazyClickListener onLotteryBtnClick;
    private final LotteryResultLayout.OnLayoutHideListener onLotteryLayoutHide;
    private final OnLazyClickListener onOutletSelectClick;
    private final DeliverPauseLayout.OnEvenClickListener onPauseEvenClick;
    private final Function1<Boolean, Unit> onTouchSensorAnimationListener;
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener;
    private PalletTtsScheme palletTtsScheme;
    private final VoiceTask pauseVoiceTask;
    private final Function1<ScheduleContract.TriggerEvent, Unit> scheduleEventLister;
    private final DeliverActivity$singleBatteryListener$1 singleBatteryListener;
    private TtsVoiceHelper.TtsConfigData ttsConfigDataArrived;
    private VoiceInteractionDialog voiceInteractionDialog;
    private final VoicePlayerHelper voicePlayerHelper;
    private final Function1<WakeupInfo, Unit> wakeupListener;
    private final String TAG = getClass().getSimpleName();
    private final CoroutineScope mainScope = CoroutineScopeKt.MainScope();
    private CallFromType currentFromMode = CallFromType.DEFAULT;

    /* renamed from: callSetVM$delegate, reason: from kotlin metadata */
    private final Lazy callSetVM = LazyKt.lazy(new Function0<CallSettingVM>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$callSetVM$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final CallSettingVM invoke() {
            DeliverActivity deliverActivity = DeliverActivity.this;
            androidx.lifecycle.ViewModel viewModel = new ViewModelProvider(deliverActivity, new ViewModelProvider.AndroidViewModelFactory(deliverActivity.getApplication())).get(CallSettingVM.class);
            Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProvider(this, …     .get(VM::class.java)");
            return (CallSettingVM) ((BaseViewModel) viewModel);
        }
    });

    /* renamed from: deliverPresenter$delegate, reason: from kotlin metadata */
    private final Lazy deliverPresenter = LazyKt.lazy(new Function0<DeliveryPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$deliverPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeliveryPresenter invoke() {
            DeliveryPresenter deliveryPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DeliveryPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(DeliveryPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                deliveryPresenter = new DeliveryPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(DeliveryPresenter.class).toString(), deliveryPresenter);
            } else {
                if (!(basePresenterInterface instanceof DeliveryPresenter)) {
                    basePresenterInterface = null;
                }
                deliveryPresenter = (DeliveryPresenter) basePresenterInterface;
            }
            if (deliveryPresenter != null) {
                return deliveryPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter");
        }
    });
    private final RunningErrorHelper runningErrorHelper = new RunningErrorHelper();

    /* renamed from: remoteDeliveryPresenter$delegate, reason: from kotlin metadata */
    private final Lazy remoteDeliveryPresenter = LazyKt.lazy(new Function0<RemoteDeliveryPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$remoteDeliveryPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RemoteDeliveryPresenter invoke() {
            RemoteDeliveryPresenter remoteDeliveryPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(RemoteDeliveryPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(RemoteDeliveryPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                remoteDeliveryPresenter = new RemoteDeliveryPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(RemoteDeliveryPresenter.class).toString(), remoteDeliveryPresenter);
            } else {
                if (!(basePresenterInterface instanceof RemoteDeliveryPresenter)) {
                    basePresenterInterface = null;
                }
                remoteDeliveryPresenter = (RemoteDeliveryPresenter) basePresenterInterface;
            }
            if (remoteDeliveryPresenter != null) {
                return remoteDeliveryPresenter;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.presenter.delivery_task.remote_task.RemoteDeliveryPresenter");
        }
    });
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.ON_THE_WAY.ordinal()] = 1;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.APPROACHING.ordinal()] = 2;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.ARRIVAL.ordinal()] = 3;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.DONE.ordinal()] = 4;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.DONE_BEFORE_ARRIVAL.ordinal()] = 5;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.ALL_LEFT_CANCEL.ordinal()] = 6;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.ALL_DONE.ordinal()] = 7;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.NOT_FIND_TARGET.ordinal()] = 8;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.MODIFY.ordinal()] = 9;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.PAUSE.ordinal()] = 10;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryEvent.ACTIVE.ordinal()] = 11;
            $EnumSwitchMapping$1 = new int[DeliveryContract.DeliveryEvent.values().length];
            $EnumSwitchMapping$1[DeliveryContract.DeliveryEvent.PALLET_CHANGE.ordinal()] = 1;
            $EnumSwitchMapping$1[DeliveryContract.DeliveryEvent.PALLET_PLACE.ordinal()] = 2;
            $EnumSwitchMapping$1[DeliveryContract.DeliveryEvent.PALLET_TAKE.ordinal()] = 3;
            $EnumSwitchMapping$1[DeliveryContract.DeliveryEvent.EMPTY.ordinal()] = 4;
        }
    }

    private final ConstraintLayout getArriveLayout() {
        return (ConstraintLayout) this.arriveLayout.getValue();
    }

    private final long getArriveVoiceLooperTime() {
        return ((Number) this.arriveVoiceLooperTime.getValue()).longValue();
    }

    private final AutoResumeCountDownPresenter getAutoResumeCountDownPresenter() {
        return (AutoResumeCountDownPresenter) this.autoResumeCountDownPresenter.getValue();
    }

    private final CallSettingVM getCallSetVM() {
        return (CallSettingVM) this.callSetVM.getValue();
    }

    private final DeliverArriveLayout getDeliverArriverLayout() {
        return (DeliverArriveLayout) this.deliverArriverLayout.getValue();
    }

    private final DeliverPauseLayout getDeliverPauseLayout() {
        return (DeliverPauseLayout) this.deliverPauseLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeliveryPresenter getDeliverPresenter() {
        return (DeliveryPresenter) this.deliverPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LotteryResultLayout getLotteryResultLayout() {
        return (LotteryResultLayout) this.lotteryResultLayout.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BluetoothHelper getMBluetoothHelper() {
        return (BluetoothHelper) this.mBluetoothHelper.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DangerousAreaTipHelper getMDangerousAreaTipHelper() {
        return (DangerousAreaTipHelper) this.mDangerousAreaTipHelper.getValue();
    }

    private final RemoteDeliveryPresenter getRemoteDeliveryPresenter() {
        return (RemoteDeliveryPresenter) this.remoteDeliveryPresenter.getValue();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
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

    /* JADX WARN: Type inference failed for: r0v37, types: [com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$functionButton$1] */
    public DeliverActivity() {
        MotionEventHelper motionEventHelper = new MotionEventHelper();
        motionEventHelper.setActionExitObstruct(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$motionEventHelper$1$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
            }
        });
        this.motionEventHelper = motionEventHelper;
        this.beeperCallHelper = new BeeperCallHelper();
        this.autoResumeCountDownPresenter = LazyKt.lazy(new Function0<AutoResumeCountDownPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$autoResumeCountDownPresenter$2
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
        this.movingLoopVoiceTask = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(new VoiceTask(10000L, (Pair<Long, ? extends VoiceItem>[]) new Pair[]{new Pair(10000L, VoiceItem.voice7_1)}), null, null, 6, null);
        this.pauseVoiceTask = new VoiceTask(-1L, VoiceItem.voice7_2);
        this.locationLostVoiceTask = new VoiceTask(-1L, VoiceItem.voice17_1);
        this.arrivedTakeWarnTask = new VoiceTask(5000L, VoiceItem.voice34_6).withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$arrivedTakeWarnTask$1
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                String str;
                Intrinsics.checkParameterIsNotNull(event, "event");
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "showPalletChangedon StateChange event: " + event);
                DeliverActivity.this.playArrivedWaitVoice();
            }
        });
        this.PLAY_ARRIVED_VOICE = 100;
        this.CALL_AUTO_COMPLETE_TIME_WHAT = 103;
        this.arrivedWaitVoiceTask = new VoiceTask(-1L, VoiceItem.voice9_1);
        this.voicePlayerHelper = new VoicePlayerHelper();
        this.arrivedMsgIds = new ArrayList();
        this.mDangerousAreaTipHelper = LazyKt.lazy(new Function0<DangerousAreaTipHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$mDangerousAreaTipHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DangerousAreaTipHelper invoke() {
                return new DangerousAreaTipHelper();
            }
        });
        this.mBluetoothHelper = LazyKt.lazy(new Function0<BluetoothHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$mBluetoothHelper$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BluetoothHelper invoke() {
                return new BluetoothHelper();
            }
        });
        this.functionButton = new FunctionButtonListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$functionButton$1
            @Override // com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task.FunctionButtonListener
            public void onClick() {
            }
        };
        this.scheduleEventLister = new Function1<ScheduleContract.TriggerEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$scheduleEventLister$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScheduleContract.TriggerEvent triggerEvent) {
                invoke2(triggerEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScheduleContract.TriggerEvent it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == ScheduleContract.TriggerEvent.AVOID) {
                    DeliveryTrack.INSTANCE.onStartScheduling();
                } else if (it == ScheduleContract.TriggerEvent.NORMAL) {
                    DeliveryTrack.INSTANCE.onStopScheduling();
                }
            }
        };
        this.isNeedInitCurrentPalletTts = true;
        this.onAiVoiceFinish = new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onAiVoiceFinish$1
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
                VoiceInteractionDialog voiceInteractionDialog;
                DeliveryContract.DeliveryEvent deliveryEvent;
                String str;
                voiceInteractionDialog = DeliverActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
                    voiceInteractionDialog.dismiss();
                }
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                if (deliveryEvent == DeliveryContract.DeliveryEvent.ARRIVAL) {
                    str = DeliverActivity.this.TAG;
                    Pdlog.m3273d(str, "onAiVoiceFinish()");
                    DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
                    String currentTask = TableTaskManager.INSTANCE.getCurrentTask();
                    if (currentTask == null) {
                        currentTask = "";
                    }
                    deliveryTrack.onFinishOne(currentTask, BaseMoveTrackTask.FinishOneCause.Voice);
                    DeliverActivity.this.onClickDeliverFinish();
                    DeliverActivity.this.resetBtnFinish();
                    DeliverActivity.this.removeSendingMsg();
                }
                return true;
            }
        };
        this.arriveVoiceLooperTime = LazyKt.lazy(new Function0<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$arriveVoiceLooperTime$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Long invoke() {
                return Long.valueOf(invoke2());
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final long invoke2() {
                return Constans.INSTANCE.getPlayLooperArriveVoiceTime();
            }
        });
        this.mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$mainHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                int i;
                int i2;
                DeliveryContract.DeliveryEvent deliveryEvent;
                DeliveryPalletAlarmDialog deliveryPalletAlarmDialog;
                TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper;
                VoiceTask voiceTask;
                TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper2;
                ViewModel viewModel;
                ArrayList<TrayModel> trays;
                ViewModel viewModel2;
                int i3 = message.what;
                i = DeliverActivity.this.PLAY_ARRIVED_VOICE;
                if (i3 == i) {
                    deliveryEvent = DeliverActivity.this.currentEventStatus;
                    if (deliveryEvent == DeliveryContract.DeliveryEvent.ARRIVAL) {
                        deliveryPalletAlarmDialog = DeliverActivity.this.deliveryPalletAlartDialog;
                        if (deliveryPalletAlarmDialog == null || !deliveryPalletAlarmDialog.isShowing()) {
                            voiceTaskWrapper = DeliverActivity.this.looperArrivedVoiceTask;
                            if (voiceTaskWrapper != null) {
                                TtsVoiceWrapperPlayer ttsVoiceWrapperPlayer = TtsVoiceWrapperPlayer.INSTANCE;
                                voiceTaskWrapper2 = DeliverActivity.this.looperArrivedVoiceTask;
                                if (voiceTaskWrapper2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                ttsVoiceWrapperPlayer.play(voiceTaskWrapper2, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$mainHandler$1.1
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                                        invoke2(audioPlayEvent);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(AudioTrackUtils.AudioPlayEvent event) {
                                        String str;
                                        DeliveryContract.DeliveryEvent deliveryEvent2;
                                        DeliveryContract.DeliveryEvent deliveryEvent3;
                                        Intrinsics.checkParameterIsNotNull(event, "event");
                                        str = DeliverActivity.this.TAG;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Looper trayVoiceTask tts voice ");
                                        deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                                        sb.append(deliveryEvent2);
                                        sb.append(" ; event = ");
                                        sb.append(event);
                                        Pdlog.m3273d(str, sb.toString());
                                        if (event == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                                            deliveryEvent3 = DeliverActivity.this.currentEventStatus;
                                            if (deliveryEvent3 == DeliveryContract.DeliveryEvent.ARRIVAL) {
                                                DeliverActivity.this.playArrivedWaitVoice();
                                            }
                                        }
                                    }
                                });
                            } else {
                                VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                                voiceTask = DeliverActivity.this.arrivedWaitVoiceTask;
                                voicePlayer.play(voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$mainHandler$1.2
                                    @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                                    public void onStateChange(PlayEvent event) {
                                        DeliveryContract.DeliveryEvent deliveryEvent2;
                                        Intrinsics.checkParameterIsNotNull(event, "event");
                                        if (event == PlayEvent.COMPLETION_ONCE) {
                                            deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                                            if (deliveryEvent2 == DeliveryContract.DeliveryEvent.ARRIVAL) {
                                                DeliverActivity.this.playArrivedWaitVoice();
                                            }
                                        }
                                    }
                                }));
                            }
                        }
                        PeripheralsSceneUtil.INSTANCE.showDeliveryArrivedNotify();
                        viewModel = DeliverActivity.this.currentViewModel;
                        if (viewModel != null && (trays = viewModel.getTrays()) != null) {
                            int i4 = 0;
                            for (Object obj : trays) {
                                int i5 = i4 + 1;
                                if (i4 < 0) {
                                    CollectionsKt.throwIndexOverflow();
                                }
                                TrayModel trayModel = (TrayModel) obj;
                                viewModel2 = DeliverActivity.this.currentViewModel;
                                if (viewModel2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (trayModel.getDeliveryModel(viewModel2.getDestination()) != null) {
                                    PeripheralsSceneUtil.INSTANCE.showDeliveryArrivedPalletNotify(i4);
                                }
                                i4 = i5;
                            }
                        }
                    }
                } else {
                    int i6 = message.what;
                    i2 = DeliverActivity.this.CALL_AUTO_COMPLETE_TIME_WHAT;
                    if (i6 == i2) {
                        DeliverActivity.this.callReturn();
                    }
                }
                return true;
            }
        });
        this.mTargetName = "";
        this.arriveLayout = LazyKt.lazy(new DeliverActivity$arriveLayout$2(this));
        this.onErrorDialogShowStatus = new Function2<Boolean, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onErrorDialogShowStatus$1
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
                int i;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorDialogShowStatus " + z);
                if (z) {
                    DeliverActivity deliverActivity = DeliverActivity.this;
                    i = deliverActivity.TYPE_PAUSE_FEATURE_ERROR;
                    DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
                    PeripheralsSceneUtil.INSTANCE.showRunError();
                    if (z2) {
                        DeliveryTrack.INSTANCE.onEmergencyStop();
                    }
                }
            }
        };
        this.onFallDropCallBack = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onFallDropCallBack$1
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
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Abnormal);
                DeliverActivity.this.releaseDataAndFinish();
            }
        };
        this.onLostLocationLostCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onLostLocationLostCallback$1
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
                String str2;
                VoiceTask voiceTask;
                int i;
                int i2;
                int i3;
                DeliveryPresenter deliverPresenter;
                LocateCase locateCase = CheckLocationHelper.INSTANCE.getLocateCase();
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onLostLocationLostCallback, current LocationCase is " + locateCase.name());
                if (locateCase == LocateCase.Marker || locateCase == LocateCase.LaserMark) {
                    str2 = DeliverActivity.this.TAG;
                    Pdlog.m3273d(str2, "onLostLocationLostCallback");
                    ((FaceVideoView) DeliverActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getLostLocation());
                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                    voiceTask = DeliverActivity.this.locationLostVoiceTask;
                    voicePlayer.play(voiceTask);
                    DeliverActivity deliverActivity = DeliverActivity.this;
                    i = deliverActivity.TYPE_PAUSE_FEATURE_ERROR;
                    DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
                    PeripheralsSceneUtil.INSTANCE.lostLocation();
                    return;
                }
                if (locateCase == LocateCase.Laser || locateCase == LocateCase.Slamware) {
                    i2 = DeliverActivity.this.currentMode;
                    if (i2 == 3) {
                        TableTaskManager.INSTANCE.clearAll();
                    }
                    Intent intent = new Intent(DeliverActivity.this, (Class<?>) LaserRunningLocationLostActivity.class);
                    i3 = DeliverActivity.this.currentMode;
                    if (i3 == 0) {
                        intent.putExtra(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, 1);
                    } else if (i3 == 1) {
                        intent.putExtra(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, 3);
                    } else if (i3 == 2) {
                        intent.putExtra(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, 5);
                    } else if (i3 == 4) {
                        intent.putExtra(LaserRunningLocationLostActivity.CURRENT_MODE_TYPE, 5);
                    }
                    DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LostLocation);
                    deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                    deliverPresenter.actionCancelAll();
                    DeliverActivity.this.jumpAndFinish(intent);
                }
            }
        };
        this.onErrorClearCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onErrorClearCallback$1
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
                DeliveryPresenter deliverPresenter;
                String str2;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onErrorClearCallback");
                if (((FaceVideoView) DeliverActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).isPlayLostLocation()) {
                    ((FaceVideoView) DeliverActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                    deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                    deliverPresenter.actionPause();
                    str2 = DeliverActivity.this.TAG;
                    Pdlog.m3273d(str2, "onErrorClearCallback  pause");
                }
            }
        };
        this.currentEventStatus = DeliveryContract.DeliveryEvent.ON_THE_WAY;
        this.isDeliverActive = true;
        this.onDeliverFinishClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onDeliverFinishClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onDeliverFinishClick");
                DeliverActivity.this.resetBtnFinish();
                DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
                String currentTask = TableTaskManager.INSTANCE.getCurrentTask();
                if (currentTask == null) {
                    currentTask = "";
                }
                deliveryTrack.onFinishOne(currentTask, BaseMoveTrackTask.FinishOneCause.Click);
                DeliverActivity.this.onClickDeliverFinish();
            }
        };
        this.onLotteryBtnClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onLotteryBtnClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                LotteryResultLayout lotteryResultLayout;
                int calculationResult = LotteryUtil.calculationResult();
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "showLotteryResult " + calculationResult);
                lotteryResultLayout = DeliverActivity.this.getLotteryResultLayout();
                lotteryResultLayout.showLotteryResult(calculationResult > -1, calculationResult);
                DeliverActivity.this.resetBtnFinish();
            }
        };
        this.onLotteryLayoutHide = new LotteryResultLayout.OnLayoutHideListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onLotteryLayoutHide$1
            @Override // com.pudutech.bumblebee.robot_ui.ui.view.LotteryResultLayout.OnLayoutHideListener
            public void onLayoutHide() {
                String str;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onLotteryResultLayoutHide");
                DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
                String currentTask = TableTaskManager.INSTANCE.getCurrentTask();
                if (currentTask == null) {
                    currentTask = "";
                }
                deliveryTrack.onFinishOne(currentTask, BaseMoveTrackTask.FinishOneCause.LotteryHide);
                DeliverActivity.this.onClickDeliverFinish();
            }
        };
        this.onPauseEvenClick = new DeliverActivity$onPauseEvenClick$1(this);
        this.onFaceAnimationViewClick = new SingleClickListener(null, 0, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onFaceAnimationViewClick$1
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
                DeliveryContract.DeliveryEvent deliveryEvent;
                DeliveryContract.DeliveryEvent deliveryEvent2;
                DeliveryPresenter deliverPresenter;
                DeliveryContract.DeliveryEvent deliveryEvent3;
                DeliveryContract.DeliveryEvent deliveryEvent4;
                str = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onFaceAnimationViewClick current status = ");
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                sb.append(deliveryEvent);
                Pdlog.m3273d(str, sb.toString());
                deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                if (deliveryEvent2 != DeliveryContract.DeliveryEvent.ON_THE_WAY) {
                    deliveryEvent3 = DeliverActivity.this.currentEventStatus;
                    if (deliveryEvent3 != DeliveryContract.DeliveryEvent.ACTIVE) {
                        deliveryEvent4 = DeliverActivity.this.currentEventStatus;
                        if (deliveryEvent4 != DeliveryContract.DeliveryEvent.APPROACHING) {
                            return;
                        }
                    }
                }
                deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                deliverPresenter.actionPause();
            }
        }, 3, null);
        this.onArrivingLayoutClick = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onArrivingLayoutClick$1
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
                DeliveryContract.DeliveryEvent deliveryEvent;
                DeliveryContract.DeliveryEvent deliveryEvent2;
                DeliveryPresenter deliverPresenter;
                str = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onArrivingLayoutClick current status = ");
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                sb.append(deliveryEvent);
                Pdlog.m3273d(str, sb.toString());
                deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                if (deliveryEvent2 == DeliveryContract.DeliveryEvent.APPROACHING) {
                    deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                    deliverPresenter.actionPauseNoTimer();
                }
            }
        };
        this.deliverArriverLayout = LazyKt.lazy(new Function0<DeliverArriveLayout>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$deliverArriverLayout$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DeliverArriveLayout invoke() {
                OnLazyClickListener onLazyClickListener;
                Function0<Unit> function0;
                int i;
                OnLazyClickListener onLazyClickListener2;
                int i2;
                String str;
                OnLazyClickListener onLazyClickListener3;
                View inflate = ((ViewStub) DeliverActivity.this.findViewById(C4188R.id.arrive_layout_stub)).inflate();
                if (inflate == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.view.DeliverArriveLayout");
                }
                DeliverArriveLayout deliverArriveLayout = (DeliverArriveLayout) inflate;
                if (SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false)) {
                    onLazyClickListener3 = DeliverActivity.this.onLotteryBtnClick;
                    deliverArriveLayout.setOnClickFinishListener(onLazyClickListener3);
                } else {
                    onLazyClickListener = DeliverActivity.this.onDeliverFinishClick;
                    deliverArriveLayout.setOnClickFinishListener(onLazyClickListener);
                    Button btn_finish = (Button) deliverArriveLayout._$_findCachedViewById(C4188R.id.btn_finish);
                    Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
                    String customFinishBtnContent = Constans.INSTANCE.getCustomFinishBtnContent();
                    if (customFinishBtnContent.length() == 0) {
                        customFinishBtnContent = DeliverActivity.this.getString(C4188R.string.pdStr2_15);
                        Intrinsics.checkExpressionValueIsNotNull(customFinishBtnContent, "getString(R.string.pdStr2_15)");
                    }
                    btn_finish.setText(customFinishBtnContent);
                    Button button = (Button) deliverArriveLayout._$_findCachedViewById(C4188R.id.btn_finish);
                    int dimension = ((int) deliverArriveLayout.getResources().getDimension(C4188R.dimen.delivery_arrive_content_ll_w)) - 100;
                    String customFinishBtnContent2 = Constans.INSTANCE.getCustomFinishBtnContent();
                    if (customFinishBtnContent2.length() == 0) {
                        customFinishBtnContent2 = DeliverActivity.this.getString(C4188R.string.pdStr2_15);
                        Intrinsics.checkExpressionValueIsNotNull(customFinishBtnContent2, "getString(R.string.pdStr2_15)");
                    }
                    UiUtils.adjustTvTextSize(button, dimension, customFinishBtnContent2);
                }
                function0 = DeliverActivity.this.onArrivingLayoutClick;
                deliverArriveLayout.setOnArrivingLayoutClick(function0);
                i = DeliverActivity.this.currentMode;
                if (i == 1) {
                    deliverArriveLayout.switchTheme(2);
                } else {
                    deliverArriveLayout.switchTheme(0);
                }
                onLazyClickListener2 = DeliverActivity.this.onOutletSelectClick;
                deliverArriveLayout.setOnClickOutletSelectListener(onLazyClickListener2);
                i2 = DeliverActivity.this.currentMode;
                if (i2 == 0) {
                    TextView tv_dish_port = (TextView) deliverArriveLayout._$_findCachedViewById(C4188R.id.tv_dish_port);
                    Intrinsics.checkExpressionValueIsNotNull(tv_dish_port, "tv_dish_port");
                    TextView textView = tv_dish_port;
                    DeliverSettingModel deliverSettingModel = DeliverSettingModel.INSTANCE;
                    Context context = deliverArriveLayout.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "context");
                    textView.setVisibility(deliverSettingModel.getOutletSelectSwitchState(context) ? 0 : 8);
                } else {
                    TextView tv_dish_port2 = (TextView) deliverArriveLayout._$_findCachedViewById(C4188R.id.tv_dish_port);
                    Intrinsics.checkExpressionValueIsNotNull(tv_dish_port2, "tv_dish_port");
                    tv_dish_port2.setVisibility(8);
                }
                String currentMapDiningOutLetChosen = RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen();
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "curOutlet: " + currentMapDiningOutLetChosen);
                String str2 = currentMapDiningOutLetChosen;
                if (!(str2 == null || str2.length() == 0)) {
                    TextView tv_dish_port3 = (TextView) deliverArriveLayout._$_findCachedViewById(C4188R.id.tv_dish_port);
                    Intrinsics.checkExpressionValueIsNotNull(tv_dish_port3, "tv_dish_port");
                    tv_dish_port3.setText(str2);
                } else {
                    TextView tv_dish_port4 = (TextView) deliverArriveLayout._$_findCachedViewById(C4188R.id.tv_dish_port);
                    Intrinsics.checkExpressionValueIsNotNull(tv_dish_port4, "tv_dish_port");
                    tv_dish_port4.setText(deliverArriveLayout.getContext().getString(C4188R.string.no_outlet));
                }
                return deliverArriveLayout;
            }
        });
        this.deliverPauseLayout = LazyKt.lazy(new Function0<DeliverPauseLayout>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$deliverPauseLayout$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DeliverPauseLayout invoke() {
                DeliverPauseLayout.OnEvenClickListener onEvenClickListener;
                int i;
                int i2;
                int i3;
                CallFromType callFromType;
                View inflate = ((ViewStub) DeliverActivity.this.findViewById(C4188R.id.pause_layout_stub)).inflate();
                if (inflate != null) {
                    DeliverPauseLayout deliverPauseLayout = (DeliverPauseLayout) inflate;
                    onEvenClickListener = DeliverActivity.this.onPauseEvenClick;
                    deliverPauseLayout.setOnEvenClickListener(onEvenClickListener);
                    i = DeliverActivity.this.currentMode;
                    if (i != 1) {
                        i2 = DeliverActivity.this.currentMode;
                        if (i2 != 2) {
                            i3 = DeliverActivity.this.currentMode;
                            if (i3 != 4) {
                                deliverPauseLayout.switchTheme(0);
                            }
                        }
                        deliverPauseLayout.switchTheme(1);
                    } else {
                        deliverPauseLayout.switchTheme(2);
                    }
                    callFromType = DeliverActivity.this.currentFromMode;
                    if (callFromType != CallFromType.DEFAULT) {
                        deliverPauseLayout.showEditBtn(false);
                        deliverPauseLayout.showCompleteImmediatelyBtn(false);
                    }
                    return deliverPauseLayout;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.view.DeliverPauseLayout");
            }
        });
        this.lotteryResultLayout = LazyKt.lazy(new Function0<LotteryResultLayout>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$lotteryResultLayout$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LotteryResultLayout invoke() {
                LotteryResultLayout.OnLayoutHideListener onLayoutHideListener;
                View inflate = ((ViewStub) DeliverActivity.this.findViewById(C4188R.id.lottery_result_layout)).inflate();
                if (inflate != null) {
                    LotteryResultLayout lotteryResultLayout = (LotteryResultLayout) inflate;
                    onLayoutHideListener = DeliverActivity.this.onLotteryLayoutHide;
                    lotteryResultLayout.setHideListener(onLayoutHideListener);
                    return lotteryResultLayout;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.ui.view.LotteryResultLayout");
            }
        });
        this.isFirstStart = true;
        this.locationLostTouchCancelCallback = new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$locationLostTouchCancelCallback$1
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
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "locationLostTouchCancelCallback");
                ((FaceVideoView) DeliverActivity.this._$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                DeliverActivity deliverActivity = DeliverActivity.this;
                i = deliverActivity.TYPE_PAUSE_FEATURE_ERROR;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity, i, false, 2, null);
            }
        };
        this.wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$wakeupListener$1
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
                boolean z;
                String str;
                ViewModel viewModel;
                boolean z2;
                DeliveryContract.DeliveryEvent deliveryEvent;
                boolean z3;
                String str2;
                int i;
                String str3;
                int i2;
                VoiceInteractionDialog voiceInteractionDialog3;
                DeliveryTrack.INSTANCE.onWakeup();
                voiceInteractionDialog = DeliverActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog == null) {
                    DeliverActivity deliverActivity = DeliverActivity.this;
                    deliverActivity.voiceInteractionDialog = new VoiceInteractionDialog(deliverActivity);
                    voiceInteractionDialog3 = DeliverActivity.this.voiceInteractionDialog;
                    if (voiceInteractionDialog3 != null) {
                        voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$wakeupListener$1.1
                            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                            public void onDismiss() {
                                String str4;
                                ViewModel viewModel2;
                                boolean z4;
                                DeliveryContract.DeliveryEvent deliveryEvent2;
                                boolean z5;
                                String str5;
                                int i3;
                                String str6;
                                int i4;
                                str4 = DeliverActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("wakeupListener onDismiss  ");
                                viewModel2 = DeliverActivity.this.currentViewModel;
                                sb.append(viewModel2);
                                sb.append(" and ");
                                z4 = DeliverActivity.this.isDeliverActive;
                                sb.append(z4);
                                Pdlog.m3273d(str4, sb.toString());
                                deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                                if (deliveryEvent2 == DeliveryContract.DeliveryEvent.ARRIVAL) {
                                    str6 = DeliverActivity.this.TAG;
                                    Pdlog.m3273d(str6, "wakeupListener onDismiss is ARRIVAL");
                                    DeliverActivity.this.playArrivedWaitVoice();
                                    DeliverActivity deliverActivity2 = DeliverActivity.this;
                                    i4 = DeliverActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                                    deliverActivity2.onPauseFeatureChange(i4, false);
                                    DeliverActivity.this.playArriveMusic();
                                    return;
                                }
                                z5 = DeliverActivity.this.isDeliverActive;
                                if (z5) {
                                    return;
                                }
                                str5 = DeliverActivity.this.TAG;
                                Pdlog.m3273d(str5, "wakeupListener onDismiss is not ARRIVAL");
                                DeliverActivity deliverActivity3 = DeliverActivity.this;
                                i3 = DeliverActivity.this.TYPE_PAUSE_FEATURE_NORMAL;
                                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i3, false, 2, null);
                            }
                        });
                    }
                }
                voiceInteractionDialog2 = DeliverActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                    return;
                }
                z = DeliverActivity.this.isRelease;
                if (z) {
                    return;
                }
                voiceInteractionDialog2.show();
                str = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("wakeupListener show ");
                viewModel = DeliverActivity.this.currentViewModel;
                sb.append(viewModel);
                sb.append(" and ");
                z2 = DeliverActivity.this.isDeliverActive;
                sb.append(z2);
                Pdlog.m3273d(str, sb.toString());
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                if (deliveryEvent == DeliveryContract.DeliveryEvent.ARRIVAL) {
                    str3 = DeliverActivity.this.TAG;
                    Pdlog.m3273d(str3, "wakeupListener show is ARRIVAL");
                    DeliverActivity.this.stopArrivedVoice();
                    DeliverActivity deliverActivity2 = DeliverActivity.this;
                    i2 = deliverActivity2.TYPE_PAUSE_FEATURE_AIVOICE;
                    deliverActivity2.onPauseFeatureChange(i2, false);
                    DeliverActivity.this.stopArrivedMusic();
                    return;
                }
                z3 = DeliverActivity.this.isDeliverActive;
                if (z3) {
                    return;
                }
                str2 = DeliverActivity.this.TAG;
                Pdlog.m3273d(str2, "wakeupListener show is not ARRIVAL");
                DeliverActivity deliverActivity3 = DeliverActivity.this;
                i = deliverActivity3.TYPE_PAUSE_FEATURE_AIVOICE;
                DeliverActivity.onPauseFeatureChange$default(deliverActivity3, i, false, 2, null);
            }
        };
        this.onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onTouchSensorPlaceListener$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                invoke2(place, event);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
                Intrinsics.checkParameterIsNotNull(place, "place");
                Intrinsics.checkParameterIsNotNull(event, "event");
                DeliveryTrack.INSTANCE.onTouch(place.ordinal(), event.getValue());
            }
        };
        this.onTouchSensorAnimationListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onTouchSensorAnimationListener$1
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
                DeliveryContract.DeliveryEvent deliveryEvent;
                DeliveryContract.DeliveryEvent deliveryEvent2;
                int i;
                int i2;
                str = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("onTouchSensorAnimationListener ");
                sb.append(z);
                sb.append(" , current status = ");
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                sb.append(deliveryEvent);
                Pdlog.m3273d(str, sb.toString());
                deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                if (deliveryEvent2 == DeliveryContract.DeliveryEvent.PAUSE) {
                    if (z) {
                        DeliverActivity deliverActivity = DeliverActivity.this;
                        i2 = deliverActivity.TYPE_PAUSE_FEATURE_TOUCH;
                        DeliverActivity.onPauseFeatureChange$default(deliverActivity, i2, false, 2, null);
                    } else {
                        DeliverActivity deliverActivity2 = DeliverActivity.this;
                        i = deliverActivity2.TYPE_PAUSE_FEATURE_NORMAL;
                        DeliverActivity.onPauseFeatureChange$default(deliverActivity2, i, false, 2, null);
                    }
                }
            }
        };
        this.TYPE_PAUSE_FEATURE_DIALOG = 1;
        this.TYPE_PAUSE_FEATURE_AIVOICE = 2;
        this.TYPE_PAUSE_FEATURE_TOUCH = 3;
        this.TYPE_PAUSE_FEATURE_ERROR = 4;
        this.currentPauseFeature = this.TYPE_PAUSE_FEATURE_NORMAL;
        this.singleBatteryListener = new DeliverActivity$singleBatteryListener$1(this);
        this.onOutletSelectClick = new OnLazyClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$onOutletSelectClick$1
            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyClickListener
            public void onSingleClick() {
                String str;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "onOutletSelectClick");
                DeliverActivity.this.showOutletSelectDialog();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callReturn() {
        ReturnPointBean callReturnPoint = getCallSetVM().getCallReturnPoint();
        if (callReturnPoint == null) {
            startDeliverTaskEditActivity(0);
            return;
        }
        if (Intrinsics.areEqual(callReturnPoint.getMapName(), RobotMapManager.INSTANCE.getDefaultPdmap())) {
            String pointName = callReturnPoint.getPointName();
            if (Intrinsics.areEqual(pointName, RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen())) {
                Intent putExtra = new Intent(this, (Class<?>) TurnBackActivity.class).putExtra(TurnBackActivity.KEY_MODE, this.currentMode).putExtra(TurnBackActivity.CUSTOM_OUTLET, this.customOutlet);
                Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(this@DeliverActiv…TOM_OUTLET, customOutlet)");
                jumpAndFinish(IntentExtKt.saveSceneId(putExtra, DeliveryTrack.INSTANCE.getSessionId()));
                return;
            } else if (Intrinsics.areEqual(pointName, RobotMapManager.INSTANCE.getCurrentMapUsherChosen())) {
                jumpAndFinish(IntentExtKt.saveSceneId(new Intent(this, (Class<?>) GotoWelcomeAreaActivity.class), DeliveryTrack.INSTANCE.getSessionId()));
                return;
            } else {
                startDeliverTaskEditActivity(0);
                return;
            }
        }
        startDeliverTaskEditActivity(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetBtnFinish() {
        if (((Button) _$_findCachedViewById(C4188R.id.btn_finish)) != null) {
            if (SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false)) {
                Button btn_finish = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
                Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
                btn_finish.setText(getString(C4188R.string.pdStr7_110));
            } else {
                Button btn_finish2 = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
                Intrinsics.checkExpressionValueIsNotNull(btn_finish2, "btn_finish");
                String customFinishBtnContent = Constans.INSTANCE.getCustomFinishBtnContent();
                if (customFinishBtnContent.length() == 0) {
                    customFinishBtnContent = getString(C4188R.string.pdStr2_15);
                    Intrinsics.checkExpressionValueIsNotNull(customFinishBtnContent, "getString(R.string.pdStr2_15)");
                }
                btn_finish2.setText(customFinishBtnContent);
                Button button = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
                int dimension = ((int) getResources().getDimension(C4188R.dimen.delivery_arrive_content_ll_w)) - 100;
                String customFinishBtnContent2 = Constans.INSTANCE.getCustomFinishBtnContent();
                if (customFinishBtnContent2.length() == 0) {
                    customFinishBtnContent2 = getString(C4188R.string.pdStr2_15);
                    Intrinsics.checkExpressionValueIsNotNull(customFinishBtnContent2, "getString(R.string.pdStr2_15)");
                }
                UiUtils.adjustTvTextSize(button, dimension, customFinishBtnContent2);
            }
        }
        DeliveryPresenter deliverPresenter = getDeliverPresenter();
        if (deliverPresenter != null) {
            deliverPresenter.onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClickDeliverFinish() {
        if (this.isRelease) {
            Pdlog.m3274e(this.TAG, "goToCruise failed isRelease ");
            return;
        }
        if (Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch()) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            this.motionEventHelper.setCanHandleMovingEvent(true);
            playArrivedFinish(true);
        } else {
            getDeliverPresenter().actionFinish();
        }
        getMBluetoothHelper().disConnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopArrivedMusic() {
        MusicPlayerHelper.getInstance().pausePlay();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playArriveMusic() {
        if (this.currentMode != 3) {
            MusicPlayerHelper.getInstance().startPlay(this.currentMode == 1 ? ModeEnum.BIRTHDAY : ModeEnum.ARRIVED);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Pdlog.m3273d(this.TAG, "onCreate");
        setContentView(C4188R.layout.activity_deliver);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new DeliverActivity$onCreate$1(null), 2, null);
        this.currentMode = getIntent().getIntExtra("DELIVER_MODE", 0);
        this.currentFromMode = CallFromType.INSTANCE.fromType(getIntent().getIntExtra(DELIVER_FROM, 0));
        resetMusicProgress();
        DeliverTaskEditActivity.INSTANCE.setCanFillIn(false);
        if (!TableTaskManager.INSTANCE.getHasTask()) {
            Pdlog.m3274e(this.TAG, "checkTableTask failed");
            startDeliverTaskEditActivity(0);
            return;
        }
        initAiVoice();
        bindPresenter();
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).addOnFaceClickListener(this.onFaceAnimationViewClick);
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTouchLostLocationCancelCallback(this.locationLostTouchCancelCallback);
        startDeliverTask();
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        getDeliverPresenter().setCallNaviState(LoRaManager.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void onPauseFeatureChange$default(DeliverActivity deliverActivity, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        deliverActivity.onPauseFeatureChange(i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onPauseFeatureChange(int type, boolean isSetPause) {
        Pdlog.m3273d(this.TAG, "onPauseFeatureChange type = " + type);
        this.currentPauseFeature = type;
        if (type == this.TYPE_PAUSE_FEATURE_NORMAL) {
            if (isSetPause) {
                getDeliverPresenter().actionPause();
            }
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            return;
        }
        if (type == this.TYPE_PAUSE_FEATURE_DIALOG) {
            getDeliverPresenter().actionPauseNoTimer();
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
        } else {
            if (type == this.TYPE_PAUSE_FEATURE_AIVOICE) {
                if (isSetPause) {
                    getDeliverPresenter().actionPauseNoTimer();
                }
                TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
                this.touchSensorEventHelper.stopCurrentAnimation();
                return;
            }
            if (type == this.TYPE_PAUSE_FEATURE_TOUCH) {
                getDeliverPresenter().actionPauseNoTimer();
            } else if (type == this.TYPE_PAUSE_FEATURE_ERROR) {
                AiVoiceManager.INSTANCE.stopAiRecording();
                TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
                getDeliverPauseLayout().hideCountdownLayout();
            }
        }
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.setWorkMode(WorkMode.Delivery);
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    private final void startDeliverTask() {
        Pdlog.m3273d(this.TAG, "startDeliverTask");
        ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
        if (isCallDirect()) {
            if ((allTask != null ? Integer.valueOf(allTask.size()) : null).intValue() > 0) {
                ArrayList<DeliveryModel> allDestinations = allTask.get(0).getAllDestinations();
                if ((allDestinations != null ? Integer.valueOf(allDestinations.size()) : null).intValue() > 0) {
                    this.mTargetName = allDestinations.get(0).getDestination();
                }
            }
        }
        getDeliverPresenter().actionInitTask(getDeliverMode(), allTask, TableTaskManager.INSTANCE.getSortType(), TableTaskManager.INSTANCE.getMovePerformance());
        getDeliverPresenter().actionActive();
    }

    private final boolean isCallDirect() {
        return this.currentMode == 4;
    }

    private final DeliveryMode getDeliverMode() {
        int i = this.currentMode;
        if (i == 0) {
            return DeliveryMode.GENERAL;
        }
        if (i == 1) {
            return DeliveryMode.BIRTHDAY;
        }
        if (i == 2) {
            return DeliveryMode.DIRECT;
        }
        if (i == 3) {
            return DeliveryMode.SPECIAL;
        }
        if (i == 4) {
            return DeliveryMode.CALL_DIRECT;
        }
        return DeliveryMode.GENERAL;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Pdlog.m3273d(this.TAG, "onWindowFocusChanged " + hasFocus);
        if (hasFocus && this.isFirstStart) {
            this.isFirstStart = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseDataAndFinish() {
        Pdlog.m3274e(this.TAG, "releaseDataAndFinish");
        DeliveryPresenter deliverPresenter = getDeliverPresenter();
        if (deliverPresenter != null) {
            deliverPresenter.actionPauseNoTimer();
        }
        DeliveryPresenter deliverPresenter2 = getDeliverPresenter();
        if (deliverPresenter2 != null) {
            deliverPresenter2.actionCancelAll();
        }
        release();
        finish();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent i) {
        Intrinsics.checkParameterIsNotNull(i, "i");
        Pdlog.m3273d(this.TAG, "jumpAndFinish");
        release();
        startActivity(i);
        finish();
    }

    private final void release() {
        this.isRelease = true;
        CoroutineScopeKt.cancel$default(this.mainScope, null, 1, null);
        MusicPlayerHelper.getInstance().release();
        VoiceInteractionDialog voiceInteractionDialog = this.voiceInteractionDialog;
        if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
            voiceInteractionDialog.dismiss();
        }
        this.mainHandler.removeCallbacksAndMessages(null);
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        unbindPresenter();
        stopArrivedVoice();
        Disposable disposable = this.callCompleteCountDown;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private final TrackType getTrackType() {
        int i = this.currentMode;
        if (i == 0) {
            return TrackType.DELIVERY;
        }
        if (i == 1) {
            return TrackType.BIRTHDAY;
        }
        if (i == 2) {
            return TrackType.DIRECT;
        }
        if (i == 3) {
            return TrackType.SPECIAL;
        }
        if (i == 4) {
            return TrackType.CALL_DIRECT;
        }
        return TrackType.DELIVERY;
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
        int i = this.currentMode;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@DeliverActivity::class.java.simpleName");
        deliveryTrack.onCreateTask(i, simpleName, this.currentFromMode);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this.singleBatteryListener);
        DeliverActivity deliverActivity = this;
        this.runningErrorHelper.bind(this.onErrorDialogShowStatus, this.onLostLocationLostCallback, this.onErrorClearCallback, this.onFallDropCallBack, deliverActivity, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$bindPresenter$1
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
                DeliveryPresenter deliverPresenter;
                deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                deliverPresenter.actionActive();
            }
        });
        BeeperCallHelper.bind$default(this.beeperCallHelper, deliverActivity, false, false, 4, null);
        this.beeperCallHelper.setOnCancelCallListener(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$bindPresenter$2
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
                DeliveryPresenter deliverPresenter;
                int i2;
                String str2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "bindPresenter : beeperCallHelper onCancelCallListener");
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.RemoteCancel);
                deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                deliverPresenter.actionCancelAll();
                Intent intent = new Intent(DeliverActivity.this, (Class<?>) TurnBackActivity.class);
                i2 = DeliverActivity.this.currentMode;
                Intent putExtra = intent.putExtra(TurnBackActivity.KEY_MODE, i2);
                str2 = DeliverActivity.this.customOutlet;
                putExtra.putExtra(TurnBackActivity.CUSTOM_OUTLET, str2);
                DeliverActivity.this.jumpAndFinish(intent);
            }
        });
        DeliverActivity deliverActivity2 = this;
        getAutoResumeCountDownPresenter().replaceView(deliverActivity2);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        touchSensorEventHelper.bindPresenter(face_animation_view);
        this.touchSensorEventHelper.setOnVoiceStateListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$bindPresenter$3
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
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                Intrinsics.checkParameterIsNotNull(it, "it");
                if (it == PlayEvent.COMPLETION_ONCE || it == PlayEvent.STOP) {
                    DeliverActivity.this.playArrivedWaitVoice();
                }
                mDangerousAreaTipHelper = DeliverActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        this.touchSensorEventHelper.setOnAnimationShowListener(this.onTouchSensorAnimationListener);
        getDeliverPresenter().replaceView(deliverActivity2);
        this.motionEventHelper.setOnTheWayAnimation(getOnTheWayAni$default(this, false, 1, null));
        MotionEventHelper motionEventHelper = this.motionEventHelper;
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = this.movingLoopVoiceTask;
        FaceVideoView face_animation_view2 = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view2, "face_animation_view");
        motionEventHelper.bind(voiceTaskWrapper, face_animation_view2, this.scheduleEventLister);
        this.motionEventHelper.setOnVoicePlayListener(new Function1<PlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$bindPresenter$4
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
                DangerousAreaTipHelper mDangerousAreaTipHelper;
                Intrinsics.checkParameterIsNotNull(it, "it");
                mDangerousAreaTipHelper = DeliverActivity.this.getMDangerousAreaTipHelper();
                mDangerousAreaTipHelper.onStateChange(it);
            }
        });
        this.singleBatteryListener.showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        MusicPlayerHelper.getInstance().setMusicPlayerStateCallback(new SimpleMusicPlayerCallbck() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$bindPresenter$5
            @Override // com.pudutech.bumblebee.robot_ui.p054ui.helper.SimpleMusicPlayerCallbck, com.pudutech.mpcomponent.interf.IMusicPlayerStateCallback
            public void onPrepared() {
                String str;
                DeliveryContract.DeliveryEvent deliveryEvent;
                MotionEventHelper motionEventHelper2;
                super.onPrepared();
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "setMusicPlayerStateCallback onPrepared");
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                if (deliveryEvent == DeliveryContract.DeliveryEvent.ON_THE_WAY) {
                    motionEventHelper2 = DeliverActivity.this.motionEventHelper;
                    motionEventHelper2.stopMovingVoice();
                }
            }
        });
        getRemoteDeliveryPresenter().replaceView(deliverActivity2);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionAccomplished(this.onAiVoiceFinish);
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
        DeliverActivity deliverActivity3 = this;
        getMDangerousAreaTipHelper().bindLifecycle(deliverActivity3);
        getMBluetoothHelper().bindLifecycle(deliverActivity3);
    }

    private final void unbindPresenter() {
        Pdlog.m3273d(this.TAG, "unbindPresenter");
        this.motionEventHelper.unBind();
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this.singleBatteryListener);
        this.runningErrorHelper.unbind();
        this.touchSensorEventHelper.unBindPresent();
        DeliverActivity deliverActivity = this;
        getDeliverPresenter().removeView(deliverActivity);
        getAutoResumeCountDownPresenter().removeView(deliverActivity);
        this.beeperCallHelper.unbind();
        getRemoteDeliveryPresenter().removeView(deliverActivity);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionAccomplished((Function0) null);
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
    }

    private final void showOnTheWayStatus(ViewModel model) {
        Pdlog.m3273d(this.TAG, "showOnTheWayStatus");
        AiVoiceManager.INSTANCE.stopAiRecording();
        this.touchSensorEventHelper.setCanHandle(true, true);
        this.motionEventHelper.setCanHandleMovingEvent(true);
        hideErrorTipDialog();
        PeripheralsSceneUtil.INSTANCE.showDeliveryOnTheWay();
        showOnTheWayLayout(model.getDestination());
        this.isDeliverActive = true;
        int i = this.currentMode;
        if (i != 3) {
            if (i == 1) {
                if (Constans.INSTANCE.isBirthdayMusicOnTheWay()) {
                    MusicPlayerHelper.getInstance().startPlay(ModeEnum.BIRTHDAY);
                    return;
                }
                return;
            }
            MusicPlayerHelper.getInstance().startPlay(ModeEnum.DELIVERY_AND_RETURNING);
            return;
        }
        String str = SpUtils.get(this, "key_spaces_mode_select_music", "");
        String str2 = str;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            Music music = new Music();
            music.setPath(str);
            Pdlog.m3273d(this.TAG, "MODE_SPECIAL music is " + str);
            MusicPlayerHelper.getInstance().playSinleSong(music);
            return;
        }
        Pdlog.m3274e(this.TAG, "MODE_SPECIAL music is null");
    }

    private final boolean isShowDeliverFace() {
        return Constans.INSTANCE.getDeliverFaceSwitch() && this.currentMode != 4;
    }

    private final void showDeliverPauseStatus(ViewModel model) {
        Pdlog.m3273d(this.TAG, "showDeliverPauseStatus");
        this.isDeliverActive = false;
        this.motionEventHelper.setCanHandleMovingEvent(false);
        getDeliverPauseLayout().showView();
        getDeliverPauseLayout().setTrayInfo(model.getTrays(), model.getDestination());
        hideOnTheWayLayout();
        getDeliverArriverLayout().hideView();
        if (this.runningErrorHelper.isErrorShowing()) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            getDeliverPauseLayout().hideCountdownLayout();
        } else {
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        }
        PeripheralsSceneUtil.INSTANCE.showDeliveryPause();
        Pdlog.m3273d(this.TAG, "pause play");
        MusicPlayerHelper.getInstance().pausePlay();
    }

    private final void showOnTheWayLayout(String target) {
        if (this.lastEventIsDone && !Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch()) {
            playArrivedFinish(false);
        } else {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(getOnTheWayAni$default(this, false, 1, null));
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(target);
        }
    }

    private final void hideOnTheWayLayout() {
        Pdlog.m3273d(this.TAG, "hideOnTheWayLayout");
        ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
    }

    private final void showDeliverArrivingStatus(ViewModel model) {
        Pdlog.m3273d(this.TAG, "showDeliverArrivingStatus");
        String destination = model.getDestination();
        if (!TextUtils.isEmpty(destination)) {
            TtsVoiceManager.INSTANCE.playTtsVoice(destination, false, null);
        }
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        this.motionEventHelper.setCanHandleMovingEvent(false);
        hideOnTheWayLayout();
        getDeliverPauseLayout().hideView();
        getDeliverArriverLayout().showArrivingLayout(model.getTrays(), model.getDestination());
        int i = 0;
        for (Object obj : model.getTrays()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TrayModel) obj).getDeliveryModel(model.getDestination()) != null) {
                PeripheralsSceneUtil.INSTANCE.showDeliveryArriving(Integer.valueOf(i), model.getDeliveryMode() == DeliveryMode.GENERAL);
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDeliverArrivedStatus(ViewModel model) {
        Pdlog.m3273d(this.TAG, "showDeliverArrivedStatus");
        hideOnTheWayLayout();
        this.motionEventHelper.setCanHandleMovingEvent(false);
        getDeliverPauseLayout().hideView();
        getDeliverArriverLayout().showArrivedLayout(model.getTrays(), model.getDestination());
        getLotteryResultLayout().setDestination(model.getDestination());
        playArrivedVoice(model);
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
        PeripheralsSceneUtil.INSTANCE.showDeliveryArrived();
        PeripheralsSceneUtil.INSTANCE.showLedString(getLedDestinationStr(model));
        int i = 0;
        for (Object obj : model.getTrays()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TrayModel) obj).getDeliveryModel(model.getDestination()) != null) {
                PeripheralsSceneUtil.showDeliveryArrivedTray$default(PeripheralsSceneUtil.INSTANCE, i, false, 2, null);
            }
            i = i2;
        }
        this.motionEventHelper.showLedScreenString();
        int i3 = this.currentMode;
        if (i3 == 3) {
            MusicPlayerHelper.getInstance().pausePlay();
            return;
        }
        if (i3 == 1) {
            if (Constans.INSTANCE.isBirthdayMusicOnTheWay()) {
                if (Constans.INSTANCE.isBirthdayMusicArrival()) {
                    return;
                }
                stopArrivedMusic();
                return;
            } else {
                if (Constans.INSTANCE.isBirthdayMusicArrival()) {
                    MusicPlayerHelper.getInstance().startPlay(ModeEnum.BIRTHDAY);
                    return;
                }
                return;
            }
        }
        MusicPlayerHelper.getInstance().startPlay(ModeEnum.ARRIVED);
    }

    private final void playArrivedVoice(ViewModel model) {
        DeliverActivity deliverActivity = this;
        String str = SpUtils.get(deliverActivity, "key_spaces_mode_select_arrive_voice", "");
        String str2 = SpUtils.get(deliverActivity, Constans.KEY_SPACES_MODE_SELECT_ARRIVE_VOICE_TYPE, "");
        if (this.currentMode == 3) {
            String str3 = str;
            if (!(str3 == null || StringsKt.isBlank(str3))) {
                try {
                    if (Intrinsics.areEqual(str2, VoiceType.APPOINT_VOICE.toString())) {
                        VoiceTask voiceTask = new VoiceTask(-1L, new VoiceProperty(-1L, VoiceItem.voice10_3, Integer.parseInt(str)));
                        voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$playArrivedVoice$1
                            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                            public void onStateChange(PlayEvent event) {
                                String str4;
                                DeliveryContract.DeliveryEvent deliveryEvent;
                                DeliveryContract.DeliveryEvent deliveryEvent2;
                                Intrinsics.checkParameterIsNotNull(event, "event");
                                str4 = DeliverActivity.this.TAG;
                                StringBuilder sb = new StringBuilder();
                                sb.append("trayVoiceTask PlayEvent = ");
                                sb.append(event);
                                sb.append(" , currentEventStatus = ");
                                deliveryEvent = DeliverActivity.this.currentEventStatus;
                                sb.append(deliveryEvent);
                                Pdlog.m3273d(str4, sb.toString());
                                if (event == PlayEvent.COMPLETION_ONCE) {
                                    deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                                    if (deliveryEvent2 == DeliveryContract.DeliveryEvent.ARRIVAL) {
                                        DeliverActivity.this.playArrivedWaitVoice();
                                    }
                                }
                            }
                        });
                        TtsVoiceHelper.INSTANCE.stopCruiseTts();
                        VoicePlayer.INSTANCE.play(voiceTask);
                        return;
                    }
                    if (Intrinsics.areEqual(str2, VoiceType.TTS_VOICE.toString())) {
                        VoicePlayer.INSTANCE.stop();
                        TtsVoiceHelper.TtsConfigData ttsConfigData = (TtsVoiceHelper.TtsConfigData) null;
                        for (TtsVoiceHelper.TtsConfigData ttsConfigData2 : TtsVoiceHelper.INSTANCE.getTtsConfigList(TtsVoiceHelper.TtsVoiceType.SPECIAL_MODE_ARRIVE)) {
                            if (Intrinsics.areEqual(str, ttsConfigData2.getName())) {
                                ttsConfigData = ttsConfigData2;
                            }
                        }
                        if (ttsConfigData != null) {
                            TtsVoiceHelper.INSTANCE.playPcm(ttsConfigData, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$playArrivedVoice$$inlined$let$lambda$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                                    invoke2(audioPlayEvent);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                                    DeliveryContract.DeliveryEvent deliveryEvent;
                                    Intrinsics.checkParameterIsNotNull(it, "it");
                                    if (it == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                                        deliveryEvent = DeliverActivity.this.currentEventStatus;
                                        if (deliveryEvent == DeliveryContract.DeliveryEvent.ARRIVAL) {
                                            DeliverActivity.this.playArrivedWaitVoice();
                                        }
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e) {
                    Pdlog.m3274e(this.TAG, Log.getStackTraceString(e));
                }
            }
        }
        VoiceTask merchantVoiceTask = VoicePackageHelper.INSTANCE.isSelectMerchantTts() ? getMerchantVoiceTask(model) : getTrayVoiceTask(model);
        merchantVoiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$playArrivedVoice$4
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                String str4;
                DeliveryContract.DeliveryEvent deliveryEvent;
                DeliveryContract.DeliveryEvent deliveryEvent2;
                Intrinsics.checkParameterIsNotNull(event, "event");
                str4 = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("trayVoiceTask PlayEvent = ");
                sb.append(event);
                sb.append(" , currentEventStatus = ");
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                sb.append(deliveryEvent);
                Pdlog.m3273d(str4, sb.toString());
                if (event == PlayEvent.COMPLETION_ONCE) {
                    deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                    if (deliveryEvent2 == DeliveryContract.DeliveryEvent.ARRIVAL) {
                        DeliverActivity.this.playArrivedWaitVoice();
                    }
                }
            }
        });
        TtsVoiceWrapperPlayer.VoiceTaskWrapper voiceTaskWrapper = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(merchantVoiceTask, TtsVoiceWrapperPlayer.PlayType.DELIVER_ARRIVED, this.ttsConfigDataArrived);
        if (this.currentMode == 1) {
            if (this.voicePlayerHelper.checkFileExist(VoiceItem.voice50_1) || TtsVoiceHelper.INSTANCE.isOpen(deliverActivity, TtsVoiceHelper.TtsVoiceType.BIRTHDAY_TYPE)) {
                voiceTaskWrapper = new TtsVoiceWrapperPlayer.VoiceTaskWrapper(merchantVoiceTask, TtsVoiceWrapperPlayer.PlayType.BIRTHDAY_MODE, null, 4, null);
                Pdlog.m3273d(this.TAG, "MODE_BIRTHDAY play");
            }
        } else {
            this.looperArrivedVoiceTask = (TtsVoiceWrapperPlayer.VoiceTaskWrapper) null;
            if ((BusinessSetting.INSTANCE.getDelayAutoFinishSwitch() && merchantVoiceTask.getList().size() > 1) || TtsVoiceHelper.INSTANCE.isOpen(deliverActivity, TtsVoiceHelper.TtsVoiceType.DELIVER_TYPE) || voiceTaskWrapper.getTtsConfigData() != null) {
                this.looperArrivedVoiceTask = voiceTaskWrapper;
            }
        }
        TtsVoiceWrapperPlayer.INSTANCE.play(voiceTaskWrapper, new Function1<AudioTrackUtils.AudioPlayEvent, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$playArrivedVoice$5
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AudioTrackUtils.AudioPlayEvent audioPlayEvent) {
                invoke2(audioPlayEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AudioTrackUtils.AudioPlayEvent it) {
                String str4;
                DeliveryContract.DeliveryEvent deliveryEvent;
                DeliveryContract.DeliveryEvent deliveryEvent2;
                Intrinsics.checkParameterIsNotNull(it, "it");
                str4 = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("trayVoiceTask tts voice ");
                deliveryEvent = DeliverActivity.this.currentEventStatus;
                sb.append(deliveryEvent);
                Pdlog.m3273d(str4, sb.toString());
                if (it == AudioTrackUtils.AudioPlayEvent.COMPLETE) {
                    deliveryEvent2 = DeliverActivity.this.currentEventStatus;
                    if (deliveryEvent2 == DeliveryContract.DeliveryEvent.ARRIVAL) {
                        DeliverActivity.this.playArrivedWaitVoice();
                    }
                }
            }
        });
    }

    private final boolean isSupportEuropeanPlayFormat() {
        DeliverActivity deliverActivity = this;
        return LanguageUtils.INSTANCE.isDE(deliverActivity) || LanguageUtils.INSTANCE.isFr(deliverActivity) || LanguageUtils.INSTANCE.isRU(deliverActivity);
    }

    private final VoiceTask getMerchantVoiceTask(ViewModel model) {
        if (this.currentMode == 1 && this.voicePlayerHelper.checkFileExist(VoiceItem.voice50_1)) {
            Pdlog.m3273d(this.TAG, "getMerchantVoiceTask() return voice50_1");
            return new VoiceTask(-1L, VoiceItem.voice50_1);
        }
        TtsVoiceProperty ttsVoiceProperty = (TtsVoiceProperty) null;
        String destination = model.getDestination();
        if (!(destination == null || destination.length() == 0)) {
            ttsVoiceProperty = TtsVoiceManager.INSTANCE.getTtsVoiceProperty(model.getDestination());
        }
        if (ttsVoiceProperty == null) {
            return new VoiceTask(-1L, VoiceItem.voice8_4);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(ttsVoiceProperty);
        arrayList.add(new VoiceProperty(0L, VoiceItem.voice8_2, -1));
        if (this.voicePlayerHelper.checkFileExist(VoiceItem.voice8_3)) {
            int i = 0;
            for (IndexedValue indexedValue : CollectionsKt.withIndex(model.getTrays())) {
                DeliveryModel current = ((TrayModel) indexedValue.getValue()).getCurrent();
                if ((current != null ? current.getStatus() : null) == TaskStatus.ARRIVAL) {
                    arrayList.add(new VoiceProperty(0L, VoiceUtils.INSTANCE.toTrayItem(indexedValue.getIndex() + 1), -1));
                    i++;
                }
            }
            if (i > 0) {
                arrayList.add(new VoiceProperty(0L, VoiceItem.voice8_3, -1));
            }
        }
        Pdlog.m3273d(this.TAG, "getMerchantVoiceTask() list = " + arrayList);
        Object[] array = arrayList.toArray(new Property[0]);
        if (array != null) {
            Property[] propertyArr = (Property[]) array;
            return new VoiceTask(-1L, (Property[]) Arrays.copyOf(propertyArr, propertyArr.length));
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final VoiceTask getTrayVoiceTask(ViewModel model) {
        VoiceItem[] items;
        if (this.currentMode == 1 && this.voicePlayerHelper.checkFileExist(VoiceItem.voice50_1)) {
            Pdlog.m3273d(this.TAG, "return voice50_1");
            return new VoiceTask(-1L, VoiceItem.voice50_1);
        }
        String destination = model.getDestination();
        DeliverActivity deliverActivity = this;
        boolean isKo = LanguageUtils.INSTANCE.isKo(deliverActivity);
        boolean isSupportEuropeanPlayFormat = isSupportEuropeanPlayFormat();
        if ((!VoiceUtils.INSTANCE.checkDigitAndLetterOnly(destination) && !isKo) || !this.voicePlayerHelper.checkFileExist(VoiceItem.voice8_2) || ((isKo && !this.voicePlayerHelper.checkFileExist(VoiceItem.voice8_3)) || (isSupportEuropeanPlayFormat && !VoiceUtils.INSTANCE.checkEuropeanPlayFormat(destination)))) {
            return new VoiceTask(-1L, VoiceItem.voice8_4);
        }
        ArrayList arrayList = new ArrayList();
        if (LanguageUtils.INSTANCE.isEnglish(deliverActivity)) {
            Pdlog.m3273d(this.TAG, "is english , add 8_5 voice");
            arrayList.add(new Pair(0L, VoiceItem.voice8_5));
        }
        if (VoiceUtils.INSTANCE.checkDoubleDigits(destination) && (LanguageUtils.INSTANCE.isZh(deliverActivity) || LanguageUtils.INSTANCE.isZhTw())) {
            items = VoiceUtils.INSTANCE.toItems(destination);
        } else if (isKo) {
            items = new VoiceItem[0];
        } else if (isSupportEuropeanPlayFormat) {
            items = VoiceUtils.INSTANCE.toEuropeanPlayFormat(destination);
        } else {
            items = VoiceUtils.INSTANCE.toItems(destination);
        }
        for (VoiceItem voiceItem : items) {
            arrayList.add(new Pair(0L, voiceItem));
        }
        arrayList.add(new Pair(0L, VoiceItem.voice8_2));
        if (this.voicePlayerHelper.checkFileExist(VoiceItem.voice8_3)) {
            int i = 0;
            for (IndexedValue indexedValue : CollectionsKt.withIndex(model.getTrays())) {
                DeliveryModel current = ((TrayModel) indexedValue.getValue()).getCurrent();
                if ((current != null ? current.getStatus() : null) == TaskStatus.ARRIVAL) {
                    if (i == 0) {
                        arrayList.add(new Pair(300L, VoiceUtils.INSTANCE.toTrayItem(indexedValue.getIndex() + 1)));
                    } else {
                        arrayList.add(new Pair(0L, VoiceUtils.INSTANCE.toTrayItem(indexedValue.getIndex() + 1)));
                    }
                    i++;
                }
            }
            if (i > 0) {
                arrayList.add(new Pair(0L, VoiceItem.voice8_3));
            }
        }
        Object[] array = arrayList.toArray(new Pair[0]);
        if (array != null) {
            Pair[] pairArr = (Pair[]) array;
            return new VoiceTask(-1L, (Pair<Long, ? extends VoiceItem>[]) Arrays.copyOf(pairArr, pairArr.length));
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playArrivedWaitVoice() {
        long arriveVoiceLooperTime;
        Pdlog.m3273d(this.TAG, "playArrivedWaitVoice");
        stopArrivedVoice();
        if (this.ttsConfigDataArrived != null) {
            PalletTtsScheme palletTtsScheme = this.palletTtsScheme;
            if (palletTtsScheme == null) {
                Intrinsics.throwNpe();
            }
            arriveVoiceLooperTime = palletTtsScheme.getInterval() * 1000;
        } else {
            arriveVoiceLooperTime = getArriveVoiceLooperTime();
        }
        Pdlog.m3273d(this.TAG, "playArrivedWaitVoice, loopTime: " + arriveVoiceLooperTime);
        if (this.currentMode != 1) {
            this.mainHandler.sendEmptyMessageDelayed(this.PLAY_ARRIVED_VOICE, arriveVoiceLooperTime);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopArrivedVoice() {
        this.mainHandler.removeMessages(this.PLAY_ARRIVED_VOICE);
    }

    private final void dismissPalletDialog() {
        this.mainHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$dismissPalletDialog$1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                String threadInfo;
                DeliveryPalletAlarmDialog deliveryPalletAlarmDialog;
                str = DeliverActivity.this.TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("dialog");
                threadInfo = DeliverActivity.this.getThreadInfo();
                sb.append(threadInfo);
                Pdlog.m3275i(str, sb.toString());
                deliveryPalletAlarmDialog = DeliverActivity.this.deliveryPalletAlartDialog;
                if (deliveryPalletAlarmDialog != null) {
                    deliveryPalletAlarmDialog.dismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getThreadInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getId());
        sb.append(']');
        sb.append(Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper()) ? " is UI-Thread" : "");
        return sb.toString();
    }

    private final String getLedDestinationStr(ViewModel model) {
        int i = this.currentMode;
        if (i != 1) {
            if (i == 3) {
                if (Constans.INSTANCE.getSpecialModeText().length() > 0) {
                    return Constans.INSTANCE.getSpecialModeText();
                }
                return model.getDestination();
            }
            return model.getDestination();
        }
        if (TextUtils.isEmpty(Constans.INSTANCE.getBirthdayText())) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr9_2, new Object[]{model.getDestination()});
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr9_2, model.destination)");
            Object[] objArr = new Object[0];
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return format;
        }
        return Constans.INSTANCE.getBirthdayText();
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.ViewInterface
    public void showViewModelChanged(DeliveryContract.DeliveryEvent event, ViewModel model) {
        boolean z;
        Object obj;
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showViewModelChanged event = " + event + " , destination = " + model.getDestination() + " , model = " + model);
        TableTaskManager.INSTANCE.setCurrentTask(model.getDestination());
        this.currentViewModel = model;
        this.lastEventIsDone = this.currentEventStatus == DeliveryContract.DeliveryEvent.DONE;
        switch (event) {
            case ON_THE_WAY:
                DeliveryTrack.INSTANCE.onMove();
                if (this.isNeedInitCurrentPalletTts) {
                    this.isNeedInitCurrentPalletTts = false;
                    initCurrentPalletTts(model.getDestination());
                    showOnTheWayStatus(model);
                } else if (this.currentEventStatus != event) {
                    showOnTheWayStatus(model);
                }
                if (!isShowDeliverFace()) {
                    ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).setTarget(model.getDestination());
                }
                this.motionEventHelper.setCurrentMovingText(getLedDestinationStr(model));
                break;
            case APPROACHING:
                int i = this.currentMode;
                if (i != 2 && i != 4) {
                    showDeliverArrivingStatus(model);
                    break;
                }
                break;
            case ARRIVAL:
                DeliveryTrack.INSTANCE.onArrive(model.getDestination());
                CallSettingRepo.INSTANCE.startReportArrive(model.getDestination());
                int i2 = this.currentMode;
                if (i2 != 2 && i2 != 4) {
                    List<TableWatchBindItem> watchTableBindInfo = Constans.INSTANCE.getWatchTableBindInfo();
                    if (watchTableBindInfo != null) {
                        z = false;
                        for (TableWatchBindItem tableWatchBindItem : watchTableBindInfo) {
                            Iterator<T> it = tableWatchBindItem.getTableBindItems().iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    obj = it.next();
                                    TableBindItem tableBindItem = (TableBindItem) obj;
                                    if (Intrinsics.areEqual(tableBindItem.getName(), model.getDestination()) && tableBindItem.isBind()) {
                                    }
                                } else {
                                    obj = null;
                                }
                            }
                            if (((TableBindItem) obj) != null) {
                                MessageProtobuf.Msg.Builder createMsgBuilder = ImsPresenterHolder.INSTANCE.createMsgBuilder(ImsTaskObjHolder.INSTANCE.buildTask(ShortUUID.INSTANCE.randomUUID(), RemoteTaskState.ARRIVED.getStateId()), ImsTaskObjHolder.INSTANCE.buildCallPoint(model.getDestination(), 1));
                                if (createMsgBuilder != null) {
                                    createMsgBuilder.setReceiver(tableWatchBindItem.getMac());
                                }
                                Pdlog.m3273d(this.TAG, "送餐到达,桌号绑定的手表为" + tableWatchBindItem.getMac());
                                ImsTaskObjHolder.INSTANCE.setRemoteMsg(createMsgBuilder != null ? createMsgBuilder.build() : null);
                                MessageProtobuf.Msg remoteMsg = ImsTaskObjHolder.INSTANCE.getRemoteMsg();
                                if ((remoteMsg != null ? remoteMsg.getMsgId() : null) != null) {
                                    List<String> list = this.arrivedMsgIds;
                                    MessageProtobuf.Msg remoteMsg2 = ImsTaskObjHolder.INSTANCE.getRemoteMsg();
                                    String msgId = remoteMsg2 != null ? remoteMsg2.getMsgId() : null;
                                    if (msgId == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    list.add(msgId);
                                }
                                ImsPresenterHolder.INSTANCE.reportTaskStatus(RemoteTaskState.ARRIVED.getStateId(), ImsTaskObjHolder.INSTANCE.getRemoteMsg());
                                z = true;
                            }
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        MessageProtobuf.Msg.Builder createMsgBuilder2 = ImsPresenterHolder.INSTANCE.createMsgBuilder(ImsTaskObjHolder.INSTANCE.buildTask(ShortUUID.INSTANCE.randomUUID(), RemoteTaskState.ARRIVED.getStateId()), ImsTaskObjHolder.INSTANCE.buildCallPoint(model.getDestination(), 1));
                        ImsTaskObjHolder.INSTANCE.setRemoteMsg(createMsgBuilder2 != null ? createMsgBuilder2.build() : null);
                        MessageProtobuf.Msg remoteMsg3 = ImsTaskObjHolder.INSTANCE.getRemoteMsg();
                        if ((remoteMsg3 != null ? remoteMsg3.getMsgId() : null) != null) {
                            List<String> list2 = this.arrivedMsgIds;
                            MessageProtobuf.Msg remoteMsg4 = ImsTaskObjHolder.INSTANCE.getRemoteMsg();
                            String msgId2 = remoteMsg4 != null ? remoteMsg4.getMsgId() : null;
                            if (msgId2 == null) {
                                Intrinsics.throwNpe();
                            }
                            list2.add(msgId2);
                        }
                        ImsPresenterHolder.INSTANCE.reportTaskStatus(RemoteTaskState.ARRIVED.getStateId(), ImsTaskObjHolder.INSTANCE.getRemoteMsg());
                    }
                    BuildersKt__Builders_commonKt.launch$default(this.mainScope, null, null, new DeliverActivity$showViewModelChanged$2(this, model, null), 3, null);
                    break;
                }
                break;
            case DONE:
                resetMusicProgress();
                removeSendingMsg();
                TableTaskManager.INSTANCE.finishTask(model.getDestination());
                stopArrivedVoice();
                dismissPalletDialog();
                PeripheralsSceneUtil.INSTANCE.stopAll();
                this.isNeedInitCurrentPalletTts = true;
                break;
            case DONE_BEFORE_ARRIVAL:
                DeliveryTrack.INSTANCE.onFinishOne(model.getDestination(), BaseMoveTrackTask.FinishOneCause.InAdvance);
                TableTaskManager.INSTANCE.finishTask(model.getDestination());
                this.isNeedInitCurrentPalletTts = true;
                break;
            case ALL_LEFT_CANCEL:
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Cancel);
                TableTaskManager.INSTANCE.clearAll();
                startDeliverTaskEditActivity(0);
                break;
            case ALL_DONE:
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.Normal);
                TableTaskManager.INSTANCE.clearAll();
                if (getDeliverArriverLayout().getIsNeedRecycle()) {
                    showRecycleTargetDialog();
                    break;
                } else {
                    int i3 = this.currentMode;
                    if (i3 == 2) {
                        startDeliverTaskEditActivity(0);
                        Pdlog.m3273d(this.TAG, "DeliveryEvent.ALL_DONE------startDeliverTaskEditActivity");
                        return;
                    }
                    if (i3 == 4) {
                        CallHistoryManager.INSTANCE.addHistory(model.getDestination());
                        if (getArriveLayout().getVisibility() == 0) {
                            Pdlog.m3273d(this.TAG, "showViewModelChanged arriveLayout is show ,need to start DeliverTaskEditActivity");
                            callReturn();
                            return;
                        }
                        Pdlog.m3273d(this.TAG, "showViewModelChanged BeeperUtils.isCallReachedoConfirmationSwitch:" + BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch() + ' ');
                        if (BeeperUtils.INSTANCE.isCallReachedoConfirmationSwitch()) {
                            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).stopPlay();
                            getArriveLayout().setVisibility(0);
                            this.motionEventHelper.stopMovingVoice();
                            if (this.voicePlayerHelper.checkFileExist(VoiceItem.voice35_1)) {
                                VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice35_1));
                            } else {
                                VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice7_1));
                            }
                            if (BeeperUtils.INSTANCE.isAutoCompleteCallTimeSwitch()) {
                                long getAutoCompleteCallTime = BeeperUtils.INSTANCE.getGetAutoCompleteCallTime() / 1000;
                                Pdlog.m3273d(this.TAG, "DeliveryEvent.ALL_DONE------autoCompleteCallTime:" + getAutoCompleteCallTime);
                                Disposable disposable = this.callCompleteCountDown;
                                if (disposable != null) {
                                    disposable.dispose();
                                }
                                this.callCompleteCountDown = CountdownUtil.INSTANCE.createCountDown(getAutoCompleteCallTime).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showViewModelChanged$3
                                    @Override // io.reactivex.functions.Consumer
                                    public final void accept(Long l) {
                                        String str = DeliverActivity.this.getString(C4188R.string.pdStr2_15) + " (" + l + "s)";
                                        SpannableString spannableString = new SpannableString(str);
                                        Button complete_btn = (Button) DeliverActivity.this._$_findCachedViewById(C4188R.id.complete_btn);
                                        Intrinsics.checkExpressionValueIsNotNull(complete_btn, "complete_btn");
                                        int textSize = (int) ((complete_btn.getTextSize() / 3) * 2);
                                        spannableString.setSpan(new AbsoluteSizeSpan(textSize), DeliverActivity.this.getString(C4188R.string.pdStr2_15).length(), str.length(), 34);
                                        spannableString.setSpan(new VerticalAlignTextSpan(textSize, -3355444), DeliverActivity.this.getString(C4188R.string.pdStr2_15).length(), str.length(), 34);
                                        Button complete_btn2 = (Button) DeliverActivity.this._$_findCachedViewById(C4188R.id.complete_btn);
                                        Intrinsics.checkExpressionValueIsNotNull(complete_btn2, "complete_btn");
                                        complete_btn2.setText(spannableString);
                                    }
                                }, new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showViewModelChanged$4
                                    @Override // io.reactivex.functions.Consumer
                                    public final void accept(Throwable th) {
                                    }
                                }, new Action() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showViewModelChanged$5
                                    @Override // io.reactivex.functions.Action
                                    public final void run() {
                                        DeliverActivity.this.callReturn();
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        callReturn();
                        return;
                    }
                    Intent intent = new Intent(this, (Class<?>) TurnBackActivity.class);
                    Intent putExtra = intent.putExtra("SHOW_THANKS", !Constans.INSTANCE.getDeliverFinishVoiceAdvanceSwitch());
                    Intrinsics.checkExpressionValueIsNotNull(putExtra, "intent.putExtra(\n       …h()\n                    )");
                    IntentExtKt.saveSceneId(putExtra, DeliveryTrack.INSTANCE.getSessionId());
                    intent.putExtra(TurnBackActivity.KEY_MODE, this.currentMode).putExtra(TurnBackActivity.CUSTOM_OUTLET, this.customOutlet);
                    jumpAndFinish(intent);
                    break;
                }
            case NOT_FIND_TARGET:
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.NoTarget);
                TableTaskManager.INSTANCE.clearAll();
                startDeliverTaskEditActivity(0);
                break;
            case MODIFY:
                DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.LocalModification);
                int i4 = this.currentMode;
                if (i4 != 1) {
                    if (i4 != 2) {
                        if (i4 != 3) {
                            if (i4 == 4) {
                                startDeliverTaskEditActivity(5);
                                break;
                            } else {
                                startDeliverTaskEditActivity(1);
                                break;
                            }
                        } else {
                            startDeliverTaskEditActivity(9);
                            break;
                        }
                    } else {
                        startDeliverTaskEditActivity(5);
                        break;
                    }
                } else {
                    startDeliverTaskEditActivity(3);
                    break;
                }
            case PAUSE:
                DeliveryTrack.INSTANCE.onPause();
                if (this.currentEventStatus != event) {
                    VoicePlayer.INSTANCE.play(this.pauseVoiceTask);
                    showDeliverPauseStatus(model);
                    break;
                }
                break;
            case ACTIVE:
                DeliveryTrack.INSTANCE.onMove();
                DeliveryContract.DeliveryEvent deliveryEvent = this.currentEventStatus;
                if (deliveryEvent != event && deliveryEvent != DeliveryContract.DeliveryEvent.ON_THE_WAY) {
                    showOnTheWayStatus(model);
                    break;
                }
                break;
        }
        if (event == DeliveryContract.DeliveryEvent.PALLET_CHANGE || event == DeliveryContract.DeliveryEvent.PALLET_TAKE || event == DeliveryContract.DeliveryEvent.PALLET_PLACE) {
            return;
        }
        this.currentEventStatus = event;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetMusicProgress() {
        if (this.currentMode == 1) {
            MusicPlayerHelper.getInstance().resetMusicProgress(ModeEnum.BIRTHDAY);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.ViewInterface
    public void showPalletChanged(DeliveryContract.DeliveryEvent event, final ViewModel model, int index) {
        DeliveryPalletAlarmDialog deliveryPalletAlarmDialog;
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(model, "model");
        DeliveryTrack.INSTANCE.onTray(index, DeliveryContract.DeliveryEvent.PALLET_CHANGE.ordinal());
        int i = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i == 1) {
            dealPalletChange(model);
            if (Constans.INSTANCE.getPalletAlarmSwitch() && isPalletHaveTask(model.getTrays().get(index)) && (deliveryPalletAlarmDialog = this.deliveryPalletAlartDialog) != null && deliveryPalletAlarmDialog.isShowing()) {
                dismissPalletDialog();
                AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
                return;
            }
            return;
        }
        if (i == 2) {
            Pdlog.m3273d(this.TAG, "PALLET_PLACE ");
            if (Constans.INSTANCE.getPalletAlarmSwitch() && isPalletHaveTask(model.getTrays().get(index))) {
                dismissPalletDialog();
                return;
            }
            return;
        }
        if (i == 3) {
            Pdlog.m3273d(this.TAG, "PALLET_PLACE 开关 " + Constans.INSTANCE.getPalletAlarmSwitch());
            if (Constans.INSTANCE.getPalletAlarmSwitch() && isPalletHaveTask(model.getTrays().get(index))) {
                AiVoiceManager.INSTANCE.stopAiRecording();
                TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
                getDeliverPresenter().countdownPause();
                this.mainHandler.post(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showPalletChanged$2
                    @Override // java.lang.Runnable
                    public final void run() {
                        DeliveryPalletAlarmDialog deliveryPalletAlarmDialog2;
                        DeliveryPalletAlarmDialog deliveryPalletAlarmDialog3;
                        DeliveryPalletAlarmDialog deliveryPalletAlarmDialog4;
                        String str;
                        String threadInfo;
                        DeliveryPalletAlarmDialog deliveryPalletAlarmDialog5;
                        String str2;
                        String threadInfo2;
                        VoiceTask voiceTask;
                        String str3;
                        String threadInfo3;
                        deliveryPalletAlarmDialog2 = DeliverActivity.this.deliveryPalletAlartDialog;
                        if (deliveryPalletAlarmDialog2 == null) {
                            DeliverActivity deliverActivity = DeliverActivity.this;
                            deliverActivity.deliveryPalletAlartDialog = new DeliveryPalletAlarmDialog(deliverActivity);
                            str3 = DeliverActivity.this.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("dialog created");
                            threadInfo3 = DeliverActivity.this.getThreadInfo();
                            sb.append(threadInfo3);
                            Pdlog.m3275i(str3, sb.toString());
                        }
                        deliveryPalletAlarmDialog3 = DeliverActivity.this.deliveryPalletAlartDialog;
                        if (deliveryPalletAlarmDialog3 != null) {
                            deliveryPalletAlarmDialog3.setTrayInfo(model.getTrays(), model.getDestination());
                        }
                        deliveryPalletAlarmDialog4 = DeliverActivity.this.deliveryPalletAlartDialog;
                        if (deliveryPalletAlarmDialog4 != null) {
                            deliveryPalletAlarmDialog4.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showPalletChanged$2.1
                                @Override // android.content.DialogInterface.OnDismissListener
                                public final void onDismiss(DialogInterface dialogInterface) {
                                    DeliveryPresenter deliverPresenter;
                                    DeliverActivity.this.stopPalletAlarm();
                                    AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
                                    deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                                    deliverPresenter.countdownResume();
                                }
                            });
                        }
                        str = DeliverActivity.this.TAG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("dialog show begin");
                        threadInfo = DeliverActivity.this.getThreadInfo();
                        sb2.append(threadInfo);
                        Pdlog.m3275i(str, sb2.toString());
                        deliveryPalletAlarmDialog5 = DeliverActivity.this.deliveryPalletAlartDialog;
                        if (deliveryPalletAlarmDialog5 != null) {
                            deliveryPalletAlarmDialog5.show();
                        }
                        str2 = DeliverActivity.this.TAG;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("dialog show after");
                        threadInfo2 = DeliverActivity.this.getThreadInfo();
                        sb3.append(threadInfo2);
                        Pdlog.m3275i(str2, sb3.toString());
                        PeripheralsSceneUtil.INSTANCE.showDeliveryArrivedWrongPalletNotify();
                        VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                        voiceTask = DeliverActivity.this.arrivedTakeWarnTask;
                        voicePlayer.play(voiceTask);
                    }
                });
                stopArrivedVoice();
                return;
            }
            return;
        }
        if (i != 4) {
            return;
        }
        Pdlog.m3273d(this.TAG, "DeliveryEvent.EMPTY");
        Button btn_finish = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
        Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
        ViewExtKt.gone(btn_finish);
        DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
        String currentTask = TableTaskManager.INSTANCE.getCurrentTask();
        if (currentTask == null) {
            currentTask = "";
        }
        deliveryTrack.onFinishOne(currentTask, BaseMoveTrackTask.FinishOneCause.TraySensor);
        if (this.currentMode == 4) {
            Button complete_btn = (Button) _$_findCachedViewById(C4188R.id.complete_btn);
            Intrinsics.checkExpressionValueIsNotNull(complete_btn, "complete_btn");
            ViewExtKt.gone(complete_btn);
            if (this.mainHandler.hasMessages(this.CALL_AUTO_COMPLETE_TIME_WHAT)) {
                this.mainHandler.removeMessages(this.CALL_AUTO_COMPLETE_TIME_WHAT);
            }
            this.mainHandler.sendEmptyMessageDelayed(this.CALL_AUTO_COMPLETE_TIME_WHAT, 2000L);
            return;
        }
        this.mainHandler.postDelayed(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showPalletChanged$3
            @Override // java.lang.Runnable
            public final void run() {
                DeliverActivity.this.resetBtnFinish();
                DeliverActivity.this.onClickDeliverFinish();
            }
        }, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopPalletAlarm() {
        TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
        VoicePlayer.INSTANCE.stop(this.arrivedTakeWarnTask);
        PeripheralsSceneUtil.INSTANCE.stopDeliveryArrivedWrongPalletNotify();
    }

    private final void dealPalletChange(ViewModel model) {
        int i = 0;
        for (Object obj : model.getTrays()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            TrayModel trayModel = (TrayModel) obj;
            Pdlog.m3273d(this.TAG, "PALLET_CHANGE index = " + i + " pair = " + trayModel.getIsEmpty());
            DeliveryModel current = trayModel.getCurrent();
            if ((current != null ? current.getStatus() : null) == TaskStatus.ARRIVAL) {
                DeliveryModel current2 = trayModel.getCurrent();
                if ((current2 != null ? current2.getStatus() : null) == TaskStatus.ARRIVAL && !trayModel.getIsEmpty()) {
                    PeripheralsSceneUtil.showDeliveryArrivedTray$default(PeripheralsSceneUtil.INSTANCE, i, false, 2, null);
                } else {
                    PeripheralsSceneUtil.INSTANCE.showDeliveryArrivedTray(i, false);
                }
            }
            i = i2;
        }
    }

    private final boolean isPalletHaveTask(TrayModel t) {
        if (t == null) {
            return false;
        }
        String palletTaskName = PalletTaskUtil.INSTANCE.getPalletTaskName(t);
        return !(palletTaskName == null || StringsKt.isBlank(palletTaskName));
    }

    static /* synthetic */ FaceVideoAnimation getOnTheWayAni$default(DeliverActivity deliverActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return deliverActivity.getOnTheWayAni(z);
    }

    private final FaceVideoAnimation getOnTheWayAni(boolean isAdvance) {
        if (this.currentMode == 1) {
            return SceneAnimationResources.INSTANCE.getBirthdayOnTheWay();
        }
        if (isAdvance || isShowDeliverFace()) {
            return SceneAnimationResources.INSTANCE.getOnTheWay();
        }
        return SceneAnimationResources.INSTANCE.getOnTheWayAround();
    }

    private final void playArrivedFinish(final boolean isAdvance) {
        String str;
        if (this.isPlayingFinishVoice) {
            Pdlog.m3273d(this.TAG, "playArrivedFinish: " + this.isPlayingFinishVoice);
            return;
        }
        stopArrivedVoice();
        this.isPlayingFinishVoice = true;
        if (isShowDeliverFace() || isAdvance) {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getThanks());
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(getOnTheWayAni(true));
        } else {
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(SceneAnimationResources.INSTANCE.getThanks());
            ((FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view)).playAnimation(getOnTheWayAni$default(this, false, 1, null));
            FaceVideoView faceVideoView = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
            ViewModel viewModel = this.currentViewModel;
            if (viewModel == null || (str = viewModel.getDestination()) == null) {
                str = "";
            }
            faceVideoView.setTarget(str);
        }
        VoiceTask voiceTask = new VoiceTask(-1L, VoiceItem.voice10_2);
        voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$playArrivedFinish$1
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                String str2;
                DeliveryPresenter deliverPresenter;
                Intrinsics.checkParameterIsNotNull(event, "event");
                str2 = DeliverActivity.this.TAG;
                Pdlog.m3273d(str2, "playArrivedFinish voice finish,isAdvance: " + isAdvance + ", event: " + event);
                if (isAdvance && (event == PlayEvent.COMPLETION_ONCE || event == PlayEvent.STOP)) {
                    deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                    deliverPresenter.actionActive();
                }
                if (event == PlayEvent.COMPLETION_ONCE || event == PlayEvent.STOP) {
                    DeliverActivity.this.isPlayingFinishVoice = false;
                }
            }
        });
        VoicePlayer.INSTANCE.play(voiceTask);
        PeripheralsSceneUtil.INSTANCE.showDeliveryArrivedFinish();
    }

    private final void showRecycleTargetDialog() {
        Pdlog.m3273d(this.TAG, "showRecycleTargetDialog");
        TransferDishesDialog transferDishesDialog = new TransferDishesDialog(this);
        transferDishesDialog.setListener(new TransferDishesDialog.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showRecycleTargetDialog$1
            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.TransferDishesDialog.Callback
            public void onWashRoom(Dialog dialog) {
                String str;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "showRecycleTargetDialog onWashRoom");
                DeliverActivity.this.jumpAndFinish(TransferDishesActivity.INSTANCE.getGoWashRoomIntent(DeliverActivity.this));
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.TransferDishesDialog.Callback
            public void onRecycleTable(Dialog dialog) {
                String str;
                str = DeliverActivity.this.TAG;
                Pdlog.m3273d(str, "showRecycleTargetDialog onRecycleTable");
                DeliverActivity.this.jumpAndFinish(TransferDishesActivity.INSTANCE.getGoRecycleTableIntent(DeliverActivity.this));
            }
        });
        transferDishesDialog.show();
    }

    private final void startDeliverTaskEditActivity(int mode) {
        Pdlog.m3273d(this.TAG, "startDeliverTaskEditActivity " + mode);
        Intent intent = new Intent(this, (Class<?>) DeliverTaskEditActivity.class);
        intent.putExtra("MODE_TYPE", mode);
        jumpAndFinish(intent);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.remote_task.RemoteDeliveryContract.ViewInterface
    public boolean onReceiveRemoteModifyTask(SortType sortType, ArrayList<TrayModel> allTrays) {
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Intrinsics.checkParameterIsNotNull(allTrays, "allTrays");
        Pdlog.m3273d(this.TAG, "onReceiveRemoteModifyTask : sortType = " + sortType + "; allTrays = " + allTrays + "; ");
        if (this.currentEventStatus != DeliveryContract.DeliveryEvent.ON_THE_WAY && this.currentEventStatus != DeliveryContract.DeliveryEvent.ACTIVE) {
            return false;
        }
        Iterator<T> it = allTrays.iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (((TrayModel) it.next()).getAllDestinations().size() > 0) {
                z = false;
            }
        }
        if (z) {
            Pdlog.m3273d(this.TAG, "onReceiveRemoteModifyTask : clearAll");
            TableTaskManager.INSTANCE.clearAll();
            Intent putExtra = new Intent(this, (Class<?>) TurnBackActivity.class).putExtra(TurnBackActivity.KEY_MODE, this.currentMode).putExtra(TurnBackActivity.CUSTOM_OUTLET, this.customOutlet);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(\n                …TOM_OUTLET, customOutlet)");
            jumpAndFinish(putExtra);
            DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.RemoteModification);
            getDeliverPresenter().actionCancelAll();
        } else {
            DeliveryTrack.INSTANCE.onStop(BaseMoveTrackTask.StopCause.RemoteModification);
            DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
            int i = this.currentMode;
            String simpleName = getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@DeliverActivity::class.java.simpleName");
            DeliveryTrack.onCreateTask$default(deliveryTrack, i, simpleName, null, 4, null);
            TableTaskManager.setAllTask$default(TableTaskManager.INSTANCE, allTrays, sortType, MovePerformance.NORMAL, false, 8, null);
            startDeliverTask();
        }
        return true;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.AutoResumeCountDownContract.ViewInterface
    public void showTimeLeft(long time, AutoResumeCountDownContract.Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Pdlog.m3273d(this.TAG, "showTimeLeft " + time);
        getDeliverPauseLayout().setCountdown(time);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.ViewInterface
    public void showDelayAutoFinish() {
        Pdlog.m3273d(this.TAG, "showDelayAutoFinish()");
        DeliveryTrack deliveryTrack = DeliveryTrack.INSTANCE;
        String currentTask = TableTaskManager.INSTANCE.getCurrentTask();
        if (currentTask == null) {
            currentTask = "";
        }
        deliveryTrack.onFinishOne(currentTask, BaseMoveTrackTask.FinishOneCause.CountDown);
        onClickDeliverFinish();
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract.ViewInterface
    public void showCountdownFinish(long millisUntilFinished) {
        Button button;
        if (this.isRelease) {
            Pdlog.m3274e(this.TAG, "showCountdownFinish isRelease ");
            return;
        }
        if (((Button) _$_findCachedViewById(C4188R.id.btn_finish)) == null) {
            DeliveryPresenter deliverPresenter = getDeliverPresenter();
            if (deliverPresenter != null) {
                deliverPresenter.onDestroy();
            }
            Pdlog.m3274e(this.TAG, "showCountdownFinish btn_finish is null ");
            return;
        }
        String customFinishBtnContent = Constans.INSTANCE.getCustomFinishBtnContent();
        if (customFinishBtnContent.length() == 0) {
            customFinishBtnContent = getString(C4188R.string.pdStr2_15);
            Intrinsics.checkExpressionValueIsNotNull(customFinishBtnContent, "getString(R.string.pdStr2_15)");
        }
        String str = customFinishBtnContent;
        if (SpUtils.get(RobotContext.INSTANCE.getContext(), "key_interaction_switch", false)) {
            str = getString(C4188R.string.pdStr7_110);
        }
        String valueOf = String.valueOf(millisUntilFinished);
        String str2 = str + " (" + valueOf + "s)";
        Intrinsics.checkExpressionValueIsNotNull(str2, "sb.append(text1)\n       ….append(text4).toString()");
        SpannableString spannableString = new SpannableString(str2);
        Button btn_finish = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
        Intrinsics.checkExpressionValueIsNotNull(btn_finish, "btn_finish");
        int textSize = (int) ((btn_finish.getTextSize() / 3) * 2);
        spannableString.setSpan(new AbsoluteSizeSpan(textSize), str.length(), str2.length(), 34);
        spannableString.setSpan(new VerticalAlignTextSpan(textSize, -3355444), str.length(), str2.length(), 34);
        Button button2 = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
        if (button2 != null) {
            button2.setText(spannableString);
        }
        if (Intrinsics.areEqual(valueOf, "0") && (button = (Button) _$_findCachedViewById(C4188R.id.btn_finish)) != null) {
            button.setText(str);
        }
        String str3 = this.TAG;
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("showCountdownFinish() text=");
        Button button3 = (Button) _$_findCachedViewById(C4188R.id.btn_finish);
        sb.append(String.valueOf(button3 != null ? button3.getText() : null));
        objArr[0] = sb.toString();
        Pdlog.m3273d(str3, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSendingMsg() {
        CallSettingRepo.INSTANCE.stopReportArrive();
        Iterator<T> it = this.arrivedMsgIds.iterator();
        while (it.hasNext()) {
            ImsPresenterHolder.INSTANCE.removeMsg((String) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Pdlog.m3273d(this.TAG, "onDestroy");
        DeliveryPresenter deliverPresenter = getDeliverPresenter();
        if (deliverPresenter != null) {
            deliverPresenter.onDestroy();
        }
        removeSendingMsg();
        getDeliverPresenter().setCallNaviState((OnCallNaviStateChangeListener) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
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
    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOutletSelectDialog() {
        TextView tv_dish_port = (TextView) _$_findCachedViewById(C4188R.id.tv_dish_port);
        Intrinsics.checkExpressionValueIsNotNull(tv_dish_port, "tv_dish_port");
        SelectOutletDialog selectOutletDialog = new SelectOutletDialog(this, tv_dish_port.getText().toString());
        selectOutletDialog.setOnComplete(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showOutletSelectDialog$1
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
            public final void invoke2(String str) {
                TextView tv_dish_port2 = (TextView) DeliverActivity.this._$_findCachedViewById(C4188R.id.tv_dish_port);
                Intrinsics.checkExpressionValueIsNotNull(tv_dish_port2, "tv_dish_port");
                tv_dish_port2.setText(str);
                DeliverActivity.this.customOutlet = str;
            }
        });
        selectOutletDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverActivity$showOutletSelectDialog$2
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                DeliveryPresenter deliverPresenter;
                deliverPresenter = DeliverActivity.this.getDeliverPresenter();
                deliverPresenter.countdownResume();
            }
        });
        selectOutletDialog.show();
        getDeliverPresenter().countdownPause();
    }

    private final void initCurrentPalletTts(String destination) {
        TtsVoiceHelper.TtsConfigData ttsPalletMoving;
        ArrayList<Property> list;
        ArrayList<Property> list2;
        ArrayList<Property> list3;
        ArrayList<Property> list4;
        Object obj;
        Pdlog.m3273d(this.TAG, "initCurrentPalletTts, destination: " + destination);
        ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it = allTask.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            Iterator<T> it2 = ((TrayModel) next).getAllDestinations().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                } else {
                    obj = it2.next();
                    if (Intrinsics.areEqual(((DeliveryModel) obj).getDestination(), destination)) {
                        break;
                    }
                }
            }
            DeliveryModel deliveryModel = (DeliveryModel) obj;
            if (Intrinsics.areEqual(destination, deliveryModel != null ? deliveryModel.getDestination() : null)) {
                arrayList2.add(next);
            }
        }
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            PalletTtsScheme palletTtsScheme = (PalletTtsScheme) ((TrayModel) it3.next()).getPalletTtsScheme();
            if (palletTtsScheme != null) {
                arrayList.add(palletTtsScheme);
            }
        }
        this.palletTtsScheme = arrayList.isEmpty() ? null : (PalletTtsScheme) CollectionsKt.random(arrayList, Random.INSTANCE);
        Pdlog.m3273d(this.TAG, "initCurrentPalletTts,palletTtsSchemeList:" + arrayList.size() + ", palletTtsScheme: " + this.palletTtsScheme);
        VoiceTask voiceTask = this.movingLoopVoiceTask.getVoiceTask();
        if (voiceTask != null && (list4 = voiceTask.getList()) != null) {
            list4.clear();
        }
        VoiceTask voiceTask2 = this.movingLoopVoiceTask.getVoiceTask();
        if (voiceTask2 != null && (list3 = voiceTask2.getList()) != null) {
            list3.add(new VoiceProperty(10000L, VoiceItem.voice7_1, -1));
        }
        VoiceTask voiceTask3 = this.movingLoopVoiceTask.getVoiceTask();
        if (voiceTask3 != null) {
            voiceTask3.setLoopTime_ms(10000L);
        }
        PalletTtsScheme palletTtsScheme2 = this.palletTtsScheme;
        if (palletTtsScheme2 != null && palletTtsScheme2.getIsTtsPalletMovingOn() && (ttsPalletMoving = palletTtsScheme2.getTtsPalletMoving()) != null) {
            VoiceTask voiceTask4 = this.movingLoopVoiceTask.getVoiceTask();
            if (voiceTask4 != null && (list2 = voiceTask4.getList()) != null) {
                list2.clear();
            }
            VoiceTask voiceTask5 = this.movingLoopVoiceTask.getVoiceTask();
            if (voiceTask5 != null && (list = voiceTask5.getList()) != null) {
                list.add(new TtsVoiceProperty(palletTtsScheme2.getInterval() * 1000, ttsPalletMoving.getPath()));
            }
            VoiceTask voiceTask6 = this.movingLoopVoiceTask.getVoiceTask();
            if (voiceTask6 != null) {
                voiceTask6.setLoopTime_ms(0L);
            }
        }
        PalletTtsScheme palletTtsScheme3 = this.palletTtsScheme;
        TtsVoiceHelper.TtsConfigData ttsPalletArrived = palletTtsScheme3 != null ? palletTtsScheme3.getTtsPalletArrived() : null;
        PalletTtsScheme palletTtsScheme4 = this.palletTtsScheme;
        if (!(palletTtsScheme4 != null && palletTtsScheme4.getIsTtsPalletArrivedOn())) {
            ttsPalletArrived = null;
        }
        this.ttsConfigDataArrived = ttsPalletArrived;
    }
}
