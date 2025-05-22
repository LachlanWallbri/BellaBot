package com.pudutech.voiceinteraction.component.utils;

import android.content.Context;
import com.iflytek.aiui.vad.sdk.EVad;
import com.iflytek.aiui.vad.sdk.Vad;
import com.iflytek.aiui.vad.sdk.VadConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.config.Constans;
import com.pudutech.voiceinteraction.component.VoiceCommentConfig;
import java.io.IOException;
import java.io.InputStream;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: VadTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0015J\u0010\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\n\u0010\u001c\u001a\u0004\u0018\u00010\u0004H\u0002J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0011J\u001a\u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0006\u0010\"\u001a\u00020\u0015J&\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/voiceinteraction/component/utils/VadTool;", "", "()V", "APPID", "", "ASSETS_CONFIG_PATH", "TAG", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "VAD_PATH", "mLock", "Ljava/util/concurrent/locks/ReentrantLock;", "mVad", "Lcom/iflytek/aiui/vad/sdk/Vad;", "mVadListener", "Lcom/iflytek/aiui/vad/sdk/Vad$VadListener;", "vadlock", "Ljava/lang/Object;", "create", "", "context", "Landroid/content/Context;", "destroy", "detect", "audioData", "", "getWIFIMac", "init", "", "vadListener", "readAsset", "filePath", "resetVad", "setVADConfig", "BOS_THRESHOLD", "EOS_THRESHOLD", "BOS_TIMEOUT", "EOS_TIMEOUT", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class VadTool {
    private static String APPID = null;
    private static final String ASSETS_CONFIG_PATH = "cfg/aiui_phone.cfg";
    private static Vad mVad;
    private static Vad.VadListener mVadListener;
    public static final VadTool INSTANCE = new VadTool();
    private static String VAD_PATH = "vad/evad_5d9c055f.jet";
    private static String TAG = "VadTool";
    private static final Object vadlock = new Object();
    private static final ReentrantLock mLock = new ReentrantLock();

    private VadTool() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final void setTAG(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        TAG = str;
    }

    public final void create(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (mVad == null) {
            String str = "sn=" + getWIFIMac() + ",appid=5d9c055f";
            byte[] readAsset = readAsset(VAD_PATH, context);
            Integer valueOf = readAsset != null ? Integer.valueOf(readAsset.length) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            EVad createInstance = EVad.createInstance(str, readAsset, valueOf.intValue(), mVadListener);
            if (createInstance == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.iflytek.aiui.vad.sdk.EVad");
            }
            mVad = createInstance;
            setVADConfig("0.4", Constans.KEY_DEFAULT_SPEED_CONFIG, String.valueOf(VoiceCommentConfig.INSTANCE.getVadBosTimeout()), String.valueOf(VoiceCommentConfig.INSTANCE.getVadEosTimeout()));
            Vad vad = mVad;
            Integer valueOf2 = vad != null ? Integer.valueOf(vad.requestAuth()) : null;
            String str2 = TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("create VAD instance success version: ");
            Vad vad2 = mVad;
            sb.append(vad2 != null ? vad2.getVersion() : null);
            sb.append(" ;VAD requestAuth info:");
            sb.append(valueOf2);
            sb.append(" (0 for success) ;params:");
            sb.append(str);
            sb.append(";vad_path:");
            sb.append(VAD_PATH);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str2, objArr);
            return;
        }
        Pdlog.m3273d(TAG, " Vad instance is exists");
    }

    public final void destroy() {
        mLock.lockInterruptibly();
        try {
            if (mVad != null) {
                Vad vad = mVad;
                if (vad != null) {
                    vad.destroy();
                }
                mVad = (Vad) null;
                Pdlog.m3273d(TAG, " Vad instance is destroy");
            }
        } finally {
            mLock.unlock();
        }
    }

    public final int init(Context context, Vad.VadListener vadListener) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(vadListener, "vadListener");
        if (mVad != null) {
            return 0;
        }
        mVadListener = vadListener;
        try {
            byte[] readAsset = readAsset("cfg/aiui_phone.cfg", context);
            if (readAsset == null) {
                Intrinsics.throwNpe();
            }
            APPID = new JSONObject(new String(readAsset, Charsets.UTF_8)).optJSONObject("login").get("appid").toString();
            create(context);
            Pdlog.m3273d(TAG, " init Vad instance");
            return 0;
        } catch (Exception unused) {
            Pdlog.m3273d(TAG, "init Vad instance fail");
            return -1;
        }
    }

    public final int setVADConfig(String BOS_THRESHOLD, String EOS_THRESHOLD, String BOS_TIMEOUT, String EOS_TIMEOUT) {
        Intrinsics.checkParameterIsNotNull(BOS_THRESHOLD, "BOS_THRESHOLD");
        Intrinsics.checkParameterIsNotNull(EOS_THRESHOLD, "EOS_THRESHOLD");
        Intrinsics.checkParameterIsNotNull(BOS_TIMEOUT, "BOS_TIMEOUT");
        Intrinsics.checkParameterIsNotNull(EOS_TIMEOUT, "EOS_TIMEOUT");
        Vad vad = mVad;
        Integer valueOf = vad != null ? Integer.valueOf(vad.setParameter(VadConstant.KEY_BOS_THRESHOLD, BOS_THRESHOLD)) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        int intValue = valueOf.intValue();
        Vad vad2 = mVad;
        Integer valueOf2 = vad2 != null ? Integer.valueOf(vad2.setParameter(VadConstant.KEY_BOS_TIMEOUT, BOS_TIMEOUT)) : null;
        if (valueOf2 == null) {
            Intrinsics.throwNpe();
        }
        int intValue2 = valueOf2.intValue();
        Vad vad3 = mVad;
        Integer valueOf3 = vad3 != null ? Integer.valueOf(vad3.setParameter(VadConstant.KEY_EOS_TIMEOUT, EOS_TIMEOUT)) : null;
        if (valueOf3 == null) {
            Intrinsics.throwNpe();
        }
        int intValue3 = valueOf3.intValue();
        Pdlog.m3273d(TAG, "Vad changeState BOS_THRESHOLD=" + BOS_THRESHOLD + ':' + intValue + "  ;EOS_THRESHOLD=" + EOS_THRESHOLD + ":0;KEY_BOS_TIMEOUT=" + BOS_TIMEOUT + ':' + intValue2 + ";KEY_EOS_TIMEOUT=" + EOS_TIMEOUT + ':' + intValue3);
        return intValue | 0 | intValue2 | intValue3;
    }

    public final void detect(byte[] audioData) {
        Vad vad;
        if (audioData == null || (vad = mVad) == null) {
            return;
        }
        vad.detect(audioData, audioData.length);
    }

    private final byte[] readAsset(String filePath, Context context) {
        InputStream inputStream = (InputStream) null;
        try {
            try {
                inputStream = context.getResources().getAssets().open(filePath);
                byte[] bArr = new byte[inputStream.available()];
                inputStream.read(bArr);
                if (inputStream != null) {
                    inputStream.close();
                }
                return bArr;
            } catch (IOException e) {
                e.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                return null;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public final void resetVad() {
        Vad vad = mVad;
        if (vad != null) {
            vad.reset();
        }
    }

    private final String getWIFIMac() {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface nif = (NetworkInterface) it.next();
                Intrinsics.checkExpressionValueIsNotNull(nif, "nif");
                if (StringsKt.equals(nif.getName(), "wlan0", true)) {
                    byte[] hardwareAddress = nif.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Byte.valueOf(b)};
                        String format = String.format("%02X:", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                        sb.append(format);
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
