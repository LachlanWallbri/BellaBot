package com.pudutech.bumblebee.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseConfig;
import com.pudutech.mirsdk.hardware.HardwareConfig;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: BusinessSetting.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0000¢\u0006\u0002\b8J\b\u00109\u001a\u000205H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R&\u0010\u0015\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00148F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R$\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000e\"\u0004\b\u001f\u0010\u0010R$\u0010!\u001a\u00020 2\u0006\u0010\n\u001a\u00020 @FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010&\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u000e\"\u0004\b(\u0010\u0010R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010+\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00148F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0017\"\u0004\b-\u0010\u0019R$\u0010.\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0010R&\u00101\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u00148F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019¨\u0006:"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/BusinessSetting;", "", "()V", "AUTO_BACK_OR_NEXT_SIZE", "", "FILE_NAME", "KEY_BTN_VOICE", "KEY_MUSIC_VOICE", "KEY_SOUND_VOICE", "TAG", ES6Iterator.VALUE_PROPERTY, "", "arrivalLoopVoiceDelayTime_ms", "getArrivalLoopVoiceDelayTime_ms", "()J", "setArrivalLoopVoiceDelayTime_ms", "(J)V", BusinessSetting.AUTO_BACK_OR_NEXT_SIZE, "getAutoBackOrNextSize", "setAutoBackOrNextSize", "", "btnVoice", "getBtnVoice", "()I", "setBtnVoice", "(I)V", "callFreezeTime_ms", "getCallFreezeTime_ms", "setCallFreezeTime_ms", "cruisePauseKeepTime_ms", "getCruisePauseKeepTime_ms", "setCruisePauseKeepTime_ms", "", "delayAutoFinishSwitch", "getDelayAutoFinishSwitch", "()Z", "setDelayAutoFinishSwitch", "(Z)V", "delayAutoFinish_ms", "getDelayAutoFinish_ms", "setDelayAutoFinish_ms", "editor", "Landroid/content/SharedPreferences$Editor;", "musicVoice", "getMusicVoice", "setMusicVoice", "notCruisePauseKeepTime_ms", "getNotCruisePauseKeepTime_ms", "setNotCruisePauseKeepTime_ms", "soundVoice", "getSoundVoice", "setSoundVoice", "initConfig", "", "context", "Landroid/content/Context;", "initConfig$module_bumblebee_presenter_robotRelease", "oldFileCompatibility", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BusinessSetting {
    private static final String AUTO_BACK_OR_NEXT_SIZE = "autoBackOrNextSize";
    private static final String KEY_BTN_VOICE = "KEY_BTN_VOICE";
    private static final String KEY_MUSIC_VOICE = "KEY_MUSIC_VOICE";
    private static final String KEY_SOUND_VOICE = "KEY_SOUND_VOICE";
    private static boolean delayAutoFinishSwitch;
    private static SharedPreferences.Editor editor;
    public static final BusinessSetting INSTANCE = new BusinessSetting();
    private static final String TAG = "BusinessSetting";
    private static final String FILE_NAME = "BusinessSetting";
    private static long delayAutoFinish_ms = HardwareConfig.RGBDFwUpdateTimeOut;
    private static long callFreezeTime_ms = 120000;
    private static long arrivalLoopVoiceDelayTime_ms = 15000;
    private static long notCruisePauseKeepTime_ms = 10000;
    private static long cruisePauseKeepTime_ms = 20000;
    private static int soundVoice = 70;
    private static int musicVoice = 70;
    private static int btnVoice = 70;
    private static long autoBackOrNextSize = 15000;

    private BusinessSetting() {
    }

    public final void initConfig$module_bumblebee_presenter_robotRelease(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Pdlog.m3273d(TAG, "initConfig context=" + context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        editor = sharedPreferences != null ? sharedPreferences.edit() : null;
        oldFileCompatibility();
        if (sharedPreferences != null) {
            Pdlog.m3273d(TAG, "load config");
            INSTANCE.setDelayAutoFinishSwitch(sharedPreferences.getBoolean("delayAutoFinishSwitch", delayAutoFinishSwitch));
            INSTANCE.setDelayAutoFinish_ms(sharedPreferences.getLong("delayAutoFinish_ms", delayAutoFinish_ms));
            INSTANCE.setArrivalLoopVoiceDelayTime_ms(sharedPreferences.getLong("arrivalLoopVoiceDelayTime_s", arrivalLoopVoiceDelayTime_ms));
            INSTANCE.setCallFreezeTime_ms(sharedPreferences.getLong("callFreezeTime_ms", callFreezeTime_ms));
            INSTANCE.setNotCruisePauseKeepTime_ms(sharedPreferences.getLong("notCruisePauseKeepTime_ms", notCruisePauseKeepTime_ms));
            INSTANCE.setCruisePauseKeepTime_ms(sharedPreferences.getLong("cruisePauseKeepTime_ms", cruisePauseKeepTime_ms));
        }
        CruiseConfig.INSTANCE.initConfig$module_bumblebee_presenter_robotRelease(context);
    }

    public final boolean getDelayAutoFinishSwitch() {
        return delayAutoFinishSwitch;
    }

    public final void setDelayAutoFinishSwitch(boolean z) {
        SharedPreferences.Editor putBoolean;
        Pdlog.m3273d(TAG, "set delayAutoFinishSwitch " + z);
        delayAutoFinishSwitch = z;
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null || (putBoolean = editor2.putBoolean("delayAutoFinishSwitch", z)) == null) {
            return;
        }
        putBoolean.apply();
    }

    public final long getDelayAutoFinish_ms() {
        return delayAutoFinish_ms;
    }

    public final void setDelayAutoFinish_ms(long j) {
        SharedPreferences.Editor putLong;
        Pdlog.m3273d(TAG, "set delayAutoFinish_ms " + j);
        delayAutoFinish_ms = j;
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null || (putLong = editor2.putLong("delayAutoFinish_ms", j)) == null) {
            return;
        }
        putLong.apply();
    }

    public final long getCallFreezeTime_ms() {
        return callFreezeTime_ms;
    }

    public final void setCallFreezeTime_ms(long j) {
        SharedPreferences.Editor putLong;
        Pdlog.m3273d(TAG, "set callFreezeTime_ms " + j);
        callFreezeTime_ms = j;
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null || (putLong = editor2.putLong("callFreezeTime_ms", j)) == null) {
            return;
        }
        putLong.apply();
    }

    public final long getArrivalLoopVoiceDelayTime_ms() {
        return arrivalLoopVoiceDelayTime_ms;
    }

    public final void setArrivalLoopVoiceDelayTime_ms(long j) {
        SharedPreferences.Editor putLong;
        Pdlog.m3273d(TAG, "set arrivalLoopVoiceDelayTime_s " + j);
        arrivalLoopVoiceDelayTime_ms = j;
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null || (putLong = editor2.putLong("arrivalLoopVoiceDelayTime_s", j)) == null) {
            return;
        }
        putLong.apply();
    }

    public final long getNotCruisePauseKeepTime_ms() {
        return notCruisePauseKeepTime_ms;
    }

    public final void setNotCruisePauseKeepTime_ms(long j) {
        SharedPreferences.Editor putLong;
        notCruisePauseKeepTime_ms = j;
        Peripherals.INSTANCE.getFunctionButton().setNotCruisePauseKeepTime_ms(j);
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null || (putLong = editor2.putLong("notCruisePauseKeepTime_ms", j)) == null) {
            return;
        }
        putLong.apply();
    }

    public final long getCruisePauseKeepTime_ms() {
        return cruisePauseKeepTime_ms;
    }

    public final void setCruisePauseKeepTime_ms(long j) {
        SharedPreferences.Editor putLong;
        cruisePauseKeepTime_ms = j;
        Peripherals.INSTANCE.getFunctionButton().setCruisePauseKeepTime_ms(j);
        SharedPreferences.Editor editor2 = editor;
        if (editor2 == null || (putLong = editor2.putLong("cruisePauseKeepTime_ms", j)) == null) {
            return;
        }
        putLong.apply();
    }

    public final int getSoundVoice() {
        return SpUtils.get(BusinessContext.INSTANCE.getContext(), "KEY_SOUND_VOICE", 70);
    }

    public final void setSoundVoice(int i) {
        Pdlog.m3273d(TAG, "setSoundVoice " + i);
        soundVoice = i;
        SpUtils.set(BusinessContext.INSTANCE.getContext(), "KEY_SOUND_VOICE", i);
    }

    public final int getMusicVoice() {
        return SpUtils.get(BusinessContext.INSTANCE.getContext(), "KEY_MUSIC_VOICE", 70);
    }

    public final void setMusicVoice(int i) {
        Pdlog.m3273d(TAG, "setMusicVoice " + i);
        musicVoice = i;
        SpUtils.set(BusinessContext.INSTANCE.getContext(), "KEY_MUSIC_VOICE", i);
    }

    public final int getBtnVoice() {
        return SpUtils.get(BusinessContext.INSTANCE.getContext(), "KEY_BTN_VOICE", 70);
    }

    public final void setBtnVoice(int i) {
        Pdlog.m3273d(TAG, "setBtnVoice " + i);
        btnVoice = i;
        SpUtils.set(BusinessContext.INSTANCE.getContext(), "KEY_BTN_VOICE", i);
    }

    public final long getAutoBackOrNextSize() {
        return SpUtils.get(BusinessContext.INSTANCE.getContext(), AUTO_BACK_OR_NEXT_SIZE, 15000L);
    }

    public final void setAutoBackOrNextSize(long j) {
        Pdlog.m3273d(TAG, "setAutoBackOrNextSize " + j);
        autoBackOrNextSize = j;
        SpUtils.set(BusinessContext.INSTANCE.getContext(), AUTO_BACK_OR_NEXT_SIZE, j);
    }

    private final void oldFileCompatibility() {
        File file = new File("sdcard/debug_pneumonia");
        if (file.exists()) {
            Pdlog.m3273d(TAG, "oldFileCompatibility");
            BusinessSetting$oldFileCompatibility$1 businessSetting$oldFileCompatibility$1 = BusinessSetting$oldFileCompatibility$1.INSTANCE;
            try {
                List<String> readLines = TextStreamsKt.readLines(new FileReader(file));
                Long invoke2 = BusinessSetting$oldFileCompatibility$1.INSTANCE.invoke2(readLines.get(0));
                if (invoke2 != null) {
                    INSTANCE.setArrivalLoopVoiceDelayTime_ms(invoke2.longValue() * 1000);
                }
                Long invoke22 = BusinessSetting$oldFileCompatibility$1.INSTANCE.invoke2(readLines.get(1));
                if (invoke22 != null) {
                    INSTANCE.setDelayAutoFinish_ms(invoke22.longValue() * 1000);
                    INSTANCE.setDelayAutoFinishSwitch(true);
                }
                Long invoke23 = BusinessSetting$oldFileCompatibility$1.INSTANCE.invoke2(readLines.get(2));
                if (invoke23 != null) {
                    INSTANCE.setNotCruisePauseKeepTime_ms(invoke23.longValue() * 1000);
                }
            } catch (Exception e) {
                Pdlog.m3273d(TAG, String.valueOf(e));
            }
        }
        if (file.exists()) {
            Pdlog.m3273d(TAG, "file delete");
            file.delete();
        } else {
            Pdlog.m3273d(TAG, "file not exists");
        }
    }
}
