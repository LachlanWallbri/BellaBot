package com.pudutech.bumblebee.robot_ui.p054ui.helper;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.SpeechUtility;
import com.loc.C3898x;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.base.BaseApplication;
import com.pudutech.bumblebee.presenter.robot_voices_task.VoicePackageHelper;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.RobotContext;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.bumblebee.robot_ui.manager.SystemSoundManager;
import com.pudutech.bumblebee.robot_ui.manager.ThreadPoolManager;
import com.pudutech.bumblebee.robot_ui.p054ui.adapter.SelectSpeakerAdapter;
import com.pudutech.bumblebee.robot_ui.p054ui.helper.TtsVoiceHelper;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.IgnoreAnnotation;
import com.pudutech.disinfect.baselib.util.LanguageUtils;
import com.pudutech.lib_update.util.FileUtils;
import com.pudutech.tts_sdk.PdTtsSdk;
import com.pudutech.tts_sdk.TtsConfig;
import com.pudutech.tts_sdk.tts.OnTtsListener;
import com.pudutech.tts_sdk.utils.AudioTrackUtils;
import com.pudutech.tts_sdk.utils.CMediaPlayer;
import com.sunny.ylhk.lib_base_mvvm.util.TtsMarkUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TtsVoiceHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0015\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\bÆ\u0002\u0018\u00002\u00020\u0001:\n\u008d\u0001\u008e\u0001\u008f\u0001\u0090\u0001\u0091\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u00103\u001a\u00020)2\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%H\u0002JQ\u00107\u001a\u00020)2\u0006\u00106\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%2\b\b\u0002\u00108\u001a\u00020\u00172\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00042#\u0010:\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020)0$JY\u00107\u001a\u00020)2\u0006\u00106\u001a\u00020\u00042\u0006\u0010<\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%2\b\b\u0002\u00108\u001a\u00020\u00172\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00042#\u0010:\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020)0$JQ\u0010=\u001a\u00020)2\u0006\u00106\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%2\b\b\u0002\u00108\u001a\u00020\u00172\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00042#\u0010>\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010?¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020)0$J\u0016\u0010A\u001a\u00020)2\u0006\u0010B\u001a\u00020\f2\u0006\u0010(\u001a\u00020%J\b\u0010C\u001a\u00020)H\u0002J\u0010\u0010D\u001a\u00020)2\u0006\u0010(\u001a\u00020%H\u0002J\u0016\u0010E\u001a\u00020\u00172\u0006\u00106\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%J\u0016\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u0006\u0010(\u001a\u00020%J\u0016\u0010J\u001a\u00020)2\u0006\u0010K\u001a\u00020\f2\u0006\u0010(\u001a\u00020%J&\u0010L\u001a\u00020)2\u0006\u00106\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u00042\u0006\u0010N\u001a\u00020OJ\u0018\u0010P\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010Q\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010R\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u000e\u0010S\u001a\u00020\u00042\u0006\u0010H\u001a\u00020IJ\u0018\u0010T\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J \u0010U\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%H\u0002J\u0018\u0010V\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010W\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010X\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J \u0010Y\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010Z\u001a\u00020%H\u0002J\u0018\u0010[\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010\\\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010]\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0018\u0010^\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0010\u0010_\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%H\u0002J\u0006\u0010`\u001a\u00020\u0004J\u0018\u0010a\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u0006\u0010b\u001a\u00020\u0004J\u0016\u0010c\u001a\u0012\u0012\u0004\u0012\u00020d0\u000bj\b\u0012\u0004\u0012\u00020d`\rJ\u000e\u0010e\u001a\u00020f2\u0006\u0010(\u001a\u00020%J\u001e\u0010g\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010(\u001a\u00020%J\u0006\u0010h\u001a\u00020)J\u000e\u0010i\u001a\u00020)2\u0006\u0010H\u001a\u00020IJ\u0016\u0010j\u001a\u00020\u00172\u0006\u0010H\u001a\u00020I2\u0006\u0010(\u001a\u00020%J\u0018\u0010k\u001a\u00020)2\u0006\u0010l\u001a\u00020\u00172\b\u0010m\u001a\u0004\u0018\u00010nJ\b\u0010o\u001a\u00020)H\u0002J\b\u0010p\u001a\u00020)H\u0002J\u0010\u0010q\u001a\u00020)2\u0006\u0010(\u001a\u00020%H\u0002J5\u0010r\u001a\u00020)2\u0006\u0010K\u001a\u00020\f2%\b\u0002\u0010s\u001a\u001f\u0012\u0013\u0012\u00110t¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020)\u0018\u00010$J\u0006\u0010v\u001a\u00020)J5\u0010w\u001a\u00020)2\u0006\u0010(\u001a\u00020%2%\b\u0002\u0010s\u001a\u001f\u0012\u0013\u0012\u00110t¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(u\u0012\u0004\u0012\u00020)\u0018\u00010$JW\u0010x\u001a\u00020)2\u0016\u0010y\u001a\u0012\u0012\u0004\u0012\u00020z0\u000bj\b\u0012\u0004\u0012\u00020z`\r2\u0006\u0010{\u001a\u00020f2\u0006\u00109\u001a\u00020\u00042'\b\u0002\u0010>\u001a!\u0012\u0015\u0012\u0013\u0018\u00010?¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020)\u0018\u00010$J7\u0010|\u001a\u00020)2\u0006\u00109\u001a\u00020\u00042'\b\u0002\u0010>\u001a!\u0012\u0015\u0012\u0013\u0018\u00010?¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b(@\u0012\u0004\u0012\u00020)\u0018\u00010$J\u0010\u0010}\u001a\u00020)2\u0006\u0010(\u001a\u00020%H\u0002J\u0016\u0010~\u001a\u00020)2\u0006\u00109\u001a\u00020\u00042\u0006\u0010\u007f\u001a\u00020\u001eJ\u0018\u0010\u0080\u0001\u001a\u00020)2\u0007\u0010\u0081\u0001\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%J\u0017\u0010\u0082\u0001\u001a\u00020)2\u0006\u00106\u001a\u00020\u00042\u0006\u0010N\u001a\u00020OJ \u0010\u0083\u0001\u001a\u00020)2\u0006\u0010H\u001a\u00020I2\u0007\u0010\u0084\u0001\u001a\u00020G2\u0006\u0010(\u001a\u00020%J\"\u0010\u0085\u0001\u001a\u00020)2\u0006\u0010(\u001a\u00020%2\u0006\u00106\u001a\u00020\u00042\u0007\u0010\u0086\u0001\u001a\u00020\fH\u0002J\u0019\u0010\u0087\u0001\u001a\u0012\u0012\u0004\u0012\u00020d0\u000bj\b\u0012\u0004\u0012\u00020d`\rH\u0002J\u0007\u0010\u0088\u0001\u001a\u00020)J \u0010\u0089\u0001\u001a\u00020)2\u0006\u0010K\u001a\u00020\f2\u0007\u0010\u008a\u0001\u001a\u00020\u00042\u0006\u0010(\u001a\u00020%J\u0010\u0010\u008b\u0001\u001a\u00020)2\u0007\u0010\u008c\u0001\u001a\u00020fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R7\u0010#\u001a\u001f\u0012\u0013\u0012\u00110%¢\u0006\f\b&\u0012\b\b'\u0012\u0004\b\b((\u0012\u0004\u0012\u00020)\u0018\u00010$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001e\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00100\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u00102\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0092\u0001"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper;", "", "()V", "DEF_SPEEK", "", "getDEF_SPEEK", "()Ljava/lang/String;", "FLIE_MARK", "TAG", "kotlin.jvm.PlatformType", "birthdayList", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "Lkotlin/collections/ArrayList;", "cruiseConfigDateList", "cruiseStayDateList", "deliverConfigDateList", "greeterConfigDateList", "greeterGuideConfigDateList", "gson", "Lcom/google/gson/Gson;", "guideArrivalConfigDataList", "isInitSuccess", "", "()Z", "setInitSuccess", "(Z)V", "isIniting", "setIniting", "mSetSpeakerListern", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$SetSpeakerListern;", "getMSetSpeakerListern", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$SetSpeakerListern;", "setMSetSpeakerListern", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$SetSpeakerListern;)V", "onChangeListener", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "Lkotlin/ParameterName;", "name", "ttsVoiceType", "", "getOnChangeListener", "()Lkotlin/jvm/functions/Function1;", "setOnChangeListener", "(Lkotlin/jvm/functions/Function1;)V", "palletDeliverConfigDateList", "recyclePointArriveList", "recycleTableArriveList", "recycleTableLeaveList", "specialModeArriveDateList", "addConfigItem", "filePath", "it", "text", "addFlTtsVoice", "notNeedCache", "speaker", "callBack", SpeechUtility.TAG_RESOURCE_RESULT, "md5", "addNewTtsVoice", "callback", "", C3898x.f4338g, "changeChoose", "item", "changeOldSpeaker", "checkSelect", "checkTtsExist", "checkTtsOpenType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceOpenType;", "context", "Landroid/content/Context;", "deleteConfig", "configData", "genTtsVoice", "path", "onTtsListener", "Lcom/pudutech/tts_sdk/tts/OnTtsListener;", "getBirthdayFilePath", "getCruiseFilePath", "getCruiseStayFilePath", "getCruiseTempFilePath", "getDeliverFilePath", "getFilePath", "getGreeterFilePath", "getGreeterGuideFilePath", "getGuideArrivalFilePath", "getListByType", "type", "getPalletDeliverFilePath", "getRecyclePointArriveFilePath", "getRecycleTableArriveFilePath", "getRecycleTableLeaveFilePath", "getSaveSpKey", "getSelectedSpeaker", "getSpcialModeArrivePath", "getSpeakerKey", "getSpeakers", "Lcom/pudutech/bumblebee/robot_ui/ui/adapter/SelectSpeakerAdapter$SpeakerData;", "getTimeInterval", "", "getTtsConfigList", "init", "initPdTtsSdk", "isOpen", "loadAllLoacalConfig", "isResetData", "locale", "Ljava/util/Locale;", "loadFlLocalConfig", "loadZhLocalConfig", "notifyChange", "playPcm", "listener", "Lcom/pudutech/tts_sdk/utils/AudioTrackUtils$AudioPlayEvent;", "event", "playTempCruiseTts", "playTtsVoice", "resetVoiceDate", TmpConstant.TYPE_VALUE_ARRAY, "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceDate;", "i", "resetVoiceDateList", "saveConfig", "setSpeaker", "setSpeakerListern", "setTimeInterval", "interval", "setTtsTempPlay", "setTtsType", "ttsVoiceOpenType", "setVoiceList", "ttsConfigData", "speakerList", "stopCruiseTts", "updateConfig", "newContent", "updateVolume", "volume", "SetSpeakerListern", "TtsConfigData", "TtsVoiceDate", "TtsVoiceOpenType", "TtsVoiceType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class TtsVoiceHelper {
    private static final String DEF_SPEEK;
    public static final String FLIE_MARK = ".pcm";
    public static final TtsVoiceHelper INSTANCE;
    private static final String TAG;
    private static final ArrayList<TtsConfigData> birthdayList;
    private static final ArrayList<TtsConfigData> cruiseConfigDateList;
    private static final ArrayList<TtsConfigData> cruiseStayDateList;
    private static final ArrayList<TtsConfigData> deliverConfigDateList;
    private static final ArrayList<TtsConfigData> greeterConfigDateList;
    private static final ArrayList<TtsConfigData> greeterGuideConfigDateList;
    private static final Gson gson;
    private static final ArrayList<TtsConfigData> guideArrivalConfigDataList;
    private static boolean isInitSuccess;
    private static boolean isIniting;
    private static SetSpeakerListern mSetSpeakerListern;
    private static Function1<? super TtsVoiceType, Unit> onChangeListener;
    private static final ArrayList<TtsConfigData> palletDeliverConfigDateList;
    private static final ArrayList<TtsConfigData> recyclePointArriveList;
    private static final ArrayList<TtsConfigData> recycleTableArriveList;
    private static final ArrayList<TtsConfigData> recycleTableLeaveList;
    private static final ArrayList<TtsConfigData> specialModeArriveDateList;

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$SetSpeakerListern;", "", "doing", "", "error", "success", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public interface SetSpeakerListern {
        void doing();

        void error();

        void success();
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "", "(Ljava/lang/String;I)V", "CRUISE_TYPE", "DELIVER_TYPE", "GREETER_TYPE", "GREETER_GUIDE_TYPE", "GUIDE_ARRIVAL_TYPE", "SPECIAL_MODE_ARRIVE", "CRUISE_STAY_TYPE", "BIRTHDAY_TYPE", "PALLET_DELIVER_TYPE", "RECYCLE_TABLE_ARRIVE", "RECYCLE_TABLE_LEAVE", "RECYCLE_POINT_ARRIVE", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum TtsVoiceType {
        CRUISE_TYPE,
        DELIVER_TYPE,
        GREETER_TYPE,
        GREETER_GUIDE_TYPE,
        GUIDE_ARRIVAL_TYPE,
        SPECIAL_MODE_ARRIVE,
        CRUISE_STAY_TYPE,
        BIRTHDAY_TYPE,
        PALLET_DELIVER_TYPE,
        RECYCLE_TABLE_ARRIVE,
        RECYCLE_TABLE_LEAVE,
        RECYCLE_POINT_ARRIVE
    }

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;

        static {
            $EnumSwitchMapping$0[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$0[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$0[TtsVoiceType.GREETER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$0[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$0[TtsVoiceType.GUIDE_ARRIVAL_TYPE.ordinal()] = 5;
            $EnumSwitchMapping$0[TtsVoiceType.SPECIAL_MODE_ARRIVE.ordinal()] = 6;
            $EnumSwitchMapping$0[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 7;
            $EnumSwitchMapping$0[TtsVoiceType.BIRTHDAY_TYPE.ordinal()] = 8;
            $EnumSwitchMapping$0[TtsVoiceType.PALLET_DELIVER_TYPE.ordinal()] = 9;
            $EnumSwitchMapping$0[TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 10;
            $EnumSwitchMapping$0[TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 11;
            $EnumSwitchMapping$0[TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 12;
            $EnumSwitchMapping$1 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$1[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$1[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$1[TtsVoiceType.GREETER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$1[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$1[TtsVoiceType.GUIDE_ARRIVAL_TYPE.ordinal()] = 5;
            $EnumSwitchMapping$1[TtsVoiceType.SPECIAL_MODE_ARRIVE.ordinal()] = 6;
            $EnumSwitchMapping$1[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 7;
            $EnumSwitchMapping$1[TtsVoiceType.BIRTHDAY_TYPE.ordinal()] = 8;
            $EnumSwitchMapping$1[TtsVoiceType.PALLET_DELIVER_TYPE.ordinal()] = 9;
            $EnumSwitchMapping$1[TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 10;
            $EnumSwitchMapping$1[TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 11;
            $EnumSwitchMapping$1[TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 12;
            $EnumSwitchMapping$2 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$2[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$2[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$2[TtsVoiceType.GREETER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$2[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$2[TtsVoiceType.GUIDE_ARRIVAL_TYPE.ordinal()] = 5;
            $EnumSwitchMapping$2[TtsVoiceType.SPECIAL_MODE_ARRIVE.ordinal()] = 6;
            $EnumSwitchMapping$2[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 7;
            $EnumSwitchMapping$2[TtsVoiceType.BIRTHDAY_TYPE.ordinal()] = 8;
            $EnumSwitchMapping$2[TtsVoiceType.PALLET_DELIVER_TYPE.ordinal()] = 9;
            $EnumSwitchMapping$2[TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 10;
            $EnumSwitchMapping$2[TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 11;
            $EnumSwitchMapping$2[TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 12;
            $EnumSwitchMapping$3 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$3[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$3[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$3[TtsVoiceType.GREETER_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$3[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$3[TtsVoiceType.GUIDE_ARRIVAL_TYPE.ordinal()] = 5;
            $EnumSwitchMapping$3[TtsVoiceType.SPECIAL_MODE_ARRIVE.ordinal()] = 6;
            $EnumSwitchMapping$3[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 7;
            $EnumSwitchMapping$3[TtsVoiceType.BIRTHDAY_TYPE.ordinal()] = 8;
            $EnumSwitchMapping$3[TtsVoiceType.PALLET_DELIVER_TYPE.ordinal()] = 9;
            $EnumSwitchMapping$3[TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 10;
            $EnumSwitchMapping$3[TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 11;
            $EnumSwitchMapping$3[TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 12;
            $EnumSwitchMapping$4 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$4[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$4[TtsVoiceType.GREETER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$4[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$4[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$5 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$5[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$5[TtsVoiceType.GREETER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$5[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$5[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$6 = new int[TtsVoiceType.values().length];
            $EnumSwitchMapping$6[TtsVoiceType.CRUISE_TYPE.ordinal()] = 1;
            $EnumSwitchMapping$6[TtsVoiceType.DELIVER_TYPE.ordinal()] = 2;
            $EnumSwitchMapping$6[TtsVoiceType.BIRTHDAY_TYPE.ordinal()] = 3;
            $EnumSwitchMapping$6[TtsVoiceType.GREETER_TYPE.ordinal()] = 4;
            $EnumSwitchMapping$6[TtsVoiceType.GREETER_GUIDE_TYPE.ordinal()] = 5;
            $EnumSwitchMapping$6[TtsVoiceType.GUIDE_ARRIVAL_TYPE.ordinal()] = 6;
            $EnumSwitchMapping$6[TtsVoiceType.SPECIAL_MODE_ARRIVE.ordinal()] = 7;
            $EnumSwitchMapping$6[TtsVoiceType.CRUISE_STAY_TYPE.ordinal()] = 8;
            $EnumSwitchMapping$6[TtsVoiceType.PALLET_DELIVER_TYPE.ordinal()] = 9;
            $EnumSwitchMapping$6[TtsVoiceType.RECYCLE_TABLE_ARRIVE.ordinal()] = 10;
            $EnumSwitchMapping$6[TtsVoiceType.RECYCLE_TABLE_LEAVE.ordinal()] = 11;
            $EnumSwitchMapping$6[TtsVoiceType.RECYCLE_POINT_ARRIVE.ordinal()] = 12;
        }
    }

    static {
        TtsVoiceHelper ttsVoiceHelper = new TtsVoiceHelper();
        INSTANCE = ttsVoiceHelper;
        TAG = ttsVoiceHelper.getClass().getSimpleName();
        cruiseConfigDateList = new ArrayList<>();
        deliverConfigDateList = new ArrayList<>();
        greeterConfigDateList = new ArrayList<>();
        greeterGuideConfigDateList = new ArrayList<>();
        guideArrivalConfigDataList = new ArrayList<>();
        specialModeArriveDateList = new ArrayList<>();
        cruiseStayDateList = new ArrayList<>();
        birthdayList = new ArrayList<>();
        palletDeliverConfigDateList = new ArrayList<>();
        recycleTableArriveList = new ArrayList<>();
        recycleTableLeaveList = new ArrayList<>();
        recyclePointArriveList = new ArrayList<>();
        gson = GsonSingleton.INSTANCE.getINSTANCE().getMGson();
        DEF_SPEEK = "x_chongchong";
    }

    private TtsVoiceHelper() {
    }

    public final boolean isIniting() {
        return isIniting;
    }

    public final void setIniting(boolean z) {
        isIniting = z;
    }

    public final boolean isInitSuccess() {
        return isInitSuccess;
    }

    public final void setInitSuccess(boolean z) {
        isInitSuccess = z;
    }

    public final Function1<TtsVoiceType, Unit> getOnChangeListener() {
        return onChangeListener;
    }

    public final void setOnChangeListener(Function1<? super TtsVoiceType, Unit> function1) {
        onChangeListener = function1;
    }

    public final String getDEF_SPEEK() {
        return DEF_SPEEK;
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceOpenType;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "CLOSE", "OPEN", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public enum TtsVoiceOpenType {
        CLOSE(0),
        OPEN(1);

        private final int type;

        TtsVoiceOpenType(int i) {
            this.type = i;
        }

        public final int getType() {
            return this.type;
        }
    }

    public final void initPdTtsSdk(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (isIniting || isInitSuccess) {
            Pdlog.m3274e(TAG, "init is initing");
            return;
        }
        isIniting = true;
        String str = SpUtils.get(BaseApplication.INSTANCE.getInstance(), getSpeakerKey(), DEF_SPEEK);
        int ttsSoundVoice = SystemSoundManager.INSTANCE.getTtsSoundHelper().getTtsSoundVoice();
        TtsConfig.INSTANCE.setIFLY_APPID("5d9c055f");
        TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(str);
        Pdlog.m3273d(TAG, "speaker：" + str);
        TtsConfig.INSTANCE.setIFLY_SPEECH_SPEED(50);
        updateVolume(ttsSoundVoice);
        PdTtsSdk.INSTANCE.init(context, new Function1<Integer, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$initPdTtsSdk$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                String str2;
                TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                str2 = TtsVoiceHelper.TAG;
                Pdlog.m3273d(str2, "PdTtsSdk init , it = " + i);
                if (i == 0) {
                    TtsVoiceHelper.INSTANCE.setInitSuccess(true);
                }
                TtsVoiceHelper.INSTANCE.setIniting(false);
            }
        });
    }

    public final void init() {
        changeOldSpeaker();
        loadAllLoacalConfig(false, null);
        initPdTtsSdk(BaseApplication.INSTANCE.getInstance());
    }

    public final String getSpeakerKey() {
        if (LanguageUtils.INSTANCE.isZh()) {
            return Constans.KEY_AIUI_SPEAKER_SET;
        }
        if (LanguageUtils.INSTANCE.isZhTw()) {
            return Constans.KEY_AIUI_TW_SPEAKER_SET;
        }
        return "key_tw_aiui_speaker_set_" + LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null);
    }

    private final void changeOldSpeaker() {
        String str = SpUtils.get(BaseApplication.INSTANCE.getInstance(), getSpeakerKey(), DEF_SPEEK);
        if (StringsKt.contains$default((CharSequence) "xiaoyan-aisxping-aisjinger-aisbabyxu", (CharSequence) str, false, 2, (Object) null)) {
            SpUtils.set(BaseApplication.INSTANCE.getInstance(), getSpeakerKey(), DEF_SPEEK);
            TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(DEF_SPEEK);
            Pdlog.m3273d(TAG, "changeOldSpeaker()---:" + str);
        }
    }

    public final synchronized void loadAllLoacalConfig(boolean isResetData, Locale locale) {
        boolean isZh;
        if (isResetData) {
            try {
                for (TtsVoiceType ttsVoiceType : TtsVoiceType.values()) {
                    INSTANCE.getListByType(ttsVoiceType).clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (locale == null) {
            isZh = LanguageUtils.INSTANCE.isZh();
        } else {
            isZh = LanguageUtils.INSTANCE.isZh(locale);
        }
        if (isZh) {
            loadZhLocalConfig();
        } else {
            loadFlLocalConfig();
        }
    }

    private final ArrayList<SelectSpeakerAdapter.SpeakerData> speakerList() {
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            ArrayList<SelectSpeakerAdapter.SpeakerData> arrayList = new ArrayList<>();
            String str = DEF_SPEEK;
            String string = RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_198);
            Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(R.string.pdStr7_198)");
            arrayList.add(new SelectSpeakerAdapter.SpeakerData(str, string, false, false));
            arrayList.add(new SelectSpeakerAdapter.SpeakerData("x2_yezi", RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_195) + "1", false, false));
            arrayList.add(new SelectSpeakerAdapter.SpeakerData("x2_ningning", RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_195) + "2", false, false));
            arrayList.add(new SelectSpeakerAdapter.SpeakerData("x2_xiaoyuan", RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_195) + "3", false, false));
            arrayList.add(new SelectSpeakerAdapter.SpeakerData("x2_xiaoyue", RobotContext.INSTANCE.getContext().getString(C4188R.string.pdStr7_195) + TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD, false, false));
            return arrayList;
        }
        return TtsVoiceFlHelper.INSTANCE.getSpeakerList();
    }

    public final ArrayList<SelectSpeakerAdapter.SpeakerData> getSpeakers() {
        String selectedSpeaker = getSelectedSpeaker();
        ArrayList<SelectSpeakerAdapter.SpeakerData> speakerList = speakerList();
        for (SelectSpeakerAdapter.SpeakerData speakerData : speakerList) {
            if (Intrinsics.areEqual(speakerData.getSpeaker(), selectedSpeaker)) {
                speakerData.setSelect(true);
            }
        }
        return speakerList;
    }

    private final void loadFlLocalConfig() {
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new TtsVoiceHelper$loadFlLocalConfig$1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ArrayList<TtsConfigData> getListByType(TtsVoiceType type) {
        switch (type) {
            case CRUISE_TYPE:
                return cruiseConfigDateList;
            case DELIVER_TYPE:
                return deliverConfigDateList;
            case GREETER_TYPE:
                return greeterConfigDateList;
            case GREETER_GUIDE_TYPE:
                return greeterGuideConfigDateList;
            case GUIDE_ARRIVAL_TYPE:
                return guideArrivalConfigDataList;
            case SPECIAL_MODE_ARRIVE:
                return specialModeArriveDateList;
            case CRUISE_STAY_TYPE:
                return cruiseStayDateList;
            case BIRTHDAY_TYPE:
                return birthdayList;
            case PALLET_DELIVER_TYPE:
                return palletDeliverConfigDateList;
            case RECYCLE_TABLE_ARRIVE:
                return recycleTableArriveList;
            case RECYCLE_TABLE_LEAVE:
                return recycleTableLeaveList;
            case RECYCLE_POINT_ARRIVE:
                return recyclePointArriveList;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void loadZhLocalConfig() {
        Function1<? super TtsVoiceType, Unit> function1;
        for (TtsVoiceType ttsVoiceType : TtsVoiceType.values()) {
            String str = SpUtils.get(RobotContext.INSTANCE.getContext(), INSTANCE.getSaveSpKey(ttsVoiceType), "");
            Pdlog.m3273d(TAG, "initLocalConfig " + ttsVoiceType.name() + ": " + str);
            ArrayList<TtsConfigData> listByType = INSTANCE.getListByType(ttsVoiceType);
            String str2 = str;
            if (!(str2 == null || StringsKt.isBlank(str2))) {
                try {
                    ArrayList<TtsConfigData> arrayList = (ArrayList) gson.fromJson(str, new TypeToken<ArrayList<TtsConfigData>>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$loadZhLocalConfig$1$arrayList$1
                    }.getType());
                    if (arrayList != null) {
                        for (TtsConfigData ttsConfigData : arrayList) {
                            if (new File(ttsConfigData.getPath()).exists()) {
                                listByType.add(ttsConfigData);
                            } else {
                                Pdlog.m3274e(TAG, "initLocalConfig " + ttsVoiceType.name() + " ,check file failed, " + ttsConfigData);
                            }
                        }
                    }
                    if (listByType.size() > 0 && (function1 = onChangeListener) != null) {
                        function1.invoke(ttsVoiceType);
                    }
                } catch (Exception e) {
                    Pdlog.m3274e(TAG, "initLocalConfig failed " + ttsVoiceType.name());
                    Pdlog.m3274e(TAG, Log.getStackTraceString(e));
                }
            }
        }
    }

    public final SetSpeakerListern getMSetSpeakerListern() {
        return mSetSpeakerListern;
    }

    public final void setMSetSpeakerListern(SetSpeakerListern setSpeakerListern) {
        mSetSpeakerListern = setSpeakerListern;
    }

    /* JADX WARN: Type inference failed for: r0v15, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v8, types: [T, java.lang.String] */
    public final void setSpeaker(final String speaker, SetSpeakerListern setSpeakerListern) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(setSpeakerListern, "setSpeakerListern");
        mSetSpeakerListern = setSpeakerListern;
        SetSpeakerListern setSpeakerListern2 = mSetSpeakerListern;
        if (setSpeakerListern2 != null) {
            setSpeakerListern2.doing();
        }
        Pdlog.m3273d(TAG, "speaker：" + speaker);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            objectRef.element = TtsConfig.INSTANCE.getIFLY_SPEECH_VOICE_NAME();
            TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(speaker);
        } else {
            objectRef.element = TtsConfig.INSTANCE.getGoogleTtsVoiceName();
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsSpeaker(speaker);
        }
        resetVoiceDateList(speaker, new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$setSpeaker$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                if (th == null) {
                    SpUtils.set(RobotContext.INSTANCE.getContext(), TtsVoiceHelper.INSTANCE.getSpeakerKey(), speaker);
                    TtsVoiceHelper.SetSpeakerListern mSetSpeakerListern2 = TtsVoiceHelper.INSTANCE.getMSetSpeakerListern();
                    if (mSetSpeakerListern2 != null) {
                        mSetSpeakerListern2.success();
                        return;
                    }
                    return;
                }
                if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
                    TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME((String) objectRef.element);
                } else {
                    TtsVoiceFlHelper.INSTANCE.setGoogleTtsSpeaker((String) objectRef.element);
                }
                TtsVoiceHelper.SetSpeakerListern mSetSpeakerListern3 = TtsVoiceHelper.INSTANCE.getMSetSpeakerListern();
                if (mSetSpeakerListern3 != null) {
                    mSetSpeakerListern3.error();
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void resetVoiceDateList$default(TtsVoiceHelper ttsVoiceHelper, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceHelper.resetVoiceDateList(str, function1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.util.ArrayList] */
    public final void resetVoiceDateList(final String speaker, final Function1<? super Throwable, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        for (TtsVoiceType ttsVoiceType : TtsVoiceType.values()) {
            Iterator<T> it = INSTANCE.getListByType(ttsVoiceType).iterator();
            while (it.hasNext()) {
                ((ArrayList) objectRef.element).add(new TtsVoiceDate((TtsConfigData) it.next(), ttsVoiceType));
            }
        }
        TtsVoiceManager.INSTANCE.resetMerchantTts(speaker, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$resetVoiceDateList$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (z) {
                    TtsVoiceHelper.INSTANCE.resetVoiceDate((ArrayList) Ref.ObjectRef.this.element, 0, speaker, callback);
                    return;
                }
                Function1 function1 = callback;
                if (function1 != null) {
                }
            }
        });
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceDate;", "", "ttsConfigData", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "ttsVoiceType", "Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "(Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;)V", "getTtsConfigData", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "getTtsVoiceType", "()Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsVoiceType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final /* data */ class TtsVoiceDate {
        private final TtsConfigData ttsConfigData;
        private final TtsVoiceType ttsVoiceType;

        public static /* synthetic */ TtsVoiceDate copy$default(TtsVoiceDate ttsVoiceDate, TtsConfigData ttsConfigData, TtsVoiceType ttsVoiceType, int i, Object obj) {
            if ((i & 1) != 0) {
                ttsConfigData = ttsVoiceDate.ttsConfigData;
            }
            if ((i & 2) != 0) {
                ttsVoiceType = ttsVoiceDate.ttsVoiceType;
            }
            return ttsVoiceDate.copy(ttsConfigData, ttsVoiceType);
        }

        /* renamed from: component1, reason: from getter */
        public final TtsConfigData getTtsConfigData() {
            return this.ttsConfigData;
        }

        /* renamed from: component2, reason: from getter */
        public final TtsVoiceType getTtsVoiceType() {
            return this.ttsVoiceType;
        }

        public final TtsVoiceDate copy(TtsConfigData ttsConfigData, TtsVoiceType ttsVoiceType) {
            Intrinsics.checkParameterIsNotNull(ttsConfigData, "ttsConfigData");
            Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
            return new TtsVoiceDate(ttsConfigData, ttsVoiceType);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TtsVoiceDate)) {
                return false;
            }
            TtsVoiceDate ttsVoiceDate = (TtsVoiceDate) other;
            return Intrinsics.areEqual(this.ttsConfigData, ttsVoiceDate.ttsConfigData) && Intrinsics.areEqual(this.ttsVoiceType, ttsVoiceDate.ttsVoiceType);
        }

        public int hashCode() {
            TtsConfigData ttsConfigData = this.ttsConfigData;
            int hashCode = (ttsConfigData != null ? ttsConfigData.hashCode() : 0) * 31;
            TtsVoiceType ttsVoiceType = this.ttsVoiceType;
            return hashCode + (ttsVoiceType != null ? ttsVoiceType.hashCode() : 0);
        }

        public String toString() {
            return "TtsVoiceDate(ttsConfigData=" + this.ttsConfigData + ", ttsVoiceType=" + this.ttsVoiceType + ")";
        }

        public TtsVoiceDate(TtsConfigData ttsConfigData, TtsVoiceType ttsVoiceType) {
            Intrinsics.checkParameterIsNotNull(ttsConfigData, "ttsConfigData");
            Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
            this.ttsConfigData = ttsConfigData;
            this.ttsVoiceType = ttsVoiceType;
        }

        public final TtsConfigData getTtsConfigData() {
            return this.ttsConfigData;
        }

        public final TtsVoiceType getTtsVoiceType() {
            return this.ttsVoiceType;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void resetVoiceDate$default(TtsVoiceHelper ttsVoiceHelper, ArrayList arrayList, int i, String str, Function1 function1, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceHelper.resetVoiceDate(arrayList, i, str, function1);
    }

    public final void resetVoiceDate(final ArrayList<TtsVoiceDate> array, int i, final String speaker, final Function1<? super Throwable, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = i;
        if (array.isEmpty()) {
            if (callback != null) {
                callback.invoke(null);
            }
        } else {
            intRef.element++;
            if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
                addNewTtsVoice(array.get(i).getTtsConfigData().getName(), array.get(i).getTtsVoiceType(), true, speaker, new Function1<Throwable, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$resetVoiceDate$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        if (th == null) {
                            if (Ref.IntRef.this.element < array.size()) {
                                TtsVoiceHelper.INSTANCE.resetVoiceDate(array, Ref.IntRef.this.element, speaker, callback);
                                return;
                            }
                            Function1 function1 = callback;
                            if (function1 != null) {
                                return;
                            }
                            return;
                        }
                        Function1 function12 = callback;
                        if (function12 != null) {
                        }
                    }
                });
            } else {
                addFlTtsVoice(array.get(i).getTtsConfigData().getName(), array.get(i).getTtsVoiceType(), true, speaker, new Function1<String, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$resetVoiceDate$2
                    /* JADX INFO: Access modifiers changed from: package-private */
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
                    public final void invoke2(String str) {
                        if (str == null) {
                            if (Ref.IntRef.this.element < array.size()) {
                                TtsVoiceHelper.INSTANCE.resetVoiceDate(array, Ref.IntRef.this.element, speaker, callback);
                                return;
                            }
                            Function1 function1 = callback;
                            if (function1 != null) {
                                return;
                            }
                            return;
                        }
                        Function1 function12 = callback;
                        if (function12 != null) {
                        }
                    }
                });
            }
        }
    }

    public static /* synthetic */ void addFlTtsVoice$default(TtsVoiceHelper ttsVoiceHelper, String str, String str2, TtsVoiceType ttsVoiceType, boolean z, String str3, Function1 function1, int i, Object obj) {
        if ((i & 8) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 16) != 0) {
            str3 = (String) null;
        }
        ttsVoiceHelper.addFlTtsVoice(str, str2, ttsVoiceType, z2, str3, function1);
    }

    public final void addFlTtsVoice(String text, String md5, TtsVoiceType ttsVoiceType, boolean notNeedCache, String speaker, Function1<? super String, Unit> callBack) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        if (speaker != null) {
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsSpeaker(speaker);
        } else {
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsVoiceName();
        }
        String filePath = TtsVoiceFlHelper.INSTANCE.getFilePath(BaseApplication.INSTANCE.getInstance(), md5, ttsVoiceType);
        Pdlog.m3273d(TAG, "addFlTtsVoice:开始生成:" + filePath);
        PdTtsSdk.INSTANCE.startTts(text, filePath, new TtsVoiceHelper$addFlTtsVoice$1(md5, text, ttsVoiceType, callBack), PdTtsSdk.TtsEngine.Google, notNeedCache);
    }

    public static /* synthetic */ void addFlTtsVoice$default(TtsVoiceHelper ttsVoiceHelper, String str, TtsVoiceType ttsVoiceType, boolean z, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        ttsVoiceHelper.addFlTtsVoice(str, ttsVoiceType, z2, str2, function1);
    }

    public final void addFlTtsVoice(String text, TtsVoiceType ttsVoiceType, boolean notNeedCache, String speaker, Function1<? super String, Unit> callBack) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        String md5 = MD5Util.toMD5((text + LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null)) + ttsVoiceType.name());
        Intrinsics.checkExpressionValueIsNotNull(md5, "md5");
        addFlTtsVoice(text, md5, ttsVoiceType, notNeedCache, speaker, callBack);
    }

    public static /* synthetic */ void addNewTtsVoice$default(TtsVoiceHelper ttsVoiceHelper, String str, TtsVoiceType ttsVoiceType, boolean z, String str2, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            str2 = (String) null;
        }
        ttsVoiceHelper.addNewTtsVoice(str, ttsVoiceType, z2, str2, function1);
    }

    public final void addNewTtsVoice(final String text, final TtsVoiceType ttsVoiceType, boolean notNeedCache, String speaker, final Function1<? super Throwable, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "addNewTtsVoice " + text);
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            callback.invoke(new Exception("tts not init"));
        } else {
            if (speaker != null) {
                TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(speaker);
            } else {
                TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(getSelectedSpeaker());
            }
            Observable.just(text).map(new Function<T, R>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$1
                @Override // io.reactivex.functions.Function
                public final String apply(String it) {
                    String str;
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    if (!LanguageUtils.INSTANCE.isZh()) {
                        it = (text + LanguageUtils.Companion.getLocaleStr$default(LanguageUtils.INSTANCE, false, 1, null)) + ttsVoiceType.name();
                    }
                    String md5 = MD5Util.toMD5(it);
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3273d(str, "gen md5 name = " + md5);
                    return md5;
                }
            }).subscribeOn(Schedulers.m3958io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TtsVoiceHelper$addNewTtsVoice$p$2(text, ttsVoiceType, callback, notNeedCache), new Consumer<Throwable>() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$addNewTtsVoice$p$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    String str;
                    String str2;
                    TtsVoiceHelper ttsVoiceHelper = TtsVoiceHelper.INSTANCE;
                    str = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str, "md5 error ??");
                    TtsVoiceHelper ttsVoiceHelper2 = TtsVoiceHelper.INSTANCE;
                    str2 = TtsVoiceHelper.TAG;
                    Pdlog.m3274e(str2, Log.getStackTraceString(th));
                    Function1.this.invoke(th);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addConfigItem(String filePath, String it, String text, TtsVoiceType ttsVoiceType) {
        setVoiceList(ttsVoiceType, text, new TtsConfigData(filePath, it, text, false, false, 16, null));
        checkSelect(ttsVoiceType);
        saveConfig(ttsVoiceType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setVoiceList(TtsVoiceType ttsVoiceType, String text, TtsConfigData ttsConfigData) {
        ArrayList<TtsConfigData> listByType = getListByType(ttsVoiceType);
        if (checkTtsExist(text, ttsVoiceType)) {
            return;
        }
        listByType.add(ttsConfigData);
    }

    private final void checkSelect(TtsVoiceType ttsVoiceType) {
        ArrayList<TtsConfigData> listByType = getListByType(ttsVoiceType);
        if (!listByType.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : listByType) {
                if (((TtsConfigData) obj).isSelect()) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                listByType.get(0).setSelect(true);
            }
        }
    }

    public final void deleteConfig(final TtsConfigData configData, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(configData, "configData");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Pdlog.m3273d(TAG, "deleteConfig " + configData);
        getListByType(ttsVoiceType).remove(configData);
        checkSelect(ttsVoiceType);
        if (!LanguageUtils.INSTANCE.isZh()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new TtsVoiceHelper$deleteConfig$2(configData, ttsVoiceType, null), 3, null);
        } else {
            ThreadPoolManager.getInstance().execSimpleTask(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$deleteConfig$1
                @Override // java.lang.Runnable
                public final void run() {
                    FileUtils.delete(TtsVoiceHelper.TtsConfigData.this.getPath());
                }
            });
            saveConfig(ttsVoiceType);
        }
        notifyChange(ttsVoiceType);
    }

    public final void updateConfig(TtsConfigData configData, String newContent, TtsVoiceType ttsVoiceType) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(configData, "configData");
        Intrinsics.checkParameterIsNotNull(newContent, "newContent");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Pdlog.m3273d(TAG, "editConfig " + configData + ", newContent: " + newContent);
        ArrayList<TtsConfigData> listByType = getListByType(ttsVoiceType);
        final TtsConfigData copy$default = TtsConfigData.copy$default(configData, null, null, null, false, false, 31, null);
        Iterator<T> it = listByType.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (Intrinsics.areEqual(((TtsConfigData) obj).getName(), newContent)) {
                    break;
                }
            }
        }
        TtsConfigData ttsConfigData = (TtsConfigData) obj;
        if (ttsConfigData != null) {
            configData.setName(ttsConfigData.getName());
            configData.setPath(ttsConfigData.getPath());
            configData.setMd5(ttsConfigData.getMd5());
            listByType.remove(ttsConfigData);
        }
        checkSelect(ttsVoiceType);
        if (!LanguageUtils.INSTANCE.isZh()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new TtsVoiceHelper$updateConfig$3(copy$default, ttsVoiceType, null), 3, null);
        } else {
            ThreadPoolManager.getInstance().execSimpleTask(new Runnable() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$updateConfig$2
                @Override // java.lang.Runnable
                public final void run() {
                    FileUtils.delete(TtsVoiceHelper.TtsConfigData.this.getPath());
                }
            });
            saveConfig(ttsVoiceType);
        }
        notifyChange(ttsVoiceType);
    }

    private final void notifyChange(TtsVoiceType ttsVoiceType) {
        Function1<? super TtsVoiceType, Unit> function1 = onChangeListener;
        if (function1 != null) {
            function1.invoke(ttsVoiceType);
        }
    }

    private final void saveConfig(TtsVoiceType ttsVoiceType) {
        try {
            String toJson = gson.toJson(getListByType(ttsVoiceType));
            Pdlog.m3273d(TAG, "saveConfig type = " + ttsVoiceType + ' ' + toJson);
            Context context = RobotContext.INSTANCE.getContext();
            String saveSpKey = getSaveSpKey(ttsVoiceType);
            Intrinsics.checkExpressionValueIsNotNull(toJson, "toJson");
            SpUtils.set(context, saveSpKey, toJson);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "saveConfig error");
            Pdlog.m3274e(TAG, Log.getStackTraceString(e));
        }
    }

    private final String getSaveSpKey(TtsVoiceType ttsVoiceType) {
        switch (ttsVoiceType) {
            case CRUISE_TYPE:
                return "key_cruise_tts_config";
            case DELIVER_TYPE:
                return "key_deliver_tts_config";
            case GREETER_TYPE:
                return Constans.KEY_GREETER_TTS_CONFIG;
            case GREETER_GUIDE_TYPE:
            case GUIDE_ARRIVAL_TYPE:
                return "key_greeter_guide_tts_config";
            case SPECIAL_MODE_ARRIVE:
                return Constans.KEY_SPECIAL_MODE_ARRIVE_CONFIG;
            case CRUISE_STAY_TYPE:
                return Constans.KEY_SPECIAL_MODE_CRUISE_CONFIG;
            case BIRTHDAY_TYPE:
                return Constans.KEY_BIRTHDAY_TTS_CONFIG;
            case PALLET_DELIVER_TYPE:
                return Constans.KEY_PALLET_DELIVER_TTS_CONFIG;
            case RECYCLE_TABLE_ARRIVE:
                return Constans.KEY_RECYCLE_TABLE_ARRIVE_CONFIG;
            case RECYCLE_TABLE_LEAVE:
                return Constans.KEY_RECYCLE_TABLE_LEAVE_CONFIG;
            case RECYCLE_POINT_ARRIVE:
                return Constans.KEY_RECYCLE_POINT_ARRIVE_CONFIG;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void genTtsVoice(String text, String path, String speaker, OnTtsListener onTtsListener) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(speaker, "speaker");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        PdTtsSdk.TtsEngine ttsEngine = PdTtsSdk.TtsEngine.Ifly;
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(speaker);
        } else {
            ttsEngine = PdTtsSdk.TtsEngine.Google;
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsSpeaker(speaker);
        }
        PdTtsSdk.TtsEngine ttsEngine2 = ttsEngine;
        Pdlog.m3273d(TAG, "speaker：" + speaker);
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            onTtsListener.onError(-1, "sdk not init");
        } else {
            PdTtsSdk.INSTANCE.startTts(text, path, onTtsListener, ttsEngine2, true);
        }
    }

    public final void setTtsTempPlay(String text, OnTtsListener onTtsListener) {
        String cruiseTempFilePath;
        PdTtsSdk.TtsEngine ttsEngine;
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(onTtsListener, "onTtsListener");
        if (!isInitSuccess) {
            Pdlog.m3274e(TAG, "PdTtsSdk init failed");
            onTtsListener.onError(-1, "sdk not init");
            return;
        }
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            TtsConfig.INSTANCE.setIFLY_SPEECH_VOICE_NAME(getSelectedSpeaker());
            Pdlog.m3273d(TAG, "speaker：" + TtsConfig.INSTANCE.getIFLY_SPEECH_VOICE_NAME());
            cruiseTempFilePath = getCruiseTempFilePath(BaseApplication.INSTANCE.getInstance());
            ttsEngine = PdTtsSdk.TtsEngine.Ifly;
        } else {
            TtsVoiceFlHelper.INSTANCE.setGoogleTtsVoiceName();
            cruiseTempFilePath = TtsVoiceFlHelper.INSTANCE.getTempPlayFilePath(BaseApplication.INSTANCE.getInstance());
            ttsEngine = PdTtsSdk.TtsEngine.Google;
        }
        PdTtsSdk.INSTANCE.startTts(text, cruiseTempFilePath, onTtsListener, ttsEngine, true);
    }

    public final String getSelectedSpeaker() {
        return SpUtils.get(RobotContext.INSTANCE.getContext(), getSpeakerKey(), DEF_SPEEK);
    }

    public final boolean checkTtsExist(String text, TtsVoiceType ttsVoiceType) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(text, "text");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Iterator<T> it = getListByType(ttsVoiceType).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((TtsConfigData) obj).getName(), text)) {
                break;
            }
        }
        return ((TtsConfigData) obj) != null;
    }

    public final TtsVoiceOpenType checkTtsOpenType(Context context, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        if (VoicePackageHelper.INSTANCE.isSelectMerchantTts()) {
            Pdlog.m3273d(TAG, "checkTtsOpenType open MerchantTts");
            return TtsVoiceOpenType.CLOSE;
        }
        if (LanguageUtils.INSTANCE.isZh(context)) {
            int i = SpUtils.get(context, TtsMarkUtil.INSTANCE.getTtsSwitchKey(ttsVoiceType), 0);
            if (i == TtsVoiceOpenType.CLOSE.getType()) {
                return TtsVoiceOpenType.CLOSE;
            }
            boolean isZh = LanguageUtils.INSTANCE.isZh(context);
            boolean isEnglish = LanguageUtils.INSTANCE.isEnglish(context);
            if (!isZh && !isEnglish) {
                Pdlog.m3273d(TAG, "checkCruiseTtsOpenType not is zh language");
                return TtsVoiceOpenType.CLOSE;
            }
            if (ttsVoiceType == TtsVoiceType.PALLET_DELIVER_TYPE || !getTtsConfigList(ttsVoiceType).isEmpty()) {
                return i == TtsVoiceOpenType.OPEN.getType() ? TtsVoiceOpenType.OPEN : TtsVoiceOpenType.CLOSE;
            }
            Pdlog.m3274e(TAG, "cruiseConfigDateList is empty ????");
            setTtsType(context, TtsVoiceOpenType.CLOSE, ttsVoiceType);
            return TtsVoiceOpenType.CLOSE;
        }
        if (TtsMarkUtil.INSTANCE.getTtsSwitch(ttsVoiceType) == TtsVoiceOpenType.CLOSE.getType()) {
            return TtsVoiceOpenType.CLOSE;
        }
        if (ttsVoiceType != TtsVoiceType.PALLET_DELIVER_TYPE && getTtsConfigList(ttsVoiceType).isEmpty()) {
            Pdlog.m3274e(TAG, "cruiseConfigDateList is empty ????");
            setTtsType(context, TtsVoiceOpenType.CLOSE, ttsVoiceType);
            return TtsVoiceOpenType.CLOSE;
        }
        return TtsVoiceOpenType.OPEN;
    }

    public final boolean isOpen(Context context, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        return checkTtsOpenType(context, ttsVoiceType) == TtsVoiceOpenType.OPEN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFilePath(Context context, String name, TtsVoiceType ttsVoiceType) {
        switch (ttsVoiceType) {
            case CRUISE_TYPE:
                return getCruiseFilePath(context, name);
            case DELIVER_TYPE:
                return getDeliverFilePath(context, name);
            case GREETER_TYPE:
                return getGreeterFilePath(context, name);
            case GREETER_GUIDE_TYPE:
                return getGreeterGuideFilePath(context, name);
            case GUIDE_ARRIVAL_TYPE:
                return getGuideArrivalFilePath(context, name);
            case SPECIAL_MODE_ARRIVE:
                return getSpcialModeArrivePath(context, name);
            case CRUISE_STAY_TYPE:
                return getCruiseStayFilePath(context, name);
            case BIRTHDAY_TYPE:
                return getBirthdayFilePath(context, name);
            case PALLET_DELIVER_TYPE:
                return getPalletDeliverFilePath(context, name);
            case RECYCLE_TABLE_ARRIVE:
                return getRecycleTableArriveFilePath(context, name);
            case RECYCLE_TABLE_LEAVE:
                return getRecycleTableLeaveFilePath(context, name);
            case RECYCLE_POINT_ARRIVE:
                return getRecyclePointArriveFilePath(context, name);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final String getCruiseFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getCruiseFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getDeliverFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_deliver/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getBirthdayFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getBirthdayFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_birthday/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getPalletDeliverFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getPalletDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_pallet_deliver/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getGreeterFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getGreeterFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_greeter/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getGreeterGuideFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getGreeterGuideFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_greeter_guide/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getGuideArrivalFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getGuideArrivalFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_guide_arrival/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getSpcialModeArrivePath(Context context, String name) {
        Pdlog.m3274e(TAG, "SpcialModeArrivePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_spcial_mode_arrive/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getCruiseStayFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getDeliverFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_cruise_stay/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    public final String getCruiseTempFilePath(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        StringBuilder sb = new StringBuilder();
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        sb.append(cacheDir.getAbsolutePath());
        sb.append("/tts/temp_cruise.pcm");
        return sb.toString();
    }

    private final String getRecycleTableArriveFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getRecycleTableArriveFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_recycle_table_arrive/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getRecycleTableLeaveFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getRecycleTableLeaveFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_recycle_table_leave/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    private final String getRecyclePointArriveFilePath(Context context, String name) {
        Pdlog.m3274e(TAG, "getRecycleTableLeaveFilePath " + name);
        StringBuilder sb = new StringBuilder();
        File filesDir = context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        sb.append(filesDir.getAbsolutePath());
        sb.append("/tts_recycle_point_leave/");
        sb.append(name);
        sb.append(FLIE_MARK);
        return sb.toString();
    }

    public final ArrayList<TtsConfigData> getTtsConfigList(TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        switch (ttsVoiceType) {
            case CRUISE_TYPE:
                return cruiseConfigDateList;
            case DELIVER_TYPE:
                return deliverConfigDateList;
            case GREETER_TYPE:
                return greeterConfigDateList;
            case GREETER_GUIDE_TYPE:
                return greeterGuideConfigDateList;
            case GUIDE_ARRIVAL_TYPE:
                return guideArrivalConfigDataList;
            case SPECIAL_MODE_ARRIVE:
                return specialModeArriveDateList;
            case CRUISE_STAY_TYPE:
                return cruiseStayDateList;
            case BIRTHDAY_TYPE:
                return birthdayList;
            case PALLET_DELIVER_TYPE:
                return palletDeliverConfigDateList;
            case RECYCLE_TABLE_ARRIVE:
                return recycleTableArriveList;
            case RECYCLE_TABLE_LEAVE:
                return recycleTableLeaveList;
            case RECYCLE_POINT_ARRIVE:
                return recyclePointArriveList;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void setTimeInterval(String interval, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(interval, "interval");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        Application companion = BaseApplication.INSTANCE.getInstance();
        if (LanguageUtils.INSTANCE.isZh()) {
            int i = WhenMappings.$EnumSwitchMapping$4[ttsVoiceType.ordinal()];
            if (i == 1) {
                SpUtils.set(companion, "key_cruise_voice_interval", interval);
                return;
            }
            if (i == 2) {
                SpUtils.set(companion, "key_greeter_voice_interval", interval);
                return;
            } else if (i == 3) {
                SpUtils.set(companion, "key_greeter_guide_voice_interval", interval);
                return;
            } else {
                if (i != 4) {
                    return;
                }
                SpUtils.set(companion, Constans.KEY_CRUISE_STAY_VOICE_INTERVAL_TIME, interval);
                return;
            }
        }
        TtsMarkUtil.INSTANCE.saveTtsPlayInterval(ttsVoiceType, interval);
    }

    public final int getTimeInterval(TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        String str = "20";
        if (LanguageUtils.INSTANCE.isZh()) {
            int i = WhenMappings.$EnumSwitchMapping$5[ttsVoiceType.ordinal()];
            if (i == 1) {
                str = SpUtils.get(BaseApplication.INSTANCE.getInstance(), "key_cruise_voice_interval", "20");
            } else if (i == 2) {
                str = SpUtils.get(BaseApplication.INSTANCE.getInstance(), "key_greeter_voice_interval", "20");
            } else if (i == 3) {
                str = SpUtils.get(BaseApplication.INSTANCE.getInstance(), "key_greeter_guide_voice_interval", "20");
            } else if (i == 4) {
                try {
                    try {
                        str = SpUtils.get(BaseApplication.INSTANCE.getInstance(), Constans.KEY_CRUISE_STAY_VOICE_INTERVAL_TIME, "5");
                    } catch (Exception unused) {
                        str = String.valueOf(SpUtils.get((Context) BaseApplication.INSTANCE.getInstance(), Constans.KEY_CRUISE_STAY_VOICE_INTERVAL_TIME, 5));
                        Pdlog.m3273d(TAG, "KEY_CRUISE_STAY_VOICE_INTERVAL_TIME--String is error:获得时间:" + str);
                        return Integer.parseInt(str);
                    }
                } catch (Exception unused2) {
                    Pdlog.m3273d(TAG, "KEY_CRUISE_STAY_VOICE_INTERVAL_TIME--int is error:获得时间:20");
                    Pdlog.m3273d(TAG, "KEY_CRUISE_STAY_VOICE_INTERVAL_TIME--String is error:获得时间:" + str);
                    return Integer.parseInt(str);
                }
            }
        } else {
            str = TtsMarkUtil.INSTANCE.getTtsPlayInterval(ttsVoiceType);
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception unused3) {
            Pdlog.m3274e(TAG, "INTERVAL 转换失败");
            return 5;
        }
    }

    public final void setTtsType(Context context, TtsVoiceOpenType ttsVoiceOpenType, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(ttsVoiceOpenType, "ttsVoiceOpenType");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        if (LanguageUtils.INSTANCE.isZh()) {
            SpUtils.set(context, TtsMarkUtil.INSTANCE.getTtsSwitchKey(ttsVoiceType), ttsVoiceOpenType.getType());
        } else {
            TtsMarkUtil.INSTANCE.saveTtsSwitch(ttsVoiceOpenType, ttsVoiceType);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playTtsVoice$default(TtsVoiceHelper ttsVoiceHelper, TtsVoiceType ttsVoiceType, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceHelper.playTtsVoice(ttsVoiceType, function1);
    }

    public final void playTtsVoice(TtsVoiceType ttsVoiceType, final Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        ArrayList<TtsConfigData> ttsConfigList = getTtsConfigList(ttsVoiceType);
        ArrayList arrayList = new ArrayList();
        for (Object obj : ttsConfigList) {
            if (((TtsConfigData) obj).isSelect()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        Pdlog.m3273d(TAG, "playTtsVoice " + arrayList2);
        if (!arrayList2.isEmpty()) {
            TtsConfigData ttsConfigData = (TtsConfigData) arrayList2.get(Random.INSTANCE.nextInt(arrayList2.size()));
            Pdlog.m3273d(TAG, "playTtsVoice " + ttsConfigData);
            if (TtsVoiceFlHelper.INSTANCE.isGoogleTts(ttsConfigData.getPath())) {
                PdTtsSdk.INSTANCE.playTtsMp3File(ttsConfigData.getPath(), new CMediaPlayer.OnPlayStateChangedListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$playTtsVoice$1
                    @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                    public void onPlayStop() {
                        CMediaPlayer.OnPlayStateChangedListener.DefaultImpls.onPlayStop(this);
                    }

                    @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                    public void onPlayFinished() {
                        Function1 function1 = Function1.this;
                        if (function1 != null) {
                        }
                    }

                    @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                    public void onPlaying() {
                        Function1 function1 = Function1.this;
                        if (function1 != null) {
                        }
                    }
                });
                return;
            } else {
                PdTtsSdk.INSTANCE.playTtsPcmFile(ttsConfigData.getPath(), listener);
                return;
            }
        }
        Pdlog.m3274e(TAG, "playTtsVoice , select is null");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void playPcm$default(TtsVoiceHelper ttsVoiceHelper, TtsConfigData ttsConfigData, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = (Function1) null;
        }
        ttsVoiceHelper.playPcm(ttsConfigData, function1);
    }

    public final void playPcm(TtsConfigData configData, final Function1<? super AudioTrackUtils.AudioPlayEvent, Unit> listener) {
        Intrinsics.checkParameterIsNotNull(configData, "configData");
        Pdlog.m3273d(TAG, "playPcm " + configData);
        if (TtsVoiceFlHelper.INSTANCE.isGoogleTts(configData.getPath())) {
            PdTtsSdk.INSTANCE.playTtsMp3File(configData.getPath(), new CMediaPlayer.OnPlayStateChangedListener() { // from class: com.pudutech.bumblebee.robot_ui.ui.helper.TtsVoiceHelper$playPcm$1
                @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                public void onPlayFinished() {
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }

                @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                public void onPlaying() {
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }

                @Override // com.pudutech.tts_sdk.utils.CMediaPlayer.OnPlayStateChangedListener
                public void onPlayStop() {
                    Function1 function1 = Function1.this;
                    if (function1 != null) {
                    }
                }
            });
        } else {
            PdTtsSdk.INSTANCE.playTtsPcmFile(configData.getPath(), listener);
        }
    }

    public final void playTempCruiseTts() {
        Pdlog.m3273d(TAG, "playTempCruiseTts");
        if (LanguageUtils.INSTANCE.isZh() || LanguageUtils.INSTANCE.isZhTw()) {
            PdTtsSdk.playTtsPcmFile$default(PdTtsSdk.INSTANCE, getCruiseTempFilePath(RobotContext.INSTANCE.getContext()), null, 2, null);
        } else {
            PdTtsSdk.playTtsMp3File$default(PdTtsSdk.INSTANCE, TtsVoiceFlHelper.INSTANCE.getTempPlayFilePath(RobotContext.INSTANCE.getContext()), null, 2, null);
        }
    }

    public final void stopCruiseTts() {
        Pdlog.m3273d(TAG, "stopCruiseTts");
        PdTtsSdk.INSTANCE.stopTtsPcmFile();
        PdTtsSdk.INSTANCE.stopTtsMp3File();
    }

    public final void updateVolume(int volume) {
        TtsConfig.INSTANCE.setIFLY_SPEECH_VOLUME(100);
        TtsConfig.INSTANCE.setPCM_PLAY_VOLUME((float) (volume * 0.01d));
        Pdlog.m3273d(TAG, "Update tts volume: " + volume);
    }

    public final void changeChoose(TtsConfigData item, TtsVoiceType ttsVoiceType) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(ttsVoiceType, "ttsVoiceType");
        switch (ttsVoiceType) {
            case CRUISE_TYPE:
                item.setSelect(!item.isSelect());
                break;
            case DELIVER_TYPE:
                for (TtsConfigData ttsConfigData : getTtsConfigList(ttsVoiceType)) {
                    ttsConfigData.setSelect(Intrinsics.areEqual(ttsConfigData, item));
                }
                break;
            case BIRTHDAY_TYPE:
                for (TtsConfigData ttsConfigData2 : getTtsConfigList(ttsVoiceType)) {
                    ttsConfigData2.setSelect(Intrinsics.areEqual(ttsConfigData2, item));
                }
                break;
            case GREETER_TYPE:
                item.setSelect(!item.isSelect());
                break;
            case GREETER_GUIDE_TYPE:
                item.setSelect(!item.isSelect());
                break;
            case GUIDE_ARRIVAL_TYPE:
                for (TtsConfigData ttsConfigData3 : getTtsConfigList(ttsVoiceType)) {
                    ttsConfigData3.setSelect(Intrinsics.areEqual(ttsConfigData3, item));
                }
                break;
            case SPECIAL_MODE_ARRIVE:
                item.setSelect(!item.isSelect());
                break;
            case CRUISE_STAY_TYPE:
                for (TtsConfigData ttsConfigData4 : getTtsConfigList(ttsVoiceType)) {
                    ttsConfigData4.setSelect(Intrinsics.areEqual(ttsConfigData4, item));
                }
                break;
            case PALLET_DELIVER_TYPE:
                item.setSelect(!item.isSelect());
                break;
            case RECYCLE_TABLE_ARRIVE:
                item.setSelect(!item.isSelect());
                break;
            case RECYCLE_TABLE_LEAVE:
                item.setSelect(!item.isSelect());
                break;
            case RECYCLE_POINT_ARRIVE:
                item.setSelect(!item.isSelect());
                break;
        }
        if (!item.isSelect()) {
            ArrayList<TtsConfigData> ttsConfigList = getTtsConfigList(ttsVoiceType);
            ArrayList arrayList = new ArrayList();
            for (Object obj : ttsConfigList) {
                if (((TtsConfigData) obj).isSelect()) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.isEmpty()) {
                item.setSelect(true);
            }
        }
        if (!LanguageUtils.INSTANCE.isZh()) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new TtsVoiceHelper$changeChoose$5(item, ttsVoiceType, null), 3, null);
        } else {
            saveConfig(ttsVoiceType);
        }
    }

    /* compiled from: TtsVoiceHelper.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J;\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00072\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\n\"\u0004\b\r\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/ui/helper/TtsVoiceHelper$TtsConfigData;", "", "path", "", "md5", "name", "isSelect", "", "isPlay", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "()Z", "setPlay", "(Z)V", "setSelect", "getMd5", "()Ljava/lang/String;", "setMd5", "(Ljava/lang/String;)V", "getName", "setName", "getPath", "setPath", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes3.dex */
    public static final /* data */ class TtsConfigData {

        @IgnoreAnnotation
        private boolean isPlay;
        private boolean isSelect;
        private String md5;
        private String name;
        private String path;

        public static /* synthetic */ TtsConfigData copy$default(TtsConfigData ttsConfigData, String str, String str2, String str3, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = ttsConfigData.path;
            }
            if ((i & 2) != 0) {
                str2 = ttsConfigData.md5;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                str3 = ttsConfigData.name;
            }
            String str5 = str3;
            if ((i & 8) != 0) {
                z = ttsConfigData.isSelect;
            }
            boolean z3 = z;
            if ((i & 16) != 0) {
                z2 = ttsConfigData.isPlay;
            }
            return ttsConfigData.copy(str, str4, str5, z3, z2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMd5() {
            return this.md5;
        }

        /* renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsSelect() {
            return this.isSelect;
        }

        /* renamed from: component5, reason: from getter */
        public final boolean getIsPlay() {
            return this.isPlay;
        }

        public final TtsConfigData copy(String path, String md5, String name, boolean isSelect, boolean isPlay) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            Intrinsics.checkParameterIsNotNull(md5, "md5");
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new TtsConfigData(path, md5, name, isSelect, isPlay);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TtsConfigData)) {
                return false;
            }
            TtsConfigData ttsConfigData = (TtsConfigData) other;
            return Intrinsics.areEqual(this.path, ttsConfigData.path) && Intrinsics.areEqual(this.md5, ttsConfigData.md5) && Intrinsics.areEqual(this.name, ttsConfigData.name) && this.isSelect == ttsConfigData.isSelect && this.isPlay == ttsConfigData.isPlay;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            String str = this.path;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.md5;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.name;
            int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            boolean z = this.isSelect;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            int i2 = (hashCode3 + i) * 31;
            boolean z2 = this.isPlay;
            int i3 = z2;
            if (z2 != 0) {
                i3 = 1;
            }
            return i2 + i3;
        }

        public String toString() {
            return "TtsConfigData(path=" + this.path + ", md5=" + this.md5 + ", name=" + this.name + ", isSelect=" + this.isSelect + ", isPlay=" + this.isPlay + ")";
        }

        public TtsConfigData(String path, String md5, String name, boolean z, boolean z2) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            Intrinsics.checkParameterIsNotNull(md5, "md5");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.path = path;
            this.md5 = md5;
            this.name = name;
            this.isSelect = z;
            this.isPlay = z2;
        }

        public final String getPath() {
            return this.path;
        }

        public final void setPath(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.path = str;
        }

        public final String getMd5() {
            return this.md5;
        }

        public final void setMd5(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.md5 = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final boolean isSelect() {
            return this.isSelect;
        }

        public final void setSelect(boolean z) {
            this.isSelect = z;
        }

        public /* synthetic */ TtsConfigData(String str, String str2, String str3, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, z, (i & 16) != 0 ? false : z2);
        }

        public final boolean isPlay() {
            return this.isPlay;
        }

        public final void setPlay(boolean z) {
            this.isPlay = z;
        }
    }
}
