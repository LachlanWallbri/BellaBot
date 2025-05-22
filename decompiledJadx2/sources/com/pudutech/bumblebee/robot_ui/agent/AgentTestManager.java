package com.pudutech.bumblebee.robot_ui.agent;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.C4188R;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import com.pudutech.bumblebee.robot_ui.util.FileUtil;
import com.pudutech.disinfect.baselib.util.GsonSingleton;
import com.pudutech.disinfect.baselib.util.MMKVManager;
import com.pudutech.leaselib.utils.EncryptUtils;
import com.pudutech.leaselib.utils.FileIOUtils;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.shared.lib_syntime.SystemTimeSetting;
import java.io.File;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: AgentTestManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r*\u0001%\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002HIB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u00107\u001a\u0002022\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;J\n\u0010<\u001a\u0004\u0018\u00010=H\u0002J\u0006\u0010>\u001a\u00020\bJ\u0011\u0010?\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010@J\u0006\u0010A\u001a\u00020\u001dJ\u0006\u0010B\u001a\u000202J\u0010\u0010C\u001a\u0002022\u0006\u0010D\u001a\u00020\u0013H\u0002J\u0006\u0010E\u001a\u000202J\u0006\u0010F\u001a\u000202J\u0011\u0010G\u001a\u000202H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010@R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000R$\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u001d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010\"\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u001d8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u001f\"\u0004\b#\u0010!R\u0010\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0004\n\u0002\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R7\u0010-\u001a\u001f\u0012\u0013\u0012\u00110(¢\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(1\u0012\u0004\u0012\u000202\u0018\u00010.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006J"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager;", "", "()V", "AES_PADDING", "", "AGENT_CONFIG_PATH", "AGENT_INTERVAL_CONFIG_PATH", "AGENT_TEST_NO_DATA", "", "IV", "", "KEY", "KEY_12_TIP", "KEY_AGENT_TEST_DATA", "KEY_AGENT_TEST_TIME", "ONE_MINUTE", "TAG", "TIME_INTERVAL", "TOTAL_TEST_NUMBEER", "", "TWELVE_HOUR", "TWENTY_FOUR_HOUR", "WHAT_TEST_TIME", ES6Iterator.VALUE_PROPERTY, "getTestTime", "getGetTestTime", "()J", "setGetTestTime", "(J)V", "", "isHaveTestData", "()Z", "setHaveTestData", "(Z)V", "isHaved12Tip", "setHaved12Tip", "mHandle", "com/pudutech/bumblebee/robot_ui/agent/AgentTestManager$mHandle$1", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$mHandle$1;", "mTipType", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "getMTipType", "()Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "setMTipType", "(Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;)V", "onTipCallBack", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "type", "", "getOnTipCallBack", "()Lkotlin/jvm/functions/Function1;", "setOnTipCallBack", "(Lkotlin/jvm/functions/Function1;)V", "cleanFactory", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getAgentConfig", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$AgentConfig;", "getTimeDiff", "isCanAgentTest", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isOut24H", "saveAgentTestData", "saveNumData", "number", "startCountdown", "stopCountdown", "updateNumber", "AgentConfig", "TipType", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class AgentTestManager {
    private static final String AES_PADDING = "AES/CBC/PKCS5Padding";
    private static final String AGENT_CONFIG_PATH = "/sdcard/pudu/config/agent.config";
    private static final String AGENT_INTERVAL_CONFIG_PATH = "/sdcard/pudu/config/interval.config";
    public static final long AGENT_TEST_NO_DATA = -2;
    private static final String KEY = "793P3Mj9rUTNWil3";
    private static final String KEY_12_TIP = "key_haved_tip";
    public static final String KEY_AGENT_TEST_DATA = "key_agent_test_data";
    private static final String KEY_AGENT_TEST_TIME = "key_agent_test_time";
    public static final long ONE_MINUTE = 60000;
    private static final String TAG = "AgentTestManager";
    private static final int TOTAL_TEST_NUMBEER = 30;
    private static final long TWELVE_HOUR = 720;
    public static final long TWENTY_FOUR_HOUR = 1440;
    private static final int WHAT_TEST_TIME = 226;
    private static AgentTestManager$mHandle$1 mHandle;
    private static Function1<? super TipType, Unit> onTipCallBack;
    public static final AgentTestManager INSTANCE = new AgentTestManager();
    private static long TIME_INTERVAL = 600000;
    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static TipType mTipType = TipType.TipNo.INSTANCE;

    /* JADX WARN: Type inference failed for: r0v6, types: [com.pudutech.bumblebee.robot_ui.agent.AgentTestManager$mHandle$1] */
    static {
        final Looper mainLooper = Looper.getMainLooper();
        mHandle = new Handler(mainLooper) { // from class: com.pudutech.bumblebee.robot_ui.agent.AgentTestManager$mHandle$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                long j;
                super.handleMessage(msg);
                if (msg == null || msg.what != 226) {
                    return;
                }
                long timeDiff = AgentTestManager.INSTANCE.getTimeDiff();
                if (timeDiff >= AgentTestManager.TWENTY_FOUR_HOUR) {
                    if (!Intrinsics.areEqual(AgentTestManager.INSTANCE.getMTipType(), AgentTestManager.TipType.Tip24.INSTANCE)) {
                        AgentTestManager.INSTANCE.stopCountdown();
                        AgentTestManager.INSTANCE.setMTipType(AgentTestManager.TipType.Tip24.INSTANCE);
                        Function1<AgentTestManager.TipType, Unit> onTipCallBack2 = AgentTestManager.INSTANCE.getOnTipCallBack();
                        if (onTipCallBack2 != null) {
                            onTipCallBack2.invoke(AgentTestManager.INSTANCE.getMTipType());
                        }
                        Pdlog.m3273d("AgentTestManager", "mHandle()  24h end");
                        return;
                    }
                    return;
                }
                if (timeDiff >= 720 && !AgentTestManager.INSTANCE.isHaved12Tip() && (!Intrinsics.areEqual(AgentTestManager.INSTANCE.getMTipType(), AgentTestManager.TipType.Tip12.INSTANCE))) {
                    AgentTestManager.INSTANCE.setMTipType(AgentTestManager.TipType.Tip12.INSTANCE);
                    Function1<AgentTestManager.TipType, Unit> onTipCallBack3 = AgentTestManager.INSTANCE.getOnTipCallBack();
                    if (onTipCallBack3 != null) {
                        onTipCallBack3.invoke(AgentTestManager.INSTANCE.getMTipType());
                    }
                    Pdlog.m3273d("AgentTestManager", "mHandle()  12h arrive");
                }
                Pdlog.m3273d("AgentTestManager", "mHandle() mTipType = " + AgentTestManager.INSTANCE.getMTipType() + " mCurAgentTest-- = " + timeDiff + ' ');
                AgentTestManager agentTestManager = AgentTestManager.INSTANCE;
                j = AgentTestManager.TIME_INTERVAL;
                sendEmptyMessageDelayed(226, j);
            }
        };
    }

    private AgentTestManager() {
    }

    /* compiled from: AgentTestManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0005\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "", "()V", "toString", "", "Tip12", "Tip24", "TipNo", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType$TipNo;", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType$Tip12;", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType$Tip24;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static abstract class TipType {

        /* compiled from: AgentTestManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType$TipNo;", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class TipNo extends TipType {
            public static final TipNo INSTANCE = new TipNo();

            private TipNo() {
                super(null);
            }
        }

        private TipType() {
        }

        public /* synthetic */ TipType(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: AgentTestManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType$Tip12;", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Tip12 extends TipType {
            public static final Tip12 INSTANCE = new Tip12();

            private Tip12() {
                super(null);
            }
        }

        /* compiled from: AgentTestManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType$Tip24;", "Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$TipType;", "()V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes2.dex */
        public static final class Tip24 extends TipType {
            public static final Tip24 INSTANCE = new Tip24();

            private Tip24() {
                super(null);
            }
        }

        public String toString() {
            String simpleName = getClass().getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "javaClass.simpleName");
            return simpleName;
        }
    }

    public final TipType getMTipType() {
        return mTipType;
    }

    public final void setMTipType(TipType tipType) {
        Intrinsics.checkParameterIsNotNull(tipType, "<set-?>");
        mTipType = tipType;
    }

    public final Function1<TipType, Unit> getOnTipCallBack() {
        return onTipCallBack;
    }

    public final void setOnTipCallBack(Function1<? super TipType, Unit> function1) {
        onTipCallBack = function1;
    }

    public final Object isCanAgentTest(Continuation<? super Boolean> continuation) {
        if (new File(AGENT_INTERVAL_CONFIG_PATH).exists()) {
            String readContentFromFile = FileUtil.INSTANCE.readContentFromFile(AGENT_INTERVAL_CONFIG_PATH);
            Pdlog.m3273d(TAG, "isCanAgentTest()  time =" + readContentFromFile);
            if (readContentFromFile != null) {
                try {
                    if (readContentFromFile != null) {
                        TIME_INTERVAL = Long.parseLong(StringsKt.trim((CharSequence) readContentFromFile).toString()) * 1000;
                        Pdlog.m3273d(TAG, "isCanAgentTest()  TIME_INTERVAL =" + TIME_INTERVAL);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Pdlog.m3273d(TAG, "isCanAgentTest() fail TIME_INTERVAL =" + TIME_INTERVAL + ' ', Unit.INSTANCE);
                }
            }
        }
        File file = new File(AGENT_CONFIG_PATH);
        if (!file.isFile() || !file.exists()) {
            Pdlog.m3273d(TAG, "isCanAgentTest() file no exists");
            saveNumData(-2);
            return Boxing.boxBoolean(true);
        }
        AgentConfig agentConfig = getAgentConfig();
        Pdlog.m3273d(TAG, "isCanAgentTest() fromJson =" + agentConfig);
        if (agentConfig != null && agentConfig.getNumber() > 0) {
            return Boxing.boxBoolean(true);
        }
        return Boxing.boxBoolean(false);
    }

    private final AgentConfig getAgentConfig() {
        byte[] readFile2BytesByStream = FileIOUtils.readFile2BytesByStream(AGENT_CONFIG_PATH);
        byte[] bytes = KEY.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] decryptAES = EncryptUtils.decryptAES(readFile2BytesByStream, bytes, "AES/CBC/PKCS5Padding", IV);
        String str = decryptAES == null ? null : new String(decryptAES, Charsets.UTF_8);
        NetWorkLog.INSTANCE.mo3278d(TAG, "decryptAES = " + str);
        AgentConfig agentConfig = (AgentConfig) GsonSingleton.INSTANCE.getINSTANCE().fromJson(str, AgentConfig.class);
        Pdlog.m3273d(TAG, "getAgentConfig()  agentConfig = " + str + "  fromJson = " + agentConfig);
        return agentConfig;
    }

    public final Object updateNumber(Continuation<? super Unit> continuation) {
        AgentConfig agentConfig = getAgentConfig();
        if (agentConfig != null) {
            int number = agentConfig.getNumber() - 1;
            Pdlog.m3273d(TAG, "updateNumber()  cumNum =" + number);
            INSTANCE.saveNumData(number);
        }
        Pdlog.m3273d(TAG, "updateNumber()  fromJson =" + agentConfig);
        return Unit.INSTANCE;
    }

    public final void cleanFactory(Context context, CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        FileUtil.INSTANCE.cleanFactory(context, coroutineScope, context.getString(C4188R.string.test_clear_please_wait), new AgentTestManager$cleanFactory$1(null));
    }

    private final void saveNumData(int number) {
        AgentConfig agentConfig = new AgentConfig(number, 0);
        if (number == -2) {
            agentConfig = new AgentConfig(30, 0);
        }
        String json = GsonSingleton.INSTANCE.getINSTANCE().toJson(agentConfig);
        Charset charset = Charsets.UTF_8;
        if (json == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = json.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] bytes2 = KEY.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        Pdlog.m3273d(TAG, "saveNumData() toJson = " + json + "   resule = " + FileIOUtils.writeFileFromBytesByStream(AGENT_CONFIG_PATH, EncryptUtils.encryptAES(bytes, bytes2, "AES/CBC/PKCS5Padding", IV)));
    }

    public final boolean isOut24H() {
        return getTimeDiff() >= TWENTY_FOUR_HOUR;
    }

    public final long getTimeDiff() {
        long currentTimeMillis = (System.currentTimeMillis() - getGetTestTime()) / 60000;
        Pdlog.m3273d(TAG, "getTimeDif() timeDiff =" + currentTimeMillis + "  getTestTime = " + SystemTimeSetting.INSTANCE.formatDateOne(getGetTestTime()));
        return currentTimeMillis;
    }

    public final void saveAgentTestData() {
        setGetTestTime(System.currentTimeMillis());
        setHaveTestData(true);
        Pdlog.m3273d(TAG, "saveAgentTestData() getTestTime =" + SystemTimeSetting.INSTANCE.formatDateOne(getGetTestTime()));
    }

    public final void startCountdown() {
        if (!isHaved12Tip()) {
            mTipType = getTimeDiff() >= TWELVE_HOUR ? TipType.Tip12.INSTANCE : TipType.TipNo.INSTANCE;
            Pdlog.m3273d(TAG, "check >12H mTipType =" + mTipType);
        }
        if (mHandle.hasMessages(WHAT_TEST_TIME)) {
            mHandle.removeMessages(WHAT_TEST_TIME);
        }
        mHandle.sendEmptyMessage(WHAT_TEST_TIME);
        Pdlog.m3273d(TAG, "startCountdown() mTipType =" + mTipType + " mCurAgentTest =" + SystemTimeSetting.INSTANCE.formatDateOne(getGetTestTime()) + " localTime = " + SystemTimeSetting.INSTANCE.formatDateOne(System.currentTimeMillis()));
    }

    public final void stopCountdown() {
        if (mHandle.hasMessages(WHAT_TEST_TIME)) {
            mHandle.removeMessages(WHAT_TEST_TIME);
        }
        Pdlog.m3273d(TAG, "stopCountdown() mCurAgentTest =" + SystemTimeSetting.INSTANCE.formatDateOne(getGetTestTime()) + " localTime = " + SystemTimeSetting.INSTANCE.formatDateOne(System.currentTimeMillis()));
    }

    /* compiled from: AgentTestManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/agent/AgentTestManager$AgentConfig;", "", "number", "", "time", "(II)V", "getNumber", "()I", "getTime", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes2.dex */
    public static final /* data */ class AgentConfig {
        private final int number;
        private final int time;

        public static /* synthetic */ AgentConfig copy$default(AgentConfig agentConfig, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = agentConfig.number;
            }
            if ((i3 & 2) != 0) {
                i2 = agentConfig.time;
            }
            return agentConfig.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getNumber() {
            return this.number;
        }

        /* renamed from: component2, reason: from getter */
        public final int getTime() {
            return this.time;
        }

        public final AgentConfig copy(int number, int time) {
            return new AgentConfig(number, time);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AgentConfig)) {
                return false;
            }
            AgentConfig agentConfig = (AgentConfig) other;
            return this.number == agentConfig.number && this.time == agentConfig.time;
        }

        public int hashCode() {
            return (Integer.hashCode(this.number) * 31) + Integer.hashCode(this.time);
        }

        public String toString() {
            return "AgentConfig(number=" + this.number + ", time=" + this.time + ")";
        }

        public AgentConfig(int i, int i2) {
            this.number = i;
            this.time = i2;
        }

        public /* synthetic */ AgentConfig(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? -1 : i, i2);
        }

        public final int getNumber() {
            return this.number;
        }

        public final int getTime() {
            return this.time;
        }
    }

    public final void setGetTestTime(long j) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_AGENT_TEST_TIME, Long.valueOf(j));
    }

    public final long getGetTestTime() {
        return MMKVManager.INSTANCE.getINSTANCE().getLong(KEY_AGENT_TEST_TIME, -2L);
    }

    public final void setHaveTestData(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_AGENT_TEST_DATA, Boolean.valueOf(z));
    }

    public final boolean isHaveTestData() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_AGENT_TEST_DATA, false);
    }

    public final void setHaved12Tip(boolean z) {
        MMKVManager.INSTANCE.getINSTANCE().set(KEY_12_TIP, Boolean.valueOf(z));
    }

    public final boolean isHaved12Tip() {
        return MMKVManager.INSTANCE.getINSTANCE().getBoolean(KEY_12_TIP, false);
    }
}
