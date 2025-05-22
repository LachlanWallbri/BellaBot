package com.pudutech.bumblebee.robot_ui.p054ui;

import android.R;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.PresenterHolder;
import com.pudutech.bumblebee.presenter.battery_task.BatteryContract2;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingPresenter;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.general_task.DispatchKeyContract;
import com.pudutech.bumblebee.presenter.general_task.DispatchKeyPresenter;
import com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemPresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BasePresenterInterface;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.robot_open_task.bean.CustomCallTargetBean;
import com.pudutech.bumblebee.presenter.robot_voices.Listener;
import com.pudutech.bumblebee.presenter.robot_voices.PlayEvent;
import com.pudutech.bumblebee.presenter.robot_voices.VoicePlayer;
import com.pudutech.bumblebee.presenter.robot_voices.VoiceTask;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract;
import com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInPresenter;
import com.pudutech.bumblebee.presenter.touch_sensor_task.TouchSensorContract;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestHelper;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceManager;
import com.pudutech.bumblebee.robot_ui.manager.AiVoiceTriggerHelper;
import com.pudutech.bumblebee.robot_ui.manager.BatteryInfoManager;
import com.pudutech.bumblebee.robot_ui.manager.TableTaskManager;
import com.pudutech.bumblebee.robot_ui.module.charging.RobotChargingActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.SettingActivity;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.SmartInputTableFragment;
import com.pudutech.bumblebee.robot_ui.module.setting.p053ui.dialog.ShowTipMsgDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.custom_call.CustomCallActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.ConfirmTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.HomeSettingDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.MaintenanceTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.RgbdOilTipDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceInteractionDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.dialog.VoiceModeDialog;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.ISelectTableFragment;
import com.pudutech.bumblebee.robot_ui.p054ui.fragment.SelectTableFragment;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.BeeperCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.CustomCallHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.HealthCheckHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LeaseHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.LockMachineHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.PalletCountHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TouchSensorEventHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.bumblebee.robot_ui.p054ui.recycle.RecyclingPlatesActivity;
import com.pudutech.bumblebee.robot_ui.p054ui.view.MyStatusBarLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.RecyclePlateTaskLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.RobotDeliverTaskLayout;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.FaceVideoView;
import com.pudutech.bumblebee.robot_ui.p054ui.view.videoface.SceneAnimationResources;
import com.pudutech.bumblebee.robot_ui.track.ext.IntentExtKt;
import com.pudutech.bumblebee.robot_ui.track.task.PeripheralsTrack;
import com.pudutech.bumblebee.robot_ui.track.task.VoiceInteractionTrack;
import com.pudutech.bumblebee.robot_ui.ui_helpers.SceneRecord;
import com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceLongClickListenerKt;
import com.pudutech.bumblebee.robot_ui.ui_utils.VoiceSwitchChangeListener;
import com.pudutech.bumblebee.robot_ui.util.DensityUtil;
import com.pudutech.bumblebee.robot_ui.util.FlowCardHelper;
import com.pudutech.bumblebee.robot_ui.util.FragmentUtils;
import com.pudutech.bumblebee.robot_ui.util.PeripheralsSceneUtil;
import com.pudutech.bumblebee.robot_ui.util.RobotSpeedUtil;
import com.pudutech.bumblebee.robot_ui.util.ToastUtils;
import com.pudutech.bumblebee.robot_ui.util.UiUtils;
import com.pudutech.bumblebee.robot_ui.viewmodel.DeliverTaskVM;
import com.pudutech.location.view.SignalView;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.mpmodule.MusicPlayerHelper;
import com.pudutech.mpmodule.p060ui.PlayerModuleActivityManager;
import com.pudutech.resources.voice.VoiceItem;
import com.pudutech.robot.module.report.track2.WakeUpType;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.shared.lib_syntime.SntpTimeSyncManager;
import com.pudutech.voiceinteraction.component.cmd.WorkMode;
import com.pudutech.voiceinteraction.component.config.WakeupInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.anko.Sdk27PropertiesKt;

/* compiled from: DeliverTaskEditActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0094\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014*\u0002+`\u0018\u0000 \u008b\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007:\u0002\u008b\u0002B\u0005¢\u0006\u0002\u0010\bJ\u0012\u0010\u0089\u0001\u001a\u00020^2\u0007\u0010\u008a\u0001\u001a\u00020\fH\u0002J\t\u0010\u008b\u0001\u001a\u00020^H\u0002J-\u0010\u008c\u0001\u001a\t\u0012\u0004\u0012\u00020\n0\u008d\u00012\u001b\u0010\u008e\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0090\u00010\u008f\u0001j\n\u0012\u0005\u0012\u00030\u0090\u0001`\u0091\u0001H\u0002J\u0015\u0010\u0092\u0001\u001a\u00020)2\n\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001H\u0016J\n\u0010\u0095\u0001\u001a\u00030\u0096\u0001H\u0002J\n\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0002J\u001d\u0010\u0099\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0090\u00010\u008f\u0001j\n\u0012\u0005\u0012\u00030\u0090\u0001`\u0091\u0001H\u0002J\t\u0010\u009a\u0001\u001a\u00020^H\u0002J\t\u0010\u009b\u0001\u001a\u00020^H\u0002J\t\u0010\u009c\u0001\u001a\u00020^H\u0002J\t\u0010\u009d\u0001\u001a\u00020^H\u0002J\t\u0010\u009e\u0001\u001a\u00020^H\u0002J\t\u0010\u009f\u0001\u001a\u00020^H\u0002J\t\u0010 \u0001\u001a\u00020^H\u0002J\t\u0010¡\u0001\u001a\u00020)H\u0002J\t\u0010¢\u0001\u001a\u00020)H\u0016J\t\u0010£\u0001\u001a\u00020)H\u0002J\t\u0010¤\u0001\u001a\u00020)H\u0002J\t\u0010¥\u0001\u001a\u00020)H\u0002J\u0013\u0010¦\u0001\u001a\u00020^2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0002J\u0013\u0010©\u0001\u001a\u00020^2\b\u0010§\u0001\u001a\u00030¨\u0001H\u0016J\t\u0010ª\u0001\u001a\u00020^H\u0002J/\u0010«\u0001\u001a\u00020^2\u001b\u0010¬\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0090\u00010\u008f\u0001j\n\u0012\u0005\u0012\u00030\u0090\u0001`\u0091\u00012\u0007\u0010\u00ad\u0001\u001a\u00020\fH\u0002J\u0015\u0010®\u0001\u001a\u00020^2\n\u0010¯\u0001\u001a\u0005\u0018\u00010°\u0001H\u0014J\t\u0010±\u0001\u001a\u00020^H\u0014J\u001c\u0010²\u0001\u001a\u00020)2\b\u0010³\u0001\u001a\u00030´\u00012\u0007\u0010µ\u0001\u001a\u00020\fH\u0016J\u0012\u0010¶\u0001\u001a\u00020^2\u0007\u0010·\u0001\u001a\u00020\nH\u0002J\u001c\u0010¸\u0001\u001a\u00020^2\b\u0010i\u001a\u0004\u0018\u00010\f2\u0007\u0010¹\u0001\u001a\u00020)H\u0016J\u0015\u0010º\u0001\u001a\u00020^2\n\u0010§\u0001\u001a\u0005\u0018\u00010¨\u0001H\u0014J\t\u0010»\u0001\u001a\u00020^H\u0014J9\u0010¼\u0001\u001a\u00020)2\b\u0010½\u0001\u001a\u00030¾\u00012\u001b\u0010¿\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0090\u00010\u008f\u0001j\n\u0012\u0005\u0012\u00030\u0090\u0001`\u0091\u00012\u0007\u0010À\u0001\u001a\u00020)H\u0016J9\u0010Á\u0001\u001a\u00020)2\u001b\u0010Â\u0001\u001a\u0016\u0012\u0005\u0012\u00030Ã\u00010\u008f\u0001j\n\u0012\u0005\u0012\u00030Ã\u0001`\u0091\u00012\b\u0010·\u0001\u001a\u00030Ä\u00012\u0007\u0010Å\u0001\u001a\u00020\nH\u0016J\t\u0010Æ\u0001\u001a\u00020^H\u0014J\t\u0010Ç\u0001\u001a\u00020^H\u0014J\t\u0010È\u0001\u001a\u00020^H\u0014J\u0012\u0010É\u0001\u001a\u00020^2\u0007\u0010Ê\u0001\u001a\u00020)H\u0016J\t\u0010Ë\u0001\u001a\u00020^H\u0002J\t\u0010Ì\u0001\u001a\u00020^H\u0002J\t\u0010Í\u0001\u001a\u00020^H\u0002J\u0012\u0010Î\u0001\u001a\u00020^2\u0007\u0010Ï\u0001\u001a\u00020\u0016H\u0002J\t\u0010Ð\u0001\u001a\u00020^H\u0002J\t\u0010Ñ\u0001\u001a\u00020^H\u0002J\u0012\u0010Ò\u0001\u001a\u00020^2\u0007\u0010Ó\u0001\u001a\u00020)H\u0002J\t\u0010Ô\u0001\u001a\u00020^H\u0002J\u0012\u0010Õ\u0001\u001a\u00020^2\u0007\u0010Ö\u0001\u001a\u00020)H\u0002J\t\u0010×\u0001\u001a\u00020^H\u0002J\u001c\u0010Ø\u0001\u001a\u00020^2\b\u0010Ù\u0001\u001a\u00030Ú\u00012\u0007\u0010Û\u0001\u001a\u00020)H\u0002J\t\u0010Ü\u0001\u001a\u00020^H\u0002J\u0013\u0010Ý\u0001\u001a\u00020^2\b\u0010Þ\u0001\u001a\u00030ß\u0001H\u0016J\u001f\u0010à\u0001\u001a\u0004\u0018\u00010P2\u0007\u0010µ\u0001\u001a\u00020\f2\t\b\u0002\u0010á\u0001\u001a\u00020)H\u0002J\u0012\u0010â\u0001\u001a\u00020^2\u0007\u0010p\u001a\u00030ã\u0001H\u0016J\t\u0010ä\u0001\u001a\u00020^H\u0016J\t\u0010å\u0001\u001a\u00020^H\u0002J\u0012\u0010æ\u0001\u001a\u00020^2\u0007\u0010Ó\u0001\u001a\u00020)H\u0016J\t\u0010ç\u0001\u001a\u00020^H\u0016J\u0007\u0010è\u0001\u001a\u00020^J\u0012\u0010é\u0001\u001a\u00020^2\u0007\u0010ê\u0001\u001a\u00020\nH\u0016J\u0013\u0010ë\u0001\u001a\u00020^2\b\u0010Þ\u0001\u001a\u00030ì\u0001H\u0016J\u0012\u0010í\u0001\u001a\u00020^2\u0007\u0010î\u0001\u001a\u00020)H\u0002J\t\u0010ï\u0001\u001a\u00020^H\u0002J\u0007\u0010ð\u0001\u001a\u00020^J\t\u0010ñ\u0001\u001a\u00020^H\u0002J\t\u0010ò\u0001\u001a\u00020^H\u0002J\t\u0010ó\u0001\u001a\u00020^H\u0002J\t\u0010ô\u0001\u001a\u00020^H\u0002J\u0012\u0010õ\u0001\u001a\u00020^2\u0007\u0010ö\u0001\u001a\u00020)H\u0016JL\u0010÷\u0001\u001a\u00030ø\u00012\u0007\u0010ù\u0001\u001a\u00020\f2\u0007\u0010µ\u0001\u001a\u00020\f2\u0007\u0010ú\u0001\u001a\u00020\f2\u0007\u0010û\u0001\u001a\u00020\f2\r\u0010ü\u0001\u001a\b\u0012\u0004\u0012\u00020^0c2\r\u0010ý\u0001\u001a\b\u0012\u0004\u0012\u00020^0cH\u0002J\t\u0010þ\u0001\u001a\u00020^H\u0002J\u001f\u0010ÿ\u0001\u001a\u00020^2\t\b\u0002\u0010\u00ad\u0001\u001a\u00020\f2\t\b\u0002\u0010\u0080\u0002\u001a\u00020)H\u0002J\t\u0010\u0081\u0002\u001a\u00020^H\u0002J\t\u0010\u0082\u0002\u001a\u00020^H\u0002J\u0012\u0010\u0083\u0002\u001a\u00020^2\u0007\u0010î\u0001\u001a\u00020)H\u0002J\t\u0010\u0084\u0002\u001a\u00020^H\u0002J\t\u0010\u0085\u0002\u001a\u00020^H\u0002J\u0012\u0010\u0086\u0002\u001a\u00020^2\u0007\u0010\u0087\u0002\u001a\u00020\fH\u0016J\u001d\u0010\u0088\u0002\u001a\u00020^2\u0007\u0010\u0089\u0002\u001a\u00020)2\t\b\u0002\u0010\u008a\u0002\u001a\u00020)H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u0004\u0018\u00010\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u001f\u001a\u0004\b\"\u0010#R\u000e\u0010%\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,R\u001b\u0010-\u001a\u00020.8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b1\u0010\u001f\u001a\u0004\b/\u00100R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u00104\u001a\u0004\u0018\u0001058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u0010\u001f\u001a\u0004\b6\u00107R\u001b\u00109\u001a\u00020:8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010\u001f\u001a\u0004\b;\u0010<R\u000e\u0010>\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u00020)X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u000e\u0010M\u001a\u00020NX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010Q\u001a\u00020R8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bU\u0010\u001f\u001a\u0004\bS\u0010TR\u0010\u0010V\u001a\u0004\u0018\u00010WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020YX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Z\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010[\u001a\u000e\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020^0\\X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u00020`X\u0082\u0004¢\u0006\u0004\n\u0002\u0010aR\u0014\u0010b\u001a\b\u0012\u0004\u0012\u00020)0cX\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010d\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(h\u0012\u0013\u0012\u00110\f¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(i\u0012\u0004\u0012\u00020)0eX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010j\u001a\u001d\u0012\u0013\u0012\u00110)¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(k\u0012\u0004\u0012\u00020^0\\X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010l\u001a2\u0012\u0013\u0012\u00110m¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(n\u0012\u0013\u0012\u00110o¢\u0006\f\bf\u0012\b\bg\u0012\u0004\b\b(p\u0012\u0004\u0012\u00020^0eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010q\u001a\u0004\u0018\u00010rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010t\u001a\u00020u8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bx\u0010\u001f\u001a\u0004\bv\u0010wR\u0010\u0010y\u001a\u0004\u0018\u00010zX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010{\u001a\u0004\u0018\u00010|X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010}\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010~\u001a\u00020\u007fX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0080\u0001\u001a\u00030\u0081\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0084\u0001\u0010\u001f\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0012\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0087\u0001\u001a\u0011\u0012\u0007\u0012\u0005\u0018\u00010\u0088\u0001\u0012\u0004\u0012\u00020^0\\X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u008c\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity;", "Lcom/pudutech/bumblebee/robot_ui/ui/MyBaseActivity;", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/general_task/DispatchKeyContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingContract$ViewInterface;", "()V", "PLAY_VOICE", "", "TAG", "", "kotlin.jvm.PlatformType", "TYPE_FEATURE_AIVOICE", "TYPE_FEATURE_DIALOG", "TYPE_FEATURE_INPUT", "TYPE_FEATURE_NOMAL", "TYPE_FEATURE_STANDBY", "beeperCallHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/BeeperCallHelper;", "clickGoAlertVoice", "Lcom/pudutech/bumblebee/presenter/robot_voices/VoiceTask;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "currentModeType", "deliveryTaskSettingPresenter", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingPresenter;", "getDeliveryTaskSettingPresenter", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryTaskSettingPresenter;", "deliveryTaskSettingPresenter$delegate", "Lkotlin/Lazy;", "dispatchKeyPresenter", "Lcom/pudutech/bumblebee/presenter/general_task/DispatchKeyPresenter;", "getDispatchKeyPresenter", "()Lcom/pudutech/bumblebee/presenter/general_task/DispatchKeyPresenter;", "dispatchKeyPresenter$delegate", "enterIntoVoice", "faceAnimationView", "Lcom/pudutech/bumblebee/robot_ui/ui/view/videoface/FaceVideoView;", "fillInStatusInit", "", "functionButton", "com/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$functionButton$1", "Lcom/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$functionButton$1;", "healthCheckHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/HealthCheckHelper;", "getHealthCheckHelper", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/HealthCheckHelper;", "healthCheckHelper$delegate", "homeSettingDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/HomeSettingDialog;", "idleMovePresenter", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "getIdleMovePresenter", "()Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "idleMovePresenter$delegate", "informationSystemPresenter", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemPresenter;", "getInformationSystemPresenter", "()Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemPresenter;", "informationSystemPresenter$delegate", "inputOneTableVoice", "isFirstStart", "isInStandby", "isJumpToMusicAc", "isPlayClickGoAlertVoice", "isPlayInputOneTableVoice", "isPlayInputOneTableVoiceFinish", "isPlayLowPowerVoice", "isRelease", "isShowLowPowerDialog", "isplayEntryVoice", "getIsplayEntryVoice", "()Z", "setIsplayEntryVoice", "(Z)V", "leaseHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/LeaseHelper;", "lowerPowerDialog", "Lcom/pudutech/bumblebee/robot_ui/module/setting/ui/dialog/ShowTipMsgDialog;", "mAgentTestHelper", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "getMAgentTestHelper", "()Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestHelper;", "mAgentTestHelper$delegate", "mLockMachineHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/LockMachineHelper;", "mainHandler", "Landroid/os/Handler;", "needSelectTableDialog", "onCustomCallListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CustomCallTargetBean;", "", "onHomeSettingDialogClickListener", "com/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$onHomeSettingDialogClickListener$1", "Lcom/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$onHomeSettingDialogClickListener$1;", "onMissionStartListener", "Lkotlin/Function0;", "onTaskInputListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "tray", "destination", "onTouchSensorAnimationListener", "isShow", "onTouchSensorPlaceListener", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Place;", "place", "Lcom/pudutech/bumblebee/presenter/touch_sensor_task/TouchSensorContract$Event;", "event", "popupWindowPalletTts", "Landroid/widget/PopupWindow;", "recyclePlateEnterIntoVoice", "scheduleFillInPresenter", "Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter;", "getScheduleFillInPresenter", "()Lcom/pudutech/bumblebee/presenter/schedule_task/ScheduleFillInPresenter;", "scheduleFillInPresenter$delegate", "selectTableFragment", "Lcom/pudutech/bumblebee/robot_ui/ui/fragment/ISelectTableFragment;", "selectVoiceDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceModeDialog;", "showStatus", "touchSensorEventHelper", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TouchSensorEventHelper;", "vm", "Lcom/pudutech/bumblebee/robot_ui/viewmodel/DeliverTaskVM;", "getVm", "()Lcom/pudutech/bumblebee/robot_ui/viewmodel/DeliverTaskVM;", "vm$delegate", "voiceInteractionDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/VoiceInteractionDialog;", "wakeupListener", "Lcom/pudutech/voiceinteraction/component/config/WakeupInfo;", "addSelectTask", "task", "bindPresenter", "checkTrayDestination", "", "trayList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "dispatchTouchEvent", "ev", "Landroid/view/MotionEvent;", "getMode", "Lcom/pudutech/bumblebee/robot_ui/util/PeripheralsSceneUtil$Mode;", "getMovePerformance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "getSelectTask", "gotoquitFillIn", "initAiVoice", "initAllData", "initDragTips", "initSignal", "initTablesInput", "initView", "isBirthdayMode", "isBusyState", "isCanSaveDeliveryHistory", "isModifyMode", "isPalletTtsEnable", "jump", "intent", "Landroid/content/Intent;", "jumpAndFinish", "jumpToStartTask", "notifyOrderStart", "allTask", "employees", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDispatchKey", TransferTable.COLUMN_KEY, "Lcom/pudutech/bumblebee/presenter/general_task/DispatchKeyContract$KEY;", NotificationCompat.CATEGORY_MESSAGE, "onFeatureChange", "type", "onFillIn", "fillIn", "onNewIntent", "onPause", "onReceiveDeliveryTask", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "allTrays", "executeTask", "onReceiveOrderInfo", "info", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfoType;", "trayIndex", "onResume", "onStart", "onStop", "onWindowFocusChanged", "hasFocus", "parserIntent", "playClickGoAlertVoice", "playEntryVoiceIfNeed", "playPowerLowVoice", "voiceTask", "release", "reset", "setMusicBtnBg", "open", "setSelectTableFragment", "setSteadySwitchLayout", "switch", "setTaskListLayout", "setViewVisibility", "view", "Landroid/view/View;", "show", "settingInfoClickListener", "showChargerEvent", "model", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$ChargerModel;", "showDialog", "needCloseSensorFace", "showIdleEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "showLowerNotify", "showMaintenanceTip", "showMutliVoiceButton", "showPalletTtsVoiceButton", "showPalletTtsWindow", "showPowerChange", "i", "showPowerEvent", "Lcom/pudutech/bumblebee/presenter/battery_task/BatteryContract2$PowerModel;", "showRecycleTaskLayout", "boolean", "showRgbdTip", "showSelectVoiceDialog", "showSettingDialog", "showSleepAnimation", "showStandbyAnimation", "showStatusTip", "showTableLayout", "haveTableGroup", "showTwoBtnDialog", "Lcom/pudutech/bumblebee/robot_ui/ui/dialog/ConfirmTipDialog;", "title", "leftBtn", "rightBtn", "onBtn1Click", "onBtn2Click", "startDelayVoice", "startDeliverTask", "force", "stopDelayVoice", "stopStandby", "switchBirthdayTheme", "unBindPresenter", "updateModeUi", "updateMutliVoiceTip", "voiceName", "updateStartBtnLayout", "isModify", "isDelivery", "Companion", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class DeliverTaskEditActivity extends MyBaseActivity implements BatteryContract2.ViewInterface, IdleMoveContract.ViewInterface, ScheduleFillInContract.ViewInterface, InformationSystemContract.ViewInterface, DispatchKeyContract.ViewInterface, DeliveryTaskSettingContract.ViewInterface {
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
    private static boolean isCanFillIn;
    private final int TYPE_FEATURE_NOMAL;
    private HashMap _$_findViewCache;
    private int currentModeType;
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
    private boolean isplayEntryVoice;
    private ShowTipMsgDialog lowerPowerDialog;
    private LockMachineHelper mLockMachineHelper;
    private ShowTipMsgDialog needSelectTableDialog;
    private PopupWindow popupWindowPalletTts;
    private ISelectTableFragment selectTableFragment;
    private VoiceModeDialog selectVoiceDialog;
    private VoiceInteractionDialog voiceInteractionDialog;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean isFirstBoot = true;
    private String TAG = getClass().getSimpleName();

    /* renamed from: vm$delegate, reason: from kotlin metadata */
    private final Lazy vm = new ViewModelLazy(Reflection.getOrCreateKotlinClass(DeliverTaskVM.class), new Function0<ViewModelStore>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$$special$$inlined$viewModels$2
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
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$$special$$inlined$viewModels$1
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
    private final int PLAY_VOICE = 100;
    private boolean isFirstStart = true;

    /* renamed from: scheduleFillInPresenter$delegate, reason: from kotlin metadata */
    private final Lazy scheduleFillInPresenter = LazyKt.lazy(new Function0<ScheduleFillInPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$scheduleFillInPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ScheduleFillInPresenter invoke() {
            ScheduleFillInPresenter scheduleFillInPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(ScheduleFillInPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(ScheduleFillInPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                scheduleFillInPresenter = new ScheduleFillInPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(ScheduleFillInPresenter.class).toString(), scheduleFillInPresenter);
            } else {
                if (!(basePresenterInterface instanceof ScheduleFillInPresenter)) {
                    basePresenterInterface = null;
                }
                scheduleFillInPresenter = (ScheduleFillInPresenter) basePresenterInterface;
            }
            if (scheduleFillInPresenter == null) {
                Intrinsics.throwNpe();
            }
            return scheduleFillInPresenter;
        }
    });

    /* renamed from: dispatchKeyPresenter$delegate, reason: from kotlin metadata */
    private final Lazy dispatchKeyPresenter = LazyKt.lazy(new Function0<DispatchKeyPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$dispatchKeyPresenter$2
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

    /* renamed from: idleMovePresenter$delegate, reason: from kotlin metadata */
    private final Lazy idleMovePresenter = LazyKt.lazy(new Function0<IdleMovePresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$idleMovePresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final IdleMovePresenter invoke() {
            IdleMovePresenter idleMovePresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "popOrCreateIt " + Reflection.getOrCreateKotlinClass(IdleMovePresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                idleMovePresenter = new IdleMovePresenter();
            } else {
                presenterHolder.getBox().remove(Reflection.getOrCreateKotlinClass(IdleMovePresenter.class).toString());
                if (!(basePresenterInterface instanceof IdleMovePresenter)) {
                    basePresenterInterface = null;
                }
                idleMovePresenter = (IdleMovePresenter) basePresenterInterface;
            }
            return idleMovePresenter;
        }
    });

    /* renamed from: informationSystemPresenter$delegate, reason: from kotlin metadata */
    private final Lazy informationSystemPresenter = LazyKt.lazy(new Function0<InformationSystemPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$informationSystemPresenter$2
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

    /* renamed from: deliveryTaskSettingPresenter$delegate, reason: from kotlin metadata */
    private final Lazy deliveryTaskSettingPresenter = LazyKt.lazy(new Function0<DeliveryTaskSettingPresenter>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$deliveryTaskSettingPresenter$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DeliveryTaskSettingPresenter invoke() {
            DeliveryTaskSettingPresenter deliveryTaskSettingPresenter;
            PresenterHolder presenterHolder = PresenterHolder.INSTANCE;
            BasePresenterInterface basePresenterInterface = presenterHolder.getBox().get(Reflection.getOrCreateKotlinClass(DeliveryTaskSettingPresenter.class).toString());
            Pdlog.m3273d(presenterHolder.getTAG(), "findOrCreateIt " + Reflection.getOrCreateKotlinClass(DeliveryTaskSettingPresenter.class) + ' ' + basePresenterInterface);
            if (basePresenterInterface == null) {
                deliveryTaskSettingPresenter = new DeliveryTaskSettingPresenter();
                presenterHolder.getBox().put(Reflection.getOrCreateKotlinClass(DeliveryTaskSettingPresenter.class).toString(), deliveryTaskSettingPresenter);
            } else {
                if (!(basePresenterInterface instanceof DeliveryTaskSettingPresenter)) {
                    basePresenterInterface = null;
                }
                deliveryTaskSettingPresenter = (DeliveryTaskSettingPresenter) basePresenterInterface;
            }
            return deliveryTaskSettingPresenter;
        }
    });
    private final LeaseHelper leaseHelper = new LeaseHelper();
    private final BeeperCallHelper beeperCallHelper = new BeeperCallHelper();

    /* renamed from: healthCheckHelper$delegate, reason: from kotlin metadata */
    private final Lazy healthCheckHelper = LazyKt.lazy(new Function0<HealthCheckHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$healthCheckHelper$2
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HealthCheckHelper invoke() {
            HealthCheckHelper healthCheckHelper = new HealthCheckHelper();
            healthCheckHelper.setOnHealthAlert(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$healthCheckHelper$2.1
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
                    Pdlog.m3273d(str, "onHealthAlert ");
                    DeliverTaskEditActivity.this.showMaintenanceTip();
                }
            });
            healthCheckHelper.setOnRgbdAlert(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$healthCheckHelper$2.2
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
                    Pdlog.m3273d(str, "onRgbdAlert ");
                    DeliverTaskEditActivity.this.showRgbdTip();
                }
            });
            return healthCheckHelper;
        }
    });
    private final TouchSensorEventHelper touchSensorEventHelper = new TouchSensorEventHelper();
    private int showStatus = -1;
    private final VoiceTask enterIntoVoice = new VoiceTask(-1, VoiceItem.voice5_1);
    private final VoiceTask recyclePlateEnterIntoVoice = new VoiceTask(-1, VoiceItem.voice34_3);
    private final VoiceTask inputOneTableVoice = new VoiceTask(-1, VoiceItem.voice5_2);
    private final VoiceTask clickGoAlertVoice = new VoiceTask(-1, VoiceItem.voice6_1);

    /* renamed from: mAgentTestHelper$delegate, reason: from kotlin metadata */
    private final Lazy mAgentTestHelper = LazyKt.lazy(new Function0<AgentTestHelper>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$mAgentTestHelper$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AgentTestHelper invoke() {
            return new AgentTestHelper();
        }
    });
    private final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private final Handler mainHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$mainHandler$1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            int i;
            String str;
            boolean z;
            boolean z2;
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
            }
            return true;
        }
    });
    private final DeliverTaskEditActivity$functionButton$1 functionButton = new DeliverTaskEditActivity$functionButton$1(this);
    private final DeliverTaskEditActivity$onHomeSettingDialogClickListener$1 onHomeSettingDialogClickListener = new DeliverTaskEditActivity$onHomeSettingDialogClickListener$1(this);
    private final int TYPE_FEATURE_INPUT = 1;
    private final int TYPE_FEATURE_AIVOICE = 2;
    private final int TYPE_FEATURE_STANDBY = 3;
    private final int TYPE_FEATURE_DIALOG = 4;
    private final Function1<Boolean, Unit> onTouchSensorAnimationListener = new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onTouchSensorAnimationListener$1
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
    private final Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit> onTouchSensorPlaceListener = new Function2<TouchSensorContract.Place, TouchSensorContract.Event, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onTouchSensorPlaceListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            invoke2(place, event);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TouchSensorContract.Place place, TouchSensorContract.Event event) {
            Intrinsics.checkParameterIsNotNull(place, "place");
            Intrinsics.checkParameterIsNotNull(event, "event");
            PeripheralsTrack peripheralsTrack = PeripheralsTrack.INSTANCE;
            int ordinal = place.ordinal();
            int value = event.getValue();
            String simpleName = DeliverTaskEditActivity.this.getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@DeliverTaskEditActi…ty::class.java.simpleName");
            PeripheralsTrack.onTouchPeripherals$default(peripheralsTrack, ordinal, value, simpleName, null, 8, null);
        }
    };
    private final Function1<WakeupInfo, Unit> wakeupListener = new Function1<WakeupInfo, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$wakeupListener$1
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
            String str;
            VoiceInteractionDialog voiceInteractionDialog;
            VoiceInteractionDialog voiceInteractionDialog2;
            int i;
            VoiceInteractionDialog voiceInteractionDialog3;
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "wakeupListener WakeupInfo: " + wakeupInfo);
            VoiceInteractionTrack.INSTANCE.onWakeup(WakeUpType.VOICE);
            voiceInteractionDialog = DeliverTaskEditActivity.this.voiceInteractionDialog;
            if (voiceInteractionDialog == null) {
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                deliverTaskEditActivity.voiceInteractionDialog = new VoiceInteractionDialog(deliverTaskEditActivity);
                voiceInteractionDialog3 = DeliverTaskEditActivity.this.voiceInteractionDialog;
                if (voiceInteractionDialog3 != null) {
                    voiceInteractionDialog3.setOnDialogDismissListener(new VoiceInteractionDialog.OnDialogDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$wakeupListener$1.1
                        @Override // com.pudutech.bumblebee.robot_ui.ui.dialog.VoiceInteractionDialog.OnDialogDismissListener
                        public void onDismiss() {
                            int i2;
                            DeliverTaskEditActivity deliverTaskEditActivity2 = DeliverTaskEditActivity.this;
                            i2 = DeliverTaskEditActivity.this.TYPE_FEATURE_NOMAL;
                            deliverTaskEditActivity2.onFeatureChange(i2);
                            DeliverTaskEditActivity.this.startDelayVoice();
                        }
                    });
                }
            }
            voiceInteractionDialog2 = DeliverTaskEditActivity.this.voiceInteractionDialog;
            if (voiceInteractionDialog2 == null || voiceInteractionDialog2.isShowing()) {
                return;
            }
            DeliverTaskEditActivity deliverTaskEditActivity2 = DeliverTaskEditActivity.this;
            i = deliverTaskEditActivity2.TYPE_FEATURE_AIVOICE;
            deliverTaskEditActivity2.onFeatureChange(i);
            DeliverTaskEditActivity.this.stopDelayVoice();
            voiceInteractionDialog2.show();
        }
    };
    private Function1<? super CustomCallTargetBean, Unit> onCustomCallListener = new Function1<CustomCallTargetBean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onCustomCallListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CustomCallTargetBean customCallTargetBean) {
            invoke2(customCallTargetBean);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(CustomCallTargetBean it) {
            String str;
            Intrinsics.checkParameterIsNotNull(it, "it");
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "onCustomCallListener task: " + it);
            DeliverTaskEditActivity.this.jumpAndFinish(CustomCallActivity.Companion.createIntent(DeliverTaskEditActivity.this, it));
        }
    };
    private final Function2<Integer, String, Boolean> onTaskInputListener = new Function2<Integer, String, Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onTaskInputListener$1
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
            VoiceInteractionDialog voiceInteractionDialog;
            Intrinsics.checkParameterIsNotNull(destination, "destination");
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, " onTaskInputListener : tray = " + i + "; destination = " + destination + "; ");
            voiceInteractionDialog = DeliverTaskEditActivity.this.voiceInteractionDialog;
            if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
                voiceInteractionDialog.dismiss();
            }
            if (((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).selectTray(i - 1)) {
                ((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setTaskName(destination, false);
            } else {
                Context context = RobotContext.INSTANCE.getContext();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = DeliverTaskEditActivity.this.getString(C4188R.string.pdStr2_38);
                Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_38)");
                Object[] objArr = {Integer.valueOf(i)};
                String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                ToastUtils.show(context, format, new Object[0]);
            }
            return true;
        }
    };
    private final Function0<Boolean> onMissionStartListener = new Function0<Boolean>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onMissionStartListener$1
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
            VoiceInteractionDialog voiceInteractionDialog;
            str = DeliverTaskEditActivity.this.TAG;
            Pdlog.m3273d(str, "onMissionStartListener ");
            voiceInteractionDialog = DeliverTaskEditActivity.this.voiceInteractionDialog;
            if (voiceInteractionDialog != null && voiceInteractionDialog.isShowing()) {
                voiceInteractionDialog.dismiss();
            }
            DeliverTaskEditActivity.startDeliverTask$default(DeliverTaskEditActivity.this, "", false, 2, null);
            return true;
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BatteryContract2.ViewEvent.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[BatteryContract2.ViewEvent.POWER_LOW_2_5.ordinal()] = 1;
            $EnumSwitchMapping$0[BatteryContract2.ViewEvent.POWER_EMPTY.ordinal()] = 2;
            $EnumSwitchMapping$0[BatteryContract2.ViewEvent.POWER_LOW_5_10.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[IdleMoveContract.ViewEvent.values().length];
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.READY.ordinal()] = 1;
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.STAND_BY.ordinal()] = 2;
            $EnumSwitchMapping$1[IdleMoveContract.ViewEvent.SLEEP.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[InformationSystemContract.OrderInfoType.values().length];
            $EnumSwitchMapping$2[InformationSystemContract.OrderInfoType.SCAN.ordinal()] = 1;
            $EnumSwitchMapping$2[InformationSystemContract.OrderInfoType.INFORMATION_SYS.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DeliveryTaskSettingPresenter getDeliveryTaskSettingPresenter() {
        return (DeliveryTaskSettingPresenter) this.deliveryTaskSettingPresenter.getValue();
    }

    private final DispatchKeyPresenter getDispatchKeyPresenter() {
        return (DispatchKeyPresenter) this.dispatchKeyPresenter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HealthCheckHelper getHealthCheckHelper() {
        return (HealthCheckHelper) this.healthCheckHelper.getValue();
    }

    private final IdleMovePresenter getIdleMovePresenter() {
        return (IdleMovePresenter) this.idleMovePresenter.getValue();
    }

    private final InformationSystemPresenter getInformationSystemPresenter() {
        return (InformationSystemPresenter) this.informationSystemPresenter.getValue();
    }

    private final AgentTestHelper getMAgentTestHelper() {
        return (AgentTestHelper) this.mAgentTestHelper.getValue();
    }

    private final ScheduleFillInPresenter getScheduleFillInPresenter() {
        return (ScheduleFillInPresenter) this.scheduleFillInPresenter.getValue();
    }

    private final DeliverTaskVM getVm() {
        return (DeliverTaskVM) this.vm.getValue();
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

    public DeliverTaskEditActivity() {
    }

    public static final /* synthetic */ FaceVideoView access$getFaceAnimationView$p(DeliverTaskEditActivity deliverTaskEditActivity) {
        FaceVideoView faceVideoView = deliverTaskEditActivity.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        return faceVideoView;
    }

    /* compiled from: DeliverTaskEditActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/DeliverTaskEditActivity$Companion;", "", "()V", "BIRTHDAY_MODE", "", "BIRTHDAY_MODIFY_MODE", "DIRECT_MODE", "DIRECT_MODIFY_MODE", "MODE_TYPE", "", "MODIFY_MODE", "NEW_MODE", "RECYCLING_PLATE_MODE", "RECYCLING_PLATE_MODIFY_MODE", "SHOW_STATUS_KEY", "SPECIAL_MODE", "SPECIAL_MODIFY_MODE", "STATUS_CALL_TIMEOUT", "STATUS_NO_DINING_OUTLET", "STATUS_NO_RECYCLE_TABLE", "STATUS_NO_WASH_ROOM", "isCanFillIn", "", "()Z", "setCanFillIn", "(Z)V", "isFirstBoot", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isCanFillIn() {
            return DeliverTaskEditActivity.isCanFillIn;
        }

        public final void setCanFillIn(boolean z) {
            DeliverTaskEditActivity.isCanFillIn = z;
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getNeedReinitApp()) {
            return;
        }
        setContentView(C4188R.layout.activity_deliver_task_edit);
        Pdlog.m3273d(this.TAG, "onCreate");
        reset();
        initView();
        initAllData();
        ((Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch)).setOnCheckedChangeListener(new VoiceSwitchChangeListener(null, 0, false, new Function2<CompoundButton, Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onCreate$1
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
                DeliverTaskEditActivity.this.setSteadySwitchLayout(z);
                if (z) {
                    DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                    ToastUtils.show(deliverTaskEditActivity, deliverTaskEditActivity.getString(C4188R.string.pdStr7_153), new Object[0]);
                }
                if (RobotSpeedUtil.INSTANCE.isSteadyRepeatLast()) {
                    Constans.INSTANCE.setSteadyRunning(z);
                }
            }
        }, 7, null));
        this.mLockMachineHelper = new LockMachineHelper(this, this);
        LockMachineHelper lockMachineHelper = this.mLockMachineHelper;
        if (lockMachineHelper != null) {
            getLifecycle().addObserver(lockMachineHelper);
            lockMachineHelper.setOnHavedLocked(new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$onCreate$$inlined$let$lambda$1
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
                    int i;
                    int i2;
                    String str;
                    if (z) {
                        DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                        i2 = deliverTaskEditActivity.TYPE_FEATURE_DIALOG;
                        deliverTaskEditActivity.onFeatureChange(i2);
                        str = DeliverTaskEditActivity.this.TAG;
                        Pdlog.m3273d(str, "setOnHavedLocked:" + z);
                        return;
                    }
                    DeliverTaskEditActivity deliverTaskEditActivity2 = DeliverTaskEditActivity.this;
                    i = deliverTaskEditActivity2.TYPE_FEATURE_NOMAL;
                    deliverTaskEditActivity2.onFeatureChange(i);
                }
            });
        }
        initSignal();
    }

    private final void initSignal() {
        SignalView signal_view = (SignalView) _$_findCachedViewById(C4188R.id.signal_view);
        Intrinsics.checkExpressionValueIsNotNull(signal_view, "signal_view");
        setBindSignal(signal_view);
        DeliverTaskEditActivity deliverTaskEditActivity = this;
        FlowCardHelper.INSTANCE.reportFlowCard(deliverTaskEditActivity);
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "this.applicationContext");
        new SntpTimeSyncManager(applicationContext);
        getMAgentTestHelper().bindLifecycle(deliverTaskEditActivity);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity
    public boolean isBusyState() {
        return isModifyMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Pdlog.m3273d(this.TAG, "onNewIntent");
        setIntent(intent);
        reset();
        initAllData();
        LockMachineHelper lockMachineHelper = this.mLockMachineHelper;
        if (lockMachineHelper != null) {
            lockMachineHelper.onNewIntent();
        }
    }

    private final void initAllData() {
        Pdlog.m3273d(this.TAG, "initAllData");
        SceneRecord.INSTANCE.saveScene(this, SceneRecord.Scene.DELIVERY_SCENE);
        parserIntent();
        setSelectTableFragment();
        initAiVoice();
        updateModeUi();
        setTaskListLayout();
        bindPresenter();
        onFeatureChange(this.TYPE_FEATURE_NOMAL);
        if (((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).hasTask()) {
            onFeatureChange(this.TYPE_FEATURE_INPUT);
        }
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            int i = this.currentModeType;
            iSelectTableFragment.setShowAllGroup(i == 4 || i == 5);
        }
        MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
        setMusicBtnBg(musicPlayerHelper.isOpenMusicSwitch());
        if (this.currentModeType != 1) {
            RobotMapManager.INSTANCE.setCustomOutlet("");
        }
    }

    private final boolean isBirthdayMode() {
        int i = this.currentModeType;
        return i == 2 || i == 3;
    }

    private final void setTaskListLayout() {
        Pdlog.m3273d(this.TAG, "setTaskListLayout");
        int i = this.currentModeType;
        if (i == 7) {
            ArrayList<TrayModel> allTask = TableTaskManager.INSTANCE.getAllTask();
            ArrayList arrayList = new ArrayList();
            for (Object obj : allTask) {
                if (!((TrayModel) obj).getAllDestinations().isEmpty()) {
                    arrayList.add(obj);
                }
            }
            ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).setTask(arrayList);
            return;
        }
        if (i == 6) {
            ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).setTask(new ArrayList<>());
            return;
        }
        if (isModifyMode()) {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setModifyMode(true);
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).start();
            if (Constans.INSTANCE.getSingleTrayMultiTableSwitch() && Constans.INSTANCE.getOrderDeliveryModeSwitch()) {
                TableTaskManager.INSTANCE.setReverse();
            }
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).initData(TableTaskManager.INSTANCE.getAllTask(), TableTaskManager.INSTANCE.getCurrentTask(), Constans.INSTANCE.getSortType());
            return;
        }
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setModifyMode(false);
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).startShowAnimation();
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setOnAnimationFinishListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$setTaskListLayout$1
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
                int i2;
                ArrayList<TrayModel> lastTask;
                int i3;
                RobotDeliverTaskLayout robotDeliverTaskLayout = (RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.robot_deliver_task_layout);
                i2 = DeliverTaskEditActivity.this.currentModeType;
                if (i2 != 0) {
                    i3 = DeliverTaskEditActivity.this.currentModeType;
                    if (i3 != 8) {
                        lastTask = new ArrayList<>();
                        robotDeliverTaskLayout.initData(lastTask, null, Constans.INSTANCE.getSortType());
                    }
                }
                lastTask = Constans.INSTANCE.getLastTask();
                if (lastTask == null) {
                    lastTask = new ArrayList<>();
                }
                robotDeliverTaskLayout.initData(lastTask, null, Constans.INSTANCE.getSortType());
            }
        });
    }

    private final void initAiVoice() {
        Pdlog.m3273d(this.TAG, "initAiVoice");
        AiVoiceManager.INSTANCE.setWorkMode(WorkMode.Delivery);
        AiVoiceManager.INSTANCE.addWakeupListener(this.wakeupListener);
    }

    private final void parserIntent() {
        Pdlog.m3273d(this.TAG, "parserIntent");
        Intent intent = getIntent();
        if (intent != null) {
            this.currentModeType = intent.getIntExtra("MODE_TYPE", 0);
            if (this.currentModeType == 1) {
                String str = this.TAG + 1;
            }
            this.showStatus = intent.getIntExtra("SHOW_STATUS_KEY", 0);
        }
        PeripheralsSceneUtil.INSTANCE.setPlayMode(getMode());
    }

    private final PeripheralsSceneUtil.Mode getMode() {
        int i = this.currentModeType;
        if (i == 0 || i == 1) {
            return PeripheralsSceneUtil.Mode.Delivery;
        }
        if (i == 2 || i == 3) {
            return PeripheralsSceneUtil.Mode.Birthday;
        }
        if (i == 4 || i == 5) {
            return PeripheralsSceneUtil.Mode.Direct;
        }
        if (i == 8 || i == 9) {
            return PeripheralsSceneUtil.Mode.Special;
        }
        return PeripheralsSceneUtil.Mode.Delivery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFeatureChange(int type) {
        Pdlog.m3273d(this.TAG, "onFeatureChange " + type);
        if (type == this.TYPE_FEATURE_NOMAL) {
            if (this.isRelease) {
                return;
            }
            AiVoiceManager.startAiRecording$default(AiVoiceManager.INSTANCE, false, 1, null);
            if (!((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).hasTask()) {
                TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, true, false, 2, null);
            }
            Peripherals.INSTANCE.getFunctionButton().setMute(false);
            IdleMovePresenter idleMovePresenter = getIdleMovePresenter();
            if (idleMovePresenter == null) {
                Intrinsics.throwNpe();
            }
            idleMovePresenter.actionTimerCount(true);
            return;
        }
        if (type == this.TYPE_FEATURE_INPUT) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            return;
        }
        if (type == this.TYPE_FEATURE_AIVOICE) {
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            this.touchSensorEventHelper.stopCurrentAnimation();
            Peripherals.INSTANCE.getFunctionButton().setMute(true);
            VoicePlayer.INSTANCE.stop();
            return;
        }
        if (type != this.TYPE_FEATURE_STANDBY && type == this.TYPE_FEATURE_DIALOG) {
            AiVoiceManager.INSTANCE.stopAiRecording();
            TouchSensorEventHelper.setCanHandle$default(this.touchSensorEventHelper, false, false, 2, null);
            Peripherals.INSTANCE.getFunctionButton().setMute(true);
            if (this.isInStandby) {
                Pdlog.m3273d(this.TAG, "is in standby , do not set actionTimerCount");
                return;
            }
            IdleMovePresenter idleMovePresenter2 = getIdleMovePresenter();
            if (idleMovePresenter2 == null) {
                Intrinsics.throwNpe();
            }
            idleMovePresenter2.actionTimerCount(false);
        }
    }

    private final void showStatusTip() {
        int i = this.showStatus;
        if (i == 1) {
            String string = getString(C4188R.string.pdStr11_2);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr11_2)");
            showDialog$default(this, string, false, 2, null);
            return;
        }
        if (i == 2) {
            String string2 = getString(C4188R.string.pdStr11_1);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr11_1)");
            showDialog$default(this, string2, false, 2, null);
        } else if (i == 3) {
            String string3 = getString(C4188R.string.pdStr16_27);
            Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.pdStr16_27)");
            showDialog$default(this, string3, false, 2, null);
        } else {
            if (i != 4) {
                return;
            }
            String string4 = getString(C4188R.string.pdStr16_26);
            Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.pdStr16_26)");
            showDialog$default(this, string4, false, 2, null);
        }
    }

    static /* synthetic */ void updateStartBtnLayout$default(DeliverTaskEditActivity deliverTaskEditActivity, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        deliverTaskEditActivity.updateStartBtnLayout(z, z2);
    }

    private final void updateStartBtnLayout(boolean isModify, boolean isDelivery) {
        String string;
        int i = !isModify ? C4188R.drawable.home_go_btn : C4188R.drawable.modify_go_on_btn;
        if (isModify) {
            string = getString(C4188R.string.pdStr2_13);
        } else if (!isDelivery) {
            string = getString(C4188R.string.pdStr16_5);
        } else {
            string = getString(C4188R.string.pdStr2_2);
        }
        if (Constans.INSTANCE.getSettingSteadyModeSwitch() && isDelivery) {
            TextView btn_start = (TextView) _$_findCachedViewById(C4188R.id.btn_start);
            Intrinsics.checkExpressionValueIsNotNull(btn_start, "btn_start");
            btn_start.setVisibility(4);
            LinearLayout steady_btn_ly = (LinearLayout) _$_findCachedViewById(C4188R.id.steady_btn_ly);
            Intrinsics.checkExpressionValueIsNotNull(steady_btn_ly, "steady_btn_ly");
            steady_btn_ly.setVisibility(0);
            TextView small_btn_start = (TextView) _$_findCachedViewById(C4188R.id.small_btn_start);
            Intrinsics.checkExpressionValueIsNotNull(small_btn_start, "small_btn_start");
            Sdk27PropertiesKt.setBackgroundResource(small_btn_start, i);
            TextView small_btn_start2 = (TextView) _$_findCachedViewById(C4188R.id.small_btn_start);
            Intrinsics.checkExpressionValueIsNotNull(small_btn_start2, "small_btn_start");
            small_btn_start2.setText(string);
            int steadyModeType = Constans.INSTANCE.getSteadyModeType();
            if (steadyModeType == 0) {
                Switch steady_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch, "steady_mode_switch");
                steady_mode_switch.setChecked(TableTaskManager.INSTANCE.getMovePerformance() == MovePerformance.STEADY);
                Switch steady_mode_switch2 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch2, "steady_mode_switch");
                steady_mode_switch2.setVisibility(0);
            } else if (steadyModeType == 1) {
                Switch steady_mode_switch3 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch3, "steady_mode_switch");
                steady_mode_switch3.setChecked(true);
                Switch steady_mode_switch4 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch4, "steady_mode_switch");
                steady_mode_switch4.setVisibility(8);
            } else if (steadyModeType == 2) {
                Switch steady_mode_switch5 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch5, "steady_mode_switch");
                steady_mode_switch5.setChecked(Constans.INSTANCE.isSteadyRunning());
                Switch steady_mode_switch6 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
                Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch6, "steady_mode_switch");
                steady_mode_switch6.setVisibility(0);
            }
            Pdlog.m3273d(this.TAG, "updateStartBtnLayout steadyModeType mode = " + Constans.INSTANCE.getSteadyModeType());
            Switch steady_mode_switch7 = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
            Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch7, "steady_mode_switch");
            setSteadySwitchLayout(steady_mode_switch7.isChecked());
            return;
        }
        LinearLayout steady_btn_ly2 = (LinearLayout) _$_findCachedViewById(C4188R.id.steady_btn_ly);
        Intrinsics.checkExpressionValueIsNotNull(steady_btn_ly2, "steady_btn_ly");
        steady_btn_ly2.setVisibility(8);
        TextView btn_start2 = (TextView) _$_findCachedViewById(C4188R.id.btn_start);
        Intrinsics.checkExpressionValueIsNotNull(btn_start2, "btn_start");
        btn_start2.setVisibility(0);
        TextView btn_start3 = (TextView) _$_findCachedViewById(C4188R.id.btn_start);
        Intrinsics.checkExpressionValueIsNotNull(btn_start3, "btn_start");
        Sdk27PropertiesKt.setBackgroundResource(btn_start3, i);
        UiUtils.adjustTvTextSize((TextView) _$_findCachedViewById(C4188R.id.btn_start), DensityUtil.dp2px(this, 675.0f), string);
        TextView btn_start4 = (TextView) _$_findCachedViewById(C4188R.id.btn_start);
        Intrinsics.checkExpressionValueIsNotNull(btn_start4, "btn_start");
        btn_start4.setText(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSteadySwitchLayout(boolean r5) {
        Pdlog.m3273d(this.TAG, "setSteadySwitchLayout : switch = " + r5 + "; ");
        if (r5) {
            ((TextView) _$_findCachedViewById(C4188R.id.steady_btn_tv)).setTextColor(getColor(C4188R.color.steady_mode_switch_layout_on_color));
        } else if (isBirthdayMode()) {
            ((TextView) _$_findCachedViewById(C4188R.id.steady_btn_tv)).setTextColor(getColor(C4188R.color.white));
        } else {
            ((TextView) _$_findCachedViewById(C4188R.id.steady_btn_tv)).setTextColor(getColor(C4188R.color.font_color_1));
        }
    }

    private final void updateModeUi() {
        RelativeLayout birthday_set_container = (RelativeLayout) _$_findCachedViewById(C4188R.id.birthday_set_container);
        Intrinsics.checkExpressionValueIsNotNull(birthday_set_container, "birthday_set_container");
        birthday_set_container.setVisibility(8);
        switch (this.currentModeType) {
            case 0:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ImageView setting_info = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info, "setting_info");
                setting_info.setVisibility(0);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, false, false, 2, null);
                TextView mode_desc = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc, "mode_desc");
                mode_desc.setText(getString(C4188R.string.pdStr2_1));
                LinearLayout setting_ly = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly, "setting_ly");
                setViewVisibility(setting_ly, true);
                return;
            case 1:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ImageView setting_info2 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info2, "setting_info");
                setting_info2.setVisibility(8);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, true, false, 2, null);
                TextView mode_desc2 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc2, "mode_desc");
                mode_desc2.setText(getString(C4188R.string.pdStr17_6));
                LinearLayout setting_ly2 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly2, "setting_ly");
                setViewVisibility(setting_ly2, false);
                return;
            case 2:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(true);
                MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper.setOpenBirthdaySwitch(true);
                ImageView setting_info3 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info3, "setting_info");
                setting_info3.setVisibility(0);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, false, false, 2, null);
                TextView mode_desc3 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc3, "mode_desc");
                mode_desc3.setText(getString(C4188R.string.pdStr9_1));
                LinearLayout setting_ly3 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly3, "setting_ly");
                setViewVisibility(setting_ly3, false);
                if (Constans.INSTANCE.isBirthdayMusicSetTip()) {
                    ImageView iv_birthday_music_set_tip = (ImageView) _$_findCachedViewById(C4188R.id.iv_birthday_music_set_tip);
                    Intrinsics.checkExpressionValueIsNotNull(iv_birthday_music_set_tip, "iv_birthday_music_set_tip");
                    iv_birthday_music_set_tip.setVisibility(0);
                } else {
                    ImageView iv_birthday_music_set_tip2 = (ImageView) _$_findCachedViewById(C4188R.id.iv_birthday_music_set_tip);
                    Intrinsics.checkExpressionValueIsNotNull(iv_birthday_music_set_tip2, "iv_birthday_music_set_tip");
                    iv_birthday_music_set_tip2.setVisibility(8);
                }
                if (Constans.INSTANCE.isFromBirthdayMusicSet()) {
                    Constans.INSTANCE.setFromBirthdayMusicSet(false);
                } else {
                    Constans.INSTANCE.setBirthdayText("");
                }
                RelativeLayout birthday_set_container2 = (RelativeLayout) _$_findCachedViewById(C4188R.id.birthday_set_container);
                Intrinsics.checkExpressionValueIsNotNull(birthday_set_container2, "birthday_set_container");
                birthday_set_container2.setVisibility(0);
                return;
            case 3:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(true);
                ImageView setting_info4 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info4, "setting_info");
                setting_info4.setVisibility(8);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, true, false, 2, null);
                TextView mode_desc4 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc4, "mode_desc");
                mode_desc4.setText(getString(C4188R.string.pdStr17_6));
                LinearLayout setting_ly4 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly4, "setting_ly");
                setViewVisibility(setting_ly4, false);
                if (Constans.INSTANCE.isBirthdayMusicSetTip()) {
                    ImageView iv_birthday_music_set_tip3 = (ImageView) _$_findCachedViewById(C4188R.id.iv_birthday_music_set_tip);
                    Intrinsics.checkExpressionValueIsNotNull(iv_birthday_music_set_tip3, "iv_birthday_music_set_tip");
                    iv_birthday_music_set_tip3.setVisibility(0);
                } else {
                    ImageView iv_birthday_music_set_tip4 = (ImageView) _$_findCachedViewById(C4188R.id.iv_birthday_music_set_tip);
                    Intrinsics.checkExpressionValueIsNotNull(iv_birthday_music_set_tip4, "iv_birthday_music_set_tip");
                    iv_birthday_music_set_tip4.setVisibility(8);
                }
                RelativeLayout birthday_set_container3 = (RelativeLayout) _$_findCachedViewById(C4188R.id.birthday_set_container);
                Intrinsics.checkExpressionValueIsNotNull(birthday_set_container3, "birthday_set_container");
                birthday_set_container3.setVisibility(0);
                return;
            case 4:
                showRecycleTaskLayout(false);
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                MusicPlayerHelper musicPlayerHelper2 = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper2, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper2.setOpenBirthdaySwitch(false);
                ImageView setting_info5 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info5, "setting_info");
                setting_info5.setVisibility(0);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, false, false, 2, null);
                TextView mode_desc5 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc5, "mode_desc");
                mode_desc5.setText(getString(C4188R.string.pdStr4_1));
                LinearLayout setting_ly5 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly5, "setting_ly");
                setViewVisibility(setting_ly5, false);
                return;
            case 5:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                MusicPlayerHelper musicPlayerHelper3 = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper3, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper3.setOpenBirthdaySwitch(false);
                ImageView setting_info6 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info6, "setting_info");
                setting_info6.setVisibility(8);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(1);
                updateStartBtnLayout$default(this, true, false, 2, null);
                TextView mode_desc6 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc6, "mode_desc");
                mode_desc6.setText(getString(C4188R.string.pdStr17_6));
                LinearLayout setting_ly6 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly6, "setting_ly");
                setViewVisibility(setting_ly6, false);
                return;
            case 6:
                showRecycleTaskLayout(true);
                switchBirthdayTheme(false);
                ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).showBottomBtn(true);
                ImageView setting_info7 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info7, "setting_info");
                setting_info7.setVisibility(0);
                updateStartBtnLayout(false, true);
                LinearLayout setting_ly7 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly7, "setting_ly");
                setViewVisibility(setting_ly7, false);
                return;
            case 7:
                showRecycleTaskLayout(true);
                switchBirthdayTheme(false);
                ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).showBottomBtn(false);
                ImageView setting_info8 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info8, "setting_info");
                setting_info8.setVisibility(8);
                updateStartBtnLayout(true, true);
                LinearLayout setting_ly8 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly8, "setting_ly");
                setViewVisibility(setting_ly8, false);
                return;
            case 8:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ImageView setting_info9 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info9, "setting_info");
                setting_info9.setVisibility(0);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, false, false, 2, null);
                TextView mode_desc7 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc7, "mode_desc");
                mode_desc7.setText(getString(C4188R.string.pdStr17_1));
                LinearLayout setting_ly9 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly9, "setting_ly");
                setViewVisibility(setting_ly9, false);
                return;
            case 9:
                showRecycleTaskLayout(false);
                switchBirthdayTheme(false);
                ImageView setting_info10 = (ImageView) _$_findCachedViewById(C4188R.id.setting_info);
                Intrinsics.checkExpressionValueIsNotNull(setting_info10, "setting_info");
                setting_info10.setVisibility(8);
                ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setInputTaskCount(PalletCountHelper.INSTANCE.getCount());
                updateStartBtnLayout$default(this, true, false, 2, null);
                TextView mode_desc8 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
                Intrinsics.checkExpressionValueIsNotNull(mode_desc8, "mode_desc");
                mode_desc8.setText(getString(C4188R.string.pdStr17_6));
                LinearLayout setting_ly10 = (LinearLayout) _$_findCachedViewById(C4188R.id.setting_ly);
                Intrinsics.checkExpressionValueIsNotNull(setting_ly10, "setting_ly");
                setViewVisibility(setting_ly10, false);
                return;
            default:
                return;
        }
    }

    private final void showRecycleTaskLayout(boolean r5) {
        if (r5) {
            RecyclePlateTaskLayout recycle_plate_task_layout = (RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout);
            Intrinsics.checkExpressionValueIsNotNull(recycle_plate_task_layout, "recycle_plate_task_layout");
            recycle_plate_task_layout.setVisibility(0);
            RobotDeliverTaskLayout robot_deliver_task_layout = (RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout);
            Intrinsics.checkExpressionValueIsNotNull(robot_deliver_task_layout, "robot_deliver_task_layout");
            robot_deliver_task_layout.setVisibility(4);
            TextView mode_desc = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
            Intrinsics.checkExpressionValueIsNotNull(mode_desc, "mode_desc");
            mode_desc.setVisibility(4);
            return;
        }
        RecyclePlateTaskLayout recycle_plate_task_layout2 = (RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout);
        Intrinsics.checkExpressionValueIsNotNull(recycle_plate_task_layout2, "recycle_plate_task_layout");
        recycle_plate_task_layout2.setVisibility(8);
        RobotDeliverTaskLayout robot_deliver_task_layout2 = (RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout);
        Intrinsics.checkExpressionValueIsNotNull(robot_deliver_task_layout2, "robot_deliver_task_layout");
        robot_deliver_task_layout2.setVisibility(0);
        TextView mode_desc2 = (TextView) _$_findCachedViewById(C4188R.id.mode_desc);
        Intrinsics.checkExpressionValueIsNotNull(mode_desc2, "mode_desc");
        mode_desc2.setVisibility(0);
    }

    private final void switchBirthdayTheme(boolean r4) {
        View childAt;
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            iSelectTableFragment.setBirthdayTheme(r4);
        }
        if (r4) {
            View findViewById = findViewById(R.id.content);
            if (!(findViewById instanceof ViewGroup)) {
                findViewById = null;
            }
            ViewGroup viewGroup = (ViewGroup) findViewById;
            childAt = viewGroup != null ? viewGroup.getChildAt(0) : null;
            if (childAt != null) {
                childAt.setBackgroundColor(getColor(C4188R.color.theme_birthday_bg));
            }
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).switchBirthday(true);
            return;
        }
        View findViewById2 = findViewById(R.id.content);
        if (!(findViewById2 instanceof ViewGroup)) {
            findViewById2 = null;
        }
        ViewGroup viewGroup2 = (ViewGroup) findViewById2;
        childAt = viewGroup2 != null ? viewGroup2.getChildAt(0) : null;
        if (childAt != null) {
            childAt.setBackgroundColor(getColor(C4188R.color.theme_main_bg));
        }
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).switchBirthday(false);
    }

    private final void initView() {
        settingInfoClickListener();
        ((TextView) _$_findCachedViewById(C4188R.id.btn_start)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverTaskEditActivity.startDeliverTask$default(DeliverTaskEditActivity.this, null, false, 3, null);
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.small_btn_start)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverTaskEditActivity.startDeliverTask$default(DeliverTaskEditActivity.this, null, false, 3, null);
            }
        });
        FaceVideoView face_animation_view = (FaceVideoView) _$_findCachedViewById(C4188R.id.face_animation_view);
        Intrinsics.checkExpressionValueIsNotNull(face_animation_view, "face_animation_view");
        this.faceAnimationView = face_animation_view;
        ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setOnTaskChangeListener(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$3
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
                if (((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).hasTask()) {
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
        _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout).setOnClickListener(new View.OnClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
            }
        });
        ((TextView) _$_findCachedViewById(C4188R.id.btn_go_to_task_ui)).setOnClickListener(new DeliverTaskEditActivity$initView$5(this));
        ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).setGoRecycleClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$6
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = DeliverTaskEditActivity.this.TAG;
                Pdlog.m3273d(str, "setGoRecycleClickListener onSingleClick");
                DeliverTaskEditActivity.this.jump(TransferDishesActivity.INSTANCE.getGoRecycleTableIntent(DeliverTaskEditActivity.this));
            }
        });
        ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).setGoWashRoomClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$7
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                String str;
                str = DeliverTaskEditActivity.this.TAG;
                Pdlog.m3273d(str, "setGoWashRoomClickListener onSingleClick");
                DeliverTaskEditActivity.this.jump(TransferDishesActivity.INSTANCE.getGoWashRoomIntent(DeliverTaskEditActivity.this));
            }
        });
        ((Button) _$_findCachedViewById(C4188R.id.history_btn)).setOnClickListener(new DeliverTaskEditActivity$initView$8(this));
        ((Button) _$_findCachedViewById(C4188R.id.voice_setting_btn)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$9
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverTaskEditActivity.this.showSelectVoiceDialog();
            }
        });
        ((Button) _$_findCachedViewById(C4188R.id.setting_btn)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$10
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                int i;
                Intent createIntent = DeliverSettingActivity.INSTANCE.createIntent(DeliverTaskEditActivity.this);
                i = DeliverTaskEditActivity.this.currentModeType;
                createIntent.putExtra("MODE_TYPE", i);
                DeliverTaskEditActivity.this.jump(createIntent);
            }
        });
        Button pallet_tts_btn = (Button) _$_findCachedViewById(C4188R.id.pallet_tts_btn);
        Intrinsics.checkExpressionValueIsNotNull(pallet_tts_btn, "pallet_tts_btn");
        pallet_tts_btn.setVisibility(isPalletTtsEnable() ? 0 : 8);
        ((Button) _$_findCachedViewById(C4188R.id.pallet_tts_btn)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$11
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                super.onSingleClick();
                DeliverTaskEditActivity.this.showPalletTtsWindow();
            }
        });
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.voice_tip_tv);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        Button music_btn = (Button) _$_findCachedViewById(C4188R.id.music_btn);
        Intrinsics.checkExpressionValueIsNotNull(music_btn, "music_btn");
        VoiceLongClickListenerKt.onVoiceLongClickListener$default(music_btn, null, 0, new Function1<View, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$13
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
                MusicPlayerHelper musicPlayerHelper = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper, "MusicPlayerHelper.getInstance()");
                if (musicPlayerHelper.isOpenMusicSwitch()) {
                    DeliverTaskEditActivity.this.setMusicBtnBg(false);
                    MusicPlayerHelper musicPlayerHelper2 = MusicPlayerHelper.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper2, "MusicPlayerHelper.getInstance()");
                    musicPlayerHelper2.setOpenMusicSwitch(false);
                    return;
                }
                DeliverTaskEditActivity.this.setMusicBtnBg(true);
                MusicPlayerHelper musicPlayerHelper3 = MusicPlayerHelper.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(musicPlayerHelper3, "MusicPlayerHelper.getInstance()");
                musicPlayerHelper3.setOpenMusicSwitch(true);
            }
        }, 3, null);
        ((TextView) _$_findCachedViewById(C4188R.id.tv_birthday_music_set)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initView$14
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                int i;
                if (Constans.INSTANCE.isBirthdayMusicSetTip()) {
                    Constans.INSTANCE.setBirthdayMusicSetTip(false);
                    ImageView iv_birthday_music_set_tip = (ImageView) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.iv_birthday_music_set_tip);
                    Intrinsics.checkExpressionValueIsNotNull(iv_birthday_music_set_tip, "iv_birthday_music_set_tip");
                    iv_birthday_music_set_tip.setVisibility(8);
                }
                Intent intent = new Intent(DeliverTaskEditActivity.this, (Class<?>) BirthdayModeMusicSettingActivity.class);
                i = DeliverTaskEditActivity.this.currentModeType;
                intent.putExtra("MODE_TYPE", i);
                DeliverTaskEditActivity.this.jump(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setMusicBtnBg(boolean open) {
        Button music_btn = (Button) _$_findCachedViewById(C4188R.id.music_btn);
        Intrinsics.checkExpressionValueIsNotNull(music_btn, "music_btn");
        Sdk27PropertiesKt.setBackgroundResource(music_btn, open ? C4188R.drawable.music_btn_selector : C4188R.drawable.ic_music_close);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void gotoquitFillIn() {
        isCanFillIn = false;
        View turn_back_fill_in_layout = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
        turn_back_fill_in_layout.setVisibility(8);
        getScheduleFillInPresenter().quitFillIn();
        dismissPasswordDialog();
    }

    private final void initDragTips() {
        Pdlog.m3275i(this.TAG, "initDragTips: " + Constans.INSTANCE.getDragTip() + "  size:" + RobotMapManager.INSTANCE.getAllTableGroup().size());
        if (Constans.INSTANCE.getDragTip() && (RobotMapManager.INSTANCE.getAllTableGroup().size() != 0 || this.currentModeType == 4)) {
            View layout_drag = _$_findCachedViewById(C4188R.id.layout_drag);
            Intrinsics.checkExpressionValueIsNotNull(layout_drag, "layout_drag");
            layout_drag.setVisibility(0);
            ((TextView) _$_findCachedViewById(C4188R.id.btn_i_get)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initDragTips$1
                /* JADX INFO: Access modifiers changed from: package-private */
                {
                    super(null, 0, 3, null);
                }

                @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
                public void onSingleClick() {
                    CheckBox checkbox_tips = (CheckBox) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.checkbox_tips);
                    Intrinsics.checkExpressionValueIsNotNull(checkbox_tips, "checkbox_tips");
                    if (checkbox_tips.isChecked()) {
                        Constans.INSTANCE.setDragTip(false);
                    }
                    View layout_drag2 = DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.layout_drag);
                    Intrinsics.checkExpressionValueIsNotNull(layout_drag2, "layout_drag");
                    layout_drag2.setVisibility(8);
                }
            });
            return;
        }
        View layout_drag2 = _$_findCachedViewById(C4188R.id.layout_drag);
        Intrinsics.checkExpressionValueIsNotNull(layout_drag2, "layout_drag");
        layout_drag2.setVisibility(8);
    }

    private final void setSelectTableFragment() {
        int tableInputType = Constans.INSTANCE.getTableInputType();
        if (tableInputType == 0 && !(this.selectTableFragment instanceof SelectTableFragment)) {
            this.selectTableFragment = new SelectTableFragment();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
            if (iSelectTableFragment == null) {
                Intrinsics.throwNpe();
            }
            FragmentUtils.replace(supportFragmentManager, iSelectTableFragment, C4188R.id.select_table_container);
            initTablesInput();
            initDragTips();
            return;
        }
        if (tableInputType != 1 || (this.selectTableFragment instanceof SmartInputTableFragment)) {
            return;
        }
        this.selectTableFragment = new SmartInputTableFragment();
        FragmentManager supportFragmentManager2 = getSupportFragmentManager();
        ISelectTableFragment iSelectTableFragment2 = this.selectTableFragment;
        if (iSelectTableFragment2 == null) {
            Intrinsics.throwNpe();
        }
        FragmentUtils.replace(supportFragmentManager2, iSelectTableFragment2, C4188R.id.select_table_container);
        initTablesInput();
    }

    private final boolean isModifyMode() {
        int i = this.currentModeType;
        return i == 1 || i == 3 || i == 5 || i == 7 || i == 9;
    }

    private final List<Integer> checkTrayDestination(ArrayList<TrayModel> trayList) {
        Pdlog.m3273d(this.TAG, "checkTrayDestination trayList: " + trayList);
        Pdlog.m3273d(this.TAG, "checkTrayDestination _placePalletList: " + getVm().getPlacePalletList());
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = getVm().getPlacePalletList().iterator();
        while (it.hasNext()) {
            int intValue = ((Number) it.next()).intValue();
            ArrayList<DeliveryModel> allDestinations = trayList.get(intValue - 1).getAllDestinations();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : allDestinations) {
                if (((DeliveryModel) obj).getStatus() == TaskStatus.AWAIT) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            Pdlog.m3273d(this.TAG, "checkTrayDestination destList: " + arrayList3);
            if (arrayList3.isEmpty()) {
                arrayList.add(Integer.valueOf(intValue));
            }
        }
        Pdlog.m3273d(this.TAG, "checkTrayDestination warnList: " + arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void startDeliverTask$default(DeliverTaskEditActivity deliverTaskEditActivity, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        if ((i & 2) != 0) {
            z = false;
        }
        deliverTaskEditActivity.startDeliverTask(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startDeliverTask(final String employees, boolean force) {
        boolean z;
        String string;
        ShowTipMsgDialog showDialog;
        int i;
        if (BatteryInfoManager.INSTANCE.checkAllowedForMove() || isModifyMode()) {
            Pdlog.m3273d(this.TAG, "startDeliverTask");
            ArrayList<TrayModel> selectTask = getSelectTask();
            List<Integer> checkTrayDestination = checkTrayDestination(selectTask);
            if ((!checkTrayDestination.isEmpty()) && !force && getVm().getTrayDestinationWarnSwitch() && !isModifyMode() && this.currentModeType == 0) {
                StringBuilder sb = new StringBuilder();
                Iterator<T> it = checkTrayDestination.iterator();
                while (it.hasNext()) {
                    sb.append(((Number) it.next()).intValue());
                    sb.append(",");
                }
                String string2 = getString(C4188R.string.pdStr5_1);
                Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.pdStr5_1)");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string3 = getString(C4188R.string.tray_destination_empty_warn);
                Intrinsics.checkExpressionValueIsNotNull(string3, "getString(R.string.tray_destination_empty_warn)");
                Object[] objArr = {sb.substring(0, sb.length() - 1)};
                String format = String.format(string3, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                String string4 = getString(C4188R.string.continue_start);
                Intrinsics.checkExpressionValueIsNotNull(string4, "getString(R.string.continue_start)");
                String string5 = getString(C4188R.string.pdStr8_4);
                Intrinsics.checkExpressionValueIsNotNull(string5, "getString(R.string.pdStr8_4)");
                showTwoBtnDialog(string2, format, string4, string5, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$startDeliverTask$2
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
                        DeliverTaskEditActivity.this.startDeliverTask(employees, true);
                    }
                }, new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$startDeliverTask$3
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                });
                return;
            }
            ArrayList<TrayModel> arrayList = selectTask;
            if (!(arrayList instanceof Collection) || !arrayList.isEmpty()) {
                Iterator<T> it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    if (!((TrayModel) it2.next()).getAllDestinations().isEmpty()) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (!isModifyMode() && Constans.INSTANCE.getRepeatLastTaskSwitch() && z && ((i = this.currentModeType) == 0 || i == 8)) {
                Constans.INSTANCE.saveLastTask(selectTask);
            }
            Iterator<T> it3 = arrayList.iterator();
            while (it3.hasNext()) {
                CollectionsKt.reverse(((TrayModel) it3.next()).getAllDestinations());
            }
            if (!z) {
                Pdlog.m3273d(this.TAG, "startDeliverTask task is null ...");
                if (this.needSelectTableDialog == null) {
                    int i2 = this.currentModeType;
                    if (i2 == 7 || i2 == 6) {
                        String string6 = getString(C4188R.string.pdStr16_30);
                        Intrinsics.checkExpressionValueIsNotNull(string6, "getString(R.string.pdStr16_30)");
                        showDialog = showDialog(string6, true);
                    } else {
                        String string7 = getString(C4188R.string.pdStr2_18);
                        Intrinsics.checkExpressionValueIsNotNull(string7, "getString(R.string.pdStr2_18)");
                        showDialog = showDialog(string7, true);
                    }
                    this.needSelectTableDialog = showDialog;
                }
                ShowTipMsgDialog showTipMsgDialog = this.needSelectTableDialog;
                if (showTipMsgDialog != null) {
                    int i3 = this.currentModeType;
                    if (i3 == 7 || i3 == 6) {
                        string = getString(C4188R.string.pdStr16_30);
                    } else {
                        string = getString(C4188R.string.pdStr2_18);
                    }
                    Intrinsics.checkExpressionValueIsNotNull(string, "if (currentModeType == R…18)\n                    }");
                    showTipMsgDialog.showTipMsg(string);
                }
                ShowTipMsgDialog showTipMsgDialog2 = this.needSelectTableDialog;
                if (showTipMsgDialog2 != null) {
                    showTipMsgDialog2.show();
                    return;
                }
                return;
            }
            TableTaskManager.INSTANCE.setAllTask(selectTask, ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getSortType(), getMovePerformance(), isCanSaveDeliveryHistory());
            notifyOrderStart(selectTask, employees);
            Pdlog.m3273d(this.TAG, "startDeliverTask task is " + selectTask);
            jumpToStartTask();
            return;
        }
        Pdlog.m3273d(this.TAG, "batteryPresenter check Allowed for move is false");
        String string8 = getString(C4188R.string.pdStr2_19);
        Intrinsics.checkExpressionValueIsNotNull(string8, "getString(R.string.pdStr2_19)");
        MyBaseActivity.showTipDialog$default(this, string8, null, null, null, 14, null);
        VoicePlayer.INSTANCE.play(new VoiceTask(-1L, VoiceItem.voice14_2));
    }

    private final boolean isCanSaveDeliveryHistory() {
        int i = this.currentModeType;
        return i == 0 || i == 1 || i == 8 || i == 9 || i == 3 || i == 2;
    }

    private final MovePerformance getMovePerformance() {
        if (Constans.INSTANCE.getSettingSteadyModeSwitch()) {
            Switch steady_mode_switch = (Switch) _$_findCachedViewById(C4188R.id.steady_mode_switch);
            Intrinsics.checkExpressionValueIsNotNull(steady_mode_switch, "steady_mode_switch");
            if (steady_mode_switch.isChecked()) {
                return MovePerformance.STEADY;
            }
        }
        return MovePerformance.NORMAL;
    }

    private final void notifyOrderStart(ArrayList<TrayModel> allTask, String employees) {
        ArrayList<InformationSystemContract.OrderInfo> arrayList = new ArrayList<>();
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
        if (arrayList.isEmpty()) {
            return;
        }
        getInformationSystemPresenter().actionOrderStart(arrayList, new ICallback() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$notifyOrderStart$2
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                String str;
                Intrinsics.checkParameterIsNotNull(e, "e");
                str = DeliverTaskEditActivity.this.TAG;
                Pdlog.m3274e(str, "onFailed : " + Log.getStackTraceString(e));
            }

            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
                String str;
                str = DeliverTaskEditActivity.this.TAG;
                Pdlog.m3273d(str, "onSuccess : result = " + result + "; ");
            }
        }, employees);
    }

    private final void jumpToStartTask() {
        Intent intent;
        Pdlog.m3273d(this.TAG, "jumpToStartTask currentModeType = " + this.currentModeType);
        int i = this.currentModeType;
        if (i == 6 || i == 7) {
            intent = new Intent(this, (Class<?>) RecyclingPlatesActivity.class);
        } else {
            intent = new Intent(this, (Class<?>) DeliverActivity.class);
        }
        int i2 = this.currentModeType;
        if (i2 == 2 || i2 == 3) {
            intent.putExtra("DELIVER_MODE", 1);
        } else if (i2 == 4 || i2 == 5) {
            intent.putExtra("DELIVER_MODE", 2);
        } else if (i2 == 8 || i2 == 9) {
            intent.putExtra("DELIVER_MODE", 3);
        }
        int i3 = this.currentModeType;
        if (i3 == 0 || i3 == 4 || i3 == 2 || i3 == 8) {
            SpUtils.set(RobotContext.INSTANCE.getContext(), "key_deliver_recycle_plate_switch", false);
        }
        isCanFillIn = false;
        jump(intent);
    }

    private final ArrayList<TrayModel> getSelectTask() {
        int i = this.currentModeType;
        if (i == 7 || i == 6) {
            return ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).getAllTask();
        }
        return ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getAllTask();
    }

    private final ConfirmTipDialog showTwoBtnDialog(final String title, final String msg, final String leftBtn, final String rightBtn, final Function0<Unit> onBtn1Click, final Function0<Unit> onBtn2Click) {
        final ConfirmTipDialog confirmTipDialog = new ConfirmTipDialog((Context) this, true);
        confirmTipDialog.setTitle(title);
        confirmTipDialog.setMsg(msg);
        confirmTipDialog.setBtn1Text(leftBtn);
        confirmTipDialog.setBtn2Text(rightBtn);
        confirmTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showTwoBtnDialog$$inlined$apply$lambda$1
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
                onBtn1Click.invoke();
                ConfirmTipDialog.this.dismiss();
            }
        });
        confirmTipDialog.setOnBtn2Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showTwoBtnDialog$$inlined$apply$lambda$2
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
                onBtn2Click.invoke();
                ConfirmTipDialog.this.dismiss();
            }
        });
        confirmTipDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showTwoBtnDialog$$inlined$apply$lambda$3
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                int i;
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
                deliverTaskEditActivity.onFeatureChange(i);
            }
        });
        confirmTipDialog.show();
        onFeatureChange(this.TYPE_FEATURE_DIALOG);
        return confirmTipDialog;
    }

    static /* synthetic */ ShowTipMsgDialog showDialog$default(DeliverTaskEditActivity deliverTaskEditActivity, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return deliverTaskEditActivity.showDialog(str, z);
    }

    private final ShowTipMsgDialog showDialog(String msg, final boolean needCloseSensorFace) {
        return MyBaseActivity.showTipDialog$default(this, msg, new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showDialog$1
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                int i;
                TouchSensorEventHelper touchSensorEventHelper;
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_DIALOG;
                deliverTaskEditActivity.onFeatureChange(i);
                if (needCloseSensorFace) {
                    touchSensorEventHelper = DeliverTaskEditActivity.this.touchSensorEventHelper;
                    touchSensorEventHelper.stopCurrentAnimation();
                }
            }
        }, new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showDialog$2
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
        int i = this.currentModeType;
        if (i == 6 || i == 7) {
            return;
        }
        this.isPlayClickGoAlertVoice = true;
        VoicePlayer.INSTANCE.play(this.clickGoAlertVoice);
        PeripheralsSceneUtil.INSTANCE.editDeliverGoAlert();
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
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

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        Pdlog.m3273d(this.TAG, "onStart");
        if (this.isRelease && this.isJumpToMusicAc) {
            Pdlog.m3273d(this.TAG, "onStart isJumpToMusicAc");
            reset();
            initAllData();
        }
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, android.app.Activity, android.view.Window.Callback
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
            showStatusTip();
            Pdlog.m3273d(this.TAG, "onWindowFocusChanged isPlayLowPowerVoice = " + this.isPlayLowPowerVoice + "  ");
            playEntryVoiceIfNeed();
            PeripheralsSceneUtil.INSTANCE.idleState(isBirthdayMode());
        }
    }

    private final void settingInfoClickListener() {
        ((ImageView) _$_findCachedViewById(C4188R.id.setting_info)).setOnClickListener(new OnLazyVoiceClickListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$settingInfoClickListener$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(null, 0, 3, null);
            }

            @Override // com.pudutech.bumblebee.robot_ui.ui_utils.OnLazyVoiceClickListener
            public void onSingleClick() {
                DeliverTaskEditActivity.this.showSettingDialog();
            }
        });
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
            homeSettingDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showSettingDialog$1
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
            homeSettingDialog3.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showSettingDialog$2
                @Override // android.content.DialogInterface.OnShowListener
                public final void onShow(DialogInterface dialogInterface) {
                    String str;
                    TouchSensorEventHelper touchSensorEventHelper;
                    str = DeliverTaskEditActivity.this.TAG;
                    Pdlog.m3273d(str, "showSettingDialog setOnShowListener");
                    touchSensorEventHelper = DeliverTaskEditActivity.this.touchSensorEventHelper;
                    touchSensorEventHelper.stopCurrentAnimation();
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

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
        Pdlog.m3273d(this.TAG, "onDestroy");
    }

    private final void bindPresenter() {
        Pdlog.m3273d(this.TAG, "bindPresenter");
        VoiceInteractionTrack voiceInteractionTrack = VoiceInteractionTrack.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this@DeliverTaskEditActi…ty::class.java.simpleName");
        VoiceInteractionTrack.onCreateTask$default(voiceInteractionTrack, simpleName, null, 2, null);
        BatteryInfoManager.INSTANCE.addBatteryChangeNotifyListeners(this);
        IdleMovePresenter idleMovePresenter = getIdleMovePresenter();
        if (idleMovePresenter == null) {
            Intrinsics.throwNpe();
        }
        DeliverTaskEditActivity deliverTaskEditActivity = this;
        idleMovePresenter.replaceView(deliverTaskEditActivity);
        IdleMovePresenter idleMovePresenter2 = getIdleMovePresenter();
        if (idleMovePresenter2 == null) {
            Intrinsics.throwNpe();
        }
        idleMovePresenter2.actionIDLE();
        IdleMovePresenter idleMovePresenter3 = getIdleMovePresenter();
        if (idleMovePresenter3 == null) {
            Intrinsics.throwNpe();
        }
        idleMovePresenter3.actionTimerCount(true);
        getScheduleFillInPresenter().replaceView(deliverTaskEditActivity);
        getInformationSystemPresenter().replaceView(deliverTaskEditActivity);
        getDispatchKeyPresenter().replaceView(deliverTaskEditActivity);
        BeeperCallHelper.bind$default(this.beeperCallHelper, this, false, false, 6, null);
        DeliveryTaskSettingPresenter deliveryTaskSettingPresenter = getDeliveryTaskSettingPresenter();
        if (deliveryTaskSettingPresenter != null) {
            deliveryTaskSettingPresenter.replaceView(deliverTaskEditActivity);
        }
        if (!isModifyMode()) {
            this.leaseHelper.setOnLeaseExpireForceCloseCallback(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$bindPresenter$1
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
        Peripherals.INSTANCE.getFunctionButton().addListener(this.functionButton);
        TouchSensorEventHelper touchSensorEventHelper = this.touchSensorEventHelper;
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        touchSensorEventHelper.bindPresenter(faceVideoView);
        this.touchSensorEventHelper.setOnAnimationShowListener(this.onTouchSensorAnimationListener);
        this.touchSensorEventHelper.setOnTouchSensorPlaceListener(this.onTouchSensorPlaceListener);
        AiVoiceTriggerHelper.INSTANCE.setOnTaskInputListener(this.onTaskInputListener);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionStartListener(this.onMissionStartListener);
        showPowerEvent(BatteryInfoManager.INSTANCE.getPowerEvent());
        showChargerEvent(BatteryInfoManager.INSTANCE.getChargerEvent());
        showPowerChange(BatteryInfoManager.INSTANCE.getPower());
        if (BatteryInfoManager.INSTANCE.getNeedShowLowPowerNotify()) {
            showLowerNotify();
        }
        if (isFirstBoot) {
            getHealthCheckHelper().startCheckOnce();
            isFirstBoot = false;
        }
        CustomCallHelper.INSTANCE.addCallListener(this.onCustomCallListener);
    }

    private final void unBindPresenter() {
        Pdlog.m3273d(this.TAG, "unBindPresenter");
        DeliverTaskEditActivity deliverTaskEditActivity = this;
        getScheduleFillInPresenter().removeView(deliverTaskEditActivity);
        Peripherals.INSTANCE.getFunctionButton().removeListener(this.functionButton);
        BatteryInfoManager.INSTANCE.removeBatteryChangeNotifyListeners(this);
        IdleMovePresenter idleMovePresenter = getIdleMovePresenter();
        if (idleMovePresenter == null) {
            Intrinsics.throwNpe();
        }
        idleMovePresenter.actionTimerCount(false);
        IdleMovePresenter idleMovePresenter2 = getIdleMovePresenter();
        if (idleMovePresenter2 == null) {
            Intrinsics.throwNpe();
        }
        idleMovePresenter2.removeView(deliverTaskEditActivity);
        getInformationSystemPresenter().removeView(deliverTaskEditActivity);
        this.touchSensorEventHelper.unBindPresent();
        this.beeperCallHelper.unbind();
        this.leaseHelper.unbind();
        getDispatchKeyPresenter().removeView(deliverTaskEditActivity);
        DeliveryTaskSettingPresenter deliveryTaskSettingPresenter = getDeliveryTaskSettingPresenter();
        if (deliveryTaskSettingPresenter != null) {
            deliveryTaskSettingPresenter.removeView(deliverTaskEditActivity);
        }
        AiVoiceTriggerHelper.INSTANCE.setOnTaskInputListener((Function2) null);
        AiVoiceTriggerHelper.INSTANCE.setOnMissionStartListener((Function0) null);
        stopStandby();
        getHealthCheckHelper().unBindPresenter();
        CustomCallHelper.INSTANCE.removeCallListener(this.onCustomCallListener);
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, com.pudutech.bumblebee.robot_ui.p054ui.FinishInter
    public void jumpAndFinish(Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        jump(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void jump(Intent intent) {
        Pdlog.m3273d(this.TAG, "jump");
        release();
        startActivity(intent);
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
        this.isplayEntryVoice = false;
        View turn_back_fill_in_layout = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
        turn_back_fill_in_layout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void release() {
        this.isRelease = true;
        AiVoiceManager.INSTANCE.removeWakeupListener(this.wakeupListener);
        AiVoiceManager.INSTANCE.stopAiRecording();
        unBindPresenter();
        stopDelayVoice();
        VoicePlayer.INSTANCE.stop();
        VoiceModeDialog voiceModeDialog = this.selectVoiceDialog;
        if (voiceModeDialog != null && voiceModeDialog.isShowing()) {
            voiceModeDialog.dismiss();
        }
        if (this.selectVoiceDialog != null) {
            this.selectVoiceDialog = (VoiceModeDialog) null;
        }
        PopupWindow popupWindow = this.popupWindowPalletTts;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    private final void showSleepAnimation() {
        Pdlog.m3273d(this.TAG, "showSleepAnimation");
        FaceVideoView faceVideoView = this.faceAnimationView;
        if (faceVideoView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("faceAnimationView");
        }
        faceVideoView.playAnimation(SceneAnimationResources.INSTANCE.getSleep());
        PeripheralsSceneUtil.INSTANCE.sleep();
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
        PeripheralsSceneUtil.INSTANCE.standby();
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
        PeripheralsSceneUtil.INSTANCE.idleState(isBirthdayMode());
        onFeatureChange(this.TYPE_FEATURE_NOMAL);
        resetScreenLight();
    }

    private final void initTablesInput() {
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment != null) {
            iSelectTableFragment.setOnSelectTable(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initTablesInput$1
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
                    String str2;
                    boolean z;
                    int i;
                    int i2;
                    int i3;
                    int i4;
                    int i5;
                    int i6;
                    VoiceTask voiceTask;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (!((RobotDeliverTaskLayout) DeliverTaskEditActivity.this._$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getIsAnimating()) {
                        str2 = DeliverTaskEditActivity.this.TAG;
                        Pdlog.m3273d(str2, "initTablesInput " + it);
                        z = DeliverTaskEditActivity.this.isPlayInputOneTableVoice;
                        if (!z) {
                            DeliverTaskEditActivity.this.isPlayInputOneTableVoice = true;
                            i = DeliverTaskEditActivity.this.currentModeType;
                            if (i != 3) {
                                i2 = DeliverTaskEditActivity.this.currentModeType;
                                if (i2 != 2) {
                                    i3 = DeliverTaskEditActivity.this.currentModeType;
                                    if (i3 != 5) {
                                        i4 = DeliverTaskEditActivity.this.currentModeType;
                                        if (i4 != 4) {
                                            i5 = DeliverTaskEditActivity.this.currentModeType;
                                            if (i5 != 7) {
                                                i6 = DeliverTaskEditActivity.this.currentModeType;
                                                if (i6 != 6) {
                                                    VoicePlayer voicePlayer = VoicePlayer.INSTANCE;
                                                    voiceTask = DeliverTaskEditActivity.this.inputOneTableVoice;
                                                    voicePlayer.play(voiceTask.withListener(new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$initTablesInput$1.1
                                                        @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
                                                        public void onStateChange(PlayEvent event) {
                                                            Intrinsics.checkParameterIsNotNull(event, "event");
                                                            if (event == PlayEvent.COMPLETION_ONCE) {
                                                                DeliverTaskEditActivity.this.isPlayInputOneTableVoiceFinish = true;
                                                                DeliverTaskEditActivity.this.startDelayVoice();
                                                            }
                                                            if (event == PlayEvent.STOP) {
                                                                DeliverTaskEditActivity.this.isPlayInputOneTableVoiceFinish = true;
                                                            }
                                                        }
                                                    }));
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            DeliverTaskEditActivity.this.isPlayInputOneTableVoiceFinish = true;
                        }
                        DeliverTaskEditActivity.this.addSelectTask(it);
                        return;
                    }
                    str = DeliverTaskEditActivity.this.TAG;
                    Pdlog.m3273d(str, "initTablesInput 动画未结束");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addSelectTask(String task) {
        int i = this.currentModeType;
        if (i == 6 || i == 7) {
            ((RecyclePlateTaskLayout) _$_findCachedViewById(C4188R.id.recycle_plate_task_layout)).addTask(task);
            return;
        }
        RobotDeliverTaskLayout robotDeliverTaskLayout = (RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout);
        int i2 = this.currentModeType;
        robotDeliverTaskLayout.setTaskName(task, (i2 == 0 || i2 == 1 || i2 == 9 || i2 == 8) ? Constans.INSTANCE.getSingleTrayMultiTableSwitch() : false);
    }

    private final void playPowerLowVoice(VoiceTask voiceTask) {
        if (this.isRelease) {
            return;
        }
        this.isPlayLowPowerVoice = true;
        VoicePlayer.INSTANCE.play(voiceTask);
    }

    public final boolean getIsplayEntryVoice() {
        return this.isplayEntryVoice;
    }

    public final void setIsplayEntryVoice(boolean z) {
        this.isplayEntryVoice = z;
    }

    private final void playEntryVoiceIfNeed() {
        Pdlog.m3273d(this.TAG, "playEntryVoiceIfNeed");
        if (Constans.INSTANCE.isLockedMachine()) {
            Pdlog.m3273d(this.TAG, "playEntryVoiceIfNeed() locked haved");
            return;
        }
        if (this.isplayEntryVoice) {
            Pdlog.m3273d(this.TAG, "playEntryVoiceIfNeed() isplayEntryVoice =" + this.isplayEntryVoice);
            return;
        }
        if (this.isPlayLowPowerVoice) {
            return;
        }
        View turn_back_fill_in_layout = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
        Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
        if (turn_back_fill_in_layout.getVisibility() == 0 || !this.fillInStatusInit || this.isFirstStart) {
            return;
        }
        this.isplayEntryVoice = true;
        Listener listener = new Listener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$playEntryVoiceIfNeed$onStateChange$1
            @Override // com.pudutech.bumblebee.presenter.robot_voices.Listener
            public void onStateChange(PlayEvent event) {
                Intrinsics.checkParameterIsNotNull(event, "event");
                DeliverTaskEditActivity.this.setIsplayEntryVoice(false);
            }
        };
        int i = this.currentModeType;
        if (i == 6 || i == 7) {
            this.recyclePlateEnterIntoVoice.withListener(listener);
            VoicePlayer.INSTANCE.play(this.recyclePlateEnterIntoVoice);
        } else {
            this.enterIntoVoice.withListener(listener);
            VoicePlayer.INSTANCE.play(this.enterIntoVoice);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showChargerEvent(BatteryContract2.ChargerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showChargerEvent " + model);
        if (model.getEvent() != BatteryContract2.ViewEvent.CHARGER_DISCONNECT) {
            PlayerModuleActivityManager.getInstance().finishAllModulesActivities();
            jump(new Intent(this, (Class<?>) RobotChargingActivity.class));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showLowerNotify() {
        Pdlog.m3273d(this.TAG, "showLowerNotify ");
        if (this.isShowLowPowerDialog) {
            return;
        }
        BatteryInfoManager.INSTANCE.clearFirstTimeWarning();
        Pdlog.m3273d(this.TAG, "showBatteryEvent ShowLowPowerDialog");
        ShowTipMsgDialog showTipMsgDialog = this.lowerPowerDialog;
        if (showTipMsgDialog != null && showTipMsgDialog.isShowing()) {
            this.isShowLowPowerDialog = true;
            return;
        }
        this.isShowLowPowerDialog = true;
        String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr2_17);
        Intrinsics.checkExpressionValueIsNotNull(string, "RobotContext.context.getString(R.string.pdStr2_17)");
        this.lowerPowerDialog = showDialog$default(this, string, false, 2, null);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerChange(int i) {
        ((MyStatusBarLayout) _$_findCachedViewById(C4188R.id.layout_my_status_bar)).setBattery(i);
    }

    @Override // com.pudutech.bumblebee.presenter.battery_task.BatteryContract2.ViewInterface
    public void showPowerEvent(BatteryContract2.PowerModel model) {
        Intrinsics.checkParameterIsNotNull(model, "model");
        Pdlog.m3273d(this.TAG, "showPowerEvent " + model);
        PeripheralsSceneUtil.INSTANCE.idleState(isBirthdayMode());
        int i = WhenMappings.$EnumSwitchMapping$0[model.getEvent().ordinal()];
        if (i == 1) {
            playPowerLowVoice(new VoiceTask(-1L, VoiceItem.voice14_2));
        } else if (i == 2) {
            playPowerLowVoice(new VoiceTask(-1L, VoiceItem.voice14_3));
        } else {
            if (i != 3) {
                return;
            }
            playPowerLowVoice(new VoiceTask(-1L, VoiceItem.voice14_1));
        }
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.ViewInterface
    public void showIdleEvent(IdleMoveContract.ViewEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3273d(this.TAG, "showIdleEvent " + event);
        int i = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i == 1) {
            stopStandby();
        } else if (i == 2) {
            showStandbyAnimation();
        } else if (i == 3) {
            showSleepAnimation();
        }
        PopupWindow popupWindow = this.popupWindowPalletTts;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x002f, code lost:
    
        if (r3.getVisibility() == 0) goto L16;
     */
    @Override // com.pudutech.bumblebee.presenter.schedule_task.ScheduleFillInContract.ViewInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onFillIn(String destination, boolean fillIn) {
        if (this.isRelease) {
            Pdlog.m3273d(this.TAG, "onFillIn isRelease");
            return;
        }
        if (fillIn) {
            if (this.fillInStatusInit || !isCanFillIn || this.currentModeType != 0) {
                View turn_back_fill_in_layout = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
                Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout, "turn_back_fill_in_layout");
            }
            if (destination != null) {
                Pdlog.m3273d(this.TAG, "onFillIn : destination = " + destination + "; fillIn = " + fillIn + "; ");
                GoHomePresenter.INSTANCE.setMIsFillIn(fillIn);
                Intent putExtra = new Intent(this, (Class<?>) TurnBackActivity.class).putExtra(TurnBackActivity.KEY_MODE, this.currentModeType).putExtra(TurnBackActivity.CUSTOM_OUTLET, getIntent().getStringExtra(TurnBackActivity.CUSTOM_OUTLET));
                Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(\n                …                        )");
                Intent intent = getIntent();
                Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
                jump(IntentExtKt.saveSceneId(putExtra, IntentExtKt.getSceneId$default(intent, null, 1, null)));
                return;
            }
            View turn_back_fill_in_layout2 = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
            Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout2, "turn_back_fill_in_layout");
            if (turn_back_fill_in_layout2.getVisibility() != 0) {
                View turn_back_fill_in_layout3 = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
                Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout3, "turn_back_fill_in_layout");
                turn_back_fill_in_layout3.setVisibility(0);
            }
        } else {
            View turn_back_fill_in_layout4 = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
            Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout4, "turn_back_fill_in_layout");
            if (turn_back_fill_in_layout4.getVisibility() == 0) {
                View turn_back_fill_in_layout5 = _$_findCachedViewById(C4188R.id.turn_back_fill_in_layout);
                Intrinsics.checkExpressionValueIsNotNull(turn_back_fill_in_layout5, "turn_back_fill_in_layout");
                turn_back_fill_in_layout5.setVisibility(8);
            }
        }
        if (this.fillInStatusInit) {
            return;
        }
        Pdlog.m3273d(this.TAG, "onFillIn " + destination + " -- " + fillIn);
        this.fillInStatusInit = true;
        playEntryVoiceIfNeed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showRgbdTip() {
        final RgbdOilTipDialog rgbdOilTipDialog = new RgbdOilTipDialog(this);
        rgbdOilTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showRgbdTip$1
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
                boolean z;
                int i;
                rgbdOilTipDialog.dismiss();
                z = DeliverTaskEditActivity.this.isRelease;
                if (z) {
                    return;
                }
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
                deliverTaskEditActivity.onFeatureChange(i);
            }
        });
        rgbdOilTipDialog.show();
        onFeatureChange(this.TYPE_FEATURE_DIALOG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showMaintenanceTip() {
        final MaintenanceTipDialog maintenanceTipDialog = new MaintenanceTipDialog(this);
        maintenanceTipDialog.setOnBtn1Click(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showMaintenanceTip$1
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
                HealthCheckHelper healthCheckHelper;
                boolean z;
                int i;
                healthCheckHelper = DeliverTaskEditActivity.this.getHealthCheckHelper();
                healthCheckHelper.clearHealthCheck();
                maintenanceTipDialog.dismiss();
                z = DeliverTaskEditActivity.this.isRelease;
                if (z) {
                    return;
                }
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
                deliverTaskEditActivity.onFeatureChange(i);
            }
        });
        maintenanceTipDialog.setOnNextBtnClick(new Function0<Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showMaintenanceTip$2
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
                boolean z;
                int i;
                maintenanceTipDialog.dismiss();
                z = DeliverTaskEditActivity.this.isRelease;
                if (z) {
                    return;
                }
                DeliverTaskEditActivity deliverTaskEditActivity = DeliverTaskEditActivity.this;
                i = deliverTaskEditActivity.TYPE_FEATURE_NOMAL;
                deliverTaskEditActivity.onFeatureChange(i);
            }
        });
        maintenanceTipDialog.show();
        onFeatureChange(this.TYPE_FEATURE_DIALOG);
    }

    @Override // com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract.ViewInterface
    public boolean onReceiveOrderInfo(ArrayList<InformationSystemContract.OrderInfo> info, InformationSystemContract.OrderInfoType type, int trayIndex) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Pdlog.m3273d(this.TAG, "onReceiveOrderInfo : info = " + info + "; type = " + type + "; ");
        int i = this.currentModeType;
        if (i == 4 || i == 5 || i == 6 || i == 7) {
            ToastUtils.show(this, getString(C4188R.string.pdStr2_35), new Object[0]);
            return false;
        }
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        if (Constans.INSTANCE.getSingleTrayMultiTableSwitch()) {
            Pdlog.m3273d(this.TAG, "getSingleTrayMultiTableSwitch true ");
            ToastUtils.show(this, getString(C4188R.string.pdStr2_34), new Object[0]);
            return false;
        }
        InformationSystemContract.OrderInfo orderInfo = info.get(0);
        Intrinsics.checkExpressionValueIsNotNull(orderInfo, "info[0]");
        InformationSystemContract.OrderInfo orderInfo2 = orderInfo;
        ISelectTableFragment iSelectTableFragment = this.selectTableFragment;
        if (iSelectTableFragment == null || !iSelectTableFragment.hasTable(orderInfo2.getSeatName())) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = getString(C4188R.string.pdStr2_36);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.pdStr2_36)");
            Object[] objArr = {orderInfo2.getSeatName()};
            String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            ToastUtils.show(this, format, new Object[0]);
            return false;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$2[type.ordinal()];
        if (i2 == 1) {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setTaskName(orderInfo2.getSeatName(), orderInfo2);
            return true;
        }
        if (i2 == 2) {
            if (trayIndex < 0 || trayIndex > ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getInputTaskCount()) {
                return false;
            }
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).setTrayOrders(orderInfo2.getSeatName(), info, trayIndex - 1);
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract.ViewInterface
    public boolean onReceiveDeliveryTask(SortType sortType, ArrayList<TrayModel> allTrays, boolean executeTask) {
        Intrinsics.checkParameterIsNotNull(sortType, "sortType");
        Intrinsics.checkParameterIsNotNull(allTrays, "allTrays");
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        Pdlog.m3273d(this.TAG, "onReceiveDeliveryTask : sortType = " + sortType + "; allTrays = " + allTrays + "; executeTask = " + executeTask + "; ");
        if (this.currentModeType == 0) {
            ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).initData(allTrays, null, sortType);
            if (executeTask) {
                startDeliverTask$default(this, "", false, 2, null);
            }
            return true;
        }
        Pdlog.m3274e(this.TAG, "onReceiveDeliveryTask : is not NEW_MODE ");
        return false;
    }

    @Override // com.pudutech.bumblebee.presenter.general_task.DispatchKeyContract.ViewInterface
    public boolean onDispatchKey(DispatchKeyContract.KEY key, String msg) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3273d(this.TAG, "onDispatchKey : key = " + key + "; msg = " + msg + "; ");
        if (key == DispatchKeyContract.KEY.QR_EMPLOYEES) {
            getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
            startDeliverTask(msg, true);
            return true;
        }
        if (key != DispatchKeyContract.KEY.REMOTE_START) {
            return false;
        }
        getGeneralPresenter().actionTouchScreen(getClass().getSimpleName());
        startDeliverTask(msg, true);
        return true;
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Pdlog.m3273d(this.TAG, "onResume");
        if (this.selectTableFragment instanceof SelectTableFragment) {
            initDragTips();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        Pdlog.m3273d(this.TAG, "onPause");
    }

    @Override // com.pudutech.bumblebee.robot_ui.p054ui.MyBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Pdlog.m3273d(this.TAG, "onStop");
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract.ViewInterface
    public void showTableLayout(boolean haveTableGroup) {
        if (Constans.INSTANCE.getTableInputType() == 1) {
            View view_ghost_1 = _$_findCachedViewById(C4188R.id.view_ghost_1);
            Intrinsics.checkExpressionValueIsNotNull(view_ghost_1, "view_ghost_1");
            setViewVisibility(view_ghost_1, false);
            View view_birth_ghost_1 = _$_findCachedViewById(C4188R.id.view_birth_ghost_1);
            Intrinsics.checkExpressionValueIsNotNull(view_birth_ghost_1, "view_birth_ghost_1");
            setViewVisibility(view_birth_ghost_1, false);
            return;
        }
        View view_ghost_12 = _$_findCachedViewById(C4188R.id.view_ghost_1);
        Intrinsics.checkExpressionValueIsNotNull(view_ghost_12, "view_ghost_1");
        setViewVisibility(view_ghost_12, !haveTableGroup);
        View view_birth_ghost_12 = _$_findCachedViewById(C4188R.id.view_birth_ghost_1);
        Intrinsics.checkExpressionValueIsNotNull(view_birth_ghost_12, "view_birth_ghost_1");
        setViewVisibility(view_birth_ghost_12, !haveTableGroup);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract.ViewInterface
    public void showMutliVoiceButton(boolean open) {
        TextView voice_tip_tv = (TextView) _$_findCachedViewById(C4188R.id.voice_tip_tv);
        Intrinsics.checkExpressionValueIsNotNull(voice_tip_tv, "voice_tip_tv");
        setViewVisibility(voice_tip_tv, open);
        Button voice_setting_btn = (Button) _$_findCachedViewById(C4188R.id.voice_setting_btn);
        Intrinsics.checkExpressionValueIsNotNull(voice_setting_btn, "voice_setting_btn");
        setViewVisibility(voice_setting_btn, open);
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract.ViewInterface
    public void updateMutliVoiceTip(String voiceName) {
        Intrinsics.checkParameterIsNotNull(voiceName, "voiceName");
        TextView textView = (TextView) _$_findCachedViewById(C4188R.id.voice_tip_tv);
        textView.setText(voiceName);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
    }

    @Override // com.pudutech.bumblebee.presenter.delivery_task.DeliveryTaskSettingContract.ViewInterface
    public void showPalletTtsVoiceButton() {
        Button pallet_tts_btn = (Button) _$_findCachedViewById(C4188R.id.pallet_tts_btn);
        Intrinsics.checkExpressionValueIsNotNull(pallet_tts_btn, "pallet_tts_btn");
        pallet_tts_btn.setVisibility(isPalletTtsEnable() ? 0 : 8);
        for (TrayModel trayModel : ((RobotDeliverTaskLayout) _$_findCachedViewById(C4188R.id.robot_deliver_task_layout)).getAllTask()) {
            if (this.currentModeType != 1) {
                trayModel.setPalletTtsScheme(null);
            }
        }
    }

    public final void showSelectVoiceDialog() {
        if (this.selectVoiceDialog == null) {
            this.selectVoiceDialog = new VoiceModeDialog(this);
            VoiceModeDialog voiceModeDialog = this.selectVoiceDialog;
            if (voiceModeDialog != null) {
                voiceModeDialog.setOnComplete(new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showSelectVoiceDialog$1
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
                        DeliveryTaskSettingPresenter deliveryTaskSettingPresenter;
                        deliveryTaskSettingPresenter = DeliverTaskEditActivity.this.getDeliveryTaskSettingPresenter();
                        if (deliveryTaskSettingPresenter != null) {
                            if (str == null) {
                                str = "";
                            }
                            deliveryTaskSettingPresenter.actionUpdateVoice(str);
                        }
                    }
                });
            }
            VoiceModeDialog voiceModeDialog2 = this.selectVoiceDialog;
            if (voiceModeDialog2 != null) {
                voiceModeDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.DeliverTaskEditActivity$showSelectVoiceDialog$2
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        BeeperCallHelper beeperCallHelper;
                        beeperCallHelper = DeliverTaskEditActivity.this.beeperCallHelper;
                        beeperCallHelper.receiverCallTask(false);
                    }
                });
            }
        }
        VoiceModeDialog voiceModeDialog3 = this.selectVoiceDialog;
        if (voiceModeDialog3 == null || voiceModeDialog3.isShowing()) {
            return;
        }
        this.beeperCallHelper.stopReceiverCallTask();
        voiceModeDialog3.show();
    }

    private final void setViewVisibility(View view, boolean show) {
        view.setVisibility(show ? 0 : 8);
    }

    public final void showPalletTtsWindow() {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new DeliverTaskEditActivity$showPalletTtsWindow$1(this, LayoutInflater.from(this).inflate(C4188R.layout.pallet_tts_setting_popupview, (ViewGroup) null, false), null), 3, null);
    }

    private final boolean isPalletTtsEnable() {
        return this.currentModeType == 0 && Constans.INSTANCE.isOpenPalletDeliverTtsFunction() && TtsVoiceHelper.INSTANCE.isOpen(this, TtsVoiceHelper.TtsVoiceType.PALLET_DELIVER_TYPE);
    }
}
